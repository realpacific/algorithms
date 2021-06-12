package algorithmsinanutshell

import utils.PrintUtils
import java.lang.Integer.min

/**
 * # All pair shortest path algorithm
 *
 * While Dijkstra Shortest Path algorithm helps find shortest path between start and end vertex, [FloydWarshallAlgorithm]
 * finds the shortest path between all vertices in a [graph]
 *
 * [Source](https://www.youtube.com/watch?v=oNI0rf2P9gE)
 */
class FloydWarshallAlgorithm(val graph: Graph) {

    private var adjacencyMatrix = Array(graph.vertexCount) { row ->
        Array(graph.vertexCount) { col ->
            if (row == col) 0 else Integer.MAX_VALUE
        }
    }

    private fun buildAdjacencyMatrix() {
        graph.getAllNodes().forEach { startVertex ->
            startVertex.edges.forEach {
                val endVertex = it.endVertex
                adjacencyMatrix[startVertex.value - 1][endVertex.value - 1] = it.weight!!
            }
        }
    }

    init {
        buildAdjacencyMatrix()
    }

    fun execute() {
        println("Adjacency Matrix")
        PrintUtils.printIntArrWithCast(adjacencyMatrix.toList())

        for (k in 0 until graph.vertexCount) {
            for (row in 0 until graph.vertexCount) {
                for (col in 0 until graph.vertexCount) {
                    if (row == col) {
                        continue
                    }
                    // This is necessary to prevent addition of number with Integer.MAX_VALUE i.e (Integer.MAX_VALUE + N)
                    if (adjacencyMatrix[row][k] == Integer.MAX_VALUE || adjacencyMatrix[k][col] == Integer.MAX_VALUE) {
                        continue
                    }

                    adjacencyMatrix[row][col] = min(
                        adjacencyMatrix[row][col], adjacencyMatrix[row][k] + adjacencyMatrix[k][col]
                    )
                }
            }
        }
        println("Shortest Path Matrix")
        PrintUtils.printIntArrWithCast(adjacencyMatrix.toList())
    }
}


fun main() {
    val graph = Graph.createWeightedDirectedGraph()
    val v1 = graph.add(1)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)

    v1.connectWith(v2, 3)
    v1.connectWith(v4, 7)

    v2.connectWith(v1, 8)
    v2.connectWith(v3, 2)

    v3.connectWith(v4, 1)
    v3.connectWith(v1, 5)

    v4.connectWith(v1, 2)

    FloydWarshallAlgorithm(graph).execute()
}