package algorithmdesignmanualbook.sorting

import utils.assertArraysSame
import java.util.*

private fun mergeSort(array: IntArray, low: Int, high: Int) {
    if (array.isEmpty()) {
        return
    }
    // WARNING: if statement and not while
    if (low < high) {
        val mid = (low + high) / 2
        // break left
        mergeSort(array, low, mid)
        // break right
        mergeSort(array, mid + 1, high)
        // sort and merge
        merge(array, low, high, mid)
    }
}

fun merge(array: IntArray, low: Int, high: Int, mid: Int) {
    val queue1 = LinkedList<Int>()
    val queue2 = LinkedList<Int>()
    for (i in low..mid) {
        queue1.add(array[i])
    }
    for (i in mid + 1..high) {
        queue2.add(array[i])
    }

    var i = low
    // do till one of them is empty
    while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
        // pick the smallest of item; which is at head since at last level, only 1 item remains
        if (queue1.peek() < queue2.peek()) {
            array[i] = queue1.pop()
        } else {
            array[i] = queue2.pop()
        }
        i++
    }

    // Remaining items in queue1
    while (queue1.isNotEmpty()) {
        array[i] = queue1.pop()
        i++
    }
    // Remaining items in queue2
    while (queue2.isNotEmpty()) {
        array[i] = queue2.pop()
        i++
    }
}


/**
 * @suppress
 */
fun main() {
    val input1 = intArrayOf(6, 8, 1, 5, 2, 9, 11)
    mergeSort(input1, 0, input1.lastIndex)
    println(Arrays.toString(input1))
    assertArraysSame(expected = arrayOf(1, 2, 5, 6, 8, 9, 11), actual = input1.toTypedArray())


    val input2 = intArrayOf(8, 1, 5, 2, 9, 11)
    mergeSort(input2, 0, input2.lastIndex)
    println(Arrays.toString(input2))
    assertArraysSame(expected = arrayOf(1, 2, 5, 8, 9, 11), actual = input2.toTypedArray())
}