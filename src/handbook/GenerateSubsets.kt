package handbook

import _utils.Document
import utils.shouldBe
import kotlin.math.pow

@Document("Bit method – Each subset of n elements can be represented as sequences of n bits")
private fun generateSubsetUsingBitProperties(nums: IntArray): Set<Set<Int>> {
    fun convertIndicesToElement(array: Set<Int>): Set<Int> {
        return array.map(nums::elementAt).toSet()
    }

    val result = mutableSetOf<Set<Int>>()

    // this can be also be achieved using (1 << n)
    val totalElements = (2).toDouble().pow(nums.size).toInt() // n=3, subsets.size() == 8

    repeat(totalElements) {
        val temp = mutableSetOf<Int>()
        for (j in nums.indices) {
            if (it.and((1).shl(j)) > 0) {
                temp.add(j)
            }
        }
        result.add(convertIndicesToElement(temp))
    }
    println(result)
    return result
}

/*
 * Space O(n*2^n) = 2^n elements and max size of element is n
 */
@Document("Backtrack method")
private fun generateSubsetUsingBacktracking(nums: IntArray): Set<Set<Int>> {
    val result = mutableListOf<Set<Int>>()

    fun backtrack(startIndex: Int, current: MutableList<Int>) {

        result.add(current.toSet())

        for (i in startIndex..nums.lastIndex) {
            current.add(nums[i])
            backtrack(i + 1, current)
            current.removeLastOrNull()
        }
    }

    backtrack(0, mutableListOf())
    return result.toSet()
}

fun main() {

    fun <T : Comparable<T>> assertSetEqual(
        actual: Iterable<Iterable<T>>,
        expected: Iterable<Iterable<T>>
    ): Iterable<Iterable<T>> {
        actual.toHashSet().containsAll(expected.toHashSet()) shouldBe true
        expected.toHashSet().containsAll(actual.toHashSet()) shouldBe true
        return actual
    }

    assertSetEqual(
        actual = generateSubsetUsingBitProperties(intArrayOf()),
        expected = setOf(setOf())
    )

    assertSetEqual(
        actual = generateSubsetUsingBitProperties(intArrayOf(1, 2, 3)),
        expected = setOf(
            setOf(), setOf(1), setOf(2), setOf(3),
            setOf(1, 2), setOf(2, 3), setOf(1, 3), setOf(1, 2, 3),
        )
    )

    assertSetEqual(
        actual = generateSubsetUsingBacktracking(intArrayOf(1, 2, 3)),
        expected = setOf(
            setOf(), setOf(1), setOf(2), setOf(3),
            setOf(1, 2), setOf(2, 3), setOf(1, 3), setOf(1, 2, 3),
        )
    )


    assertSetEqual(
        actual = generateSubsetUsingBitProperties(intArrayOf(1, 5, 3)),
        expected = setOf(
            setOf(), setOf(1), setOf(5), setOf(3),
            setOf(1, 5), setOf(5, 3), setOf(1, 3), setOf(1, 5, 3)
        )
    )

    assertSetEqual(
        actual = generateSubsetUsingBitProperties(intArrayOf(1, 5)),
        expected = setOf(
            setOf(), setOf(1), setOf(5), setOf(1, 5),
        )
    )
    assertSetEqual(
        actual = generateSubsetUsingBitProperties(intArrayOf(1)),
        expected = setOf(setOf(), setOf(1))
    )

}