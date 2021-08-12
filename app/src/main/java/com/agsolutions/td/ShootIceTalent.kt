package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.agsolutions.td.Companion.Companion.shardSpeed
import com.agsolutions.td.Companion.Companion.shardStart
import com.agsolutions.td.Companion.Companion.shootListIce

class ShootIceTalent (var direction: Double) {

    var paint: Paint
    var shard = TowerRadius(600.0f, 750.0f, 10.0f)

    val towerRadiusReal: TowerRadius = TowerRadius(1.0f, 1.0f, 1.0f)
    var count = 0
    var color = Color.WHITE
    var hit = false



    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = color


    }

    fun draw(canvas: Canvas) {

        canvas.drawCircle(shard.x.toFloat(), shard.y.toFloat(), shard.r.toFloat(), paint)

    }

    fun update() {

        paint.color = color

        if (direction == 0.0) {
            shard.y -= (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 1.5) {
            shard.x += (shardSpeed * Companion.gameSpeedAdjuster)
            shard.y -= (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 3.0) {
            shard.x += (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 4.5) {
            shard.x += (shardSpeed * Companion.gameSpeedAdjuster)
            shard.y += (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 6.0) {
            shard.y += (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 7.5) {
            shard.x -= (shardSpeed * Companion.gameSpeedAdjuster)
            shard.y += (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 9.0) {
            shard.x -= (shardSpeed * Companion.gameSpeedAdjuster)
        }
        if (direction == 10.5) {
            shard.x -= (shardSpeed * Companion.gameSpeedAdjuster)
            shard.y -= (shardSpeed * Companion.gameSpeedAdjuster)
        }


        shootListIce.forEach() {
                if (it.shard.y > (shardStart.y + 400) || it.shard.y <= (shardStart.y - 400) || it.shard.x > (shardStart.x + 400) || it.shard.x <= (shardStart.x - 400)) {
                    shard.x = shardStart.x
                    shard.y = shardStart.y
                    hit = false
                    color = Color.WHITE

            }
        }

    }
}
