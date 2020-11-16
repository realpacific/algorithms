from typing import List


def pairs_with_sum(array: List, required_sum: int):
    result = []
    array.sort()

    first = 0
    last = len(array) - 1

    while first < last:
        s = array[first] + array[last]
        if s == required_sum:
            result.append((array[first], array[last]))
            first += 1
            last -= 1
        elif s > required_sum:
            last -= 1
        elif s < required_sum:
            first += 1
    print(result)
    return result


if __name__ == '__main__':
    pairs_with_sum([-2, -1, 0, 3, 5, 6, 7, 9, 13, 14], required_sum=11)
