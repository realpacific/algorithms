package questions.common

import _utils.SkipDocumentation
import algorithmdesignmanualbook.padLeft
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

    fun isSameAs(node: TreeNode?): Boolean {
        return Companion.isSameAs(this, node)
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

        fun from(first: Int, vararg arr: Int): TreeNode {
            return from(arrayOf(first, *arr.toTypedArray()))!!
        }

        fun isSameAs(n1: TreeNode?, n2: TreeNode?): Boolean {
            if (n1 == null && n2 == null) {
                return true
            }
            if ((n1 == null && n2 != null) || (n1 != null && n2 == null)) {
                return false
            }
            if (n1 != null && n2 != null && n1.`val` != n2.`val`) {
                return false
            }
            if (
                ((n1!!.left == null && n2!!.left == null) || (n1.left?.`val` == n2!!.left?.`val`))
                &&
                ((n1.right == null && n2.right == null) || (n1.right?.`val` == n2.right?.`val`))
            ) {
                return isSameAs(n1.left, n2.left) && isSameAs(n1.right, n2.right)
            }

            return false
        }
    }
}

fun main() {
    // https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg
    val node = TreeNode.from(arrayOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1))
    node?.breadthFirstTraversal()

    assertTrue(node!!.isSameAs(node))
    assertFalse(node.isSameAs(TreeNode.from(arrayOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null))!!))
    assertTrue(TreeNode.from(1, 2, 3, 4, 5).isSameAs(TreeNode.from(1, 2, 3, 4, 5)))
    assertFalse(TreeNode.from(1, 2, 3, 4, 5).isSameAs(TreeNode.from(1, 2, 3, 4, 5, 6)))
    assertFalse(TreeNode.from(1, 2, 3, 4, 5, 6, 7).isSameAs(TreeNode.from(1, 2, 3, 4, 5, 6)))
    assertFalse(TreeNode.from(1, 3, 2).isSameAs(TreeNode.from(1, 2, 3)))
}