package arraysandstrings

import arraysandstrings.IsPermutationOfStringAPalindrome.isPalindrome
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private object IsPermutationOfStringAPalindrome {


    /**
     * A string's permutation is a palindrome
     * if
     * for a odd length string, there must be only one odd # of character (the middle one)
     * for a even length string, all chars must have even # of occurrence
     */
    fun isPalindrome(str: String): Boolean {
        val input = str.toLowerCase().replace(" ", "")
        val charCount = mutableMapOf<Char, Int>()
        for (s in input) {
            charCount[s] = (charCount[s] ?: 0) + 1
        }
        return if (input.length % 2 == 0) {
            charCount.values.all { it % 2 == 0 }
        } else {
            val oddOccurrence = charCount.values.filter { it % 2 == 1 }
            oddOccurrence.size == 1
        }
    }
}

fun main() {
    assertTrue(isPalindrome("Tact Coa"))
    assertTrue(isPalindrome("level"))
    assertTrue(isPalindrome("noon"))
    assertFalse(isPalindrome("noon2a"))
}