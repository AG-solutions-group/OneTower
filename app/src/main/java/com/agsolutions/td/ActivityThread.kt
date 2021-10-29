package com.agsolutions.td

import android.util.Log
import com.agsolutions.td.Fragments.EarthTalentFragment
import com.agsolutions.td.GameActivity.Companion.companionList


class ActivityThread(private val gameActivity: GameActivity) : Thread() {
    companion object {
        var runningActivity: Boolean = false
    }
    var talents = Talents ()

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long

        while (runningActivity) {
            startTime = System.nanoTime()


                try {
                 //   if (gameThreadBool) {
                        gameActivity.update()
                   //     activityThreadBool = true
                   //     gameThreadBool = false
                   // }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runningActivity = false
                }

                timeMillis = (System.nanoTime() - startTime) / 1000000
                waitTime = (GameThread.targetTime / companionList.gameSpeedAdjuster).toLong() - timeMillis

                try {
                    if (waitTime > 0) sleep(waitTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

    }

}