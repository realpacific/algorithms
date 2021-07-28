package algorithmsinanutshell.spatialtree

import algorithmdesignmanualbook.print
import kotlin.math.pow


/**
 * Given a target T and a set of points S, find the nearest neighbour of T in S.
 *
 * https://www.youtube.com/watch?v=Glp7THUpGow
 *
 * https://www.youtube.com/watch?v=XG4zpiJAkD4
 *
 * https://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/kdtrees.pdf
 */
class NearestNeighbourQueries(val array: Array<Array<Int>>) {

    private val tree: MultiDimNode = KDTree(array).tree

    private fun findNearest(subTree: MultiDimNode?, target: Array<Int>, depth: Int): MultiDimNode? {
        if (subTree == null) return null
        val dimensionIndex = depth % target.size

        val nextBranch: MultiDimNode?
        val otherBranch: MultiDimNode?
        if (target[dimensionIndex] < subTree.value[dimensionIndex]) {
            nextBranch = subTree.left
            otherBranch = subTree.right
        } else {
            nextBranch = subTree.right
            otherBranch = subTree.left
        }


        var nodeFromNextBranch = findNearest(nextBranch, target, depth + 1)
        var best = closest(MultiDimNode(target), nodeFromNextBranch, subTree)

        val euclideanDistance = best!!.distanceFrom(target)
        val perpendicularDistance = (target[dimensionIndex] - subTree.value[dimensionIndex]).toDouble().pow(2)

        // Traverse into the unvisited section i.e otherBranch if the best distance found so far is bigger than
        // the perpendicular distance to the unvisited branch
        if (euclideanDistance >= perpendicularDistance) {
            nodeFromNextBranch = findNearest(otherBranch, target, depth + 1)
            best = closest(MultiDimNode(target), nodeFromNextBranch, subTree)
            return best
        }
        return best
    }

    fun execute(target: Array<Int>): MultiDimNode? {
        return findNearest(tree, target, 0)
    }

    private fun closest(target: MultiDimNode, vararg p1: MultiDimNode?): MultiDimNode? {
        return p1.toList()
            .filterNotNull()
            .minByOrNull {
                it.distanceFrom(target.value)
            }
    }
}

fun main() {
    run {
        val array = arrayOf(
            arrayOf(3, 6), arrayOf(17, 15), arrayOf(13, 15),
            arrayOf(6, 12), arrayOf(9, 1), arrayOf(2, 7), arrayOf(10, 19)
        )
        NearestNeighbourQueries(array).execute(arrayOf(1, 2)).print()
    }

    run {
        val array =
            arrayOf(arrayOf(30, 40), arrayOf(5, 25), arrayOf(10, 12), arrayOf(70, 70), arrayOf(50, 30), arrayOf(35, 45))
        NearestNeighbourQueries(array).execute(arrayOf(52, 52)).print()
    }
}