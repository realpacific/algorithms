package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 * [Source](https://leetcode.com/problems/power-of-four)
 */
@UseCommentAsDocumentation
private fun isPowerOfFour(n: Int): Boolean {
    if (n == 1) return true
    var remainder = n
    while (remainder > 4) {
        if (remainder % 4 != 0) {
            return false
        }
        // remainder /= 4
        remainder = remainder.shr(2)
    }
    return remainder == 4
}

fun main() {
    isPowerOfFour(n = 17) shouldBe false
    isPowerOfFour(n = 16) shouldBe true
    isPowerOfFour(n = 4) shouldBe true
    isPowerOfFour(n = 8) shouldBe false
    isPowerOfFour(n = 1) shouldBe true
}