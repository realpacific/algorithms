package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, handle multiple queries of the following type:
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * [Source](https://leetcode.com/problems/range-sum-query-immutable/)
 */
@UseCommentAsDocumentation
private class NumArray(nums: IntArray) {
    val sumArray = IntArray(nums.size) { 0 }
    val input = nums

    init {
        var cumulativeSum = 0
        nums.forEachIndexed { index, i ->
            cumulativeSum += i
            sumArray[index] = cumulativeSum
        }
    }

    fun sumRange(left: Int, right: Int): Int {
        val leftSum = sumArray[left]
        val rightSum = sumArray[right]
        return rightSum - leftSum + input[left]
    }
}

fun main() {
    run {
        val obj = NumArray(intArrayOf(-2, 0, 3, -5, 2, -1))
        obj.sumRange(0, 2) shouldBe 1
        obj.sumRange(2, 5) shouldBe -1
        obj.sumRange(0, 5) shouldBe -3
    }
}