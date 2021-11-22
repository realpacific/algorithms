package handbook

import utils.shouldBe
import utils.shouldBeOneOf

/**
 * Given array of integers, generate its permutations.
 */
private class GeneratePermutations(val nums: IntArray) : () -> List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    override fun invoke(): List<List<Int>> {
        permutation(0, nums.lastIndex)
        return result
    }

    private fun permutation(low: Int, high: Int) {
        if (low == high) {
            result.add(nums.toList())
        }
        for (i in low..high) {
            swap(nums, low, i)
            permutation(low + 1, high)
            swap(nums, low, i)
        }
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }


}

fun main() {
    run {
        val expected = listOf(
            listOf(0, 1, 2),
            listOf(0, 2, 1),
            listOf(1, 0, 2),
            listOf(1, 2, 0),
            listOf(2, 0, 1),
            listOf(2, 1, 0)
        )
        GeneratePermutations(intArrayOf(0, 1, 2))
            .invoke()
            .also {
                it.size shouldBe expected.size
            }
            .forEach {
                it shouldBeOneOf expected
            }
    }

    run {
        val expected = listOf(
            listOf(0, 1),
            listOf(1, 0)
        )
        GeneratePermutations(intArrayOf(0, 1))
            .invoke()
            .also {
                it.size shouldBe expected.size
            }
            .forEach {
                it shouldBeOneOf expected
            }
    }

}