package algorithmsinanutshell

import algorithmdesignmanualbook.print
import kotlin.test.assertTrue

/**
 * [Link](https://www.geeksforgeeks.org/orientation-3-ordered-points/)
 *
 * [Related](https://youtu.be/bbTqI0oqL5U?t=161)
 */
private class OrientationOf3Points(val p: Point, val q: Point, val r: Point) {

    /**
     * ```
     *   p0 (x0,y0)    p1 (x1,y1)
     *     x           x
     *      \         /
     *       \       /
     *        \     /
     *           x
     *         p2  (x2,y2)
     *
     *  vector(P2P1) X vector(P2P0) =   | x2-x1   x2-x0 |
     *                                  | y2-y1   y2-y0 |
     *
     *  IF cross product is >0, then clockwise
     *  if negative then counter-clockwise
     *  if 0 then collinear
     *```
     */
    fun getOrientation(): Orientation {
        // p = P0
        // q = P1
        // r = P2
        // P2P1 X P2P0 = (x2-x1) * (y2-y0) - (y2-y1) * (x2-x0)
        val determinate = (r.x - q.x) * (r.y - p.y) - (r.y - q.y) * (r.x - p.x)
        return when {
            determinate > 0 -> Orientation.CW
            determinate == 0 -> Orientation.COLINEAR
            else -> Orientation.ACW
        }
    }
}

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

    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 6 fromTo 0, 2 fromTo 0)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.COLINEAR
        }
    }

    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 4 fromTo 0, 4 fromTo 10)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.ACW
        }
    }


    run {
        val algorithm = OrientationOf3Points(0 fromTo 0, 4 fromTo 0, 4 fromTo -10)
        assertTrue {
            algorithm.getOrientation().print() == Orientation.CW
        }
    }
}