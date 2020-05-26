import sys


def smallest_diff(a, b):
    a.sort()
    b.sort()
    result = sys.maxsize

    a_index = 0
    b_index = 0
    while a_index < len(a) and b_index < len(b):
        if abs(a[a_index] - b[b_index]) < result:
            result = abs(a[a_index] - b[b_index])
        if a[a_index] < b[b_index]:
            a_index += 1
        else:
            b_index += 1
    return result


if __name__ == '__main__':
    assert smallest_diff([1, 3, 2, 11, 15], [23, 127, 235, 19, 8]) == 3
