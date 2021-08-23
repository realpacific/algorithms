import glob
import re
from typing import Dict, List

ignore_filenames = ['__init__.py']

header = """
# algorithms :robot:

Collection of Data Structures and Algorithms solutions


"""

footer = """
 ___
 To generate docs, use: `python3 generate_docs.py`
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
        register_headers[title].append(file_path_)
        register_headers[title] = register_headers[title]


def write_markdown(content: str):
    f.write(header)
    f.write(content)
    f.write(footer)


if __name__ == "__main__":
    find_files_with_extension('**/*.java')
    find_files_with_extension('**/*.kt')
    find_files_with_extension('**/*.py')
    content_ = ''
    paths: List[str]
    for (key, paths) in register_headers.items():
        content_ += f"##### {key}\n"
        for path in paths:
            split = path.split('/')
            file = split[len(split) - 1]
            if file in ignore_filenames:
                continue
            content_ += f"* [{file.split('.')[0]}]({path})\n"
        content_ += '\n'

    with open("README.md", "w") as f:
        write_markdown(content_)
