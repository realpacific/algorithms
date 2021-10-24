package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * You are given an integer array prices where `prices[i]` is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 *
 * [Source](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)
 *
 * @see [BestTimeStock]
 */
@UseCommentAsDocumentation
private object BestTimeStockII

private fun maxProfit(prices: IntArray): Int {
    val futureSellPrices = Array<Int>(prices.size) { Integer.MIN_VALUE }
    val futureBuyPrices = Array<Int>(prices.size) { Integer.MAX_VALUE }
    for (index in prices.lastIndex downTo 0) {
        futureSellPrices[index] = maxOf(futureSellPrices.getOrNull(index + 1) ?: Integer.MIN_VALUE, prices[index])
        futureBuyPrices[index] = minOf(futureBuyPrices.getOrNull(index + 1) ?: Integer.MAX_VALUE, prices[index])
    }

    var result = 0
    var boughtAt: Int? = null
    for (index in 0..prices.lastIndex) {
        val maxSell = futureSellPrices[index] // max sell price in future
        val minBuy = futureBuyPrices[index] // min buy price in future
        val current = prices[index]
        if (boughtAt == null) { // haven't bought yet
            if (current == maxSell) { // if this is maxSell price, then don't buy
                boughtAt = null
            } else if (current in minBuy until maxSell) { // buy if you can sell it at higher price in future
                boughtAt = current
            }
        } else { // if already bought, see if you can sell
            if (current == maxSell) { // this is max sell price, then sell it!
                result += (current - boughtAt) // sold
            } else if (current > boughtAt && current in minBuy until maxSell) { // sell if there's profit
                result += (current - boughtAt) //sold
            }
            if (current in minBuy until maxSell) { // re-buy it if you can have profit in the future
                boughtAt = current // re-bought at the same day
            } else {
                boughtAt = null // sold
            }
        }

    }
    return result
}

fun main() {
    maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)) shouldBe 7
    maxProfit(intArrayOf(2, 4, 1)) shouldBe 2
    maxProfit(intArrayOf(1, 2, 3, 4, 5)) shouldBe 4
}