package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame
import kotlin.math.exp
import kotlin.math.max

/**
 * Given an array of intervals where `intervals[i] = [starti, endi]`, merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * [Source](https://leetcode.com/problems/merge-intervals/)
 */
@UseCommentAsDocumentation
private fun merge(intervals: Array<IntArray>): Array<IntArray> {
    if (intervals.size == 1) return intervals

    sortIntervalsByFirstElement(intervals)              // sort ascending

    val result = arrayListOf<IntArray>()

    for (i in 1..intervals.lastIndex) {
        val prev = result.lastOrNull() ?: intervals[0]  // to compare with last element in the result
        val current = intervals[i]

        if (current[0] <= prev[1]) {                    // has overlap
            if (result.isNotEmpty()) {
                result.removeAt(result.lastIndex)       // remove the last added
            }
            val mergeInterval = intArrayOf(prev[0], max(current[1], prev[1]))  // merge
            result.add(mergeInterval)
        } else {
            // if no overlaps
            if (result.isEmpty()) {
                result.add(prev) // in case of first element in interval
            }
            result.add(current)
        }
    }
    return result.toTypedArray()
}

private fun sortIntervalsByFirstElement(intervals: Array<IntArray>) {
    intervals.sortBy { it[0] }
}

fun main() {
    assertIterableSame(
        actual = merge(arrayOf(intArrayOf(1, 4), intArrayOf(0, 2), intArrayOf(3, 5))).toList(),
        expected = arrayOf(intArrayOf(0, 5)).toList()
    )

    assertIterableSame(
        actual = merge(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3))).toList(),
        expected = arrayOf(intArrayOf(1, 4)).toList()
    )
    assertIterableSame(
        actual = merge(arrayOf(intArrayOf(1, 4), intArrayOf(5, 6))).toList(),
        expected = arrayOf(intArrayOf(1, 4), intArrayOf(5, 6)).toList()
    )
    assertIterableSame(
        actual = merge(arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))).toList(),
        expected = arrayOf(intArrayOf(1, 6), intArrayOf(8, 10), intArrayOf(15, 18)).toList()
    )
    assertIterableSame(
        actual = merge(arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))).toList(),
        expected = arrayOf(intArrayOf(1, 5)).toList()
    )


}