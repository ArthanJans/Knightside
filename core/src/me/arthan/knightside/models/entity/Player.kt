package me.arthan.knightside.models.entity

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Direction

class Player(pos: Vector2, facing: Direction): Entity(pos, facing){
    override var name: String = "player"
    override var health: Int = 100
    override var mana: Int = 100
}