package questions

import kotlin.test.assertEquals

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * [Source](https://leetcode.com/problems/single-number/)
 */
private fun singleNumber(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    val record = mutableSetOf<Int>()
    // forEach loop was slower
    for (i in 0..nums.lastIndex) {
        if (record.contains(nums[i])) record.remove(nums[i])
        else record.add(nums[i])
    }
    return record.first()
}

fun main() {
    assertEquals(1, singleNumber(intArrayOf(2, 2, 1)))
    assertEquals(4, singleNumber(intArrayOf(4, 1, 2, 1, 2)))
    assertEquals(1, singleNumber(intArrayOf(1)))
}