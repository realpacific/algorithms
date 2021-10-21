package questions


import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, return true if any value appears at least twice in the array,
 *  and return false if every element is distinct.
 * [Source](https://leetcode.com/problems/contains-duplicate/)
 */
@UseCommentAsDocumentation
private fun containsDuplicate(nums: IntArray): Boolean {
    if (nums.size == 1) {
        return false
    }
    val set = HashSet<Int>(nums.size)
    nums.forEach {
        if (set.contains(it)) {
            return true
        }
        set.add(it)
    }
    return false
}

private fun containsDuplicateFunc(nums: IntArray): Boolean {
    return nums.size != nums.distinct().size
}

fun main() {
    containsDuplicate(nums = intArrayOf(1, 2, 3, 1)) shouldBe true
    containsDuplicateFunc(nums = intArrayOf(1, 2, 3, 1)) shouldBe true

    containsDuplicate(nums = intArrayOf(1, 2, 3, 4)) shouldBe false
    containsDuplicateFunc(nums = intArrayOf(1, 2, 3, 4)) shouldBe false

    containsDuplicate(nums = intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)) shouldBe true
    containsDuplicateFunc(nums = intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)) shouldBe true
}