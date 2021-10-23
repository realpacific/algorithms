package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*
import kotlin.collections.HashMap

/**
 * Suppose Andy and Doris want to choose a restaurant for dinner,
 * and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum.
 * If there is a choice tie between answers, output all of them with no order requirement.
 * You could assume there always exists an answer.
 * [Source](https://leetcode.com/problems/minimum-index-sum-of-two-lists/)
 */
@UseCommentAsDocumentation
private fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
    val map = HashMap<String, Int>(list1.size)
    list1.forEachIndexed { index, s ->
        map[s] = index
    }
    val result = mutableListOf<String>()
    var lowestSum = Integer.MAX_VALUE

    list2.forEachIndexed { index, s ->
        val indexInList1 = map[s] ?: return@forEachIndexed
        val sum = indexInList1 + index
        if (sum == lowestSum) {
            result.add(s)
        } else if (sum < lowestSum) { // better answer found
            lowestSum = sum
            result.clear()
            result.add(s)
        }
    }
    return result.toTypedArray()
}

fun main() {
    findRestaurant(
        list1 = arrayOf("Shogun", "Tapioca Express", "Burger King", "KFC"),
        list2 = arrayOf("Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun")
    ) shouldBe arrayOf("Shogun")

    findRestaurant(
        list1 = arrayOf("Shogun", "Tapioca Express", "Burger King", "KFC"),
        list2 = arrayOf("KFC", "Burger King", "Tapioca Express", "Shogun")
    ) shouldBe arrayOf("KFC", "Burger King", "Tapioca Express", "Shogun")

}