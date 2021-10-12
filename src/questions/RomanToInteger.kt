package questions

import _utils.UseCommentAsDocumentation
import java.util.*
import kotlin.collections.LinkedHashMap
import kotlin.test.assertEquals

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * I = 1, V=5, X = 10, L = 50, C = 100, D = 500, M = 1000
 *
 * For example, 2 is written as II in Roman numeral, just two one's added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * * I can be placed before V (5) and X (10) to make 4 and 9.
 * * X can be placed before L (50) and C (100) to make 40 and 90.
 * * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer.
 */
@UseCommentAsDocumentation
private fun romanToInt(s: String): Int {
    val charMap = mapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100, 'D' to 500, 'M' to 1000)
    return parse(s, 0, charMap, 0)
}

fun parse(s: String, index: Int, map: Map<Char, Int>, total: Int): Int {
    val first = s.getOrNull(index) ?: return total
    val second = s.getOrNull(index + 1) ?: return total + map[first]!!
    var newTotal = total

    // Subtraction case
    return if (
        (first == 'I' && (second == 'V' || second == 'X')) ||
        (first == 'X' && (second == 'L' || second == 'C')) ||
        (first == 'C' && (second == 'D' || second == 'M'))
    ) {
        newTotal += (map[second]!! - map[first]!!)
        // +2 as we tried to pair two roman char together so skip the pairs
        parse(s, index + 2, map, newTotal)
    } else {
        // Didn't get paired so use this character individually
        parse(s, index + 1, map, total + map[first]!!)
    }
}

fun main() {
    assertEquals(1994, romanToInt("MCMXCIV"))
    assertEquals(3, romanToInt("III"))
    assertEquals(4, romanToInt("IV"))
    assertEquals(9, romanToInt("IX"))
}