package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * You are given an array prices where `prices[i]` is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock
 * and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * [Source](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
 * @see [BestTimeStockII]
 */
@UseCommentAsDocumentation
private object BestTimeStock

private fun maxProfit(prices: IntArray): Int {
    val maxPossibleSellPrice = Array<Int>(prices.size) { -1 }
    for (index in prices.lastIndex downTo 0) {
        // find the max sell price possible by filling in from the end
        // comparing max price in future and current price
        // for [3, 2, 6, 5, 0, 3] -> [6, 6, 6, 5, 3, 3]
        maxPossibleSellPrice[index] =
            maxOf(maxPossibleSellPrice.getOrNull(index + 1) ?: -1, prices[index])
    }

    var result = 0
    for (index in 0..prices.lastIndex) {
        val sellPrice = maxPossibleSellPrice[index]
        result = maxOf(sellPrice - prices[index], result)
    }
    return result
}

fun main() {
    maxProfit(intArrayOf(3, 2, 6, 5, 0, 3)) shouldBe 4
    maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)) shouldBe 5
    maxProfit(intArrayOf(7, 6, 4, 3, 1)) shouldBe 0
}