package algorithmsinanutshell

import kotlin.math.max
import kotlin.test.assertEquals

/**
 * Always a balanced tree
 * https://www.youtube.com/watch?v=jDM6_TnYIqE
 *
 * To delete a non-leaf/non-root node, remove it and find the right most descendant and put it there.
 * The child of right most descendant will replace the old position of rightmost descendant
 *
 * Since there are fixed number of rotation, it can be considered as O(1)
 *
 * Other variations of tree are
 *  * n-way tree like B-trees
 *  * red-black tree with more relaxed rotation rules and enforces height of one branch isn't greater than 2x other branch
 */
class AVLTree(val value: Int) {
    var root: Node = Node(value)
        private set

    fun add(value: Int) {
        root = root.add(value)
    }

    override fun toString(): String {
        return "AVLTree($root)"
    }

    data class Node(val value: Int) {
        var left: Node? = null
            private set
        var right: Node? = null
            private set
        private var height = 0

        fun add(value: Int): Node {
            var newRoot = this
            if (value <= this.value) {
                left = addToSubTree(left, value)
                if (diff() == 2) {
                    newRoot = if (value <= left!!.value) rotateRight() else rotateLR()
                }
            } else {
                right = addToSubTree(right, value)
                if (diff() == -2) {
                    newRoot = if (value > right!!.value) rotateLeft() else rotateRL()
                }
            }
            newRoot.computeHeight()
            return newRoot
        }

        //       O   this
        //      /
        //     O    newRoot
        //    /
        //   O      grandson
        private fun rotateRight(): Node {
            // [this] is the upper most O
            val newRoot = left!!
            val grandson = newRoot.right
            this.left = grandson
            newRoot.right = this

            computeHeight()
            return newRoot
        }


        private fun rotateLeft(): Node {
            val newRoot = this.right!!
            val grandChild = this.right!!.left
            newRoot.left = this
            this.right = grandChild
            computeHeight()
            return newRoot
        }

        //      O    this
        //     /
        //    O     mid
        //     \
        //      O   newRoot
        private fun rotateLR(): Node {
            val newRoot = this.left!!.right!!
            val mid = this.left!!
            this.left = newRoot.right
            mid.right = newRoot.left

            newRoot.left = mid
            newRoot.right = this

            mid.computeHeight()
            computeHeight()
            return newRoot
        }

        private fun rotateRL(): Node {
            val mid = this.right!!
            val newRoot = mid.left!!

            this.right = newRoot.left
            mid.left = newRoot.right

            newRoot.left = this
            newRoot.right = mid

            newRoot.computeHeight()
            computeHeight()
            return newRoot
        }

        private fun addToSubTree(tree: Node?, value: Int): Node {
            var parent = tree ?: return Node(value)
            parent = parent.add(value)
            return parent
        }

        private fun diff(): Int {
            var leftHeight = 0
            var rightHeight = 0
            if (left != null) {
                leftHeight = 1 + left!!.height
            }
            if (right != null) {
                rightHeight = 1 + right!!.height
            }
            return leftHeight - rightHeight
        }

        private fun computeHeight() {
            var height = -1
            if (left != null) {
                height = max(height, left!!.height)
            }
            if (right != null) {
                height = max(height, right!!.height)
            }
            this.height = height + 1
        }

        override fun toString(): String {
            return "Node(value=$value, left=${left}, right=${right}, h=$height)"
        }
    }

    fun print() {
        println(root)
    }
}

/**
 * @suppress
 */
fun main() {
    val tree = AVLTree(100)
    tree.add(30)
    tree.add(20)
    tree.print()
    tree.add(140)
    tree.add(150)
    tree.print()
    tree.add(50)
    tree.add(60)
    tree.add(110)
    tree.add(105)
    tree.print()

    //          100
    //      30        140
    //   20      50        150
    assertEquals(100, tree.root.value)
    assertEquals(30, tree.root.left!!.value)
    assertEquals(140, tree.root.right!!.value)
    assertEquals(20, tree.root.left!!.left!!.value)
    assertEquals(50, tree.root.left!!.right!!.value)
    assertEquals(150, tree.root.right!!.right!!.value)
}
