package arraysandstrings

import kotlin.test.assertTrue

fun main() {
    assertTrue(isRotation("waterbottle", "erbottlewat"))
}

private fun isRotation(s1: String, s2: String): Boolean {
    val concat = s2 + s2
    return (concat.contains(s1))
}