package algorithmdesignmanualbook.searching

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

/**
 * [4-33] Algorithm to determine whether there exists an i index such as ai = i given array of {a1, a2, a3 ... an}
 * Sorted and distinct case:
 */
fun findMagicIndex(array: IntArray, low: Int, high: Int): Int? {
    if (high < low) {
        return null
    }
    val mid = (low + high) / 2
    val middleElement = array[mid]
    return if (middleElement == mid + 1) {
        println("Element is at index $mid with value ${array[mid]}")
        mid + 1
    } else if (middleElement > mid + 1) {
        findMagicIndex(array, low, mid - 1)
    } else {
        findMagicIndex(array, mid + 1, high)
    }
}


/**
 * @suppress
 */
fun main() {
    assertTrue {
        val array = intArrayOf(0, 2, 4, 5, 8, 10, 12, 19, 26)
        findMagicIndex(array, 0, array.lastIndex).print() == 2
    }
    assertTrue {
        val array = intArrayOf(0, 3, 4, 5, 8, 10, 12, 19, 26)
        findMagicIndex(array, 0, array.lastIndex).print() == null
    }

    assertTrue {
        val array = intArrayOf(-1, 0, 1, 3, 5, 10, 12, 19, 26)
        findMagicIndex(array, 0, array.lastIndex).print() == 5
    }
}