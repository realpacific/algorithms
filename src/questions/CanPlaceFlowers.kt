package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
 * and an integer n, return if n new flowers can be planted in the flowerbed
 * without violating the no-adjacent-flowers rule.
 *
 * [Source](https://leetcode.com/problems/can-place-flowers/)
 */
@UseCommentAsDocumentation
private fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    if (flowerbed.size < n) {
        return false
    }
    var remaining = n
    var current = 0
    while (current <= flowerbed.lastIndex && remaining > 0) {
        val isEmpty = flowerbed[current] == 0
        if (!isEmpty) {
            current += 2
            continue
        } else {
            val prevPlot = flowerbed.getOrNull(current - 1)
            val nextPlot = flowerbed.getOrNull(current + 1)
            if ((nextPlot == null || nextPlot == 0) && (prevPlot == null || prevPlot == 0)) {
                remaining--
                flowerbed[current] = 1
                current += 2
                continue
            }
        }
        current++

    }
    return remaining == 0
}

fun main() {
    canPlaceFlowers(flowerbed = intArrayOf(0, 0, 1, 0, 0), n = 1) shouldBe true
    canPlaceFlowers(flowerbed = intArrayOf(0, 0, 0, 0, 0, 0, 0), n = 4) shouldBe true
    canPlaceFlowers(flowerbed = intArrayOf(1, 0, 1, 0, 1), n = 1) shouldBe false
    canPlaceFlowers(flowerbed = intArrayOf(1, 0, 0, 0, 1), n = 1) shouldBe true
    canPlaceFlowers(flowerbed = intArrayOf(1, 0, 0, 0, 1), n = 2) shouldBe false

}