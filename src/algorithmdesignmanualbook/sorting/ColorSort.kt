package algorithmdesignmanualbook.sorting

import algorithmdesignmanualbook.print
import utils.assertIterableSame
import java.util.*

typealias Color = Pair<Int, String>

/**
 * Assume that we are given n pairs of items as input, where the first item is a
 * number and the second item is one of three colors (red, blue, or yellow).
 * Further assume that the items are sorted by number.
 * Give an O(n) algorithm to sort the items by color (all reds before all blues before all yellows)
 * such that the numbers for identical colors stay sorted.
 *
 * Solution: Maintain 3 queue for each color. At last, dequeue red, blue and then yellow
 */
fun colorSort(array: Array<Color>): List<Color> {
    array.print("Input:")
    val redQueue = LinkedList<Color>()
    val blueQueue = LinkedList<Color>()
    val yellowQueue = LinkedList<Color>()

    array.forEach {
        when (it.second) {
            "blue" -> blueQueue.addLast(it)
            "red" -> redQueue.addLast(it)
            "yellow" -> yellowQueue.addLast(it)
        }
    }
    val result = mutableListOf<Color>()
    while (redQueue.isNotEmpty()) {
        result.add(redQueue.removeFirst())
    }
    while (blueQueue.isNotEmpty()) {
        result.add(blueQueue.removeFirst())
    }
    while (yellowQueue.isNotEmpty()) {
        result.add(yellowQueue.removeFirst())
    }
    return result.print()
}

fun main() {
    val input = arrayOf(Pair(1, "blue"), Pair(3, "red"), Pair(4, "blue"), Pair(6, "yellow"), Pair(9, "red"))
    assertIterableSame(
        actual = colorSort(input),
        expected = listOf(Pair(3, "red"), Pair(9, "red"), Pair(1, "blue"), Pair(4, "blue"), Pair(6, "yellow"))
    )
}