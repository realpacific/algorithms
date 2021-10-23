package questions

import _utils.UseCommentAsDocumentation
import algorithmdesignmanualbook.print
import utils.shouldBe

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * [Source](https://leetcode.com/problems/move-zeroes/)
 */
@UseCommentAsDocumentation
private fun moveZeroes(nums: IntArray) {
    if (nums.size == 1) {
        return
    }
    var countOfZeros = 0
    for (i in 0..nums.lastIndex) {
        val current = nums[i]
        if (current == 0) {
            countOfZeros++ // count number of zeros
            continue
        }
        nums[i - countOfZeros] = current // move non-zero elements left by the total number of zeros encountered
    }
    while (countOfZeros > 0) {
        nums[nums.lastIndex - countOfZeros + 1] = 0 // start placing all zeros to the end
        countOfZeros--
    }
}

fun main() {
    run {
        val input = intArrayOf(0, 1, 0, 3, 12)
        moveZeroes(input)
        input.print() shouldBe intArrayOf(1, 3, 12, 0, 0)
    }
}