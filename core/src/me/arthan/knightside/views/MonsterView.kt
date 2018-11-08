package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.monster.Monster

class MonsterView(var monster: Monster, var spriteBatch: Batch): View() {
    private var animationSprite = AnimationSprite("monster/" + monster.name.toLowerCase())

    override fun render(delta: Float) {
        var check = monster.hit
        if (check != null) {
            if (animationSprite.hit(check, delta)) {
                monster.finishHit()
                animationSprite.update(monster.getMovingDir(), 0f, false)
            }
        } else {
            animationSprite.update(monster.getMovingDir(), delta, false)
        }

        if (monster.dead) {
            remove = true
        } else {
            spriteBatch.begin()
            spriteBatch.draw(animationSprite.getRegion(), monster.pos.x - animationSprite.spriteWidth / 2, monster.pos.y - animationSprite.spriteHeight / 2)
            spriteBatch.end()
        }
    }
}