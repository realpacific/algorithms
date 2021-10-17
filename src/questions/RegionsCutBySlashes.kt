package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

private const val LEFT = '/'
private const val RIGHT = '\\'

/**
 * An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
 * These characters divide the square into contiguous regions.
 * Given the grid `grid` represented as a string array, return the number of regions.
 * Note that backslash characters are escaped, so a '\' is represented as '\\'.
 *
 * [Explanation](https://www.youtube.com/watch?v=Wafu5vOxPRE) – [Source](https://leetcode.com/problems/regions-cut-by-slashes/)
 */
@UseCommentAsDocumentation
private fun regionsBySlashes(grid: Array<String>): Int {
    val dots = grid.size + 1

    // this is basically a union find problem
    // if there's a cycle, then add one to the result
    val unionFind = UnionFind(dots)

    // Connect the borders
    for (i in 0 until dots) {
        for (j in 0 until dots) {
            if (i == 0 || i == dots - 1 || j == 0 || j == dots - 1) {
                val cellNo = i * dots + j
                if (cellNo != 0)
                    unionFind.union(0, cellNo)
            }
        }
    }

    // the border is a region
    var result = 1

    for (i in 0..grid.lastIndex) {
        val row = grid[i].toCharArray()
        for (j in 0..row.lastIndex) {
            val isCycle = when (row[j]) {
                LEFT -> {
                    val cellNoTop = (i * dots) + (j + 1)
                    val cellNoBottom = (i + 1) * dots + (j)
                    unionFind.union(cellNoTop, cellNoBottom)
                }
                RIGHT -> {
                    val cellNoTop = (i * dots) + (j)
                    val cellNoBottom = (i + 1) * dots + (j + 1)
                    unionFind.union(cellNoTop, cellNoBottom)
                }
                else -> {
                    false
                }
            }
            if (isCycle) {
                result++
            }
        }
    }
    return result
}

private class UnionFind(n: Int) {
    private val parent = Array(n * n) { it }
    private val rank = Array(n * n) { 1 }

    fun union(x: Int, y: Int): Boolean {
        val lx = find(x)
        val ly = find(y)
        if (lx == ly) {
            return true
        }
        if (rank[lx] == rank[ly]) {
            parent[lx] = ly
            rank[ly]++
        } else if (rank[lx] > rank[ly]) {
            parent[ly] = lx
        } else if (rank[lx] < rank[ly]) {
            parent[lx] = ly
        }
        return false
    }

    fun find(x: Int): Int {
        if (parent[x] == x) {
            return x
        }
        val temp = find(parent[x])
        parent[x] = temp
        return temp
    }

}

fun main() {
    regionsBySlashes(arrayOf(" /", "/ ")) shouldBe 2
    regionsBySlashes(arrayOf(" /", "  ")) shouldBe 1
    regionsBySlashes(arrayOf("\\/", "/\\")) shouldBe 4
    regionsBySlashes(arrayOf("/\\", "\\/")) shouldBe 5
    regionsBySlashes(arrayOf("//", "/ ")) shouldBe 3
}