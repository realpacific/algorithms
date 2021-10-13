package questions

import _utils.UseCommentAsDocumentation
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 * * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 *
 * [Source](https://leetcode.com/problems/happy-number)
 */
@UseCommentAsDocumentation
private fun isHappy(n: Int): Boolean {
    if (n == 1) return true
    var digit = n
    val seen = mutableSetOf<Int>()
    while (digit != 1) {
        digit = sumOfSquareDigits(digit.toString())
        if (digit == 1) {
            return true
        } else if (seen.contains(digit)) {
            return false
        }
        seen.add(digit)
    }
    return false
}

private fun sumOfSquareDigits(n: String): Int {
    var result = 0
    for (i in n) {
        val numericValue = Character.getNumericValue(i)
        result += numericValue * numericValue
    }
    return result
}

fun main() {
    assertTrue(isHappy(1))

    // For 19:
    // 12 + 92 = 82
    //82 + 22 = 68
    //62 + 82 = 100
    //12 + 02 + 02 = 1
    assertTrue(isHappy(19))

    assertFalse(isHappy(2))
}