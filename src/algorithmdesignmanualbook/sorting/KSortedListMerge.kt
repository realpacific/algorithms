package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import utils.assertArraysSame

/**
 * Give an O(n log k)-time algorithm that merges k sorted lists with a total of n
 * elements into one sorted list. (Hint: use a heap to speed up the elementary O(kn)-time algorithm).
 */
private fun merge(arrays: Array<Array<Int>>, n: Int): Array<Int?> {
    val indices = Array(arrays.size) { 0 }
    // total size is the size of the result
    val result = Array<Int?>(n) { null }
    // What if i load the [arrays] in a heap?
    // Comparison between smallest elements of heap will still take O(k)
    for (i in 0 until n) { // O(n)
        val currentValues = arrays.mapIndexed { index, array -> // O(k)
            // can run out of items
            Pair(array.getOrNull(indices.getOrNull(index) ?: -1), index)
        }
        // take the min value
        val minValue = currentValues.filter { it.first != null }.minByOrNull { it.first!! }!!
        result[i] = minValue.first
        // increase the index
        indices[minValue.second] += 1
    }
    return result
}

fun main() {
    val input1 = arrayOf(1, 2, 6, 8, 10, 11)
    val input2 = arrayOf(1, 2, 7, 9, 12)
    val input3 = arrayOf(-1, 0, 3, 5)
    val input4 = arrayOf(-2, -1, 12, 22)
    val input = arrayOf(input1, input2, input3, input4)
    assertArraysSame(
        expected = merge(input, n = input.map { it.size }.sum()).print(),
        actual = arrayOf(-2, -1, -1, 0, 1, 1, 2, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 12, 22)
    )

}