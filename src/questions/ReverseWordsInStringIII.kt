package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.lang.StringBuilder

/**
 * Given a string s, reverse the order of characters in each word within a sentence
 * while still preserving whitespace and initial word order.
 *
 * [Source](https://leetcode.com/problems/reverse-words-in-a-string-iii/)
 */
@UseCommentAsDocumentation
private fun reverseWords(s: String): String {
    if (s.length == 1) {
        return s
    }
    val sb = StringBuilder(s)
    var wordBegin = 0
    var wordEnd = wordBegin // To consider for single letter words, start from same position as [wordBegin]
    while (wordEnd < sb.length && wordBegin < sb.length) {
        var wordEndChar: Char? = sb.getOrNull(wordEnd) ?: break
        while (wordEndChar != ' ') { // until you find a white space, move right
            wordEnd++
            wordEndChar = sb.getOrNull(wordEnd)
            if (wordEndChar == null || wordEndChar == ' ') { // end of the string reached || found a white space
                wordEnd--
                break
            }
        }
        // [wordBeing, wordEnd] contains a word
        swap(sb, begin = wordBegin, end = wordEnd) // swap in current word
        wordBegin = wordEnd + 2 // move ahead of white space
        wordEnd = wordBegin
    }
    return sb.toString()
}

private fun swap(sb: StringBuilder, begin: Int, end: Int) {
    var startFrom = begin
    var endAt = end
    while (startFrom < endAt) {
        val temp = sb[startFrom]
        sb[startFrom] = sb[endAt]
        sb[endAt] = temp
        startFrom++
        endAt--
    }
}

fun main() {
    reverseWords(s = "a b d ee$#% aef\$ea eaef eaj ae##ea'fe") shouldBe "a b d %#\$ee ae\$fea feae jae ef'ae##ea"
    reverseWords(s = "I love u") shouldBe "I evol u"
    reverseWords(s = "God Ding") shouldBe "doG gniD"
    reverseWords(s = "Let's take LeetCode contest") shouldBe "s'teL ekat edoCteeL tsetnoc"

}