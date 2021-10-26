package questions

import _utils.UseCommentAsDocumentation
import questions.common.ClassInvoker
import java.util.*

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * [Source](https://leetcode.com/problems/min-stack/)
 */
@UseCommentAsDocumentation
class MinStack {

    private val array = mutableListOf<Int?>()
    private val map = TreeMap<Int, MutableList<Int>>()

    fun push(`val`: Int) {
        array.add(`val`)
        map[`val`] = map.getOrDefault(`val`, mutableListOf()).apply {
            this.add(array.lastIndex)
        }
    }

    fun pop() {
        val lastIndex = array.lastIndex
        val elem = array.removeAt(lastIndex)
        val indices = map[elem]!!
        indices.removeAt(indices.size - 1)
        if (indices.isEmpty()) {
            map.remove(elem)
        }
    }

    fun top(): Int {
        val lastIndex = array.lastIndex
        return array[lastIndex]!!
    }

    fun getMin(): Int {
        return map.firstKey()
    }

    fun print() {
        println(array)
    }
}


fun main() {

    run {
        val invoker = ClassInvoker<IntArray, Int>(
            listOf(MinStack::class.java.simpleName, "push", "push", "push", "getMin", "pop", "top", "getMin")
        ) {
            it.getOrNull(0)
        }
        invoker.invoke(
            listOf(
                intArrayOf(),
                intArrayOf(-2),
                intArrayOf(0),
                intArrayOf(-3),
                intArrayOf(),
                intArrayOf(),
                intArrayOf(),
                intArrayOf()
            ),
            listOf(null, null, null, null, -3, null, 0, -2)
        )

    }
}