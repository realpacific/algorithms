package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 * `A -> 1, B -> 2, C -> 3, Z -> 26, AA -> 27, AB -> 28`
 *
 * [Source](https://leetcode.com/problems/excel-sheet-column-title/)
 */
@UseCommentAsDocumentation
private fun convertToTitle(columnNumber: Int): String {
    val characters = CharArray(26) {
        (it + 65).toChar()
    }
    if (columnNumber <= 26) {
        return characters[columnNumber - 1].toString()
    }

    var remaining = columnNumber
    var result = ""
    while (remaining > 0) {
        result += characters[(remaining - 1) % 26]
        remaining = (remaining - 1) / 26
    }
    return result.reversed()
}

fun main() {
    convertToTitle(columnNumber = 1) shouldBe "A"
    convertToTitle(columnNumber = 28) shouldBe "AB"
    convertToTitle(columnNumber = 701) shouldBe "ZY"
    convertToTitle(columnNumber = 702) shouldBe "ZZ"
    convertToTitle(columnNumber = 2147483647) shouldBe "FXSHRXW"
}