package me.arthan.knightside.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import me.arthan.knightside.models.entity.Player

class PlayerView(var player: Player, var spriteBatch: Batch): View() {

    var texture: Texture
    var sprite: Sprite = Sprite()

    init {
        texture = Texture("Player/image1.png")
    }

    override fun render(delta: Float) {
        spriteBatch.begin()
        spriteBatch.draw(texture, player.pos.x - 24, player.pos.y - 24, 0, 0, 48, 48)
        spriteBatch.end()
    }

}