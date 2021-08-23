package algorithmsinanutshell.spatialtree

import algorithmsinanutshell.Point

/**
 * https://algs4.cs.princeton.edu/92search/QuadTree.java.html
 *
 * https://www.youtube.com/watch?v=jxbDYxm-pXg
 *
 * https://www.youtube.com/watch?v=xFcQaig5Z2A
 */
class QuadTree {

    /**
     * QuadNode consists of a value and 4 quadrants.
     *
     *          |  sw  |  se  |  nw  |  ne  |
     */
    inner class QuadNode(
        val value: Int, val point: Point
    ) {
        var sw: QuadNode? = null
        var se: QuadNode? = null
        var nw: QuadNode? = null
        var ne: QuadNode? = null

        val x: Int
            get() = point.x


        val y: Int
            get() = point.y

        override fun toString(): String {
            return "QuadNode($value, sw=$sw, se=$se, nw=$nw, ne=$ne)"
        }
    }

    private var root: QuadNode? = null

    fun insert(x: Int, y: Int, value: Int) {
        val newNode = QuadNode(value, point = Point(x, y))
        root = insert(root, newNode)
    }

    private fun insert(parent: QuadNode?, node: QuadNode): QuadNode {
        if (parent == null) {
            return node
        } else if (node.x < parent.x && node.y < parent.y) {
            parent.sw = insert(parent.sw, node)
        } else if (node.x < parent.x && node.y > parent.y) {
            parent.nw = insert(parent.nw, node)
        } else if (node.x > parent.x && node.y < parent.y) {
            parent.se = insert(parent.se, node)
        } else if (node.x > parent.x && node.y > parent.y) {
            parent.ne = insert(parent.ne, node)
        }
        return parent
    }

    override fun toString(): String {
        return "QuadTree($root)"
    }

    /**
     * Given a rectangle with ([xmin], [ymin]) and ([xmax], [ymax]), find the points inside it.
     */
    fun rangeQuery(xmin: Int, ymin: Int, xmax: Int, ymax: Int): List<QuadNode> {
        val result = mutableListOf<QuadNode>()
        rangeQuery(root!!, xmin, ymin, xmax, ymax, result)
        return result
    }

    private fun rangeQuery(root: QuadNode?, xmin: Int, ymin: Int, xmax: Int, ymax: Int, result: MutableList<QuadNode>) {
        if (root == null) {
            return
        }
        if (root.x in xmin..xmax && root.y in ymin..ymax) {
            result.add(root)
        }

        if (root.x > xmin && root.y > ymin) {
            rangeQuery(root.sw, xmin, ymin, xmax, ymax, result)
        }
        if (root.x > xmin && root.y < ymax) {
            rangeQuery(root.nw, xmin, ymin, xmax, ymax, result)
        }
        if (root.x < xmax && root.y > ymin) {
            rangeQuery(root.se, xmin, ymin, xmax, ymax, result)
        }
        if (root.x < xmax && root.y < ymax) {
            rangeQuery(root.ne, xmin, ymin, xmax, ymax, result)
        }
    }
}

/**
 * @suppress
 */
fun main() {
    val tree = QuadTree()
    tree.insert(10, 10, 1010)
    tree.insert(11, 11, 1111)
    tree.insert(5, 5, 55)
    tree.insert(3, 3, 33)
    tree.insert(2, 2, 22)
    tree.insert(4, 4, 43)
    println(tree)
    tree.rangeQuery(1, 1, 6, 6).forEach { println(it) }
}