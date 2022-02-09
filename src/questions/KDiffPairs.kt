package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 * * `0 <= i < j < nums.length`
 * * `abs(nums[i] - nums[j]) == k`
 *
 * [Source](https://leetcode.com/problems/k-diff-pairs-in-an-array/)
 */
@UseCommentAsDocumentation
private fun findPairs(nums: IntArray, k: Int): Int {
    val countMap = mutableMapOf<Int, Int>()
    nums.forEach {
        countMap[it] = countMap.getOrDefault(it, 0) + 1
    }
    val seen = mutableSetOf<Pair<Int, Int>>()
    for (it in nums.distinct()) {
        val other = k + it
        val pair = Pair(minOf(other, it), maxOf(other, it))

        // already seen
        if (seen.contains(pair)) {
            continue
        }
        // other == it is the case when k = 0
        // It requires preventing self match in the [countMap]
        if (other == it && countMap[it]!! > 1) { // given k=0, [it] is repeated in the [nums]
            seen.add(pair)
            continue
        } else if (other == it) { // given k=0, [it] is not repeated so don't add this to [seen]
            continue
        }
        if (countMap.containsKey(other)) {
            seen.add(pair)
        }
    }
    return seen.size
}

fun main() {
    findPairs(nums = intArrayOf(1, 3, 1, 5, 4), k = 0) shouldBe 1
    findPairs(nums = intArrayOf(1, 1, 1, 1, 1), k = 0) shouldBe 1
    findPairs(nums = intArrayOf(1, 2, 4, 4, 3, 3, 0, 9, 2, 3), k = 3) shouldBe 2
    // (1, 3) and (3, 5)
    findPairs(nums = intArrayOf(3, 1, 4, 1, 5), k = 2) shouldBe 2
    //(1, 2), (2, 3), (3, 4) and (4, 5).
    findPairs(nums = intArrayOf(1, 2, 3, 4, 5), k = 1) shouldBe 4

}
