package algorithmdesignmanualbook.partialsum

import algorithmdesignmanualbook.print
import java.util.*
import kotlin.experimental.and
import kotlin.experimental.inv

/**
 * Let A be an array of n real numbers. Design an algorithm to perform any sequence of the following operations:
 * • Add(i,y) – Add the value y to the ith number.
 * • Partial-sum(i) – Return the sum of the first i numbers
 * Each operation must take O(logn).
 *
 * Fenwick Tree or Binary Indexed Tree is a tree containing n+1 nodes.
 * Each node's parent is right most 1 flipped i.e
 * * 8 -> 1000 so parent is 0000 (0)
 * * 7 -> 0111 so parent is 0110 (6)
 * * 10 -> 1010 so parent is 1000 (8)
 * * 5 -> 0101 so parent is 0100 (4)
 * Sum can be obtained by (0..5) -> tree[6] + tree[4] + tree[0] i.e index+1 and then go upwards to parents
 *
 * To get the parent:
 * > * 2's complement (Flip all bits and add 1)
 * > * AND it with original number
 * > * Subtract it from original number
 * 7 (111) ---(step 1)---> 000+1=001 ---(AND 111)-->001 --(Subtract from 111)--->110
 *
 */
class PartialSumUsingFenwickTree(private val values: Array<Int>) {
    val tree = Array(values.size + 1) { 0 }

    init {
        construcFenwickTree()
    }

    fun removeLastSetBit(byte: Byte): Byte {
        return (byte - byte.and(byte.inv().plus(0b1).toByte())).toByte()
    }

    private fun construcFenwickTree() {
        values.forEachIndexed { index, value ->
            update(index, value)
        }
    }

    private fun update(index: Int, value: Int) {
        var bTreeIndex = index + 1
        while (bTreeIndex <= values.size) {
            tree[bTreeIndex] += value
            bTreeIndex += bTreeIndex.and(-bTreeIndex)
        }
    }

    fun getSum(index: Int): Int {
        var sum = 0
        var bIndex = index + 1
        while (bIndex > 0) {
            sum += tree[bIndex]
            bIndex -= bIndex.and(-bIndex)
        }
        return sum
    }

}


fun main() {
    val solution = PartialSumUsingFenwickTree(arrayOf(1, 2, 3, 4, 5, 6))
    solution.removeLastSetBit(0b111).print { it.toString(2) }
    solution.tree.print {
        Arrays.toString(it)
    }

    solution.getSum(2).print()
}