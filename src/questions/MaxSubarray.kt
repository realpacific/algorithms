package questions

import _utils.UseCommentAsDocumentation
import utils.assertAllWithArgs

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
    return nums.maxOrNull()!!
}

private fun maxSubArrayII(nums: IntArray): Int {
    var best = 0
    var sum = 0
    for (i in 0..nums.lastIndex) {
        sum = maxOf(nums[i], nums[i] + sum)
        best = maxOf(best, sum)
    }
    return best
}

private fun maxSubArrayIII(nums: IntArray): Int {
    var best = 0
    for (i in 0..nums.lastIndex) {
        var sum = 0
        for (j in i..nums.lastIndex) {
            sum += nums[j]
            best = maxOf(best, sum)
        }
    }
    return best
}


fun main() {
    assertAllWithArgs(
        6,
        argsProducer = { intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4) },
        ::maxSubArray,
        ::maxSubArrayII,
        ::maxSubArrayIII
    )
    assertAllWithArgs(
        23,
        argsProducer = { intArrayOf(5, 4, -1, 7, 8) },
        ::maxSubArray,
        ::maxSubArrayII,
        ::maxSubArrayIII
    )
}