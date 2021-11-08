package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 * [Source](https://leetcode.com/problems/maximum-product-subarray/) – [Solution](https://leetcode.com/problems/maximum-product-subarray/discuss/48302/2-Passes-scan-beats-99)
 */
@UseCommentAsDocumentation
private fun maxProduct(nums: IntArray): Int {
    var max = Integer.MIN_VALUE
    var prod = 1
    nums.forEach {
        prod *= it
        max = maxOf(prod, max)
        if (it == 0) {
            prod = 1
        }
    }

    prod = 1
    for (i in nums.lastIndex downTo 0) {
        prod *= nums[i]
        max = maxOf(max, prod)
        if (nums[i] == 0) {
            prod = 1
        }
    }
    return max
}

fun main() {
    maxProduct(intArrayOf(-3, 0, 1, -2)) shouldBe 1
    maxProduct(intArrayOf(-2, 0, -1)) shouldBe 0
    maxProduct(intArrayOf(0, 2)) shouldBe 2
    maxProduct(intArrayOf(-3, -1, -1)) shouldBe 3
    maxProduct(nums = intArrayOf(2, 3, -2, 4)) shouldBe 6

}