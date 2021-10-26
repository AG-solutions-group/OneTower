package com.agsolutions.td.Main

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.tutorial_items.*

class About : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var screenwidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        var screenheight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()


        window.setLayout((screenwidth * 0.7).toInt(), (screenheight* 0.7).toInt())
        window.setElevation(10F)

        okayBTN.setOnClickListener() {
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}