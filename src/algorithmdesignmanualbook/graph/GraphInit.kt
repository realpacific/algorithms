package algorithmdesignmanualbook.graph

import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

fun main() {
    val graph = Graph(true)
    val v1 = graph.addVertex("1")
    val v2 = graph.addVertex("2")
    val v3 = graph.addVertex("3")
    graph.join(v2, v3)
    graph.join(v1, v2)
    graph.join(v1, v3)
    println(graph)

    assertTrue { graph.isIn(v1.value) }
    assertTrue { graph.isIn(v2.value) }

    graph.join(v2, v3)
    graph.join(v2, v3)
    graph.join(v2, v3)
    graph.join(v3, v2)


}