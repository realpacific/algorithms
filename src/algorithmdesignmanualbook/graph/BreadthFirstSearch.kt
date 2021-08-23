package algorithmdesignmanualbook.graph

import algorithmdesignmanualbook.withPrint
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private fun breadthFirstTraversal(graph: Graph) {
    val start = graph.getRandomVertex()
    val queue = LinkedList<Graph.Vertex>()

    queue.addLast(start)

    while (queue.isNotEmpty()) {
        val first = queue.removeFirst()
        if (first.state == Graph.State.PROCESSED) {
            continue
        }
        first.edges.forEach {
            if (it.endVertex.state == Graph.State.UNDISCOVERED) {
                queue.addLast(it.endVertex)
                it.endVertex.state = Graph.State.DISCOVERED
            }
        }
        println(first.value)
        first.state = Graph.State.PROCESSED
    }
}

/**
 * @suppress
 */
fun main() {
    withPrint("Graph 1") {
        val graph1 = Graph.getDefaultDirected()
        breadthFirstTraversal(graph1)
    }

    withPrint("Graph 2") {
        val graph2 = Graph.getDefaultUnDirected()
        breadthFirstTraversal(graph2)
    }
}