package me.arthan.knightside.controllers.behaviour

import me.arthan.knightside.models.DIRS
import me.arthan.knightside.models.entity.Entity
import java.util.*

class RandomMoveBehaviour: Behaviour() {

    val rand = Random()

    override fun update(delta: Float, entity: Entity) {
        if(rand.nextInt(100) == 4) {
            entity.facing = DIRS[rand.nextInt(DIRS.size)]
        }
    }

}