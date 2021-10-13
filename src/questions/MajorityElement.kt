package questions

import kotlin.test.assertEquals

/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * [Source](https://leetcode.com/problems/majority-element/)
 */
private fun majorityElement(nums: IntArray): Int {
    val majorityCondition = nums.size / 2
    val map = mutableMapOf<Int, Int>()
    for (i in 0..nums.lastIndex) {
        val element = nums[i]
        map[element] = (map[element] ?: 0) + 1
        if (map[element]!! > majorityCondition) {
            return element
        }
    }
    return -1
}

// Source: https://leetcode.com/problems/majority-element/discuss/51613/O(n)-time-O(1)-space-fastest-solution
private fun majorityElementOptimal(nums: IntArray): Int {
    // IF majority element occurs more than n/2 times and there's only one of it
    var majority = nums[0]
    var count = 1
    for (i in 1..nums.lastIndex) { // first element is already considered, so start from 1
        val elem = nums[i]
        if (count == 0) {
            // It wasn't the majority element
            majority = elem
            count++
        } else if (elem == majority) {
            count++
        } else {
            count--
        }
    }
    return majority
}

fun main() {
    assertEquals(1, majorityElementOptimal(intArrayOf(1, 1, 1, 1, 2, 3, 4, 5)))
    assertEquals(5, majorityElementOptimal(intArrayOf(6, 5, 5)))
    assertEquals(3, majorityElementOptimal(intArrayOf(3, 2, 3)))
    assertEquals(2, majorityElementOptimal(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
}