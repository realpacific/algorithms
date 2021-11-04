package questions

import _utils.UseCommentAsDocumentation
import kotlin.math.ceil
import kotlin.test.assertEquals

/**
 * Given an array of integers nums containing `n + 1` integers where each integer is in the range `[1, n]` inclusive.
 * There is only one repeated number in `nums`, return this repeated number.
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * [Source](https://leetcode.com/problems/find-the-duplicate-number/)
 */
@UseCommentAsDocumentation
private fun findDuplicate(nums: IntArray): Int {
    val maxValue = nums.maxOrNull()!!

    // we need to accommodate [maxValue]. One array can hold 8 1/0s
    val countsOfByteArraysRequiredToHoldMaxValue = ceil((maxValue.toDouble() + 1) / 8).toInt()

    // only dealing with positive number, sign doesn't matter so using 8
    // [ 00000000, 00000000, 00000000, 00000000 ]
    // [ 00000000, 00000000, 00000000, 00000001 ] => 1 has been seen
    // [ 00000000, 00000000, 00000000, 00000010 ] => 2 has been seen
    // [ 00000000, 00000000, 00000001, 00000000 ] => 9 has been seen
    // each bit in an element represents whether the value has occurred yet or not
    val byteArray = ByteArray(countsOfByteArraysRequiredToHoldMaxValue) {
        0.toByte()
    }
    nums.forEach {
        val indexOfStoringArray = it / 8 // find the index where the [it] should be stored
        // after finding the index in the [array], find how much to shift
        // if it is product of 8, then it should be shifted to full length of a byte
        // else it should be shifted by compensating the [indexOfStoringArray]
        val howMuchToShift = if (it > 0 && it % 8 == 0) 8 else it - 8 * indexOfStoringArray

        // shift left
        // -1 because [nums] is in range [1,n] so element = 1 should be in 0th array at 0th bit
        val setter = 1.shl(howMuchToShift - 1)

        // when and-ed with setter, if it doesn't return 0 means it had already been set so this must be the duplicate
        val mask = byteArray[indexOfStoringArray].toInt().and(setter)
        if (mask != 0) {
            return it
        }
        val newMask = setter.or(byteArray[indexOfStoringArray].toInt()) // set the bit
        byteArray[indexOfStoringArray] = newMask.toByte() // write it to [array]
    }
    return -1 // unknown
}

fun main() {
    assertEquals(16, findDuplicate(intArrayOf(14, 16, 12, 1, 16, 17, 6, 8, 5, 19, 16, 13, 16, 3, 11, 16, 4, 16, 9, 7)))
    assertEquals(8, findDuplicate(intArrayOf(8, 9, 8, 4, 2, 8, 1, 5)))
    assertEquals(7, findDuplicate(intArrayOf(7, 9, 7, 4, 2, 8, 7, 7, 1, 5)))
    assertEquals(1, findDuplicate(intArrayOf(1, 3, 1, 2)))
    assertEquals(2, findDuplicate(intArrayOf(1, 3, 2, 2)))
    assertEquals(3, findDuplicate(intArrayOf(3, 1, 3, 4, 2)))
}