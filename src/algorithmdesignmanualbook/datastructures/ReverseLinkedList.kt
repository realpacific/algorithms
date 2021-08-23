package algorithmdesignmanualbook.datastructures


data class LinkedListNode(val value: Int) {
    var next: LinkedListNode? = null
        private set

    fun add(value: Int) {
        var current: LinkedListNode? = this
        while (current?.next != null) {
            current = current.next
        }
        current!!.next = LinkedListNode(value)
    }

    fun print() {
        val sb = StringBuilder()
        var current: LinkedListNode? = this
        while (current != null) {
            sb.append("${current.value}").append(" -> ")
            current = current.next
        }
        println(sb.removeSuffix(" -> ").toString())
    }

    /**
     * [link here](https://leetcode.com/problems/reverse-linked-list)
     */
    fun reverse(): LinkedListNode? {
        var prev: LinkedListNode? = null
        var current: LinkedListNode? = this
        if (current?.next == null) {
            return this
        }
        while (current != null) {
            val temp = current.next
            current.next = prev
            prev = current
            current = temp
        }
        return prev
    }
}

/**
 * @suppress
 */
fun main() {
    val list = LinkedListNode(1)
    list.reverse()?.print()

    list.add(2)
    list.add(3)
    list.add(4)
    list.add(5)

    list.print()

    list.reverse()?.print()
}