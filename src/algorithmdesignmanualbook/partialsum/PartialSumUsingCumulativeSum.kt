package algorithmdesignmanualbook.partialsum

import java.util.*
import kotlin.test.assertEquals

/**
 * Let A[1..n] be an array of real numbers. Design an algorithm to perform any
 * sequence of the following operations:
 * • Add(i,y) – Add the value y to the ith number.
 * • Partial-sum(i) – Return the sum of the first i numbers
 */
class PartialSum(private val values: Array<Int>) {
    private val cumulativeSum = Array(values.size) { 0 }

    init {
        values.forEachIndexed { index, value ->
            val prevSum = cumulativeSum.getOrNull(index - 1) ?: 0
            cumulativeSum[index] = prevSum + value
        }
        print()
    }

    fun print() = print(Arrays.toString(values)).also { println(Arrays.toString(cumulativeSum)) }

    fun cumulativeValueAt(index: Int) = values[index]

    fun add(index: Int, value: Int) {
        values[index] = values[index] + value
        for (i in index..values.lastIndex) {
            val prevSum = cumulativeSum.getOrNull(i - 1) ?: 0
            cumulativeSum[i] = prevSum + values[i]
        }
    }

    fun partialSum(index: Int): Int {
        return cumulativeSum[index]
    }
}

fun main() {
    val solution = PartialSum(arrayOf(1, 2, 3, 4, 5, 6))
    solution.add(index = 2, value = 10)
    assertEquals(3, solution.partialSum(1))

    // [1, 2, 13, 4, 5, 6]
    assertEquals(13, solution.cumulativeValueAt(2))

    solution.add(index = 0, value = 10)
    // [11, 2, 13, 4, 5, 6]
    assertEquals(11, solution.cumulativeValueAt(0))

    solution.print()
    assertEquals(13, solution.partialSum(1))
    assertEquals(26, solution.partialSum(2))

}