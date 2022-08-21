package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSameInAnyOrder

/**
 * Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * [Source](https://leetcode.com/problems/generate-parentheses/)
 */
@UseCommentAsDocumentation
private fun generateParenthesis(n: Int): List<String> {
    if (n == 1) return listOf(VALID_PAREN)
    val result = mutableSetOf<String>()
    result.add(VALID_PAREN)
    _generateParens(n, 0, result)
    return result                      // contains intermediate values from different iterations
        .filter { it.length == 2 * n } // remove the intermediate values (these are of length < 2*n)
        .toList()
}


private fun _generateParens(n: Int, currentIteration: Int, result: MutableSet<String>) {
    if (currentIteration > n) {
        return
    }
    HashSet(result)                              // clone
        .forEach {
            result.add(VALID_PAREN + it)         // add parens at start
            result.add(it + VALID_PAREN)         // add parens at end
            for (i in 0..it.length / 2) {
                val newValue = addPairAfterStartParensAt(it, i) // add parens in the middle
                result.add(newValue)
            }
        }
    _generateParens(n, currentIteration + 1, result)
}

private fun addPairAfterStartParensAt(str: String, startIndexOffset: Int): String {
    val occurrenceOfStartParen = str.indexOf('(', startIndex = startIndexOffset)
    return str.substring(0, occurrenceOfStartParen + 1)
        .plus(VALID_PAREN)
        .plus(str.substring(occurrenceOfStartParen + 1))
}

private const val VALID_PAREN = "()"

fun main() {

    assertIterableSameInAnyOrder(
        listOf("((()))", "(()())", "(())()", "()(())", "()()()"),
        generateParenthesis(3),
    )

    assertIterableSameInAnyOrder(
        listOf("()()", "(())"),
        generateParenthesis(2),
    )

    assertIterableSameInAnyOrder(
        actual = generateParenthesis(1),
        expected = listOf("()")
    )
}