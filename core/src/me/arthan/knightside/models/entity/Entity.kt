package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Model
import kotlin.math.cos
import kotlin.math.sin

open abstract class Entity(pos: Vector2, facing: Direction): Model {
    var pos = pos
    var facing = facing

    fun move(dir: Direction) {
        this.pos.add(cos(dir.radians).toFloat(), -1 * sin(dir.radians).toFloat())
        this.facing = dir
    }


}