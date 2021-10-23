package questions

import _utils.UseCommentAsDocumentation
import algorithmdesignmanualbook.print
import questions.common.LeetNode
import utils.assertIterableSame
import utils.shouldBe

/**
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 * [Source](https://leetcode.com/problems/merge-two-sorted-lists/)
 */
@UseCommentAsDocumentation
private fun mergeTwoLists(l1: LeetNode?, l2: LeetNode?): LeetNode? {
    return returnLowerNode(l1, l2)
}

private fun returnLowerNode(l1: LeetNode?, l2: LeetNode?): LeetNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    return if (l1.`val` <= l2.`val`) {
        l1.next = returnLowerNode(l1.next, l2)
        l1
    } else {
        l2.next = returnLowerNode(l1, l2.next)
        l2
    }
}

fun main() {

    assertIterableSame(
        expected = LeetNode.from(1, 1, 1, 1, 2, 2).toList(),
        actual = mergeTwoLists(
            LeetNode.from(1, 1, 2),
            LeetNode.from(1, 1, 2)
        )!!.toList()
    )

    mergeTwoLists(
        l1 = LeetNode.from(1, 2, 4),
        l2 = LeetNode.from(1, 3, 4)
    )!!.toList() shouldBe LeetNode.from(1, 1, 2, 3, 4, 4).toList()

    assertIterableSame(
        expected = LeetNode.from(1, 1, 2, 3, 4, 4).toList(),
        actual = mergeTwoLists(LeetNode.from(1, 2, 4), LeetNode.from(1, 3, 4))!!.toList()
    )

    assertIterableSame(
        expected = LeetNode.from(1, 2, 2).toList(),
        actual = mergeTwoLists(LeetNode.from(1, 2), LeetNode.from(2))!!.toList()
    )
}