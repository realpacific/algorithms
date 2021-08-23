package linkedlists

/**
 * @suppress
 */
fun main() {
    val list1 = LinkedList()
    // 617
    list1.addLast(Node(7))
    list1.addLast(Node(1))
    list1.addLast(Node(6))

    // 295
    val list2 = with(LinkedList()) {
        addLast(Node(5))
        addLast(Node(9))
        addLast(Node(2))
        this
    }
    val result = LinkedList()
    addNode(list1.node, list2.node, carry = 0, result = result)
    result.printAll()
}

fun addNode(node1: Node?, node2: Node?, carry: Int, result: LinkedList) {
    if (node1 == null && node2 == null && carry == 0) {
        return
    }
    var value = carry
    if (node1 != null) {
        value += node1.value
    }
    if (node2 != null) {
        value += node2.value
    }
    val newNodeValue = value % 10
    val newCarry = value / 10
    result.addLast(Node(newNodeValue))
    addNode(node1?.next, node2?.next, newCarry, result)

}