package algorithmdesignmanualbook

import utils.assertArraysSame

/**
 * You have an unordered array X of n integers. Find the array M containing
 * n elements where Mi is the product of all integers in X except for Xi. You may not use division.
 */
private interface MultiplicativeArray {
    fun getMultiplicativeArray(array: Array<Int>): Array<Int>
}

/**
 *
 * Given array A, maintain two arrays
 * * X: holds product of `A[0]..A[i]` at index i
 * * Y: holds product of `A[lastIndex]..A[i]` at index i
 *
 * So now the item of result at index i is `X[i-1] * Y[i+1]`
 * [Solution here](https://www.geeksforgeeks.org/a-product-array-puzzle/)
 */
class MultiplicativeArrayWithDoubleArrayApproach : MultiplicativeArray {
    override fun getMultiplicativeArray(array: Array<Int>): Array<Int> {
        val fromFrontProduct = Array(array.size) { 1 }
        val fromBackProduct = Array(array.size) { 1 }
        for (i in 0..array.lastIndex) {
            fromFrontProduct[i] = fromFrontProduct.getOrElse(i - 1) { 1 } * array[i]
        }
        for (i in array.lastIndex downTo 0) {
            fromBackProduct[i] = fromBackProduct.getOrElse(i + 1) { 1 } * array[i]
        }

        val result = Array(array.size) { 0 }
        for (i in 0..array.lastIndex) {
            result[i] = fromFrontProduct.getOrElse(i - 1) { 1 } * fromBackProduct.getOrElse(i + 1) { 1 }
        }
        return result.print()
    }
}

/**
 * Find total product and at index `i`, divide the total product by the value `A[i]`
 *
 * *Note:* What if one of the element is 0 or multiple elements are 0
 */
class MultiplicativeArrayWithDivision : MultiplicativeArray {
    override fun getMultiplicativeArray(array: Array<Int>): Array<Int> {
        val zeroIndexFirst = array.indexOf(0)
        // Or traverse once and find all positions of 0
        val zeroIndexLast = array.lastIndexOf(0)
        // there are multiple zeros, then all elements are zero
        if (zeroIndexFirst != zeroIndexLast) {
            return Array(array.size) { 0 }.print()
        } else if (zeroIndexFirst > -1) {
            // there is only one index so only the index where 0 is will have total product
            val result = Array(array.size) { 0 }
            var totalProduct = 1
            array.forEachIndexed { index, i ->
                if (index != zeroIndexFirst) {
                    totalProduct *= i
                }
            }
            result[zeroIndexFirst] = totalProduct
            return result.print()
        }

        val result = Array(array.size) { 1 }

        var totalProduct = 1
        array.forEach { totalProduct *= it }
        for (i in 0..result.lastIndex) {
            result[i] = totalProduct / array[i]
        }
        return result.print()
    }
}


fun main() {
    val solutionWithDivision = MultiplicativeArrayWithDivision()
    val solutionWithDoubleArrayApproach = MultiplicativeArrayWithDoubleArrayApproach()

    listOf(solutionWithDivision, solutionWithDoubleArrayApproach)
            .forEach {
                withPrint(it.javaClass.simpleName) { it }
                assertArraysSame(
                        expected = arrayOf(120, 40, 60, 30, 24),
                        actual = it.getMultiplicativeArray(arrayOf(1, 3, 2, 4, 5))
                )
                assertArraysSame(
                        expected = arrayOf(0, 0, 0, 0, 0, 120),
                        actual = it.getMultiplicativeArray(arrayOf(1, 3, 2, 4, 5, 0))
                )
                assertArraysSame(
                        expected = arrayOf(0, 0, 0, 0, 0, 0),
                        actual = it.getMultiplicativeArray(arrayOf(0, 3, 2, 4, 5, 0))
                )
            }

}