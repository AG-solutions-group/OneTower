package com.agsolutions.td

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tutorial_items.okayBTN
import kotlinx.android.synthetic.main.tutorial_start.*
import kotlinx.android.synthetic.main.tutorial_start.checkBoxHints
import kotlinx.android.synthetic.main.tutorial_start_display.*

class TutorialEnemies : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_enemies)


        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}

class TutorialItems : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_items)


        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setGravity(Gravity.TOP)
        window.setElevation(10F)

        okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}

class TutorialStart : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_start)


        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        checkBoxHints.isChecked = !GameActivity.companionList.hintsBool
        checkBoxHints.setOnCheckedChangeListener { _, isChecked ->
            GameActivity.companionList.hintsBool = !isChecked
        }

        okayBTN.setOnClickListener() {
            mHandler.postDelayed({
                intent = Intent(this, StartItems::class.java)
                startActivity(intent)
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}

class DisplayTutorialStart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_start_display)

        window.setLayout(600 , 900 )
        window.setElevation(10F)

        okayBTNDisplay.setOnClickListener() {
                finish()
        }

    }
    override fun onBackPressed() {
    }

}

class TutorialGameEnd : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_gameend)

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        okayBTN.setOnClickListener() {
            mHandler.postDelayed({
                GameActivity.paused = false
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}

class TutorialTalents : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_talents)


        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (900.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        checkBoxHints.setOnCheckedChangeListener { _, isChecked ->
            GameActivity.companionList.hintsBool = !isChecked
        }

        okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}

class TutorialTouchScreen : AppCompatActivity() {

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_touch_screen)


        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setGravity(Gravity.TOP)
        window.setElevation(10F)

        okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
    override fun onBackPressed() {
    }

}