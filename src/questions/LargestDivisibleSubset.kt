package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBeOneOf

/**
 * Given a set of distinct positive integers nums, return the largest subset answer
 * such that every pair (`answer[i]`, `answer[j]`) of elements in this subset satisfies:
 *
 * `answer[i] % answer[j] == 0`, or
 * `answer[j] % answer[i] == 0`
 * If there are multiple solutions, return any of them.
 *
 * [Source](https://leetcode.com/problems/largest-divisible-subset/)
 *
 * @see LongestIncreasingSubSeq
 *
 * @see LengthOfLongestIncreasingSubSeq
 */
@UseCommentAsDocumentation
private fun largestDivisibleSubset(nums: IntArray): List<Int> {
    if (nums.size == 1) return nums.toList()
    nums.sort()
    var indexOfLargestSubsequence = 0
    val sizes = IntArray(nums.size) { 1 }
    for (i in 1..nums.lastIndex) {
        for (j in 0 until i) {
            if (nums[i] % nums[j] == 0 && sizes[i] <= sizes[j]) {
                sizes[i] = 1 + sizes[j]
            }
        }
        if (sizes[indexOfLargestSubsequence] < sizes[i]) {
            indexOfLargestSubsequence = i
        }
    }

    val totalSize = sizes[indexOfLargestSubsequence]
    val result = IntArray(totalSize) { -1 }
    var current = totalSize
    for (i in result.lastIndex downTo 0) {
        for (j in sizes.lastIndex downTo 0) {
            val rightElementInSubset = result.getOrNull(i + 1)
            // [2,3,4,8] ---- LIS ---> [1,1,2,3] so need to check once again
            val shouldBeInSubset = if (rightElementInSubset == null) true else rightElementInSubset % nums[j] == 0
            if (sizes[j] == current && shouldBeInSubset) {
                result[i] = nums[j]
                current--
                break
            }
        }
    }
    return result.toList()
}


fun main() {
    largestDivisibleSubset(intArrayOf(2, 3, 4, 8)) shouldBeOneOf listOf(listOf(2, 4, 8))
    largestDivisibleSubset(intArrayOf(1, 2, 4, 8, 9, 72)) shouldBeOneOf listOf(listOf(1, 2, 4, 8, 72))
    largestDivisibleSubset(intArrayOf(3, 4, 16, 8)) shouldBeOneOf listOf(listOf(4, 8, 16))

    largestDivisibleSubset(intArrayOf(4, 8, 10, 240)) shouldBeOneOf listOf(listOf(4, 8, 240))
    largestDivisibleSubset(intArrayOf(1, 2, 3)) shouldBeOneOf listOf(listOf(1, 2), listOf(1, 3))
    largestDivisibleSubset(intArrayOf(1, 2, 3, 6, 9, 12)) shouldBeOneOf
            listOf(listOf(1, 3, 6, 12), listOf(1, 2, 6, 12))
    largestDivisibleSubset(intArrayOf(9, 75, 12, 18, 90, 4, 36, 8, 28, 2)) shouldBeOneOf
            listOf(listOf(2, 4, 12, 36))
}