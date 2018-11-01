package me.arthan.knightside

import com.badlogic.gdx.utils.JsonValue
import me.arthan.knightside.utils.decode

class ControllerLoader(var filepath: String) {
    var jsonobj = decode(filepath)


    init {
        jsonobj.getInt("")
    }
}