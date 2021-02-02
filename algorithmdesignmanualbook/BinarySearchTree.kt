package algorithmdesignmanualbook

import kotlin.test.assertEquals
import kotlin.test.assertTrue

open class BinarySearchTree(val node: Node) {
    open fun add(newNode: Node) {
        if (node.value < newNode.value) {
            if (node.right == null) {
                newNode.parent = node
                node.right = newNode
            } else {
                BinarySearchTree(node.right!!).add(newNode)
            }
        } else {
            if (node.left == null) {
                newNode.parent = node
                node.left = newNode
            } else {
                BinarySearchTree(node.left!!).add(newNode)
            }
        }
    }

    open fun deleteFirst(value: Int) {
        val parent = parentOfFirstValue(value) ?: return
        if (parent.left?.value == value) {
            parent.left = null
        } else {
            parent.right = null
        }
    }

    fun print() {
        println(node)
    }

    fun findOrNull(value: Int): Node? {
        if (value == node.value) {
            return node
        }
        if (value < node.value) {
            node.left?.let {
                val subTree = BinarySearchTree(it)
                return subTree.findOrNull(value)
            } ?: return null
        } else {
            node.right?.let {
                val subTree = BinarySearchTree(it)
                return subTree.findOrNull(value)
            } ?: return null
        }
    }

    fun min(): Int {
        var min: Node = this.node
        while (min.left != null) {
            min = min.left!!
        }
        return min.value
    }

    fun max(): Int {
        var max: Node = this.node
        while (max.right != null) {
            max = max.right!!
        }
        return max.value
    }

    fun traverseInOrder() {
        node.left?.toBST()?.traverseInOrder()
        println(node.value)
        node.right?.toBST()?.traverseInOrder()
    }

    fun traversePreOrder() {
        println(node.value)
        node.left?.toBST()?.traversePreOrder()
        node.right?.toBST()?.traversePreOrder()
    }

    fun traversePostOrder() {
        node.left?.toBST()?.traversePostOrder()
        node.right?.toBST()?.traversePostOrder()
        println(node.value)
    }

    fun parentOfFirstValue(value: Int): Node? {
        return findOrNull(value)?.parent
    }

    fun height(): Int {
        return kotlin.math.max(node.left?.toBST()?.height() ?: 0, node.right?.toBST()?.height() ?: 0) + 1
    }
}

fun main() {
    val node6 = Node.create(6)
    val bst = BinarySearchTree(node6)
    val node1 = Node.create(1).also(bst::add)
    val node4 = Node.create(4).also(bst::add)
    val node7 = Node.create(7).also(bst::add)
    val node10 = Node.create(10).also(bst::add)
    val node0 = Node.create(0).also(bst::add)

    bst.print()

    assertTrue(bst.findOrNull(1) != null)
    assertTrue(bst.findOrNull(100) == null)
    assertTrue(bst.max() == 10)
    assertTrue(bst.min() == 0)

    assertEquals(bst.parentOfFirstValue(1), node6)
    assertEquals(bst.parentOfFirstValue(4), node1)
    assertEquals(bst.parentOfFirstValue(7), node6)
    assertEquals(bst.parentOfFirstValue(10), node7)

    println("Inorder traversal")
    bst.traverseInOrder()

    println("Pre-order traversal")
    bst.traversePreOrder()

    println("Post-order traversal")
    bst.traversePostOrder()

    bst.deleteFirst(node0.value)
    assertTrue(bst.min() == 1)
    assertTrue(bst.findOrNull(0) == null)

    assertTrue(bst.height() == 3)

    bst.deleteFirst(node0.value)
    bst.deleteFirst(node4.value)
    bst.deleteFirst(node10.value)
    assertTrue(bst.height() == 2)

}