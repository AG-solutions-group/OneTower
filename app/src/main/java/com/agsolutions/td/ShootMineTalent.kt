package com.agsolutions.td

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.agsolutions.td.Companion.Companion.mapPick

class ShootMineTalent {
    companion object {

    }
    var paint: Paint
    var mineRadius = TowerRadius(600.0f, 750.0f, 30.0f)
    var newMine = true
    var broken = false
    var minePicCounter = 0
    var mineNextPic = 0

    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = Color.parseColor("#653F05")

    }

    fun draw(canvas: Canvas) {

        canvas.drawCircle(mineRadius.x.toFloat(), mineRadius.y.toFloat(), mineRadius.r.toFloat(), paint)

    }

    fun update() {

        if (mapPick == 1) {
            if (!newMine){

            }else {
                newMine = false
                when ((0..3).random()){
                    0 -> {
                        mineRadius.y = 1000f
                        mineRadius.x = (200..800).random().toFloat()
                    }
                    1 -> {
                        mineRadius.x = 200f
                        mineRadius.y = (500..1000).random().toFloat()
                    }
                    2 -> {
                        mineRadius.y = 500f
                        mineRadius.x = (200..800).random().toFloat()
                    }
                    3 -> {
                        mineRadius.x = 800f
                        mineRadius.y = (500..1000).random().toFloat()
                    }

                }
            }
        }
    }
}
