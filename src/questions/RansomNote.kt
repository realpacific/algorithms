package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * [Source](https://leetcode.com/problems/ransom-note-kt/)
 */
@UseCommentAsDocumentation
private fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val charCount = mutableMapOf<Char, Int>()
    for (i in ransomNote) {
        charCount[i] = charCount.getOrDefault(i, 0) + 1
    }
    for (i in magazine) {
        val counts = charCount[i] ?: continue
        if (counts - 1 == 0) {
            charCount.remove(i)
        } else {
            charCount[i] = counts - 1
        }
        if (charCount.isEmpty()) {
            return true
        }
    }
    return false
}

fun main() {
    canConstruct(ransomNote = "a", magazine = "b") shouldBe false
    canConstruct(ransomNote = "aa", magazine = "ab") shouldBe false
    canConstruct(ransomNote = "aa", magazine = "aab") shouldBe true
}