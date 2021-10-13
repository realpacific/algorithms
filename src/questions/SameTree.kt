package questions

import questions.common.TreeNode
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 *
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * [Source](https://leetcode.com/problems/same-tree/)
 */
private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    return TreeNode.isSameAs(p, q)
}

fun main() {
    assertFalse(isSameTree(p = TreeNode.from(0), q = TreeNode.from(1)))
    assertTrue(isSameTree(p = TreeNode.from(1, 2, 3), q = TreeNode.from(1, 2, 3)))
    assertFalse(isSameTree(p = TreeNode.from(1, 2), q = TreeNode.from(arrayOf(1, null, 2))))
    assertFalse(isSameTree(p = TreeNode.from(1, 2, 1), q = TreeNode.from(arrayOf(1, 1, 2))))
}