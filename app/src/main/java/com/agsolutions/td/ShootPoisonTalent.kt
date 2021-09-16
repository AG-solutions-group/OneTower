package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class ShootPoisonTalent {
    companion object {
        var bulletSpeed: Float = 12.0F


    }
    var paint: Paint
    var poisonCloud = TowerRadius(600.0f, 750.0f, 50.0f)
    var poisonCloudPosition = 0
    var broken = 0
    var poisonPicCounter = 0
    var poisonNextPic = 0

    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = Color.GREEN


    }

    fun draw(canvas: Canvas) {

        canvas.drawCircle(poisonCloud.x.toFloat(), poisonCloud.y.toFloat(), poisonCloud.r.toFloat(), paint)

    }

    fun update() {

        GameActivity.companionList.readLockEnemy.lock ()
        try {
            var enemyListIterator = GameActivity.companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var enemy = enemyListIterator.next()

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
                    if (poisonCloud.x > (enemy.circle!!.x + xSpeed())) ((poisonCloud.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.x + xSpeed().toFloat()) - poisonCloud.x) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                var ny =
                    if (poisonCloud.y > (enemy.circle!!.y + xSpeed())) ((poisonCloud.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))
                    else (((enemy.circle!!.y + ySpeed().toFloat()) - poisonCloud.y) / (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster))

                var n =
                    if (nx > ny) (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / nx
                    else (bulletSpeed * GameActivity.companionList.gameSpeedAdjuster) / ny


                if (cross(enemy)){
                    poisonCloudPosition = 2
                }else if (poisonCloudPosition == 2){
                    poisonCloud.x += 0
                    poisonCloud.y += 0
                }else {
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
                    break


                }
            }
        }finally {
            GameActivity.companionList.readLockEnemy.unlock()
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
