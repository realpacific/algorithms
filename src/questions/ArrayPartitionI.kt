package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn)
 * such that the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 * [Source](https://leetcode.com/problems/array-partition-i/)
 */
@UseCommentAsDocumentation
private fun arrayPairSum(nums: IntArray): Int {
    nums.sort() // SORT
    var result = 0
    // pair max with maximum and min with other min
    for (i in 0 until nums.size / 2) {
        result += minOf(nums[2 * i], nums[2 * i + 1])
    }
    return result
}

fun main() {
    // min(1, 2) + min(3, 4) = 1 + 3 = 4
    arrayPairSum(intArrayOf(1, 4, 3, 2)) shouldBe 4

    // The optimal pairing is (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9.
    arrayPairSum(intArrayOf(6, 2, 6, 5, 1, 2)) shouldBe 9
}