package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction

open class Entity(pos: Vector2, facing: Direction) {
    var pos = pos
    var facing = facing

    fun move(dir: Direction) {
        this.pos.add(Math.cos(Math.toRadians(dir.degrees.toDouble())).toFloat(), -1 * Math.sin(Math.toRadians(dir.degrees.toDouble())).toFloat())
    }
}