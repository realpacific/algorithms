package questions

import algorithmdesignmanualbook.print
import kotlin.math.abs
import kotlin.test.assertEquals

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 *
 * [Source](https://leetcode.com/problems/3sum-closest/)
 */
fun threeSumClosest(nums: IntArray, target: Int): Int {
    require(nums.size >= 3)
    val sorted = nums.sorted()
    var closest = sorted[0] + sorted[1] + sorted.last()

    for (i in 0..sorted.lastIndex) {
        // `i` is fixed, left & right keeps converging to middle
        var left = i + 1
        var right = sorted.lastIndex
        while (left < right) {
            // sum right now
            val sums = sorted[left] + sorted[right] + sorted[i]
            if (sums == target) {
                // closest value is 0 so return immediately
                println("${sorted[left]} ${sorted[right]} ${sorted[i]}")
                return sums
            } else if (abs(sums - target) < abs(closest - target)) {
                // update if closer value is found
                println("${sorted[left]} ${sorted[right]} ${sorted[i]}")
                closest = sums
            }
            if (sums > target) {
                // Sum is larger so decrease right index
                right -= 1
            } else if (sums < target) {
                // Smaller so increase left index
                left += 1
            }
        }
    }
    return closest
}


fun main() {
    assertEquals(expected = 2, threeSumClosest(intArrayOf(-4, -1, 1, 2), 1).print())
    assertEquals(expected = 5, threeSumClosest(intArrayOf(-1, 0, 1, 2, 3, 4, 6), 5).print())
    assertEquals(expected = 5, threeSumClosest(intArrayOf(-1, 0, 1, 2, 3, 4, 7), 5).print())
    assertEquals(expected = 6, threeSumClosest(intArrayOf(-1, 0, 1, 2, 10, 7), 5).print())
}