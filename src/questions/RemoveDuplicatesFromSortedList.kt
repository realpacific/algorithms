package questions

import _utils.UseCommentAsDocumentation
import utils.assertIterableSame



/**
 * Given the head of a sorted linked list, delete all duplicates such
 * that each element appears only once. Return the linked list sorted as well.
 *
 * [source](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)
 */
@UseCommentAsDocumentation
private fun deleteDuplicates(head: LeetNode?): LeetNode? {
    if (head == null) return head

    var current: LeetNode? = head.next
    var prev: LeetNode? = head
    while (current != null) {
        if (prev!!.`val` == current.`val`) {
            prev.next = current.next
            current = prev.next
        } else {
            prev = current
            current = prev.next
        }
    }
    return head
}

fun main() {
    run {
        val node = LeetNode.from(intArrayOf(1, 1, 2, 2))
        assertIterableSame(listOf(1, 2), deleteDuplicates(node)!!.toList())
    }
    run {
        val node = LeetNode.from(intArrayOf(1, 1, 1))
        assertIterableSame(listOf(1), deleteDuplicates(node)!!.toList())
    }
    run {
        val node = LeetNode.from(intArrayOf(1, 1, 2, 3, 3))
        assertIterableSame(listOf(1, 2, 3), deleteDuplicates(node)!!.toList())
    }
    run {
        val node = LeetNode.from(intArrayOf(1, 1, 2))
        assertIterableSame(listOf(1, 2), deleteDuplicates(node)!!.toList())
    }
}
