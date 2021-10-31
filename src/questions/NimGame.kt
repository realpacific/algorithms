package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You are playing the following Nim Game with your friend:
 *
 * * Initially, there is a heap of stones on the table.
 * * You and your friend will alternate taking turns, and you go first.
 * * On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
 * * The one who removes the last stone is the winner.
 *
 * Given n, the number of stones in the heap, return true if you can win the game assuming
 * both you and your friend play optimally, otherwise return false.
 *
 * [Source](https://leetcode.com/problems/nim-game/)  – [Solution](https://leetcode.com/problems/nim-game/discuss/73749/Theorem:-all-4s-shall-be-false)
 */
@UseCommentAsDocumentation
private fun canWinNim(n: Int): Boolean {
    // Anyone who gets a 4 is destined to lose as other player gets either 1,2,3 stones remaining
    return n % 4 != 0
}

//    if (n <= 3) {
//        return true
//    }
//    val memo = Array<Boolean?>(n + 1) { null }
//    return playNim(n - 1, true, memo)
//            || playNim(n - 2, true, memo)
//            || playNim(n - 3, true, memo)
//}
//
//private fun playNim(remaining: Int, iRemovedIt: Boolean, memo: Array<Boolean?>): Boolean {
//    if (memo.getOrNull(remaining) != null) {
//        return memo[remaining]!!
//    }
//    if (remaining == 0) {
//        return iRemovedIt
//    }
//    if (remaining <= 3 && iRemovedIt) {
//        return false
//    } else if (remaining <= 3 && !iRemovedIt) {
//        return true
//    } else if (remaining == 4 && iRemovedIt) {
//        return true
//    }
//    return playNim(remaining - 1, !iRemovedIt, memo).also { println("${remaining - 1} == $it") }
//            || playNim(remaining - 2, !iRemovedIt, memo).also { println("${remaining - 2} == $it") }
//            || playNim(remaining - 3, !iRemovedIt, memo).also { println("${remaining - 3} == $it") }
//
//}
//
fun main() {
    canWinNim(8) shouldBe false
    canWinNim(5) shouldBe true
    canWinNim(4) shouldBe false
    canWinNim(2) shouldBe true
    canWinNim(1) shouldBe true

}