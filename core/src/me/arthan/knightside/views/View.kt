package me.arthan.knightside.views

abstract class View {
    abstract fun render(delta: Float)

    var remove = true
}