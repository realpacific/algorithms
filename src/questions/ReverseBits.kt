package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * [Source](https://leetcode.com/problems/reverse-bits/) – [Solution](https://leetcode.com/problems/reverse-bits/discuss/54746/Java-Solution-and-Optimization)
 */
@UseCommentAsDocumentation
fun reverseBits(n: Int): Int {
    var bit = n
    var result = 0b0
    for (i in 0..31) {
        result += bit.and(0b1)
        bit = bit ushr 1
        if (i < 31)
            result = result shl 1
    }
    return result
}

fun reverseBitsII(n: Int): Int {
    var bit = n
    var result = 0b0
    for (i in 0..31) {
        val lsb = bit.and(1) // get LSB
        bit = bit.shr(1) // shift right
        result = result.shl(1).or(lsb) // result shift left then or it with lsb
    }
    return result
}

fun main() {
    reverseBitsII(0b00000010100101000001111010011100) shouldBe 964176192
    reverseBits(0b00000010100101000001111010011100) shouldBe 964176192
}