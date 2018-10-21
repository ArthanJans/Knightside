package me.arthan.knightside.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import me.arthan.knightside.Knightside

fun main (args: Array<String>) {
    var config = LwjglApplicationConfiguration()
    LwjglApplication(Knightside(), config)
}
