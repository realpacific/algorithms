package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import java.util.*
import kotlin.test.assertTrue

/**
 * Get the left most index using binary search
 */
private fun getLeftMostMatch(str: String, array: Array<String>, low: Int, high: Int): Int {
    if (low <= high) {
        val mid = (low + high) / 2
        if ((mid == 0 || array[mid - 1] != str) && array[mid] == str) {
            // if the left of mid is different than str, then this is the left most index
            return mid
        } else if (str > array[mid]) {
            return getLeftMostMatch(str, array, mid + 1, high)
        } else {
            return getLeftMostMatch(str, array, low, mid - 1)
        }
    }
    return -1
}

private fun getRightMostMatch(str: String, array: Array<String>, low: Int, high: Int): Int {
    if (low <= high) {
        val mid = (low + high) / 2
        if ((mid == array.lastIndex || array[mid + 1] != str) && array[mid] == str) {
            // if the right of mid is different than str, then this is the right most index
            return mid
        } else if (str < array[mid]) {
            return getRightMostMatch(str, array, low, mid - 1)
        } else {
            return getRightMostMatch(str, array, mid + 1, high)
        }
    }
    return -1
}

/**
 * Find the range in which [str] occurs in [array].
 *
 * [Solution here](https://tutorialspoint.dev/algorithm/searching-algorithms/count-number-of-occurrences-or-frequency-in-a-sorted-array)
 */
private fun findNumberOfOccurrence(str: String, array: Array<String>): IntRange? {
    val left = getLeftMostMatch(str, array, 0, array.lastIndex)
    // if no occurrence in left, then no such item
    if (left == -1) {
        return null
    }
    val right = getRightMostMatch(str, array, left + 1, array.lastIndex)
    // if no occurrence in left, since we started from left+1, then this is the only one item in array
    if (right == -1) {
        return left..left
    }
    return left..right
}

fun main() {
    val input = "search, sort, search, ball, sort, hello, hi, hello, hello, hello, hello, apple, string, string, zebra"
        .split(", ")
        .toTypedArray().sortedArray()
    input.print("Input", printer = Arrays::toString)

    assertTrue {
        val str = "hello"
        findNumberOfOccurrence(str, input).print("Occurrence range for $str") == 2..6
    }

    assertTrue {
        val str = "zoo"
        findNumberOfOccurrence(str, input).print("Occurrence range for $str") == null
    }

    assertTrue {
        val str = "hi"
        findNumberOfOccurrence(str, input).print("Occurrence range for $str") == 7..7
    }

    assertTrue {
        val str = "sort"
        findNumberOfOccurrence(str, input).print("Occurrence range for $str") == 10..11
    }
    assertTrue {
        val str = "zebra"
        findNumberOfOccurrence(str, input).print("Occurrence range for $str") == 14..14
    }

}