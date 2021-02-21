package algorithmdesignmanualbook.searching

import kotlin.test.assertTrue

/**
 * [4-34]
 *  Suppose that you are given a sorted sequence of distinct integers {a1, a2, . . . , an},
 *  drawn from 1 to m where n < m. Give an O(lg n) algorithm to find an integer ≤ m
 *  that is not present in a. For full credit, find the smallest such integer.
 *
 *  Solution: Binary search into the array.
 *  Since its sorted and starts from index 1, every element at index i should have element i.
 *
 */
private fun smallestMissingNumber(array: IntArray, low: Int, high: Int): Int {
    if (low >= high) {
        // a[i] == i so missing element is the index
        return low
    }
    if (array.first() != 1) {
        return 1
    } else if (array.last() == array.size) {
        return array.lastIndex + 1 + 1
    }
    // Calibrate to find midIndex
    val midIndex = (low - 1 + high - 1) / 2
    val midItem = array[midIndex]
    // index i should have element i
    // Since elements are distinct, if condition is not met, then there is some missing element at left
    if (midItem != midIndex + 1) {
        return smallestMissingNumber(array, low, midIndex + 1 - 1)
    } else {
        // Since a[i] == i so all left elements are sequential so missing element must be at right
        return smallestMissingNumber(array, midIndex + 1 + 1, array.size)
    }
}

fun main() {
    assertTrue {
        val array = intArrayOf(2, 3, 4, 5, 8, 10, 12, 19, 26)
        smallestMissingNumber(array, 1, array.size) == 1
    }
    assertTrue {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        smallestMissingNumber(array, 1, array.size) == 10
    }
    assertTrue {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 9, 12)
        smallestMissingNumber(array, 1, array.size) == 8
    }
    assertTrue {
        val array = intArrayOf(1, 5, 6, 7, 8)
        smallestMissingNumber(array, 1, array.size) == 2
    }

    assertTrue {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 10)
        smallestMissingNumber(array, 1, array.size) == 9
    }
}