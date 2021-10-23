package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * <img src="https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg" height="150" width="150"/>
 * intArrayOf(Source)(https://leetcode.com/problems/max-area-of-island)
 */
@UseCommentAsDocumentation
private fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    val seen = mutableSetOf<Pair<Int, Int>>()
    var max = 0
    for (row in 0..grid.lastIndex) {
        for (col in 0..grid[0].lastIndex) {
            max = maxOf(max, maxConnected(grid, row, col, seen))
        }
    }
    return max
}

private fun maxConnected(grid: Array<IntArray>, row: Int, col: Int, seen: MutableSet<Pair<Int, Int>>): Int {
    if (row > grid.lastIndex || row < 0 || col < 0 || col > grid[0].lastIndex) {
        return 0
    }
    val point = Pair(row, col)
    if (point in seen) {
        return 0
    }
    seen.add(point)
    if (grid[row][col] == 1) {
        return 1 + maxConnected(grid, row - 1, col, seen)
            .plus(maxConnected(grid, row + 1, col, seen))
            .plus(maxConnected(grid, row, col + 1, seen))
            .plus(maxConnected(grid, row, col - 1, seen))
    }
    return 0
}

fun main() {
    maxAreaOfIsland(
        arrayOf(
            intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
        )
    ) shouldBe 6
}