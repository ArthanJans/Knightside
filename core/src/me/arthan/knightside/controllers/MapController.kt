package me.arthan.knightside.controllers

import me.arthan.knightside.models.Map

class MapController(var map: Map): Controller(){

    var controllers = ArrayList<Controller>()

    override fun update(delta: Float, map: Map) {
        for (controller in controllers){
            controller.update(delta, map)
            if (controller.remove) {
                controllers.remove(controller)
            }
        }
    }
}