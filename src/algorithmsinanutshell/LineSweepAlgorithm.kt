package algorithmsinanutshell

import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertTrue

/**
 * Given a number of lines, find the lines that intersects with each other
 *
 * Starting from left, sweep a vertical line through each point. Find the intersection of lines with each other
 * that the vertical lines touches. If the sweep line has moved past a line (both points are to its left),
 * this can be ignored.
 *
 * [link](https://www.geeksforgeeks.org/given-a-set-of-line-segments-find-if-any-two-segments-intersect/)
 */
class LineSweepAlgorithm(private val lines: List<Line>) {

    // Sort by x-coordinate
    private var _lines = lines.sortedBy { line -> line.p1.x }

    /**
     * Find active lines w.r.t [vLine]
     */
    private fun findLinesWithOnePointToLeftOfVerticalLine(vLine: Line): List<Line> {
//        return _lines.filter { line ->
//            min(line.p1.x, line.p2.x) <= vLine.p1.x && max(line.p2.x, line.p1.x) >= vLine.p1.x
//        }
        val result = mutableListOf<Line>()
        for (line in _lines) {
            // Since _lines is sorted, no need to go through all
            if (min(line.p1.x, line.p2.x) <= vLine.p1.x && max(line.p2.x, line.p1.x) >= vLine.p1.x) {
                result.add(line)
            } else {
                return result
            }
        }
        return result
    }

    fun execute(): Map<Line, Set<Line>> {
        val intersectionMap = mapOf(*lines.map { l -> Pair(l, mutableSetOf<Line>()) }.toTypedArray())
        // Could be optimized
        // Find minY and maxY to find the length of vertical line that moves from minX to maxX
        // OR GO with top to bottom instead of left to right, _lines is already sorted
        val (minY, maxY) = Pair(
            _lines.minOf { line -> min(line.p1.y, line.p2.y) },
            _lines.maxOf { line -> max(line.p1.y, line.p2.y) }
        )
        for (line in _lines) {
            val sweepLine = Line(line.p1.x fromTo minY, line.p1.x fromTo maxY)
            val activeLines = findLinesWithOnePointToLeftOfVerticalLine(sweepLine)
            for (i in 0..activeLines.lastIndex) {
                for (j in 0..activeLines.lastIndex) {
                    if (i == j) {
                        continue
                    }
                    val l1 = activeLines[i]
                    val l2 = activeLines[j]

                    // Already exists
                    if (intersectionMap[l1]!!.contains(l2)) {
                        continue
                    }
                    if (IntersectionOfLines(l1, l2).hasIntersection()) {
                        intersectionMap[l1]!!.add(l2)
                        intersectionMap[l2]!!.add(l1)
                    }
                }
            }
        }
        intersectionMap.forEach { (t, u) ->
            println("$t: $u")
        }
        return intersectionMap
    }
}

fun main() {
    run {
        val lines = listOf(
            Line(1 fromTo 5, 4 fromTo 5),
            Line(6 fromTo 4, 9 fromTo 4),
            Line(3 fromTo 2, 10 fromTo 3),
            Line(7 fromTo 1, 8 fromTo 1),
            Line(2 fromTo 5, 10 fromTo 1),
        )
        val intersections = LineSweepAlgorithm(lines).execute()
        intersections.forEach { (currentLine, intersectingLines) ->
            intersectingLines.forEach {
                assertTrue { IntersectionOfLines(it, currentLine).hasIntersection() }
            }
        }
    }

    run {
        // Has no intersection
        val lines = listOf(
            Line(0 fromTo 0, 10 fromTo 0),
            Line(5 fromTo 3, 10 fromTo 3),
            Line(3 fromTo 4, 10 fromTo 4)
        )
        val intersections = LineSweepAlgorithm(lines).execute()
        intersections.forEach { (_, intersectingLines) ->
            assertTrue { intersectingLines.isEmpty() }
        }
    }

    run {
        //
        //   *    *   *
        //     \  | /
        //        x
        //     /  | \
        //    *   *  *
        val lines = listOf(
            Line(1 fromTo 6, 4 fromTo 8),
            Line(1 fromTo 8, 4 fromTo 6),
            Line(3 fromTo 10, 3 fromTo 4)
        )
        val intersections = LineSweepAlgorithm(lines).execute()
        intersections.forEach { (currentLine, intersectingLines) ->
            assertTrue { intersectingLines.size == 2 }
            intersectingLines.forEach {
                assertTrue { IntersectionOfLines(it, currentLine).hasIntersection() }
            }
        }
    }
}