package algorithmsinanutshell

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Two lines (P1, P2) and (P3,P4) intersect when orientation of:
 * * (P1 wrt P3,P4) and (P2 wrt P3,P4)
 * * (P3 wrt P1,P2) and (P4 wrt P1,P2)
 *
 * are different.
 *
 * A line (P1, P2) can be represented as vector P1P2 and orientation is the way (whether counter-clockwise or clockwise)
 * direction it makes fromTo move from P1P2 fromTo P3.
 *
 *           P3 o
 *
 *       P1 o----->---o P2
 *
 *   orientation of P3 wrt P1P2 is ACW.
 *
 *
 * ### Special case is when (P1, P2) and (P3,P4) are collinear.
 *
 *  * In case of non-intersecting case, orientation is 0 for both P3(P1P2) and P4(P1,P2) and vv.
 *
 *       P1 o----->---o P2   P3 o----->---o P4  (Non-intersecting case)
 *
 *  * In case of intersecting case, orientation is different for both P3(P1P2) and P4(P1,P2) and vv.
 *
 *       P1 o----->--oP3-----P2o-->---o P4 (Intersecting case)
 *
 *
 * https://www.youtube.com/watch?v=bbTqI0oqL5U
 */
class IntersectionOfLines(val l1: Line, val l2: Line) {

    private fun getOrientation(p1: Point, p2: Point, other: Point): Orientation {
        val alpha = abs(p1.angle(other))
        val beta = abs(p2.angle(other))
        if (alpha == beta) return Orientation.COLINEAR
        return if (beta > alpha) Orientation.ACW else Orientation.CW
    }

    /**
     * Find whether [other] lies in the line [p1] to [p2]
     */
    private fun hasOverlap(p1: Point, p2: Point, other: Point): Boolean {
        return (other.x >= min(p1.x, p2.x) && other.x <= max(p1.x, p2.x))
            .and(other.y >= min(p1.y, p2.y) && other.y <= max(p1.y, p2.y))
    }

    fun hasIntersection(): Boolean {
        val o1 = getOrientation(l1.p1, l1.p2, l2.p1)
        val o2 = getOrientation(l1.p1, l1.p2, l2.p2)

        val o3 = getOrientation(l2.p1, l2.p2, l1.p1)
        val o4 = getOrientation(l2.p1, l2.p2, l1.p2)

        // Intersection when orientation are different
        if (o1 != o2 && o3 != o4) {
            return true
        }
        if (o1 == Orientation.COLINEAR && hasOverlap(l1.p1, l1.p2, l2.p1)) {
            return true
        }

        if (o2 == Orientation.COLINEAR && hasOverlap(l1.p1, l1.p2, l2.p2)) {
            return true
        }

        if (o3 == Orientation.COLINEAR && hasOverlap(l2.p1, l2.p2, l1.p1)) {
            return true
        }

        if (o4 == Orientation.COLINEAR && hasOverlap(l2.p1, l2.p2, l1.p2)) {
            return true
        }

        return false
    }
}

fun main() {
    run {
        val l1 = Line(5 fromTo 5, 10 fromTo 12)
        val l2 = Line(0 fromTo 0, 1 fromTo 1)
        val algorithm = IntersectionOfLines(l1, l2)
        assertFalse { algorithm.hasIntersection() }
    }

    run {
        val l1 = Line(0 fromTo 0, 5 fromTo 5)
        val l2 = Line(0 fromTo 5, 5 fromTo 0)
        val algorithm = IntersectionOfLines(l1, l2)
        assertTrue { algorithm.hasIntersection() }
    }



    run {
        val l1 = Line(0 fromTo 0, 5 fromTo 0)
        val l2 = Line(1 fromTo 0, 15 fromTo 0)
        val algorithm = IntersectionOfLines(l1, l2)
        assertTrue { algorithm.hasIntersection() }
    }
}