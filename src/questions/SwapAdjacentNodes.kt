package questions

import _utils.UseCommentAsDocumentation
import questions.common.LeetNode
import questions.common.LeetNode.Companion.from
import utils.shouldBe

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * 1 -> 2 -> 3 -> 4 -----> 2 -> 1 -> 4 -> 3
 */
@UseCommentAsDocumentation
private fun swapAdjacent(node: LeetNode): LeetNode {
    if (node.next == null) {
        return node
    }
    var copy = node
    var prev: LeetNode? = null // in 1st node, prev=null
    var current: LeetNode? = node
    var next: LeetNode? = current?.next

    while (current != null) {
        if (next == null && prev != null) {        // handle the last node of odd case [1,2,3,4,5]
            prev.next = current
            break
        }

        current.next = next?.next
        next?.next = current
        prev?.next = next
        if (prev == null) { // handle the first case
            copy = next!!
        }

        // move twice forward (1,2),3,4 -> (2,1),3,4 -> 2,1,(3,4) -> 2,1,4,3
        prev = next?.next
        next = current.next?.next
        current = prev?.next
    }

    return copy
}

fun main() {
    swapAdjacent(from(intArrayOf(1, 2, 3, 4, 5))).toList() shouldBe from(intArrayOf(2, 1, 4, 3, 5)).toList()
    swapAdjacent(from(intArrayOf(1))).toList() shouldBe from(intArrayOf(1)).toList()
    swapAdjacent(from(intArrayOf(1, 2, 3, 4))).toList() shouldBe from(intArrayOf(2, 1, 4, 3)).toList()
    swapAdjacent(from(intArrayOf(1, 2, 3, 4, 5, 6)))
        .toList() shouldBe from(intArrayOf(2, 1, 4, 3, 6, 5)).toList()
}