package algorithmdesignmanualbook

import kotlin.test.assertTrue

/**
 * Given a singly-linked list, find its middle node.
 *
 * Solution: Fast pointer-slow pointer approach
 * Use two pointer, one traverses one step ahead and another by 2 steps.
 * When the fast pointer reaches the end, the slow pointer is at middle.
 */
private fun LinkedListNode.getMiddleNode(): LinkedListNode {
    var slowPointer: LinkedListNode = this
    var fastPointer: LinkedListNode? = this

    while (fastPointer?.next != null) {
        slowPointer = slowPointer.next!!
        fastPointer = fastPointer.next?.next
    }
    return slowPointer
}


fun main() {
    val list = LinkedListNode(1)
    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)
    list.add(6)
    list.add(7)
    list.add(8)
    list.add(9)
    list.getMiddleNode()
    assertTrue {
        list.getMiddleNode().value == 5
    }
}