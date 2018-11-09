package me.arthan.knightside.controllers.behaviour.moveBehaviour

import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity

abstract class MoveBehaviour {

    abstract fun update(delta: Float, entity: Entity, map: Map)

}