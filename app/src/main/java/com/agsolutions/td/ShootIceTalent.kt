package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint


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
            shard.y -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 1.5) {
            shard.x += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 3.0) {
            shard.x += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 4.5) {
            shard.x += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 6.0) {
            shard.y += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 7.5) {
            shard.x -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y += (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 9.0) {
            shard.x -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }
        if (direction == 10.5) {
            shard.x -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
            shard.y -= (GameActivity.companionList.shardSpeed * GameActivity.companionList.gameSpeedAdjuster)
        }


        GameActivity.companionList.shootListIce.forEach() {
                if (it.shard.y > (GameActivity.companionList.shardStart.y + 400) || it.shard.y <= (GameActivity.companionList.shardStart.y - 400) || it.shard.x > (GameActivity.companionList.shardStart.x + 400) || it.shard.x <= (GameActivity.companionList.shardStart.x - 400)) {
                    shard.x = GameActivity.companionList.shardStart.x
                    shard.y = GameActivity.companionList.shardStart.y
                    hit = false
                    color = Color.WHITE

            }
        }

    }
}
