package algorithmdesignmanualbook.graph

import _utils.UseCommentAsDocumentation
import java.util.*
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Union-Find represent each subset as backward trees
 *      !  0  !  1  !  2  !
 *      !  1  !  2  !  0  !
 *
 * [Video1](https://www.youtube.com/watch?v=ayW5B2W9hfo) - [Video2](https://www.youtube.com/watch?v=eTaWFhPXPz4) - [Solution](https://www.geeksforgeeks.org/union-find/)
 */
@UseCommentAsDocumentation
private class UnionFindSimpleGraph(private val vertexCount: Int, private val edgesCount: Int) {

    class Edge(var src: Int, var dest: Int)

    val edge = Array<Edge?>(edgesCount) {
        null
    }

    fun find(parent: Array<Int>, i: Int): Int {
        // you've reached the root/parent of the "tree"/relationship
        if (parent[i] == -1 || parent[i] == i) {
            return i
        }
        // visit its parent if unionized
        return find(parent, parent[i])
    }

    fun hasCycle(): Boolean {
        // disjoint sets
        val parent = Array(vertexCount) { -1 }

        for (i in 0 until edgesCount) {
            val root1 = find(parent, edge[i]!!.src)
            val root2 = find(parent, edge[i]!!.dest)

            // since SimpleGraph#find returns itself if not initialized, root1 & root2 cant be equal
            if (root1 == root2)
                return true

            // make r2 the parent of r1 i.e put in same set
            union(parent, root1, root2)
        }
        return false
    }

    private fun union(parent: Array<Int>, root1: Int, root2: Int) {
        parent[root1] = root2
    }
}

fun main() {
    run {
        val graph = UnionFindSimpleGraph(3, 3)
        graph.edge[0] = UnionFindSimpleGraph.Edge(0, 1)
        graph.edge[1] = UnionFindSimpleGraph.Edge(1, 2)
        graph.edge[2] = UnionFindSimpleGraph.Edge(2, 0)
        assertTrue { graph.hasCycle() }
    }
    run {
        val graph = UnionFindSimpleGraph(4, 3)
        graph.edge[0] = UnionFindSimpleGraph.Edge(0, 1)
        graph.edge[1] = UnionFindSimpleGraph.Edge(1, 2)
        graph.edge[2] = UnionFindSimpleGraph.Edge(2, 3)
        assertFalse { graph.hasCycle() }
    }
}