package questions


import _utils.UseCommentAsDocumentation
import questions.common.LeetNode
import utils.assertIterableSame

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * [Source](https://leetcode.com/problems/sort-list/)
 */
@UseCommentAsDocumentation
private fun sortList(head: LeetNode?): LeetNode? {
    if (head == null) return null
    if (head.next == null) return head // just one node

    // finding the mid of linked list using fast-slow pointer
    // fast moves by 2
    // slow moves by 1
    var slowPointer: LeetNode? = head
    var fastPointer: LeetNode? = slowPointer!!.next?.next

    if (fastPointer == null) { // only two nodes, this can be sorted by comparing with each other
        return if (slowPointer.`val` > slowPointer.next!!.`val`) swap(head, head.next!!)
        else head
    }

    // find the mid of linked list
    while (fastPointer != null) {
        slowPointer = slowPointer!!.next // when fastPointer=null, slowPointer=mid point
        fastPointer = fastPointer.next?.next
    }

    val anotherHalf = slowPointer!!.next // keep another half of linked list
    slowPointer.next = null // detach the linked list

    val firstHalf = sortList(head) // sort the first half (head ---> ... ---> slowPointer )
    val secondHalf = sortList(anotherHalf) // sort the second half (slowPointer ---> ... ---> null)

    if (firstHalf == null) return secondHalf
    else if (secondHalf == null) return firstHalf

    var newHead: LeetNode? = null
    if (firstHalf.`val` <= secondHalf.`val`) { // lowest element is in the first half
        newHead = firstHalf // make it the head
        sort(
            newHead,
            firstHalf.next,
            secondHalf
        ) // since head starts with first node of firstHalf, so compare with firstHalf.next
    } else { // lowest in the second half
        newHead = secondHalf
        sort(newHead, firstHalf, secondHalf.next)
    }
    return newHead
}

/**
 * @param head [LeetNode] where the sorted nodes are to be attached
 * @param node1 [LeetNode] to be compared
 * @param node2 [LeetNode] to be compared
 */
private fun sort(head: LeetNode, node1: LeetNode?, node2: LeetNode?) {
    if (node1 == null && node2 == null) return
    if (node1 == null) { // node1 is done
        head.next = node2
        return
    }
    if (node2 == null) { // node2 is done
        head.next = node1
        return
    }
    if (node1.`val` <= node2.`val`) {
        head.next = node1 // attach the lowest to the [head]
        sort(head.next!!, node1.next, node2) // compare next
    } else if (node1.`val` > node2.`val`) {
        head.next = node2  // attach the lowest to the [head]
        sort(head.next!!, node1, node2.next) // compare next
    }
}

private fun swap(prev: LeetNode, next: LeetNode): LeetNode {
    val temp = next.next
    next.next = prev
    prev.next = temp
    return next
}

fun main() {

    run {
        val input = intArrayOf(-1, 5, 4, 2, 0, 9, 3, 4)
        assertIterableSame(
            expected = input.sorted(),
            sortList(LeetNode.from(input))!!.toList(),
        )
    }

    assertIterableSame(
        LeetNode.from(-1, 0, 3, 4, 5).toList(),
        sortList(LeetNode.from(-1, 5, 3, 4, 0))!!.toList(),
    )

    assertIterableSame(
        LeetNode.from(1, 2, 3, 4).toList(),
        sortList(LeetNode.from(4, 2, 1, 3))!!.toList(),
    )
    assertIterableSame(
        LeetNode.from(1, 2).toList(),
        sortList(LeetNode.from(1, 2))!!.toList(),
    )
    assertIterableSame(
        LeetNode.from(1).toList(),
        sortList(LeetNode.from(1))!!.toList(),
    )
    assertIterableSame(
        LeetNode.from(2, 100).toList(),
        sortList(LeetNode.from(100, 2))!!.toList(),
    )

}