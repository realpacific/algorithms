package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j
 * in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.
 *
 * [Source](https://leetcode.com/problems/contains-duplicate-ii/)
 */
@UseCommentAsDocumentation
private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    // How about using hash set instead of window
    val hashSet = LinkedHashSet<Int>()
    for (i in 0..nums.lastIndex) {
        if (i > k) { // now start removing items
            // remove that element which is out of the window of width [k]
            val elemToBeRemoved = nums[i - (k + 1)]
            hashSet.remove(elemToBeRemoved)
        }

        val current = nums[i]
        // if the current window contains that element, then duplicate present
        if (hashSet.contains(current)) {
            return true
        }
        hashSet.add(current)
    }
    return false
}

fun main() {
    containsNearbyDuplicate(nums = intArrayOf(1, 2, 3, 9, 5, 6, 2), k = 3) shouldBe false
    containsNearbyDuplicate(nums = intArrayOf(1, 2, 3, 1), k = 3) shouldBe true
    containsNearbyDuplicate(nums = intArrayOf(1, 0, 1, 1), k = 1) shouldBe true
    containsNearbyDuplicate(nums = intArrayOf(1, 2, 3, 1, 2, 3), k = 2) shouldBe false
    containsNearbyDuplicate(nums = intArrayOf(1, 2, 3, 4, 1), k = 3) shouldBe false
}