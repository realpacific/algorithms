package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * You have `n` coins and you want to build a staircase with these coins.
 * The staircase consists of `k` rows where the `i`th row has exactly `i` coins.
 * The last row of the staircase may be incomplete.
 * Given the integer n, return the number of complete rows of the staircase you will build.
 *
 * [Source](https://leetcode.com/problems/arranging-coins/)
 */
@UseCommentAsDocumentation
private fun arrangeCoins(n: Int): Int {
    if (n == 1) return 1
    var temp = n
    var col = 0
    while (temp > 0) {
        col++
        temp -= col // load [col] number of coins on each column
    }
    var result = col

    // [temp] is -ve if last column remains unfilled
    // to fill this last column you can remove coins from last row and fill it
    // this will decrease the number of complete rows by 1
    if (temp < 0) {
        result--
    }
    return result

}

fun main() {
    arrangeCoins(2) shouldBe 1
    arrangeCoins(1) shouldBe 1
    arrangeCoins(5) shouldBe 2
    arrangeCoins(8) shouldBe 3
}