package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a non-negative integer x, compute and return the square root of x.
 * [Source](https://leetcode.com/problems/sqrtx/)
 */
@UseCommentAsDocumentation
private fun mySqrt(x: Int): Int {
    if (x == 1) return x
    val xLong = x.toLong() // squaring x can't fit in Int it goes beyond Integer.MAX_VALUE
    var num: Long = xLong.shr(1)
    var prev: Long = xLong

    // Find the range of possible answers by dividing by 2
    // Given 40: 20 (20x20) -> 10 (10x10=100) -> 5 (5x5=25)
    // So answer must lie between 5 & 10
    while (num * num > x) {
        prev = num // prev is used to compensate for float to int conversions eg. sqrt(6)
        num = num.shr(1)
    }
    // Check if the answer is 5
    if (num * num == x.toLong()) {
        return num.toInt()
    }
    // else traverse from 5 to 10
    var low = num
    val high = prev
    // OR do binary search here
    while (low <= high) {
        val lowSq = low * low
        if (lowSq == xLong) {
            return low.toInt()
        } else if (lowSq < x) {
            low++
        } else if (lowSq > x) {
            return (low - 1).toInt()
        }
    }
    return num.toInt()
}

fun main() {
    mySqrt(2147395599) shouldBe 46339
    mySqrt(6) shouldBe 2
    mySqrt(100) shouldBe 10
    mySqrt(50) shouldBe 7
    mySqrt(40) shouldBe 6
    mySqrt(25) shouldBe 5
    mySqrt(1) shouldBe 1
    mySqrt(64) shouldBe 8
    mySqrt(8) shouldBe 2
    mySqrt(4) shouldBe 2
}