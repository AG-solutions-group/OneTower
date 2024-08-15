package com.agsolutions.td.Main

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityAboutBinding
import com.agsolutions.td.databinding.ActivityLogInScreenBinding

class About : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var screenwidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        var screenheight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()


        window.setLayout((screenwidth * 0.7).toInt(), (screenheight* 0.7).toInt())
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}