package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * [Source](https://leetcode.com/problems/rotate-image-/)
 */
@UseCommentAsDocumentation
fun rotate(matrix: Array<IntArray>) {
    // 1. Find the transpose of the matrix
    // 2. Reverse the rows of the transpose

    // Transpose = swap rows & col
    transpose(matrix)
    // Reverse rows
    reverseRows(matrix)
}

private fun reverseRows(matrix: Array<IntArray>) {
    val colMaxIndex = matrix[0].lastIndex
    for (row in 0..matrix.lastIndex) {
        for (col in 0..matrix[0].lastIndex / 2) { // swap only till col midpoint
            val temp = matrix[row][colMaxIndex - col]
            matrix[row][colMaxIndex - col] = matrix[row][col]
            matrix[row][col] = temp
        }
    }
}

private fun transpose(matrix: Array<IntArray>) {
    // GOTCHA: Swap 00:00, 01:10, 02:20, 11:11, 12:21, 22:22
    for (row in 0..matrix.lastIndex) {
        for (col in row..matrix[0].lastIndex) {
            swapRowsCols(matrix, row, col)
        }
    }
}

private fun swapRowsCols(matrix: Array<IntArray>, i: Int, j: Int) {
    if (i == j) return
    val temp = matrix[i][j]
    matrix[i][j] = matrix[j][i]
    matrix[j][i] = temp
}

fun main() {
    run {
        val input = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        rotate(input)

        assertIterableSame(
            input.toList(),
            arrayOf(
                intArrayOf(7, 4, 1),
                intArrayOf(8, 5, 2),
                intArrayOf(9, 6, 3)
            ).toList()
        )
    }


    run {
        val input = arrayOf(
            intArrayOf(5, 1, 9, 11),
            intArrayOf(2, 4, 8, 10),
            intArrayOf(13, 3, 6, 7),
            intArrayOf(15, 14, 12, 16)
        )
        rotate(input)
        assertIterableSame(
            input.toList(),
            arrayOf(
                intArrayOf(15, 13, 2, 5),
                intArrayOf(14, 3, 4, 1),
                intArrayOf(12, 6, 8, 9),
                intArrayOf(16, 7, 10, 11)
            ).toList()
        )

    }

}