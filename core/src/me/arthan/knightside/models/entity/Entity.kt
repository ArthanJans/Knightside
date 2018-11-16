package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Polygon
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.Model
import me.arthan.knightside.models.opposite
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

open abstract class Entity(var pos: Vector2, var facing: Direction): Model() {

    abstract var health: Int
    abstract var maxhealth: Int
    abstract var mana: Int

    var moving = false
    var knockback = 0f
    abstract var speed: Float
    var attack: Direction? = null
    var hit: Direction? = null

    var dead = false


    fun move(dir: Direction?, map: Map) {
        if (dir == null || attack != null) {
            moving = false
        } else {
            moving = true

            var x = cos(dir.radians).toFloat() * this.speed
            var y = -1 * sin(dir.radians).toFloat() * this.speed

            if (knockback != 0f) {
                x *= -1 * knockback / this.speed
                y *= -1 * knockback / this.speed
                knockback -= 0.1f
                if (knockback < 0f) {
                    knockback = 0f
                }
            }

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

            this.facing = dir
        }
    }

    fun getCollsionRect(pos: Vector2 = this.pos): Rectangle {
        return Rectangle(pos.x - 5, pos.y - 7, 10f, 10f)
    }

    fun attack(dir: Direction?, map: Map) {
        moving = false
        if (attack == null) {
            if (dir != null) {
                facing = dir
                attack = dir
            } else {
                attack = facing
            }
            val atk = this.attack
            if (atk != null) {
                val entities = map.overlapEntitiesPolygon(getAttackPolygon(16f, 16f))

                for (entity in entities) {
                    if (entity != this) {
                        entity.hit(1, opposite(atk))
                    }
                }
            }
        }
    }

    private fun getAttackPolygon(width: Float, range: Float): Polygon {
        val polygon = Polygon()
        polygon.setPosition(pos.x, pos.y)
        polygon.vertices = floatArrayOf(0f, width/2, range, width/2, range, -1f * width/2, 0f, -1f * width/2)
        val deg = attack?.degrees
        if (deg != null) {
            polygon.rotate(-1f * deg.toFloat())
        }
        return polygon
    }

    fun finishAttack() {
        attack = null
    }

    fun getMovingDir(): Direction? {
        return if (moving) {
            facing
        } else {
            null
        }
    }

    fun hit(damage: Int, dir: Direction) {
        this.health -= damage
        if (this.health <= 0) {
            this.die()
        }

        this.hit = dir
        this.facing = dir
        this.knockback = 2f


    }

    fun finishHit() {
        this.hit = null
    }

    fun die() {
        dead = true
    }


}