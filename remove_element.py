# https://leetcode.com/problems/remove-element/
from timeit import default_timer as timer

def remove_element(nums, val):
    start = timer()
    i = 0
    while True:
        if i >= len(nums):
            break
        if nums[i] == val:
            del nums[i]
            i -= 1
            if i < 0:
                i = 0
        else:
            i += 1
    print(nums, timer() - start)


remove_element([3, 2, 2, 3], 3)
remove_element([0, 1, 2, 2, 3, 0, 4, 2], 2)
remove_element([3, 2, 2, 3, 2, 3, 3, 3, 3, 33, 3, 3, 3, 3, 3, 32, 2, 2, 2, 1, 1, 1], 3)
