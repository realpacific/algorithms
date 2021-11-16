package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Calculate the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index
 * is equal to the sum of all the numbers strictly to the index's right.
 * Return the leftmost pivot index. If no such index exists, return -1.
 * [Source](https://leetcode.com/problems/pivot-index/)
 */
@UseCommentAsDocumentation
private fun pivotIndex(nums: IntArray): Int {
    val cumulativeSumLeft = IntArray(nums.size) { 0 }
    val cumulativeSumRight = IntArray(nums.size) { 0 }
    for (i in 0..nums.lastIndex) {
        cumulativeSumLeft[i] = (cumulativeSumLeft.getOrNull(i - 1) ?: 0) + nums[i]
        cumulativeSumRight[nums.lastIndex - i] =
            (cumulativeSumRight.getOrNull(nums.lastIndex - i + 1) ?: 0) + nums[nums.lastIndex - i]
    }
    for (i in 0..nums.lastIndex) {
        if (cumulativeSumRight[i] == cumulativeSumLeft[i]) {
            return i
        }
    }
    return -1
}

fun main() {
    pivotIndex(intArrayOf(1, 7, 3, 6, 5, 6)) shouldBe 3
    pivotIndex(intArrayOf(1, 2, 3)) shouldBe -1
    pivotIndex(intArrayOf(2, 1, -1)) shouldBe 0
}