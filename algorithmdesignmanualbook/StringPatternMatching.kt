package algorithmdesignmanualbook

import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun main() {
    assertTrue(matchPattern("abba", "aababba"))
    assertTrue(matchPattern("", "aababba"))
    assertTrue(matchPattern("aa", "aababba"))
    assertTrue(matchPattern("bba", "aababba"))
    assertTrue(matchPattern("a", "aababba"))
    assertTrue(matchPattern("aababba", "aababba"))
    assertFalse(matchPattern("aabax", "aababba"))
    assertFalse(matchPattern("x", "aababba"))
    assertFalse(matchPattern("aababbax", "aababba"))
    assertTrue(matchPattern("bab", "aababba"))
    assertTrue(matchPattern("babb", "aababba"))
}

/**
 * Find substring match
 *
 * O(ab) where a = len of pattern and b = len of string
 */
fun matchPattern(pattern: String, str: String): Boolean {
    val lenPattern = pattern.length
    val lenString = str.length

    if (lenPattern == 0) return true
    if (lenPattern > lenString) return false

    for (i in 0 until lenString) {
        var head = i
        // At current head, if the pattern cant fit in the remaining substring, then no matches
        if ((head + lenPattern) > lenString) {
            return false
        }

        var matchCount = 0
        for (j in 0 until lenPattern) {
            if (pattern[j] == str[head]) {
                head++
                // Count the number of matches. If matches and len of pattern is same, then match
                matchCount++
            } else {
                break
            }
            if (matchCount == lenPattern) {
                return true
            }
        }
    }

    return false
}