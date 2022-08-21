package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of
 * a given target value. If target is not found in the array, return [-1, -1].
 *
 * [Source](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
 */
@UseCommentAsDocumentation
private fun searchRange(nums: IntArray, target: Int): IntArray {
    if (nums.isEmpty() || nums.first() > target || nums.last() < target) return intArrayOf(-1, -1)
    if (nums.size <= 2) return intArrayOf(nums.indexOfFirst { it == target }, nums.indexOfLast { it == target })
    return findFirstIndex(nums, target, low = 0, high = nums.lastIndex)
}

fun findFirstIndex(nums: IntArray, target: Int, low: Int, high: Int): IntArray {
    if (low == high && nums.getOrNull(low) == target) {
        return intArrayOf(low, low)
    }
    if (low < 0 || low > high || high > nums.lastIndex) {
        return intArrayOf(-1, -1)
    }
    val mid = (low + high) / 2
    val midValue = nums[mid]
    return if (target < midValue) {
        findFirstIndex(nums, target, low, mid - 1)
    } else if (target > midValue) {
        findFirstIndex(nums, target, mid + 1, high)
    } else {
        var startIndex = mid
        while (startIndex >= 0 && nums[startIndex] == midValue) {
            startIndex--
        }
        startIndex++
        var endIndex = mid
        while (endIndex <= nums.lastIndex && nums[endIndex] == midValue) {
            endIndex++
        }
        endIndex--
        intArrayOf(startIndex, endIndex)
    }
}


fun main() {
    searchRange(nums = intArrayOf(1, 1, 2), target = 1) shouldBe intArrayOf(0, 1)
    searchRange(nums = intArrayOf(1, 2, 2), target = 2) shouldBe intArrayOf(1, 2)
    searchRange(nums = intArrayOf(1, 2, 3), target = 1) shouldBe intArrayOf(0, 0)
    searchRange(nums = intArrayOf(5, 7, 7, 8, 8, 10), target = 8) shouldBe intArrayOf(3, 4)
    searchRange(nums = intArrayOf(1, 3), target = 1) shouldBe intArrayOf(0, 0)
    searchRange(nums = intArrayOf(1), target = 1) shouldBe intArrayOf(0, 0)
    searchRange(nums = intArrayOf(5, 7, 7, 8, 8, 10), target = 6) shouldBe intArrayOf(-1, -1)
}