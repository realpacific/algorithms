package algorithmdesignmanualbook.datastructures

import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @suppress
 */
fun main() {
    assertTrue { checkIfBalancedParentheses("()()()()") }
    assertTrue { checkIfBalancedParentheses("()") }
    assertTrue { checkIfBalancedParentheses("()(())") }
    assertFalse { checkIfBalancedParentheses("((())") }
    assertFalse { checkIfBalancedParentheses("(()") }
    assertFalse { checkIfBalancedParentheses("))") }
    assertFalse { checkIfBalancedParentheses("))") }
    assertFalse { checkIfBalancedParentheses(")") }
    assertFalse { checkIfBalancedParentheses(")())") }
    assertFalse { checkIfBalancedParentheses("())(") }

    assertEquals(findPositionOfInvalidParenthesesOrNull("()()"), null)
    assertEquals(findPositionOfInvalidParenthesesOrNull("()"), null)
    assertEquals(findPositionOfInvalidParenthesesOrNull("(())"), null)
    assertEquals(findPositionOfInvalidParenthesesOrNull("()()()()"), null)
    assertEquals(findPositionOfInvalidParenthesesOrNull("(()"), 2)
    assertEquals(findPositionOfInvalidParenthesesOrNull("(()))"), 4)
    assertEquals(findPositionOfInvalidParenthesesOrNull(")))"), 0)

}

fun findPositionOfInvalidParenthesesOrNull(string: String): Int? {
    if (string.isEmpty()) return null
    val stack = Stack<String>()
    string.forEachIndexed { index, c ->
        if (c == '(') {
            stack.push("(")
        } else if (c == ')') {
            if (stack.isEmpty()) {
                return index
            }
            stack.pop()
        }
    }
    return if (stack.isEmpty()) null else string.lastIndex
}

/**
 * @return true if a string contains properly nested and balanced parentheses, and false if otherwise.
 */
fun checkIfBalancedParentheses(string: String): Boolean {
    if (string.isEmpty()) return true
    val stack = Stack<String>()
    string.forEachIndexed { index, c ->
        if (c == '(') {
            stack.push("(")
        } else if (c == ')') {
            if (stack.isEmpty()) {
                return false
            }
            stack.pop()
        }
    }
    return stack.isEmpty()
}
