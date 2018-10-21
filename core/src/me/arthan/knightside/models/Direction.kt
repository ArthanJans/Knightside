package me.arthan.knightside.models

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.PI
import kotlin.math.atan

class Direction(rad: Double) {
    var radians: Double = rad
    var degrees: Double
        get() = radians/PI * 180
        set(value){
            radians = value / 180 * PI
        }

}

fun add(dir1: Direction, dir2: Direction): Direction? {
    var opposite = sin(dir1.radians) + sin(dir2.radians)
    var adjacent = cos(dir1.radians) + cos(dir2.radians)
    if (adjacent == 0.0) {
        if (opposite > 0) {
            return DOWN
        } else if (opposite < 0) {
            return UP
        } else {
            return null
        }
    } else if (opposite == 0.0) {
        if (adjacent > 0) {
            return RIGHT
        } else if (adjacent < 0) {
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

val UP = Direction(3*PI/2)
val DOWN = Direction(PI/2)
val LEFT = Direction(PI)
val RIGHT = Direction(0.0)