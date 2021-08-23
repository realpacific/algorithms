package algorithmdesignmanualbook.datastructures

import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * You are given a search string and a magazine.
 * You seek to generate all the characters in search string by cutting them out from the magazine.
 * Give an algorithm to efficiently determine whether the magazine contains all the letters in the search string
 *
 * Notes:
 * * Cases matters
 * * Whitespace doesn't matter
 */
fun isGenerateStringFromCutoutPossible(searchStr: String, magazine: String): Boolean {
    val frequency = mutableMapOf<Char, Int>()
    searchStr.replace(" ", "").forEach {
        frequency[it] = frequency.getOrDefault(it, 0) + 1
    }
    magazine.forEach {
        if (frequency.containsKey(it)) {
            val newCounts = frequency[it]!!.minus(1)
            if (newCounts == 0) {
                frequency.remove(it)
            } else {
                frequency[it] = newCounts
            }
        }
    }
    if (frequency.isNotEmpty()) {
        println("Requires: $frequency")
    }
    return frequency.isEmpty()
}


/**
 * @suppress
 */
fun main() {
    assertFalse { isGenerateStringFromCutoutPossible("hello", "Give an algorithm") }
    assertTrue { isGenerateStringFromCutoutPossible("sing me a zing", "You are given a search string and a magazine") }
    assertFalse { isGenerateStringFromCutoutPossible("you", "You are given a search string and a magazine") }
    assertTrue { isGenerateStringFromCutoutPossible("You give me a gain", "You are given a search string and a magazine") }
}