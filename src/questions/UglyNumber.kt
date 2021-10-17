package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return true if n is an ugly number.
 * [Source](https://leetcode.com/problems/ugly-number/)
 */
@UseCommentAsDocumentation
private fun isUgly(n: Int): Boolean {
    if(n == 1) return true
    if(n == 0) return false
    var number = n
    while (number % 2 == 0) number = number.shr(1)
    while (number % 3 == 0) number /= 3
    while (number % 5 == 0) number /= 5
    return number == 1
}

fun main() {
    isUgly(n = -2147483648) shouldBe false
    isUgly(n = 6) shouldBe true
    isUgly(n = 8) shouldBe true
    isUgly(n = 14) shouldBe false
}