package com.agsolutions.td

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.overallXp
import com.agsolutions.td.Companion.Companion.screenDensity
import com.agsolutions.td.LogIn.HttpTask
import kotlinx.android.synthetic.main.game_end.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class GameEnd : AppCompatActivity() {
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null

    operator fun JSONArray.iterator(): Iterator<JSONObject> =
        (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_end)

        titleTVX.setTextSize(Companion.scaleTextMain/ screenDensity)
        lvlTV.setTextSize(Companion.scaleTextMain * 1.5f/ screenDensity)

        window.setLayout((600.0f * ((Companion.scaleScreen) /10)).toInt(), (400.0f * ((Companion.scaleScreen) /10)).toInt())
        window.setElevation(10F)
        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)


        lvlTV.text = level.toString()

        endGameBTN.setOnClickListener() {
            var editor = sharedPref!!.edit()
            editor.putBoolean("continueGame", false)
            editor.apply()

            GameActivity.gameEnd = 0
            mHandler.postDelayed({
                finish()
            }, 50)
        }

        initViews()

        if (isOnline(this)) {
            postData2()
            postData()
        } else {
            var date: String = Calendar.getInstance().get(
                Calendar.DAY_OF_MONTH).toString() + "." +(Calendar.getInstance().get(Calendar.MONTH)+1).toString() + "." + Calendar.getInstance().get(Calendar.YEAR).toString()
            var usernameX = sharedPref!!.getString("username", "user")

            if (mapMode == 1){
                var levelOld = sharedPref!!.getInt("GetHighscoreMap11", 0)
                if (level > levelOld){
                    var editor = sharedPref!!.edit()
                    editor.putBoolean("wasOffline", true)
                    editor.putInt("GetHighscoreMap11", level)
                    editor.putString("GetHighscoreMap11Username", usernameX)
                    editor.putString("GetHighscoreMap11Date", date)
                    editor.apply()
                }
            }else if (mapMode == 2){
                var levelOld = sharedPref!!.getInt("GetHighscoreMap12", 0)
                if (level > levelOld){
                    var editor = sharedPref!!.edit()
                    editor.putBoolean("wasOffline", true)
                    editor.putInt("GetHighscoreMap12", level)
                    editor.putString("GetHighscoreMap12Username", usernameX)
                    editor.putString("GetHighscoreMap12Date", date)
                    editor.apply()
                }
            }

            var editor = sharedPref!!.edit()
            editor.putFloat("overallxp", (sharedPref!!.getFloat("overallxp", 0f) + overallXp))
            editor.apply()
        }
    }

    override fun onBackPressed() {

    }

    private fun initViews() {
        gameEndProgressBar.visibility = View.INVISIBLE
    }

    /**     * This method is to initialize listeners     */


    private fun postData() {
        var date: String = Calendar.getInstance().get(
            Calendar.DAY_OF_MONTH).toString() + "." +(Calendar.getInstance().get(Calendar.MONTH)+1).toString() + "." + Calendar.getInstance().get(Calendar.YEAR).toString()
        var mapModeX = 0
        var usernameX = sharedPref!!.getString("username", "user")
        if (mapMode == 1) mapModeX = 11
        else mapModeX = 12
            val json = JSONObject()
            json.put("date", date)
            json.put("username", usernameX)
            json.put("highscore", level.toString())
            json.put("map", mapModeX.toString())

            gameEndProgressBar.visibility = View.VISIBLE
            HttpTask {
                gameEndProgressBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val json_res = JSONObject(it)
                if (json_res.getString("status").equals("true")) {

                } else {
                    Log.d("post Data:::::::", json_res.getString("message"))
                }
            }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/highscore.php", json.toString())
        }

    private fun postData2() {

        var usernameX = sharedPref!!.getString("username", "user")
        val json = JSONObject()
        json.put("username", usernameX)
        json.put("xp", (sharedPref!!.getFloat("overallxp", 0f) + overallXp).toString())

        gameEndProgressBar.visibility = View.VISIBLE
        HttpTask {
            gameEndProgressBar.visibility = View.INVISIBLE
            if (it == null) {
                println("connection error")
                return@HttpTask
            }
            println(it)
            val json_res = JSONObject(it)
            if (json_res.getString("status").equals("true")) {

            } else {
                Log.d("post Data:::::::", json_res.getString("message"))
            }
        }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/update_xp.php", json.toString())
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}



