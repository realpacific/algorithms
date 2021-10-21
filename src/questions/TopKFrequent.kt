package questions


import _utils.UseCommentAsDocumentation
import utils.shouldBeOneOf
import java.util.*

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * [Source](https://leetcode.com/problems/top-k-frequent-elements//)
 */
@UseCommentAsDocumentation
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val charToCountMap = HashMap<Int, Int>(nums.size / 2)

    // Reverse sorted map of frequency to [nums] element
    val frequencyToCharMap = TreeMap<Int, MutableSet<Int>>(object : Comparator<Int> {
        override fun compare(o1: Int?, o2: Int?): Int {
            return o2!! - o1!!     // This will be sorted in reverse order of frequency
        }
    })

    for (i in 0..nums.lastIndex) {
        val value = nums[i]
        val prevCount = charToCountMap.getOrDefault(value, 0)
        val newCount = prevCount + 1
        charToCountMap[value] = newCount

        if (prevCount > 0) {
            // it has been encountered before
            // so remove it from its old key
            val set = frequencyToCharMap.getOrDefault(prevCount, mutableSetOf())
            set.remove(value)
        }

        // and add it to [newCount] key
        val set = frequencyToCharMap.getOrDefault(newCount, mutableSetOf())
        set.add(value)
        frequencyToCharMap[newCount] = set
    }

    // Since [frequencyToCharMap] is reverse sorted, top k elements should be in first few position
    val result = IntArray(k) { 0 }
    var index = 0
    for (numSet in frequencyToCharMap.values) {
        for (value in numSet) {
            result[index] = value
            if (index >= k) {
                break
            }
            index++
        }
        if (index >= k) {
            break
        }
    }
    return result
}

fun main() {
    topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2) shouldBeOneOf listOf(intArrayOf(1, 2), intArrayOf(2, 1))
}