package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.cutting_stats_event.*

class CuttingStatsEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cutting_stats_event)

        titleTVCut.setTextSize((GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())
        descriptionTVCut.setTextSize(( GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())
        titleMysteryTVCut.setTextSize(( GameActivity.companionList.scaleTextMain * 1.3f / GameActivity.companionList.screenDensity ).toFloat())

        window.setLayout((600.0f * ((resources.getInteger(R.integer.scale).toFloat()) /10)).toInt(), (700.0f * ((resources.getInteger(R.integer.scale).toFloat()) /10)).toInt())
        window.setElevation(10F)

        endGameBTNCut.setOnClickListener() {
            GameActivity.companionList.diamonds += 1
            GameActivity.paused = false
                finish()
        }
    }

    override fun onBackPressed() {
    }
}



