package algorithmdesignmanualbook

import utils.assertIterableSameInAnyOrder
import java.util.*
import kotlin.test.assertTrue

class FlattenBSTIntoLinkedList(private val bst: BinarySearchTree) {

    fun flatten(): LinkedList<Int> {
        val linkedList = LinkedList<Int>()
        inOrderTraversal(bst.getNode(), linkedList)
        return linkedList
    }

    private fun inOrderTraversal(currentNode: Node?, holder: LinkedList<Int>) {
        if (currentNode == null) {
            return
        }
        inOrderTraversal(currentNode.left, holder)
        holder.add(currentNode.value)
        inOrderTraversal(currentNode.right, holder)
    }
}


fun main() {
    val bst1 = createBST()
    //           10
    //     6          15
    //  4    7     12    19
    bst1.print()
    val flatten = FlattenBSTIntoLinkedList(bst1).flatten()
    flatten.print()

    assertIterableSameInAnyOrder(actual = flatten, expected = listOf(4, 6, 7, 10, 12, 15, 19))
}
