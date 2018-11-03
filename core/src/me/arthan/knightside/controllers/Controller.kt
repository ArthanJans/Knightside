package me.arthan.knightside.controllers

import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.Entity

abstract class Controller{
    abstract fun update(delta: Float, map: Map)
}