package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.mapPick
import com.agsolutions.td.GameActivity
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.game_menu.*
import kotlinx.android.synthetic.main.pick_mode.*
import kotlin.system.exitProcess


class PickMode : AppCompatActivity() {
    companion object {
    }
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_mode)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        var scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)


        window.setLayout((600.0f * (scaleScreen / 10)).toInt(), (800.0f * (scaleScreen / 10)).toInt())
        window.setGravity(Gravity.RIGHT)
        window.setElevation(10F)

        if (level > 0 && mapPick != 0) saveAndQuitBtn.visibility = View.VISIBLE

        normalBtn.setOnClickListener(){
            intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 1)
            intent.putExtra("pickMode", 1)
            startActivity(intent)
            exitProcess(0)
        }

        circularBtn.setOnClickListener(){
            intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 1)
            intent.putExtra("pickMode", 2)
            startActivity(intent)
            exitProcess(0)
        }

                normalQBtn.setOnClickListener() {
                    modeQTV.text =
                        "You lose a life when enemies reach the portal. The game ends when you have no lives left."
                }
                circularQBtn.setOnClickListener(){
                    modeQTV.text = "Enemies circulate. The game ends when there are more than 30 enemies on the map."
                }


    }
    override fun onBackPressed() {
    }
}
