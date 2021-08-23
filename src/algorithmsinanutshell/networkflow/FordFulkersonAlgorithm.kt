package algorithmsinanutshell.networkflow

import kotlin.test.assertEquals


/**
 * Algorithm to find the max flow to the sink in a given [network]
 *
 * https://brilliant.org/wiki/ford-fulkerson-algorithm/
 *
 * https://www.youtube.com/watch?v=NwenwITjMys
 *
 * O(E*mf) where E=edge, mf=value of max flow
 */
class FordFulkersonAlgorithm(private val network: Network) {

    init {
        network.validate()
    }

    private fun getPath(from: NVertex, to: NVertex, visitedEdge: MutableSet<Pair<NEdge, Int>>): Set<Pair<NEdge, Int>>? {
        if (from == to) {
            return visitedEdge
        }
        val edges = from.edges
        for (edge in edges) {
            val residualCapacity = edge.capacity - edge.flow

            // Ignore path if residual capacity is not remaining
            if (residualCapacity > 0 && !visitedEdge.contains(Pair(edge, residualCapacity))) {
                // Record the current edge along with residual capacity. The minimum residual capacity is added to flow of whole path
                visitedEdge.add(Pair(edge, residualCapacity))

                val result = getPath(edge.end, to, visitedEdge)
                if (result != null) {
                    // The minimum residual capacity of all edges in current path is added to flow of whole path
                    // Increment flow in current edge
                    edge.flow += visitedEdge.minOf { it.second }
                    return result
                }
            }
        }
        return null
    }

    fun calculateMaxFlow(): Int {
        val source = network.source
        val sink = network.sink
        var path: Set<Pair<NEdge, Int>>?

        // Keep looping since the path may have remaining residual capacity even after visiting it
        do {
            path = getPath(source, sink, mutableSetOf())
        } while (path != null)

        network.vertices.forEach(::println)

        var totalFlow = 0
        network.vertices.forEach { vertex ->
            totalFlow += vertex.edges.filter {
                it.end == network.sink
            }.sumBy { it.flow }
        }
        return totalFlow
    }
}

/**
 * @suppress
 */
fun main() {
    network1()
    network2()
}

private fun network1() {
    val network = Network()
    val s = network.add('S')
    val a = network.add('A')
    val b = network.add('B')
    val c = network.add('C')
    val d = network.add('D')
    val t = network.add('T')

    network.source = s
    network.sink = t

    s.connect(a, 4)
    s.connect(c, 3)
    a.connect(b, 4)
    b.connect(c, 3)
    b.connect(t, 2)
    c.connect(d, 6)
    d.connect(t, 6)

    assertEquals(7, FordFulkersonAlgorithm(network).calculateMaxFlow())
}

private fun network2() {
    val network = Network()
    val s = network.add('S')
    val a = network.add('A')
    val b = network.add('B')
    val c = network.add('C')
    val d = network.add('D')
    val t = network.add('T')

    network.source = s
    network.sink = t

    s.connect(a, 3)
    s.connect(b, 2)

    a.connect(c, 2)
    a.connect(d, 2)

    b.connect(c, 2)
    b.connect(d, 3)

    c.connect(t, 3)
    d.connect(t, 2)

    assertEquals(5, FordFulkersonAlgorithm(network).calculateMaxFlow())
}