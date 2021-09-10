package algorithmdesignmanualbook.sorting

import utils.assertArraysSame
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.*
import kotlin.test.assertTrue

class HeapSort {
    private var array = emptyArray<Int>()
    private var nextIndex = 0

    fun peekArray() = array.copyOf(array.size)

    fun add(element: Int) {
        if (array.isEmpty() || nextIndex > array.lastIndex) {
            growArray()
        }
        array[nextIndex] = element
        bubbleUp(nextIndex)
        nextIndex++
    }

    private fun growArray() {
        // Size is always index + 1
        val temp = Array(nextIndex + 1) { 0 }
        System.arraycopy(array, 0, temp, 0, array.size)
        array = temp
    }

    private fun shrinkArray() {
        val temp = Array(array.size - 1) { 0 }
        System.arraycopy(array, 0, temp, 0, array.size - 1)
        array = temp
        nextIndex--
    }

    private fun bubbleUp(index: Int) {
        var currentIndex = index
        var parentIndex = parentIndexOfIndex(currentIndex)
        while (currentIndex != parentIndex && array[parentIndex] > array[currentIndex]) {
            swap(parentIndex, currentIndex)
            currentIndex = parentIndex
            parentIndex = parentIndexOfIndex(currentIndex)
        }
    }

    private fun swap(from: Int, to: Int) {
        println("Swapping index:$from-->$to; value:${array[from]}-->${array[to]}")
        val temp = array[from]
        array[from] = array[to]
        array[to] = temp
    }

    private fun parentIndexOfIndex(index: Int): Int = if (index % 2 == 0) max(0, index / 2 - 1) else index / 2

    private fun indexOfLeftChildOfIndex(index: Int): Int = index * 2 + 1

    private fun indexOfRightChildOfIndex(index: Int): Int = index * 2 + 2

    fun print() = println(Arrays.toString(array))

    fun peekMinValue() = array[0]

    fun deleteMin() {
        // Bring right most element to the root
        swap(0, array.lastIndex)
        // Delete the last element
        shrinkArray()
        // then bubble root element down
        bubbleDown()
    }

    private fun bubbleDown() {
        var currentIndex = 0
        while (currentIndex < array.size) {
            val leftIndex = indexOfLeftChildOfIndex(currentIndex)
            val rightIndex = indexOfRightChildOfIndex(currentIndex)
            // Left index is filled first so it must be less than last index
            if (leftIndex > array.lastIndex) {
                break
            }
            val leftValue = array[leftIndex]
            val rightValue = array.getOrNull(rightIndex) ?: Integer.MAX_VALUE

            // WARNING: MUST compare with both left and right node and swap with the minimum
            val minValue = min(leftValue, rightValue)

            // Satisfactory position
            if (minValue >= array[currentIndex]) {
                break
            }

            if (minValue == leftValue) {
                // swap with left
                swap(leftIndex, currentIndex)
                currentIndex = leftIndex
            } else if (minValue == rightValue) {
                // swap with right
                swap(rightIndex, currentIndex)
                currentIndex = rightIndex
            } else {
                break
            }
        }
    }
}

fun main() {
    val sort = HeapSort()
    sort.add(20)
    sort.add(30)
    sort.add(40)
    sort.add(50)
    sort.add(60)
    sort.print()
    assertArraysSame(expected = arrayOf(20, 30, 40, 50, 60), actual = sort.peekArray())
    sort.add(10)
    sort.print()
    assertArraysSame(expected = arrayOf(10, 30, 20, 50, 60, 40), actual = sort.peekArray())
    assertTrue { sort.peekMinValue() == 10 }

    sort.deleteMin()
    assertArraysSame(expected = arrayOf(20, 30, 40, 50, 60), actual = sort.peekArray())

    sort.deleteMin()
    assertTrue { sort.peekMinValue() == 30 }
    assertArraysSame(expected = arrayOf(30, 50, 40, 60), actual = sort.peekArray())
}