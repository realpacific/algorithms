package algorithmdesignmanualbook.datastructures

data class Node(val value: Int) {
    var parent: Node? = null
    var left: Node? = null
    var right: Node? = null

    companion object {
        fun create(value: Int) = Node(value)
    }

    override fun toString(): String {
        return "Node(value:$value, left:${left}, right=${right}, parent:${parent?.value})"
    }

    fun toBST() = BinarySearchTree(this)

    fun isLeafNode() = left == null && right == null
}