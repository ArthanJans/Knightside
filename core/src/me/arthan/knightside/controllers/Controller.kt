package me.arthan.knightside.controllers

import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.Map

interface Controller{
    fun update(delta: Float, map: Map, entities: ArrayList<Entity>)
}