package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string s consisting only of characters 'a', 'b', and 'c'.
 * You are asked to apply the following algorithm on the string any number of times:
 *
 * * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
 * * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
 * * The prefix and the suffix should not intersect at any index.
 * * The characters from the prefix and suffix must be the same.
 * * Delete both the prefix and the suffix.

 * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
 * [Source](https://leetcode.com/problems/min-length-after-deleting-similar-ends-kt/)
 */
@UseCommentAsDocumentation
private fun minimumLength(s: String): Int {
    if (s.length <= 1) return 1
    var frontPointer = 0
    var endPointer = s.lastIndex
    val totalLength = s.length

    if (s[frontPointer] != s[endPointer]) return totalLength

    var result = totalLength

    while (frontPointer < endPointer) {
        val frontChar = s[frontPointer]
        val endChar = s[endPointer]

        var nextFrontIndex = frontPointer + 1
        var nextEndIndex = endPointer - 1

        var countsOfCharRemovedFromFront = 0
        var countsOfCharRemovedFromBack = 0

        if (frontChar == endChar) { // prefix and suffix can be removed

            // delete prefix and suffix at current index
            countsOfCharRemovedFromFront++
            countsOfCharRemovedFromBack++

            // eg: 'bcb' the [nextEndIndex] and [nextFrontIndex] is same so this is the final answer
            if (nextFrontIndex == nextEndIndex) {
                result -= (countsOfCharRemovedFromFront + countsOfCharRemovedFromBack)
                return result
            }

            // remove all prefix that matches [frontChar]
            while (nextFrontIndex < endPointer && frontChar == s[nextFrontIndex]) {
                nextFrontIndex++
                countsOfCharRemovedFromFront++ // count how many prefixes have been removed
            }
            // remove all suffix that matches [endChar]
            while (nextEndIndex > nextFrontIndex && endChar == s[nextEndIndex]) {
                nextEndIndex--
                countsOfCharRemovedFromBack++ // count how many suffixes have been removed
            }

            if (nextFrontIndex == nextEndIndex) {
                return result
            } else {
                result -= (countsOfCharRemovedFromFront + countsOfCharRemovedFromBack)
            }

            frontPointer = nextFrontIndex
            endPointer = nextEndIndex
        } else {
            // nothing can be further removed
            break
        }
    }
    return result
}

fun main() {
    minimumLength("bbbbbbbbbbbbbbbbbbbbbbbbbbbabbbbbbbbbbbbbbbccbcbcbccbbabbb") shouldBe 1

    // - Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
    // - Take prefix = "b" and suffix = "bb" and remove them, s = "cca".
    minimumLength("aabccabba") shouldBe 3

    minimumLength("cabaabac") shouldBe 0

    minimumLength("ca") shouldBe 2
}