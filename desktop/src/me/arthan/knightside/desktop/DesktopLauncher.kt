package me.arthan.knightside.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import me.arthan.knightside.Knightside

fun main (args: Array<String>) {
    var config = LwjglApplicationConfiguration()
    config.height = 720
    config.width = 1080
    LwjglApplication(Knightside(), config)
}
