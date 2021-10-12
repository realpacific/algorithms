package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Given the root of a binary tree and an integer targetSum,
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 */
@UseCommentAsDocumentation
fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null) {
        // This one to satisfy one of the tests in LeetCode
        return false
        // return targetSum == 0
    }

    return addToSum(root = root, prevSum = 0, targetSum = targetSum)
}

fun addToSum(root: TreeNode?, prevSum: Int, targetSum: Int): Boolean {
    if (root == null) return false

    // Total sum including current node
    val currentSum = prevSum + root.`val`

    // Returns true ONLY WHEN root-to-leaf has sum of target sum
    if (currentSum == targetSum && root.isLeaf()) return true

    if (addToSum(root.left, currentSum, targetSum)) {
        // Found
        return true
    }
    if (addToSum(root.right, currentSum, targetSum)) {
        // Found
        return true
    }
    return false
}


fun main() {
    assertFalse(
        hasPathSum(
            root = TreeNode.from(arrayOf(1, 2, 3)),
            targetSum = 5
        )
    )
    assertTrue(
        hasPathSum(
            root = TreeNode.from(arrayOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1)),
            targetSum = 22
        )
    )
    assertFalse(
        hasPathSum(
            root = TreeNode.from(arrayOf(1, 2)),
            targetSum = 0
        )
    )

    assertFalse(hasPathSum(root = TreeNode.from(arrayOf()), targetSum = 0))
}