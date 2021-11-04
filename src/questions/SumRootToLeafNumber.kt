package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe

/**
 *
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path `1 -> 2 -> 3` represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 *
 * [Source](https://leetcode.com/problems/sum-root-to-leaf-numbers/)
 */
@UseCommentAsDocumentation
private fun sumNumbers(root: TreeNode?): Int {
    if (root == null) return 0
    val pathValue = mutableListOf<Int>()
    depthFirstTraversal(root, 0, pathValue) // depth first traversal
    return pathValue.sum()
}

private fun depthFirstTraversal(root: TreeNode?, sum: Int, result: MutableList<Int>) {
    if (root == null) return
    val newSum = sum * 10 + root.`val` // increase the 10th place.  491 = 4*100 + 9*10 + 1
    if (root.left == null && root.right == null) { // if leaf node
        result.add(newSum)
    }
    depthFirstTraversal(root.left, newSum, result)
    depthFirstTraversal(root.right, newSum, result)
}

fun main() {
    //       4
    //    9     0   => 491+495+40
    //  5    1
    sumNumbers(TreeNode.from(4, 9, 0, 5, 1)) shouldBe 1026
    sumNumbers(TreeNode.from(1, 2, 3)) shouldBe 25
}