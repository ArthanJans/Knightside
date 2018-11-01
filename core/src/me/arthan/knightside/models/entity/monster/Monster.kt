package me.arthan.knightside.models.entity.monster

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction
import me.arthan.knightside.models.entity.Entity

class Monster (pos: Vector2, facing: Direction): Entity(pos, facing) {
    override var name = "Monster"
    override var health: Int = 100
    override var mana: Int = 100
    override var speed: Float = 1f
}