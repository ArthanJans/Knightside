package me.arthan.knightside.controllers

import me.arthan.knightside.models.Map

abstract class Controller{
    abstract fun update(delta: Float, map: Map)

    var remove = false
}