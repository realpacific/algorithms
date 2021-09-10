package algorithmsinanutshell

import _utils.SkipDocumentation
import kotlin.math.atan

@SkipDocumentation

data class Line(val p1: Point, val p2: Point)


infix fun Int.fromTo(y: Int) = Point(this, y)


data class Point(val x: Int, val y: Int) {

    fun angle(point: Point): Double {
        val slope = (point.y - y).toDouble() / (point.x - x)
        // tanß = p/b
        var angle = Math.toDegrees(atan(slope))
        if (angle < 0) {
            angle += 360
        }
        return angle
    }
}

enum class Orientation {
    CW, ACW, COLINEAR
}