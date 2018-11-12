package me.arthan.knightside.controllers

import me.arthan.knightside.models.Map

class MapController(var map: Map): Controller(){

    var controllers = ArrayList<Controller>()

    override fun update(delta: Float, map: Map) {
        var index = 0
        while (index < controllers.size) {
            var controller = controllers[index]
            controller.update(delta, map)
            if (controller.remove) {
                controllers.remove(controller)
            } else {
                index++
            }
        }
    }
}