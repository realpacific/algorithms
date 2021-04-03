package algorithmdesignmanualbook.graph


class SimpleGraph(val vertexCount: Int, val edgesCount: Int) {

    class Edge(var src: Int, var dest: Int, var weight: Int)

    val edges = Array<Edge?>(edgesCount) { null }
}