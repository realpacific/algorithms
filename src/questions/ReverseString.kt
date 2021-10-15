package questions

import utils.shouldBe

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * Do not allocate extra space for another array.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * [Source](https://leetcode.com/problems/reverse-string/)
 */
private fun reverseString(s: CharArray) {
    val lastIndex = s.lastIndex
    // GOTCHA: only loop till half of the length
    for (i in 0..lastIndex / 2) {
        val temp = s[i]
        s[i] = s[lastIndex - i]
        s[lastIndex - i] = temp
    }
}

fun main() {
    run {
        val arr = charArrayOf('h', 'e', 'l', 'l', 'o')
        reverseString(arr)
        arr shouldBe charArrayOf('o', 'l', 'l', 'e', 'h')
    }

    run {
        val arr = charArrayOf('H', 'a', 'n', 'n', 'a', 'h')
        reverseString(arr)
        arr shouldBe charArrayOf('h', 'a', 'n', 'n', 'a', 'H')
    }
}