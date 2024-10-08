package com.agsolutions.td

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.databinding.GameSettingsBinding


class GameSettings : AppCompatActivity() {
    companion object {
    }
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null

    private lateinit var binding: GameSettingsBinding
    var updateViewModel = UpdateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        window.setLayout((400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)

        with(binding) {
            soundEffectsBar.progress = (GameActivity.companionList.globalSoundEffects / 10).toInt()
            soundMusicBar.progress = (GameActivity.companionList.globalSoundMusic / 10).toInt()

            soundEffectsNumberTV.text =
                (GameActivity.companionList.globalSoundEffects.toInt()).toString()
            soundMusicNumberTV.text =
                (GameActivity.companionList.globalSoundMusic.toInt()).toString()
            if (GameActivity.companionList.hintsBool) {
                hintsNumberTV.text = "ON"
                hintsBar.isChecked = true
            } else {
                hintsNumberTV.text = "OFF"
                hintsBar.isChecked = false
            }

            soundEffectsBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    // Display the current progress of SeekBar
                    soundEffectsBar.progress = i
                    soundEffectsNumberTV.text = (i * 10).toString()
                    GameActivity.companionList.globalSoundEffects = i * 10.toFloat()
                    GameActivity.companionList.musicChanged = true
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
                    GameActivity.companionList.globalSoundMusic = i * 10.toFloat()
                    GameActivity.companionList.musicChanged = true
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Do something
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Do something
                }
            })

            hintsBar.setOnCheckedChangeListener { _, isChecked ->
                GameActivity.companionList.hintsBool = isChecked
                if (isChecked) hintsNumberTV.text = "ON"
                else hintsNumberTV.text = "OFF"

                var editor = sharedPref!!.edit()
                editor.putBoolean("Global Hints", GameActivity.companionList.hintsBool)
                editor.apply()
            }


            returnBtn.setOnClickListener() {
                var editor = sharedPref!!.edit()
                editor.putFloat("Global Music", GameActivity.companionList.globalSoundMusic)
                editor.putFloat("Global Effects", GameActivity.companionList.globalSoundEffects)
                editor.apply()

                mHandler.postDelayed({
                    finish()
                }, 100)

            }
        }

        observeViewmodel()
    }


    fun observeViewmodel() {

        with(updateViewModel) {
            textButton.observe(this@GameSettings) { size ->
                binding.returnBtn.textSize = size
            }

            textMain.observe(this@GameSettings) { size ->
                binding.soundMusicTV.textSize = size
                binding.soundMusicNumberTV.textSize = size
                binding.soundEffectsTV.textSize = size
                binding.soundEffectsNumberTV.textSize = size
                binding.hintsTV.textSize = size
                binding.hintsNumberTV.textSize = size

            }
        }
    }
}