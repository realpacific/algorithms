package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are given an `m x n` matrix `mat` and two integers `r` and `c`
 * representing the number of rows and the number of columns of the wanted reshaped matrix.
 * The reshaped matrix should be filled with all the elements of the original matrix
 * in the same row-traversing order as they were.
 *
 * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix;
 * Otherwise, output the original matrix.
 *
 * [Source](https://leetcode.com/problems/reshape-the-matrix/)
 */
@UseCommentAsDocumentation
private fun matrixReshape(mat: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
    val col = mat[0].size
    val row = mat.size
    if (r * c != col * row) {
        return mat
    }
    val result = Array<IntArray>(r) { IntArray(c) { -1 } }
    var rowIndex = 0
    var colIndex = 0
    for (i in 0 until row) {
        for (j in 0 until col) {
            if (colIndex >= c) {
                rowIndex++
                colIndex = 0
            }
            result[rowIndex][colIndex] = mat[i][j]
            colIndex++
        }
    }
    return result
}

fun main() {
    run() {
        val result = matrixReshape(mat = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)), r = 1, c = 4)
        val expected = arrayOf(intArrayOf(1, 2, 3, 4))
        result.forEachIndexed { index, ints ->
            ints shouldBe expected[index]
        }
    }

    run {
        val result = matrixReshape(
            mat = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ), r = 2, c = 4
        )

        val expected = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )

        result.forEachIndexed { index, ints ->
            ints shouldBe expected[index]
        }
    }
}