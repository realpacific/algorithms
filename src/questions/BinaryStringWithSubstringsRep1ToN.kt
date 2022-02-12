package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a binary string s and a positive integer n, return true if the binary representation of all the integers
 * in the range [1, n] are substrings of s, or false otherwise.
 * A substring is a contiguous sequence of characters within a string.
 *
 * [Source](https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/)
 */
@UseCommentAsDocumentation
private fun queryString(s: String, n: Int): Boolean {
    /**
     * Given n, returns r such that r < n and 2^x=r where x is +ve integer
     */
    fun findPowerOf2LowerThanN(): Int {
        var multiplication = 1
        while (multiplication < n) {
            if (multiplication * 2 >= n) {
                return multiplication
            }
            multiplication *= 2
        }
        return multiplication
    }

    val lowerPowerOf2 = findPowerOf2LowerThanN()

    // Theorem: If we can prove that s is substring of 4~n, s will contain substring for  1~n
    // Similarly, if we can prove that s is substring of 16~n, s will contain substring for 1~n
    // Similarly, if we can prove that s is substring of 32~n, s will contain substring for  1~n
    // We just have to prove this for n down to lower power of 2.
    for (i in n downTo lowerPowerOf2) {
        val binaryRep = Integer.toBinaryString(i)
        if (!s.contains(binaryRep)) return false
    }
    return true
}

fun main() {
    queryString(s = "100101110", n = 7) shouldBe true
    queryString(s = "0110", n = 3) shouldBe true

    queryString(s = "0110", n = 4) shouldBe false
}