package com.agsolutions.td

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import kotlinx.android.synthetic.main.tutorial_items.*


class GameWiki : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_wiki)


        window.setLayout((1000.0f * ((companionList.scaleScreen) /10)).toInt(), (1400.0f * ((companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

       okayBTN.setOnClickListener() {
           GameActivity.paused = false
           mHandler.postDelayed({
               finish()
           }, 50)
       }

    }
    override fun onBackPressed() {
    }

}