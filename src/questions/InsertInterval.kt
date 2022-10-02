package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame
import java.util.*


/**
 * You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [starti, endi]`
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by `starti`.
 * You are also given an interval `newInterval = [start, end]` that represents the start and end of another interval.
 *
 * Insert `newInterval` into `intervals` such that `intervals` is still sorted in ascending order by `starti` and
 * `intervals` still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * Return `intervals` after the insertion.
 *
 * [Source](https://leetcode.com/problems/insert-interval/)
 */
@UseCommentAsDocumentation
private fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    if (intervals.isEmpty()) {
        return arrayOf(newInterval)
    }

    fun hasOverlap(current: IntArray, intervalInHand: IntArray): Boolean {
        val range1 = current.first()..current.last()
        val range2 = intervalInHand.first()..intervalInHand.last()
        return intervalInHand[0] in range1 || intervalInHand[1] in range1 || current[0] in range2 || current[1] in range2
    }

    fun isSelfInBetweenPrevAndCurrent(prev: IntArray, current: IntArray, self: IntArray): Boolean {
        return self.first() > prev.last() && self.last() < current.first()
    }


    fun isLessThanFirst(): Boolean {
        return newInterval.last() < intervals.first().last()
    }

    fun isHigherThanLast(): Boolean {
        return newInterval.first() > intervals.last().last()
    }


    fun merge(vararg _intervals: IntArray): IntArray {
        return intArrayOf(_intervals.map { it[0] }.minOrNull()!!, _intervals.map { it[1] }.maxOrNull()!!)
    }

    val result = arrayListOf<IntArray>()

    fun getLatestAddedElem(): IntArray {
        return result.last()
    }

    // check if whether [newInterval] should be inserted before [intervals] i.e. no overlaps with the first
    if (isLessThanFirst() && !hasOverlap(intervals.first(), newInterval)) {
        result.add(newInterval)
        result.addAll(intervals)
        return result.toTypedArray()
    }
    // check if whether [newInterval] should be inserted after [intervals] i.e. no overlaps with the last
    if (isHigherThanLast() && !hasOverlap(intervals.last(), newInterval)) {
        result.addAll(intervals)
        result.add(newInterval)
        return result.toTypedArray()
    }

    // add first interval immediately
    // the latest value of [result] is what we will be merging with [newInterval]
    result.add(intervals.first())

    // loop till the size not the index as it might be required to merge [newIntervals] with the last element
    for (i in 1..intervals.size) {

        // [newInterval] has overlap with [result]'s latest so merge them and replace
        if (hasOverlap(getLatestAddedElem(), newInterval)) {
            val latestAddedInterval = result.removeAt(result.lastIndex) // remove
            result.add(merge(latestAddedInterval, newInterval))         // merge and add
        }

        // now do same for the [current]
        val current = intervals.getOrNull(i)

        if (current != null) {  // since we are looping till size
            if (hasOverlap(current, getLatestAddedElem())) {
                // [current] overlaps with the [result] so merge them
                val latestAddedInterval = result.removeAt(result.lastIndex)
                result.add(merge(latestAddedInterval, current))
            } else {
                // [current] doesn't require merging so, it should be added to [result]

                // However, it is possible that the [newInterval] lies in between [current] & [result] WITHOUT overlaps
                //
                // In this case, the [newInterval] is added as it is to the final [result] and then [current] is added
                if (isSelfInBetweenPrevAndCurrent(prev = getLatestAddedElem(), current = current, self = newInterval)) {
                    result.add(newInterval)
                }
                result.add(current)
            }
        }
    }
    return result.toTypedArray()
}

fun main() {

    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(3, 5), intArrayOf(12, 15)),
            newInterval = intArrayOf(6, 6)
        ).toList(),
        expected = arrayOf(intArrayOf(3, 5), intArrayOf(6, 6), intArrayOf(12, 15)).toList()
    )


    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 2)),
            newInterval = intArrayOf(2, 7)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 7)).toList()
    )

    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 5)),
            newInterval = intArrayOf(1, 7)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 7)).toList()
    )

    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 5)),
            newInterval = intArrayOf(2, 7)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 7)).toList()
    )
    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)),
            newInterval = intArrayOf(2, 5)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 5), intArrayOf(6, 9)).toList()
    )
    assertIterableSame(
        actual = insert(
            intervals = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 10),
                intArrayOf(12, 16)
            ),
            newInterval = intArrayOf(4, 8)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 10), intArrayOf(12, 16)).toList()
    )

    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(3, 5)),
            newInterval = intArrayOf(1, 2)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 2), intArrayOf(3, 5)).toList()
    )

    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(3, 5)),
            newInterval = intArrayOf(3, 5)
        ).toList(),
        expected = arrayOf(intArrayOf(3, 5)).toList()
    )


    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 5)),
            newInterval = intArrayOf(2, 3)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 5)).toList()
    )





    assertIterableSame(
        actual = insert(
            intervals = arrayOf(intArrayOf(1, 5)),
            newInterval = intArrayOf(6, 8)
        ).toList(),
        expected = arrayOf(intArrayOf(1, 5), intArrayOf(6, 8)).toList()
    )


}