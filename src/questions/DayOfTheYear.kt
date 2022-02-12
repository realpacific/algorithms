package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe

/**
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 * [Source](https://leetcode.com/problems/day-of-the-year/)
 */
@UseCommentAsDocumentation
private fun dayOfYear(date: String): Int {
    // https://en.wikipedia.org/wiki/Month#/media/File:Month_-_Knuckles_(en).svg
    val numberOfDaysArr = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val (year, month, day) = date.split('-').map { it.toInt() }

    // https://docs.microsoft.com/en-us/office/troubleshoot/excel/determine-a-leap-year
    // year evenly divisible by 100 must also be divisible by 400
    val isLeapYear = if (year % 100 == 0) year % 400 == 0 else year % 4 == 0

    if (isLeapYear) numberOfDaysArr[1] = 29
    var result = 0
    for (i in 1 until month) {
        result += numberOfDaysArr[i - 1]
    }
    result += day
    return result
}

fun main() {
    dayOfYear("1900-05-02") shouldBe 122
    dayOfYear("2019-01-09") shouldBe 9
    dayOfYear("2019-02-10") shouldBe 41
}