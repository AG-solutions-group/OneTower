package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.databinding.ActivityPickMapBinding

class PickMap : AppCompatActivity() {
    var mHandler = Handler()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null
    private lateinit var binding: ActivityPickMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        var scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)
        var height = getResources().getDisplayMetrics().heightPixels

        window.setLayout((600.0f * (scaleScreen /10)).toInt(), (height * 0.6f).toInt())
        window.setGravity(Gravity.RIGHT)
        window.setElevation(10F)

        val retoure = intent.getIntExtra("return", 1)
        if (retoure != 0) {
            mHandler.postDelayed({
                finish()
            }, 100)
        }

        /*
        tutorialIV.setOnClickListener(){
            intent = Intent (this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 0)
            intent.putExtra("pickMode", 1)
            startActivity(intent)
            exitProcess(0)
        }

         */
        binding.mapOneIV.setOnClickListener(){
            intent = Intent (this, PickMode::class.java)
            startActivity(intent)
        }

       /* mapTwoIV.setOnClickListener(){
            intent = Intent (this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", 2)
            startActivity(intent)
            finish()
        }

        */
    }
    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}