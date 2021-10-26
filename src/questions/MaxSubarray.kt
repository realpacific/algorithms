package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * [Source](https://leetcode.com/problems/maximum-subarray/) – [Solution](https://leetcode.com/problems/maximum-subarray/discuss/20396/Easy-Python-Way)
 */
@UseCommentAsDocumentation
private fun maxSubArray(nums: IntArray): Int {
    for (i in 1..nums.lastIndex) {
        if (nums[i - 1] > 0) { // if previous sum is -ve, then no use for it so keep the ith index as it is i.e. discard the prev sums
            nums[i] += nums[i - 1] // mutate with sum of positives
        }
    }
    return nums.max()!!
}

fun main() {
    maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)) shouldBe 6
    maxSubArray(intArrayOf(5, 4, -1, 7, 8)) shouldBe 23
}