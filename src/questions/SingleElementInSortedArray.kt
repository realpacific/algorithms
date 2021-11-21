package questions


import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in `O(log n)` time and `O(1)` space.
 *
 * [Source](https://leetcode.com/problems/single-element-in-a-sorted-array/)
 */
@UseCommentAsDocumentation
private fun singleNonDuplicate(nums: IntArray): Int {

    /**
     * Theorem:
     *
     * If no single element exists till index i such that nums[i] == nums[i + 1], then i must be even.
     * i.e. starting index must always be even.
     *
     *  0  1   2   3   4   5   6   7   8
     * _________________________________
     *  1  1   2   2   3   3   4   4  (5)
     * (1) 2   2   3   3   4   4   5   5
     * 
     * Using this fact, do binary search
     */
    fun findSingleNonDuplicate(low: Int, high: Int): Int {
        if (high == low && high > nums.lastIndex) {
            return nums.last()
        }
        if (low < 0) {
            return nums.first()
        }
        val midIndex = (low + high) / 2
        val rightElemIndex = midIndex + 1
        val leftElemIndex = midIndex - 1
        if (nums[midIndex] == nums.getOrNull(rightElemIndex)) { // is mid == right element?
            val startingIndex = midIndex // if yes, mid is the starting element
            if (startingIndex % 2 == 0) { // if mid is even, then single element is at its right
                return findSingleNonDuplicate(startingIndex + 2, high)
            } else {
                return findSingleNonDuplicate(low, startingIndex - 1) // else at its left
            }
        } else if (nums.getOrNull(leftElemIndex) == nums[midIndex]) { // if left is the starting index
            val startingIndex = leftElemIndex
            if (startingIndex % 2 == 0) { // is starting index even?
                return findSingleNonDuplicate(startingIndex + 2, high) // if yes, then no single element at left
            } else {
                return findSingleNonDuplicate(low, startingIndex - 1) // single element must exists at left
            }
        } else {
            return nums[midIndex] // found the single element
        }
    }
    return findSingleNonDuplicate(0, nums.size)
}


fun main() {
    singleNonDuplicate(nums = intArrayOf(1, 1, 2, 2, 3, 3, 4, 4, 5)) shouldBe 5
    singleNonDuplicate(nums = intArrayOf(3, 3, 7, 7, 10, 11, 11)) shouldBe 10

    singleNonDuplicate(nums = intArrayOf(1, 1, 2, 3, 3, 4, 4, 8, 8)) shouldBe 2
    singleNonDuplicate(nums = intArrayOf(3, 3, 7, 10, 10, 11, 11)) shouldBe 7
    singleNonDuplicate(nums = intArrayOf(3, 3, 7, 7, 10, 10, 11, 11, 12)) shouldBe 12
    singleNonDuplicate(nums = intArrayOf(1, 3, 3, 7, 7, 10, 10, 11, 11)) shouldBe 1
}