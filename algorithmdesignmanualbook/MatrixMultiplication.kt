package algorithmdesignmanualbook

import utils.Utils
import kotlin.test.assertFails

typealias  Matrix = Array<Array<Int>>
typealias  Dimen = Pair<Int, Int>

fun main() {
    val matA = arrayOf(arrayOf(1, 2, 3), arrayOf(3, 3, 3), arrayOf(5, 4, 3))
    val matB = arrayOf(arrayOf(1, 3), arrayOf(2, 3), arrayOf(3, 3))

    withBenchmark { multiplyMatrix(matA, matB) }

    val matA2 = arrayOf(arrayOf(1, 2, 3), arrayOf(3, 3, 3))
    val matB2 = arrayOf(arrayOf(1, 3), arrayOf(3, 3))

    assertFails { multiplyMatrix(matA2, matB2) }
}

/**
 * Complexity of this algorithm = O(xyz) i.e. CUBIC
 */
fun multiplyMatrix(matA: Matrix, matB: Matrix): Matrix {
    Utils.printArr(matA.toList())
    println("x")
    Utils.printArr(matB.toList())

    require(matA[0].size == matB.size) {
        "Matrix multiplication not possible between ${matA.dimension().str()} & ${matB.dimension().str()}"
    }

    val result = Array(matA[0].size) {
        Array(matB[0].size) { 0 }
    }

    for (i in 0..matA[0].lastIndex) {
        for (j in 0..matB[0].lastIndex) {
            result[i][j] = 0
            for (k in 0..matA.lastIndex) {
                result[i][j] += matA[i][k] * matB[k][j]
            }
        }

    }
    return result.also {
        Utils.printArr(result.toList())
        println()
    }
}


private fun Matrix.dimension(): Dimen = Pair(this.size, this[0].size)

private fun Dimen.str(): String = "${first}x${second} "
