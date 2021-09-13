package questions

import kotlin.test.assertEquals

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * Input: text = "nlaebolko"; Output: 1
 *
 * [Source](https://leetcode.com/explore/item/3973)
 */
fun maxNumberOfBalloons(text: String): Int {
    if (text.length < "balloon".length)
        return 0

    val balloon = arrayOf(0, 0, 0, 0, 0, 0, 0)
    // OR use when
    val strategy: Map<Char, () -> Unit> = mapOf(
        'b' to { balloon[0]++ },
        'a' to { balloon[1]++ },
        'n' to { balloon[6]++ },
        'l' to {
            if (balloon[2] == balloon[3]) {
                balloon[2]++
            } else if (balloon[2] < balloon[3]) {
                balloon[2]++
            } else {
                balloon[3]++
            }
        },
        'o' to {
            if (balloon[4] == balloon[5]) {
                balloon[4]++
            } else if (balloon[4] < balloon[5]) {
                balloon[4]++
            } else {
                balloon[5]++
            }
        }
    )
    text.forEach {
        strategy[it]?.invoke()
    }
    return balloon.min()!!
}

fun main() {
    assertEquals(1, maxNumberOfBalloons("nlaebolko"))
    assertEquals(2, maxNumberOfBalloons("loonbalxballpoon"))
    assertEquals(0, maxNumberOfBalloons("leetcode"))
    assertEquals(1, maxNumberOfBalloons("balloon"))
    assertEquals(3, maxNumberOfBalloons("balloonballoonballoon"))
}