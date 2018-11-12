package me.arthan.knightside

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.controllers.PlayerController
import me.arthan.knightside.loaders.MapLoader
import me.arthan.knightside.loaders.MonsterLoader
import me.arthan.knightside.models.DOWN
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.EntityContainer
import me.arthan.knightside.models.entity.Player
import me.arthan.knightside.views.MapView
import me.arthan.knightside.views.PlayerView

class MainScreen: Screen{

    var mapLoader = MapLoader("untitled.json")

    var player = Player(mapLoader.player_spawn, DOWN)
    var playerView = PlayerView(player, mapLoader.view.renderer.batch)
    var playerController = PlayerController(player)

    var shapeRenderer = ShapeRenderer()

//    init {
//        var monster = MonsterLoader("monster/monster.json")
//        monster.view.spriteBatch = mapLoader.view.renderer.batch
//        //TODO("Maybe change the use of EntityContainer class to just use view and controller separately")
//        monsters.add(EntityContainer(monster.model, monster.view, monster.controller))
//    }

    init {
        mapLoader.model.entities.add(player)
        mapLoader.view.views.add(playerView)
        mapLoader.controller.controllers.add(playerController)
    }

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mapLoader.view.update(Vector2(player.pos.x, player.pos.y), delta)
        mapLoader.controller.update(delta, mapLoader.view.map)
        mapLoader.view.render(delta)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color.WHITE
        shapeRenderer.rect(5.0f, 5.0f, 210.0f, 55.0f)
        shapeRenderer.color = Color.GREEN
        shapeRenderer.rect(10.0f, 35.0f, 200.0f * (player.health.toFloat() / player.maxhealth.toFloat()), 20.0f)
        shapeRenderer.color = Color.BLUE
        shapeRenderer.rect(10.0f, 10.0f, 200.0f, 20.0f)
        shapeRenderer.end()
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}