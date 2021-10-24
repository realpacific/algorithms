package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe
import java.util.*

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * Every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * [Source](https://leetcode.com/problems/count-complete-tree-nodes/)
 */
@UseCommentAsDocumentation
private fun countNodes(root: TreeNode?): Int {
    if (root == null) return 0
    return breadthFirstTraversal(root)
}

private fun breadthFirstTraversal(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    val queue: Queue<TreeNode> = LinkedList<TreeNode>()
    queue.add(root)
    var count = 1
    while (queue.isNotEmpty()) {
        val first = queue.remove()
        if (first.left != null) {
            queue.add(first.left)
            count++
        }
        if (first.right != null) {
            queue.add(first.right)
            count++
        }
    }
    return count
}

fun main() {
    countNodes(TreeNode.from(1, 2, 3, 4, 5, 6)) shouldBe 6
    countNodes(TreeNode.from(1)) shouldBe 1
}