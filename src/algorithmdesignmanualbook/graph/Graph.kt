package algorithmdesignmanualbook.graph

class Graph(private val isDirected: Boolean) {
    private val vertices = mutableMapOf<String, Vertex>()

    fun isIn(value: String): Boolean {
        return vertices.containsKey(value)
    }

    fun addVertex(value: String): Vertex {
        require(!vertices.containsKey(value))
        vertices[value] = Vertex(value)
        return vertices[value]!!
    }

    fun join(v1: Vertex, v2: Vertex, weight: Int = 0) {
        v1.addEdge(v2, weight)
        if (!isDirected) {
            v2.addEdge(v1, weight)
        }
    }

    fun join(value1: String, value2: String, weight: Int = 0) {
        val v1 = vertices[value1]
        val v2 = vertices[value2]
        requireNotNull(v1)
        requireNotNull(v2)
        join(v1, v2, weight)
    }

    override fun toString(): String {
        return "Graph(${vertices.values})"
    }

    data class Vertex(val value: String) {
        var edges = mutableSetOf<Edge>()
        var state = State.UNDISCOVERED

        fun addEdge(vertex: Vertex, weight: Int = 0) {
            val edge = Edge(this, vertex)
            edge.weight = weight
            this.edges.add(edge)
        }

        override fun toString(): String {
            return "Vertex(value=$value, edges=${edges})"

        }
    }

    enum class State {
        DISCOVERED, UNDISCOVERED, PROCESSED
    }

    data class Edge(val startVertex: Vertex, val endVertex: Vertex) {
        var weight: Int = 0

        override fun toString(): String {
            return "Vertex(${endVertex.value}, weight=$weight)"
        }
    }
}
