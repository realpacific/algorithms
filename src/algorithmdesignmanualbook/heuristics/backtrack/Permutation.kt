package algorithmdesignmanualbook.heuristics.backtrack

class Permutation(val str: String) {
    val result = mutableListOf<String>()

    fun execute(): List<String> {
        if (str.isEmpty()) {
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

/**
 * @suppress
 */
fun main() {
    Permutation("abc").execute().forEach {
        println(it)
    }
}