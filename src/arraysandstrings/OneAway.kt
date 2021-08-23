package arraysandstrings

import kotlin.math.abs
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object OneAway {
    fun isOneAway(str1: String, str2: String): Boolean {
        println("\n--------------")
        println("$str1  $str2")

        val lengthDiffAbs = abs(str1.length - str2.length)
        if (lengthDiffAbs >= 2) {
            return false
        }
        return if (str1.length == str2.length) {
            checkIfOneEdited(str1, str2)
        } else {
            checkIfInserted(str1, str2)
        }
    }

    private fun String.count(): MutableMap<Char, Int> {
        val countMap = mutableMapOf<Char, Int>()
        for (i in this) {
            countMap[i] = (countMap[i] ?: 0) + 1
        }
        return countMap
    }

    private fun checkIfInserted(str1: String, str2: String): Boolean {
        val str1Count = str1.count()
        val str2Count = str2.count()

        var numberOfInsertions = 0

        str1Count.keys.union(str2Count.keys).forEach {
            if (str1Count[it] != str2Count[it]) {
                println("checkIfInserted: $it")
                numberOfInsertions += 1
            }
        }
        return numberOfInsertions == 1
    }

    private fun checkIfOneEdited(str1: String, str2: String): Boolean {
        var editCount = 0
        str1.forEachIndexed { index, c ->
            if (str2[index] != c) {
                editCount += 1
                println("checkIfOneEdited ${str2[index]}")
            }
        }
        return editCount == 1
    }
}

/**
 * @suppress
 */
fun main() {
    assertFalse(actual = OneAway.isOneAway("pacos", "acpos"))

    assertTrue(actual = OneAway.isOneAway("paco", "aco"))
    assertTrue(actual = OneAway.isOneAway("paco", "baco"))
    assertTrue(actual = OneAway.isOneAway("pacos", "paco"))
    assertFalse(actual = OneAway.isOneAway("pacos", "pacosss"))

    assertTrue(actual = OneAway.isOneAway("pale", "ple"))
    assertTrue(actual = OneAway.isOneAway("pales", "pale"))
    assertTrue(actual = OneAway.isOneAway("pale", "bale"))
    assertFalse(actual = OneAway.isOneAway("pale", "bae"))


}