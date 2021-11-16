package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import utils.shouldBeOneOf

/**
 * Given an integer array nums, return the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements
 * without changing the order of the remaining elements.
 *
 * [Explanation](https://youtu.be/mouCn3CFpgg?t=1057)
 *
 * @see LengthOfLongestIncreasingSubSeq
 */
@UseCommentAsDocumentation
private object LongestIncreasingSubSeq

private fun longestIncreasingSubsequence(nums: IntArray): IntArray {
    if (nums.size == 1) return nums
    var maxSubsequenceIndex = 0 // record the index
    val increasingSeq = IntArray(nums.size) { 1 }
    for (i in 1..nums.lastIndex) {
        for (j in 0 until i) {
            // (Is still increasing?) && (choose the best i.e. larger answer)
            if (nums[i] > nums[j] && increasingSeq[i] <= increasingSeq[j]) {
                increasingSeq[i] = 1 + increasingSeq[j]
            }
        }
        if (increasingSeq[maxSubsequenceIndex] < increasingSeq[i]) {
            maxSubsequenceIndex = i
        }
    }

    // iterate backwards from [maxSubsequenceIndex], and record every element whose [increasingSeq[i]] decreases by 1
    var numberOfItemsInLIS = increasingSeq[maxSubsequenceIndex]
    val result =
        IntArray(increasingSeq[maxSubsequenceIndex]) { -1 } // size is same as the value in [increasingSeq]'s [maxSubsequenceIndex]th index
    result[result.lastIndex] = nums[maxSubsequenceIndex]
    var resultIndex = result.lastIndex - 1
    for (i in maxSubsequenceIndex - 1 downTo 0) {
        if (increasingSeq[i] == numberOfItemsInLIS - 1) { // include it in LIS if value decreases by 1
            result[resultIndex] = nums[i]
            numberOfItemsInLIS--
            resultIndex--
        }
    }
    return result
}

fun main() {
    longestIncreasingSubsequence(intArrayOf(5, 8, 7, 1, 9)) shouldBeOneOf
            listOf(intArrayOf(5, 8, 9), intArrayOf(5, 7, 9))
    longestIncreasingSubsequence(intArrayOf(1, 1, 1, 1, 1, 1)) shouldBe intArrayOf(1)
    longestIncreasingSubsequence(intArrayOf(1, 2, 3, 4, 5, 6)) shouldBe intArrayOf(1, 2, 3, 4, 5, 6)
    longestIncreasingSubsequence(intArrayOf(1, 2, 3, 4, 5, 6, 1)) shouldBe intArrayOf(1, 2, 3, 4, 5, 6)
    longestIncreasingSubsequence(intArrayOf(3, 2, 1)) shouldBeOneOf listOf(intArrayOf(1), intArrayOf(2), intArrayOf(3))
    longestIncreasingSubsequence(intArrayOf(1, 2, 1)) shouldBe intArrayOf(1, 2)
}