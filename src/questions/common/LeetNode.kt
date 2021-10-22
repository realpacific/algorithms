package questions.common

import _utils.SkipDocumentation


@SkipDocumentation

/**
 * Seen typically in LeetCode
 */
class LeetNode(var `val`: Int) {
    var next: LeetNode? = null

    override fun toString(): String {
        return "LeetNode(${`val`})"
    }

    fun toList(): List<Int> {
        val list = mutableListOf<Int>()
        var current: LeetNode? = this
        while (current != null) {
            list.add(current.`val`)
            current = current.next
        }
        return list
    }

    companion object {
        fun from(arr: IntArray): LeetNode {
            val node = LeetNode(arr[0])
            if (arr.size == 1) return node
            var current = node
            for (i in 1..arr.lastIndex) {
                current.next = LeetNode(arr[i])
                current = current.next!!
            }
            return node
        }
    }
}