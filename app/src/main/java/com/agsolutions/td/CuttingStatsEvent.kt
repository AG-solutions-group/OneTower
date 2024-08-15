package com.agsolutions.td

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.databinding.CuttingStatsEventBinding

class CuttingStatsEvent : AppCompatActivity() {

    private lateinit var binding: CuttingStatsEventBinding
    var updateViewModel = UpdateViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CuttingStatsEventBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.titleTVCut.setTextSize((GameActivity.companionList.scaleTextMain * 1.3f / GameActivity.companionList.screenDensity).toFloat())
        binding.titleMysteryTVCut.setTextSize(( GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity).toFloat())
        binding.descriptionTVCut.setTextSize(( GameActivity.companionList.scaleTextMain / GameActivity.companionList.screenDensity ).toFloat())

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (700.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        binding.endGameBTNCut.setOnClickListener() {
            if (GameActivity.companionList.mapMode != 2) GameActivity.companionList.lives += 5
            else GameActivity.companionList.livesMode2 += 5
            GameActivity.paused = false
                finish()
        }
        observeViewmodel()
    }

    fun observeViewmodel() {

        with(updateViewModel) {
            textBig.observe(this@CuttingStatsEvent) { size ->
                binding.titleTVCut.setTextSize(size)
            }
            textMain.observe(this@CuttingStatsEvent) { size ->
                binding.titleMysteryTVCut.setTextSize(size)
                binding.descriptionTVCut.setTextSize(size)
            }
        }
    }
}



