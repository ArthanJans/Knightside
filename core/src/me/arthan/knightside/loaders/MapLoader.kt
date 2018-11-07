package me.arthan.knightside.loaders

import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.utils.decode
import me.arthan.knightside.views.MapView
import me.arthan.knightside.models.Map

class MapLoader(var filepath: String) {

    var jsonobj = decode(filepath)

    var model = Map(jsonobj.getString("name"), jsonobj.getString("name"))
    var view = MapView(model)

    var player_spawn = Vector2(jsonobj.get("player_spawn").getFloat("x") * model.tileWidth + (model.tileWidth / 2), jsonobj.get("player_spawn").getFloat("y") * model.tileHeight + (model.tileHeight / 2))

}