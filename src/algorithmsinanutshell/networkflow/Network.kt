package algorithmsinanutshell.networkflow

typealias NVertex = Network.Vertex
typealias NEdge = Network.Edge

class Network {
    private val _vertices = mutableListOf<Vertex>()
    lateinit var source: Vertex
    lateinit var sink: Vertex

    val vertices: List<Vertex>
        get() = _vertices.toList()

    override fun toString(): String {
        return "Network(vertices=$_vertices)"
    }

    fun validate(): Boolean {
        require(::source.isInitialized && ::sink.isInitialized)
        return true
    }

    fun add(value: Char): Vertex {
        return Vertex(value).also(_vertices::add)
    }

    data class Vertex(val name: Char) {
        val edges = mutableListOf<Edge>()

        fun connect(end: Vertex, capacity: Int) {
            val edge = Edge(this, end, capacity)
            edges.add(edge)
        }

        override fun toString(): String {
            return "Vertex($name, edges=$edges)"
        }
    }

    class Edge(val start: Vertex, val end: Vertex, val capacity: Int) {
        var flow: Int = 0

        override fun toString(): String {
            return "Edge(${start.name} ---${flow}/${capacity}--> ${end.name})"
        }

        override fun equals(other: Any?): Boolean {
            if (other is Edge) {
                return other.start == this.start && other.end == this.end
            }
            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = start.hashCode()
            result = 31 * result + end.hashCode()
            return result
        }
    }
}