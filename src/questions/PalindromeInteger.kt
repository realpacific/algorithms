package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*
import kotlin.math.ceil
import kotlin.math.log10

/**
 * Write a function that checks whether an integer is a palindrome.
 *
 * For example, 191 is a palindrome, as well as 111. 123 is not a palindrome.
 */
@UseCommentAsDocumentation
private fun isIntegerAPalindrome(value: Int): Boolean {
    if (value <= 9) return true
    val length = (ceil(log10(value.toFloat()))).toInt() // calculate length of digit using log10

    val isOddLength = length % 2 != 0
    val stack: Stack<Int> = Stack<Int>()
    val midIndex = (length - 1) / 2         // go from last to the middle digit while pushing the digit to stack;
                                            // after reaching the middle, if the [value] is of odd length, ignore the middle digit
                                            // once it reaches the middle, pop the stack and compare popped value with the remaining digits

    var current = value
    var count = length - 1

    while (current != 0) {
        val lastDigit = current % 10        // get the last digit
        current /= 10                       // remove the last digit
        if (count > midIndex) {             // for the last half
            stack.push(lastDigit)               // push each digit into stack
        } else if (count == midIndex) {     // for the middle digit, start popping the stack and comparing it with remaining digits
            if (!isOddLength) {                 // if the [value] is of even length
                val popped = stack.pop()            // then treat it normally i.e. pop the stack
                if (popped != lastDigit) {          // check if popped digit is equal to the [lastDigit]
                    return false                        // not a palindrome
                }
            } else {                            // if the [value] is of odd length (eg. 12[3]21)
                                                    // then do nothing and ignore the middle digit i.e. 3
            }

        } else {                                // pop the stack and check if the value is equal to last index of [current]
            val popped = stack.pop()
            if (popped != lastDigit) {          // if popped value != last digit, then not a palindrome
                return false
            }
        }
        count--
    }
    return true
}

fun main() {
    isIntegerAPalindrome(1) shouldBe true

    isIntegerAPalindrome(191) shouldBe true
    isIntegerAPalindrome(111) shouldBe true
    isIntegerAPalindrome(11111) shouldBe true
    isIntegerAPalindrome(111111) shouldBe true
    isIntegerAPalindrome(121111) shouldBe false
    isIntegerAPalindrome(123) shouldBe false
    isIntegerAPalindrome(1234) shouldBe false
    isIntegerAPalindrome(12355) shouldBe false
    isIntegerAPalindrome(123321) shouldBe true
    isIntegerAPalindrome(12) shouldBe false
    isIntegerAPalindrome(121) shouldBe true
    isIntegerAPalindrome(1221) shouldBe true
    isIntegerAPalindrome(0) shouldBe true
}