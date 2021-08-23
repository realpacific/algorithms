package algorithmdesignmanualbook.dynamic

import algorithmdesignmanualbook.print
import kotlin.system.measureTimeMillis
import kotlin.test.assertTrue

private fun editDistance(str1: String, str2: String, m: Int, n: Int): Int {
    // str2 insertion
    if (m == 0) {
        return n
    }
    // str1 insertion
    if (n == 0) {
        return m
    }

    // no changes. shift both to lower index for compare
    if (str1[m - 1] == str2[n - 1]) {
        return editDistance(str1, str2, m - 1, n - 1)
    }
    return 1 + listOf(
        editDistance(str1, str2, m - 1, n), //Insert
        editDistance(str1, str2, m, n - 1), // Delete
        editDistance(str1, str2, m - 1, n - 1), // Replace
    ).minOrNull()!!
}


private fun editDistance(str1: String, str2: String, m: Int, n: Int, mem: Array<Array<Int>>): Int {
    // str2 insertion
    if (m == 0) {
        mem[m][n] = n
        return n
    }
    // str1 insertion
    if (n == 0) {
        mem[m][n] = m
        return m
    }

    // no changes. shift both to lower index for compare
    if (str1[m - 1] == str2[n - 1]) {
        val distance = editDistance(str1, str2, m - 1, n - 1, mem)
        mem[m - 1][n - 1] = distance
        return distance
    }
    val insert = editDistance(str1, str2, m - 1, n, mem)
    mem[m - 1][n] = insert

    val delete = editDistance(str1, str2, m, n - 1, mem)
    mem[m][n - 1] = delete

    val replace = editDistance(str1, str2, m - 1, n - 1, mem)
    mem[m - 1][n - 1] = replace

    return 1 + listOf(
        insert, //Insert
        delete, // Delete
        replace, // Replace
    ).minOrNull()!!

}

/**
 * @suppress
 */
fun main() {
    run {
        val (str1, str2) = Pair("saturday", "friday")
        assertTrue {
            editDistance(str1, str2, str1.length, str2.length) == 5
        }
    }

    run {
        val (str1, str2) = Pair("saturday", "sunday")
        measureTimeMillis {
            assertTrue {
                editDistance(str1, str2, str1.length, str2.length) == 3
            }
        }
        measureTimeMillis {
            assertTrue {
                editDistance(str1, str2, str1.length, str2.length, Array(str1.length) { Array(str2.length) { 0 } }) == 3
            }
        }

    }

}