package me.arthan.knightside.views

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import me.arthan.knightside.models.entity.monster.Monster
import com.badlogic.gdx.graphics.Camera
import me.arthan.knightside.models.entity.Entity

class MonsterView(var monster: Monster, var spriteBatch: Batch): EntityView() {
    override var entity: Entity = monster
    private var shapeRenderer = ShapeRenderer()
    private var animationSprite = AnimationSprite("monster/" + monster.name.toLowerCase())

    override fun render(delta: Float) {
        var check = monster.hit
        if (check != null) {
            if (animationSprite.hit(check, delta)) {
                monster.finishHit()
                if (monster.dead) {
                    remove = true
                } else {
                    animationSprite.update(monster.hit, 0f, false)
                }
            }
        } else {
            animationSprite.update(monster.getMovingDir(), delta, false)
        }

        spriteBatch.begin()
        spriteBatch.draw(animationSprite.getRegion(), monster.pos.x - animationSprite.spriteWidth / 2, monster.pos.y - animationSprite.spriteHeight / 2)
        spriteBatch.end()
    }

    fun health(camera: Camera) {
        shapeRenderer.projectionMatrix = camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color.WHITE
        shapeRenderer.rect(monster.pos.x - 5f, monster.pos.y + 11f, 10f, 1.5f)
        shapeRenderer.color = Color.GREEN
        shapeRenderer.rect(monster.pos.x - 4.75f, monster.pos.y + 11.25f, 9.5f * (monster.health.toFloat() / monster.maxhealth.toFloat()), 1f)
        shapeRenderer.end()
    }
}