package com.agsolutions.td

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.databinding.GameWikiBinding


class GameWiki : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: GameWikiBinding
    var updateViewModel = UpdateViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameWikiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((1000.0f * ((companionList.scaleScreen) /10)).toInt(), (1400.0f * ((companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
           GameActivity.paused = false
           mHandler.postDelayed({
               finish()
           }, 50)
       }

       observeViewmodel()
    }

    fun observeViewmodel() {

        with(updateViewModel) {
            textBig.observe(this@GameWiki) { size ->
                binding.textView1.textSize = size
                binding.textView2.textSize = size
                binding.textView4.textSize = size
                binding.textView6.textSize = size
                binding.textView8.textSize = size
            }
            textMain.observe(this@GameWiki) { size ->
                binding.textView3.textSize = size
                binding.textView5.textSize = size
                binding.textView7.textSize = size
                binding.textView9.textSize = size
            }
            textBig.observe(this@GameWiki) { size ->
                binding.okayBTN.textSize = size
            }
        }
    }
}