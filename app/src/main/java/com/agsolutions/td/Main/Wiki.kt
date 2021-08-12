package com.agsolutions.td.Main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.game_wiki_main.*

class Wiki : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_wiki_main)

    okayMainBTN.setOnClickListener() {
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