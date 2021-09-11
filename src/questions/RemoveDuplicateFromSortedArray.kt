package questions

import algorithmdesignmanualbook.print
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place.
 * The relative order of the elements should be kept the same.
 * More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 *
 * [Source](https://leetcode.com/problems/remove-duplicates-from-sorted-array/submissions/)
 */
fun removeDuplicateFromSortedArray(nums: IntArray): Pair<Int, List<Int>> {
    var k = 1
    var isIncrementing = true
    for (i in 0..nums.lastIndex) {
        var j = i + 1
        if (j > nums.lastIndex) {
            continue
        }
        // Skip till you find an item that's not been swapped and is different that the current
        while (j <= nums.lastIndex && (nums[i] >= nums[j])) {
            j += 1
        }
        swap(nums, i + 1, j)
        if (isIncrementing && nums[i] < nums[i + 1]) {
            k++
        } else {
            // as soon as find a non-sorted item, then stop counting
            isIncrementing = false
        }
    }
    return k to nums.toList()
}

private fun swap(nums: IntArray, i: Int, j: Int) {
    if (i > nums.lastIndex || j > nums.lastIndex) {
        return
    }
    val temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
}

fun main() {
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)).print()
            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(0, 1, 2, 3, 4))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4)).print()
            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(0, 1, 2, 3, 4))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(1, 1, 1, 2, 2, 3, 3, 4)).print()
            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(1, 2, 3, 4))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(1)).print()
            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(1))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(4, 4, 4, 4, 4)).print()

            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(4))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(4, 4, 4, 4, 5)).print()

            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(4, 5))
            }
    }
    assertTrue {
        removeDuplicateFromSortedArray(intArrayOf(1, 1, 2)).print()
            .run {
                validateItemsUptoNIsEqualTo(this.second.toIntArray(), this.first, intArrayOf(1, 2))
            }
    }
}

private fun validateItemsUptoNIsEqualTo(nums: IntArray, n: Int, expected: IntArray): Boolean {
    for (i in 0 until n) {
        assertEquals(expected[i], nums[i])
    }
    return true
}