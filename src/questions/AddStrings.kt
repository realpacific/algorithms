package questions

import kotlin.math.max
import kotlin.test.assertEquals

/**
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 * [Source](https://leetcode.com/problems/add-strings/)
 */
fun addStrings(num1: String, num2: String): String {
    val length = max(num1.length, num2.length)
    val num1_ = num1.padStart(length, '0')
    val num2_ = num2.padStart(length, '0')
    var result = ""
    var hasCarry = false
    for (i in length - 1 downTo 0) {
        // GOTCHA: DON'T USE toInt() as it returns char's ASCII value
        val sum = (Character.getNumericValue(num1_[i]) + Character.getNumericValue(num2_[i]) + if (hasCarry) 1 else 0)

        hasCarry = sum > 9
        result = if (hasCarry) {
            (sum % 10).toString() + result
        } else {
            sum.toString() + result
        }
    }
    // Remaining carry over
    if (hasCarry) {
        result = "1$result"
    }
    return result
}

fun main() {
    assertEquals("10", addStrings(num1 = "1", num2 = "9"))
    assertEquals("134", addStrings(num1 = "11", num2 = "123"))
    assertEquals("533", addStrings(num1 = "456", num2 = "77"))
    assertEquals("0", addStrings(num1 = "0", num2 = "0"))
}