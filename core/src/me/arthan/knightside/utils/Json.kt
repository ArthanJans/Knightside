package me.arthan.knightside.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue

fun decode(filename: String): JsonValue {
    val text = Gdx.files.internal(filename).readString()
    return JsonReader().parse(text)
}