package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 *
 * [Source](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
 */
@UseCommentAsDocumentation
private fun findAnagrams(s: String, p: String): List<Int> {
    if (p.length > s.length) {
        return emptyList()
    }
    val result = mutableListOf<Int>()
    val charCounts = mutableMapOf<Char, Int>()
    for (i in p) {
        charCounts[i] = charCounts.getOrDefault(i, 0) + 1
    }

    // val sortedP = p.toCharArray().sorted()
    // findAnagramUsingSortCompare(s, 0, sortedP, result)
    findAnagramUsingCharCount(s, 0, p.length, charCounts, result)
    return result
}

/**
 * Sliding window method with map
 * @see questions.isAnagram
 */
private fun findAnagramUsingCharCount(
    s: String,
    startIndex: Int,
    targetSize: Int,
    countMap: MutableMap<Char, Int>,
    result: MutableList<Int>
) {
    if (s.length - startIndex < targetSize) {
        return
    }
    val copy = HashMap(countMap)
    val subString = s.substring(startIndex, startIndex + targetSize)
    for (i in subString) {
        val newVal = copy.getOrDefault(i, 0) - 1
        if (newVal == 0) copy.remove(i)
        else copy[i] = newVal
    }
    if (copy.isEmpty()) {
        result.add(startIndex)
    }
    findAnagramUsingCharCount(s, startIndex + 1, targetSize, countMap, result)
}

/**
 * Sliding window method with sorted compare
 * @see questions.isAnagramII
 */
private fun findAnagramUsingSortCompare(s: String, startIndex: Int, target: List<Char>, result: MutableList<Int>) {
    val size = target.size
    if (s.length - startIndex < size) {
        return
    }
    val subString = s.substring(startIndex, startIndex + size).toCharArray().sorted()
    if (subString == target) { // sorted strings are equal then it is anagram
        result.add(startIndex)
    }
    findAnagramUsingSortCompare(s, startIndex + 1, target, result)
}

fun main() {
    // The substring with start index = 0 is "cba", which is an anagram of "abc".
    // The substring with start index = 6 is "bac", which is an anagram of "abc".
    findAnagrams(s = "cbaebabacd", p = "abc") shouldBe listOf(0, 6)

    // The substring with start index = 0 is "ab", which is an anagram of "ab".
    // The substring with start index = 1 is "ba", which is an anagram of "ab".
    // The substring with start index = 2 is "ab", which is an anagram of "ab".
    findAnagrams(s = "abab", p = "ab") shouldBe listOf(0, 1, 2)
}