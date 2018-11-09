package me.arthan.knightside.controllers.behaviour.moveBehaviour

import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity

class MoveForward: MoveBehaviour() {

    override fun update(delta: Float, entity: Entity, map: Map) {
        entity.move(entity.facing, map)
    }
}