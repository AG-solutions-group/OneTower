package com.agsolutions.td.Main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.*
import com.agsolutions.td.LogIn.HttpTask
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_display_scale.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.circularQBtn
import kotlinx.android.synthetic.main.activity_main.normalQBtn
import kotlinx.android.synthetic.main.activity_main.quitBTN
import kotlinx.android.synthetic.main.activity_start_items_menu.*
import kotlinx.android.synthetic.main.game_end.*
import kotlinx.android.synthetic.main.pick_mode.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.math.atan2
import kotlin.random.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    companion object {
        var username = "placeholder"
        var userLevel = 0
        var startScreenBool = false
        var firstDisplayScale = 0
        var screenDensity = 0f
        var scaleScreen = 0f
        var overallXp = 0f
        var startScreenTowerBool = true
        var startScreenTimerTower = 0
        var towerList = mutableListOf<Tower>()
        var enemyCount = 0
        var enemyList = mutableListOf<EnemyStartScreen>()
        var shootList = mutableListOf<ShootStartScreen>()
        var rotationTowerX = 0f
        var rotationTowerY = 0f
        var rotationEnemyX = 0f
        var rotationEnemyY = 0f
    }

    var pickAMap = false
    var sharedPrefZ: SharedPreferences? = null
    private var PRIVATE_MODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startScreenBool = true
        screenDensity = resources.displayMetrics.density

        sharedPrefZ = getSharedPreferences("Global", PRIVATE_MODE)
        scaleScreen = sharedPrefZ!!.getFloat("ScaleBackground", 10f)

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

            HttpTask {
                progressMainBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val json_res = JSONObject(it)
                if (json_res.getString("status").equals("true")) {
                    var userdata = User(0, "username",  0.0)
                    var jsonArray = JSONArray(json_res.getString("data"))
                    for (i in 0..(jsonArray.length() - 1)) {
                        val item = jsonArray.getJSONObject(i)
                        userdata.id = item.getInt("id")
                        userdata.username = item.getString("username")
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

    override fun onBackPressed() {

    }

    private fun initViews() {

        if (firstDisplayScale == 1) {
            newGameBtn.visibility = View.INVISIBLE
            highScoreBTN.visibility = View.INVISIBLE
            startItemsBTN.visibility = View.INVISIBLE
            aboutBTN.visibility = View.INVISIBLE
            wikiBTN.visibility = View.INVISIBLE
            normalQBtn.visibility = View.INVISIBLE
            circularQBtn.visibility = View.INVISIBLE
        } else {
            newGameBtn.visibility = View.VISIBLE
            highScoreBTN.visibility = View.VISIBLE
            startItemsBTN.visibility = View.VISIBLE
            aboutBTN.visibility = View.VISIBLE
            wikiBTN.visibility = View.VISIBLE
            normalQBtn.visibility = View.INVISIBLE
            circularQBtn.visibility = View.INVISIBLE
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
            if (pickAMap){
                    intent = Intent(this, GameActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("pickMap", 1)
                    intent.putExtra("pickMode", 1)
                    startActivity(intent)
                    exitProcess(0)
            }else{
               pickAMap = true
                newGameBtn.setBackgroundResource(R.drawable.startbuttonnormal)
                startItemsBTN.setBackgroundResource(R.drawable.startbuttoncircular)
                highScoreBTN.setBackgroundResource(R.drawable.startbuttoncancel)

                normalQBtn.visibility = View.VISIBLE
                circularQBtn.visibility = View.VISIBLE

                aboutBTN.visibility = View.INVISIBLE
                wikiBTN.visibility = View.INVISIBLE
                quitBTN.visibility = View.INVISIBLE
                scaleBTN.visibility = View.INVISIBLE
            }
        }
        startItemsBTN.setOnClickListener() {
            if (pickAMap){
                intent = Intent(this, GameActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("pickMap", 1)
                intent.putExtra("pickMode", 2)
                startActivity(intent)
                exitProcess(0)
            }else {
                intent = Intent(this, StartItemsMenu::class.java)
                intent.putExtra("userLevel", userLevel)
                startActivity(intent)
                exitProcess(0)
            }
        }

        normalQBtn.setOnClickListener() {
            Toast.makeText(this, "You lose a life when enemies reach the portal. The game ends when you have no lives left.",Toast.LENGTH_LONG).show()
        }
        circularQBtn.setOnClickListener(){
            Toast.makeText(this,"Enemies circle. The game ends when there are more than 30 enemies on the map.",Toast.LENGTH_LONG).show()
        }

        highScoreBTN.setOnClickListener() {
            if (pickAMap){
                pickAMap = false
                newGameBtn.setBackgroundResource(R.drawable.startbuttonnewgame)
                startItemsBTN.setBackgroundResource(R.drawable.startbuttonitems)
                highScoreBTN.setBackgroundResource(R.drawable.startbuttonscore)

                normalQBtn.visibility = View.INVISIBLE
                circularQBtn.visibility = View.INVISIBLE

                aboutBTN.visibility = View.VISIBLE
                wikiBTN.visibility = View.VISIBLE
                quitBTN.visibility = View.VISIBLE
                scaleBTN.visibility = View.VISIBLE

            } else {
                intent = Intent(this, Highscore::class.java)
                startActivity(intent)
                exitProcess(0)
            }
        }
        wikiBTN.setOnClickListener() {
            Toast.makeText(this,"Coming Soon.",Toast.LENGTH_LONG).show()
        }
        aboutBTN.setOnClickListener() {
            intent = Intent(this, About::class.java)
            startActivity(intent)

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


// start screen -------------------------------------------------------------------------------------------------------------
// start screen -------------------------------------------------------------------------------------------------------------
// start screen -------------------------------------------------------------------------------------------------------------
// start screen -------------------------------------------------------------------------------------------------------------

class GameViewStartScreen(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes),
    SurfaceHolder.Callback {

    companion object {

    }

    private var thread: GameThreadStartScreen
    val enemy = EnemyStartScreen(0f, 0f, 0)
    var firstBoot = true
    var backgroundStartScreen: Bitmap? = null
    var towerGunPurple: Bitmap? = null
    var shootBulletPic: Bitmap? = null
    var towerBase: Bitmap? = null


    //initialize items----------------------------------------------------------------------------

    init {

        towerGunPurple = BitmapFactory.decodeResource(context.resources, R.drawable.towergunbig)
        backgroundStartScreen = BitmapFactory.decodeResource(context.resources, R.drawable.backgroundstartscreen)
        shootBulletPic = BitmapFactory.decodeResource(context.resources, R.drawable.bulletbig)
        towerBase = BitmapFactory.decodeResource(context.resources, R.drawable.towerbasesmall)

        // add callback
        holder.addCallback(this)

        // instantiate the game thread
        thread = GameThreadStartScreen(holder, this)

    }

    //surface holder things-----------------------------------------------------------------------

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {

        if (firstBoot) {
            GameThreadStartScreen.running = true
            firstBoot = false
            thread.start()
        } else if (!GameThreadStartScreen.running) {
            GameThreadStartScreen.running = true
        } else if (thread.state == Thread.State.TERMINATED) {
            thread.start()
            GameThreadStartScreen.running = true
        }
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        thread.interrupt()
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        thread.interrupt()
    }

    @InternalCoroutinesApi
    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (MainActivity.firstDisplayScale != 1) {

            if (MainActivity.startScreenTowerBool) {
                MainActivity.startScreenTowerBool = false
                var towerPlace = Tower(10f, 0f, 0f, 60f, 1f, 2f)
                towerPlace.towerRange = TowerRadius(175f, ((2160 / 2)).toFloat(), 300f)
                towerPlace.towerR = 300f
                MainActivity.towerList.add(towerPlace)
            }

            canvas.scale(
                (MainActivity.scaleScreen / 10), (MainActivity.scaleScreen / 10)
            )

            var rectBackgroundStartScreen = Rect(0, 0, 1200, 2160)
            canvas.drawBitmap(backgroundStartScreen!!, null, rectBackgroundStartScreen, null)

            //draw tower

            var rectBaseX =
                Rect(100.toInt(), ((((2160 / 2)) - 75).toInt()), (250).toInt(), ((((2160 / 2)) + 75).toInt()))
            canvas.drawBitmap(towerBase!!, null, rectBaseX, null)
            if (MainActivity.towerList[0].towerPrimaryElement == "wind") {
            } else {
                canvas.save()
                canvas.rotate(getAngle(MainActivity.towerList[0]), (175f), (2160 / 2f))
                var rectTowerX =
                    Rect((85).toInt(), (((2160 / 2) - 75).toInt()), (385).toInt(), (((2160 / 2) + 75).toInt()))
                canvas.drawBitmap(towerGunPurple!!, null, rectTowerX, null)
                canvas.restore()
            }


        // draw enemy

        MainActivity.enemyCount++
        if (MainActivity.enemyCount > (10..100).random()) {
            MainActivity.enemyCount = 0
            val color =
                Color.argb(255, Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))
            var x: EnemyStartScreen =
                EnemyStartScreen(1f, 1f, color)

            when ((0..1).random()) {
                0 -> {
                    x.circle!!.x = 405f
                    x.circle!!.y = 0f
                    x.passed = 4.toByte()
                }
                1 -> {
                    x.circle!!.x = 405f
                    x.circle!!.y = 2160f
                    x.passed = 2.toByte()
                }
            }
            var enemyListIterator = MainActivity.enemyList.listIterator()
            enemyListIterator.add(x)
            // enemy out of screen

        }

        var enemyListIterator = MainActivity.enemyList.listIterator()
        while (enemyListIterator.hasNext()) {
            var it = enemyListIterator.next()
            if (it.circle!!.y > (2160).toFloat() || it.circle!!.y < 0f || it.dead == 7) {
                EnemyStartScreen.crossesAllListStartScreen.remove(it)
                EnemyStartScreen.crossesNoneListStartScreen.remove(it)
                enemyListIterator.remove()
            } else {
                when (it.passed) {
                    2.toByte() ->
                        it.circle!!.y -= 3f
                    4.toByte() ->
                        it.circle!!.y += 3f
                }
                it.draw(canvas)
            }
        }

        // crosses

        var enemyListIteratorX = MainActivity.enemyList.listIterator()
        while (enemyListIteratorX.hasNext()) {
            var it = enemyListIteratorX.next()

            val distanceX =
                (MainActivity.towerList[0].towerRange.x) - (it.circle!!.x)
            val distanceY =
                (MainActivity.towerList[0].towerRange.y) - (it.circle!!.y)
            val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)
            val sumOfRadius =
                MainActivity.towerList[0].towerR + ((it.circle!!.r) - 20)
            if (squaredDistance <= sumOfRadius * sumOfRadius) {

                if (EnemyStartScreen.crossesAllListStartScreen.contains(it)) {
                } else {
                    //if (firstLastRandom == 1) crossesAllList.add(0, it)     // last
                    //else crossesAllList.add(crossesAllList.size, it)      // first
                    EnemyStartScreen.crossesAllListStartScreen.add(0, it)
                    if (EnemyStartScreen.crossesNoneListStartScreen.contains(it)) EnemyStartScreen.crossesNoneListStartScreen.remove(it)
                }
            } else {
                if (EnemyStartScreen.crossesAllListStartScreen.contains(it)) EnemyStartScreen.crossesAllListStartScreen.remove(it)
                if (EnemyStartScreen.crossesNoneListStartScreen.contains(it)) {
                } else EnemyStartScreen.crossesNoneListStartScreen.add(0, it)
            }
        }

        // draw bullet

        var shootListIteratorZ = MainActivity.shootList.listIterator()
        while (shootListIteratorZ.hasNext()) {
            var bullet = shootListIteratorZ.next()
            var enemyListIteratorZ = MainActivity.enemyList.listIterator()
            while (enemyListIteratorZ.hasNext()) {
                var it = enemyListIteratorZ.next()

                val distanceX = (bullet.bullet.x) - (it.circle!!.x)
                val distanceY = (bullet.bullet.y) - (it.circle!!.y)

                val squaredDistance =
                    (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = bullet.bullet.r + ((it.circle!!.r) - (20.0))

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    bullet.broken = 1
                    MainActivity.towerList[0].randomEnemyForShotBool = true
                    it.dead = 7

                }
            }
        }

        var shootListIterator = MainActivity.shootList.listIterator()
        while (shootListIterator.hasNext()) {
            var bullet = shootListIterator.next()
            if (bullet.broken == 1) {
                shootListIterator.remove()
            } else {
                bullet.update()
                if (bullet.bullet.x > ((175 + 60))) {
                    canvas.save()
                    canvas.rotate(getAngleBullet(bullet), bullet.bullet.x, bullet.bullet.y)
                    var baseShootBullet =
                        Rect((bullet.bullet.x - (bullet.bullet.r * 4)).toInt(), (bullet.bullet.y - (bullet.bullet.r * 4)).toInt(), (bullet.bullet.x + (bullet.bullet.r * 4)).toInt(), (bullet.bullet.y + (bullet.bullet.r * 4)).toInt())
                    canvas.drawBitmap(shootBulletPic!!, null, baseShootBullet, null)
                    canvas.restore()
                }
            }
        }

        MainActivity.startScreenTimerTower++
        if (MainActivity.startScreenTimerTower >= 60f && EnemyStartScreen.crossesAllListStartScreen.isNotEmpty()) {
            var shootListIteratorX = MainActivity.shootList.listIterator()
            var x = ShootStartScreen()
            x.bullet.x = MainActivity.towerList[0].towerRange.x
            x.bullet.y = MainActivity.towerList[0].towerRange.y
            x.towerId = 0
            shootListIteratorX.add(x)   // add new shoot
            MainActivity.startScreenTimerTower = 0
        }
    } else {
            var rectBackgroundStartScreen = Rect(0, 0, resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
            canvas.drawBitmap(backgroundStartScreen!!, null, rectBackgroundStartScreen, null)
        }
    }


    fun getAngle(it:Tower): Float {
            var angle =
                Math.toDegrees(atan2((MainActivity.rotationTowerY - (2160 /2)).toDouble(), (MainActivity.rotationTowerX - 175f).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
    }

    fun getAngleBullet(bullet: ShootStartScreen): Float {
            var angle =
                Math.toDegrees(atan2((MainActivity.rotationEnemyY - MainActivity.towerList[bullet.towerId].towerRange.y).toDouble(), (MainActivity.rotationEnemyX - MainActivity.towerList[bullet.towerId].towerRange.x).toDouble()))
                    .toFloat()
            if (angle < 0) {
                angle += 360f
            }
            return angle
    }

}

class GameThreadStartScreen(private val surfaceHolder: SurfaceHolder, private val gameView: GameViewStartScreen) :
    Thread() {
    companion object {
        var running: Boolean = false
        private var canvas: Canvas? = null
    }
    private val targetFPS =
        60 // frames per second, the rate at which you would like to refresh the Canvas

    @InternalCoroutinesApi
    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        var targetTime: Long

        while (running) {
            targetTime = (1000 / (targetFPS)).toLong()
            startTime = System.nanoTime()

            try {
                canvas = null
                if (running && surfaceHolder.surface.isValid){ //&& activityThreadBool) {
                    // locking the canvas allows us to draw on to it
                    canvas = this.surfaceHolder.lockCanvas()
                    if (canvas != null) {
                        this.gameView.draw(canvas!!)
                        //        this.gameView.update()

                        surfaceHolder.unlockCanvasAndPost(canvas)
                        //        activityThreadBool = false
                        //        gameThreadBool = true

                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
                running = false
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                if (waitTime > 0) sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
class EnemyStartScreen(var hp: Float, var maxHp: Float, var color: Int){
    companion object {
        // StartScreen
        var crossesAllListStartScreen = mutableListOf<EnemyStartScreen>()
        var crossesNoneListStartScreen = mutableListOf<EnemyStartScreen>()
        var randomEnemyForShotStartScreen = EnemyStartScreen (0.0f,0.0f, 0)
    }

    var circle: TowerRadius? = null

    var passed: Byte = 0
    var dead = 0
    var random1 = 0
    var random2 = 0
    var random3 = 0
    var random4 = 0
    var random5 = 0
    var random6 = 0
    var random7 = 0
    var random8 = 0
    var random9 = 0
    var random10 = 0
    var random11 = 0
    var random12 = 0
    var randomAngle = 0f
    var paint: Painter
    var paintBlack: Painter
    var enemyPathBool = 0


    init {
        circle = TowerRadius(0.0f, 0.0f, 30.0f)
        paint = Painter()
        paintBlack = Painter()
    }

    fun draw(canvas: Canvas) {
        if (enemyPathBool == 0) randomizer ()
        paintShader ()
                drawPolygonOutline(canvas, circle!!.x, circle!!.y, circle!!.r, paintBlack)
                drawPolygonBase(canvas, circle!!.x, circle!!.y, circle!!.r, paint)

    }

    fun randomizer (){
        random1 = (10..25).random()
        random2 = (10..25).random()
        random3 = (10..25).random()
        random4 = (10..25).random()
        random5 = (10..25).random()
        random6 = (10..25).random()
        random7 = (10..25).random()
        random8 = (10..25).random()
        random9 = (10..25).random()
        random10 = (10..25).random()
        random11 = (0..45).random()
        random12 = (10..25).random()
        enemyPathBool = (1..3).random()
        randomAngle = (0..360).random().toFloat()

    }

    private fun drawPolygonBase(mCanvas: Canvas, x: Float, y: Float, r: Float, paint: Paint) {
            when (enemyPathBool) {
                1 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()

                }
                2 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(x + random6, y - random7)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                3 -> {
                    val path = Path()
                    var b = x + random1
                    var c = y + random8
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x, y + random11)
                    path.lineTo(x - random2, y + random3)
                    path.lineTo(x - random4, y - random5)
                    path.lineTo(x + random6, y - random7)
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
            }
        }
    private fun drawPolygonOutline(mCanvas: Canvas, x: Float, y: Float,  r: Float, paint: Paint) {
            when (enemyPathBool) {
                1 -> {

                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                2 -> {
                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(x + (random6 + 1), y - (random7 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
                3 -> {
                    val path = Path()
                    var b = x + (random1 + 1)
                    var c = y + (random8 + 1)
                    mCanvas.save()
                    mCanvas.rotate(randomAngle, x, y)
                    path.moveTo(b, c)
                    path.lineTo(x, y + (random11 + 1))
                    path.lineTo(x - (random2 + 1), y + (random3 + 1))
                    path.lineTo(x - (random4 + 1), y - (random5 + 1))
                    path.lineTo(x + (random6 + 1), y - (random7 + 1))
                    path.lineTo(b, c)
                    path.close()
                    mCanvas.drawPath(path, paint)
                    mCanvas.restore()
                }
            }
        }
    fun paintShader () {
        if (passed != 5.toByte()) {
            var gradient: LinearGradient? = null
                when (passed) {
                    0.toByte(), 2.toByte() ->
                        gradient =
                            LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, color, Color.LTGRAY, Shader.TileMode.CLAMP)
                    1.toByte() ->
                        gradient =
                            LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, color, Color.LTGRAY, Shader.TileMode.CLAMP)
                    3.toByte() ->
                        gradient =
                            LinearGradient(circle!!.x - circle!!.r, 0f, circle!!.x + circle!!.r, 0f, Color.LTGRAY, color, Shader.TileMode.CLAMP)
                    4.toByte() ->
                        gradient =
                            LinearGradient(0f, circle!!.y - circle!!.r, 0f, circle!!.y + circle!!.r, Color.LTGRAY, color, Shader.TileMode.CLAMP)
                }
            var gradientMatrix = Matrix()
            gradientMatrix!!.preRotate((360 - randomAngle), circle!!.x, circle!!.y)
            gradient!!.setLocalMatrix(gradientMatrix)
            paint.shader = gradient!!
        }
    }

}
class ShootStartScreen () {

    var bulletSpeed: Float = 10.0F
    var paint: Paint
    var bullet = TowerRadius(600.0f, 750.0f, 5.0f)
    var broken = 0
    var id = -1
    var color = Color.YELLOW
    var towerId = 0
    var hitEnemyId = -1


    init {
        paint = Paint()
        paint.isAntiAlias
        paint.isFilterBitmap
        paint.color = color

    }

    fun draw(canvas: Canvas) {

        //    canvas.drawCircle(bullet.x.toFloat(), bullet.y.toFloat(), bullet.r.toFloat(), paint)


    }

    fun update() {

                if (EnemyStartScreen.crossesAllListStartScreen.isNotEmpty()) {

                            if (MainActivity.towerList[0].randomEnemyForShotBool) {
                                EnemyStartScreen.randomEnemyForShotStartScreen = EnemyStartScreen.crossesAllListStartScreen.random()
                                movement(EnemyStartScreen.randomEnemyForShotStartScreen)
                                MainActivity.towerList[0].randomEnemyForShotBool = false
                            }  // random
                            else movement(EnemyStartScreen.randomEnemyForShotStartScreen)
                } else {
                    if (MainActivity.enemyList.contains(EnemyStartScreen.randomEnemyForShotStartScreen)) {
                        movement(EnemyStartScreen.randomEnemyForShotStartScreen)
                    } else {
                        MainActivity.towerList[0].randomEnemyForShotBool = true
                            bullet.x = 200.0f
                            bullet.y = 750.0f
                        broken = 1
                    }
                }

    }

    fun movement(enemy: EnemyStartScreen) {

        fun xSpeed(): Int {
            var speed = 0
            when (enemy.passed) {
                0.toByte() -> speed = 0
                1.toByte() -> speed = (3) * (-1)
                2.toByte() -> speed = 0
                3.toByte() -> speed = (3)
                4.toByte() -> speed = 0
            }
            return speed
        }

        fun ySpeed(): Int {
            var speed = 0
            when (enemy.passed) {
                0.toByte() -> speed = (3) * (-1)
                1.toByte() -> speed = 0
                2.toByte() -> speed = (3) * (-1)
                3.toByte() -> speed = 0
                4.toByte() -> speed = (3)
            }
            return speed
        }

        var nx =
            if (bullet.x > (enemy.circle!!.x + xSpeed())) ((bullet.x - (enemy.circle!!.x + xSpeed().toFloat())) / (bulletSpeed ))
            else (((enemy.circle!!.x + xSpeed().toFloat()) - bullet.x) / (bulletSpeed ))
        var ny =
            if (bullet.y > (enemy.circle!!.y + xSpeed())) ((bullet.y - (enemy.circle!!.y + ySpeed().toFloat())) / (bulletSpeed ))
            else (((enemy.circle!!.y + ySpeed().toFloat()) - bullet.y) / (bulletSpeed ))

        var n =
            if (nx > ny) (bulletSpeed ) / nx
            else (bulletSpeed ) / ny


            if ( MainActivity.enemyList.contains(enemy)) {
                MainActivity.rotationTowerX = enemy.circle!!.x
                MainActivity.rotationTowerY = enemy.circle!!.y
                MainActivity.rotationEnemyX = enemy.circle!!.x
                MainActivity.rotationEnemyY = enemy.circle!!.y
                if (bullet.x > (enemy.circle!!.x + xSpeed())) {
                    bullet.x -= nx * n
                } else {
                    bullet.x += nx * n
                }

                if (bullet.y > (enemy.circle!!.y + ySpeed())) {
                    bullet.y -= ny * n
                } else {
                    bullet.y += ny * n
                }
                //    break
            } else {
                MainActivity.towerList[0].randomEnemyForShotBool = true
                    bullet.x = 200.0f
                    bullet.y = 750.0f
                broken = 1
            }
        }
}
