package questions

import _utils.UseCommentAsDocumentation
import utils.shouldBe
import java.util.*

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues.
 * The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 *
 * [Source](https://leetcode.com/problems/implement-stack-using-queues/)
 */
@UseCommentAsDocumentation
class MyStack {
    private val queue: Queue<Int> = LinkedList()
    private val backup: Queue<Int> = LinkedList()

    fun push(x: Int) {
        queue.add(x)
    }

    fun pop(): Int {
        val elem: Int
        while (true) {
            val next = queue.remove()
            if (queue.isNotEmpty()) {
                // Do not put last element
                backup.add(next)
            } else {
                // record the last element
                elem = next
                break
            }
        }
        // Offload from [backup] queue
        while (backup.isNotEmpty()) {
            val next = backup.remove()
            queue.add(next)
        }
        return elem
    }

    fun top(): Int {
        val elem: Int
        while (true) {
            val next = queue.remove()
            backup.add(next) // backup all elements
            // Record last element
            if (queue.isEmpty()) {
                elem = next
                break
            }
        }
        while (backup.isNotEmpty()) {
            val next = backup.remove()
            queue.add(next)
        }
        return elem
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }
}

fun main() {
    val myStack = MyStack()
    myStack.push(1)
    myStack.push(2)
    myStack.top() shouldBe 2
    myStack.pop() shouldBe 2
    myStack.empty() shouldBe false
}
