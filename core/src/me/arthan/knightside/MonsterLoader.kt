package me.arthan.knightside

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.JsonValue
import me.arthan.knightside.controllers.MonsterController
import me.arthan.knightside.models.UP
import me.arthan.knightside.models.entity.monster.Monster
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MonsterView

class MonsterLoader(var filepath: String) {

    var jsonobj: JsonValue

    var model: Monster
    var view: MonsterView
    var controller: MonsterController

    init {
        jsonobj = decode(filepath)
        model = Monster(Vector2(35f * 16 + 8, 31f * 16 + 8), UP)
        model.name = jsonobj.getString("name")
        view = MonsterView(model, SpriteBatch())
        controller = MonsterController(model)
    }

}