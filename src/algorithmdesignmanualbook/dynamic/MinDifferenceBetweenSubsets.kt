package algorithmdesignmanualbook.dynamic

import algorithmdesignmanualbook.print
import kotlin.math.abs


private fun findMinRec(array: Array<Int>, i: Int, sumCalculated: Int, sumTotal: Int): Int {
    // If we have reached last element.
    // Sum of one subset is sumCalculated,
    // sum of other subset is sumTotal-sumCalculated.
    // Return absolute difference of two sums.
    if (i == 0) {
        return abs((sumTotal - sumCalculated) - sumCalculated)
    }

    // For every item arr[i], we have two choices
    // (1) We do not include it first set
    // (2) We include it in first set
    // We return minimum of two choices
    return minOf(
        findMinRec(array, i - 1, sumCalculated + array[i - 1], sumTotal),
        findMinRec(array, i - 1, sumCalculated, sumTotal),
    )
}


/**
 * @suppress
 */
fun main() {
    val array = arrayOf(1, 6, 11, 5)
    findMinRec(array, array.size, 0, array.sum()).print("Min diff betn subset is: ")
}