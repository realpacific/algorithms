package questions

import questions.common.LeetNode
import utils.assertIterableSame
import java.util.*

/**
 * You are given the head of a singly linked-list. The list can be represented as `L0 → L1 → … → Ln - 1 → Ln`
 *
 * Reorder the list to be on the following form: `L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …`
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 * [Source](https://leetcode.com/problems/reorder-list)
 */
private fun reorderList(head: LeetNode?) {
    if (head == null) return
    if (head.next == null) return

    val stack = LinkedList<LeetNode>()
    var current: LeetNode? = head

    while (current != null) {
        stack.addLast(current) // add the nodes to a stack
        current = current.next
    }

    val lengthToSwapsRequired = mapOf(
        1 to 0, 2 to 0, // if there are 1 or 2 items, no swaps required
        3 to 1, 4 to 1, // if there are 3 or 4 items, just 1 swap required
        5 to 2, 6 to 2, // if there are 5 or 6 items, just 2 swap required
        7 to 3, 8 to 3,
        9 to 4,         // case for 9 or 10 items (10 is not possible, but if totalLength=0,
        10 to 4,        // ...it can be safely considered that the items is in multiple of 10
    )

    fun numberOfSwapsRequired(totalLength: Int): Int {
        if (totalLength <= 10) {
            return lengthToSwapsRequired[totalLength]!!
        }

        // From my observation, the behavior of totalLength to swaps required is:
        //
        // Length | swaps
        // * 1   ->  0
        // * 2   ->  0
        // * 9   ->  4
        // * 10  ->  4
        // * 11  ->  5 ( lengthToSwapsRequired[10] + lengthToSwapsRequired[11 % 10] = 4  ) + 1
        // * 12  ->  5 ( lengthToSwapsRequired[10] + lengthToSwapsRequired[12 % 10] = 4  ) + 1
        // * 13  ->  6 ( lengthToSwapsRequired[10] + lengthToSwapsRequired[13 % 10] = 5  ) + 1
        // * 20  ->  9
        // * 21  ->  10

        // extra 1 for every 10
        // each 10s adds a 4
        // each 10s adds a 1
        // subtract 10 from it and do it again
        return 4 + 1 + numberOfSwapsRequired(totalLength - 10)
    }

    val totalSwapsRequired = numberOfSwapsRequired(stack.size)
    var previous: LeetNode? = head
    current = head.next

    var swapsDone = 0
    while (current != null) {
        val toBeInserted =
            stack.removeLastOrNull() ?: break // pop the stack to put it in the middle of prev and current
        swapsDone++

        // place [toBeInserted] in the middle
        previous?.next = toBeInserted
        toBeInserted.next = current

        // move to next insertion point
        previous = toBeInserted.next
        current = previous?.next

        if (swapsDone > totalSwapsRequired) {
            // no more required
            current?.next = null                              // nullify the end
            break
        }
    }
}


fun main() {
    run {
        val input = LeetNode.from(IntRange(1, 49).toList().toIntArray())
        reorderList(input)
        assertIterableSame(
            actual = input.toList(),
            expected = LeetNode.from(
                1,
                49,
                2,
                48,
                3,
                47,
                4,
                46,
                5,
                45,
                6,
                44,
                7,
                43,
                8,
                42,
                9,
                41,
                10,
                40,
                11,
                39,
                12,
                38,
                13,
                37,
                14,
                36,
                15,
                35,
                16,
                34,
                17,
                33,
                18,
                32,
                19,
                31,
                20,
                30,
                21,
                29,
                22,
                28,
                23,
                27,
                24,
                26,
                25
            ).toList()
        )
    }

    run {
        val input = LeetNode.from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        reorderList(input)
        assertIterableSame(
            actual = input.toList(),
            expected = LeetNode.from(1, 11, 2, 10, 3, 9, 4, 8, 5, 7, 6).toList()
        )
    }
    run {
        val input = LeetNode.from(1, 2, 3, 4)
        reorderList(input)
        assertIterableSame(
            actual = input.toList(),
            expected = LeetNode.from(1, 4, 2, 3).toList()
        )
    }
    run {
        val input = LeetNode.from(1, 2, 3, 4, 5)
        reorderList(input)
        assertIterableSame(
            actual = input.toList(),
            expected = LeetNode.from(1, 5, 2, 4, 3).toList()
        )
    }
}