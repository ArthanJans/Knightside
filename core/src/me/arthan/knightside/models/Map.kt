package me.arthan.knightside.models

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.math.Rectangle
import me.arthan.knightside.models.entity.Entity
import kotlin.math.ceil
import kotlin.math.floor

class Map(override var name: String, var filename: String): Model(){

    val backgroundLayers: IntArray
    val foregroundLayers: IntArray
    val collisionLayer: Array<IntArray>

    var entities = ArrayList<Entity>()

    private val fileDir = ""
    private val fileExt = ".tmx"
    private val jsonFileExt = ".json"

    private val filePath: String
        get() = fileDir + filename + fileExt

    val map: TiledMap = TmxMapLoader().load(filePath)
    val tileWidth: Int = map.properties.get("tilewidth", Int::class.java)
    val tileHeight: Int = map.properties.get("tileheight", Int::class.java)

    //initialises background and foreground layers
    init {
        var index = 0
        var background = 0
        var foreground = 0
        for (layer in map.layers) {
            if (layer is TiledMapTileLayer) {
                if (layer.properties.containsKey("foreground")) {
                    foreground++
                } else {
                    background++
                }
            }
        }
        var foreIndex = 0
        var backIndex = 0
        foregroundLayers = IntArray(foreground)
        backgroundLayers = IntArray(background)
        for (layer in map.layers) {
            if (layer is TiledMapTileLayer) {
                if (layer.properties.containsKey("foreground")) {
                    foregroundLayers[foreIndex] = index
                    foreIndex++
                } else {
                    backgroundLayers[backIndex] = index
                    backIndex++
                }
                index++
            }
        }
    }

    //initialises collision layer
    init {
        collisionLayer = Array(map.properties.get("width", Int::class.java), {IntArray(map.properties.get("height", Int::class.java))})
        var i = 0
        for (layer in map.layers) {
            if (layer is TiledMapTileLayer) {
                for (x in 0 until layer.width) {
                    for (y in 0 until layer.height) {
                        var cell = layer.getCell(x, y)
                        if (i == 0) {
                            if (cell == null) {
                                collisionLayer[x][y] = 1
                            }
                        } else {
                            if (cell != null && layer.properties.containsKey("blocked")) {
                                collisionLayer[x][y] = 1
                            }
                        }
                    }
                }
                i++
            }
        }
    }

    fun doesOverlap(rect: Rectangle): Boolean {
        return doesOverlapMap(rect) || doesOverlapEntity(rect)
    }

    fun doesOverlapEntity(rect: Rectangle): Boolean {
        return overlapEntities(rect).size > 0
    }

    fun doesOverlapMap(rect: Rectangle): Boolean {
        for (x in floor(rect.x / 16).toInt() .. ceil(rect.x / 16).toInt()) {
            for (y in floor(rect.y / 16).toInt() .. ceil(rect.y / 16).toInt()) {
                if (collisionLayer[x][y] == 0) {
                    continue
                }
                if (Rectangle((x * tileWidth).toFloat(), (y * tileHeight).toFloat(), tileWidth.toFloat(), tileHeight.toFloat()).overlaps(rect)) {
                    return true
                }
            }
        }
        return false
    }

    fun overlapEntities(rect: Rectangle): ArrayList<Entity> {
        var list = ArrayList<Entity>()
        for (entity in entities) {
            if (entity.getCollsionRect().overlaps(rect) && entity.getCollsionRect() != rect) {
                list.add(entity)
            }
        }
        return list
    }

}