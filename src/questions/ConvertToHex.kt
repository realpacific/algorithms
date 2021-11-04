package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer num, return a string representing its hexadecimal representation.
 * For negative integers, two’s complement method is used.
 * All the letters in the answer string should be lowercase characters,
 * and there should not be any leading zeros in the answer except for the zero itself.
 * [Source](https://leetcode.com/problems/convert-a-number-to-hexadecimal/) – [solution](https://leetcode.com/problems/convert-a-number-to-hexadecimal/discuss/824192/Java-100-Time-with-Detailed-Explanation)
 */
@UseCommentAsDocumentation
private fun toHex(num: Int): String {
    if (num in 0..9) {
        return "$num"
    }
    val hexMap = mapOf<Int, Char>(
        10 to 'a',
        11 to 'b',
        12 to 'c',
        13 to 'd',
        14 to 'e',
        15 to 'f'
    )
    var temp = num

    var result = ""
    for (i in 1..8) {
        if (temp == 0) {
            break
        }
        val last4bits = temp.and(0b1111)
        if (last4bits <= 9) {
            result = last4bits.toString() + result
        } else {
            result = hexMap[last4bits].toString() + result
        }
        temp = temp.shr(4)
    }
    return result
}

fun main() {
    toHex(26) shouldBe "1a"
    toHex(27) shouldBe "1b"
    toHex(10) shouldBe "a"
    toHex(1) shouldBe "1"
    toHex(-1) shouldBe "ffffffff"
}