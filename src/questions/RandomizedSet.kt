package questions

import _utils.UseCommentAsDocumentation
import questions.common.ClassInvoker
import utils.shouldBe
import utils.shouldBeOneOf
import kotlin.random.Random

/**
 *
 * [Source](https://leetcode.com/problems/insert-delete-getrandom-o1/)
 */
@UseCommentAsDocumentation
class RandomizedSet() {
    private var array = Array<Int?>(5) { null }
    private val charToIndex = mutableMapOf<Int, Int>()
    private var lastInsertedAt = -1
    private val rand = java.util.Random()

    fun insert(`val`: Int): Boolean {
        if (charToIndex.containsKey(`val`)) {
            return false
        }
        lastInsertedAt++
        if (lastInsertedAt > array.lastIndex) {
            val newArr = Array<Int?>(lastInsertedAt * 2) { null }
            System.arraycopy(array, 0, newArr, 0, array.size)
            array = newArr
        }
        array[lastInsertedAt] = `val`
        charToIndex[`val`] = lastInsertedAt
        return true
    }

    fun remove(`val`: Int): Boolean {
        val index = charToIndex[`val`] ?: return false
        // GOTCHA: Swap last element with [index]
        array[index] = array[lastInsertedAt]
        // Update the map
        charToIndex[array[lastInsertedAt]!!] = index
        // Delete
        array[lastInsertedAt] = null
        lastInsertedAt--
        charToIndex.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        return array[rand.nextInt(lastInsertedAt + 1)]!!
    }

}

fun main() {
    run {
        val invoker = ClassInvoker<IntArray, Int>(
            listOf(
                "RandomizedSet",
                "insert",
                "getRandom",
                "getRandom",
                "getRandom",
                "insert",
                "insert",
                "insert",
                "insert",
                "insert",
                "getRandom",
                "getRandom",
                "insert",
                "getRandom",
                "insert",
                "insert",
                "getRandom",
                "getRandom",
                "getRandom",
                "getRandom",
                "remove",
                "insert",
                "getRandom",
                "getRandom",
                "insert",
                "remove",
                "remove",
                "insert",
                "getRandom",
                "getRandom",
                "insert",
                "insert",
                "getRandom",
                "remove",
                "remove",
                "insert",
                "remove",
                "getRandom",
                "getRandom",
                "remove",
                "getRandom",
                "insert",
                "insert",
                "getRandom",
                "remove",
                "remove",
                "remove",
                "getRandom",
                "insert",
                "insert",
                "insert",
                "insert",
                "getRandom",
                "insert",
                "getRandom",
                "remove",
                "insert",
                "insert",
                "insert",
                "getRandom",
                "getRandom",
                "insert",
                "getRandom",
                "insert",
                "insert",
                "getRandom",
                "getRandom",
                "remove",
                "getRandom",
                "remove",
                "insert",
                "getRandom",
                "insert",
                "insert",
                "insert",
                "getRandom",
                "insert",
                "insert",
                "getRandom",
                "insert",
                "getRandom",
                "insert",
                "getRandom",
                "getRandom",
                "getRandom",
                "insert",
                "getRandom",
                "getRandom",
                "insert",
                "insert",
                "insert",
                "getRandom",
                "remove",
                "insert",
                "insert",
                "getRandom",
                "insert",
                "insert",
                "insert",
                "insert"
            )
        ) {
            it.getOrNull(0)
        }
        invoker.invoke(
            listOf(
                intArrayOf(),
                intArrayOf(-7),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(6),
                intArrayOf(7),
                intArrayOf(10),
                intArrayOf(3),
                intArrayOf(8),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(-8),
                intArrayOf(),
                intArrayOf(6),
                intArrayOf(-8),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(2),
                intArrayOf(2),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(5),
                intArrayOf(-5),
                intArrayOf(-8),
                intArrayOf(-8),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(-4),
                intArrayOf(10),
                intArrayOf(),
                intArrayOf(7),
                intArrayOf(-1),
                intArrayOf(8),
                intArrayOf(-6),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(9),
                intArrayOf(),
                intArrayOf(7),
                intArrayOf(0),
                intArrayOf(),
                intArrayOf(-10),
                intArrayOf(-4),
                intArrayOf(-3),
                intArrayOf(),
                intArrayOf(-4),
                intArrayOf(-5),
                intArrayOf(8),
                intArrayOf(-2),
                intArrayOf(),
                intArrayOf(-9),
                intArrayOf(),
                intArrayOf(7),
                intArrayOf(-2),
                intArrayOf(7),
                intArrayOf(4),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(-6),
                intArrayOf(),
                intArrayOf(-8),
                intArrayOf(2),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(9),
                intArrayOf(),
                intArrayOf(-1),
                intArrayOf(3),
                intArrayOf(),
                intArrayOf(0),
                intArrayOf(-3),
                intArrayOf(-1),
                intArrayOf(),
                intArrayOf(-8),
                intArrayOf(-10),
                intArrayOf(),
                intArrayOf(3),
                intArrayOf(),
                intArrayOf(4),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(-10),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(0),
                intArrayOf(-2),
                intArrayOf(5),
                intArrayOf(),
                intArrayOf(-2),
                intArrayOf(5),
                intArrayOf(10),
                intArrayOf(),
                intArrayOf(9),
                intArrayOf(0),
                intArrayOf(7),
                intArrayOf(-2)
            ),
        )
    }

    run {

        val set = RandomizedSet()
        set.apply {
            insert(0) shouldBe true
            getRandom() shouldBe 0
            remove(0) shouldBe true
            insert(0) shouldBe true
        }
    }

    run {
        val randomizedSet = RandomizedSet()
        randomizedSet.insert(1) shouldBe true // Inserts 1 to the set. Returns true as 1 was inserted successfully.
        randomizedSet.remove(2) shouldBe false // Returns false as 2 does not exist in the set.
        randomizedSet.insert(2) shouldBe true // Inserts 2 to the set, returns true. Set now contains [1,2].
        randomizedSet.getRandom() shouldBeOneOf listOf(1, 2) // getRandom() should return either 1 or 2 randomly.
        randomizedSet.remove(1) shouldBe true // Removes 1 from the set, returns true. Set now contains [2].
        randomizedSet.insert(2) shouldBe false // 2 was already in the set, so return false.
        randomizedSet.getRandom() shouldBe 2 // Since 2 is the only number in the set, getRandom() will always return 2.

        randomizedSet.remove(2) shouldBe true

        randomizedSet.insert(4) shouldBe true
        randomizedSet.insert(5) shouldBe true
        randomizedSet.insert(6) shouldBe true
        for (i in 0..100) {
            randomizedSet.getRandom() shouldBeOneOf listOf(4, 5, 6)
        }
    }
}