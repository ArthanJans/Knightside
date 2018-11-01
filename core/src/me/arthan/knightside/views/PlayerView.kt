package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.Player

class PlayerView(var player: Player, var spriteBatch: Batch): View() {

    private var animationSprite = AnimationSprite("Player/image1")

    override fun render(delta: Float) {
        animationSprite.update(player.getMoving(), delta, false)

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), player.pos.x - animationSprite.spriteWidth / 2, player.pos.y - animationSprite.spriteHeight / 2)
        spriteBatch.end()
    }

}