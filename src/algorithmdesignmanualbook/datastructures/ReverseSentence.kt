package algorithmdesignmanualbook.datastructures

import algorithmdesignmanualbook.print
import kotlin.test.assertEquals

/**
 * Reverse the words in a sentence—i.e., “My name is Chris” becomes “Chris is name My.”
 */
private fun reverse(str: String): String {
    val split = str.split(" ")
    if (split.size <= 1) {
        return "$str."
    }
    val sb = StringBuilder(str.length + 1)
    for (i in split.lastIndex downTo 0) {
        sb.append(split[i])
        if (i != 0) {
            sb.append(" ")
        }
    }
    sb.append(".")
    return sb.toString().print()
}

/**
 * @suppress
 */
fun main() {
    assertEquals(expected = "Chris is name My.", actual = reverse("My name is Chris"))
}