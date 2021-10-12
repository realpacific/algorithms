package questions

import _utils.UseCommentAsDocumentation
import java.lang.StringBuilder
import kotlin.test.assertFalse
import kotlin.test.assertTrue


/**
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 */
@UseCommentAsDocumentation
fun isPalindrome(s: String): Boolean {
    val reg = Regex("[^A-Za-z0-9]")
    // Remove all non-alphanumeric and ignore case
    val newString = reg.replace(s, "").toLowerCase()

    // return StringBuilder(newString).reverse().toString() == newString

    // compare i and (lastIndex-i)
    for (i in 0..newString.lastIndex) {
        if (newString[i] != newString[newString.lastIndex - i]) {
            return false
        }
    }
    return true
}

fun main() {
    assertTrue(isPalindrome("A man, a plan, a canal: Panama"))
    assertFalse(isPalindrome("race a car"))
    assertTrue(isPalindrome("race car"))
    assertTrue(isPalindrome("racecar"))
}