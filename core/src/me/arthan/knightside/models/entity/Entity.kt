package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.Model
import me.arthan.knightside.models.opposite
import java.time.LocalTime
import kotlin.math.cos
import kotlin.math.sin

open abstract class Entity(var pos: Vector2, var facing: Direction): Model() {

    abstract var health: Int
    abstract var maxhealth: Int
    abstract var mana: Int

    var moving = false
    abstract var speed: Float
    var attack: Direction? = null
    var hit: Direction? = null

    fun move(dir: Direction?, map: Map) {
        if (dir == null || attack != null  || hit != null) {
            moving = false
        } else {
            moving = true

            var x = cos(dir.radians).toFloat() * this.speed
            var y = -1 * sin(dir.radians).toFloat() * this.speed

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
        return Rectangle(pos.x - 5, pos.y - 5, 10f, 10f)
    }

    fun attack(dir: Direction?, map: Map) {
        moving = false
        if (attack == null) {
            println(LocalTime.now())
            if (dir != null) {
                facing = dir
                attack = dir
            } else {
                attack = facing
            }
            val atk = this.attack
            if (atk != null) {
                var hitRange = Vector2(pos)
                var x = cos(atk.radians).toFloat() * 16
                var y = -1 * sin(atk.radians).toFloat() * 16
                hitRange.add(x, y)
                var entities = map.overlapEntities(getCollsionRect(hitRange))

                for (entity in entities) {
                    if (entity != this) {
                        entity.hit(1, opposite(atk))
                    }
                }
            }
        }
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
        println(LocalTime.now())
        println("" + this.name + " got hit")
        this.health -= damage
        if (this.health <= 0){
            this.die()
        } else {
            this.hit = dir
            this.facing = dir
        }

    }

    fun finishHit() {
        this.hit = null
    }

    fun die() {
        println("" + this.name + " died")
    }


}