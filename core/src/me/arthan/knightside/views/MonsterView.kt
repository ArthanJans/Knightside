package me.arthan.knightside.views

import com.badlogic.gdx.graphics.g2d.Batch
import me.arthan.knightside.models.entity.Player
import me.arthan.knightside.models.entity.monster.Monster

class MonsterView(var monster: Monster, var spriteBatch: Batch): View() {
    private var animationSprite = AnimationSprite("Monster/" + monster.name.toLowerCase())

    override fun render(delta: Float) {
        animationSprite.update(monster.getMoving(), delta, true)

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), monster.pos.x - 24, monster.pos.y - 24)
        spriteBatch.end()
    }
}