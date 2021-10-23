package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given an integer array nums, return the sum of Hamming distances between all the pairs of the integers in nums.
 * [Source](https://leetcode.com/problems/total-hamming-distance/)
 */
@UseCommentAsDocumentation
private fun totalHammingDistance_NonOptimal(nums: IntArray): Int {
    val pairsCountMap = HashMap<Pair<Int, Int>, Int>(nums.size)
    for (i in 0..nums.lastIndex) {
        for (j in i + 1..nums.lastIndex) {
            val first = nums[i]
            val second = nums[j]
            if (first != second) {
                val pair = first to second
                val pairRev = second to first
                if (pair in pairsCountMap || pairRev in pairsCountMap) { // [4,14] is same as [14,4] so take just 1
                    pairsCountMap[pair] = pairsCountMap.getOrDefault(pair, 0) + 1
                } else {
                    pairsCountMap[pair] = pairsCountMap.getOrDefault(pair, 0) + 1
                }
            }
        }
    }
    var sum = 0
    pairsCountMap.keys.forEach {
        sum += hammingDistance(it.first, it.second) * pairsCountMap[it]!! // multiply it by count ([4,14] and [14,4])
    }
    return sum
}

private fun hammingDistance(x: Int, y: Int): Int {
    var diff = x.xor(y) // xor = 1 when different else 0
    if (diff == 0) return 0 // both are same
    var count = 0
    while (diff > 0) {
        if (diff.and(1) == 1) { // diff AND 1 gives LSB
            count++ // count all LSB
        }
        diff = diff.shr(1) // shift [diff] right
    }
    return count
}


/**
 * [Solution](https://leetcode.com/problems/total-hamming-distance/discuss/96226/Java-O(n)-time-O(1)-Space)
 * > For each bit position 1-32 in a 32-bit integer, we count the number of integers in the array which have that bit set.
 * Then, if there are n integers in the array and k of them have a particular bit set and (n-k) do not, then that bit contributes k*(n-k) hamming distance to the total.
 */
private fun totalHammingDistance(nums: IntArray): Int {
    var total = 0
    for (j in 0 until 32) {
        var bitCount = 0
        for (i in 0..nums.lastIndex) {
            bitCount += (nums[i].shr(j).and(1))
        }
        total += bitCount * (nums.size - bitCount)
    }
    return total
}

fun main() {
    totalHammingDistance_NonOptimal(intArrayOf(4, 14, 4)) shouldBe 4
    // HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
    totalHammingDistance_NonOptimal(intArrayOf(4, 14, 2)) shouldBe 6

    totalHammingDistance(intArrayOf(4, 14, 4)) shouldBe 4
    // HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
    totalHammingDistance(intArrayOf(4, 14, 2)) shouldBe 6
}