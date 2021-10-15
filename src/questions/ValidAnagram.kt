package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe


/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * [Source](https://leetcode.com/problems/valid-anagram/)
 */
@UseCommentAsDocumentation
private fun isAnagram(s: String, t: String): Boolean {
    if (s == t) return true
    if (s.length != t.length) return false
    val countMap = mutableMapOf<Char, Int>()
    for ((i, j) in s.toCharArray().zip(t.toCharArray())) {
        countMap[i] = countMap.getOrDefault(i, 0) + 1 // increase count
        countMap[j] = countMap.getOrDefault(j, 0) - 1 // decrease count
        if (countMap[i] == 0) countMap.remove(i)
        if (countMap[j] == 0) countMap.remove(j)
    }
    return countMap.isEmpty() // since aggregates with 0 counts are removed, only check if empty
}

private fun isAnagramII(s: String, t: String): Boolean {
    if (s == t) return true
    if (s.length != t.length) return false
    return s.toCharArray().sorted() == t.toCharArray().sorted() // or simply check if their sorted values are equal
}

fun main() {
    isAnagramII(s = "anagram", t = "nagaram") shouldBe true
    isAnagramII(s = "cinema", t = "iceman") shouldBe true
    isAnagramII(s = "rat", t = "car") shouldBe false
}