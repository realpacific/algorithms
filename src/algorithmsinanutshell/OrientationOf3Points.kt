package algorithmsinanutshell

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

/**
 * [Link](https://www.geeksforgeeks.org/orientation-3-ordered-points/)
 */
private class OrientationOf3Points(val p: Point, val q: Point, val r: Point) {

    fun getOrientation(): Orientation {
        val alpha = p.angle(q)
        val beta = q.angle(r)
        if (alpha == beta) return Orientation.COLINEAR
        return if (beta > alpha) Orientation.ACW else Orientation.CW
    }
}

/**
 * @suppress
 */
fun main() {
    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 2 fromTo 4, 6 fromTo 0)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.CW
        }
    }

    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 1 fromTo 0, 4 fromTo 0)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.COLINEAR
        }
    }

    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 6 fromTo 1, 7 fromTo 5)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.ACW
        }
    }
}