package algorithmdesignmanualbook.sorting

import utils.assertArraysSame
import java.util.*


private fun swap(array: IntArray, index1: Int, index2: Int) {
    val temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp
}

private fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {
        val j = partition(array, low, high)
        quickSort(array, low, j)
        quickSort(array, j + 1, high)
    }
}

private fun partition(array: IntArray, low: Int, high: Int): Int {
    val pivot = array[low]
    var i = low
    var j = high

    // stop when ----ji----
    while (i < j) {
        // keep incrementing i till you find the element larger than pivot
        while (pivot >= array[i]) {
            i++
        }
        // keep incrementing j till you find the element smaller than pivot
        while (pivot < array[j]) {
            j--
        }

        // swap element at i and j
        if (i < j) {
            swap(array, i, j)
        }
    }
    // swap pivot and j
    swap(array, low, j)
    // return partition index
    return j
}

fun main() {
    val input1 = intArrayOf(6, 8, 1, 5, 2, 9, 11)
    quickSort(input1, 0, input1.lastIndex)
    println(Arrays.toString(input1))
    assertArraysSame(expected = arrayOf(1, 2, 5, 6, 8, 9, 11), actual = input1.toTypedArray())


    val input2 = intArrayOf(8, 1, 5, 2, 9, 11)
    quickSort(input2, 0, input2.lastIndex)
    println(Arrays.toString(input2))
    assertArraysSame(expected = arrayOf(1, 2, 5, 8, 9, 11), actual = input2.toTypedArray())
}