package me.arthan.knightside.models

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.math.Rectangle
import kotlin.math.ceil
import kotlin.math.floor

class Map(override var name: String, var filename: String): Model{

    val map = TmxMapLoader().load(filePath)
    val tileWidth = map.properties.get("tilewidth", Int::class.java)
    val tileHeight = map.properties.get("tileheight", Int::class.java)

    val backgroundLayers: Array<Int?>
    val foregroundLayers: Array<Int?>
    val collisionLayer: Array<Array<Int?>>

    private val fileDir = ""
    private val fileExt = ".tmx"

    val filePath: String
        get() = fileDir + filename + fileExt

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
        foregroundLayers = arrayOfNulls(foreground)
        backgroundLayers = arrayOfNulls(background)
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
        collisionLayer = arrayOf(arrayOfNulls(map.properties.get("width", Int::class.java)), arrayOfNulls(map.properties.get("height", Int::class.java)))
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
        for (x in floor(rect.x).toInt() .. ceil(rect.x).toInt()) {
            for (y in floor(rect.y).toInt() .. ceil(rect.y).toInt()) {
                if (collisionLayer[x][y] == 0) {
                    continue
                }
                if (Rectangle(x * tileWidth as Float, y * tileHeight as Float, tileWidth, tileHeight).overlaps(rect)) {
                    return true
                }
            }
        }

        return false
    }

}