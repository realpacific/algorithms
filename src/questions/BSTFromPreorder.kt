package questions

import questions.common.TreeNode
import kotlin.test.assertTrue

/**
 * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
 * construct the tree and return its root.
 * A preorder traversal of a binary tree displays the value of the node first,
 * then traverses `Node.left`, then traverses `Node.right`.
 *
 * [Source](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/)
 */
private fun bstFromPreorder(preorder: IntArray): TreeNode? {
    val node = TreeNode(preorder[0])
    for (i in 1..preorder.lastIndex) {
        addToBST(node, preorder[i])
    }
    return node
}

private fun addToBST(root: TreeNode, value: Int) {
    if (value < root.`val`) {
        if (root.left == null) {
            root.left = TreeNode(value)
        } else {
            addToBST(root.left!!, value)
        }
    } else if (value > root.`val`) {
        if (root.right == null) {
            root.right = TreeNode(value)
        } else {
            addToBST(root.right!!, value)
        }
    }
}

fun main() {
    run {
        assertTrue {
            // https://assets.leetcode.com/uploads/2019/03/06/1266.png
            val actual = bstFromPreorder(intArrayOf(8, 5, 1, 7, 10, 12))!!
            val expected = TreeNode.from(arrayOf(8, 5, 10, 1, 7, null, 12))!!
            actual.isSameAs(expected)
        }
    }
}