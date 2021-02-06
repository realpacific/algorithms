package algorithmdesignmanualbook

import kotlin.test.assertTrue

private typealias Table = Array<Array<Int?>>

private interface SmallestNumberInRange {
    fun getSmallest(startIndex: Int, endIndex: Int): Int
}

/**
 *
 * Suppose that we are given a sequence of n values x1, x2, ..., xn and seek to
 * quickly answer repeated queries of the form: given i and j, find the smallest value
 * in xi, . . . , xj.
 *
 * Given arrays of integer [values] of size n, convert it into matrix M of nxn
 * such that M[i][j + 1] <= M[i][j] and anything before M[i][i] is null. M[i][i] holds the ith index item of [values]
 */
class SmallestNumberInRangeMatrixApproach(private val values: Array<Int>) : SmallestNumberInRange {

    // O(n^2) initialization
    private val table: Table = Array(values.size) { row ->
        Array(values.size) { col ->
            if (col == row) {
                values[row]
            } else null
        }
    }

    // O(n^2) initialization
    init {
        for (i in 0..values.lastIndex) {
            for (j in i..values.lastIndex) {
                val compareValue = values[j]
                val previousValue = table.getOrNull(i)?.getOrNull(j - 1) ?: table[i][i]!!
                if (previousValue < compareValue) {
                    table[i][j] = previousValue
                } else {
                    table[i][j] = compareValue
                }
            }
        }
        table.forEach { row ->
            row.forEach { print(String.format("%6s", it)) }
            println()
        }
    }

    /**
     * O(1) search
     */
    override fun getSmallest(startIndex: Int, endIndex: Int): Int {
        require(startIndex < table.size && endIndex < table.size && startIndex <= endIndex)
        return table[startIndex][endIndex]!!
    }
}

/**
 * Uses map with key as i#j
 */
class SmallestNumberInRangeHashMapApproach(private val values: Array<Int>) : SmallestNumberInRange {

    private val map = mutableMapOf<String, Int>()

    private fun get(startIndex: Int, endIndex: Int): Int? {
        return map["$startIndex#$endIndex"]
    }

    private fun set(startIndex: Int, endIndex: Int, value: Int) {
        map["$startIndex#$endIndex"] = value
    }

    init {
        for (i in 0..values.lastIndex) {
            for (j in i..values.lastIndex) {
                val currentValue = values[j]
                if (i == j) {
                    set(i, i, currentValue)
                    continue
                }
                val prevValue = get(i, j - 1) ?: get(i, i)!!
                if (prevValue < currentValue) {
                    set(i, j, prevValue)
                } else {
                    set(i, j, currentValue)
                }
            }
        }
        println(map)
    }

    /**
     * O(1) search
     */
    override fun getSmallest(startIndex: Int, endIndex: Int): Int {
        require(startIndex <= endIndex && endIndex < values.size)
        return get(startIndex, endIndex)!!
    }

}

fun main() {
    val input = arrayOf(1, 2, 4, 0, 3)
    val matrixSolution = SmallestNumberInRangeMatrixApproach(input)
    val hashMapSolution = SmallestNumberInRangeHashMapApproach(input)

    listOf(matrixSolution, hashMapSolution).forEach { solution ->
        assertTrue { solution.getSmallest(0, 4) == 0 }
        assertTrue { solution.getSmallest(1, 4) == 0 }
        assertTrue { solution.getSmallest(2, 4) == 0 }
        assertTrue { solution.getSmallest(3, 4) == 0 }
        assertTrue { solution.getSmallest(4, 4) == 3 }
        assertTrue { solution.getSmallest(0, 2) == 1 }
        assertTrue { solution.getSmallest(1, 2) == 2 }
        assertTrue { solution.getSmallest(2, 2) == 4 }
    }

}