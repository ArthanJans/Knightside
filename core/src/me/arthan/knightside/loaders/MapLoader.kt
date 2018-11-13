package me.arthan.knightside.loaders

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.JsonValue
import me.arthan.knightside.controllers.MapController
import me.arthan.knightside.models.Map
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MapView

class MapLoader(filepath: String) {

    var jsonobj = decode(filepath)

    var model = Map(jsonobj.getString("name"), jsonobj.getString("name"))
    var view = MapView(model)
    var controller = MapController(model)

    var monsters = HashMap<String, JsonValue>()

    var player_spawn = Vector2(jsonobj.get("player_spawn").getFloat("x") * model.tileWidth + (model.tileWidth / 2), jsonobj.get("player_spawn").getFloat("y") * model.tileHeight + (model.tileHeight / 2))

    init {
        for (monster in jsonobj.get("entities")) {
            spawnEntity(monster.getString("name"), monster.getFloat("x"), monster.getFloat("y"))
        }
    }

    fun spawnEntity(name: String, x: Float, y: Float) {
        val loader: MonsterLoader = if(monsters.containsKey(name)) {
            MonsterLoader(monsters[name]!!)
        } else {
            var newjson = decode("monster/$name.json")
            val monster = MonsterLoader(newjson)
            monsters[name] = newjson
            monster

        }
        var entity = loader.model
        var entityView = loader.view
        var entityController = loader.controller
        entity.pos = Vector2(x * model.tileWidth + (model.tileWidth / 2), y * model.tileHeight + (model.tileHeight / 2))
        entityView.spriteBatch = view.renderer.batch
        model.entities.add(entity)
        view.views.add(entityView)
        controller.controllers.add(entityController)
    }

}