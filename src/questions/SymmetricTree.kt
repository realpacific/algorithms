package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe
import java.util.*

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * [Source](https://leetcode.com/problems/symmetric-tree/)
 */
@UseCommentAsDocumentation
private fun isSymmetric(root: TreeNode?): Boolean {
    if (root == null) return true
    val queue: Queue<TreeNodeWithDepth> = LinkedList()
    val depthToChildrenMap = TreeMap<Int, MutableList<TreeNode?>>()
    queue.add(TreeNodeWithDepth(root, depth = 0))
    traverse(queue, depthToChildrenMap)

    // start the check from last as intermediate indices have already been checked
    val sibilings = depthToChildrenMap.values.reversed()
    sibilings.forEach {
        // Check the symmetry of the list
        if (!isPalindrome(it)) {
            return false
        }
    }
    return true
}

private fun traverse(
    queue: Queue<TreeNodeWithDepth>,
    depthToChildren: TreeMap<Int, MutableList<TreeNode?>>
) {
    if (queue.isEmpty()) {
        return
    }
    val node = queue.remove()
    val depth = node.depth


    if (!depthToChildren.containsKey(depth + 1) && depthToChildren.containsKey(depth)) {
        val prevSiblings = depthToChildren[depth]!!
        // Check if all the element in the previous depth was null i.e. all of them were leaf elements
        // i.e. we are past the last depth
        if (prevSiblings.all { it == null }) {
            return
        } else if (!isPalindrome(prevSiblings)) { // check if previously added siblings are symmetric
            return // if it wasn't, break early
        }
    }

    // use the parent depth to get the current depth
    // add elements at current depth to the map
    depthToChildren[depth + 1] = depthToChildren.getOrDefault(depth + 1, mutableListOf()).apply {
        // count the nulls as well
        this.add(node?.node?.left)
        this.add(node?.node?.right)
    }
    // dont count the  null as it can't be expanded further
    // add it to queue as well
    if (node?.node?.left != null) {
        queue.add(TreeNodeWithDepth(node.node.left, depth + 1))
    }
    if (node?.node?.right != null) {
        queue.add(TreeNodeWithDepth(node?.node?.right, depth + 1))
    }

    traverse(queue, depthToChildren)
}

private fun isPalindrome(list: List<TreeNode?>): Boolean {
    for (i in 0..list.lastIndex) {
        if (list[i]?.`val` != list[list.lastIndex - i]?.`val`) {
            return false
        }
    }
    return true
}

private data class TreeNodeWithDepth(val node: TreeNode?, val depth: Int)

fun main() {
    isSymmetric(
        TreeNode.from(
            arrayOf(
                9,
                14,
                14,
                74,
                null,
                null,
                74,
                null,
                12,
                12,
                null,
                63,
                null,
                null,
                63,
                -8,
                null,
                null,
                -8,
                -53,
                null,
                null,
                -53,
                null,
                -96,
                -96,
                null,
                -65,
                null,
                null,
                -65,
                98,
                null,
                null,
                98,
                50,
                null,
                null,
                50,
                null,
                91,
                91,
                null,
                41,
                -30,
                -30,
                41,
                null,
                86,
                null,
                -36,
                -36,
                null,
                86,
                null,
                -78,
                null,
                9,
                null,
                null,
                9,
                null,
                -78,
                47,
                null,
                48,
                -79,
                -79,
                48,
                null,
                47,
                -100,
                -86,
                null,
                47,
                null,
                67,
                67,
                null,
                47,
                null,
                -86,
                -100,
                -28,
                11,
                null,
                56,
                null,
                30,
                null,
                64,
                64,
                null,
                30,
                null,
                56,
                null,
                11,
                -28,
                43,
                54,
                null,
                -50,
                44,
                -58,
                63,
                null,
                null,
                -43,
                -43,
                null,
                null,
                63,
                -58,
                44,
                -50,
                null,
                54,
                43
            )
        )
    ) shouldBe true
    isSymmetric(TreeNode.from(arrayOf(2, 3, 3, 4, 5, 5, 4, null, null, 8, 9, null, null, 9, 8))) shouldBe false
    isSymmetric(TreeNode.from(arrayOf(1, 2, 2, null, 3, null, 3))) shouldBe false
    isSymmetric(TreeNode.from(1, 2, 2, 3, 4, 4, 3)) shouldBe true
}