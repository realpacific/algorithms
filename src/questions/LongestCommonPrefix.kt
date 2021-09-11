package questions

import kotlin.test.assertEquals

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 */
fun longestCommonPrefix(strs: Array<String>): String {
    var result = ""
    var i = 0
    // Infinite loop: this way we don't have to find the shortest word
    while (true) {
        // Get element at index i
        val b = strs.map { it.getOrNull(i) }.toSet()
        // if contains null, then at least one of the element ran out of letters
        if (b.contains(null)) {
            break
        }
        // size of set == 1 then all contents same i.e. same character
        if (b.size == 1) {
            result += b.elementAt(0)
            i++
        } else {
            break
        }
    }
    return result
}

fun main() {
    assertEquals("fl", longestCommonPrefix(arrayOf("flower", "flow", "flight")))
    assertEquals("flo", longestCommonPrefix(arrayOf("flower", "flow", "floor")))
    assertEquals("fl", longestCommonPrefix(arrayOf("flow", "flew", "flown")))
    assertEquals("flower", longestCommonPrefix(arrayOf("flower", "flowers")))
    assertEquals("", longestCommonPrefix(arrayOf("dog", "racecar", "car")))
    assertEquals("", longestCommonPrefix(arrayOf("", "")))
    assertEquals("", longestCommonPrefix(arrayOf("")))
}