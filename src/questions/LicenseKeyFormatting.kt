package questions

import java.lang.StringBuilder
import kotlin.test.assertEquals

/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 * We want to reformat the string s such that each group contains exactly k characters,
 * except for the first group, which could be shorter than k but still must contain at least one character.
 * Furthermore, there must be a dash inserted between two groups,
 * and you should convert all lowercase letters to uppercase. Return the reformatted license key.
 *
 * [Source](https://leetcode.com/problems/license-key-formatting/)
 */
fun licenseKeyFormatting(s: String, k: Int): String {
    val sb = StringBuilder(s.length)
    val separator = '-'
    var count = 0
    for (i in s.lastIndex downTo 0) {
        if (s[i] == separator) continue // Ignore separators
        sb.append(s[i].toUpperCase())
        count++
        if (count % k == 0) sb.append(separator) // put '-' at every kth position
    }
    return sb.removeSuffix(separator.toString()).reversed().toString()
}


fun main() {
    assertEquals("AA-AA", licenseKeyFormatting(s = "--a-a-a-a--", k = 2))
    assertEquals(
        "5F3Z-2E9W", licenseKeyFormatting(s = "5F3Z-2e-9-w", k = 4)
    )
    assertEquals("2-5G-3J", licenseKeyFormatting(s = "2-5g-3-J", k = 2))
}