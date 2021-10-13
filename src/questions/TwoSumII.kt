package questions

import _utils.UseCommentAsDocumentation
import algorithmdesignmanualbook.print
import utils.assertIterableSame

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Return the indices of the two numbers, index1 and index2, as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * [Source](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)
 */
@UseCommentAsDocumentation
fun twoSum(numbers: IntArray, target: Int): IntArray {
    for (i in 0..numbers.lastIndex) {
        val requiredInt = target - numbers[i]
        val indexOfRequired = binarySearch(numbers, low = 0, high = numbers.size, target = requiredInt, ignoreIndex = i)
        if (indexOfRequired != null) {
            return intArrayOf(i + 1, indexOfRequired + 1).sortedArray()
        }
    }
    return intArrayOf()
}

private fun binarySearch(numbers: IntArray, low: Int, high: Int, target: Int, ignoreIndex: Int): Int? {
    // can't have <= as the [target] may occur at the middle
    if (high < low) return null
    val mid = (low + high) / 2

    // [ignoreIndex] to satisfy condition that "You may not use the same element twice."
    val midNumber = numbers.getOrNull(mid)
    return if (midNumber == null) null
    else if (midNumber == target && ignoreIndex != mid) mid
    else if (midNumber < target)
        binarySearch(numbers, mid + 1, high, target = target, ignoreIndex = ignoreIndex)
    else binarySearch(numbers, low, mid - 1, target = target, ignoreIndex = ignoreIndex)
}

fun main() {
    assertIterableSame(
        intArrayOf(3, 42).toList(),
        twoSum(
            numbers = intArrayOf(
                12,
                83,
                104,
                129,
                140,
                184,
                199,
                300,
                306,
                312,
                321,
                325,
                341,
                344,
                349,
                356,
                370,
                405,
                423,
                444,
                446,
                465,
                471,
                491,
                500,
                506,
                508,
                530,
                539,
                543,
                569,
                591,
                606,
                607,
                612,
                614,
                623,
                627,
                645,
                662,
                670,
                685,
                689,
                726,
                731,
                737,
                744,
                747,
                764,
                773,
                778,
                787,
                802,
                805,
                811,
                819,
                829,
                841,
                879,
                905,
                918,
                918,
                929,
                955,
                997,
            ), target = 789
        ).toList().print()
    )
    assertIterableSame(
        intArrayOf(1, 2).toList(), twoSum(numbers = intArrayOf(0, 0, 3, 4), target = 0).toList().print()
    )
    assertIterableSame(
        intArrayOf(2, 3).toList(), twoSum(numbers = intArrayOf(5, 25, 75), target = 100).toList().print()
    )

    assertIterableSame(
        intArrayOf(1, 2).toList(), twoSum(numbers = intArrayOf(2, 7, 11, 15), target = 9).toList().print()
    )
    assertIterableSame(
        intArrayOf(1, 3).toList(), twoSum(numbers = intArrayOf(2, 3, 4), target = 6).toList().print()
    )
    assertIterableSame(
        intArrayOf(1, 2).toList(), twoSum(numbers = intArrayOf(-1, 0), target = -1).toList().print()
    )
}