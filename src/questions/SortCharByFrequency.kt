package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBeOneOf
import java.util.*

/**
 *
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * Return the sorted string. If there are multiple answers, return any of them.
 * [Source](https://leetcode.com/problems/sort-characters-by-frequency/)
 */
@UseCommentAsDocumentation
fun frequencySort(s: String): String {
    if (s.length <= 2) return s
    // Maintains map of char to its count
    val charToCountMap = HashMap<Char, Int>(s.length)
    for (i in 0..s.lastIndex) {
        charToCountMap[s[i]] = charToCountMap.getOrDefault(s[i], 0) + 1
    }
    // maintains SORTED map of count to the character with that frequency
    // sorted w.r.t frequency of occurrence in reverse order
    // eg: in "cccaaabb" -> 3: "cccaaa", 2: "bb"
    val countToCharMap = TreeMap<Int, String>(object : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            return o2!! - o1!!     // This will be sorted in reverse order of frequency
        }
    })
    charToCountMap.forEach { (k, count) ->
        countToCharMap[count] = countToCharMap.getOrDefault(count, "") + k.toString().repeat(count)
    }
    // [countToCharMap] is already sorted so just join the values
    return countToCharMap.values.joinToString("")
}

fun main() {
    frequencySort(s = "tree") shouldBeOneOf listOf("eert", "eetr")
    frequencySort(s = "cccaaa") shouldBeOneOf listOf("cccaaa", "aaaccc")
    frequencySort(s = "Aabb") shouldBeOneOf listOf("bbAa", "bbaA")
}