package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * [Source](https://leetcode.com/problems/first-unique-character-kt/)
 */
@UseCommentAsDocumentation
fun firstUniqChar(s: String): Int {
    val orderedMap = LinkedHashMap<Char, Int>() // char to its index map
    val removedSet = mutableSetOf<Char>()
    for (i in 0..s.lastIndex) {
        val char = s[i]
        if (removedSet.contains(char)) {
            continue
        }
        val index = orderedMap[char]
        if (index != null) {
            // [char] is not unique
            removedSet.add(char)
            orderedMap.remove(char)
        } else {
            // [char] has not been seen before
            orderedMap[char] = i
        }
    }
    val firstKey = orderedMap.keys.firstOrNull() ?: return -1
    return orderedMap[firstKey] ?: -1 // since [orderedMap] is well... ordered, the answer will be on first key
}

fun main() {
    firstUniqChar(s = "loveleetcode") shouldBe 2
    firstUniqChar(s = "leetcode") shouldBe 0
    firstUniqChar(s = "aabb") shouldBe -1
}