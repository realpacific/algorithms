package algorithmdesignmanualbook.sorting

import java.util.*
import kotlin.collections.ArrayList
import kotlin.test.assertEquals

fun main() {
    val elements = "INSERTIONSORT".split("").filter(String::isNotBlank).also(::println)
    val input = elements.toTypedArray()

    insertionSort(input)
    println(Arrays.toString(input))

    val expected = ArrayList(elements).sorted()
    expected.forEachIndexed { index, s ->
        assertEquals(expected = s, actual = expected[index])
    }
}

/**
 * How does insertion sort work?
 *  * Loop through elements
 *  * At each element, compare it with element before it
 *  * If smaller, swap places
 *  * Repeat
 */
private fun insertionSort(array: Array<String>) {
    for (i in 1..array.lastIndex) {
        var decrementingHead = i
        // Iterate till the start of list
        // The current element is always at the decrementingHead position
        while (decrementingHead > 0 && array[decrementingHead - 1] >= array[decrementingHead]) {
            // Swap place with element before it
            swap(array, decrementingHead - 1, decrementingHead)
            decrementingHead--
        }
    }
}


private fun swap(array: Array<String>, index1: Int, index2: Int) {
    require(index2 <= array.lastIndex && index1 <= index2)

    val temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp
}