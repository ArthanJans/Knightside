package me.arthan.knightside.views

import me.arthan.knightside.models.entity.Entity

abstract class EntityView: View() {
    abstract var entity: Entity
}