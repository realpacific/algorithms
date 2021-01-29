package arraysandstrings

import kotlin.test.assertEquals

fun main() {
    assertEquals("Mr%20John%20Smith", urlify("Mr John Smith     "))
    assertEquals(expected = "", actual = urlify("   "))
    assertEquals("Prashant%20Barahi", urlify("Prashant          Barahi      "))
}

private fun urlify(str: String): String {
    return str.split(" ").filter { it.isNotBlank() }.joinToString("%20")
}
