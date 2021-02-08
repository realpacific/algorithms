package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import kotlin.math.pow
import kotlin.test.assertTrue

/**
 * [otherValue] is the value that is NOT to be searched i.e. index of value other than the [otherValue] is searched.
 */
private fun getLeftMostOccurrence(otherValue: String, array: Array<String>, low: Int, high: Int): Int {
    if (low <= high) {
        val mid = (low + high) / 2
        if ((mid == 0 || array[mid - 1] == otherValue) && array[mid] != otherValue) {
            return mid
        } else if (array[mid] == otherValue) {
            return getLeftMostOccurrence(otherValue, array, mid + 1, high)
        } else {
            return getLeftMostOccurrence(otherValue, array, low, mid)
        }
    }
    return -1
}

/**
 * Given unbounded 0s followed by unbounded number of 1s, find the first index of transition.
 *
 * Done using ONE-SIDED BINARY SEARCH
 *
 * Solution:
 *
 * Incremental search 1,2,4,6,8... and then binary search on transition range
 */
private fun findTransitionIndex(startIndex: Int, input: Array<String>): Int? {
    var growth = 0
    var currentIndex = startIndex + 2.toDouble().pow(growth).toInt()
    val firstElement = input[0]
    while (currentIndex <= input.lastIndex) {
        if (input[currentIndex] == firstElement) {
            growth++
            val nextIndex = 2.toDouble().pow(growth).toInt()
            if (nextIndex < input.lastIndex) {
                currentIndex = nextIndex
            }
        } else {
            // binary search since there is a transition after `startIndex`
            return startIndex + getLeftMostOccurrence(firstElement, input, startIndex, input.lastIndex)
        }
    }
    return null
}

fun main() {
    run {
        val input1 = "00000000000000000001111111111111111111".formatInput()
        assertTrue { findTransitionIndex(0, input = input1).print() == input1.indexOf("1") }
    }
    run {
        val input2 = "0000000000000000000".formatInput()
        assertTrue { findTransitionIndex(0, input = input2) == null }
    }

    run {

        val input3 = "0000000000000000001".formatInput()
        assertTrue { findTransitionIndex(0, input = input3) == input3.indexOf("1") }
    }
    run {
        val input4 = "01111".formatInput()
        assertTrue { findTransitionIndex(0, input = input4) == input4.indexOf("1") }
    }
}

private fun String.formatInput() = split("").filter(String::isNotEmpty).toTypedArray()