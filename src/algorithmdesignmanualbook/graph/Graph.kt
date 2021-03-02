package algorithmdesignmanualbook.graph

class Graph(private val isDirected: Boolean) {
    private val vertices = mutableMapOf<String, Vertex>()

    fun isIn(value: String): Boolean {
        return vertices.containsKey(value)
    }

    fun getRandomVertex(): Vertex {
        return vertices[vertices.keys.elementAt(0)]!!
    }


    fun getVertex(str: String): Vertex {
        return vertices[str]!!
    }

    fun isConnected(v1: String, v2: String): Boolean {
        return (vertices[v1] ?: return false).isConnectTo(vertices[v2] ?: return false)
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

        fun isConnectTo(v2: Vertex): Boolean {
            val edge = Edge(this, v2)
            return edges.contains(edge)
        }

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

    companion object {
        fun getDefaultDirected(): Graph {
            val graph = Graph(true)
            val v1 = graph.addVertex("1")
            val v2 = graph.addVertex("2")
            val v3 = graph.addVertex("3")
            val v4 = graph.addVertex("4")
            val v5 = graph.addVertex("5")
            val v6 = graph.addVertex("6")
            val v7 = graph.addVertex("7")
            graph.join(v2, v3)
            graph.join(v1, v2)
            graph.join(v1, v3)
            graph.join(v1, v5)
            graph.join(v5, v4)
            graph.join(v3, v6)
            graph.join(v4, v7)
            return graph
        }

        /**
         *     2---3
         *   / |   |
         * 1   |   4
         * |\ |---/
         * 6  5
         *
         */
        fun getDefaultUnDirected(): Graph {
            val graph = Graph(false)
            val v1 = graph.addVertex("1")
            val v2 = graph.addVertex("2")
            val v3 = graph.addVertex("3")
            val v4 = graph.addVertex("4")
            val v5 = graph.addVertex("5")
            val v6 = graph.addVertex("6")
            graph.join(v1, v2)
            graph.join(v2, v3)
            graph.join(v3, v4)
            graph.join(v4, v5)
            graph.join(v5, v1)
            graph.join(v1, v6)
            return graph
        }
    }
}
