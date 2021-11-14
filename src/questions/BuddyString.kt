package questions

import utils.shouldBe
import kotlin.text.StringBuilder

/**
 * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal,
 * otherwise, return false.
 *
 * [Source](https://leetcode.com/problems/buddy-strings/)
 */
fun buddyStrings(s: String, goal: String): Boolean {
    if (s.length == 1) {
        return false
    }
    if (s.length != goal.length) {
        return false
    }
    val sb = StringBuilder(s)
    val swapables = mutableSetOf<Char>()
    var isSameSwapPossible = false
    var firstSwap = -1
    for (i in 0..sb.lastIndex) {
        if (sb[i] != goal[i]) {
            if (firstSwap == -1) {
                firstSwap = i
            } else {
                val temp = sb[i]
                sb[i] = sb[firstSwap]
                sb[firstSwap] = temp
                return sb.toString() == goal
            }
        } else {
            if (swapables.contains(s[i])) {
                isSameSwapPossible = true
            } else {
                swapables.add(s[i])
            }
        }
    }
    if (firstSwap == -1) {
        return isSameSwapPossible
    } else {
        return sb.toString() == goal
    }
}

fun main() {
    buddyStrings("abcaa", "abcbb") shouldBe false
    buddyStrings("aabcdef", "aabcdfe") shouldBe true
    buddyStrings("abac", "abad") shouldBe false
    buddyStrings("aaaa", "aaaa") shouldBe true
    buddyStrings("abcde", "ebcda") shouldBe true
    buddyStrings("aaaabc", "aaaacb") shouldBe true
    buddyStrings("ab", "ab") shouldBe false
    buddyStrings("aa", "aa") shouldBe true
}