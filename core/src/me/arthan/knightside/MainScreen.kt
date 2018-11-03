package me.arthan.knightside

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.controllers.PlayerController
import me.arthan.knightside.loaders.MonsterLoader
import me.arthan.knightside.models.DOWN
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.entity.EntityContainer
import me.arthan.knightside.models.entity.Player
import me.arthan.knightside.views.MapView
import me.arthan.knightside.views.PlayerView

class MainScreen: Screen{
    var mapView = MapView(Map("Main", "untitled"))
    var player = Player(Vector2(35f * 16 + 8, 28f * 16 + 8), DOWN)
    var playerView = PlayerView(player, mapView.renderer.batch)
    var playerController = PlayerController(player)

    var monsters = ArrayList<EntityContainer>()

    init {
        mapView.map.entities.add(player)
        var monster = MonsterLoader("Monster/monster.json")
        monster.view.spriteBatch = mapView.renderer.batch
        //TODO("Maybe change the use of EntityContainer class to just use view and controller separately")
        monsters.add(EntityContainer(monster.model, monster.view, monster.controller))
        mapView.map.entities.add(monster.model)
    }

    init {
//        var controller = ControllerLoader("controller.json")
    }

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mapView.update(Vector2(player.pos.x, player.pos.y), delta)
        playerController.update(delta, mapView.map)
        for(entity in monsters) {
            entity.controller.update(delta, mapView.map)
        }

        mapView.renderBackground(delta)
        playerView.render(delta)
        for(monster in monsters) {
            monster.view.render(delta)
        }
        mapView.renderForeground(delta)
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