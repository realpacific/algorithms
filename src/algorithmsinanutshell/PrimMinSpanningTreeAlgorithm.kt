package algorithmsinanutshell

import utils.assertArraysSame
import java.util.*
import kotlin.test.assertEquals

/**
 * Prim Algorithm starts from min cost edge and then selects the next small cost edge
 * while maintaining the connection with first edge.
 *
 * This can be modeled using [PriorityQueue] sorted using [Edge] w.r.t its weight
 */
class PrimMinSpanningTreeAlgorithm(val graph: Graph) {
    private val selected = mutableSetOf<Vertex>()
    private val mst = Stack<Edge>()
    private val pq = PriorityQueue<Edge> { o1, o2 -> o1!!.weight!! - o2!!.weight!! }
    private var isExecuted = false

    private lateinit var minEdge: Graph.Edge

    init {
        graph.getAllNodes().forEach { startVertex ->
            startVertex.edges.forEach {
                if (!::minEdge.isInitialized) {
                    minEdge = it
                } else if (minEdge.weight!! > it.weight!!) {
                    minEdge = it
                }
            }
        }
    }

    fun execute(): PrimMinSpanningTreeAlgorithm {
        runAlgorithm()
        mst.forEach(::println)
        isExecuted = true
        return this
    }

    fun result(): List<Edge> {
        require(isExecuted)
        return mst.toList()
    }

    fun minCost(): Int {
        require(isExecuted)
        return mst.sumBy { it.weight!! }
    }

    private fun runAlgorithm() {
        // Add the starting vertex to selected
        selected.add(minEdge.startVertex)

        // Put its edge on priority queue
        minEdge.startVertex.edges.forEach(pq::offer)

        while (pq.isNotEmpty()) {
            // Since items in priority queue are sorted using edge's weight, lowest cost edge is always at the top
            val edge = pq.poll()
            if (selected.contains(edge.endVertex)) {
                continue
            } else {
                mst.add(edge)
                selected.add(edge.endVertex)
                // Add end vertex's edges to priority queue
                // This will put it as candidate to be included in mst
                edge.endVertex.edges.forEach(pq::offer)
            }
        }
    }
}


/**
 * @suppress
 */
fun main() {
    val graph = Graph(Relation.WEIGHTED_UNDIRECTED)
    val v1 = graph.add(1)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)
    val v5 = graph.add(5)

    v1.connectWith(v2, 3)
    v2.connectWith(v3, 6)
    v3.connectWith(v4, 5)
    v4.connectWith(v1, 10)
    v5.connectWith(v2, 9)
    v5.connectWith(v4, 8)

    val mst = PrimMinSpanningTreeAlgorithm(graph).execute()
    assertEquals(22, mst.minCost())
    assertArraysSame(arrayOf(3, 6, 5, 8), mst.result().map { it.weight }.toTypedArray())

}