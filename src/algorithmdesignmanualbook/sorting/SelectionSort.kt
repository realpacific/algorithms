package algorithmdesignmanualbook.sorting

import java.util.*

fun main() {
    val elements = "SELECTIONSORT".split("").filter(String::isNotBlank)
    val input = elements.toTypedArray()
    selectionSort(input)
    println(Arrays.toString(input))
}

/**
 * Identify the smallest element from unsorted portion and put it at the end of the sorted portion
 */
private fun selectionSort(array: Array<String>) {
    for (i in 0..array.lastIndex) {
        var smallestElementIndex = i
        for (j in (i + 1)..array.lastIndex) {
            if (array[smallestElementIndex] > array[j]) {
                smallestElementIndex = j
            }
        }
        swap(array, i, smallestElementIndex)
    }
}


private fun swap(array: Array<String>, index1: Int, index2: Int) {
    val temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp
}