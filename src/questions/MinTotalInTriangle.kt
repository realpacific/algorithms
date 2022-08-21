package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import kotlin.math.min

/**
 * Given a triangle array, return the minimum path sum from top to bottom.

 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the
 * current row, you may move to either index i or index i + 1 on the next row.
 *
 * [Source](https://leetcode.com/problems/triangle/)
 */
@UseCommentAsDocumentation
fun minimumTotal(triangle: List<List<Int>>): Int {
    val minCumulativeSumTriangle = MutableList<MutableList<Int?>>(triangle.size) {
        MutableList(it + 1) { null }
    }
    minCumulativeSumTriangle[0][0] = triangle[0][0] // initialize
    for (row in 1..triangle.lastIndex) {
        for (col in 0..triangle.last().lastIndex) {
            val currentValue = triangle.getOrNull(row)?.getOrNull(col) ?: continue

            // get previous row's col-1 and col
            // then sum it with currentValue
            // take the minimum of the two
            val minCumulativeSumYet = min(
                minCumulativeSumTriangle[row - 1].getOrNull(col)?.plus(currentValue) ?: Int.MAX_VALUE,
                minCumulativeSumTriangle[row - 1].getOrNull(col - 1)?.plus(currentValue) ?: Int.MAX_VALUE
            )
            minCumulativeSumTriangle[row][col] = minCumulativeSumYet
        }
    }
    // answer is minimum value in the last row
    return minCumulativeSumTriangle.last().filterNotNull().minOrNull()!!
}

fun main() {
    minimumTotal(listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))) shouldBe 11
    minimumTotal(listOf(listOf(-10))) shouldBe -10
}