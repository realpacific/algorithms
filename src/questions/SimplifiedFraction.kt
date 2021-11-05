package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSameInAnyOrder
import utils.shouldBe

/**
 * Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive)
 * such that the denominator is less-than-or-equal-to n. The fractions can be in any order.
 *
 * [Source](https://leetcode.com/problems/simplified-fractions/)
 */
@UseCommentAsDocumentation
private fun simplifiedFractions(n: Int): List<String> {
    var numerator = 1
    var denumerator = 2

    val result = mutableListOf<String>()
    while (true) {
        if (denumerator > n) {
            break
        }
        if (numerator == 1) {
            result.add("$numerator/$denumerator")
        } else if (findGCD(numerator, denumerator) == 1) { // check if [numerator] & [denumerator] can be divided
            result.add("$numerator/$denumerator") // add if not divisible
        }
        if (denumerator == n) {
            numerator++
            denumerator = numerator + 1
        } else {
            denumerator++
        }
    }
    return result
}

fun findGCD(a: Int, b: Int): Int {
    var (high, low) = if (a > b) Pair(a, b) else Pair(b, a)
    while (high > low) {
        val remainder = high % low
        if (remainder == 0) return low
        high = low
        low = remainder
    }
    return low
}

fun main() {
    simplifiedFractions(2) shouldBe listOf("1/2")
    assertIterableSameInAnyOrder(actual = simplifiedFractions(3), expected = listOf("1/2", "1/3", "2/3"))
    assertIterableSameInAnyOrder(actual = simplifiedFractions(4), expected = listOf("1/2", "1/3", "1/4", "2/3", "3/4"))
    assertIterableSameInAnyOrder(
        actual = simplifiedFractions(6),
        expected = listOf("1/2", "1/3", "1/4", "1/5", "1/6", "2/3", "2/5", "3/4", "3/5", "4/5", "5/6")
    )
}