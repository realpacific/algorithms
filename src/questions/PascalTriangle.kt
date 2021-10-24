package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * <img src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif" height="150" width="150"/>
 * [Source](https://leetcode.com/problems/pascals-triangle/)
 */
@UseCommentAsDocumentation
private fun pascalTriangle(numRows: Int): List<List<Int>> {
    if (numRows == 1) {
        return listOf(listOf(1))
    }
    val result = mutableListOf<List<Int>>()
    result.add(listOf(1))
    generateTriangle(1, maxRows = numRows, previous = listOf(1), result = result)
    return result
}

private fun generateTriangle(current: Int, maxRows: Int, previous: List<Int>, result: MutableList<List<Int>>) {
    if (current == maxRows) {
        return
    }
    val currentRow = MutableList(current + 1) { 1 }

    for (i in 1..current) {     // 0 and lastIndex is always 1
        // sum of the two numbers directly above it
        currentRow[i] = (previous.getOrNull(i - 1) ?: 0) + (previous.getOrNull(i) ?: 0)
    }

    result.add(currentRow)

    generateTriangle(current + 1, maxRows, currentRow, result)
}

fun main() {
    pascalTriangle(numRows = 5) shouldBe listOf(
        listOf(1),
        listOf(1, 1),
        listOf(1, 2, 1),
        listOf(1, 3, 3, 1),
        listOf(1, 4, 6, 4, 1)
    )
    pascalTriangle(numRows = 1) shouldBe listOf(
        listOf(1),
    )

    pascalTriangle(numRows = 2) shouldBe listOf(
        listOf(1),
        listOf(1, 1),
    )
    pascalTriangle(numRows = 3) shouldBe listOf(
        listOf(1),
        listOf(1, 1),
        listOf(1, 2, 1),
    )
}