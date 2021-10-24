package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given two binary strings a and b, return their sum as a binary string.
 * [Source](https://leetcode.com/problems/add-binary/)
 */
@UseCommentAsDocumentation
private fun addBinary(a: String, b: String): String {
    val size = maxOf(a.length, b.length)
    val a_ = a.padStart(size, '0')
    val b_ = b.padStart(size, '0')
    val result = CharArray(size) { ' ' }
    var carry = 0
    for (i in (size - 1) downTo 0) {
        val top = Character.getNumericValue(a_[i]) // GOTCHA: Use Character#getNumericalValue
        val bottom = Character.getNumericValue(b_[i])
        var sum: Int
        val isAllOne = top == bottom && top == 1
        if (isAllOne) { // 1 + 1
            if (carry == 0) { // 1 + 1 + 0
                sum = 0
                carry = 1
            } else { // 1 + 1 + 1
                sum = 1
                carry = 1
            }
        } else {
            val isAllZero = top == bottom && top == 0
            if (isAllZero) { // 0 + 0
                sum = carry
                carry = 0
            } else { // 1 + 0
                if (carry == 1) { // 1 + 0 + 1
                    sum = 0
                    carry = 1
                } else { // 1 + 0 + 0
                    carry = 0
                    sum = 1
                }
            }
        }
        result[i] = if (sum == 0) '0' else '1'
    }

    val value = result.joinToString("")
    return if (carry == 1) "1$value" else value // consider remaining carry
}

fun main() {
    addBinary(a = "1", b = "111") shouldBe "1000"
    addBinary(a = "11", b = "1") shouldBe "100"
    addBinary(a = "1010", b = "1011") shouldBe "10101"

}