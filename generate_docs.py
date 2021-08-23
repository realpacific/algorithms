import glob
import re
from typing import Dict, List

ignore_filenames = ['__init__.py']

header = """
# algorithms :robot:

Collection of Data Structures and Algorithms solutions


"""

footer = """
#### Generate docs using `python3 generate_docs.py`
"""

register_headers: Dict[str, List[str]] = {}


def exec(extension: str):
    for file in glob.glob(extension, recursive=True):
        if re.match('python/venv/*', file):
            continue
        split = file.split('/')
        header = split[len(split) - 2]
        if header not in register_headers:
            register_headers[header] = []
        register_headers[header].append(file)
        register_headers[header] = register_headers[header]


if __name__ == "__main__":
    exec('**/*.java')
    exec('**/*.kt')
    exec('**/*.py')
    mkdown = ''
    for (key, paths) in register_headers.items():
        mkdown += f"##### {key}\n"
        for file_path in paths:
            split = file_path.split('/')
            file = split[len(split) - 1]
            if file in ignore_filenames:
                continue
            mkdown += f"* [{file.split('.')[0]}]({file_path})\n"
        mkdown += '\n'

    with open("README.md", "w") as f:
        f.write(header)
        f.write(mkdown)
        f.write(footer)
