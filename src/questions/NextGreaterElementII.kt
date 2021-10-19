package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*


/**
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 *
 * [Source](https://leetcode.com/problems/next-greater-element-ii/)
 */
@UseCommentAsDocumentation
private fun nextGreaterElement(nums: IntArray): IntArray {
    val result = IntArray(nums.size) { -1 }
    val unknownIndices: LinkedList<Int> = LinkedList<Int>() // holds indices that didn't have NGN

    val lastElem = nums.last() // this can be used to find its NGN in forward iteration
    var isLastElementNGNFound = false

    for (i in 1..nums.lastIndex) {
        val current = nums[i]
        val prev = nums[i - 1]

        // Find the [lastElem]'s NGN while iterating forward (since array is circular)
        if (!isLastElementNGNFound && prev > lastElem) { // if not already found
            result[result.lastIndex] = prev
            isLastElementNGNFound = true
        }

        // Check for every unknown index's NGN when traversing forward till lastIndex
        val iterator = unknownIndices.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (nums[next] < current) {
                // NGN found
                result[next] = current
                iterator.remove()
            }
        }

        if (prev < current) {
            // Found NGN (next greater number)
            result[i - 1] = current
        } else {
            // If not found, store the index of -1 into [unknownIndices]
            unknownIndices.addLast(i - 1) // add it to [unknownIndices]
        }
    }


    // Since NGN was not found when traversing forward, now start from 0 to unknown index to find NGN
    // since the array is circular
    while (unknownIndices.size > 1 // since there can be only one value with NGN=-1 (the highest value)
        ||
        (unknownIndices.isNotEmpty() && unknownIndices.distinct().size != 1) // what if there are multiple equal highest value
    ) {
        val unknownIndex = unknownIndices.removeLast() ?: break // get the index
        for (i in 0..unknownIndex) { // from 0 to the above index
            if (nums[i] > nums[unknownIndex]) { // try to find NGN
                result[unknownIndex] = nums[i]
                break
            }
        }
    }
    return result
}


fun main() {
    nextGreaterElement(nums = intArrayOf(1, 2, 3, 2, 1)) shouldBe intArrayOf(2, 3, -1, 3, 2)

    nextGreaterElement(nums = intArrayOf(5, 4, 3, 2, 1)) shouldBe intArrayOf(-1, 5, 5, 5, 5)

    nextGreaterElement(
        nums = intArrayOf(1, 8, -1, -100, -1, 222, 1111111, -111111)
    ) shouldBe intArrayOf(8, 222, 222, -1, 222, 1111111, -1, 1)

    nextGreaterElement(nums = intArrayOf(100, 1, 11, 1, 120, 111, 123, 1, -1, -100))

    nextGreaterElement(
        nums = intArrayOf(1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 100)
    ) shouldBe intArrayOf(2, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, -1)

    nextGreaterElement(nums = intArrayOf(1, 5, 3, 6, 8)) shouldBe intArrayOf(5, 6, 6, 8, -1)
    nextGreaterElement(nums = intArrayOf(1, 2, 1)) shouldBe intArrayOf(2, -1, 2)
    nextGreaterElement(nums = intArrayOf(1, 2, 3, 4, 3)) shouldBe intArrayOf(2, 3, 4, -1, 4)
}