package algorithmsinanutshell

typealias Vertex = Graph.Vertex
typealias Edge = Graph.Edge
typealias State = Graph.State

enum class Relation(val symbol: String) {
    UNWEIGHTED_DIRECTED("----->"),
    UNWEIGHTED_UNDIRECTED("<----->"),
    WEIGHTED_DIRECTED("--(%s)-->"),
    WEIGHTED_UNDIRECTED("<--(%s)-->");

    fun isDirected() = this == WEIGHTED_DIRECTED || this == UNWEIGHTED_DIRECTED
    fun isWeighted() = this == WEIGHTED_DIRECTED || this == WEIGHTED_UNDIRECTED

    fun createSymbol(weight: Int? = null): String {
        return if (this.isWeighted()) {
            String.format(this.symbol, weight.toString())
        } else {
            this.symbol
        }
    }
}

class Graph(val relation: Relation = Relation.UNWEIGHTED_DIRECTED) {
    var nodes = mutableListOf<Vertex>()
        private set
    private val map = mutableMapOf<Int, Vertex>()

    val vertexCount: Int
        get() = nodes.size

    fun add(value: Int): Vertex {
        require(!map.containsKey(value)) {
            "Vertex $value already present"
        }
        return Vertex(value).also {
            nodes.add(it)
            map[value] = it
        }
    }

    fun setAllAsUnvisited() {
        getAllNodes().forEach {
            it.state = State.Undiscovered
        }
    }

    fun getUnvisited() = map.values.filter { it.state == State.Undiscovered }

    fun getAllNodes() = map.values

    override fun toString(): String {
        return "Graph(nodes=$nodes)"
    }

    inner class Vertex(val value: Int) {
        var edges = mutableListOf<Edge>()
            private set
        var state = State.Undiscovered

        override fun toString(): String {
            return "Vertex(value=$value, edges=$edges,  state=$state)"
        }

        override fun equals(other: Any?): Boolean {
            // In Graph#add method, there is a check that ensures vertex value is unique
            if (other is Vertex) {
                return this.value == other.value
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            return value
        }

        fun connectWith(vertex: Vertex, weight: Int? = null, readd: Boolean = true) {
            if (relation.isWeighted()) require(weight != null)
            else require(weight == null)

            edges.add(Edge(this, vertex, weight))
            if (!relation.isDirected() && readd) {
                vertex.connectWith(this, weight, false)
            }
        }
    }

    inner class Edge(val startVertex: Vertex, val endVertex: Vertex, val weight: Int?) {

        override fun equals(other: Any?): Boolean {
            // In case of un-directed relationship, a--b and b--a are same
            if (!relation.isDirected() && other is Edge) {
                return ((this.startVertex == other.startVertex && this.endVertex == other.endVertex)
                    .or(this.endVertex == other.startVertex && this.startVertex == other.endVertex))
                        && this.weight == other.weight
            }
            return super.equals(other)
        }

        override fun toString(): String {
            return "Edge(${startVertex.value} ${relation.createSymbol(weight)} ${endVertex.value})"
        }

        override fun hashCode(): Int {
            // start vertex and end vertex are interchangeable in case of un-directed relationship
            if (!relation.isDirected()) {
                var result = startVertex.value
                result += endVertex.value
                result = 31 * result + (weight ?: 0)
                return result
            }
            return super.hashCode()
        }
    }

    enum class State {
        Discovered, Undiscovered, Processing;
    }

    companion object {
        fun createWeightedDirectedGraph() = Graph(Relation.WEIGHTED_DIRECTED)
        fun createUnweightedUndirectedGraph() = Graph(Relation.UNWEIGHTED_UNDIRECTED)
    }
}

fun Iterable<Vertex>.getVertexNames() = this.map { it.value }.joinToString(",")


/**
 * @suppress
 */
fun main() {
    val graph = Graph(Relation.WEIGHTED_DIRECTED)

    val v1 = graph.add(1)
    val v2 = graph.add(2)
    val v3 = graph.add(3)
    val v4 = graph.add(4)
    val v5 = graph.add(5)

    v1.connectWith(v2, 1)
    v2.connectWith(v3, 4)
    v1.connectWith(v4, 5)
    v4.connectWith(v5, 6)

    println(graph)
}