package com.agsolutions.td

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.globalSoundEffects
import com.agsolutions.td.Companion.Companion.globalSoundMusic
import com.agsolutions.td.Companion.Companion.hintsBool
import kotlinx.android.synthetic.main.game_settings.*


class GameSettings : AppCompatActivity() {
    companion object {
    }
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_settings)


        window.setLayout((400.0f * ((com.agsolutions.td.Companion.scaleScreen) /10)).toInt(), (1000.0f * ((com.agsolutions.td.Companion.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        soundEffectsBar.progress = (globalSoundEffects /10).toInt()
        soundMusicBar.progress = (globalSoundMusic /10).toInt()

        soundEffectsNumberTV.text = (globalSoundEffects.toInt()).toString()
        soundMusicNumberTV.text = (globalSoundMusic.toInt()).toString()
        if (hintsBool) {
            hintsNumberTV.text = "ON"
            hintsBar.isChecked = true
        }
        else {
            hintsNumberTV.text = "OFF"
            hintsBar.isChecked = false
        }

        soundEffectsBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                soundEffectsBar.progress = i
                soundEffectsNumberTV.text = (i * 10).toString()
                globalSoundEffects = i*10.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })

        soundMusicBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                soundMusicBar.progress = i
                soundMusicNumberTV.text = (i * 10).toString()
                globalSoundMusic = i*10.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })

        hintsBar.setOnCheckedChangeListener { _, isChecked ->
            hintsBool = isChecked
            if (isChecked) hintsNumberTV.text = "ON"
            else hintsNumberTV.text = "OFF"

            var editor = sharedPref!!.edit()
            editor.putBoolean("Global Hints", hintsBool)
            editor.apply()
        }


        returnBtn.setOnClickListener(){
            var editor = sharedPref!!.edit()
            editor.putFloat("Global Music", globalSoundMusic)
            editor.putFloat("Global Effects", globalSoundEffects)
            editor.apply()

            mHandler.postDelayed({
                finish()
            }, 100)

        }


    }
    override fun onBackPressed() {
    }
}