package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 * [Source](https://leetcode.com/problems/missing-number/)
 */
@UseCommentAsDocumentation
fun missingNumber(nums: IntArray): Int {
    nums.sort()
    for (i in 0..nums.lastIndex) {
        if (i != nums[i]) {
            return i
        }
    }
    return nums.size
}

fun main() {
    missingNumber(intArrayOf(3, 0, 1)) shouldBe 2
    missingNumber(intArrayOf(1, 2)) shouldBe 0
    missingNumber(intArrayOf(0, 1, 2)) shouldBe 3
}