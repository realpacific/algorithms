from typing import List


def sub_sort(array: List[int]):
    # Get the subsection where the elements are not sorted
    left_ordered_till = 0
    right_ordered_till = 0
    for i in range(1, len(array)):
        if array[i - 1] > array[i]:
            right_ordered_till = i - 1
            break
    for i in range(len(array) - 2, -1, -1):
        if array[i + 1] < array[i]:
            left_ordered_till = i + 1
            break
    # The elements are not sorted between left_ordered_till and right_ordered_till
    print(right_ordered_till, left_ordered_till)

    # The unsorted section
    unsorted_section = array[right_ordered_till:left_ordered_till + 1]
    smallest = min(unsorted_section)
    largest = max(unsorted_section)
    print(smallest, largest, unsorted_section)

    # Find where the smallest and largest element from the unsorted section lies in
    m = 0
    n = 0
    for i in range(1, len(array)):
        if array[i] > smallest:
            m = i
            break
    for i in range(len(array) - 1, -1, -1):
        if array[i] < largest:
            n = i
            break
    print(m, n)


if __name__ == '__main__':
    arr = [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
    sub_sort(arr)
    print(arr)
