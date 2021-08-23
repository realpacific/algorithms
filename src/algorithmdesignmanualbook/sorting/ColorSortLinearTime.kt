package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.sorting.ColorValue.*
import java.util.*
import kotlin.test.assertFails
import kotlin.test.asserter

/**
 * Suppose an array A consists of n elements, each of which is red, white, or blue.
 * We seek to sort the elements so that all the reds come before all the whites, which
 * come before all the blues The only operation permitted on the keys are: examine and swap.
 */
private enum class ColorValue {
    RED, WHITE, BLUE
}

private class ColorSortLinearTime(private val array: Array<ColorValue>) {
    fun examine(index: Int): ColorValue? {
        return array.getOrNull(index)
    }

    fun swap(i: Int, j: Int) {
        // println("Swapping ${array[i]} at $i & ${array[j]} at $j")
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    fun print(): Array<ColorValue> {
        println(Arrays.toString(array))
        return array.copyOf()
    }
}

/**
 * 4-19
 * Maintain two pointers- one at 0th index (redPointer) and other at last index (bluePointer).
 * When red found, swap it with redPointer & increment redPointer
 * When blue found, swap it with bluePointer & decrement bluePointer
 * move ahead one index if WHITE
 * break if redPointer exceeds bluePointer or redPointer goes out of bounds
 */
private fun sort(sorter: ColorSortLinearTime) {
    var redPointer = 0
    var bluePointer = 0
    while (sorter.examine(bluePointer) != null) {
        bluePointer++
    }
    bluePointer--
    val lastIndex = bluePointer
    var i = 0
    while (i < bluePointer + 1) {
        val currentItem = sorter.examine(i)
        if (redPointer > lastIndex || redPointer > bluePointer) {
            break
        }

        if (currentItem == RED) {
            sorter.swap(i, redPointer)
            redPointer++
        } else if (currentItem == BLUE) {
            sorter.swap(i, bluePointer)
            bluePointer--
        }
        if (sorter.examine(i) == WHITE) {
            i++
        }
    }

    sorter.print()
}

/**
 * @suppress
 */
fun main() {
    run {
        assertFails { arrayOf(1, 1, 1, 1, 2, 2, 2, 2, 1).assertSequenceAreGrouped() }
        assertFails { arrayOf(2, 1, 1, 1, 1, 2, 2, 2, 2).assertSequenceAreGrouped() }
        assertFails { arrayOf(1, 2, 2, 2, 3, 3, 4, 5, 6, 1).assertSequenceAreGrouped() }
        arrayOf(1, 2, 2, 2, 3, 3, 4, 5).assertSequenceAreGrouped()
    }
    run {
        val sorter = ColorSortLinearTime(arrayOf(BLUE, RED, WHITE, WHITE, RED, BLUE, RED, RED, RED, WHITE))
        sort(sorter)
        sorter.print().assertSequenceAreGrouped()
    }

    run {
        val sorter = ColorSortLinearTime(arrayOf(BLUE, RED, RED, BLUE, RED, RED, RED))
        sort(sorter)
        sorter.print().assertSequenceAreGrouped()
    }

    run {
        val sorter = ColorSortLinearTime(arrayOf(WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, BLUE))
        sort(sorter)
        sorter.print().assertSequenceAreGrouped()
    }

    run {
        val sorter = ColorSortLinearTime(arrayOf(WHITE, WHITE, WHITE, RED, RED, RED, BLUE))
        sort(sorter)
        sorter.print().assertSequenceAreGrouped()
    }

    run {
        val sorter = ColorSortLinearTime(arrayOf(BLUE, WHITE, WHITE, RED, RED, RED, BLUE))
        sort(sorter)
        sorter.print().assertSequenceAreGrouped()
    }
}

/**
 * Check if array contains continuous values such that each value are grouped at a position and does not occur out of cluster
 */
private fun <T> Array<T>.assertSequenceAreGrouped() {
    if (this.isEmpty()) {
        return
    }
    val mapOfOccurrence = mutableMapOf<T, Int>()
    var itemInHand = get(0)
    for (i in 0..lastIndex) {
        val currentItem = get(i)
        if (currentItem != itemInHand) {
            val lastOccurrence = mapOfOccurrence[currentItem]
            if (lastOccurrence != null) {
                asserter.fail("Not in sequence")
            }
            mapOfOccurrence[get(i - 1)] = i - 1
        }
        itemInHand = currentItem
    }
    println(mapOfOccurrence)

}