package questions

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

fun _containerWithMostWater(height: IntArray): Int {
    if (height.size == 2) {
        return calcArea(0, 1, height)
    }
    var maxArea = calcArea(0, height.lastIndex, height)
    for (i in 0 until height.lastIndex) {
        for (j in (i + 1)..height.lastIndex) {
            val max = calcArea(i, j, height)
            if (max > maxArea) {
                maxArea = max
            }
        }
    }
    return maxArea
}

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * <img src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg" height="150" width="300">
 * [Source](https://leetcode.com/problems/container-with-most-water/)
 */
fun containerWithMostWater(height: IntArray): Int {
    var maxArea = 0
    var i = 0
    var j = height.lastIndex
    while (i < j) {
        val area = calcArea(i, j, height)
        maxArea = maxOf(area, maxArea)
        if (height[i] >= height[j]) {
            j--
        } else {
            i++
        }
    }
    return maxArea
}

private fun calcArea(x1: Int, x2: Int, heights: IntArray): Int {
    if (x1 >= x2) {
        return 0
    }
    return (x2 - x1) * minOf(heights[x2], heights[x1])
}

fun main() {
    assertTrue {
        containerWithMostWater(intArrayOf(1, 8, 100, 2, 100, 4, 8, 3, 7)).print() == 200
    }
    assertTrue {
        containerWithMostWater(intArrayOf(3, 2, 1, 3)).print() == 3 * 3
    }
    assertTrue {
        containerWithMostWater(intArrayOf(4, 3, 2, 1, 4)).print() == 16
    }
    assertTrue {
        containerWithMostWater(intArrayOf(1, 2, 1)).print() == 2
    }
    assertTrue {
        containerWithMostWater(intArrayOf(1, 1)).print() == 1
    }
    assertTrue {
        containerWithMostWater(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)).print() == 49
    }

}