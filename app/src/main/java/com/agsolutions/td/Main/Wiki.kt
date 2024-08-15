package com.agsolutions.td.Main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityAboutBinding
import com.agsolutions.td.databinding.GameWikiMainBinding

class Wiki : AppCompatActivity() {

    private lateinit var binding: GameWikiMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GameWikiMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.okayMainBTN.setOnClickListener() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}