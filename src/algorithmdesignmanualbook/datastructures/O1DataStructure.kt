package algorithmdesignmanualbook.datastructures

import algorithmdesignmanualbook.withPrint
import utils.PrintUtils
import utils.assertIterableSameInAnyOrder
import java.util.*

/**
 * Construct a DS with search, remove and add operations of O(1) in worst case
 * The elements are drawn from the finite set and initialization can take place at O(n)
 */
class O1DataStructure {
    /**
     * Value to [array] index mapping
     */
    private val map = mutableMapOf<Int, Int>()
    private val array = mutableListOf<Int>()

    fun add(value: Int) {
        if (map.containsKey(value)) {
            return
        }
        // Update both ds
        array.add(value)
        map[value] = array.lastIndex
    }

    fun remove(value: Int) {
        if (!map.containsKey(value)) {
            return
        }
        // Swap with the last element
        swap(map[value]!!, array.size - 1)
        // remove from map
        map.remove(value)
        // Remove the last element
        array.removeAt(array.size - 1)
    }

    fun getRandomOrNull(): Int? {
        if (array.isEmpty()) {
            return null
        }
        val rand = Random()
        return array.elementAt(rand.nextInt(array.size))
    }

    private fun swap(srcIndex: Int, destIndex: Int) {
        val srcValue = array[srcIndex]
        val destValue = array[destIndex]
        // Update array
        val temp = array[srcIndex]
        array[srcIndex] = destValue
        array[destIndex] = temp

        // Update map
        map[srcValue] = destIndex
        map[destValue] = srcIndex
    }

    fun print() {
        PrintUtils.println(array)
    }

    fun asList(): List<Int> {
        return array
    }
}

fun main() {
    val ds = O1DataStructure()
    ds.add(1)
    ds.add(2)
    ds.add(3)
    ds.add(4)
    assertIterableSameInAnyOrder(expected = listOf(1, 2, 3, 4), actual = ds.asList())
    ds.print()

    withPrint("Random elements") {
        println(ds.getRandomOrNull())
        println(ds.getRandomOrNull())
        println(ds.getRandomOrNull())
    }

    ds.remove(1)
    ds.remove(2)
    ds.print()
    assertIterableSameInAnyOrder(expected = listOf(3, 4), actual = ds.asList())
    ds.remove(3)
    ds.remove(4)
    assertIterableSameInAnyOrder(expected = listOf(), actual = ds.asList())
    ds.print()
}
