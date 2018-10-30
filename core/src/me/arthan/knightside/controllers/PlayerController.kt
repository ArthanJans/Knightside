package me.arthan.knightside.controllers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import me.arthan.knightside.models.*
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.entity.Player


class PlayerController(val player: Player): Controller() {

    override fun update(delta: Float, map: Map, entities: ArrayList<Entity>) {
        var dir = keyboardInput()
        player.move(dir, map)

    }

    fun keyboardInput(): Direction?{
        var dir: Direction? = null
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dir = add(dir, LEFT)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dir = add(dir, RIGHT)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dir = add(dir, DOWN)
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dir = add(dir, UP)
        }
        return dir
    }
}