package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.databinding.MysteryMessageBinding

class MysteryMessage : AppCompatActivity() {

    private lateinit var binding: MysteryMessageBinding
    var updateViewModel = UpdateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MysteryMessageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (900.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        with(binding) {
            titleMysteryTV.text = intent.getStringExtra("Title")
            descriptionTVY.text = intent.getStringExtra("Description")

            titleTVY.setTextSize((GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())
            titleMysteryTV.setTextSize((GameActivity.companionList.scaleTextMain * 1.5f / GameActivity.companionList.screenDensity).toFloat())
            descriptionTVY.setTextSize((GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())


            endGameBTN.setOnClickListener() {
                GameActivity.paused = false
                companionList.mysteryPoints += 1
                finish()
            }
        }

        observeViewmodel()
    }

    fun observeViewmodel() {

        with(updateViewModel) {
            textBig.observe(this@MysteryMessage) { size ->
                binding.titleMysteryTV.textSize = size
            }
            textMain.observe(this@MysteryMessage) { size ->
                binding.descriptionTVY.textSize = size
                binding.titleTVY.textSize = size
            }
        }
    }
}



