package com.agsolutions.td.Main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.*
import com.agsolutions.td.Companion.Companion.overallXp
import com.agsolutions.td.LogIn.HttpTask
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_display_scale.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.quitBTN
import kotlinx.android.synthetic.main.activity_start_items_menu.*
import kotlinx.android.synthetic.main.game_end.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    companion object {
        var username = "placeholder"
        var userLevel = 0
        var startScreenBool = false
        var firstDisplayScale = 0
    }

    var sharedPrefZ: SharedPreferences? = null
    private var PRIVATE_MODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startScreenBool = true
        com.agsolutions.td.Companion.screenDensity = getResources().getDisplayMetrics().density

        sharedPrefZ = getSharedPreferences("Global", PRIVATE_MODE)
        com.agsolutions.td.Companion.scaleScreen = sharedPrefZ!!.getFloat("ScaleBackground", 10f)

        var id = intent.getIntExtra("id", 0)
        if (id > 0) {
            var editor = sharedPrefZ!!.edit()
            editor.putInt("id", id)
            editor.apply()
        }
        id = sharedPrefZ!!.getInt("id", 0)

        firstDisplayScale = sharedPrefZ!!.getInt("firstStart", 1)
        var continueGameZ = sharedPrefZ!!.getBoolean("continueGame", false)

        if (continueGameZ) {
            continueGameBTN.visibility = View.VISIBLE
        }

        if (isOnline(this)) {

            progressMainBar.visibility = View.VISIBLE

            if (sharedPrefZ!!.getBoolean("wasOffline", false)) {
                var editor = sharedPrefZ!!.edit()
                editor.putBoolean("wasOffline", false)
                editor.apply()
                postData()
                postData2()
            }
            if ( (sharedPrefZ!!.getInt("hasNewItems", 0)) == 1) {
                var editor = sharedPrefZ!!.edit()
                editor.putInt("hasNewItems", 2)
                editor.apply()
                postData3()
            }

            HttpTask {
                progressMainBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val json_res = JSONObject(it)
                if (json_res.getString("status").equals("true")) {
                    var userdata = User(0, "username", "email", 0.0)
                    var jsonArray = JSONArray(json_res.getString("data"))
                    for (i in 0..(jsonArray.length() - 1)) {
                        val item = jsonArray.getJSONObject(i)
                        userdata.id = item.getInt("id")
                        userdata.username = item.getString("username")
                        userdata.email = item.getString("email")
                        userdata.xp = item.getDouble("xp")
                    }
                    Log.d("userdata Data:::::::", userdata.toString())
                    showUserTV.text = userdata.username
                    overallXp = userdata.xp.toFloat()
                    username = userdata.username.toString()
                    var editor = sharedPrefZ!!.edit()
                    editor.putString("username", username)
                    editor.putFloat("overallxp", overallXp)
                    editor.apply()
                    initViews()

                    Log.d("xponline", overallXp.toString() )

                } else {
                    Log.d("post Data:::::::", json_res.getString("message"))
                    Snackbar.make(findViewById(R.id.snackbar), json_res.getString("message") + id, Snackbar.LENGTH_LONG)
                        .show()
                }
            }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_detail.php?id=" + id)

        } else {
            showUserTV.text = sharedPrefZ!!.getString("username", "user")
            overallXp = sharedPrefZ!!.getFloat("overallxp", 0f)
            username = sharedPrefZ!!.getString("username", "user").toString()
            initViews()
        }
    }

    private fun postData() {

        val json = JSONObject()
        json.put("date", sharedPrefZ!!.getString("GetHighscoreMap11Date", "0"))
        json.put("username", sharedPrefZ!!.getString("GetHighscoreMap11Username", "user"))
        json.put("highscore", sharedPrefZ!!.getInt("GetHighscoreMap11", 0).toString())
        json.put("map", 11).toString()
        HttpTask {
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

        json.put("date", sharedPrefZ!!.getString("GetHighscoreMap12Date", "0"))
        json.put("username", sharedPrefZ!!.getString("GetHighscoreMap12Username", "user"))
        json.put("highscore", sharedPrefZ!!.getInt("GetHighscoreMap12", 0).toString())
        json.put("map", 12).toString()
        HttpTask {
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

        var usernameX = sharedPrefZ!!.getString("username", "user")
        val json = JSONObject()
        json.put("username", usernameX)
        json.put("xp", sharedPrefZ!!.getFloat("overallxp", 0f).toString())

        HttpTask {
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

    private fun postData3() {

        var usernameX = sharedPrefZ!!.getString("username", "user")

        var textFile = File("$filesDir/itemList.dat")
        var fis = FileInputStream(textFile)
        var ois = ObjectInputStream(fis)
        var itemListPlace = ois.readObject() as ArrayList<Int>
        var itemListPost = ArrayList<Int>()
        for (itemid in itemListPlace) {
            itemListPost.add(itemid)
        }

        itemListPost.forEach() {
            val json = JSONObject()
            json.put("username", usernameX)
            json.put("itemid", it.toString())

            HttpTask {
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
            }.execute("POST", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/start_items.php", json.toString())
        }
    }

    override fun onBackPressed() {

    }

    private fun initViews() {

        if (firstDisplayScale == 1) {
            newGameBtn.visibility = View.INVISIBLE
            highScoreBTN.visibility = View.INVISIBLE
            startItemsBTN.visibility = View.INVISIBLE
            aboutBTN.visibility = View.INVISIBLE
            wikiBTN.visibility = View.INVISIBLE
        } else {
            newGameBtn.visibility = View.VISIBLE
            highScoreBTN.visibility = View.VISIBLE
            startItemsBTN.visibility = View.VISIBLE
            aboutBTN.visibility = View.VISIBLE
            wikiBTN.visibility = View.VISIBLE
        }

        progressMainBar.visibility = View.INVISIBLE




        when (overallXp.toInt()) {
            in 0..1500 -> {
                userLevel = 0
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 0
                    userLevelProgressBar.max = 1500
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 1501..4000 -> {
                userLevel = 1
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 1501
                    userLevelProgressBar.max = 4000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 4001..9000 -> {
                userLevel = 2
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 4001
                    userLevelProgressBar.max = 9000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 9001..15000 -> {
            userLevel = 3
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                userLevelProgressBar.min = 9001
                userLevelProgressBar.max = 15000
                userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 15001..25000 -> {
                userLevel = 4
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 15001
                    userLevelProgressBar.max = 25000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 25001..60000 -> {
                userLevel = 5
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 25001
                    userLevelProgressBar.max = 60000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 60001..125000 -> {
                userLevel = 6
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 60001
                    userLevelProgressBar.max = 125000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 125001..300000 -> {
                userLevel = 7
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 125001
                    userLevelProgressBar.max = 300000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 300001..625000 -> {
                userLevel = 8
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 300001
                    userLevelProgressBar.max = 625000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 625001..1500000 -> {
                userLevel = 9
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 625001
                    userLevelProgressBar.max = 1500000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 1500001..3125000 -> {
                userLevel = 10
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 1500001
                    userLevelProgressBar.max = 3125000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 3125001..8000000 -> {
                userLevel = 11
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 3125001
                    userLevelProgressBar.max = 8000000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 8000001..15625000 -> {
                userLevel = 12
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 8000001
                    userLevelProgressBar.max = 15625000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 15625001..40000000 -> {
                userLevel = 13
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 15625001
                    userLevelProgressBar.max = 40000000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 40000001..781250000 -> {
                userLevel = 14
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 40000001
                    userLevelProgressBar.max = 78125000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
            in 78125001..390625000 -> {
                userLevel = 15
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    userLevelProgressBar.min = 78125001
                    userLevelProgressBar.max = 390625000
                    userLevelProgressBar.progress = overallXp.toInt()
                }
            }
        }

        var editor = sharedPrefZ!!.edit()
        editor.putInt("userLevel", userLevel)
        editor.apply()

        showUserLevelTV.text = userLevel.toString()

        continueGameBTN.setOnClickListener() {
            var mapPickX = sharedPrefZ!!.getInt("continueGameMapPick", 0)
            var mapModeX = sharedPrefZ!!.getInt("continueGameMapMode", 1)
            intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("pickMap", mapPickX)
            intent.putExtra("pickMode", mapModeX)
            intent.putExtra("LoadGame", true)
            startActivity(intent)
            exitProcess(0)
        }

        newGameBtn.setOnClickListener() {
            intent = Intent(this, PickMap::class.java)
            intent.putExtra("return", 0)
            startActivity(intent)
        }
        highScoreBTN.setOnClickListener() {
            intent = Intent(this, Highscore::class.java)
            startActivity(intent)
            exitProcess(0)
        }
        startItemsBTN.setOnClickListener() {
            intent = Intent(this, StartItemsMenu::class.java)
            intent.putExtra("userLevel", userLevel)
            startActivity(intent)
            exitProcess(0)
        }
        wikiBTN.setOnClickListener() {
            intent = Intent(this, Wiki::class.java)
            startActivity(intent)
            exitProcess(0)
        }
        aboutBTN.setOnClickListener() {
            intent = Intent(this, About::class.java)
            startActivity(intent)
            exitProcess(0)
        }
        quitBTN.setOnClickListener() {
            this.finishAffinity()
        }
        scaleBTN.setOnClickListener() {
            intent = Intent(this, DisplayScale::class.java)
            startActivity(intent)
            exitProcess(0)
        }

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