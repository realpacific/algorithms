package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * You are given an array that contains an expression in Reverse Polish Notation.
 * Return the result from evaluating the expression. You can assume the expression will always be valid.
 *
 * Input - [“2”, “3”, “+”, “3”, “*”] Output - 15 because ( (2 + 3) * 3)
 *
 * [Source](https://leetcode.com/problems/evaluate-reverse-polish-notation/)
 */
@UseCommentAsDocumentation
private fun reversePolishNotation(tokens: Array<String>): Int {
    val operationFn: (String) -> (Int, Int) -> Int = {
        when (it) {
            "+" -> { left, right -> left + right }
            "-" -> { left, right -> left - right }
            "/" -> { left, right -> left / right }
            "*" -> { left, right -> left * right }
            else -> throw RuntimeException()
        }
    }
    val stack: Deque<Int> = LinkedList()
    for (item in tokens) {
        val operand = item.toIntOrNull()
        if (operand != null) stack.addLast(operand)
        else {
            val right = stack.removeLast()
            val left = stack.removeLast()
            val result = operationFn.invoke(item).invoke(left, right)
            stack.addLast(result)
        }
    }
    return stack.pop().toInt()
}

fun main() {
    reversePolishNotation(arrayOf("2", "1", "+", "3", "*")) shouldBe 9
    reversePolishNotation(arrayOf("4", "13", "5", "/", "+")) shouldBe 6
    reversePolishNotation(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")) shouldBe 22
}