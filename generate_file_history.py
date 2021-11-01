import subprocess


def git_generate_history():
    path_to_modified_date_map = {}
    cmd = '''
        git ls-tree -r --name-only HEAD | while read filename; do
            echo "$(git log --pretty='format:%cd' --date=format:'%Y-%m-%d-%H:%M:%S' -- $filename | tail -1) $filename"
        done
    '''
    p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    for line in p.stdout.readlines():
        modified_date, path = line.decode().rstrip().split(' ')
        path_to_modified_date_map[path] = modified_date
    return path_to_modified_date_map


if __name__ == '__main__':
    print(git_generate_history())
