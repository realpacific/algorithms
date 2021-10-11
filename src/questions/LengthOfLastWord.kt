package questions

import kotlin.test.assertEquals

/**
 * Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 *
 * [Source](https://leetcode.com/problems/length-of-last-word)
 */
fun lengthOfLastWord(s: String): Int {
    val lastIndex = s.lastIndex
    var result = 0
    for (i in lastIndex downTo 0) {
        if (s[i] != ' ') {
            result++
        } else if (result != 0 && s[i] == ' ') {
            break
        }
    }
    return result
}

fun main() {
    assertEquals(5, lengthOfLastWord("Hello World"))
    assertEquals(4, lengthOfLastWord("   fly me   to   the moon  "))
    assertEquals(6, lengthOfLastWord("luffy is still joyboy"))
}