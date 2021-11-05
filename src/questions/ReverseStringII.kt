package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string `s` and an integer `k`, reverse the first `k` characters for every `2k` characters counting
 * from the start of the string.
 *
 * If there are fewer than `k` characters left, reverse all of them.
 * If there are less than `2k` but greater than or equal to `k` characters,
 * then reverse the first `k` characters and left the other as original.
 *
 * [Source](https://leetcode.com/problems/reverse-string-ii/)
 */
@UseCommentAsDocumentation
private fun reverseStr(s: String, k: Int): String {
    val sb = StringBuilder(s)
    if (sb.length < k) {
        return sb.reverse().toString()
    }
    var startIndex = 0
    var endIndex = k - 1
    while (startIndex <= sb.lastIndex) {
        val remaining = sb.length - startIndex
        if (remaining < k) {
            val reverse = sb.subSequence(startIndex, sb.length).reversed().toString()
            sb.replace(startIndex, endIndex + 1, reverse)
        } else {
            val reverse = sb.subSequence(IntRange(startIndex, endIndex)).reversed()
            sb.replace(startIndex, endIndex + 1, reverse.toString())
        }
        startIndex += 2 * k
        endIndex = startIndex + k - 1
    }

    return sb.toString()
}

fun main() {
    reverseStr(s = "abcdefg", 3) shouldBe "cbadefg"
    reverseStr("abcdefg", 2) shouldBe "bacdfeg"
    reverseStr(s = "abcd", k = 2) shouldBe "bacd"
}