package arraysandstrings

import kotlin.test.assertEquals

fun main() {
    assertEquals(compress("aabcccccaaa"), "a2b1c5a3")
    assertEquals(compress("abc"), "abc")
}

private fun compress(str: String): String {
    val sb = StringBuilder(str.length)

    var occurrenceCount = 0
    var prevCharacter: Char = str[0]

    for (currentChar in str) {
        if (prevCharacter != currentChar) {
            sb.append(prevCharacter).append(occurrenceCount)
            occurrenceCount = 0
        }
        prevCharacter = currentChar
        occurrenceCount += 1
    }
    sb.append(prevCharacter).append(occurrenceCount)
    val compressedString = sb.toString()
    return if (compressedString.length < str.length) return compressedString else str
}
