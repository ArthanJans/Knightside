package me.arthan.knightside.controllers

import me.arthan.knightside.controllers.behaviour.Behaviour
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.entity.monster.Monster
import java.util.*
import kotlin.math.PI

class MonsterController(val monster: Monster): Controller() {

    var behaviours = ArrayList<Behaviour>()

    override fun update(delta: Float, map: Map, entities: ArrayList<Entity>) {
        for(behaviour in behaviours) {
            behaviour.update(delta, monster)
        }
        monster.move(monster.facing, map)
    }

}
