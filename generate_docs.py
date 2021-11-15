import abc
import glob
import re
from typing import Dict, List, Union, Type
from abc import ABC, abstractmethod
from generate_file_history import git_generate_history
from datetime import datetime

ignore_filenames = {'__init__.py', 'write_docs.py', 'generate_docs.py', 'generate_file_history.py'}

header = """
# algorithms :robot:

A collection of solution to the data structure and algorithm problems

[View web version](https://prashantbarahi.com.np/docs/algorithms/intro) | [View by tags](https://prashantbarahi.com.np/docs/tags/) 

"""

footer = """
___
<sub>This README was auto-generated during pre-commit.</sub>
 
##### IntelliJ Live Template
```
import _utils.UseCommentAsDocumentation

/**
 * $END$
 * [Source](https://leetcode.com/problems/$camelCase$/)
*/
@UseCommentAsDocumentation
private fun solution() {
}
    
fun main() {
}
```
where `camelCase`=`lowercaseAndDash(fileName())`
"""

register_headers = []


def __find_files_with_extension(extension: str):
    for file_path_ in glob.glob(extension, recursive=True):
        if re.match('python/venv/*', file_path_):
            continue
        splits = file_path_.split('/')
        filename = splits[-1]

        if filename in ignore_filenames:
            continue
        register_headers.append(file_path_)


__find_files_with_extension('**/*.java')
__find_files_with_extension('**/*.kt')
__find_files_with_extension('**/*.py')


def __write_markdown(content: str):
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
            if re.match(f'def {self.filename}*', line) or \
                    (
                            line.strip().startswith('def') and line.endswith(':') and self.filename in line
                    ):  # method detection
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
            return '\n'.join(self.lines[docs_start:docs_end])
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
                return " <br/>".join(occurrence)
            has_read_from_comments_annotation = False
            if line.startswith(f'import _utils.UseCommentAsDocumentation'):
                has_read_from_comments_annotation = True

            if re.match(f'(private |public |)class {self.filename}*', line) \
                    or re.match(f'(private |public |)fun {function_case(self.filename)}', line) \
                    or re.match(f'(private |public |)object {self.filename}*', line) \
                    or re.match(f'@UseCommentAsDocumentation', line.strip()):
                docs_start = index
                current_line = self.lines[docs_start]
                while not current_line.startswith("/**"):
                    docs_start -= 1
                    # dont return as there might be @UseCommentAsDocumentation
                    if docs_start == 0 and not has_read_from_comments_annotation:
                        return ""
                    current_line = self.lines[docs_start]
                docs_start += 1
                docs_end = docs_start + 1
                current_line = self.lines[docs_end]

                while not (current_line.startswith('*/')):
                    docs_end = docs_end + 1
                    if docs_end >= len(self.lines) and not has_read_from_comments_annotation:
                        return ""
                    current_line = self.lines[docs_end]
                break
        if docs_start is not None and docs_end is not None:
            return '\n'.join(
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


def remove_empty_lines(string):
    lines = list(map(lambda x: x.strip(), string.split('\n')))
    result = lines[0]
    for i in range(1, len(lines)):
        # If two sequential lines breaks, then take only one
        current_line = lines[i]
        if current_line.startswith('*') or current_line.startswith(':') or current_line.startswith('!'):
            result += f' <br>{current_line}'
        elif len(current_line) == 0 and len(lines[i - 1]) != 0:
            result += f' <br>{current_line}'
        else:
            result += f' {current_line}'
    return result


if __name__ == "__main__":
    content_ = '### Table of Contents\n\n'
    paths: List[str]
    content_ += f"| Filename | Description |\n"
    content_ += f"|  --- | --- |\n"

    undocumented_files = []
    path_to_date_map: Dict[str, str] = git_generate_history()


    def sort_by_date(elem):
        return path_to_date_map.get(elem, datetime.now().strftime('%Y-%m-%d-%H:%M:%S'))


    path_sorted_by_date = sorted(register_headers, key=sort_by_date, reverse=True)
    for path in path_sorted_by_date:
        split = path.split('/')
        file_name_with_extension = split[-1]
        directory = split[-2]

        filename_, ext_ = file_name_with_extension.split('.')
        docs = None
        with open(path, "r") as f:
            file_text = f.read()
            strategy = docs_strategy.get(ext_)
            if strategy is not None:
                docs = strategy(file_text, filename_).extract_docs()
        if docs is not None:
            docs = remove_empty_lines(docs)
            content_ += f"|  [{filename_}]({path}) <br><sub>{lang[ext_]} &#8226; {directory}</sub> | <sup>{docs}</sup> |\n"
            if len(docs) == 0:
                undocumented_files.append(path)

    with open("README.md", "w") as f:
        __write_markdown(content_)
    print(f'Undocumented files: {len(undocumented_files)}')
    with open("UNDOCUMENTED.txt", "w") as file:
        file.write('\n'.join(undocumented_files))
