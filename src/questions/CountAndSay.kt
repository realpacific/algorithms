package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.math.BigInteger

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * * `countAndSay(1)` = "1"
 * * `countAndSay(n)` is the way you would "say" the digit string from `countAndSay(n-1)`,which is then converted into a different digit string.
 *
 * To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring
 * contains exactly one unique digit. Then for each substring, say the number of digits, then say the digit.
 * Finally, concatenate every said digit.
 *
 * [Source](https://leetcode.com/problems/count-and-say/)
 */
@UseCommentAsDocumentation
private fun countAndSay(n: Int): String {
    if (n == 1) { // base case
        return "1"
    }
    return _countAndSay(n, 2, "1")
}

typealias CharToConsecutiveCount = Pair<Char, Int>

private fun _countAndSay(n: Int, current: Int, value: String): String {
    if (current > n) {
        return value
    }
    return _countAndSay(n, current + 1, say(count(value.toBigInteger())))
}

/**
 * Count the number of occurrence of a character in a consecutive manner
 */
private fun count(n: BigInteger): List<CharToConsecutiveCount> {
    val str = n.toString()
    var prevInt = str[0]
    var count = 1
    val result = mutableListOf<Pair<Char, Int>>()

    for (index in 1..str.length) {
        if (index == str.length) {
            result.add(Pair(prevInt, count))
            break
        }
        if (prevInt == str[index]) {
            count++
        } else {
            result.add(Pair(prevInt, count))
            prevInt = str[index]
            count = 1
        }
    }
    return result
}

private fun say(charToCount: List<CharToConsecutiveCount>): String {
    val result = StringBuilder()
    charToCount.forEach {
        result.append(it.second.toString()).append(it.first) // occurrence + character

    }
    return result.toString()
}


fun main() {
    countAndSay(11) shouldBe "11131221133112132113212221"
    countAndSay(10) shouldBe "13211311123113112211"
    countAndSay(1) shouldBe "1"
    countAndSay(2) shouldBe "11"
    countAndSay(3) shouldBe "21"
    countAndSay(4) shouldBe "1211"
    countAndSay(5) shouldBe "111221"
}