package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*


/**
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2
 * and all are unique.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
 * and determine the next greater element of nums2[j] in nums2. If there is no next greater element,
 * then the answer for this query is -1.
 *
 * [Source](https://leetcode.com/problems/next-greater-element-i/)
 */
@UseCommentAsDocumentation
private fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    // Since nums1 values are unique, so order is maintained
    val nums1Set = nums1.toSet()

    // This will hold sorted value of nums2 as it is seen
    val sortedSeenSet = TreeSet<Int>()

    val result = IntArray(nums1.size) { -1 }

    // how many of values in [nums1] is seen and updated in [result]
    var count = result.size

    for (i in nums2.lastIndex downTo 0) {     // Start from last
        val current = nums2[i]
        sortedSeenSet.add(current) // add it to sorted data structure

        val indexInNums1 = nums1Set.indexOf(current)

        if (indexInNums1 != -1) { // [current] is present in [nums1]
            // [current] is already added in [sortedSeenSet]
            // Find where it is there
            val indexInSeenSet = sortedSeenSet.indexOf(current)

            // Find the next best answer which is right after
            val nextElement = sortedSeenSet.elementAtOrNull(indexInSeenSet + 1)
            if (nextElement == null) {
                // there is no next greater value;
                // this may be the biggest value
                result[indexInNums1] = -1
            } else {
                // even though [nextElement] is the next biggest value in ascending order,
                //  there might be other larger values in between [current] and [nextElement]
                // for eg:
                var startFrom = i + 1
                while (startFrom <= nums2.lastIndex && nums2[startFrom] != nextElement && nums2[startFrom] < current) {
                    startFrom++
                }
                result[indexInNums1] = nums2[startFrom]
            }

            count--
        }

        if (count == 0) {
            break
        }
    }
    return result
}

private fun nextGreaterElementSimple(nums1: IntArray, nums2: IntArray): IntArray {
    // Since nums1 values are unique, so order is maintained
    val nums1Set = nums1.toSet()

    val result = IntArray(nums1.size) { -1 }

    // how many of values in [nums1] is seen and updated in [result]
    var count = result.size

    for (i in nums2.lastIndex downTo 0) {     // Start from last
        val current = nums2[i]

        val indexInNums1 = nums1Set.indexOf(current)

        if (indexInNums1 != -1) { // [current] is present in nums1
            var startFrom = i + 1
            // find the greater element from current index
            while (nums2.getOrNull(startFrom) != null && nums2[startFrom] < current) {
                startFrom++
            }
            // if not found, use -1
            result[indexInNums1] = nums2.getOrNull(startFrom) ?: -1

            count--
        }

        if (count == 0) {
            break
        }
    }
    return result
}


fun main() {
    nextGreaterElement(
        nums1 = intArrayOf(1, 3, 5, 2, 4),
        nums2 = intArrayOf(6, 5, 4, 3, 2, 1, 7)
    ) shouldBe intArrayOf(7, 7, 7, 7, 7)

    nextGreaterElementSimple(
        nums1 = intArrayOf(1, 3, 5, 2, 4),
        nums2 = intArrayOf(6, 5, 4, 3, 2, 1, 7)
    ) shouldBe intArrayOf(7, 7, 7, 7, 7)

    //Explanation: The next greater element for each value of nums1 is as follows:
    //  4 is underlined in nums2 = (1,3,4,2). There is no next greater element, so the answer is -1.
    //  1 is underlined in nums2 = (1,3,4,2). The next greater element is 3.
    //  2 is underlined in nums2 = (1,3,4,2). There is no next greater element, so the answer is -1.
    nextGreaterElement(nums1 = intArrayOf(4, 1, 2), nums2 = intArrayOf(1, 3, 4, 2)) shouldBe intArrayOf(-1, 3, -1)
    nextGreaterElementSimple(nums1 = intArrayOf(4, 1, 2), nums2 = intArrayOf(1, 3, 4, 2)) shouldBe intArrayOf(-1, 3, -1)

    // Explanation: The next greater element for each value of nums1 is as follows:
    // 2 is underlined in nums2 = (1, 2, 3, 4).The next greater element is 3.
    // 4 is underlined in nums2 = (1, 2, 3, 4).There is no next greater element, so the answer is-1.
    nextGreaterElement(nums1 = intArrayOf(2, 4), nums2 = intArrayOf(1, 2, 3, 4)) shouldBe intArrayOf(3, -1)
    nextGreaterElementSimple(nums1 = intArrayOf(2, 4), nums2 = intArrayOf(1, 2, 3, 4)) shouldBe intArrayOf(3, -1)

}