package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBeOneOf

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 *
 * [Source](https://leetcode.com/problems/intersection-of-two-arrays/)
 */
@UseCommentAsDocumentation
private fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val result = mutableSetOf<Int>()
    val larger = if (nums1.size >= nums2.size) nums1 else nums2
    val smaller = if (larger === nums1) nums2 else nums1
    val positionMap = mutableMapOf<Int, MutableList<Int>>()
    smaller.forEachIndexed { index, i ->
        positionMap[i] = positionMap.getOrDefault(i, mutableListOf()).also {
            it.add(index)
        }
    }
    for (i in 0..larger.lastIndex) {
        val currentVal = larger[i]
        positionMap[currentVal]?.let { result.add(currentVal) }
    }
    return result.toIntArray()
}


fun main() {
    intersection(intArrayOf(1, 2), nums2 = intArrayOf(2, 1)) shouldBeOneOf listOf(intArrayOf(1, 2), intArrayOf(2, 1))
    intersection(intArrayOf(1, 2, 2, 1), nums2 = intArrayOf(2, 2)) shouldBeOneOf listOf(intArrayOf(2))
    intersection(intArrayOf(4, 9, 5), nums2 = intArrayOf(9, 4, 9, 8, 4)) shouldBeOneOf listOf(
        intArrayOf(9, 4),
        intArrayOf(4, 9)
    )
}