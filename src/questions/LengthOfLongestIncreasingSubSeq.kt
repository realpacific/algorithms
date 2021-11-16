package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 *
 * [Source](https://leetcode.com/problems/longest-increasing-subsequence/) | [Explanation](https://www.youtube.com/watch?v=mouCn3CFpgg)
 *
 * @see LongestIncreasingSubSeq
 */
@UseCommentAsDocumentation
private object LengthOfLongestIncreasingSubSeq

private fun lengthOfLIS(nums: IntArray): Int {
    if (nums.size == 1) return 1
    var maxSubsequenceSize = 1
    val increasingSeq = IntArray(nums.size) { 1 }
    for (i in 1..nums.lastIndex) {
        for (j in 0 until i) {
            // (Is still increasing?) && (choose the best i.e. larger answer)
            if (nums[i] > nums[j] && increasingSeq[i] <= increasingSeq[j]) {
                increasingSeq[i] = 1 + increasingSeq[j]
            }
        }
        maxSubsequenceSize = maxOf(maxSubsequenceSize, increasingSeq[i])
    }
    return maxSubsequenceSize
}

fun main() {

    val solutionFns: List<(IntArray) -> Int> = listOf(::lengthOfLIS)
    for (fn in solutionFns) {
        fn(intArrayOf(5, 8, 7, 1, 9)) shouldBe 3
        fn(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18)) shouldBe 4
        fn(intArrayOf(0, 1, 0, 3, 2, 3)) shouldBe 4
        fn(intArrayOf(7, 7, 7, 7, 7, 7, 7)) shouldBe 1
        fn((10 downTo 1).toList().toIntArray()) shouldBe 1
    }
}