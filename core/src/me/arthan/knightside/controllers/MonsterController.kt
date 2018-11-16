package me.arthan.knightside.controllers

import me.arthan.knightside.controllers.behaviour.Behaviour
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.monster.Monster
import java.util.*

class MonsterController(val monster: Monster): Controller() {

    var behaviours = ArrayList<Behaviour>()

    override fun update(delta: Float, map: Map) {
        for(behaviour in behaviours) {
            behaviour.update(delta, monster, map)
        }
        if (monster.dead) {
            remove = true
            map.entities.remove(monster)
        }
    }

}

