package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
 *
 * [Source](https://leetcode.com/problems/degree-of-an-array/)
 */
@UseCommentAsDocumentation
private fun findShortestSubArray(nums: IntArray): Int {
    val counts = TreeMap<Int, MutableList<Int>>()
    val priorityList = PriorityQueue<ValueWithOccurrence> { o1, o2 -> o2.count - o1.count }
    for (i in 0..nums.lastIndex) {
        val value = nums[i]
        counts[value] = counts.getOrDefault(value, mutableListOf()).also {
            it.add(i)
        }
        priorityList.add(ValueWithOccurrence(value, counts[value]!!.size))
    }
    val highestOccurringElement = priorityList.remove()
    val degree = highestOccurringElement.count

    val candidates = mutableListOf<Int>()
    candidates.add(highestOccurringElement.value)

    // find other highest occurring element
    while (priorityList.isNotEmpty()) {
        val nextElement = priorityList.remove()
        if (nextElement.count == degree) {
            candidates.add(nextElement.value)
        } else {
            break
        }
    }

    var result = Integer.MAX_VALUE
    for (i in candidates) {
        val range = counts[i]!!
        // find the smallest subarray
        result = minOf(result, range.last() - range.first() + 1)
    }
    return result
}

private data class ValueWithOccurrence(val value: Int, val count: Int)

fun main() {
    findShortestSubArray(intArrayOf(1, 1, 3, 3, 2, 2, 2, 3, 1)) shouldBe 3

    // The input array has a degree of 2 because both elements 1 and 2 appear twice.
    // Of the subarrays that have the same degree:
    // [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    // The shortest length is 2. So return 2.
    findShortestSubArray(intArrayOf(1, 2, 2, 3, 1)) shouldBe 2

    // The degree is 3 because the element 2 is repeated 3 times.
    // So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
    findShortestSubArray(intArrayOf(1, 2, 2, 3, 1, 4, 2)) shouldBe 6


}