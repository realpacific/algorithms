package questions

import _utils.UseCommentAsDocumentation
import questions.common.TreeNode
import utils.shouldBe

/**
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y,
 * return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * [Source](https://leetcode.com/problems/cousins-in-binary-tree/)
 */
@UseCommentAsDocumentation
private fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
    val yInfo = traverse(x = y, current = root, parent = null, depth = 0)!!
    val xInfo = traverse(x = x, current = root, parent = null, depth = 0, maxDepth = yInfo.first)!!
    return xInfo.first == yInfo.first // same depth
            && xInfo.second != null && xInfo.second != yInfo.second // different parent
}


private fun traverse(
    x: Int,
    current: TreeNode?,
    parent: TreeNode?,
    depth: Int,
    maxDepth: Int? = null
): Pair<Int, TreeNode?>? {
    if (current == null) {
        return null // NOT FOUND
    }
    if (current.`val` == x) {
        return Pair(depth, parent)
    }
    // It shouldn't exceed any farther than maxDepth
    if (maxDepth != null && depth > maxDepth) {
        return null
    }

    val leftTraverse = traverse(x, current = current.left, parent = current, depth = depth + 1)
    if (leftTraverse != null) {
        return leftTraverse
    }
    val rightTraverse =
        traverse(x, current = current.right, parent = current, depth = depth + 1)

    if (rightTraverse != null) {
        return rightTraverse
    }
    return null
}

fun main() {
    isCousins(root = TreeNode.from(arrayOf(1, 2, 3, null, 4, null, 5)), x = 5, y = 4) shouldBe true
    isCousins(root = TreeNode.from(1, 2, 3, 4), x = 4, y = 3) shouldBe false
    isCousins(root = TreeNode.from(arrayOf(1, 2, 3, null, 4)), x = 2, y = 3) shouldBe false
    isCousins(root = TreeNode.from(arrayOf(1, 2, 3, null, null, 4, 5)), x = 4, y = 5) shouldBe false

    isCousins(
        root = TreeNode.from(
            arrayOf(
                1,
                2,
                4,
                3,
                19,
                10,
                5,
                15,
                8,
                null,
                null,
                13,
                14,
                null,
                6,
                null,
                17,
                null,
                null,
                null,
                null,
                18,
                null,
                7,
                11,
                null,
                null,
                null,
                null,
                null,
                9,
                16,
                12,
                null,
                null,
                20
            )
        ),
        x = 11,
        y = 17
    ) shouldBe true
}