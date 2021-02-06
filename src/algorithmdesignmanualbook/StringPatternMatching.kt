package algorithmdesignmanualbook

import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun main() {

    assertTrue(matchPatternUsingHash("abba", "aababba"))
    assertTrue(matchPatternUsingHash("", "aababba"))
    assertTrue(matchPatternUsingHash("aa", "aababba"))
    assertTrue(matchPatternUsingHash("bba", "aababba"))
    assertTrue(matchPatternUsingHash("a", "aababba"))
    assertTrue(matchPatternUsingHash("aababba", "aababba"))
    assertTrue(matchPatternUsingHash("bab", "aababba"))
    assertTrue(matchPatternUsingHash("babb", "aababba"))
    assertFalse(matchPatternUsingHash("aabax", "aababba"))
    assertFalse(matchPatternUsingHash("x", "aababba"))
    assertFalse(matchPatternUsingHash("aababbax", "aababba"))
}


fun matchPatternUsingHash(pattern: String, str: String): Boolean {
    val lenPattern = pattern.length
    val lenString = str.length

    if (lenPattern == 0) return true
    if (lenPattern > lenString) return false
    val patternHash = pattern.hashCode()
    for (i in 0..str.lastIndex) {
        // Prevent out of bounds exception
        if (i + lenPattern > lenString) {
            return false
        }
        // Calculate hash of substring
        val subStringHash = str.substring(i, i + lenPattern).hashCode()
        if (subStringHash == patternHash) {
            return true
        }
    }
    return false
}

/**
 * Find substring match
 *
 * O(ab) where a = len of pattern and b = len of string
 */
fun matchPatternNaive(pattern: String, str: String): Boolean {
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