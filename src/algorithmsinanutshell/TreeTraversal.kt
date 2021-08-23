package algorithmsinanutshell

import algorithmdesignmanualbook.datastructures.Node
import algorithmdesignmanualbook.datastructures.createBST
import java.util.*

private fun traverseDepthFirst(node: Node?) {
    if (node != null) {
        traverseDepthFirst(node.left)
        println(node.value)
        traverseDepthFirst(node.right)
    }
}


private fun traverseBreadthFirst(node: Node?) {
    val queue = LinkedList<Node?>()
    queue.addLast(node)
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (current != null) {
            println(current.value)
            queue.addLast(current.left)
            queue.addLast(current.right)
        }
    }
}

/**
 * @suppress
 */
fun main() {
    //           10
    //     6          15
    //  4    7     12    19
    val tree = createBST()
    tree.print()
    println("Depth first")
    traverseDepthFirst(tree.getNode())

    println("Breadth first")
    traverseBreadthFirst(tree.getNode())
}