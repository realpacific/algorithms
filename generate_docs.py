import abc
import glob
import re
from typing import Dict, List, Union, Type
from abc import ABC, abstractmethod

ignore_filenames = {'__init__.py', 'generate_docs.py', 'Assertions.kt', 'PrintUtils.java', 'Utilities.kt'}

header = """
# algorithms :robot:

Collection of Data Structures and Algorithms solutions

"""

footer = """
 ___
 <sub>This README was auto-generated during pre-commit.</sub>
"""

register_headers: Dict[str, List[str]] = {}


def find_files_with_extension(extension: str):
    for file_path_ in glob.glob(extension, recursive=True):
        if re.match('python/venv/*', file_path_):
            continue
        splits = file_path_.split('/')
        title = splits[len(splits) - 2]
        if title not in register_headers:
            register_headers[title] = []
        filename = splits[len(splits) - 1]

        if filename in ignore_filenames:
            continue

        register_headers[title].append(file_path_)
        register_headers[title] = register_headers[title]


def write_markdown(content: str):
    f.write(header)
    f.write(content)
    f.write(footer)


class DocsStrategy(ABC):
    @abstractmethod
    def extract_docs(self):
        pass

    def __init__(self, content, filename):
        self.lines: List[str] = list(map(lambda x: x.strip(), content.split('\n')))
        self.filename = filename
        self.content = content


class PythonDocsStrategy(DocsStrategy):
    def __init__(self, content, filename):
        super().__init__(content, filename)

    def extract_docs(self):
        if len(self.lines) == 0:
            return ""
        docs_start = None
        docs_end = None
        for [index, line] in enumerate(self.lines):
            if re.match(f'def {self.filename}*', line):
                docs_start = index
                current_line = self.lines[docs_start]
                while not current_line.startswith("'''") and not current_line.startswith('"""'):
                    docs_start += 1
                    if docs_start >= len(self.lines):
                        return ""
                    current_line = self.lines[docs_start]
                docs_start += 1
                docs_end = docs_start + 1
                current_line = self.lines[docs_end]

                while not (current_line.startswith('"""') or current_line.startswith("'''")):
                    docs_end = docs_end + 1
                    if docs_end >= len(self.lines):
                        return ""
                    current_line = self.lines[docs_end]
                break
        if docs_start is not None and docs_end is not None:
            return '<br>'.join(self.lines[docs_start:docs_end])
        return ""


def function_case(name: str):
    start = name[0].lower()
    return start + name[1:]


class KotlinDocsStrategy(DocsStrategy):
    def __init__(self, content, filename):
        super().__init__(content, filename)

    def extract_docs(self):
        if len(self.lines) == 0:
            return ""
        docs_start = None
        docs_end = None
        # Ignore packages named _utils
        if self.lines[0].startswith(f'package _utils'):
            return None
        for [index, line] in enumerate(self.lines):
            if line.startswith(f'import _utils.SkipDocumentation'):
                return None

            if line.startswith(f'import _utils.Document'):
                occurrence = re.findall(r'@Document\("(.*)"\)', self.content)
                return " <br>".join(occurrence)
            if re.match(f'(private |public |)class {self.filename}*', line) \
                    or re.match(f'(private |public |)fun {function_case(filename_)}', line) \
                    or re.match(f'(private |public |)object {self.filename}*', line):
                docs_start = index
                current_line = self.lines[docs_start]
                while not current_line.startswith("/**"):
                    docs_start -= 1
                    if docs_start == 0:
                        return ""
                    current_line = self.lines[docs_start]
                docs_start += 1
                docs_end = docs_start + 1
                current_line = self.lines[docs_end]

                while not (current_line.startswith('*/')):
                    docs_end = docs_end + 1
                    if docs_end >= len(self.lines):
                        return ""
                    current_line = self.lines[docs_end]
                break
        if docs_start is not None and docs_end is not None:
            return ' <br>'.join(
                list(
                    filter(
                        lambda x: x.strip() not in {'```'},
                        map(
                            lambda x: x.replace('*', '', 1) if x.strip().startswith('*') else x,
                            self.lines[docs_start:docs_end]
                        )
                    )
                )
            )
        return ""


docs_strategy: Dict[str, Type[DocsStrategy]] = {
    'py': PythonDocsStrategy,
    'kt': KotlinDocsStrategy,
    'java': KotlinDocsStrategy,
}
lang: Dict[str, str] = {
    'py': 'Python',
    'kt': 'Kotlin',
    'java': 'Java',
}

if __name__ == "__main__":
    find_files_with_extension('**/*.java')
    find_files_with_extension('**/*.kt')
    find_files_with_extension('**/*.py')
    content_ = '### Table of Contents\n'
    paths: List[str]
    content_ += f"| Filename | Description |\n"
    content_ += f"|  --- | --- |\n"
    for (key, paths) in register_headers.items():
        if len(paths) == 0:
            continue

        # Reverse the string and sort
        sorted_paths = sorted(paths, key=lambda x: x[::-1])
        for path in sorted_paths:
            split = path.split('/')
            file = split[len(split) - 1]
            [filename_, ext_] = file.split('.')
            docs = None
            with open(path, "r") as f:
                content = f.read()
                strategy = docs_strategy.get(ext_)
                if strategy is not None:
                    docs = strategy(content, filename_).extract_docs()
            if docs is not None:
                content_ += f"|  [{filename_}]({path}) <br><sub>{lang[ext_]} &#8226; {key}</sub> | {docs} |\n"

    with open("README.md", "w") as f:
        write_markdown(content_)
