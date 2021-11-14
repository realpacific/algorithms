package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.lang.StringBuilder

/**
 * Design the CombinationIterator class:
 *
 * * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * * next() Returns the next combination of length combinationLength in lexicographical order.
 * * hasNext() Returns true if and only if there exists a next combination.
 *
 * [Source](https://leetcode.com/problems/iterator-for-combination/)
 */
@UseCommentAsDocumentation
class CombinationIterator(characters: String, combinationLength: Int) {
    private val cLength = combinationLength
    private val chars = characters

    // Hold indices of next return sequence
    // for eg: for combinationLength=3 and characters=abcde i.e.
    // trackIndex ranges from:
    //      012, 013, 014, 023, 024, 034, 123, 124, 134, 234
    //      abc, abd, abe, acd, ace, ade, bcd, bce, bde, cde
    // This pattern shows to generate next track index, you just have to make sure none of the digit crosses
    // its right digit i.e trackIndex[i] + 1 < trackIndex[i + 1]
    private var trackIndex: Array<Int>? = null

    init {
        findNextGenerator()
    }

    fun next(): String {
        val sb = StringBuilder(cLength)
        for (i in trackIndex!!) {
            sb.append(chars[i])
        }
        return sb.toString().also {
            findNextGenerator() // prepare next sequence
        }
    }

    private fun findNextGenerator() {
        if (trackIndex == null) { // for first time
            trackIndex = Array(cLength) { it } // for combinationLength=3, it will be [0,1,2]
        } else {
            setNextValidTrackIndex(cLength - 1)
        }
    }

    private fun setNextValidTrackIndex(index: Int) {
        require(trackIndex != null)
        trackIndex!!.let { track ->
            if (index < 0) {
                trackIndex = null // no more sequence possible
                return
            }
            val rightDigitValue = track.getOrNull(index + 1) ?: chars.length
            if (track[index] + 1 < rightDigitValue) { // to prevent collision like [0,3,3] make sure 1 can be safely added
                track[index] = track[index] + 1
                val current = track[index]

                // next sequence after [0,3,4] is [1,2,3] so
                var dist = 1
                for (i in index + 1..track.lastIndex) {
                    track[i] = dist + current
                    dist++
                }
            } else {
                setNextValidTrackIndex(index - 1)
            }
        }

    }

    fun hasNext(): Boolean {
        return trackIndex != null // next sequence is already prepared so just check if not null
    }

}

fun main() {
    run {
        val itr = CombinationIterator("abc", 2)
        itr.next() shouldBe "ab"
        itr.hasNext() shouldBe true
        itr.next() shouldBe "ac"
        itr.hasNext() shouldBe true
        itr.next() shouldBe "bc"
        itr.hasNext() shouldBe false
    }

    run {
        val itr = CombinationIterator("abcd", 3)
        itr.next() shouldBe "abc"
        itr.hasNext() shouldBe true
        itr.next() shouldBe "abd"
        itr.hasNext() shouldBe true
        itr.next() shouldBe "acd"
        itr.hasNext() shouldBe true
        itr.next() shouldBe "bcd"
        itr.hasNext() shouldBe false
    }

    run {
        CombinationIterator("chp", 1).apply {
            hasNext() shouldBe true
            next() shouldBe "c"
            hasNext() shouldBe true
            hasNext() shouldBe true
            next() shouldBe "h"
            next() shouldBe "p"
            hasNext() shouldBe false
            hasNext() shouldBe false
            hasNext() shouldBe false
            hasNext() shouldBe false
            hasNext() shouldBe false
        }
    }

    run {
        CombinationIterator("ahijp", 2).apply {
            hasNext() shouldBe true
            next() shouldBe "ah"
            next() shouldBe "ai"
            hasNext() shouldBe true
            next() shouldBe "aj"
            hasNext() shouldBe true
            next() shouldBe "ap"
            hasNext() shouldBe true
            next() shouldBe "hi"
            next() shouldBe "hj"
        }
    }
}