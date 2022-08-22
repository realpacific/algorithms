package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * Given an integer, convert it to a roman numeral.
 *
 * [Source](https://leetcode.com/problems/integer-to-roman/)
 */
@UseCommentAsDocumentation
private object IntToRoman {
    private val conversionRules = TreeMap<Int, String>() // important that it is a TreeMap

    init {
        // define all conversion rules
        listOf(
            1 to "I",
            5 to "V",
            10 to "X",
            50 to "L",
            100 to "C",
            500 to "D",
            1000 to "M"
        ).forEach {
            conversionRules[it.first] = it.second
        }

        // define the subtraction rules
        //
        // * I can be placed before V (5) and X (10) to make 4 and 9.
        // * X can be placed before L (50) and C (100) to make 40 and 90.
        // * C can be placed before D (500) and M (1000) to make 400 and 900.
        conversionRules[4] = "IV"
        conversionRules[9] = "IX"
        conversionRules[40] = "XL"
        conversionRules[90] = "XC"
        conversionRules[400] = "CD"
        conversionRules[900] = "CM"
    }

    fun intToRoman(num: Int): String {
        return _intToRoman(num, "")
    }

    private fun _intToRoman(num: Int, result: String): String {
        if (num == 0) return result                                 // exit case; weird how Romans didn't have the concept of ZERO
        val lowerKey = findPreviousLowest(num)                      // find the previous lowest and...
        val remaining = num - lowerKey                              // subtract it from num to get the remaining
        val currentValue = result + conversionRules[lowerKey]       // find the translation
        return _intToRoman(remaining, currentValue)                 // recurse till it hits the zero
    }

    private fun findPreviousLowest(num: Int): Int {
        if (conversionRules.containsKey(num)) return num // match found
        return conversionRules.lowerKey(num)!! // since conversionRules is a TreeMap, find the lower match using its method
    }
}

fun main() {
    IntToRoman.intToRoman(3) shouldBe "III"
    IntToRoman.intToRoman(4) shouldBe "IV"
    IntToRoman.intToRoman(5) shouldBe "V"
    IntToRoman.intToRoman(58) shouldBe "LVIII"
    IntToRoman.intToRoman(1994) shouldBe "MCMXCIV"
}