package linkedlists

class Node(val value: Int, var next: Node? = null) {
    override fun toString(): String {
        return "Node(value=$value, next=$next)"
    }
}

class LinkedList {

    var node: Node? = null
        private set

    fun addLast(newNode: Node) {
        if (node == null) {
            node = newNode
            return
        }
        var current: Node? = node
        while (current!!.next != null) {
            current = current.next
        }
        current.next = newNode
    }

    fun printAll() {
        var current: Node? = node
        while (current != null) {
            println(current)
            current = current.next
        }
    }

    fun hasDuplicates(): Boolean {
        val set = mutableSetOf<Int>()
        var currentNode: Node? = node ?: return false

        while (currentNode != null) {
            if (set.contains(currentNode.value)) {
                return true
            }
            set.add(currentNode.value)

            currentNode = currentNode.next
        }
        return false
    }

    /**
     * Prints from kth index to last
     */
    fun subListFrom(beginIndex: Int): List<Node> {
        val result = mutableListOf<Node>()
        var currentNode: Node? = node ?: return emptyList<Node>()
        var count = 0
        while (currentNode != null) {
            if (beginIndex == count) {
                result.add(currentNode)
            }
            currentNode = currentNode.next
            count++
        }
        return result
    }

    fun deleteAt(index: Int): Node? {
        var currentNode: Node? = node
        var count = 0
        while (currentNode != null) {
            if (index - 1 == count) {
                val deletionNode = currentNode.next
                currentNode.next = deletionNode?.next
                return deletionNode
            }
            currentNode = currentNode.next
            count++
        }
        return null
    }


    fun reverse(): LinkedList {
        var currentNode: Node? = node
        var reversedNode: Node? = null
        while (currentNode != null) {
            val cloned = Node(currentNode.value)
            if (reversedNode == null) {
                reversedNode = cloned
                reversedNode.next = null
            } else {
                cloned.next = reversedNode
                reversedNode = cloned
            }
            currentNode = currentNode.next
        }
        val result = LinkedList()
        if (reversedNode != null) {
            result.addLast(reversedNode)
        }
        return result
    }


    fun nodeAt(index: Int): Node? {
        var currentNode: Node? = node
        var count = 0
        while (currentNode != null) {
            if (index == count) {
                return currentNode
            }
            currentNode = currentNode.next
            count++
        }
        return null
    }
}