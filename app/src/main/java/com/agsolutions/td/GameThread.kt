package com.agsolutions.td

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder
import kotlinx.coroutines.InternalCoroutinesApi

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) :
    Thread() {
    companion object {
        var running: Boolean = false
        private var canvas: Canvas? = null
        var targetTime = 0L
    }
    private val targetFPS =
        60  // frames per second, the rate at which you would like to refresh the Canvas
    val targetTimeMax = (1000 / targetFPS ).toLong()

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long

        while (running) {
            if (targetTime < targetTimeMax) targetTime = targetTimeMax
            startTime = System.nanoTime()

                try {
                    canvas = null
                    if (running && surfaceHolder.surface.isValid){ //&& activityThreadBool) {
                        // locking the canvas allows us to draw on to it
                        canvas = this.surfaceHolder.lockCanvas()
                        if (canvas != null) {
                            this.gameView.draw(canvas!!)


                            surfaceHolder.unlockCanvasAndPost(canvas)
                    //        activityThreadBool = false
                    //        gameThreadBool = true

                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    running = false
                }

                timeMillis = (System.nanoTime() - startTime) / 1000000
                waitTime = targetTime - timeMillis

                if (waitTime > 0) targetTime-- else targetTime++

                try {
                    if (waitTime > 0) sleep(waitTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }


}