package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * [Source](https://leetcode.com/problems/longest-palindrome/)
 */
@UseCommentAsDocumentation
private fun longestPalindrome(s: String): Int {
    if (s.length == 1) return 1
    val charCount = mutableMapOf<Char, Int>()
    var result = 0
    for (i in s) {
        charCount[i] = charCount.getOrDefault(i, 0) + 1
        // A letter can be used in palindrome iff there are 2 of them i.e one on each side (eg: level)
        if (charCount[i]!! % 2 == 0) {
            result += 2 // increment the count
            charCount.remove(i) // remove it to make [charCount] loops efficient
        }
    }
    for (i in charCount.values) {
        if (i == 1) {
            // See if there is an element in the map with count of 1
            // this can be the letter in middle of the palindrome (eg: v in level)
            result += 1
            break
        }
    }
    return result
}

fun main() {
    longestPalindrome(s = "Aa") shouldBe 1
    longestPalindrome(s = "abccccdd") shouldBe 7 // dccaccd
    longestPalindrome(s = "a") shouldBe 1
    longestPalindrome(s = "bb") shouldBe 2
}
