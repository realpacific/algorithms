package algorithmsinanutshell

import java.util.*
import kotlin.test.assertEquals

private fun depthFirstTraversal(graph: Graph) {

    fun traverseNodeDepthFirst(vertex: Graph.Vertex) {
        val stack = Stack<Graph.Vertex>()
        val startNode = vertex

        stack.push(startNode)
        while (stack.isNotEmpty()) {
            val currentVertex = stack.pop()
            if (currentVertex.state != Graph.State.Undiscovered) {
                continue
            }
            currentVertex.state = Graph.State.Processing
            currentVertex.edges.forEach { edge ->
                stack.push(edge.startVertex)
                stack.push(edge.endVertex)
            }
            currentVertex.state = Graph.State.Discovered
            println("Visited $currentVertex")
        }
    }


    val startNode = graph.nodes.firstOrNull() ?: return
    traverseNodeDepthFirst(startNode)
    // Process dis-connected graph
    graph.getUnvisited().forEach(::traverseNodeDepthFirst)
}


private fun breadthFirstTraversal(graph: Graph) {
    fun traverseBreadthFirst(vertex: Graph.Vertex) {
        val queue = LinkedList<Graph.Vertex>()
        val startNode = vertex

        queue.addLast(startNode)
        while (queue.isNotEmpty()) {
            val currentVertex = queue.removeFirst()
            if (currentVertex.state != Graph.State.Undiscovered) {
                continue
            }
            currentVertex.state = Graph.State.Processing
            currentVertex.edges.forEach { edge ->
                queue.addLast(edge.startVertex)
                queue.addLast(edge.endVertex)
            }
            currentVertex.state = Graph.State.Discovered
            println("Visited $currentVertex")
        }
    }

    val startNode = graph.nodes.firstOrNull() ?: return
    traverseBreadthFirst(startNode)
    // Process dis-connected graph
    graph.getUnvisited().forEach(::traverseBreadthFirst)
}


fun main() {
    run {
        val graph = createGraph()
        depthFirstTraversal(graph)
        graph.getAllNodes().forEach {
            assertEquals(it.state, Graph.State.Discovered)
        }
    }

    run {
        println("Breadth First Traversal")
        val graph = createGraph()
        breadthFirstTraversal(graph)
        graph.getAllNodes().forEach {
            assertEquals(it.state, Graph.State.Discovered)
        }
    }

}

private fun createGraph(): Graph {
    val graph = Graph(Relation.UNWEIGHTED_DIRECTED)
    val v1 = graph.add(1)
    val v6 = graph.add(6)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)
    val v5 = graph.add(5)

    val v7 = graph.add(7)
    val v8 = graph.add(8)

    v1.connectWith(v2)
    v1.connectWith(v3)
    v3.connectWith(v2)
    v1.connectWith(v4)
    v3.connectWith(v6)
    v6.connectWith(v5)
    v7.connectWith(v8)
    return graph
}