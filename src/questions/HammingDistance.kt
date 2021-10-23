package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, return the Hamming distance between them.
 * [Source](https://leetcode.com/problems/hamming-distance/)
 */
@UseCommentAsDocumentation
private fun hammingDistance(x: Int, y: Int): Int {
    var diff = x.xor(y) // xor = 1 when different else 0
    if (diff == 0) return 0 // both are same
    var count = 0
    while (diff > 0) {
        if (diff.and(1) == 1) { // diff AND 1 gives LSB
            count++ // count all LSB
        }
        diff = diff.shr(1) // shift [diff] right
    }
    return count
}

fun main() {
    hammingDistance(x = 1, y = 4) shouldBe 2
    hammingDistance(x = 3, y = 1) shouldBe 1
}