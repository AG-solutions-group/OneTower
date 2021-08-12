package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.diamonds
import com.agsolutions.td.Companion.Companion.screenDensity
import com.agsolutions.td.GameActivity.PlayPause.paused
import kotlinx.android.synthetic.main.cutting_stats_event.*

class CuttingStatsEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cutting_stats_event)

        titleTVCut.setTextSize((Companion.scaleTextMain / screenDensity).toFloat())
        descriptionTVCut.setTextSize(( Companion.scaleTextMain / screenDensity).toFloat())
        titleMysteryTVCut.setTextSize(( Companion.scaleTextMain * 1.3f / screenDensity ).toFloat())

        window.setLayout((600.0f * ((resources.getInteger(R.integer.scale).toFloat()) /10)).toInt(), (700.0f * ((resources.getInteger(R.integer.scale).toFloat()) /10)).toInt())
        window.setElevation(10F)

        endGameBTNCut.setOnClickListener() {
            diamonds += 1
            paused = false
                finish()
        }
    }

    override fun onBackPressed() {
    }
}



