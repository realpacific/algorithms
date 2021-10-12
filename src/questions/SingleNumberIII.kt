package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSameInAnyOrder

/**
 * Given an integer array nums, in which exactly two elements appear only once and
 * all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 *
 * [Source](https://leetcode.com/problems/single-number-iii/)
 */
@UseCommentAsDocumentation
private fun singleNumber(nums: IntArray): IntArray {
    if (nums.size == 2) return nums
    val record = mutableSetOf<Int>()
    for (i in 0..nums.lastIndex) {
        val element = nums[i]
        if (record.contains(element)) {
            record.remove(element)
        } else {
            record.add(element)
        }
    }
    return intArrayOf(record.elementAt(0), record.elementAt(1))
}

fun main() {
    assertIterableSameInAnyOrder(intArrayOf(3, 5).toList(), singleNumber(intArrayOf(1, 2, 1, 3, 2, 5)).toList())
    assertIterableSameInAnyOrder(listOf(-1, 0), singleNumber(intArrayOf(-1, 0)).toList())
}