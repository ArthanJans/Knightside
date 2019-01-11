package me.arthan.knightside.models.entity.monster

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity
import kotlin.math.cos
import kotlin.math.sin

class Monster (pos: Vector2, facing: Direction): Entity(pos, facing) {
    override var name = "monster"
    override var health = 100
    override var maxhealth = 100
    override var mana = 100
    override var speed = 1f

    fun attackMove(dir: Direction?, map: Map) {
        if (!knockback.isZero) {
            var x = knockback.x
            var y = -1 * knockback.y

            //Try move on X axis
            var tempPos = Vector2(pos)
            this.pos.add(x, 0f)
            if (map.doesOverlap(getCollsionRect(pos))) {
                this.pos = tempPos
            }

            //Try move on Y axis
            tempPos = Vector2(pos)
            this.pos.add(0f, y)
            if (map.doesOverlap(getCollsionRect(pos))) {
                this.pos = tempPos
            }

            if (knockback.len() < 0.1f) {
                knockback.x = 0f
                knockback.y = 0f
            } else {
                knockback.setLength(knockback.len() - 0.1f)
            }
        } else if (dir == null || attack != null) {
            moving = false
        } else {
            moving = true

            var x = cos(dir.radians).toFloat() * this.speed
            var y = -1 * sin(dir.radians).toFloat() * this.speed

            //Try move on X axis
            var tempPos = Vector2(pos)
            this.pos.add(x, 0f)
            if (map.doesOverlapMap(getCollsionRect(pos))) {
                this.pos = tempPos
            } else {
                val entities = map.overlapEntities(getCollsionRect(pos))
                for (entity in entities) {
                    attack(null, entity)
                    this.pos = tempPos
                }
            }

            //Try move on Y axis
            tempPos = Vector2(pos)
            this.pos.add(0f, y)
            if (map.doesOverlapMap(getCollsionRect(pos))) {
                this.pos = tempPos
            } else {
            val entities = map.overlapEntities(getCollsionRect(pos))
            for (entity in entities) {
                attack(null, entity)
                this.pos = tempPos
            }
        }

            this.facing = dir
        }
    }
}