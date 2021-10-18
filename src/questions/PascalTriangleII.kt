package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * @see pascalTriangle
 * [Source](https://leetcode.com/problems/pascals-triangle-ii/)
 */
@UseCommentAsDocumentation
private fun getRow(rowIndex: Int): List<Int> {
    if (rowIndex == 0) {
        return listOf(1)
    }
    return generateTriangle(1, rowIndex, previous = listOf(1))
}

private fun generateTriangle(index: Int, rowIndex: Int, previous: List<Int>): List<Int> {
    if (index > rowIndex) {
        return previous
    }
    val currentRow = MutableList(index + 1) { 1 }

    for (i in 1..index) {     // 0 and lastIndex is always 1
        // sum of the two numbers directly above it
        currentRow[i] = (previous.getOrNull(i - 1) ?: 0) + (previous.getOrNull(i) ?: 0)
    }
    return generateTriangle(index + 1, rowIndex, currentRow)
}

fun main() {
    getRow(rowIndex = 4) shouldBe listOf(1, 4, 6, 4, 1)
    getRow(rowIndex = 0) shouldBe listOf(1)
    getRow(rowIndex = 1) shouldBe listOf(1, 1)
    getRow(rowIndex = 2) shouldBe listOf(1, 2, 1)
}