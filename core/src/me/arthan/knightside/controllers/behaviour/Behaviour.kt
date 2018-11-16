package me.arthan.knightside.controllers.behaviour

import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity

abstract class Behaviour {

    abstract fun update(delta: Float, entity: Entity, map: Map)

}