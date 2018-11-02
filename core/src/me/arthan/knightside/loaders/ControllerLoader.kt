package me.arthan.knightside.loaders

import me.arthan.knightside.utils.decode

class ControllerLoader(var filepath: String) {
    var jsonobj = decode(filepath)


    init {
        jsonobj.getInt("")
    }
}