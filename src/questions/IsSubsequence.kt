package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * [Source](https://leetcode.com/problems/is-subsequence/)
 */
@UseCommentAsDocumentation
private fun isSubsequence(s: String, t: String): Boolean {
    if (s.isEmpty()) return true
    val tBuilder = StringBuilder(t)
    var tIndex = 0
    for (i in 0..s.lastIndex) {
        val index = tBuilder.indexOf(s[i], tIndex) // try to find index in remaining substring
        if (index == -1) {
            return false
        }
        tIndex = index + 1
    }
    return true
}

fun main() {
    isSubsequence(s = "aaaaaa", t = "bbaaaa") shouldBe false
    isSubsequence(s = "ab", t = "baab") shouldBe true
    isSubsequence(s = "acb", t = "ahbgdc") shouldBe false
    isSubsequence(s = "ltc", t = "leetcode") shouldBe true
    isSubsequence(s = "ltf", t = "leetcode") shouldBe false
    isSubsequence(s = "abc", t = "ahbgdc") shouldBe true
    isSubsequence(s = "axc", t = "ahbgdc") shouldBe false
}