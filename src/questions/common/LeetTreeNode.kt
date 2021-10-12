package questions.common

import _utils.SkipDocumentation
import algorithmdesignmanualbook.padLeft
import java.util.*

@SkipDocumentation

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "TreeNode(${`val`})"
    }

    fun isLeaf() = left == null && right == null

    fun breadthFirstTraversal() {
        val queue = LinkedList<TreeNode?>()
        queue.add(this)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            print(current?.`val`?.toString()?.padLeft(3) ?: " null ")
            if (current != null) {
                queue.addLast(current.left)
                queue.addLast(current.right)
            }
        }
    }

    companion object {
        fun from(arr: Array<Int?>): TreeNode? {
            if (arr.isEmpty()) {
                return null
            }
            val node = TreeNode(arr[0]!!)

            val queue = LinkedList<TreeNode?>()
            queue.add(node)
            for (i in 1..arr.lastIndex) {
                var current = queue.first
                while (current == null) {
                    queue.removeFirstOrNull()
                    current = queue.first
                }

                val newNode = arr.getOrNull(i)?.let { TreeNode(it) }
                if (i % 2 != 0) {
                    current.left = newNode
                    queue.addLast(current.left)
                } else {
                    current.right = newNode
                    queue.addLast(current.right)
                    queue.removeFirstOrNull()
                }
            }
            return node
        }
    }
}

fun main() {
    // https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg
    val node = TreeNode.from(arrayOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1))
    node?.breadthFirstTraversal()
}