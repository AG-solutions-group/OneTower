package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityAboutBinding
import com.agsolutions.td.databinding.PickModeBinding
import kotlin.system.exitProcess


class PickMode : AppCompatActivity() {
    companion object {
    }
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null
    private lateinit var binding: PickModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PickModeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        var scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)


        window.setLayout((600.0f * (scaleScreen / 10)).toInt(), (800.0f * (scaleScreen / 10)).toInt())
        window.setGravity(Gravity.RIGHT)
        window.setElevation(10F)

        binding.normalBtn.setOnClickListener(){
            intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 1)
            intent.putExtra("pickMode", 1)
            startActivity(intent)
            exitProcess(0)
        }

        binding.circularBtn.setOnClickListener(){
            intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 1)
            intent.putExtra("pickMode", 2)
            startActivity(intent)
            exitProcess(0)
        }

        with(binding) {
            normalQBtn.setOnClickListener() {
                modeQTV.text =
                    "You lose a life when enemies reach the portal. The game ends when you have no lives left."
            }
            circularQBtn.setOnClickListener() {
                modeQTV.text =
                    "Enemies circle. The game ends when there are more than 30 enemies on the map."
            }

        }
    }
}
