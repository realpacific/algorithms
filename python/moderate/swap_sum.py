from typing import Tuple, List, Optional


def swap_sum(arr_a: List[int], arr_b: List[int]) -> Optional[Tuple]:
    sum_a = sum(arr_a)
    sum_b = sum(arr_b)

    # sum_a - a + b = sum_b - b + a
    # a - b = (sum_a - sum_b) / 2
    diff = sum_a - sum_b
    if diff % 2 != 0:
        return None
    target = int(diff / 2)

    for i in arr_a:
        for j in arr_b:
            if i - j == target:
                return i, j
    return None


def __check_swap(swap: Tuple, arr_a: List[int], arr_b: List[int]):
    assert sum(arr_a) - swap[0] + swap[1] == sum(arr_b) - swap[1] + swap[0]


if __name__ == '__main__':
    a = [4, 1, 2, 1, 1, 2]
    b = [3, 6, 3, 3]
    __check_swap(swap_sum(a, b), a, b)
