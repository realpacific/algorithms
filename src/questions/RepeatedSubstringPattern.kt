package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending
 * multiple copies of the substring together.
 * [Source](https://leetcode.com/problems/repeated-substring-pattern/) - [Solution](https://leetcode.com/problems/repeated-substring-pattern/discuss/94334/Easy-python-solution-with-explaination)
 */
@UseCommentAsDocumentation
private fun repeatedSubstringPattern(s: String): Boolean {
    val ss = s + s // aba+aba   abab+abab
    val subStr = ss.substring(1, ss.lastIndex) // baab   bababa
    return subStr.contains(s)
}

fun main() {
    repeatedSubstringPattern(s = "aba") shouldBe false
    repeatedSubstringPattern(s = "abab") shouldBe true
    repeatedSubstringPattern(s = "abcabcabcabc") shouldBe true
}