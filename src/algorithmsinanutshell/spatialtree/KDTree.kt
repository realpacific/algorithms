package algorithmsinanutshell.spatialtree

import algorithmdesignmanualbook.print
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * K-d tree is a binary search tree with more than 1 dimensions (i.e k dimensions).
 *
 * A 2-d tree looks like given points ((3, 6), (17, 15), (13, 15), (6, 12), (9, 1), (2, 7), (10, 19))
 *
 *
 *            (3, 6)   ----> compare x coordinate
 *           /       \
 *    (2,7)          (17, 15)     ----y  compare y
 *                      /     \
 *               (6,12)        (13,15)         ----x compare x
 *                    \        /
 *                   (9,1)  (10,19)        ----y
 *
 * At each level, the dimensions of points are compared in alternating manner.
 *
 */
class KDTree(val array: Array<Array<Int>>) {

    val tree: MultiDimNode

    init {
        require(array.isNotEmpty())
        require(array.map { it.size }.distinct().size == 1)
        tree = MultiDimNode(array[0])
        for (i in 1..array.lastIndex) {
            tree.insert(array[i], 0)
        }
    }

    fun print() {
        println(tree)
    }

    fun search() {

    }
}

class MultiDimNode(val value: Array<Int>, var left: MultiDimNode? = null, var right: MultiDimNode? = null) {

    fun insert(newValue: Array<Int>, height: Int) {
        val numberOfDimensions = newValue.size
        require(value.size == numberOfDimensions)
        val dimensionIndexToCompare = height % numberOfDimensions
        if (newValue[dimensionIndexToCompare] < value[dimensionIndexToCompare]) {
            if (left == null) {
                left = MultiDimNode(newValue)
            } else {
                left!!.insert(newValue, height + 1)
            }
        } else {
            if (right == null) {
                right = MultiDimNode(newValue)
            } else {
                right!!.insert(newValue, height + 1)
            }
        }
    }

    fun distanceFrom(target: Array<Int>): Double {
        var sum = 0.0
        for (i in 0..value.lastIndex) {
            sum += (target[i] - value[i]).toDouble().pow(2)
        }
        return sqrt(sum).print {
            "Distance ${target.toList()} ${value.toList()} = $it"
        }
    }

    override fun toString(): String {
        return "Node(${value.toList()}, left=$left, right=$right)"
    }
}

fun main() {
    val array = arrayOf(
        arrayOf(3, 6), arrayOf(17, 15), arrayOf(13, 15),
        arrayOf(6, 12), arrayOf(9, 1), arrayOf(2, 7), arrayOf(10, 19)
    )
    KDTree(array).print()
}
