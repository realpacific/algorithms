package questions


import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an array of strings words, return the words that can be typed
 * using letters of the alphabet on only one row of American keyboard.
 * [Source](https://leetcode.com/problems/keyboard-row/)
 */
@UseCommentAsDocumentation
private fun findWords(words: Array<String>): Array<String> {
    val result = ArrayList<String>(words.size)
    val rows = arrayOf("qwertyuiop", "asdfghjkl", "zxcvbnm")
    val charIndexMap = HashMap<Char, Int>(26)
    rows.forEachIndexed { index, str ->
        str.toCharArray().forEach {
            charIndexMap[it] = index
        }
    }
    words.forEach {
        if (it.toCharArray().map { char -> charIndexMap[char.toLowerCase()] }.distinct().size == 1) {
            result.add(it)
        }
    }
    return result.toTypedArray()
}

fun main() {
    findWords(arrayOf("Hello", "Alaska", "Dad", "Peace")) shouldBe arrayOf("Alaska", "Dad")
    findWords(arrayOf("omk")) shouldBe arrayOf()
    findWords(arrayOf("adsdf", "sfd")) shouldBe arrayOf("adsdf", "sfd")
}