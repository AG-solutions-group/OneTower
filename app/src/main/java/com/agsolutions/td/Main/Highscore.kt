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
import kotlinx.android.synthetic.main.activity_highscore.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        sharedPrefZ = getSharedPreferences("Global", PRIVATE_MODE)
        usernameX = sharedPrefZ!!.getString("username", "user").toString()

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
        }

        switchMapMode.setOnCheckedChangeListener(this)
        switchOverall.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
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
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}

data class HighscoreAtributes (var username: String, var level: Int, var date: String){

}

/*
HttpTask {
        //    progressHighscoreBar.visibility = View.INVISIBLE
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val json_res = JSONObject(it)
            if (json_res.getString("status").equals("true")) {
                var jsonArray = JSONArray(json_res.getString("data"))
                for (i in 0..(jsonArray.length()-1)) {
                    var item = jsonArray.getJSONObject(i)
                    var username = item.getString("username")
                    var highscore = item.getString("highscore")
                    var date = item.getString("date")
                    Highscore.personalHighscore.add(HighscoreAtributes(username, highscore, date ))
                }

                Log.d("userdata Data:::::::", "worked")

            } else {
                Log.d("post Data:::::::", json_res.getString("message"))
            }
        }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore.php?username="+MainActivity.username)
 */
