package me.arthan.knightside.models

import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

class Direction(radians: Double) {


//    constructor(degrees: Int): this(degrees/180 * PI)

    var radians: Double = radians % (2 * PI)
        set(value){
            println(value)
            field = value % (2 * PI)
            println(field)
        }

    var degrees: Double
        get() = radians/PI * 180
        set(value){
            radians = value / 180 * PI
        }

}

fun opposite(dir: Direction): Direction {
    val dir2 = Direction(dir.radians + PI)
    return dir2
}

fun add(dir1: Direction?, dir2: Direction?): Direction? {
    if (dir1 == null) {
        return dir2
    } else if (dir2 == null) {
        return dir1
    }
    var opposite = sin(dir1.radians) + sin(dir2.radians)
    var adjacent = cos(dir1.radians) + cos(dir2.radians)
    if (adjacent in -0.01 .. 0.01) {
        if (opposite > 0.01) {
            return DOWN
        } else if (opposite < -0.01) {
            return UP
        } else {
            return null
        }
    } else if (opposite in -0.01 .. 0.01) {
        if (adjacent > 0.01) {
            return RIGHT
        } else if (adjacent < -0.01) {
            return LEFT
        } else {
            return null
        }
    } else {
        var rad = atan(opposite/adjacent)
        if (rad < 0) {
            rad += PI
        }
        if (opposite < 0) {
            rad += PI
        }
        return Direction(rad)
    }
}

val UP = Direction(3 * PI / 2)
val DOWN = Direction(PI / 2)
val LEFT = Direction(PI)
val RIGHT = Direction(0.0)

val DIRS = arrayOf(UP, DOWN, LEFT, RIGHT)