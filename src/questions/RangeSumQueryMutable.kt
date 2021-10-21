package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, handle multiple queries of the following types:
 * * Update the value of an element in nums.
 * * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * [Source](https://leetcode.com/problems/range-sum-query-mutable/)
 */
@UseCommentAsDocumentation
private class NumArrayMut(nums: IntArray) {
    val sumArray = IntArray(nums.size) { 0 }
    val input = nums

    init {
        var cumulativeSum = 0
        nums.forEachIndexed { index, i ->
            cumulativeSum += i
            sumArray[index] = cumulativeSum
        }
    }

    fun update(index: Int, `val`: Int) {
        input[index] = `val`
        var cumulativeSum = sumArray.getOrNull(index - 1) ?: 0
        for (i in index..input.lastIndex) {
            cumulativeSum += input[i]
            sumArray[i] = cumulativeSum
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
        val numArray = NumArrayMut(intArrayOf(1, 3, 5))
        numArray.sumRange(0, 2) shouldBe 9
        numArray.update(1, 2);   // nums = [1, 2, 5]
        numArray.sumRange(0, 2) shouldBe 8
    }
}