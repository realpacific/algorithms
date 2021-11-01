package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * [Source](https://leetcode.com/problems/diameter-of-binary-tree/) – [solution](https://leetcode.com/problems/diameter-of-binary-tree/discuss/101132/Java-Solution-MaxDepth)
 */
@UseCommentAsDocumentation
private object DiameterOfBinaryTree {
    private var currentMax = 0

    fun diameterOfBinaryTree(root: TreeNode?): Int {
        maxDepth(root)
        return currentMax
    }


    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0
        val left = maxDepth(root.left)
        println("left ${root.left?.`val`} height:$left")
        val right = maxDepth(root.right)
        println("right ${root.right?.`val`} height:$right")
        currentMax = maxOf(currentMax, left + right)
        return 1 + maxOf(left, right)
    }

}

fun main() {
    //       1
    //    2      3
    //  4   5
    DiameterOfBinaryTree.diameterOfBinaryTree(TreeNode.from(1, 2, 3, 4, 5)) shouldBe 3
}