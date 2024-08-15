package com.agsolutions.td

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.databinding.TutorialEnemiesBinding
import com.agsolutions.td.databinding.TutorialGameendBinding
import com.agsolutions.td.databinding.TutorialItemsBinding
import com.agsolutions.td.databinding.TutorialStartBinding
import com.agsolutions.td.databinding.TutorialStartDisplayBinding
import com.agsolutions.td.databinding.TutorialTalentsBinding
import com.agsolutions.td.databinding.TutorialTouchScreenBinding


class TutorialEnemies : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialEnemiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialEnemiesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
}

class TutorialItems : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setGravity(Gravity.TOP)
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
}

class TutorialStart : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        with(binding) {
            checkBoxHints.isChecked = !GameActivity.companionList.hintsBool
            checkBoxHints.setOnCheckedChangeListener { _, isChecked ->
                GameActivity.companionList.hintsBool = !isChecked
            }

            okayBTN.setOnClickListener() {
                mHandler.postDelayed({
                    intent = Intent(this@TutorialStart, StartItems::class.java)
                    startActivity(intent)
                    finish()
                }, 50)
            }
        }
    }
}

class DisplayTutorialStart : AppCompatActivity() {

    private lateinit var binding: TutorialStartDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialStartDisplayBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout(600 , 900 )
        window.setElevation(10F)

        binding.okayBTNDisplay.setOnClickListener() {
                finish()
        }
    }
}

class TutorialGameEnd : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialGameendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialGameendBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
            mHandler.postDelayed({
                GameActivity.paused = false
                finish()
            }, 50)
        }
    }
}

class TutorialTalents : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialTalentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialTalentsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((600.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (900.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        binding.checkBoxHints.setOnCheckedChangeListener { _, isChecked ->
            GameActivity.companionList.hintsBool = !isChecked
        }

        binding.okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
}

class TutorialTouchScreen : AppCompatActivity() {

    var mHandler = Handler ()
    private lateinit var binding: TutorialTouchScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialTouchScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        window.setLayout((1000.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt(), (1400.0f * ((GameActivity.companionList.scaleScreen) /10)).toInt())
        window.setGravity(Gravity.TOP)
        window.setElevation(10F)

        binding.okayBTN.setOnClickListener() {
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }

    }
}