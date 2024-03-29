package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.Entity
import me.arthan.knightside.models.entity.Player

class PlayerView(var player: Player, var spriteBatch: Batch): EntityView() {

    override var entity: Entity = player

    private var animationSprite = AnimationSprite("Player/image1")

    override fun render(delta: Float) {
        var check = player.attack
        if (check != null) {
            if (animationSprite.attack(check, delta)) {
                player.finishAttack()
                animationSprite.update(player.getMovingDir(), 0f, false)
            }
        } else {
            check = player.hit
            if (check != null) {
                if (animationSprite.hit(check, delta)) {
                    player.finishHit()
                    if (player.dead) {
                        remove = true
                    } else {
                        animationSprite.update(player.hit, 0f, false)
                    }
                }
            } else {
                animationSprite.update(player.getMovingDir(), delta, false)
            }
        }

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), player.pos.x - animationSprite.spriteWidth / 2, player.pos.y - animationSprite.spriteHeight / 2)
        spriteBatch.end()
    }

}