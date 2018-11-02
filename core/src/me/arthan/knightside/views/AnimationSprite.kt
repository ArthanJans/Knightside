package me.arthan.knightside.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.JsonValue
import me.arthan.knightside.models.*
import me.arthan.knightside.utils.decode
import kotlin.math.PI


class AnimationSprite(var filename: String) {
    val img =  "$filename.png"
    val json = "$filename.json"
    var jsonobj: JsonValue

    var dir: Direction?
    var previous: Direction?

    var animationTime: Float

    var texture: Texture

    var spriteWidth: Int
    var spriteHeight: Int

    var idleLeft: Animation<TextureRegion>
    var idleRight: Animation<TextureRegion>
    var idleUp: Animation<TextureRegion>
    var idleDown: Animation<TextureRegion>
    var left: Animation<TextureRegion>
    var right: Animation<TextureRegion>
    var up: Animation<TextureRegion>
    var down: Animation<TextureRegion>
    var attackLeft: Animation<TextureRegion>
    var attackRight: Animation<TextureRegion>
    var attackUp: Animation<TextureRegion>
    var attackDown: Animation<TextureRegion>
    var hitLeft: Animation<TextureRegion>
    var hitRight: Animation<TextureRegion>
    var hitUp: Animation<TextureRegion>
    var hitDown: Animation<TextureRegion>
    var actionLeft: Animation<TextureRegion>
    var actionRight: Animation<TextureRegion>
    var actionUp: Animation<TextureRegion>
    var actionDown: Animation<TextureRegion>

    var current: Animation<TextureRegion>

    init {
        texture = Texture(img)
        dir = null
        previous = null
        animationTime = 0f

        jsonobj = decode(json)
        spriteWidth = jsonobj.getInt("width")
        spriteHeight = jsonobj.getInt("height")

        val animations = jsonobj.require("animations")
        val idle = animations.require("idle")
        val move = animations.require("move")
        val attack = animations.require("attack")
        val hit = animations.require("hit")
        val action = animations.require("action")

        idleLeft = readAnimation(idle.require("side"), idle.getBoolean("flipside"))
        idleRight = readAnimation(idle.require("side"))
        idleUp = readAnimation(idle.require("up"))
        idleDown = readAnimation(idle.require("down"))
        left = readAnimation(move.require("side"), idle.getBoolean("flipside"))
        right = readAnimation(move.require("side"))
        up = readAnimation(move.require("up"))
        down = readAnimation(move.require("down"))
        attackLeft = readAnimation(attack.require("side"), idle.getBoolean("flipside"))
        attackRight = readAnimation(attack.require("side"))
        attackUp = readAnimation(attack.require("up"))
        attackDown = readAnimation(attack.require("down"))
        hitLeft = readAnimation(hit.require("side"), idle.getBoolean("flipside"))
        hitRight = readAnimation(hit.require("side"))
        hitUp = readAnimation(hit.require("up"))
        hitDown = readAnimation(hit.require("down"))
        actionLeft = readAnimation(action.require("side"), idle.getBoolean("flipside"))
        actionRight = readAnimation(action.require("side"))
        actionUp = readAnimation(action.require("up"))
        actionDown = readAnimation(action.require("down"))

        current = down
    }

    private fun readAnimation(jsonarr: JsonValue, flip: Boolean = false): Animation<TextureRegion> {
        var textures = Array<TextureRegion>()
        for (i in 0 until jsonarr.size) {
            var obj = jsonarr.require(i)
            var textureRegion = TextureRegion(this.texture, obj.getInt("x") * this.spriteWidth, obj.getInt("y") * this.spriteHeight, this.spriteWidth, this.spriteHeight)
            textureRegion.flip(flip, false)
            for (j in 0 until (obj.getInt("ms") / 10.0).toInt()) {
                textures.add(textureRegion)
            }
        }
        return Animation(0.02f, textures)
    }

    fun update(dir: Direction?, delta: Float, short: Boolean): Boolean {
        if (this.dir != null)
            this.previous = this.dir
        this.dir = dir
        this.animationTime += delta
        if (short) {
            return current.isAnimationFinished(animationTime)
        }
        current = when (getRenderDir(dir)) {
            LEFT -> left
            RIGHT -> right
            DOWN -> down
            UP -> up
            else -> {
                when (getRenderDir(this.previous)) {
                    LEFT -> idleLeft
                    RIGHT -> idleRight
                    DOWN -> idleDown
                    UP -> idleUp
                    else -> idleDown
                }
            }
        }
        return true
    }

    private fun getRenderDir(dir: Direction?): Direction? {
        return if (dir == null){
            null
        } else {
            return when (dir.radians) {
                in PI/4 .. 3*PI/4 -> DOWN
                in 5*PI/4 .. 7*PI/4 -> UP
                in 3*PI/4 .. 5*PI/4 -> LEFT
                else -> RIGHT
            }
        }
    }

    fun getRegion(): TextureRegion {
        return current.getKeyFrame(animationTime, true) as TextureRegion
    }

    fun resetAnimationTime() {
        this.animationTime = 0f
    }

    fun hit(dir: Direction) {
        this.current = when (getRenderDir(dir)) {
            LEFT -> hitLeft
            RIGHT -> hitRight
            UP -> hitUp
            DOWN -> hitDown
            else -> hitDown
        }
        resetAnimationTime()
    }

    fun attack(dir: Direction, delta: Float): Boolean {
        if (! isAttack()) {
            this.current = when (getRenderDir(dir)) {
                LEFT -> attackLeft
                RIGHT -> attackRight
                UP -> attackUp
                DOWN -> attackDown
                else -> attackDown
            }
            resetAnimationTime()
        }
        return update(null, delta, true)

    }

    fun isAttack(): Boolean {
        return (this.current == attackLeft || this.current == attackRight || this.current == attackUp || this.current == attackDown)
    }

    fun action() {
        this.current = when (getRenderDir(this.previous)) {
            LEFT -> actionLeft
            RIGHT -> actionRight
            UP -> actionUp
            DOWN -> actionDown
            else -> actionDown
        }
        resetAnimationTime()
    }


}