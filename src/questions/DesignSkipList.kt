package questions

import _utils.UseCommentAsDocumentation
import questions.common.ClassInvoker
import utils.shouldBe
import kotlin.random.Random

/**
 * <img src="https://assets.leetcode.com/uploads/2019/09/27/1506_skiplist.gif" height="200" width="800"/>
 *
 * [Source](https://leetcode.com/problems/design-skiplist/) – [Gist](https://www.youtube.com/watch?v=UGaOXaXAM5M)
 */
@UseCommentAsDocumentation
class SkipList {
    private val _headNode = Array<Node>(3) { level ->
        Node(Int.MIN_VALUE).also {
            it.level = level
        }
    }
    private val random = Random(58)

    private var array: Array<Node>

    init {
        for (i in 1.._headNode.lastIndex) {
            _headNode[i].downRef = _headNode[i - 1]
        }
        array = Array(_headNode.size) {
            _headNode[it]
        }
    }

    fun search(target: Int): Boolean {
        return search(array.last(), target)
    }

    private fun search(node: Node?, target: Int): Boolean {
        if (node == null) {
            return false
        }
        val parent = findParentInCurrentLevel(node, target)
        if (parent.value == target) {
            return true
        }
        if (parent.next != null) {
            if (parent.next!!.value == target) {
                return true
            }
        }
        return search(parent.downRef, target)
    }


    /**
     * Parent is the node with value less than [target]
     * and the [Node.next] pointer points to a [Node] whose value is higher than [target]
     */
    private fun findParentInCurrentLevel(node: Node, target: Int): Node {
        var current: Node? = node
        while (current != null) {
            if (current.next == null) {
                return current
            } else if (current.value < target && current.next!!.value >= target) {
                return current
            }
            current = current.next
        }
        return node

    }


    fun add(num: Int) {
        val node = Node(num)
        val level0Parent = findParentInCurrentLevel(array[0], num)
        val next = level0Parent.next
        // level 0 must contain the node
        level0Parent.next = node
        level0Parent.level = 0
        node.next = next
        var tempNode = node
        for (i in 1..array.lastIndex) {
            if (shouldAddUp()) {
                val cloneNode = Node(num)
                cloneNode.downRef = tempNode
                cloneNode.level = i
                val parent = findParentInCurrentLevel(array[i], num)
                val next = parent.next
                parent.next = cloneNode
                cloneNode.next = next
                tempNode = cloneNode
            }
        }
    }

    private fun shouldAddUp(): Boolean {
        return random.nextBoolean()
    }

    fun erase(num: Int): Boolean {
        val eraseList = mutableSetOf<Int>()
        for (i in array.lastIndex downTo 0) {
            if (i !in eraseList) {
                erase(array[i], num, eraseList)
            }
        }
        return eraseList.isNotEmpty()
    }

    private fun erase(node: Node?, target: Int, erasedList: MutableSet<Int>) {
        if (node == null) {
            return
        }
        val parent = findParentInCurrentLevel(node, target)
        if (parent.next == null) {
            // Not found in current level, so start from scratch in one level below
            // erase(array.getOrNull(parent.level - 1), target, erasedList)
        } else if (parent.next!!.value != target) {
            // erase(array.getOrNull(parent.level - 1), target, erasedList)
        } else if (parent.next!!.value == target) {
            val nodeToBeRemoved = parent.next!!
            erasedList.add(nodeToBeRemoved.level) // collect the levels at which the erasure happened
            parent.next = parent.next?.next
        }
    }


    inner class Node(val value: Int) {
        var next: Node? = null
        var downRef: Node? = null
        var level: Int = 0

        override fun toString(): String {
            return "Node($value, lvl=${level}, next=${next?.value})"
        }
    }

}

fun main() {
    SkipList().apply {
        add(2)
        add(3)
        add(5)
        add(9)
        add(15)
        add(0)
        add(7)
        search(15) shouldBe true
        search(9) shouldBe true
        search(2) shouldBe true
        search(5) shouldBe true
        search(0) shouldBe true
        search(7) shouldBe true
        search(11) shouldBe false
        search(22) shouldBe false
        search(33) shouldBe false
        search(90) shouldBe false
        add(40)
        search(40) shouldBe true
        erase(40) shouldBe true
        search(40) shouldBe false
        erase(7) shouldBe true
        search(7) shouldBe false

    }

    SkipList().apply {
        for (i in 0..100) {
            add(i)
            add(i)
            add(i)
        }
        for (i in 1..50) {
            search(i) shouldBe true
            erase(i) shouldBe true
            erase(i) shouldBe true
            erase(i) shouldBe true
            erase(i) shouldBe false
        }
    }

    run {
        val invoker = ClassInvoker<IntArray, Int>(
            listOf(
                "SkipList",
                "add",
                "add",
                "add",
                "add",
                "add",
                "erase",
                "erase",
                "add",
                "search",
                "search",
                "add",
                "erase",
                "search",
                "add",
                "add",
                "add",
                "erase",
                "search",
                "erase",
                "search",
                "search",
                "search",
                "erase",
                "erase",
                "search",
                "erase",
                "add",
                "add",
                "erase",
                "add",
                "search",
                "search",
                "search",
                "search",
                "search"
            )
        ) {
            it.first()
        }
        invoker.invoke(
            listOf(
                intArrayOf(),
                intArrayOf(9),
                intArrayOf(4),
                intArrayOf(5),
                intArrayOf(6),
                intArrayOf(9),
                intArrayOf(2),
                intArrayOf(1),
                intArrayOf(2),
                intArrayOf(7),
                intArrayOf(4),
                intArrayOf(5),
                intArrayOf(6),
                intArrayOf(5),
                intArrayOf(6),
                intArrayOf(7),
                intArrayOf(4),
                intArrayOf(3),
                intArrayOf(6),
                intArrayOf(3),
                intArrayOf(4),
                intArrayOf(3),
                intArrayOf(8),
                intArrayOf(7),
                intArrayOf(6),
                intArrayOf(7),
                intArrayOf(4),
                intArrayOf(1),
                intArrayOf(6),
                intArrayOf(3),
                intArrayOf(4),
                intArrayOf(7),
                intArrayOf(6),
                intArrayOf(1),
                intArrayOf(0),
                intArrayOf(3)

            ),
            expectedAnswers = listOf(
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                false,
                null,
                false,
                true,
                null,
                true,
                true,
                null,
                null,
                null,
                false,
                true,
                false,
                true,
                false,
                false,
                true,
                true,
                false,
                true,
                null,
                null,
                false,
                null,
                false,
                true,
                true,
                false,
                false
            )
        )
    }
}