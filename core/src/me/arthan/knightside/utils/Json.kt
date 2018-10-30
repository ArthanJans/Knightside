package me.arthan.knightside.utils

import com.badlogic.gdx.Gdx
import org.json.JSONObject

fun decode(filename: String): JSONObject {
    var text = Gdx.files.internal(filename).readString()
    var json = JSONObject(text)
    return json
}