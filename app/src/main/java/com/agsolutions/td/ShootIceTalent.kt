package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


class ShootIceTalent () {

    var direction = 0f
    var paint: Paint
    var shard = TowerRadius(600.0f, 750.0f, 10.0f)
    var shardStart = TowerRadius (600.0f, 750.0f, 5.0f)

    var count = 0
    var color = Color.WHITE
    var iceShardTowerId = 0
    var shardSpeed: Float = 3.0F
    var shardSpeedReduce = 0.5f
    var shardSpeedBasic = 3f

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

        if (direction == 0.0f) {
            shard.y -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 1.5f) {
            shard.x += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 3.0f) {
            shard.x += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 4.5f) {
            shard.x += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 6.0f) {
            shard.y += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 7.5f) {
            shard.x -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y += (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 9.0f) {
            shard.x -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 10.5f) {
            shard.x -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y -= (shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }

        if (shard.y > (shardStart.y + 400) || shard.y <= (shardStart.y - 400) || shard.x > (shardStart.x + 400) || shard.x <= (shardStart.x - 400)) {
                    shard.x = shardStart.x
                    shard.y = shardStart.y
        }
    }
}
