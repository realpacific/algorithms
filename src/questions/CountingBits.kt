package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer `n`, return an array `ans` of length `n + 1` such that for each `i` (0 <= i <= n),
 * `ans[i]` is the number of 1's in the binary representation of `i`.
 *
 * [Source](https://leetcode.com/problems/counting-bits/)
 */
@UseCommentAsDocumentation
private fun countBits(n: Int): IntArray {
    val ans = IntArray(n + 1) { 0 }
    (0..n).forEach {
        ans[it] = countBitsOnDigit(it)
    }
    return ans
}

private fun countBitsOnDigit(n: Int): Int {
    var count = 0
    var num = n
    while (num != 0) {
        if (num.and(1) == 1) count++ // get LSB
        num = num.shr(1)
    }
    return count
}

fun main() {
    // 0 --> 0
    // 1 --> 1
    // 2 --> 10
    countBits(n = 2) shouldBe intArrayOf(0, 1, 1)

    // 0 --> 0
    // 1 --> 1
    // 2 --> 10
    // 3 --> 11
    // 4 --> 100
    // 5 --> 101
    countBits(n = 5) shouldBe intArrayOf(0, 1, 1, 2, 1, 2)
}