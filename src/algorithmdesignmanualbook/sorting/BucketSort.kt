package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import utils.assertIterableSame

/**
 * Maintains bucket of 0..9 or a-z
 * The number of iterations requires depends on number of characters in longest element (length wise)
 *
 * [Algorithm](https://www.youtube.com/watch?v=JMlYkE8hGJM)
 */
class BucketSort(arr: IntArray) {
    // 0..9 bucket
    private val bucket = LinkedHashMap<Int, MutableList<Int>>()
    private val result = mutableListOf<Int>()

    /**
     * Length of longest element
     */
    private var iterations: Int

    init {
        (0..9).forEach {
            bucket[it] = mutableListOf()
        }
        result.addAll(arr.toList())

        val largestElement = arr.maxOrNull()!!
        iterations = largestElement.getNumberOfDigits()
    }

    fun run(): List<Int> {
        // Requires pass equal to digits in longest element
        (0 until iterations).forEach { pass ->
            bucketSort(pass)
        }
        return result.toList()
    }

    private fun bucketSort(pass: Int) {
        println("Pass $pass")
        emptyBucket()
        result.forEach { item ->
            // start from LSB in case of integer
            val digit = item.getDigitAt(iterations - 1 - pass)
            // add it to respective bucket, use digits value to find the bucket
            bucket[digit]!!.add(item)
        }
        result.clear()

        // pick element from bucket while maintaining the order 0..9
        bucket.values.forEach(result::addAll)

        println("Bucket: $bucket")
    }

    private fun emptyBucket() {
        bucket.values.forEach(MutableList<Int>::clear)
    }

    /**
     * Pad element at the start so that all element's length is same as the longest one
     */
    private fun Int.getDigitAt(pos: Int): Int {
        return Integer.valueOf(this.toString().padStart(iterations, '0')[pos].toString())
    }

    private fun Int.getNumberOfDigits(): Int {
        var number = this
        var count = 0
        while (number != 0) {
            number /= 10
            count++
        }
        return count
    }
}


fun main() {
    run {
        val input = intArrayOf(44, 12, 1, 300, 3, 66, 101, 7, 9)
        val sort = BucketSort(input)
        assertIterableSame(input.clone().sorted(), sort.run().print())
    }

    run {
        val input = intArrayOf(4400, 1102, 701, 3001, 333, 2, 555, 90, 14, 86, 4477, 98, 100)
        val sort = BucketSort(input)
        assertIterableSame(input.clone().sorted(), sort.run().print())
    }

}