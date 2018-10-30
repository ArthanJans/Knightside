package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.Player
import me.arthan.knightside.models.entity.monster.Monster

class MonsterView(var monster: Monster, var spriteBatch: Batch): View() {
    private var animationSprite = AnimationSprite("Monster/image1")

    override fun render(delta: Float) {
        animationSprite.update(monster.getMoving(), delta, true)

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), monster.pos.x - animationSprite.spriteWidth / 2, monster.pos.y - animationSprite.spriteHeight / 2)
        spriteBatch.end()
    }
}