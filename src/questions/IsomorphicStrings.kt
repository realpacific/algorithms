package questions

import _utils.UseCommentAsDocumentation
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character, but a character may map to itself.
 *
 * [Source](https://leetcode.com/problems/isomorphic-strings/)
 */
@UseCommentAsDocumentation
fun isIsomorphic(s: String, t: String): Boolean {
    val translateMap = mutableMapOf<Char, Char>()
    val seen = mutableSetOf<Char>()
    for (i in 0..s.lastIndex) {
        val left = translateMap[s[i]]

        // Already seen value in s should be equal in t
        if (left != null && left != t[i]) {
            return false
        }
        // badc -> baba
        // d has no mapping but b is already mapped. That's why see if b is already "seen"
        if (seen.contains(t[i]) && !translateMap.containsKey(s[i])) {
            return false
        }
        seen.add(t[i])
        translateMap[s[i]] = t[i]
    }
    return true
}

fun main() {
    assertTrue(isIsomorphic(s = "paper", t = "title"))
    assertFalse(isIsomorphic(s = "badc", t = "baba"))
    assertTrue(isIsomorphic(s = "egg", t = "add"))
    assertFalse(isIsomorphic(s = "foo", t = "bar"))
}