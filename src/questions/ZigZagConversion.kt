package questions

import utils.PrintUtils
import java.lang.Integer.max
import kotlin.math.ceil
import kotlin.test.assertEquals

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * ```
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * ```
 * And then read line by line: "PAHNAPLSIIGYIR"
 * [Source](https://leetcode.com/problems/zigzag-conversion/)
 */
fun zigZagConversion(s: String, numRows: Int): String {
    if (numRows == 1) {
        return s
    }
    // Use matrix to keep track
    val matrix: Array<Array<Char?>> = Array<Array<Char?>>(numRows) { _ ->
        Array<Char?>(max(1, ceil(s.length / 2.0).toInt())) { _ ->
            null
        }
    }
    var col = 0
    var strIndex = 0
    var row = 0
    // Set where the head is going (diagonally upwards or downwards)
    var isDirUp = false
    while (true) {
        if (strIndex > s.lastIndex) {
            break
        }
        matrix[row][col] = s[strIndex]
        strIndex++

        if (row > 0 && row % (numRows - 1) == 0) {
            // If at the bottom, move right
            col++
        } else if (isDirUp) {
            // Diagonal move right
            col++
        }

        if (isDirUp) row-- else row++

        if (row >= numRows - 1) {
            // at the bottom so move upwards now
            isDirUp = true
        } else if (row <= 0) {
            // at the top so move downwards
            isDirUp = false
        }
    }
    PrintUtils.printAnyArrWithCast(matrix.toList(), "-")
    val result = StringBuilder()
    for (i in 0..matrix.lastIndex) {
        for (j in 0..matrix[0].lastIndex) {
            result.append(matrix[i][j] ?: "")
        }
    }
    return result.toString()
}

fun main() {
    assertEquals("ACB", zigZagConversion("ABC", 2))
    assertEquals("A", zigZagConversion("A", 2))

    //
    //  P   A   H   N
    //  A P L S I I G
    //  Y   I   R
    //
    assertEquals("PAHNAPLSIIGYIR", zigZagConversion("PAYPALISHIRING", 3))

    //  P     I    N
    //  A   L S  I G
    //  Y A   H R
    //  P     I
    assertEquals("PINALSIGYAHRPI", zigZagConversion("PAYPALISHIRING", 4))

    // P Y A I H R N
    // A P L S I I G
    assertEquals("PYAIHRNAPLSIIG", zigZagConversion("PAYPALISHIRING", 2))

    assertEquals("A", zigZagConversion("A", 1))
}