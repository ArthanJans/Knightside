package me.arthan.knightside.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.models.Map

class MapView(val map: Map): View() {
    val renderer = OrthogonalTiledMapRenderer(map.map)
    val camera = OrthographicCamera((Gdx.graphics.width / 4).toFloat(), (Gdx.graphics.height / 4).toFloat())
    var offsetCamera = Vector2(0f, 0f)

    var views = ArrayList<EntityView>()

    init {
        camera.position.set(offsetCamera, 0f)
        camera.update()
        renderer.setView(camera)
    }

    fun renderForeground(delta: Float) {
        renderer.render(map.foregroundLayers)
    }

    fun renderBackground(delta: Float) {
        renderer.render(map.backgroundLayers)
    }

    fun update(pos: Vector2, delta: Float) {
        offsetCamera.x = pos.x
        offsetCamera.y = pos.y

        var lerp = 0.1f
        var position = camera.position
        position.x += (offsetCamera.x - position.x) * lerp * delta
        position.y += (offsetCamera.y - position.y) * lerp * delta

        if (offsetCamera.x < camera.viewportWidth / 2) {
            offsetCamera.x = camera.viewportWidth / 2
        } else if (offsetCamera.x > (map.map.properties.get("width", Int::class.java) * 16) - (camera.viewportWidth / 2)) {
            offsetCamera.x = (map.map.properties.get("width", Int::class.java) * 16) - (camera.viewportWidth / 2)
        }

        if (offsetCamera.y < camera.viewportHeight / 2) {
            offsetCamera.y = camera.viewportHeight / 2
        } else if (offsetCamera.y > (map.map.properties.get("height", Int::class.java) * 16) - (camera.viewportHeight / 2)) {
            offsetCamera.y = (map.map.properties.get("height", Int::class.java) * 16) - (camera.viewportHeight / 2)
        }
        if (offsetCamera.x % 16 == 0f) {
            offsetCamera.x += 0.1f
        }
        if (offsetCamera.y % 16 == 0f) {
            offsetCamera.y += 0.1f
        }
        camera.position.set(offsetCamera, 0f)
        camera.update()
        renderer.setView(camera)
    }

    override fun render(delta: Float) {
        renderBackground(delta)
        var index = 0
        var newViews: ArrayList<EntityView> = views.clone() as ArrayList<EntityView>
        newViews.sortByDescending { T -> T.entity.pos.y }
        for (view in newViews) {
            view.render(delta)
            if (view.remove) {
                views.remove(view)
            }
        }
//        while (index < views.size) {
//            var view = views[index]
//            view.render(delta)
//            if (view.remove) {
//                views.remove(view)
//            } else {
//                index ++
//            }
//        }
        renderForeground(delta)
        index = 0
        while (index < views.size) {
            var view = views[index]
            if (view is MonsterView) {
                view.health(camera)
            }
            index ++
        }
    }
}