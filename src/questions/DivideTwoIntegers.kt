package questions

import _utils.UseCommentAsDocumentation
import algorithmdesignmanualbook.print
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note: Assume we are dealing with an environment that could only store integers
 * within the 32-bit signed integer range: [−2^31, 2^31 − 1].
 * For this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 *
 * https://leetcode.com/problems/divide-two-integers/
 */
@UseCommentAsDocumentation
private fun divide(dividend: Int, divisor: Int): Int {
    if (dividend == 0) return 0
    if (divisor == 1) return dividend
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        // overflow
        return Integer.MAX_VALUE
    }
    val neg = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)
    // Convert to long because -2,147,483,648 .. 2,147,483,647 i.e abs(MIN) doesn't fit on int
    var m = abs(dividend.toLong())
    val n = abs(divisor.toLong())
    var result = 0
    while (m >= n) {
        m -= n
        result++
    }
    // Can't multiply
    // Negate using (* -1)
    // https://stackoverflow.com/a/64325038/8389034
    result = if (neg) result.inv() + 1 else (result)
    return result
}


fun main() {
    assertEquals(-1073741824, divide(dividend = -2147483648, divisor = 2).print())
    assertEquals(-2147483648, divide(dividend = -2147483648, divisor = 1).print())
    assertEquals(2147483647, divide(dividend = -2147483648, divisor = -1).print())
    assertEquals(-2, divide(dividend = 7, divisor = -3).print())
    assertEquals(-1, divide(dividend = 1, divisor = -1).print())
    assertEquals(-1, divide(dividend = -1, divisor = 1).print())
    assertEquals(3, divide(dividend = 10, divisor = 3).print())
    assertEquals(10, divide(dividend = 50, divisor = 5).print())
}