package me.arthan.knightside.controllers

import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.entity.monster.Monster
import java.util.*
import kotlin.math.PI

class MonsterController(val monster: Monster): Controller() {

    val rand = Random()

    val dirs: DoubleArray = doubleArrayOf(0.0 * PI, 0.5 * PI, 1.0 * PI, 1.5 * PI)

    override fun update(delta: Float, map: Map, entities: ArrayList<Entity>) {
        if(rand.nextInt(100) == 4) {
            monster.facing = Direction(dirs[rand.nextInt(dirs.size)])
        }
        monster.move(monster.facing, map)
    }

}

