package questions

import _utils.UseCommentAsDocumentation
import algorithmdesignmanualbook.print
import utils.assertIterableSame
import utils.shouldBe


private class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(val=${`val`},next=${next?.`val`})"
    }

    fun toList(): List<Int> {
        var current: ListNode? = this
        val result = mutableListOf<Int>()
        while (current != null) {
            result.add(current.`val`)
            current = current.next
        }
        return result
    }

    companion object {
        fun from(vararg arr: Int): ListNode {
            require(arr.isNotEmpty())
            val l = ListNode(arr[0])
            var current: ListNode? = l
            for (i in 1..arr.lastIndex) {
                current!!.next = ListNode(arr[i])
                current = current.next
            }
            return l
        }
    }

    fun print() {
        var current: ListNode? = this
        while (current != null) {
            print(" ${current.`val`} ")
            current = current.next
        }
        println()
    }
}


/**
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 */
@UseCommentAsDocumentation
private fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    return if (l1.`val` <= l2.`val`) {
        l1.next = mergeTwoLists(l1.next, l2)
        l1
    } else {
        l2.next = mergeTwoLists(l1, l2.next)
        l2
    }
}

fun main() {
    mergeTwoLists(
        ListNode.from(1, 2, 4), ListNode.from(1, 3, 4)
    )!!.toList() shouldBe ListNode.from(1, 1, 2, 3, 4, 4).toList()

    assertIterableSame(
        expected = ListNode.from(1, 1, 1, 1, 2, 2).toList(),
        actual = mergeTwoLists(ListNode.from(1, 1, 2), ListNode.from(1, 1, 2))!!.toList()
    )

    assertIterableSame(
        expected = ListNode.from(1, 2, 2).toList(),
        actual = mergeTwoLists(ListNode.from(1, 2), ListNode.from(2))!!.toList()
    )
}