package me.arthan.knightside.loaders

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MapView
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.EntityContainer

class MapLoader(var filepath: String) {

    var jsonobj = decode(filepath)

    var model = Map(jsonobj.getString("name"), jsonobj.getString("name"))
    var view = MapView(model)

    var player_spawn = Vector2(jsonobj.get("player_spawn").getFloat("x") * model.tileWidth + (model.tileWidth / 2), jsonobj.get("player_spawn").getFloat("y") * model.tileHeight + (model.tileHeight / 2))

    var entities = ArrayList<EntityContainer>()

    init {
        var entities = ArrayList<EntityContainer>()
        for (entity in jsonobj.get("entities")) {
            var loader = MonsterLoader(entity.getString("name") + "/" + entity.getString("name") + ".json")
            loader.model.pos = Vector2(entity.get("position").getFloat("x") * model.tileWidth + (model.tileWidth / 2), entity.get("position").getFloat("y") * model.tileHeight + (model.tileHeight / 2))
            loader.view.spriteBatch = view.renderer.batch
            entities.add(EntityContainer(loader.model, loader.view, loader.controller))
        }
    }

}