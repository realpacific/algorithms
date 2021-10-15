package questions

import utils.shouldBe

/**
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly.
 * That is, each element of nums is covered by exactly one of the ranges,
 * and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 * *"a->b" if a != b
 * * "a" if a == b
 *
 * [Source](https://leetcode.com/problems/summary-ranges/)
 */

private fun summaryRanges(nums: IntArray): List<String> {
    if (nums.isEmpty()) return emptyList()
    if (nums.size == 1) return listOf(nums[0].toString())

    val result = mutableListOf<String>()
    findRange(nums, startIndex = 0, currentIndex = 1, result)
    return result
}

private fun findRange(nums: IntArray, startIndex: Int, currentIndex: Int, result: MutableList<String>) {
    if (currentIndex > nums.lastIndex) { // end
        // add the remaining
        val start = nums[startIndex]
        val end = nums[nums.lastIndex]
        addToList(start, end, result)
        return
    }
    return if (nums[currentIndex - 1] == nums[currentIndex] - 1) { // Check if sequential i.e (prevElem+1 = current)
        findRange(nums, startIndex = startIndex, currentIndex = currentIndex + 1, result)
    } else {
        val start = nums[startIndex]
        val end = nums[currentIndex - 1]
        addToList(start, end, result)
        findRange(
            nums,
            startIndex = currentIndex, // start from current as current element is the one that violated
            currentIndex = currentIndex + 1, // should be 1 step ahead of [startIndex]
            result
        )
    }
}

private fun addToList(start: Int, end: Int, result: MutableList<String>) {
    if (start == end) result.add("$start")
    else result.add("$start->$end")
}

fun main() {
    summaryRanges(nums = intArrayOf(-1)) shouldBe listOf("-1")
    summaryRanges(nums = intArrayOf(0, 1, 2, 4, 5, 7)) shouldBe listOf("0->2", "4->5", "7")
    summaryRanges(nums = intArrayOf(0, 2, 3, 4, 6, 8, 9)) shouldBe listOf("0", "2->4", "6", "8->9")
    summaryRanges(nums = intArrayOf()) shouldBe listOf()
    summaryRanges(nums = intArrayOf(0)) shouldBe listOf("0")
}