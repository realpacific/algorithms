package algorithmdesignmanualbook

import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFalse
import kotlin.test.assertTrue

open class BinarySearchTree(private var node: Node) {
    fun getNode() = node

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

    /**
     * [GeeksForGeek](https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/)
     * Three possibilities on delete:
     * * Delete at root, in this case, in-order search its right and make the min as root
     * * Delete leaf, then just remove it
     * * Delete mid, then remove the node and link the its parent to its child
     */
    open fun deleteFirst(value: Int) {
        val currentNode = findOrNull(value) ?: return
        val parent = currentNode.parent

        if (parent == null) {
            // is root node

            // This is the only one node!
            if (isLeafNode(currentNode)) {
                throw RuntimeException("Only one node remaining. Can't delete")
            }

            // All nodes are at left so make the left node as root
            if (currentNode.right == null) {
                val temp = currentNode.left!!
                temp.parent = null
                node = temp
            } else {
                // inorder traverse from right node and get the min node and make it root
                val minNodeAtRight = findMinFrom(currentNode.right)!!
                // delete the reference so that [minNodeAtRight] can be moved to the root
                when {
                    minNodeAtRight.parent?.left?.value == minNodeAtRight.value -> {
                        minNodeAtRight.parent?.left = null
                    }
                    minNodeAtRight.parent?.right?.value == minNodeAtRight.value -> {
                        minNodeAtRight.parent?.right = null
                    }
                }
                minNodeAtRight.parent = null
                minNodeAtRight.left = currentNode.left
                minNodeAtRight.right = currentNode.right
                minNodeAtRight.left?.parent = minNodeAtRight
                minNodeAtRight.right?.parent = minNodeAtRight
                node = minNodeAtRight
            }
        } else if (isLeafNode(currentNode)) {
            // If leaf node, just remove it
            if (parent.left?.value == value) {
                parent.left = null
            } else {
                parent.right = null
            }
        } else {
            // is mid node
            val tempParent = currentNode.parent!!
            val isAtLeft = tempParent.left?.value == currentNode.value
            // make it root temporarily
            currentNode.parent = null
            val tempTree = BinarySearchTree(currentNode)

            // delete itself and now the parent==null i.e (is root node) condition holds
            tempTree.deleteFirst(currentNode.value)

            // Now that the root node is deleted in [tempTree], change the parent of remaining node
            tempTree.node.parent = tempParent

            // Join the tempTree with the old tree
            if (isAtLeft) {
                // if it is at left of parent, put it at left
                tempParent.left = tempTree.node
            } else {
                tempParent.right = tempTree.node
            }
        }
    }

    fun deleteKthSmallestElement(k: Int) {
        val nodes = mutableListOf<BinarySearchTree>()
        traverseInOrder(node.toBST(), nodes, k)
        if (nodes.size < k) {
            throw RuntimeException("k is too large")
        }
        val nodeToBeDeleted = nodes.lastOrNull()?.node
        nodeToBeDeleted?.value?.let {
            println("Deleting $it")
            deleteFirst(it)
        }
    }

    private fun traverseInOrder(root: BinarySearchTree?, nodes: MutableList<BinarySearchTree>, until: Int) {
        require(until > 0)
        if (root == null) {
            return
        }
        traverseInOrder(root.node.left?.toBST(), nodes, until)
        // Requires double check
        if (nodes.size < until) {
            nodes.add(root.node.toBST())
        } else {
            return
        }
        traverseInOrder(root.node.right?.toBST(), nodes, until)
    }

    /**
     * In order traversal
     */
    fun findMinFrom(node: Node?): Node? {
        if (node?.left == null) {
            return node
        }
        return findMinFrom(node.left)
    }

    fun isLeafNode(node: Node) = node.isLeafNode()

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

    fun getRoot() = node
}

fun main() {
    example1()
    testForDeletion()
    testForDeleteKthSmallestElement()
}

fun testForDeleteKthSmallestElement() {
    //           10
    //     6          15
    //  4    7     12    19
    val bst = createBST()
    withPrint("3rd smallest item") {
        bst.deleteKthSmallestElement(3)
        assertTrue { bst.findOrNull(7) == null }
    }
    assertFails { bst.deleteKthSmallestElement(1000000) }
    withPrint("6th smallest item") {
        bst.deleteKthSmallestElement(6)
        assertTrue { bst.findOrNull(19) == null }
    }
    assertFails { bst.deleteKthSmallestElement(6) }

}

fun createBST(): BinarySearchTree {
    val node10 = Node.create(10)
    val node6 = Node.create(6)
    val node15 = Node.create(15)
    val node4 = Node.create(4)
    val node7 = Node.create(7)
    val node12 = Node.create(12)
    val node19 = Node.create(19)

    val bst = BinarySearchTree(node10)
    bst.add(node6)
    bst.add(node15)
    bst.add(node4)
    bst.add(node7)
    bst.add(node12)
    bst.add(node19)
    return bst
}

private fun testForDeletion() {
    val bst = createBST()
    //           10
    //     6          15
    //  4    7     12    19
    bst.print()

    assertTrue { bst.min() == 4 }
    // delete leaf
    //           10
    //     6          15
    //       7     12    19
    bst.deleteFirst(4)
    assertTrue { bst.min() == 6 }
    assertTrue { bst.max() == 19 }

    // delete non leaf and non-root
    bst.deleteFirst(6)
    //           10
    //      7         15
    //           12    19
    withPrint("After deleting 6") {
        bst.print()
    }
    assertTrue { bst.min() == 7 }
    assertTrue { bst.getRoot().value == 10 }
    assertTrue { bst.getRoot().left?.value == 7 }

    bst.deleteFirst(15)

    //           10
    //      7        19
    //            12
    assertTrue { bst.getRoot().right?.value == 19 }
    assertTrue { bst.getRoot().right?.parent?.value == 10 }
    assertTrue { bst.getRoot().right?.left?.value == 12 }
    assertTrue { bst.getRoot().left?.value == 7 }
    assertTrue { bst.getRoot().left?.parent?.value == 10 }


    //           10
    //      7        19*
    //            12
    bst.deleteFirst(19)
    //           10
    //      7        12
    withPrint("Delete 19") {
        bst.print()
    }
    assertTrue { bst.getRoot().right?.value == 12 }
    assertTrue { bst.getRoot().left?.value == 7 }

    bst.deleteFirst(10)
    //           12
    //      7
    assertTrue { bst.getRoot().value == 12 }
    assertTrue { bst.getRoot().left?.value == 7 }

    //      7
    //
    bst.deleteFirst(12)
    assertTrue { bst.getRoot().value == 7 }

}

private fun example1() {
    val node6 = Node.create(6)
    val bst = BinarySearchTree(node6)
    val node1 = Node.create(1).also(bst::add)
    val node4 = Node.create(4).also(bst::add)
    val node7 = Node.create(7).also(bst::add)
    val node10 = Node.create(10).also(bst::add)
    val node0 = Node.create(0).also(bst::add)

    assertTrue { bst.isLeafNode(node0) }
    assertFalse { bst.isLeafNode(node1) }
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
    bst.print()
    bst.deleteFirst(node6.value)
    bst.print()
}