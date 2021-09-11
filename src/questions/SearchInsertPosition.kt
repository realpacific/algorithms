package questions

import algorithmdesignmanualbook.print
import kotlin.math.max
import kotlin.test.assertEquals

/**
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * [Source](https://leetcode.com/problems/search-insert-position/)
 */
fun searchInsertPosition(nums: IntArray, target: Int): Int {
    if (nums.isEmpty()) {
        return 0
    }
    val resultIndex = binarySearch(nums, 0, nums.size, target)
    return if (resultIndex.second) {
        // if [target] is found
        resultIndex.first
    } else {
        // if not found, then try to find the position it would have been in
        val index = resultIndex.first
        if (index > nums.lastIndex) {
            return nums.lastIndex + 1
        }
        val result = if (target < nums[index]) index else index + 1
        return max(0, result)
    }
}

/**
 * @return [Pair] of the best index to true if found
 */
private fun binarySearch(nums: IntArray, low: Int, high: Int, target: Int): Pair<Int, Boolean> {
    if (low == high && low < nums.lastIndex) {
        return low to (nums[low] == target)
    }
    if (low >= high) {
        return low to false
    }
    val mid = (low + high) / 2

    return if (nums[mid] == target) {
        mid to true
    } else if (target < nums[mid]) {
        binarySearch(nums, low, mid - 1, target)
    } else {
        binarySearch(nums, mid + 1, high, target)
    }
}

fun main() {
    assertEquals(0, searchInsertPosition(nums = intArrayOf(1, 3), target = 1).print())

    assertEquals(1, searchInsertPosition(nums = intArrayOf(1, 3, 5, 6), target = 2).print())
    assertEquals(1, searchInsertPosition(nums = intArrayOf(1, 3), target = 2).print())
    assertEquals(3, searchInsertPosition(nums = intArrayOf(4, 5, 6), target = 7).print())

    assertEquals(0, searchInsertPosition(nums = intArrayOf(1), target = 1).print())

    assertEquals(2, searchInsertPosition(nums = intArrayOf(1, 3, 5, 6), target = 5).print())
    assertEquals(0, searchInsertPosition(nums = intArrayOf(1, 3, 5, 6), target = 0).print())
    assertEquals(1, searchInsertPosition(nums = intArrayOf(1), target = 5).print())
    assertEquals(0, searchInsertPosition(nums = intArrayOf(), target = 5).print())
    assertEquals(1, searchInsertPosition(nums = intArrayOf(1, 3, 4), target = 2).print())
}