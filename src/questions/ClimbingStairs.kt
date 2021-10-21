package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * [Source](https://leetcode.com/problems/climbing-stairs/)
 */
@UseCommentAsDocumentation
private fun climbStairs(n: Int): Int {
    val memo = Array<Int?>(n + 1) { null }
    return climbStairs(n, memo)
}

private fun climbStairs(n: Int, memo: Array<Int?>): Int {
    if (n <= 0) return 0
    if (n == 1) return 1
    if (n == 2) return 2
    val memoized = memo[n]
    if (memoized != null) {
        return memoized
    }
    val value = climbStairs(n - 1, memo) + climbStairs(n - 2, memo)
    memo[n] = value
    return value
}


fun main() {
    climbStairs(44)
    // 1 step + 1 step
    // 2 steps
    climbStairs(n = 2) shouldBe 2

    // 1 step +1 step +1 step
    // 1 step +2 steps
    // 2 steps +1 step
    climbStairs(n = 3) shouldBe 3

}