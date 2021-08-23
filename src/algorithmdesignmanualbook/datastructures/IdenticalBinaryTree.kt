package algorithmdesignmanualbook.datastructures

import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Given two binary tree, find if they are identical i.e. same value at the same position & same structure
 */
class IdenticalBinaryTree(private val bst1: BinarySearchTree, private val bst2: BinarySearchTree) {

    fun areIdentical(): Boolean {
        val queue1 = LinkedList<Node>()
        val queue2 = LinkedList<Node>()
        queue1.add(bst1.getNode())
        queue2.add(bst2.getNode())

        // Breadth-first traversal
        while (queue1.isNotEmpty() || queue2.isNotEmpty()) {
            val node1 = queue1.pop()
            val node2 = queue2.pop()

            if (node1.left?.value != node2.left?.value) {
                return false
            }

            if (node1.right?.value != node2.right?.value) {
                return false
            }

            node1.left?.let(queue1::addLast)
            node1.right?.let(queue1::addLast)

            node2.left?.let(queue2::addLast)
            node2.right?.let(queue2::addLast)
        }

        return queue1.isEmpty() && queue2.isEmpty()
    }
}


/**
 * @suppress
 */
fun main() {
    val bst1 = createBST()
    val bst2 = createBST()
    assertTrue { IdenticalBinaryTree(bst1, bst2).areIdentical() }

    val bst3 = createBST()
    bst3.add(Node(100))
    assertFalse { IdenticalBinaryTree(bst1, bst3).areIdentical() }

    bst1.add(Node(100))
    assertTrue { IdenticalBinaryTree(bst1, bst3).areIdentical() }
}
