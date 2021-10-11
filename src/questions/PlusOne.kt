package questions

import utils.assertIterableSame

/**
 * You are given a large integer represented as an integer array digits, where each digits[i]
 * is the ith digit of the integer. The digits are ordered from most significant to least
 * significant in left-to-right order. The large integer does not contain any leading 0's.
 * Increment the large integer by one and return the resulting array of digits.
 *
 * [Source](https://leetcode.com/problems/plus-one/)
 */
fun plusOne(digits: IntArray): IntArray {
    // If last digit is less than 9, just add 1 and finish e.g: 408 -> 409
    if (digits.last() < 9) {
        digits[digits.lastIndex] = digits[digits.lastIndex] + 1
        return digits
    }
    // else need to check other digits as well eg: 489 -> 490
    return incrementByOneAt(digits, digits.lastIndex)
}

private fun incrementByOneAt(digits: IntArray, index: Int): IntArray {
    if (index == -1) {
        if (digits[0] == 0) {
            // this is the case when the MSB is 0, which means there was a carry over
            // but the current array can't hold the carry
            // so create new array that includes the carry
            return intArrayOf(1, *digits)
        }
        return digits
    }

    if (digits[index] == 9) {
        // If 9, then make it 0 and process previous bit
        digits[index] = 0
        // recurse on previous digits
        return incrementByOneAt(digits, index - 1)
    } else {
        // If not 9, increment and finish
        digits[index] = digits[index] + 1
        return digits
    }
}

fun main() {
    assertIterableSame(intArrayOf(1, 2, 4).toList(), plusOne(intArrayOf(1, 2, 3)).toList())
    assertIterableSame(intArrayOf(4, 3, 2, 2).toList(), plusOne(intArrayOf(4, 3, 2, 1)).toList())
    assertIterableSame(intArrayOf(1).toList(), plusOne(intArrayOf(0)).toList())
    assertIterableSame(intArrayOf(1, 0).toList(), plusOne(intArrayOf(9)).toList())
}