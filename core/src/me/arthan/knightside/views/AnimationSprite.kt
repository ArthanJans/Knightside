package me.arthan.knightside.views

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import me.arthan.knightside.models.*
import me.arthan.knightside.utils.decode
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.PI


class AnimationSprite(var filename: String) {
    val img =  "$filename.png"
    val json = "$filename.json"
    var jsonobj: JSONObject

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

        val animations = jsonobj.getJSONObject("animations")
        val idle = animations.getJSONObject("idle")
        val move = animations.getJSONObject("move")
        val attack = animations.getJSONObject("attack")
        val hit = animations.getJSONObject("hit")
        val action = animations.getJSONObject("action")

        idleLeft = readAnimation(idle.getJSONArray("side"), idle.getBoolean("flipside"))
        idleRight = readAnimation(idle.getJSONArray("side"))
        idleUp = readAnimation(idle.getJSONArray("up"))
        idleDown = readAnimation(idle.getJSONArray("down"))
        left = readAnimation(move.getJSONArray("side"), idle.getBoolean("flipside"))
        right = readAnimation(move.getJSONArray("side"))
        up = readAnimation(move.getJSONArray("up"))
        down = readAnimation(move.getJSONArray("down"))
        attackLeft = readAnimation(attack.getJSONArray("side"), idle.getBoolean("flipside"))
        attackRight = readAnimation(attack.getJSONArray("side"))
        attackUp = readAnimation(attack.getJSONArray("up"))
        attackDown = readAnimation(attack.getJSONArray("down"))
        hitLeft = readAnimation(hit.getJSONArray("side"), idle.getBoolean("flipside"))
        hitRight = readAnimation(hit.getJSONArray("side"))
        hitUp = readAnimation(hit.getJSONArray("up"))
        hitDown = readAnimation(hit.getJSONArray("down"))
        actionLeft = readAnimation(action.getJSONArray("side"), idle.getBoolean("flipside"))
        actionRight = readAnimation(action.getJSONArray("side"))
        actionUp = readAnimation(action.getJSONArray("up"))
        actionDown = readAnimation(action.getJSONArray("down"))

        current = down
    }

    private fun readAnimation(jsonarr: JSONArray, flip: Boolean = false): Animation<TextureRegion> {
        var textures = Array<TextureRegion>()
        for (i in 0 until jsonarr.length()) {
            var obj = jsonarr.getJSONObject(i)
            var textureRegion = TextureRegion(this.texture, obj.getInt("x") * this.spriteWidth, obj.getInt("y") * this.spriteHeight, this.spriteWidth, this.spriteHeight)
            textureRegion.flip(flip, false)
            for (j in 0 until (obj.getInt("ms") / 10.0).toInt()) {
                textures.add(textureRegion)
            }
        }
        var animation = Animation<TextureRegion>(0.02f, textures)
        return animation
    }

    fun update(dir: Direction?, delta: Float, direction: Boolean) {
        if (this.dir != null)
            this.previous = this.dir
        this.dir = dir
        this.animationTime += delta
        if (direction) {
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
        }
    }

    fun getRenderDir(dir: Direction?): Direction? {
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

    fun attack() {
        this.current = when (getRenderDir(this.previous)) {
            LEFT -> attackLeft
            RIGHT -> attackRight
            UP -> attackUp
            DOWN -> attackDown
            else -> attackDown
        }
        resetAnimationTime()
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