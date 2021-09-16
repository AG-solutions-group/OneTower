package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity.Companion.companionList

import kotlinx.android.synthetic.main.game_end.endGameBTN
import kotlinx.android.synthetic.main.mystery_message.*

class MysteryMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mystery_message)

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (900.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        titleMysteryTV.text = intent.getStringExtra("Title")
        descriptionTVY.text = intent.getStringExtra("Description")

        titleTVY.setTextSize(( GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())
        titleMysteryTV.setTextSize(( GameActivity.companionList.scaleTextMain * 1.5f / GameActivity.companionList.screenDensity).toFloat())
        descriptionTVY.setTextSize(( GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())


        endGameBTN.setOnClickListener() {
            GameActivity.paused = false
            companionList.mysteryPoints += 1
                finish()
        }
    }
    override fun onBackPressed() {
    }
}



