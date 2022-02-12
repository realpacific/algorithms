package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are given two strings order and s. All the words of order are unique
 * and were sorted in some custom order previously.
 * Permute the characters of s so that they match the order that order was sorted.
 * More specifically, if a character x occurs before a character y in order,
 * then x should occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 *
 * [Source](https://leetcode.com/problems/custom-sort-string-kt/)
 */
@UseCommentAsDocumentation
private fun customSortString(order: String, s: String): String {
    val countMap: MutableMap<Char, Int> = s.groupingBy { it }.eachCount().toMutableMap()

    val sb = StringBuilder()
    for (i in order) {
        if (countMap.contains(i)) {
            sb.append(i.toString().repeat(countMap[i]!!))
            countMap.remove(i)
        }
    }
    countMap.forEach { (key, count) ->
        sb.append(key.toString().repeat(count))
    }
    return sb.toString()
}

fun main() {
    customSortString(order = "cba", s = "abcd") shouldBe "cbad"
    customSortString(order = "cbafg", s = "abcd") shouldBe "cbad"
}