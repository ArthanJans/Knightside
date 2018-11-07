package me.arthan.knightside.loaders

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.controllers.MapController
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MapView
import me.arthan.knightside.models.Map

class MapLoader(filepath: String) {

    var jsonobj = decode(filepath)

    var model = Map(jsonobj.getString("name"), jsonobj.getString("name"))
    var view = MapView(model)
    var controller = MapController(model)

    var player_spawn = Vector2(jsonobj.get("player_spawn").getFloat("x") * model.tileWidth + (model.tileWidth / 2), jsonobj.get("player_spawn").getFloat("y") * model.tileHeight + (model.tileHeight / 2))

    init {
        for (monster in jsonobj.get("entities")) {
            var loader = MonsterLoader("Monster/" + monster.getString("name") + ".json")
            var entity = loader.model
            var entityView = loader.view
            var entityController = loader.controller
            entity.pos = Vector2(monster.getFloat("x") * model.tileWidth + (model.tileWidth / 2), monster.getFloat("y") * model.tileHeight + (model.tileHeight / 2))
            entityView.spriteBatch = view.renderer.batch
            model.entities.add(entity)
            view.views.add(entityView)
            controller.controllers.add(entityController)
        }
    }
}