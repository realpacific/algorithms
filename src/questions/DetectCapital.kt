package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * * All letters in this word are capitals, like "USA".
 * * All letters in this word are not capitals, like "leetcode".
 * * Only the first letter in this word is capital, like "Google".
 *
 *  Given a string word, return true if the usage of capitals in it is right.
 *
 *  [Source](https://leetcode.com/problems/detect-capital/)
 */
@UseCommentAsDocumentation
fun detectCapitalUse(word: String): Boolean {
    word.split(' ').filter { it.isNotBlank() }.size
    val isFirstLetterCaps = isCaps(word[0])
    var hasNonCapsLetter: Boolean? = null
    var hasNonStartingCapLetter: Boolean? = null

    for (i in 1..word.lastIndex) {
        val isCurrentCaps = isCaps(word[i])

        if (isCurrentCaps && hasNonStartingCapLetter == null) {
            hasNonStartingCapLetter = true // FF
        }
        if (!isCurrentCaps && hasNonCapsLetter == null) {
            hasNonCapsLetter = true // Ff
        }
        if (hasNonStartingCapLetter == true && !isCurrentCaps) {
            return false // there is one caps case other the first one FFf
        }
        if (!isFirstLetterCaps && isCurrentCaps) {
            return false // fF
        }
        if (hasNonCapsLetter == true && isCurrentCaps) {
            return false // fffF
        }
    }
    return true

}

private fun isCaps(char: Char): Boolean {
    return char.toInt() in 65..90
}


fun main() {
    detectCapitalUse(word = "FFFFFFFFFFFFFFFFFFFFf") shouldBe false
    detectCapitalUse("USA") shouldBe true
    detectCapitalUse(word = "FlaG") shouldBe false
    detectCapitalUse(word = "lowercase") shouldBe true
    detectCapitalUse(word = "Flag") shouldBe true
}