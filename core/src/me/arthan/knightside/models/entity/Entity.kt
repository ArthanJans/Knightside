package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Model
import me.arthan.knightside.models.Map
import kotlin.math.cos
import kotlin.math.sin

open abstract class Entity(pos: Vector2, facing: Direction): Model() {
    var pos = pos
    var facing = facing

    fun move(dir: Direction, map: Map) {
        var oldpos = Vector2(this.pos)
        var x = cos(dir.radians).toFloat()
        var y = -1 * sin(dir.radians).toFloat()
        this.pos.add(x, y)
        var rect = Rectangle(this.pos.x - 7, this.pos.y - 8, 14f, 8f)
        if (map.doesOverlap(rect)) {
            this.pos = Vector2(oldpos)
            this.pos.add(x, 0f)
            var rect = Rectangle(this.pos.x - 7, this.pos.y - 8, 14f, 8f)
            if (map.doesOverlap(rect)) {
                this.pos = Vector2(oldpos)
                this.pos.add(0f, y)
                var rect = Rectangle(this.pos.x - 7, this.pos.y - 8, 14f, 8f)
                if (map.doesOverlap(rect)) {
                    this.pos = Vector2(oldpos)
                }
            }
        }
        this.facing = dir
    }


}