package algorithmsinanutshell

import utils.assertArraysSame
import java.util.*


private fun <T> Stack<T>.fromTopNext() = this[this.lastIndex - 1]

/**
 * Find Convex Hull - a polygon that surrounds all points
 *
 * [link](https://www.cs.auckland.ac.nz/software/AlgAnim/convex_hull.html)
 */
class ConvexHullScanUsingGrahamScan(private val points: Array<Point>) {

    private val result = Stack<Point>()

    init {
        require(points.size >= 3)
        // Left most point is at 0th index
        points.sortBy { it.x }
        points.forEach(::println)
    }

    /**
     * When LTR, exclude 2nd point
     *
     * When RTL, dont exclude
     *
     *        ________x
     *
     *      x
     *
     *    /
     *
     * x
     *
     * Include all if ß12 = ß23
     * x----- x ------x
     *
     */
    private fun excludeP2FromConvexHull(p1: Point, p2: Point, p3: Point): Boolean {

        // Angle made wrt horizontal line
        val theta12 = p1.angle(p2)
        val theta23 = p2.angle(p3)

        println("$p1 $p2 = $theta12")
        println("$p2 $p3 = $theta23")

        if (theta12 == theta23) {
            return false
        } else if (theta12 > 0 && theta23 <= 0) {
            return true
        }
        return false
    }

    fun execute(): Stack<Point> {
        // Add first three points
        result.add(points[0])
        result.add(points[1])
        result.add(points[2])

        for (i in 3..points.lastIndex) {
            val p3 = points[i]
            // Last two from stack
            var p2 = result.lastElement()
            var p1 = result.fromTopNext()

            while (excludeP2FromConvexHull(p1, p2, p3)) {
                // pop the last element from stack and update p2 & p1. p3 remains the same
                result.pop()
                p2 = result.lastElement()
                p1 = result.fromTopNext()
            }
            result.push(p3)
        }
        result.add(points[0])
        println("Stack Content:")
        result.forEach(::println)
        return result
    }

}

fun main() {
    val exec = ConvexHullScanUsingGrahamScan(
        arrayOf(
            0 fromTo 3,
            2 fromTo 3,
            1 fromTo 1,
            2 fromTo 1,
            3 fromTo 0,
            0 fromTo 0,
            3 fromTo 3,
        )
    )
    assertArraysSame(
        expected = arrayOf(0 fromTo 3, 0 fromTo 0, 3 fromTo 0, 3 fromTo 3, 0 fromTo 3),
        actual = exec.execute().toArray()
    )
}