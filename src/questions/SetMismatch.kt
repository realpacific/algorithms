package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 * [Source](https://leetcode.com/problems/set-mismatch/)
 */
@UseCommentAsDocumentation
private fun findErrorNums(nums: IntArray): IntArray {
    val result = IntArray(2) { -1 }
    val seen = HashSet<Int>(nums.size)
    val possibleMissing = mutableSetOf<Int>()
    var trueIndex = 0
    nums.sorted().forEach { i -> // GOTCHA: you've sorted the array, so you can't use forEachIndexed
        if (trueIndex + 1 != i) {  // out of index so potentially missing
            possibleMissing.add(trueIndex + 1) // index is the missing element
        }
        if (seen.contains(i)) { // seen check
            result[0] = i
        }
        trueIndex++
        seen.add(i)
    }
    possibleMissing.forEach { // find which element is missing
        if (!seen.contains(it)) {
            result[1] = it
            return result // missing element found return the result
        }
    }
    result[1] = nums.size // no missing element found
    return result
}

fun main() {
    // 2,3,3,4,5,6
    findErrorNums(nums = intArrayOf(3, 2, 3, 4, 6, 5)) shouldBe intArrayOf(3, 1)
    findErrorNums(nums = intArrayOf(1, 5, 3, 2, 2, 7, 6, 4, 8, 9)) shouldBe intArrayOf(2, 10)
    findErrorNums(nums = intArrayOf(3, 2, 2)) shouldBe intArrayOf(2, 1)
    findErrorNums(nums = intArrayOf(1, 2, 2, 4)) shouldBe intArrayOf(2, 3)
    findErrorNums(nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 9)) shouldBe intArrayOf(7, 8)
}