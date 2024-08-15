package com.agsolutions.td.Main

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Fragments.OverallHighscoreFragment
import com.agsolutions.td.Fragments.OverallHighscoreFragmentModeTwo
import com.agsolutions.td.Fragments.PersonalHighscoreFragment
import com.agsolutions.td.Fragments.PersonalHighscoreFragmentModeTwo
import com.agsolutions.td.R
import com.agsolutions.td.databinding.ActivityHighscoreBinding

class Highscore : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    companion object {
        var personalHighscoreList = mutableListOf<HighscoreAtributes>()
        var overallHighscoreList = mutableListOf<HighscoreAtributes>()
        var personalHighscoreListModeTwo = mutableListOf<HighscoreAtributes>()
        var overallHighscoreListModeTwo = mutableListOf<HighscoreAtributes>()
        var usernameX = "user"
    }

    val overallHighscore = OverallHighscoreFragment ()
    val personalHighscore = PersonalHighscoreFragment ()
    val overallHighscoreModeTwo = OverallHighscoreFragmentModeTwo ()
    val personalHighscoreModeTwo = PersonalHighscoreFragmentModeTwo ()
    var sharedPrefZ: SharedPreferences? = null
    private var PRIVATE_MODE = 0
    private lateinit var binding: ActivityHighscoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHighscoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPrefZ = getSharedPreferences("Global", PRIVATE_MODE)
        usernameX = sharedPrefZ!!.getString("username", "user").toString()

        with(binding) {

            when {
                !switchMapMode.isChecked && !switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, overallHighscore)
                        commit()
                    }
                }

                switchMapMode.isChecked && !switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, overallHighscoreModeTwo)
                        commit()
                    }
                }

                !switchMapMode.isChecked && switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, personalHighscore)
                        commit()
                    }
                }

                switchMapMode.isChecked && switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, personalHighscoreModeTwo)
                        commit()
                    }
                }

                else -> {}
            }

        switchMapMode.setOnCheckedChangeListener(this@Highscore)
        switchOverall.setOnCheckedChangeListener(this@Highscore)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        with(binding) {
            when {
                !switchMapMode.isChecked && !switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, overallHighscore)
                        commit()
                    }
                }

                switchMapMode.isChecked && !switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, overallHighscoreModeTwo)
                        commit()
                    }
                }

                !switchMapMode.isChecked && switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, personalHighscore)
                        commit()
                    }
                }

                switchMapMode.isChecked && switchOverall.isChecked -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.highscoreFragment, personalHighscoreModeTwo)
                        commit()
                    }
                }

                else -> {}
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}

data class HighscoreAtributes (var username: String, var level: Int, var date: String)