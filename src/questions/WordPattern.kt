package questions

import utils.shouldBe

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 * [Source](https://leetcode.com/problems/word-pattern/)
 */
private fun wordPattern(pattern: String, s: String): Boolean {
    val split = s.split(" ")
    if (pattern.length != split.size) return false
    val charToWordMap = mutableMapOf<Char, String>()
    val seen = mutableSetOf<String>()

    for (i in 0..pattern.lastIndex) {
        val char = pattern[i]
        val word = split[i]
        if (!charToWordMap.containsKey(char)) {
            if (seen.contains(word)) {
                return false
            }
            charToWordMap[char] = word
            seen.add(word)
        } else {
            if (charToWordMap[char] != word) {
                return false
            }
        }
    }
    return true
}


fun main() {
    wordPattern(pattern = "a", s = "a") shouldBe true
    wordPattern(pattern = "abba", s = "dog cat cat dog") shouldBe true
    wordPattern(pattern = "abba", s = "dog cat cat fish") shouldBe false
    wordPattern(pattern = "aaaa", s = "dog cat cat dog") shouldBe false
    wordPattern(pattern = "abba", s = "dog dog dog dog") shouldBe false
}