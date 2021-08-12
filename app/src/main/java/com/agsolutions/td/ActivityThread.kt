package com.agsolutions.td

import com.agsolutions.td.Companion.Companion.gameSpeedAdjuster
import com.agsolutions.td.Fragments.EarthTalentFragment


class ActivityThread(private val gameActivity: GameActivity) : Thread() {
    companion object {
        var runningActivity: Boolean = false
    }
    var talents = Talents ()
    var talentsEarth = EarthTalentFragment()

    private val targetFPS =
        60  * gameSpeedAdjuster// frames per second, the rate at which you would like to refresh the Canvas

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        var targetTime: Long

        while (runningActivity) {
            targetTime = (1000 / (targetFPS * gameSpeedAdjuster)).toLong()
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
                waitTime = targetTime - timeMillis

                try {
                    if (waitTime > 0) sleep(waitTime)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

    }

}