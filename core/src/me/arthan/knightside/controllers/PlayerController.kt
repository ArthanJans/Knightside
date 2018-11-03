package me.arthan.knightside.controllers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import me.arthan.knightside.models.*
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Player


class PlayerController(val player: Player): Controller() {

    override fun update(delta: Float, map: Map) {
        var attack = Gdx.input.isKeyPressed(Input.Keys.SPACE)
        var dir = keyboardInput()
        if (attack) {
            player.attack(dir, map)
        } else {
            player.move(dir, map)
        }

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