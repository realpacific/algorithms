package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.math.BigInteger
import java.util.*

/**
 * A robot is located in the top-left corner of a `m x n` grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * <img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" height="150" width="350"/>
 *
 * How many possible unique paths are there?
 *
 * [Source](https://leetcode.com/problems/unique-paths/)
 */
@UseCommentAsDocumentation
// Naive approach (times out for larger value)
private class UniquePathsNaive { // this times out for large value
    private enum class Dir { R, D }

    private var answer = 0

    fun uniquePaths(m: Int, n: Int): Int {
        if (m == 1 && n == 1) return 1
        traverse(Dir.R, 0, 0, m - 1, n - 1) // go right
        traverse(Dir.D, 0, 0, m - 1, n - 1) // go down
        return answer
    }

    private fun traverse(direction: Dir, posX: Int, posY: Int, rowMaxIndex: Int, colMaxIndex: Int) {
        if (posX > colMaxIndex || posY > rowMaxIndex) {
            return
        }
        if (direction == Dir.R) {
            val newPositionX = posX + 1
            if (newPositionX > colMaxIndex) {
                return
            }
            if (newPositionX == colMaxIndex && posY == rowMaxIndex) {
                answer++
            } else {
                traverse(Dir.R, newPositionX, posY, rowMaxIndex, colMaxIndex)
                traverse(Dir.D, newPositionX, posY, rowMaxIndex, colMaxIndex)
            }
        } else {
            val newPositionY = posY + 1
            if (newPositionY > rowMaxIndex) {
                return
            }
            if (posX == colMaxIndex && newPositionY == rowMaxIndex) {
                answer++
            } else {
                traverse(Dir.R, posX, newPositionY, rowMaxIndex, colMaxIndex)
                traverse(Dir.D, posX, newPositionY, rowMaxIndex, colMaxIndex)
            }
        }
    }
}

// https://leetcode.com/problems/unique-paths/discuss/254228/Python-3-solutions:-Bottom-up-DP-Math-Picture-Explained-Clean-and-Concise
// Approach III: Maintain array of arrays
private class UniquePathsArraysOfArray {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array<Array<Int>>(m) {
            Array<Int>(n) { 0 }
        }
        for (row in 0..dp.lastIndex) {
            for (col in 0..dp[0].lastIndex) {
                if (row == 0 && col == 0) {
                    dp[row][col] = 1 // only one way to get to (0,0) is moving left
                } else if (row == 0) {
                    dp[row][col] = dp[row][col - 1] // only way to get to first row is move up
                } else if (col == 0) {
                    dp[row][col] = dp[row - 1][col] // only way to get to left col is move left
                } else {
                    dp[row][col] = dp[row - 1][col] + dp[row][col - 1] // two ways
                }
            }
        }
        return dp[m - 1][n - 1]
    }
}

// another way
// Approach II: Use Combination (Yes, from high school maths)
private class UniquePathsUsingCombination {
    fun uniquePaths(m: Int, n: Int): Int {
        // there are (m-1)+(n-1) moves to get to the destination
        // Among (m+n-2) moves, you can have max of (m-1) downs and max of (n-1) rights
        // how do you combine these Ds and Rs to get to the destination?
        // C(n, r) = n!/(n-r)!/r!
        // C(m+n-2, m-1) = (m+n-2)!/(n-1)!/(m-1)!

        // GOTCHA: 50! can't fit in Long so use BigInteger :O
        val _factCache = TreeMap<Int, BigInteger?>()

        val min = minOf(m - 1, n - 1)
        val max = maxOf(m - 1, n - 1)
        val denominator = factorial(min, _factCache) * factorial(max, _factCache)
        val numerator = factorial(m + n - 2, _factCache)
        return (numerator / denominator).toInt()
    }

    /**
     * Efficient if [m] sent to it is in sorted as it starts from already calculated lesser value
     * if it exists
     */
    private fun factorial(m: Int, factCache: TreeMap<Int, BigInteger?>): BigInteger {
        if (factCache.containsKey(m)) return factCache[m]!!
        // add it to TreeMap i.e. key-sorted map
        factCache[m] = null
        // then find the lower key whose factorial has already been calculated
        val startFrom = factCache.lowerKey(m) ?: 1 // else start from 1
        var ans: BigInteger = factCache[startFrom] ?: BigInteger.ONE // fact(1)=1
        for (i in startFrom + 1..m) {
            ans = ans.multiply(i.toBigInteger())
        }
        factCache[m] = ans
        return ans
    }
}


fun main() {
    UniquePathsNaive().uniquePaths(m = 3, n = 2) shouldBe
            UniquePathsUsingCombination().uniquePaths(m = 3, n = 2) shouldBe
            UniquePathsArraysOfArray().uniquePaths(m = 3, n = 2) shouldBe 3


    UniquePathsNaive().uniquePaths(m = 7, n = 3) shouldBe
            UniquePathsUsingCombination().uniquePaths(m = 7, n = 3) shouldBe
            UniquePathsArraysOfArray().uniquePaths(m = 7, n = 3) shouldBe 28

    UniquePathsNaive().uniquePaths(m = 3, n = 3) shouldBe
            UniquePathsUsingCombination().uniquePaths(m = 3, n = 3) shouldBe
            UniquePathsArraysOfArray().uniquePaths(m = 3, n = 3) shouldBe 6

    UniquePathsArraysOfArray().uniquePaths(m = 51, n = 9) shouldBe
            UniquePathsUsingCombination().uniquePaths(m = 51, n = 9)
    // UniquePathsNaive().uniquePaths(m = 51, n = 9) // Leetcode times out for this

}