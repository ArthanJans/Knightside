package me.arthan.knightside.controllers.behaviour

import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.entity.Entity
import java.util.*
import kotlin.math.PI

class RandomMoveBehaviour: Behaviour() {

    val rand = Random()

    val dirs: DoubleArray = doubleArrayOf(0.0 * PI, 0.5 * PI, 1.0 * PI, 1.5 * PI)

    override fun update(delta: Float, entity: Entity) {
        if(rand.nextInt(100) == 4) {
            entity.facing = Direction(dirs[rand.nextInt(dirs.size)])
        }
    }

}