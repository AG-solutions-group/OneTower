package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.agsolutions.td.GameView.Companion.paintPoisonUlti
import java.io.Serializable


class ShootPoisonTalent : Serializable {
    companion object {
        var bulletSpeed: Float = 12.0F


    }
    var poisonCloud = TowerRadius(600.0f, 750.0f, 50.0f)
    var poisonCloudPosition = 0
    var broken = 0
    var poisonPicCounter = 0
    var poisonNextPic = 0

    init {

    }

    fun draw(canvas: Canvas) {

        canvas.drawCircle(poisonCloud.x.toFloat(), poisonCloud.y.toFloat(), poisonCloud.r.toFloat(), paintPoisonUlti)

    }

    fun update() {

            if (GameActivity.companionList.enemyList.isNotEmpty()) {

                if (poisonCloudPosition == 2) {
                    poisonCloud.x += 0
                    poisonCloud.y += 0
                } else {
                        var enemy = GameActivity.companionList.enemyList.last()

                        fun xSpeed(): Int {
                            var speed = 0
                            when (enemy.circleXMovement) {
                                "xminus" -> speed = (enemy.speed.toInt()) * (-1)
                                "xplus" -> speed = (enemy.speed.toInt())
                            }
                            return speed
                        }

                        fun ySpeed(): Int {
                            var speed = 0
                            when (enemy.circleYMovement) {
                                "yminus" -> speed = (enemy.speed.toInt()) * (-1)
                                "yplus" -> speed = (enemy.speed.toInt())
                            }
                            return speed
                        }

                        var nx =
                            if (poisonCloud.x > (enemy.circle!!.x + xSpeed())) ((poisonCloud.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed ))
                            else (((enemy.circle!!.x + xSpeed().toFloat()) - poisonCloud.x) / (bulletSpeed ))
                        var ny =
                            if (poisonCloud.y > (enemy.circle!!.y + xSpeed())) ((poisonCloud.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed ))
                            else (((enemy.circle!!.y + ySpeed().toFloat()) - poisonCloud.y) / (bulletSpeed ))

                        var n =
                            if (nx > ny) (bulletSpeed ) / nx
                            else (bulletSpeed ) / ny

                        if (cross(enemy)) {
                            poisonCloudPosition = 2
                        } else {
                            if (poisonCloud.x > (enemy.circle!!.x + xSpeed())) {
                                poisonCloud.x -= (nx * n)
                            } else {
                                poisonCloud.x += (nx * n)
                            }

                            if (poisonCloud.y > (enemy.circle!!.y + xSpeed())) {
                                poisonCloud.y -= (ny * n)
                            } else {
                                poisonCloud.y += (ny * n)
                            }
                        }
                }
            } else {
                broken = 1
            }

    }

    fun cross (it: Enemy) : Boolean {
        var x= false
        val distanceX = (poisonCloud.x) - it.circle!!.x
        val distanceY = (poisonCloud.y) - it.circle!!.y

        var squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

        val sumOfRadius = (poisonCloud.r - 30) + it.circle!!.r

        if (squaredDistance <= sumOfRadius * sumOfRadius) x = true

        return x
    }
}
