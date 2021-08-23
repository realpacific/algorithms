package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

/**
 * NO need to sort all the items.
 * Just find the sort the items before the median index
 * Using quicksort, find the partition.
 * Then throw away the left partition if the median index lies in the right portion
 * while calibrating the new median index.
 *
 * Similar to finding the kth smallest item in the unsorted list
 */
private fun fastMedian(array: IntArray, low: Int, high: Int, medianIndex: Int): Int {
    val j = partition(array, low, high)
    return if (j - low == medianIndex - 1) {
        array[j]
    } else if (j - low > medianIndex - 1) {
        fastMedian(array, low, j - 1, medianIndex)
    } else {
        fastMedian(array, j + 1, high, medianIndex - j - 1 + low)
    }
}


/**
 * Sort the list first and then return the element at mid index
 */
private fun slowMedian(array: IntArray): Int {
    return array[midpointIndex(array)]
}

private fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {
        val j = partition(array, low, high)
        quickSort(array, low, j)
        quickSort(array, j + 1, j)
    }
}

private fun partition(array: IntArray, low: Int, high: Int): Int {
    val pivot = array[low]
    println("Partitioning on ${array.toList()} on $pivot")
    var i = low
    var j = high

    while (i < j) {
        while (array[i] <= pivot) {
            i++
        }
        while (array[j] > pivot) {
            j--
        }
        if (i < j) {
            swap(array, i, j)
        }
    }
    swap(array, low, j)
    return j
}

private fun swap(array: IntArray, index1: Int, index2: Int) {
    val temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp
}

fun midpointIndex(array: IntArray): Int {
    return array.size / 2
}

/**
 * @suppress
 */
fun main() {
    run {
        println("Input 1")
        val input1 = intArrayOf(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
        quickSort(input1, 0, input1.lastIndex)
        assertTrue {
            input1.print()
            slowMedian(input1).print() == 5
        }
    }
    run {
        println("Input 2")
        val input2 = intArrayOf(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
        fastMedian(input2, 0, input2.lastIndex, midpointIndex(input2))
    }

    run {
        println("Input 3")

        val input3 = intArrayOf(-90, 11, 30, 44, 20, 12, 22, -10, 1, 8, 90, 7, 10, 5)
        assertTrue {
            val med3 = fastMedian(input3, 0, input3.lastIndex, midpointIndex(input3))
            quickSort(input3, 0, input3.lastIndex)
            slowMedian(input3).print() == med3.print()
        }
    }
}
