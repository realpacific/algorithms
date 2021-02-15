package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.sorting.ColorValue.*
import java.util.*

/**
 * Suppose an array A consists of n elements, each of which is red, white, or blue.
 * We seek to sort the elements so that all the reds come before all the whites, which
 * come before all the blues The only operation permitted on the keys are
 */
private enum class ColorValue {
    RED, WHITE, BLUE
}

private class ColorSortLinearTime(private val array: Array<ColorValue>) {
    fun examine(index: Int): ColorValue? {
        return array.getOrNull(index)
    }

    fun swap(i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    fun print() {
        println(Arrays.toString(array))
    }
}


private fun sort(sorter: ColorSortLinearTime) {
    val blues = LinkedList<Int>()
    val whites = LinkedList<Int>()
    val reds = LinkedList<Int>()
    var index = 0

    sorter.print()
}

fun main() {
    val sorter = ColorSortLinearTime(arrayOf(BLUE, RED, WHITE, WHITE, RED, BLUE))
    sort(sorter)
}