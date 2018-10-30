package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.Player

class PlayerView(var player: Player, var spriteBatch: Batch): View() {

    private var animationSprite = AnimationSprite("Player/image1")

    override fun render(delta: Float) {
        animationSprite.update(player.getMoving(), delta, true)

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), player.pos.x - 24, player.pos.y - 24)
        spriteBatch.end()
    }

}