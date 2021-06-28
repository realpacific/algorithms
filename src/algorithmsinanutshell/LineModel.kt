package algorithmsinanutshell

import kotlin.math.atan

data class Line(val p1: Point, val p2: Point)


infix fun Int.fromTo(y: Int) = Point(this, y)


data class Point(val x: Int, val y: Int) {

    fun angle(point: Point): Double {
        val slope = (point.y - y).toDouble() / (point.x - x)
        // tanß = p/b
        return Math.toDegrees(atan(slope))
    }
}

enum class Orientation {
    CW, ACW, COLINEAR
}