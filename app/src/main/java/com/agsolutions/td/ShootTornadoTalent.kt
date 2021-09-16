package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class ShootTornadoTalent {
    companion object {
        var bulletSpeed: Float = 12.0F


    }
    var paint: Paint
    var tornadoRadius = TowerRadius(600.0f, 750.0f, 5f)
    var tornadoRadiusPosition = 0
    var tornadoCount = 0
    var broken = 0
    var randomEnemyTornadoBool = true
    var randomEnemyTornado = Enemy (0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0)

    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = Color.WHITE
    }

    fun draw(canvas: Canvas) {

        canvas.drawCircle(tornadoRadius.x.toFloat(), tornadoRadius.y.toFloat(), tornadoRadius.r.toFloat(), paint)

    }

    fun update() {

        GameActivity.companionList.readLockEnemy.lock()
        try {
            if (tornadoRadiusPosition == 2) {
                tornadoRadius.x += 0
                tornadoRadius.y += 0
            } else if (randomEnemyTornadoBool) {
                randomEnemyTornadoBool = false
                randomEnemyTornado = GameActivity.companionList.enemyList.random()
                movement(randomEnemyTornado)
            } else if (!randomEnemyTornadoBool && GameActivity.companionList.enemyList.isNotEmpty()) movement(randomEnemyTornado)
            else broken = 1

        } finally {
            GameActivity.companionList.readLockEnemy.unlock()
        }
    }


    fun movement (enemy: Enemy){

                fun xSpeed(): Int {
                    var speed = 0
                    when (enemy.passed) {
                        1.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                        2.toByte() -> speed = 0
                        3.toByte() -> speed = (enemy.speed.toInt())
                        4.toByte() -> speed = 0
                    }
                    return speed
                }

                fun ySpeed(): Int {
                    var speed = 0
                    when (enemy.passed) {
                        1.toByte() -> speed = 0
                        2.toByte() -> speed = (enemy.speed.toInt()) * (-1)
                        3.toByte() -> speed = 0
                        4.toByte() -> speed = (enemy.speed.toInt())
                    }
                    return speed
                }

                var nx =
                    if (tornadoRadius.x > (enemy.circle!!.x + xSpeed())) ((tornadoRadius.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.x + xSpeed().toFloat()) - tornadoRadius.x) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                var ny =
                    if (tornadoRadius.y > (enemy.circle!!.y + xSpeed())) ((tornadoRadius.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.y + ySpeed().toFloat()) - tornadoRadius.y) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))

                var n =
                    if (nx > ny) (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / nx
                    else (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / ny



                if (cross(enemy)){
                    tornadoRadiusPosition = 2
                }else {
                    if (tornadoRadius.x > (enemy.circle!!.x + xSpeed())) {
                        tornadoRadius.x -= (nx * n)
                    } else {
                        tornadoRadius.x += (nx * n)
                    }

                    if (tornadoRadius.y > (enemy.circle!!.y + ySpeed())) {
                        tornadoRadius.y -= (ny * n)
                    } else {
                        tornadoRadius.y += (ny * n)
                    }
                }
            }

    fun cross (it: Enemy) : Boolean {
        var x= false
        val distanceX = (tornadoRadius.x) - it.circle!!.x
        val distanceY = (tornadoRadius.y) - it.circle!!.y

        var squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

        val sumOfRadius = 10

        if (squaredDistance <= sumOfRadius * sumOfRadius) {
            x = true
        }
        return x
    }
}
