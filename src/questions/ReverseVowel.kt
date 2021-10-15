package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 *
 * [Source](https://leetcode.com/problems/reverse-vowels-of-a-string/)
 */
@UseCommentAsDocumentation
private fun reverseVowels(s: String): String {
    if (s.length == 1) {
        return s
    }
    val arr = s.toCharArray()
    var front = 0
    var back = s.lastIndex

    while (true) {
        while (front <= arr.lastIndex && !isVowel(arr[front])) {
            front++
        }
        while (back >= 0 && !isVowel(arr[back])) {
            if (back < 0) break
            back--
        }
        if (front >= back) {
            break
        }
        swap(arr, front, back)
        front++
        back--
    }
    return arr.joinToString("")
}

private fun swap(arr: CharArray, i1: Int, i2: Int) {
    val temp: Char = arr[i1]
    arr[i1] = arr[i2]
    arr[i2] = temp
}

private fun isVowel(char: Char): Boolean {
    return char == 'a' || char == 'e' || char == 'i' || char == 'o' || char == 'u' || char == 'A' || char == 'E' || char == 'I' || char == 'O' || char == 'U'
}

fun main() {
    reverseVowels(s = "hello") shouldBe "holle"
    reverseVowels(s = "leetcode") shouldBe "leotcede"
    reverseVowels(s = ",.") shouldBe ",."
}