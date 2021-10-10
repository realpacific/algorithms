package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSameInAnyOrder

/**
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png" height="150" width="300">
 *
 * [Source](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
 *
 */
@UseCommentAsDocumentation
fun letterCombinations(digits: String): List<String> {
    val map = mapOf(
        2 to arrayOf('a', 'b', 'c'),
        3 to arrayOf('d', 'e', 'f'),
        4 to arrayOf('g', 'h', 'i'),
        5 to arrayOf('j', 'k', 'l'),
        6 to arrayOf('m', 'n', 'o'),
        7 to arrayOf('p', 'q', 'r', 's'),
        8 to arrayOf('t', 'u', 'v'),
        9 to arrayOf('w', 'x', 'y', 'z'),
    )

    val digit = digits.split("").filter { it != "" }
    val result = mutableListOf<String>()

    generateCombination(digit, map, index = 0, result)

    return result
}

/**
 * Start from first character, generate all result when there's only one number present i.e "2" in "234"
 * Then consider next character and using previous result, generate the newer result. i.e. "23" in "234", "234" in "234"
 */
private fun generateCombination(
    digit: List<String>,
    map: Map<Int, Array<Char>>,
    index: Int,
    result: MutableList<String>
) {
    if (index > digit.lastIndex) {
        return
    }

    val currentNumber: Int = digit[index].toInt()
    val characters: Array<Char> = map[currentNumber]!!

    // Store previous result
    val copy = ArrayList(result)

    if (result.isEmpty()) {
        result.addAll(characters.map { it.toString() })
    } else {
        result.clear()
        for (i in 0..copy.lastIndex) { // Use previous result to build the next result
            for (j in 0..characters.lastIndex) {
                result.add(copy[i] + characters[j])
            }
        }
    }
    generateCombination(digit, map, index + 1, result)
}

fun main() {
    assertIterableSameInAnyOrder(
        expected = listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
        actual = letterCombinations(digits = "23")
    )
    assertIterableSameInAnyOrder(
        expected = listOf(
            "adg", "adh", "adi", "aeg", "aeh", "aei", "afg", "afh",
            "afi", "bdg", "bdh", "bdi", "beg", "beh", "bei", "bfg", "bfh",
            "bfi", "cdg", "cdh", "cdi", "ceg", "ceh", "cei", "cfg", "cfh", "cfi"
        ),
        actual = letterCombinations(digits = "234")
    )
}