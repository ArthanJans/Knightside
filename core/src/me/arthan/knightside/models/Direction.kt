package me.arthan.knightside.models

class Direction(deg: Int) {
    var degrees: Int = deg


}

val UP = Direction(90)
val DOWN = Direction(270)
val LEFT = Direction(180)
val RIGHT = Direction(0)