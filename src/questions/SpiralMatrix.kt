package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame

/**
 * Given an `m x n` matrix, return all elements of the `matrix` in spiral order.
 *
 * [Source](https://leetcode.com/problems/spiral-matrix) – [Solution](https://leetcode.com/problems/spiral-matrix/discuss/20571/1-liner-in-Python-%2B-Ruby)
 */
@UseCommentAsDocumentation
private fun spiralOrder(matrix: Array<IntArray>): List<Int> {
    val result = ArrayList<Int>(matrix[0].size * matrix.size)
    _spiralOrder(matrix.toMutableList(), result)
    return result
}

private fun _spiralOrder(matrix: MutableList<IntArray>, result: MutableList<Int>) {
    if (matrix.isEmpty()) {
        return
    }
    val firstRow = matrix[0]
    val remaining = matrix.slice(1..matrix.lastIndex)  // remove the first row
    firstRow.forEach {
        result.add(it)                                        // add the removed row
    }
    _spiralOrder(invert(transpose(remaining)), result)        // transpose and then invert (rotate anti-clockwise)
}

private fun transpose(matrix: List<IntArray>): MutableList<IntArray> {
    if (matrix.isEmpty()) return mutableListOf()
    val transposedMatrix = MutableList(matrix[0].size) {
        IntArray(matrix.size) { -1 }
    }

    for (row in 0..matrix.lastIndex) {
        for (col in 0..matrix[0].lastIndex) {
            transposedMatrix[col][row] = matrix[row][col]
        }
    }
    return transposedMatrix
}

private fun invert(matrix: MutableList<IntArray>): MutableList<IntArray> {
    if (matrix.isEmpty()) return mutableListOf()
    for (row in 0..matrix.lastIndex / 2) { // Gotcha: loop till half way
        val temp = matrix[row]
        matrix[row] = matrix[matrix.lastIndex - row]
        matrix[matrix.lastIndex - row] = temp
    }
    return matrix
}

fun main() {
    assertIterableSame(
        listOf(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
        spiralOrder(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12)
            )
        )
    )
    assertIterableSame(
        listOf(1, 2, 3, 6, 9, 8, 7, 4, 5),
        spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))),
    )
}