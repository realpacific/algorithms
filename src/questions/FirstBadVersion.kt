package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe


abstract class VersionControl(open val badVersion: Int) {
    fun isBadVersion(version: Int): Boolean {
        return version >= badVersion
    }

    abstract fun firstBadVersion(n: Int): Int
}

/**
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 *
 * Constraint `1 <= bad <= n <= 2^31 - 1`
 *
 * [Source](https://leetcode.com/problems/first-bad-version/)
 */
@UseCommentAsDocumentation
class Solution(override val badVersion: Int) : VersionControl(badVersion) {
    override fun firstBadVersion(n: Int): Int {
        // This is basically a binary search problem
        return binarySearch(0, n)
    }

    private fun binarySearch(low: Int, high: Int): Int {
        if (high < low) {
            return -1
        }

        // HERE: high can go as far as (2^31 - 1) so need to use Long to accommodate
        val mid = ((low.toLong() + high.toLong()) / 2).toInt()
        val midElement = mid + 1

        return if (isBadVersion(midElement)) {
            if (mid == 0 || (mid > 0 && !isBadVersion(midElement - 1))) { // also check for previous element
                // if previous element is not a bad version, then this is for sure the bad version
                mid + 1
            } else {
                // previous element was not bad version so continue checking
                binarySearch(low, mid - 1)
            }
        } else {
            binarySearch(mid + 1, high)
        }
    }
}

fun main() {
    run {
        Solution(badVersion = 1702766719).firstBadVersion(n = 2126753390) shouldBe 1702766719
    }

    run {
        Solution(badVersion = 4).firstBadVersion(n = 5) shouldBe 4
    }
}
