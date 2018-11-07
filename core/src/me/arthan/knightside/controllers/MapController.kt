package me.arthan.knightside.controllers

import me.arthan.knightside.models.Map

class MapController(): Controller(){

    var controllers = ArrayList<Controller>()

    override fun update(delta: Float, map: Map) {
        for (controller in controllers){
            controller.update(delta, map)
        }
    }
}