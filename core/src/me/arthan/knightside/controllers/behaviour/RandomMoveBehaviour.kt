package me.arthan.knightside.controllers.behaviour

import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity
import java.util.*

class RandomMoveBehaviour: Behaviour() {

    val rand = Random()

    override fun update(delta: Float, entity: Entity, map: Map) {
        if(rand.nextInt(100) == 4) {
            entity.facing = Direction(rand.nextInt(360))
        }
        entity.move(entity.facing, map)
    }

}