package algorithmsinanutshell

import algorithmdesignmanualbook.padLeft
import java.util.*
import kotlin.test.assertEquals


class DijkstraAlgorithm(val graph: Graph, private val startVertex: Vertex, private val endVertex: Vertex) {
    /**
     * List containing path:length
     */
    private val connections = mutableListOf<Pair<String, Int>>()
    private val distance = mutableMapOf<Graph.Vertex, Int>()

    init {
        graph.getAllNodes().forEach {
            if (startVertex != it) {
                distance[it] = Integer.MAX_VALUE
            } else {
                distance[startVertex] = 0
            }
        }
    }

    private fun relaxVertex(queue: LinkedList<Vertex>, vertex: Vertex) {
        val currentWeight = distance[vertex]!!
        vertex.edges.forEach {
            val end = it.endVertex
            val costOfPath = it.weight!!

            if (distance[end]!! > currentWeight + costOfPath) {
                distance[end] = currentWeight + costOfPath
                queue.addLast(it.endVertex)
            }
        }
    }


    fun execute(): Pair<String, Int> {
        val queue = LinkedList<Graph.Vertex>()
        queue.addLast(startVertex)

        while (queue.isNotEmpty()) {
            val first = queue.removeFirst()
            relaxVertex(queue, first)
            println(first.value.toString() + ": " + distance[first])
        }

        println("Min distance from start to end vertex is ${distance[endVertex]}")

        // Above LOC finds best distance from start vertex
        // To find the path that has best distance, we have to do Depth first traversal
        depthFirstTraversal(startVertex, Stack<Vertex>().also { it.add(startVertex) }, 0)

        println("All paths from start to end vertex are:")
        connections.forEach {
            println(it.first.padLeft() + " : " + it.second)
        }

        val bestPath = connections.minBy { it.second }!!
        println("Shortest path is $bestPath")
        return bestPath
    }

    /**
     * Recursively traverse a list
     */
    private fun depthFirstTraversal(vertex: Vertex, stack: Stack<Vertex>, totalDistance: Int) {
        if (vertex == endVertex) {
            // If endVertex then store the path
            connections.add(Pair(stack.getVertexNames(), totalDistance))
        } else {
            for (edge in vertex.edges) {
                // On every branch out, clone the stack
                val newStack = stack.clone() as Stack<Vertex>
                newStack.add(edge.endVertex)
                depthFirstTraversal(edge.endVertex, newStack, totalDistance + edge.weight!!)
            }
        }

    }

}


/**
 * @suppress
 */
fun main() {
    test1()
    test2()
}

private fun test1() {
    val graph = Graph(Relation.WEIGHTED_DIRECTED)
    val v1 = graph.add(1)
    val v6 = graph.add(6)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)
    val v5 = graph.add(5)

    v1.connectWith(v2, 5)
    v1.connectWith(v3, 4)
    v3.connectWith(v2, 6)
    v2.connectWith(v4, 2)
    v3.connectWith(v6, 6)
    v6.connectWith(v5, 1)
    v4.connectWith(v5, 4)

    val (path, distance) = DijkstraAlgorithm(graph, v1, v5).execute()
    assertEquals(11, distance)
    assertEquals("1,2,4,5", path)
}


private fun test2() {
    val graph = Graph(Relation.WEIGHTED_DIRECTED)
    val v1 = graph.add(1)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)
    val v5 = graph.add(5)
    val v6 = graph.add(6)

    v1.connectWith(v2, 5)
    v1.connectWith(v3, 20)
    v1.connectWith(v4, 11)
    v1.connectWith(v5, 25)

    v2.connectWith(v3, 10)

    v3.connectWith(v4, 4)
    v3.connectWith(v6, 20)

    v4.connectWith(v5, 10)

    v5.connectWith(v6, 1)

    val (path, distance) = DijkstraAlgorithm(graph, v1, v6).execute()
    assertEquals(22, distance)
    assertEquals("1,4,5,6", path)
}
