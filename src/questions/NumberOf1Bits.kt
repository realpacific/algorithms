package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 * [Source](https://leetcode.com/problems/number-of-1-bits/)
 */
@UseCommentAsDocumentation
private fun hammingWeight(n: Int): Int {
    var temp = n
    var count = 0
    for (i in 0..31) {
        if (temp == 0) {
            return count
        }
        if (temp.and(1) == 1) {
            count++
        }
        temp = temp.ushr(1)
    }
    return count
}

fun main() {
    hammingWeight(0b00000000000000000000000000001011) shouldBe 3
    hammingWeight(0b00000000000000000000000010000000) shouldBe 1
}