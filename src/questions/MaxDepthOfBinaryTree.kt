package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import kotlin.math.max
import kotlin.test.assertEquals

/**
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * [Source](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
 */
@UseCommentAsDocumentation
private fun maxDepth(root: TreeNode?): Int {
    if (root == null) return 0
    return maxDepth(root, 0)
}

private fun maxDepth(root: TreeNode?, depth: Int): Int {
    if (root == null) return depth
    return max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1))
}


fun main() {
    assertEquals(3, maxDepth(TreeNode.from(arrayOf(3, 9, 20, null, null, 15, 7))))
    assertEquals(2, maxDepth(TreeNode.from(arrayOf(1, null, 2))))
    assertEquals(0, maxDepth(TreeNode.from(arrayOf())))
    assertEquals(1, maxDepth(TreeNode.from(arrayOf(0))))
}