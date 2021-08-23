package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

/**
 * O(nlogn) algorithm for finding whether there exists a pair of elements, one from S1 and one
 * from S2, that add up to x
 */
private fun twoPairSum(array1: Array<Int>, array2: Array<Int>, x: Int): Pair<Int, Int>? {
    // O(n)
    val map: Map<Int, Int> = array1.associateBy { it }
    // O(n)
    array2.forEach {
        val diff = x - it
        // O(1)
        if (map.containsKey(diff)) {
            return Pair(diff, it)
        }
    }
    return null
}


/**
 * @suppress
 */
fun main() {
    assertTrue {
        twoPairSum(
            arrayOf(4, 1, 3, 5, 1),
            arrayOf(-2, 2, -1, 0, 3),
            1
        ).print() == Pair(3, -2)
    }
    assertTrue {
        twoPairSum(
            arrayOf(4, 1, 3, 5, 1),
            arrayOf(-2, 2, -1, 0, 3),
            5
        ).print() == Pair(3, 2)
    }

}