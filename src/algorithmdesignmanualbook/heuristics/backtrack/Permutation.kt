package algorithmdesignmanualbook.heuristics.backtrack

import utils.assertIterableSameInAnyOrder

class Permutation(val str: String) {
    val result = mutableListOf<String>()

    fun execute(): List<String> {
        if (str.isEmpty()) {
            result.add("")
            return result
        }
        permutation(str, "")
        return result
    }

    private fun permutation(current: String, prefix: String) {
        if (current.isEmpty()) {
            result.add(prefix)
        }
        for (i in 0..current.lastIndex) {
            val remainder = current.substring(0, i) + current.substring(i + 1)
            permutation(remainder, prefix + current[i])
        }
    }
}

fun main() {
    assertIterableSameInAnyOrder(
        actual = Permutation("").execute(),
        expected = listOf("")
    )
    assertIterableSameInAnyOrder(
        actual = Permutation("abc").execute(),
        expected = listOf("abc", "acb", "bac", "bca", "cab", "cba")
    )

    assertIterableSameInAnyOrder(
        actual = Permutation("ab").execute(),
        expected = listOf("ab", "ba")
    )
}