package algorithmdesignmanualbook.sorting

import utils.shouldBe

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
    return if (j - low == medianIndex) {
        array[j]
    } else if (j - low > medianIndex) {
        fastMedian(array, low, j - 1, medianIndex)
    } else {
        fastMedian(array, j + 1, high, medianIndex - j - 1 + low)
    }
}

private fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {
        val j = partition(array, low, high)
        quickSort(array, low, j)
        quickSort(array, j + 1, high)
    }
}

private fun partition(array: IntArray, low: Int, high: Int): Int {
    fun swap(index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }

    val pivot = array[low]
    var i = low
    var j = high

    do {
        while (array[i] <= pivot) i++
        while (array[j] > pivot) j--
        if (i < j) swap(i, j)
    } while (i < j)
    swap(low, j)
    return j
}

fun midpointIndex(array: IntArray): Int {
    return (array.size / 2)
}

fun main() {
    run {
        val original = intArrayOf(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
        val expected = original.sorted().toIntArray()
        val quickSortInput = original.clone()
        quickSort(quickSortInput, 0, original.lastIndex)
        quickSortInput shouldBe expected

        fastMedian(original, 0, original.lastIndex, midpointIndex(original)) shouldBe 5
    }

    run {
        val original = intArrayOf(-90, 11, 30, 44, 20, 12, 22, -10, 1, 8, 90, 7, 10)
        fastMedian(original, 0, original.lastIndex, midpointIndex(original)) shouldBe 11
    }
}
