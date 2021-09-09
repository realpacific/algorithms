import glob
import re
from typing import Dict, List

ignore_filenames = {'__init__.py', 'generate_docs.py', 'Assertions.kt', 'PrintUtils.java', 'Utilities.kt'}

header = """
# algorithms :robot:

Collection of Data Structures and Algorithms solutions

"""

footer = """
 
 ### References
 
 The questions were taken from these books:
 
 * The Algorithm Design Manual by Steven Skiena
 * Algorithms in a Nutshell by George T. Heineman
 * Cracking the Coding Interview by Gayle Laakmann
 
 The source of idea/algorithm/code are referenced in the code comments.
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


if __name__ == "__main__":
    find_files_with_extension('**/*.java')
    find_files_with_extension('**/*.kt')
    find_files_with_extension('**/*.py')
    content_ = '### Contents\n'
    paths: List[str]
    for (key, paths) in register_headers.items():
        if len(paths) == 0:
            continue
        content_ += f"###### {key}\n"
        for path in paths:
            split = path.split('/')
            file = split[len(split) - 1]

            content_ += f"* [{file.split('.')[0]}]({path})\n"
        content_ += '\n'

    with open("README.md", "w") as f:
        write_markdown(content_)
