package algorithmdesignmanualbook.datastructures

import algorithmdesignmanualbook.print
import java.util.*
import kotlin.random.Random
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Design a data structure that allows one to search, insert, and delete an integer
 * X in O(1) time (i.e. , constant time, independent of the total number of integers
 * stored). Assume that 1 ≤ X ≤ n and that there are m + n units of space available,
 * where m is the maximum number of integers that can be in the table at any one
 * time. (Hint: use two arrays A[1..n] and B[1..m].) You are not allowed to initialize
 * either A or B, as that would take O(m) or O(n) operations. This means the arrays
 * are full of random garbage to begin with, so you must be very careful.
 *
 * [Solution link]((https://research.swtch.com/sparse)):
 *
 * Two arrays both of them with garbage value
 * * dense: contains actual elements in order of insertion
 * * sparse: uses *value* of [dense] as index and stores the *index* at which the value is located
 *
 * So the search value v is legit iff index v of sparse array points to dense's index whose value is also v
 */
class NoInitializationArray(val maxValue: Int, val maxSize: Int) {
    private val rand = Random(100)

    private val dense = Array(maxSize) { getGarbage() }
    private val sparse = Array(maxValue) { getGarbage() }
    private var nextIndex = 0

    private fun getGarbage() = rand.nextInt(maxValue + 1, maxValue + 1 + 200)

    fun print() {
        println("dense: ${Arrays.toString(dense)}")
        println("sparse: ${Arrays.toString(sparse)}")
    }

    fun add(value: Int) {
        require(value.isNotGarbage())
        dense[nextIndex] = value
        sparse[value] = nextIndex
        nextIndex++
    }

    private fun hasLegitValue(sparseResult: Int): Boolean {
        val value = dense.getOrNull(sparseResult) ?: return false
        return value.print().isNotGarbage()
    }

    private fun Int.isNotGarbage() = this < maxValue

    fun delete(value: Int) {
        val index = sparse.getOrNull(value) ?: return
        if (!hasLegitValue(index)) {
            return
        }
        dense[index] = getGarbage()
        sparse[value] = getGarbage()
    }

    fun search(value: Int): Boolean {
        val index = sparse.getOrNull(value) ?: return false
        return dense.getOrNull(index) == value
    }
}

fun main() {
    val solution = NoInitializationArray(maxValue = 20, maxSize = 10)
    solution.add(10)
    assertTrue { solution.search(10) }
    solution.add(6)
    assertTrue { solution.search(6) }
    solution.add(9)
    assertTrue { solution.search(9) }
    solution.add(14)
    assertTrue { solution.search(14) }

    solution.print()

    assertFalse { solution.search(100) }

    solution.delete(10)
    assertFalse { solution.search(10) }
    solution.delete(6)
    assertFalse { solution.search(6) }
    solution.delete(9)
    assertFalse { solution.search(9) }

    solution.print()
}