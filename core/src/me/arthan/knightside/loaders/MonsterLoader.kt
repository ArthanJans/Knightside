package me.arthan.knightside.loaders

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.JsonValue
import me.arthan.knightside.controllers.MonsterController
import me.arthan.knightside.controllers.behaviour.RandomMoveBehaviour
import me.arthan.knightside.controllers.behaviour.moveBehaviour.MoveForward
import me.arthan.knightside.models.DOWN
import me.arthan.knightside.models.entity.monster.Monster
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MonsterView

class MonsterLoader(jsonobj: JsonValue) {

    var model: Monster = Monster(Vector2(35f * 16 + 8, 31f * 16 + 8), DOWN)
    var view: MonsterView
    var controller: MonsterController

    init {
        model.name = jsonobj.getString("name")
        model.health = jsonobj.getInt("health")
        model.maxhealth = model.health
        model.mana = jsonobj.getInt("mana")
        model.speed = jsonobj.getFloat("speed")
        view = MonsterView(model, SpriteBatch())
        controller = MonsterController(model, when(jsonobj.getString("move_behaviour")) {
            "default" -> MoveForward()
            else -> MoveForward()
        })
        for(behaviour in jsonobj.get("behaviours").asStringArray()) {
            when(behaviour) {
                "random_move" -> controller.behaviours.add(RandomMoveBehaviour())
            }
        }
    }

}