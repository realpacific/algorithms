package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*


/**
 * Given an array of integers `temperatures` represents the daily temperatures, return an array answer
 * such that `answer[i]` is the number of days you have to wait after the `ith` day to get a warmer temperature.
 * If there is no future day for which this is possible, keep `answer[i] == 0` instead.
 *
 * [Source](https://leetcode.com/problems/daily-temperatures/)
 */
@UseCommentAsDocumentation
private fun dailyTemperatures(temperatures: IntArray): IntArray {
    val ans = IntArray(temperatures.size) { 0 }

    // Maintain an actual index that points the next higher temperature than current
    // indices          = 0, 1, 2, 3, 4
    // temp             = 4, 9, 6, 5, 7
    // successorPointer = 1, 0, 4, 4, null
    val successorPointer = Array<Int?>(temperatures.size) { null }
    // no further higher temperature for last element
    successorPointer[successorPointer.lastIndex] = null
    for (i in temperatures.lastIndex - 1 downTo 0) {
        val currentTemp = temperatures[i]
        val nextTemp = temperatures[i + 1]
        if (nextTemp > currentTemp) {
            ans[i] = 1
            successorPointer[i] = i + 1
        } else {
            val nextIndex = findImmediateSuccessorIndex(temperatures, successorPointer, i + 1, temperatures[i])
            if (nextIndex == null) {
                ans[i] = 0 // no higher temp
            } else {
                ans[i] = nextIndex - i
                successorPointer[i] = nextIndex
            }
        }

    }
    return ans
}

private fun findImmediateSuccessorIndex(
    temperatures: IntArray,
    successorPointers: Array<Int?>,
    index: Int?,
    target: Int
): Int? {
    if (index == null) {
        return null
    }
    val nextIndex = successorPointers[index] ?: return null
    return if (temperatures[nextIndex] > target) { // found next higher temp
        nextIndex
    } else {
        // not found so go to whatever its successor is
        findImmediateSuccessorIndex(temperatures, successorPointers, nextIndex, target)
    }
}

fun main() {
    dailyTemperatures(intArrayOf(80, 34, 80, 80, 80, 34, 34, 34, 34, 34)) shouldBe
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0)
    dailyTemperatures(intArrayOf(34, 80, 80, 34, 34, 80, 80, 80, 80, 34)) shouldBe
            intArrayOf(1, 0, 0, 2, 1, 0, 0, 0, 0, 0)
    dailyTemperatures(intArrayOf(89, 62, 70, 58, 47, 47, 46, 76, 100, 70)) shouldBe
            intArrayOf(8, 1, 5, 4, 3, 2, 1, 1, 0, 0)
    dailyTemperatures(intArrayOf(30, 60, 90)) shouldBe intArrayOf(1, 1, 0)
    dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)) shouldBe intArrayOf(1, 1, 4, 2, 1, 1, 0, 0)
    dailyTemperatures(intArrayOf(30, 40, 50, 60)) shouldBe intArrayOf(1, 1, 1, 0)
}
