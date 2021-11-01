package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe

/**
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * [Source](https://leetcode.com/problems/sum-of-left-leaves/)
 */
@UseCommentAsDocumentation
private fun sumOfLeftLeaves(root: TreeNode?): Int {
    return traverse(root, 0, false)
}

private fun traverse(root: TreeNode?, sum: Int, isLeft: Boolean): Int {
    if (root == null) {
        return sum
    }
    val newSum = sum + if (isLeft && root.isLeaf()) root.`val` else 0 // is leaf check, add its value to sum
    return traverse(root.left, newSum, true) + traverse(root.right, sum, false)
}


fun main() {
    // There are two left leaves in the binary tree, with values 9 and 15 respectively.
    sumOfLeftLeaves(TreeNode.from(arrayOf(3, 9, 20, null, null, 15, 7))) shouldBe 24

    //      1
    //   2     3
    // 4   5
    sumOfLeftLeaves(TreeNode.from(arrayOf(1, 2, 3, 4, 5))) shouldBe 4
}