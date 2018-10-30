package me.arthan.knightside

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import me.arthan.knightside.controllers.MonsterController
import me.arthan.knightside.controllers.PlayerController
import me.arthan.knightside.models.Map
import me.arthan.knightside.models.UP
import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.entity.Player
import me.arthan.knightside.models.entity.monster.Monster
import me.arthan.knightside.views.MonsterView
import me.arthan.knightside.views.MapView
import me.arthan.knightside.views.PlayerView

class MainScreen: Screen{
    var mapView = MapView(Map("Main", "untitled"))
    var player = Player(Vector2(35f * 16 + 8, 31f * 16 + 8), UP)
    var playerView = PlayerView(player, mapView.renderer.batch)
    var playerController = PlayerController(player)
    var monster = Monster(Vector2(35f * 16 + 8, 31f * 16 + 8), UP)
    var monsterView = MonsterView(monster, mapView.renderer.batch)
    var monsterController = MonsterController(monster)
    var entities = ArrayList<Entity>()

    init {

    }

    init {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mapView.update(Vector2(player.pos.x, player.pos.y), delta)
        playerController.update(delta, mapView.map, entities)
        monsterController.update(delta, mapView.map, entities)

        mapView.renderBackground(delta)
        playerView.render(delta)
        monsterView.render(delta)
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