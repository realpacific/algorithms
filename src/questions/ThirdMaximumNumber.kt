package questions

import _utils.UseCommentAsDocumentation
import java.util.*
import kotlin.test.assertEquals

/**
 * Given an integer array nums, return the third distinct maximum number
 * in this array. If the third maximum does not exist, return the maximum number.
 */
@UseCommentAsDocumentation
fun thirdMax(nums: IntArray): Int {
    val set = TreeSet<Int>() // use sorted data structure
    for (i in 0..nums.lastIndex) {
        set.add(nums[i])
    }
    return set.elementAtOrElse(set.size - 3) {
        set.elementAt(set.size - 1) // return max
    }
}

fun thirdMaxII(nums: IntArray): Int {
    var max1: Int? = null
    var max2: Int? = null
    var max3: Int? = null

    for (i in 0..nums.lastIndex) {
        val elem = nums[i]
        if (elem == max1 || elem == max2 || elem == max3) {
            continue
        }
        if (max3 == null) {
            max3 = elem
        } else if (elem > max3) {
            max1 = max2
            max2 = max3
            max3 = elem
        } else if (max2 == null || (elem in max2..max3)) {
            max1 = max2
            max2 = elem
        } else if (max1 == null || elem in max1..max2) {
            max1 = elem
        }
    }
    return max1 ?: max3!!
}

fun main() {
    assertEquals(4, thirdMaxII(intArrayOf(5, 2, 4, 1, 3, 6, 0)))
    assertEquals(1, thirdMaxII(intArrayOf(2, 2, 3, 1)))
    assertEquals(5, thirdMaxII(intArrayOf(5, 2, 2)))
    assertEquals(1, thirdMaxII(intArrayOf(3, 2, 1)))
    assertEquals(2, thirdMaxII(intArrayOf(1, 2)))
}