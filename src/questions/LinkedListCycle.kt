package questions

import _utils.UseCommentAsDocumentation
import questions.common.LeetNode
import utils.shouldBe

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the next pointer.
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
 * Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * [Source](https://leetcode.com/problems/linked-list-cycle-kt/)
 */
@UseCommentAsDocumentation
private fun hasCycle(head: LeetNode?): Boolean {
    if (head == null) return false
    var slowPointer = head
    var fastPointer = head.next

    while (slowPointer != null || fastPointer != null) {
        if (slowPointer == fastPointer) {
            return true
        }
        slowPointer = slowPointer?.next
        fastPointer = fastPointer?.next?.next
    }
    return false
}

private fun LeetNode.getFirst(value: Int): LeetNode? {
    var current: LeetNode? = this
    while (current != null) {
        if (current.`val` == value) {
            return current
        }
        current = current.next
    }
    return null
}

fun main() {
    hasCycle(head = LeetNode.from(3, 2, 0, -4)
        .also {
            it.getFirst(-4)!!.next = it.getFirst(2) // add cycle
        }) shouldBe true

    hasCycle(head = LeetNode.from(1, 2)
        .also {
            it.getFirst(2)!!.next = it.getFirst(1)
        }) shouldBe true

    hasCycle(head = LeetNode.from(3, 2, 0, -4)) shouldBe false

}