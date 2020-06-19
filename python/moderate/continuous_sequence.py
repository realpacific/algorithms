from typing import List


def continuous_sequence(array: List[int]):
    max_sum = 0
    current_sum = 0
    for i in array:
        current_sum += i
        if max_sum < current_sum:
            max_sum = current_sum
        elif current_sum < 0:
            current_sum = 0
    print(max_sum)
    return max_sum


if __name__ == '__main__':
    continuous_sequence([2, -8, 3, -2, 4, -10])
    continuous_sequence([2, 3, -8, -1, 2, 4, -2, 3])
