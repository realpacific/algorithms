package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame


/**
 *  Given a positive integer n, generate an n x n matrix filled with elements from 1 to n^2 in spiral order.
 *
 *  <img src="https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg" height="150" width="150"/>
 */
@UseCommentAsDocumentation
private fun generateMatrix(n: Int): Array<IntArray> {
    val result = Array(n) {
        IntArray(n) { -1 }
    }

    var current = 1

    var up = 0
    var left = 0
    var down = n - 1
    var right = n - 1
    while (current <= n * n) {
        for (i in left..right) {
            result[left][i] = current
            current++
        }

        for (i in (up + 1)..down) {
            result[i][down] = current
            current++
        }

        for (i in (right - 1) downTo left) {
            result[right][i] = current
            current++
        }

        for (i in (down - 1) downTo (up + 1)) {
            result[i][up] = current
            current++
        }

        left++
        right--
        up++
        down--
    }

    return result
}

fun main() {
    assertIterableSame(
        expected = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(8, 9, 4),
            intArrayOf(7, 6, 5)
        ).toList(),
        actual = generateMatrix(3).toList()
    )

    assertIterableSame(
        expected = arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(12, 13, 14, 5),
            intArrayOf(11, 16, 15, 6),
            intArrayOf(10, 9, 8, 7),
        ).toList(),
        actual = generateMatrix(4).toList()
    )
    assertIterableSame(
        expected = arrayOf(intArrayOf(1)).toList(),
        actual = generateMatrix(1).toList()
    )
}