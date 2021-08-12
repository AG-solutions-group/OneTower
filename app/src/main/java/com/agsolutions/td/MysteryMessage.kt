package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.mysteryPoints
import com.agsolutions.td.GameActivity.PlayPause.paused
import kotlinx.android.synthetic.main.game_end.endGameBTN
import kotlinx.android.synthetic.main.mystery_message.*

class MysteryMessage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mystery_message)

        window.setLayout((600.0f * ((Companion.scaleScreen) /10)).toInt(), (900.0f * ((Companion.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        titleMysteryTV.text = intent.getStringExtra("Title")
        descriptionTVY.text = intent.getStringExtra("Description")

        titleTVY.setTextSize(( Companion.scaleTextMain / Companion.screenDensity).toFloat())
        titleMysteryTV.setTextSize(( Companion.scaleTextMain * 1.5f / Companion.screenDensity).toFloat())
        descriptionTVY.setTextSize(( Companion.scaleTextMain / Companion.screenDensity).toFloat())


        endGameBTN.setOnClickListener() {
            paused = false
            mysteryPoints += 1
                finish()
        }
    }
    override fun onBackPressed() {
    }
}



