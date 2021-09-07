package algorithmdesignmanualbook.sorting

import kotlin.test.assertTrue

/**
 * Given a set S of n integers and an integer T,
 * give an algorithm to test whether k of the integers in S add up to T.
 *
 * [Another way](https://www.geeksforgeeks.org/find-the-k-th-permutation-sequence-of-first-n-natural-numbers/)
 *
 * ### IDEA:
 *
 * ```
 * For target=21, k=3 i.e. 21/3 and array:
 *
 * | 5 | 6 | 8 | 10 | 12 |
 *
 *  <------- 21/3 ------>
 *
 *   ^ <---   16/2    --->
 *
 *       ^ <---  10/1 --->
 * ```
 */
class KSum(_input: Array<Int>, private val target: Int, private val k: Int) {
    val result = mutableListOf<MutableList<Int>>()

    // Sort it first
    val array: Array<Int> = _input.sortedArray()

    fun execute(): List<List<Int>> {
        for (i in 0..array.lastIndex) {
            val temp = mutableListOf<Int>()
            kSumHelper(k, target, i, temp)
        }
        println(result)
        return result
    }

    private fun kSumHelper(
        remainingKCount: Int,
        remainingSum: Int,
        currentIndex: Int,
        temp: MutableList<Int>
    ) {
        // array out of bound
        if (currentIndex > array.lastIndex) {
            return
        }
        // Only 1 item remains
        if (remainingKCount == 1) {
            // When you have 1 item remaining, just find if from current index to end, there exists an item == [remainingSum]
            if (array.sliceArray(currentIndex..array.lastIndex).contains(remainingSum)) {
                temp.add(remainingSum)
                // Since sum( [temp] + remainingSum ) == target
                result.add(temp)
                return
            }
        }
        temp.add(array[currentIndex])
        kSumHelper(
            remainingKCount = remainingKCount - 1, // Since you consider current element, then decrease the [remainingKCount]
            remainingSum = remainingSum - array[currentIndex], // Remove it from sum
            currentIndex = currentIndex + 1,
            temp = temp
        )
    }
}

fun main() {
    val input = arrayOf(-4, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8)
    run {
        KSum(input, 6, 3).execute().forEach {
            assertTrue {
                it.sum() == 6 && it.size == 3
            }
        }
    }

    run {
        KSum(input, 8, 2).execute().forEach {
            assertTrue {
                it.sum() == 8 && it.size == 2
            }
        }
    }

    run {
        KSum(input, 11, 3).execute().forEach {
            assertTrue {
                it.sum() == 11 && it.size == 3
            }
        }
    }
}

