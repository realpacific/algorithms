package questions

import kotlin.test.assertEquals

/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once.
 * Find the single element and return it.
 *
 * [Source](https://leetcode.com/problems/single-number-ii/)
 */
private fun singleNumber(nums: IntArray): Int {
    if (nums.size == 1) {
        return nums[0]
    }
    val record = mutableSetOf<Int>()

    // On 2nd occurrence, add it to seen
    val seen = mutableSetOf<Int>()
    for (i in 0..nums.lastIndex) {
        val element = nums[i]
        if (record.contains(element)) {
            // Since elements occur exactly thrice, remove it from record if it has already occurred
            record.remove(element)
            seen.add(element)
        } else {
            // No need to add if it's already "seen"
            if (!seen.contains(element)) {
                record.add(element)
            }
        }
    }
    return record.elementAt(0)
}

fun main() {
    assertEquals(3, singleNumber(intArrayOf(2, 2, 3, 2)))
    assertEquals(99, singleNumber(intArrayOf(0, 1, 0, 1, 0, 1, 99)))
}