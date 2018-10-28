package me.arthan.knightside.utils

import org.json.JSONObject
import java.io.File

fun decode(filename: String): JSONObject {
    var text = File(filename).readText(Charsets.UTF_8)
    var json = JSONObject(text)
    return json
}