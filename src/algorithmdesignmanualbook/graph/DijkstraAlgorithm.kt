package algorithmdesignmanualbook.graph

import java.lang.Integer.MAX_VALUE

private fun findShortestPath(graph: Graph) {
    val distance = mutableMapOf<Graph.Vertex, Int>()
    val selected = mutableMapOf<String, Boolean>()

    var currentVertex = graph.getVertex("0")
    distance[currentVertex] = 0
    while (!selected.containsKey(currentVertex.value)) {
        selected[currentVertex.value] = true
        var edgeIndex = 0
        var edge: Graph.Edge? = currentVertex.edges.elementAtOrNull(edgeIndex)
        while (edge != null) {
            val next = edge.endVertex
            val weight = edge.weight
            val hasShorterDistance = distance.getOrDefault(next, MAX_VALUE) > distance[currentVertex]!! + weight
            if (hasShorterDistance) {
                distance[next] = distance.getOrDefault(currentVertex, MAX_VALUE) + weight
            }
            edgeIndex++
            edge = currentVertex.edges.elementAtOrNull(edgeIndex)
        }

        for (vertex: String in graph.vertices.keys) {
            if (!selected.containsKey(vertex)) {
                currentVertex = graph.getVertex(vertex)
                break
            }
        }
    }
    println(distance.map {
        Pair(it.key.value, it.value)
    })
}

fun main() {
    val graph = Graph(true)
    graph.addVertex("0")
    graph.addVertex("1")
    graph.addVertex("2")
    graph.addVertex("3")
    graph.addVertex("4")
    graph.addVertex("5")

    graph.join("0", "1", 2)
    graph.join("1", "3", 7)
    graph.join("3", "5", 1)
    graph.join("0", "2", 4)
    graph.join("2", "4", 3)
    graph.join("4", "5", 5)
    graph.join("1", "2", 1)
    graph.join("4", "3", 2)

    findShortestPath(graph)

    TODO()
}