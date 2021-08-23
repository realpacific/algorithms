package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import kotlin.test.assertFails
import kotlin.test.asserter


/**
 * 4-20
 * Rearrange an array of n keys so that all the negative keys precede all the nonnegative keys
 */
fun arrangeNegativeThenPositiveNumber(array: Array<Int>): Array<Int> {
    var negIndex = 0

    for (i in 0..array.lastIndex) {
        if (array[i] < 0) {
            swap(array, i, negIndex)
            negIndex++
        }
    }
    return array
}

/**
 * @suppress
 */
fun main() {
    run {
        arrayOf(1, 2, 3, 4).assertNegativeBeforePositive()
        arrayOf(-1,-2, 1, 2, 3, 4).assertNegativeBeforePositive()
        assertFails { arrayOf(-1,-2, 1, -3 ,2, 3, 4).assertNegativeBeforePositive() }
    }

    arrangeNegativeThenPositiveNumber(arrayOf(1, 2, 3, 4, 5, -1, -2, -3, -5, 9)).print().assertNegativeBeforePositive()
    arrangeNegativeThenPositiveNumber(arrayOf(-1, -2, -3, -5, 1, 2, 3, 4, 5, 9)).print().assertNegativeBeforePositive()
    arrangeNegativeThenPositiveNumber(arrayOf(-1, -2, -3, -5)).print().assertNegativeBeforePositive()
    arrangeNegativeThenPositiveNumber(arrayOf(2, 2, 2, 21, 2, 3, 5)).print().assertNegativeBeforePositive()
    arrangeNegativeThenPositiveNumber(arrayOf()).print().assertNegativeBeforePositive()
}

private fun <T : Number> Array<T>.assertNegativeBeforePositive() {
    if (this.isEmpty()) {
        return
    }
    var hasTransitionOccurred = false
    for (i in 0..lastIndex) {
        val currentElement: T = this[i]
        if (hasTransitionOccurred) {
            if (currentElement.toFloat() < 0f) {
                asserter.fail("Negative number occurred after positive")
            }
        }
        if (currentElement.toFloat() > 0f) {
            hasTransitionOccurred = true
        }
    }
}

private fun swap(array: Array<Int>, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}