package com.agsolutions.td

import android.content.*
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool.Builder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.core.view.iterator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.ActiveAbility.Companion.aAid0
import com.agsolutions.td.ActiveAbility.Companion.aAid1
import com.agsolutions.td.ActiveAbility.Companion.aAid2
import com.agsolutions.td.ActiveAbility.Companion.aAid3
import com.agsolutions.td.Fragments.*
import com.agsolutions.td.GameView.Companion.paintBombDmgDone
import com.agsolutions.td.GameView.Companion.paintBurnDmgDone
import com.agsolutions.td.GameView.Companion.paintEarthDmgDone
import com.agsolutions.td.GameView.Companion.paintIceDmgDone
import com.agsolutions.td.GameView.Companion.paintMoonDmgDone
import com.agsolutions.td.GameView.Companion.paintPestDmgDone
import com.agsolutions.td.GameView.Companion.paintPoisonDmgDone
import com.agsolutions.td.GameView.Companion.paintTowerDmgDone
import com.agsolutions.td.GameView.Companion.paintWizardDmgDone
import com.agsolutions.td.GameView.Companion.towerBase
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.Main.MainActivity
import com.agsolutions.td.UiView.Companion.xLevelCount
import com.agsolutions.td.UiView.Companion.yLevelCount
import com.agsolutions.td.Utils.round
import com.agsolutions.td.databinding.ActivityGameBinding
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_personal_highscore.*
import kotlinx.android.synthetic.main.talents.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow
import kotlin.system.exitProcess


class GameActivity : AppCompatActivity(), ItemAdapter.OnClickListener, ItemBagAdapter.OnBagClickListener, ItemBagAdapter.OnBagLongClickListener, ActiveAbilityAdapter.OnActiveAbilityListener {
    companion object {
        var paused = false
        var gameEnd = 1
        var companionList = Companion()
    }

    var adapter = ItemAdapter(companionList.itemList, this)
    var updateViewModel: UpdateViewModel = UpdateViewModel()
    var bagAdapter = ItemBagAdapter(companionList.itemListBagInserter, this, this)
    var activeAbilityAdapter = ActiveAbilityAdapter(companionList.activeAbilityList, this)
    var radioAdapter = RadioAdapter(companionList.radioList)
    var mHandler = Handler()
    var itemTouchHelper = ItemTouchHelper(SwipeItem(adapter))
    var itemTouchHelper2 = ItemTouchHelper(SwipeItemBag(bagAdapter))
    var fragmentStats = StatsFragment(updateViewModel)
    var fragmentStatsTower = StatsTowerFragment(updateViewModel)
    var fragmentStatsEnemy = StatsEnemyFragment(updateViewModel)
    var fragmentItem = ItemFragment()
    var fragmentUpgradeItem = ItemUpgradeFragment()
    private var activityThread: ActivityThread = ActivityThread(this)
    val attr = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
    val soundPool = Builder().setMaxStreams(10).setAudioAttributes(attr).build()
    val soundIds = arrayListOf<Int>(0, 1, 2)
    private var mediaPlayer: MediaPlayer? = null
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null
    var talents = Talents ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGameBinding = ActivityGameBinding.inflate(layoutInflater)
        binding.updateViewModel = updateViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        loadBasics ()
        sound()
        initialize()
        recycler()
        update()
        fragments()
        continueGame()

        ActivityThread.runningActivity = true
        activityThread.start()

        // show start item screen at start


    }

    //----------------------------------------------------------------------------

    override fun onPause() {
        super.onPause()
       activityThread.interrupt()

    }

    override fun onResume() {
        super.onResume()
        ActivityThread.runningActivity = true
        companionList.globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
        companionList.globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)
    }

     override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            companionList.globalSoundMusic = 0f
            companionList.globalSoundEffects = 0f
        }else {
            companionList.globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
            companionList.globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)
        }

    }

    //----------------------------------------------------------------------------

    override fun onBackPressed() {
    }

    //----------------------------------------------------------------------------

    fun loadBasics () {

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        companionList.userLevel = sharedPref!!.getInt("userLevel", 0)

        companionList.mapPick = intent.getIntExtra("pickMap", 0)
        companionList.mapMode = intent.getIntExtra("pickMode", 1)
        companionList.itemPick = intent.getIntExtra("pickMap", 0)
        var usernameX = sharedPref!!.getString("username", "user")
        var startItemsPlace = mutableListOf<Items>()

        if (isOnline(this)) {
            HttpTask {
                // progressMainBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val jsonRes = JSONObject(it)
                if (jsonRes.getString("status") == "true") {
                    var jsonArray = JSONArray(jsonRes.getString("data"))
                    for (i in 0 until jsonArray.length()) {
                        var itemlist = jsonArray.getJSONObject(i)
                        var itemid = itemlist.getInt("itemid")
                        companionList.startItemListAll.forEach() {
                            if (itemid == it.id) {

                                startItemsPlace.add(it)
                            }
                        }

                    }
                    companionList.startItemList.addAll(startItemsPlace)
                    Log.d("userdata Data:::::::", "worked")

                } else {
                    Log.d("post Data:::::::", jsonRes.getString("message"))
                }
            }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_start_items.php?username=" + (usernameX))
        }else{
            var textFile = File("$filesDir/itemList.dat")
            var fis = FileInputStream(textFile)
            var ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Int>
            for (itemid in itemListPlace) {
                companionList.startItemListAll.forEach() {
                    if (itemid == it.id) {
                        startItemsPlace.add(it)
                    }
                }
            }
            companionList.startItemList.addAll(startItemsPlace)
        }

       if (isOnline(this)) {

           if (companionList.mapMode == 1) {

               HttpTask {
                   if (it == null || it == "") {
                       println("connection error")
                       return@HttpTask
                   }
                   println(it)
                   val jsonRes = JSONObject(it)
                   if (jsonRes.getString("status") == "true") {
                       var jsonArray = JSONArray(jsonRes.getString("data"))
                       if (jsonArray.length() > 0) {
                           for (i in 0 until jsonArray.length()) {
                               var scorelist = jsonArray.getJSONObject(i)
                               val username = scorelist.getString("username")
                               val highscoreX = scorelist.getInt("highscore")
                               val date = scorelist.getString("date")

                               var editor = sharedPref!!.edit()
                               editor.putInt("Highscore", highscoreX)
                               editor.apply()
                               companionList.highscore = highscoreX

                               break
                           }
                       }else companionList.highscore = 0

                       Log.d("userdata Data:::::::", "worked")

                   } else {
                       Log.d("post Data:::::::", jsonRes.getString("message"))
                   }
               }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_overall_highscore_map_11.php")

               HttpTask {
                   if (it == null || it == "") {
                       println("connection error")
                       return@HttpTask
                   }
                   println(it)
                   val jsonRes = JSONObject(it)
                   if (jsonRes.getString("status") == "true") {
                       var jsonArray = JSONArray(jsonRes.getString("data"))
                       if (jsonArray.length() > 0) {
                           for (i in 0 until jsonArray.length()) {
                               var scorelist = jsonArray.getJSONObject(i)
                               val username = MainActivity.username
                               val highscoreY = scorelist.getInt("highscore")
                               val date = scorelist.getString("date")

                               var editor = sharedPref!!.edit()
                               editor.putInt("Personal Highscore", highscoreY)
                               editor.apply()
                               companionList.personalHighscore = highscoreY

                               break
                           }
                       }else companionList.personalHighscore = 0
                       Log.d("userdata Data:::::::", "worked")

                   } else {
                       Log.d("post Data:::::::", jsonRes.getString("message"))
                   }
               }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore_map_11.php?username=" + (usernameX))

           } else {

                HttpTask {
                   if (it == null || it == "") {
                       println("connection error")
                       return@HttpTask
                   }
                   println(it)
                   val jsonRes = JSONObject(it)
                   if (jsonRes.getString("status") == "true") {
                       var jsonArray = JSONArray(jsonRes.getString("data"))
                       if (jsonArray.length() > 0) {
                           for (i in 0 until jsonArray.length()) {
                               var scorelist = jsonArray.getJSONObject(i)
                               val username = scorelist.getString("username")
                               val highscoreX = scorelist.getInt("highscore")
                               val date = scorelist.getString("date")

                               var editor = sharedPref!!.edit()
                               editor.putInt("Highscore", highscoreX)
                               editor.apply()
                               companionList.highscore = highscoreX

                               break
                           }
                       }else companionList.highscore = 0
                       Log.d("userdata Data:::::::", "worked")

                   } else {
                       Log.d("post Data:::::::", jsonRes.getString("message"))
                   }
               }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_overall_highscore_map_12.php")

               HttpTask {
                   if (it == null || it == "") {
                       println("connection error")
                       return@HttpTask
                   }
                   println(it)
                   val jsonRes = JSONObject(it)
                   if (jsonRes.getString("status") == "true") {
                       var jsonArray = JSONArray(jsonRes.getString("data"))
                       if (jsonArray.length() > 0) {
                           for (i in 0 until jsonArray.length()) {
                               var scorelist = jsonArray.getJSONObject(i)
                               val username = MainActivity.username
                               val highscoreY = scorelist.getInt("highscore")
                               val date = scorelist.getString("date")

                               var editor = sharedPref!!.edit()
                               editor.putInt("Personal Highscore", highscoreY)
                               editor.apply()
                               companionList.personalHighscore = highscoreY

                               break
                           }
                       }else companionList.personalHighscore = 0
                       Log.d("userdata Data:::::::", "worked")

                   } else {
                       Log.d("post Data:::::::", jsonRes.getString("message"))
                   }
               }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore_map_12.php?username=" + (usernameX))

           }
        } else {
            if (companionList.mapMode == 1) {
                companionList.highscore = 0
                companionList.personalHighscore = sharedPref!!.getInt("GetHighscoreMap11", 0)
            } else {
                companionList.highscore = 0
                companionList.personalHighscore = sharedPref!!.getInt("GetHighscoreMap12", 0)
            }
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

    // -------------------------------------------------------------------------


    private fun sound() {
        companionList.globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
        companionList.globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)

        soundIds[0] = soundPool.load(baseContext, R.raw.shoot, 1)
        soundIds[1] = soundPool.load(baseContext, R.raw.explosionsoundleise, 1)
        soundIds[2] = soundPool.load(baseContext, R.raw.leak, 1)
        companionList.logVolume = (1f-(log10(companionList.maxVolume - companionList.globalSoundMusic) / log10(companionList.maxVolume)))
        companionList.logVolumeEffects = (1f-(log10(companionList.maxVolume - companionList.globalSoundEffects) / log10(companionList.maxVolume)))

    }

    //----------------------------------------------------------------------------

    private fun recycler() {
        recyclerCurrentItem.adapter = adapter
        recyclerCurrentItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerBagItem.adapter = bagAdapter
        recyclerBagItem.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerBagItem.setHasFixedSize(true)

        recyclerActiveAbility.adapter = activeAbilityAdapter
        recyclerActiveAbility.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        recyclerRadio.adapter = radioAdapter
        recyclerRadio.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerRadio.setHasFixedSize(true)



        itemTouchHelper.attachToRecyclerView(recyclerCurrentItem)
        itemTouchHelper2.attachToRecyclerView(recyclerBagItem)
    }

    //----------------------------------------------------------------------------

    private fun initialize() {

        companionList.screenDensity = getResources().getDisplayMetrics().density
        companionList.screenDensityWidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        companionList.screenDensityHeight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()


        companionList.scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)
        companionList.scaleTextMain = sharedPref!!.getFloat("ScaleTextMain", 9f)
        companionList.scaleTextNews = sharedPref!!.getFloat("ScaleTextNews", 8f)
        companionList.scaleTextStats = sharedPref!!.getFloat("ScaleTextStats", 8f)
        companionList.scaleTextButton = sharedPref!!.getFloat("ScaleTextMain", 8f)
        companionList.scaleTextBig = companionList.scaleTextMain * 1.25f

        companionList.hintsBool = sharedPref!!.getBoolean("Global Hints", true)

        if (companionList.mapMode == 2){
            livesTV.text = "NME"
        }

        // Rect
        GameView.dragRectList.addAll(mutableListOf(
            Rect(0, 0, (9999).toInt(), (300 ).toInt()),
            Rect(0, (1300 ).toInt(), (9999).toInt(), (9999).toInt()),
            Rect((170 ).toInt(), (960 ).toInt(), (940 ).toInt(), (1050 ).toInt()),
            Rect((170 ).toInt(), (475 ).toInt(), (840 ).toInt(), (565 ).toInt()),
            Rect((910 ).toInt(), (960 ).toInt(), (1000 ).toInt(), (1240 ).toInt()),
            Rect((365 ).toInt(), (475 ).toInt(), (455 ).toInt(), (1050 ).toInt()),
            Rect((170 ).toInt(), (475 ).toInt(), (260 ).toInt(), (1050 ).toInt())
        ))
        if (companionList.mapMode == 1) GameView.dragRectList.add(Rect((760 ).toInt(), (475 ).toInt(), (850 ).toInt(), (1240 ).toInt()))
        else if (companionList.mapMode == 2) GameView.dragRectList.add(Rect((760 ).toInt(), (475 ).toInt(), (850 ).toInt(), (1050 ).toInt()))

        gameSpeedBtn.setOnClickListener {
            if (companionList.gameSpeedAdjuster == 1f){
                gameSpeedBtn.setBackgroundResource(R.drawable.gamebuttononefivex)
                companionList.gameSpeedAdjuster = 1.5f
            }else if (companionList.gameSpeedAdjuster == 1.5f) {
                gameSpeedBtn.setBackgroundResource(R.drawable.gamebuttontwox)
                companionList.gameSpeedAdjuster = 2f
            }else if (companionList.gameSpeedAdjuster == 2f) {
                gameSpeedBtn.setBackgroundResource(R.drawable.gamebuttononex)
                companionList.gameSpeedAdjuster = 1f
            }
        }

        playPauseBtn.setOnClickListener() {
            if (paused) {
                update()
                paused = false

            } else {
                mHandler.removeCallbacksAndMessages(null)
                paused = true
            }
        }

        menuBtn.setOnClickListener() {
            paused = true
            intent = Intent(this, GameMenu::class.java)
            startActivity(intent)
        }

        firstLastRandomBtn.setOnClickListener() {

                            if (companionList.towerList[companionList.towerClickID].firstLastRandom == 0) {
                                companionList.towerList[companionList.towerClickID].firstLastRandomText = "last"
                                firstLastRandomBtn.setBackgroundResource(R.drawable.gamebuttonlast)
                                companionList.towerList[companionList.towerClickID].firstLastRandom = 1
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotBool = true
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotSticky = true
                            } else if (companionList.towerList[companionList.towerClickID].firstLastRandom == 1) {
                                companionList.towerList[companionList.towerClickID].firstLastRandomText = "random"
                                firstLastRandomBtn.setBackgroundResource(R.drawable.gamebuttonrandom)
                                companionList.towerList[companionList.towerClickID].firstLastRandom = 2
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotBool = true
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotSticky = true
                            } else if (companionList.towerList[companionList.towerClickID].firstLastRandom == 2) {
                                companionList.towerList[companionList.towerClickID].firstLastRandomText = "first"
                                firstLastRandomBtn.setBackgroundResource(R.drawable.gamebuttonfirst)
                                companionList.towerList[companionList.towerClickID].firstLastRandom = 3
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotBool = true
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotSticky = true
                            } else if (companionList.towerList[companionList.towerClickID].firstLastRandom == 3) {
                                companionList.towerList[companionList.towerClickID].firstLastRandomText = "sticky"
                                firstLastRandomBtn.setBackgroundResource(R.drawable.gamebuttonsticky)
                                companionList.towerList[companionList.towerClickID].firstLastRandom = 0
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotBool = true
                                companionList.towerList[companionList.towerClickID].randomEnemyForShotSticky = true
                            }

        }

        talentsBTN.setOnClickListener() {
            paused = true
            intent = Intent(this, Talents::class.java)
            startActivity(intent)
        }

    }

    //----------------------------------------------------------------------------

    private fun fragments() {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragmentStats)
                .addToBackStack(null)
                .commit()
        }

        coinIV.setOnClickListener {

            paused = true
            intent = Intent(this, Shop::class.java)
            startActivity(intent)
        }

        shopiconmystery.visibility = View.INVISIBLE
        shopiconmystery.setOnClickListener {
            intent = Intent(this, SecretShop::class.java)
            startActivity(intent)

        }

    }

    // continue game -------------------------------------------------------------------

    private fun continueGame() {

        var continueGame = intent.getBooleanExtra("LoadGame", false)
        if (continueGame) loadGame()
        else {
            if (companionList.mapMode == 2) companionList.lvlHp * 1.3f
            paused = true
            mHandler.postDelayed({
                intent = Intent(this, TutorialStart::class.java)
                startActivity(intent)
            }, 1000)
        }
    }

    //----------------------------------------------------------------------------

    fun loadGame (){

        //   var editor = sharedPref!!.edit()
        // editor.putBoolean("continueGame", false)
        // editor.apply()

        progressGameBar.visibility = View.VISIBLE


        var fis: InputStream? = null
        var ois: InputStream? = null

        try {
            var textFile = File("$filesDir/saveGame.dat")
            fis = FileInputStream(textFile)
            ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Arrays>

            companionList = itemListPlace[0] as com.agsolutions.td.Companion
            companionList.towerClick = false
            companionList.enemyClick = false
            companionList.build = true
            companionList.buildClickBool = true

            adapter = ItemAdapter(companionList.itemList, this)
            bagAdapter = ItemBagAdapter(companionList.itemListBagInserter, this, this)
            activeAbilityAdapter = ActiveAbilityAdapter(companionList.activeAbilityList, this)
            radioAdapter = RadioAdapter(companionList.radioList)

            recyclerCurrentItem.adapter = adapter
            recyclerCurrentItem.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            recyclerBagItem.adapter = bagAdapter
            recyclerBagItem.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerBagItem.setHasFixedSize(true)

            recyclerActiveAbility.adapter = activeAbilityAdapter
            recyclerActiveAbility.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            recyclerRadio.adapter = radioAdapter
            recyclerRadio.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerRadio.setHasFixedSize(true)

            itemTouchHelper = ItemTouchHelper(SwipeItem(adapter))
            itemTouchHelper2 = ItemTouchHelper(SwipeItemBag(bagAdapter))

            itemTouchHelper.attachToRecyclerView(recyclerCurrentItem)
            itemTouchHelper2.attachToRecyclerView(recyclerBagItem)

            fragmentStats = StatsFragment(updateViewModel)
            fragmentStatsTower = StatsTowerFragment(updateViewModel)
            fragmentStatsEnemy = StatsEnemyFragment(updateViewModel)
            fragmentItem = ItemFragment()
            fragmentUpgradeItem = ItemUpgradeFragment()

            adapter.notifyDataSetChanged()
            bagAdapter.notifyDataSetChanged()
            radioAdapter.notifyDataSetChanged()
            activeAbilityAdapter.notifyDataSetChanged()

            companionList.levelCount = 720
            paused = true

        } catch (e: IOException) {
        } finally {
            if (fis != null) {
                fis.close()
                ois!!.close()
            }
        }

        progressGameBar.visibility = View.INVISIBLE

    }

    //----------------------------------------------------------------------------

    fun update() {

        companionList.logVolume = (1f-(log10(companionList.maxVolume - companionList.globalSoundMusic) / log10(companionList.maxVolume)))
        companionList.logVolumeEffects = (1f-(log10(companionList.maxVolume - companionList.globalSoundEffects) / log10(companionList.maxVolume)))
        if (mediaPlayer != null) mediaPlayer!!.setVolume(companionList.logVolume, companionList.logVolume)

        dropItem2()

        runOnUiThread {
            startItems()
            insertItem()
            unusedItems()
            activeAbilities()
            if (paused) playPauseBtn.setBackgroundResource(R.drawable.gamebuttonplay)
            else playPauseBtn.setBackgroundResource(R.drawable.gamebuttonpause)

                if (companionList.towerClick && companionList.towerClickBool) {
                    companionList.towerClickBool = false
                    talentsBTN.visibility = View.VISIBLE
                    firstLastRandomBtn.visibility = View.VISIBLE


                    companionList.itemListBagInserter.clear()
                    bagAdapter.notifyDataSetChanged()

                    companionList.itemListBagInserter.addAll(companionList.towerList[companionList.towerClickID].itemListBag)

                    bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                    recyclerBagItem.smoothScrollToPosition(0)

                    if (buildBtn.background != getResources().getDrawable(R.drawable.bagicon3))
                    buildBtn.background = getResources().getDrawable(R.drawable.bagicon3)

                    if (supportFragmentManager.findFragmentById(R.id.fragment) != fragmentStatsTower) {
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, fragmentStatsTower)
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }


            if (companionList.build && companionList.buildClickBool){
                companionList.buildClickBool = false
                talentsBTN.visibility = View.INVISIBLE
                firstLastRandomBtn.visibility = View.INVISIBLE

                companionList.itemListBagInserter.clear()
                bagAdapter.notifyDataSetChanged()
                companionList.itemListBagInserter.addAll(companionList.buildListBag)
                bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                recyclerBagItem.smoothScrollToPosition(0)

                if (buildBtn.background != getResources().getDrawable(R.drawable.talentsutils))
                    buildBtn.background = getResources().getDrawable(R.drawable.talentsutils)

                if (supportFragmentManager.findFragmentById(R.id.fragment) != fragmentStats) {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment, fragmentStats)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }

            if (companionList.enemyClick && companionList.enemySelectedBool) {
                companionList.enemySelectedBool = false
                talentsBTN.visibility = View.INVISIBLE
                firstLastRandomBtn.visibility = View.INVISIBLE

                companionList.itemListBagInserter.clear()
                bagAdapter.notifyDataSetChanged()
                companionList.itemListBagInserter.addAll(companionList.buildListBag)
                bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                recyclerBagItem.smoothScrollToPosition(0)

                if (supportFragmentManager.findFragmentById(R.id.fragment) != fragmentStatsEnemy) {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment, fragmentStatsEnemy)
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }

            if (companionList.autoSpawnClick) {
                if (companionList.autoSpawn) {
                    var toast = Toast.makeText(this,
                        "Autospawn on", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                    if (companionList.level != 0 && companionList.enemyList.isEmpty()) companionList.levelCount = companionList.levelCountPlace
                } else if (!companionList.autoSpawn) {
                    var toast = Toast.makeText(this,
                        "Autospawn off", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                }
                companionList.autoSpawnClick = false
            }

            if (companionList.toastGlobal) {
                companionList.toastGlobal = false
                var toast = Toast.makeText(this,
                    companionList.toastText, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }

            // UI Draw ----------------------------------------------------------------

            if (companionList.dragStatusDrag){
                var count = 0
                GameView.dragRectList.forEach(){
                    if (Rect.intersects(it, GameView.dragRect)){
                        count++
                    }
                }
                GameActivity.companionList.towerList.forEach(){
                    var a = 0
                    var rect2 = Rect((it.towerRange.x -80).toInt(), (it.towerRange.y - 80).toInt(), (it.towerRange.x + 80).toInt(), (it.towerRange.y + 80).toInt())
                        when (companionList.dragTower.id){
                            2000 -> { // basic
                                when (it.towerRarity){
                                    "basic" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "rare" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "epic" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "legendary" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                }
                            }
                            in 2100..2109 -> { // rare
                                when (it.towerRarity){
                                    "basic" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "rare" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "epic" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "legendary" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                }
                            }
                            in 2200..2209 -> { // epic
                                when (it.towerRarity){
                                    "basic" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "rare" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "epic" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                    "legendary" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                }
                            }
                            in 2300..2309 -> { // legendary
                                when (it.towerRarity){
                                    "basic" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "rare" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "epic" -> {
                                        a++
                                        GameActivity.companionList.overrideTower = Rect.intersects(rect2, GameView.dragRect)
                                        it.canBuildEach = Rect.intersects(rect2, GameView.dragRect)
                                    }
                                    "legendary" -> {
                                        if (Rect.intersects(rect2, GameView.dragRect)) count++
                                    }
                                }
                            }
                        }
                    it.canBuild = a > 0
                }
                companionList.dragStatusCantBuild = count > 0
                companionList.invalidate++
            }
            var containsStartItemPos = -1
            if (GameActivity.companionList.towerList.isEmpty()  && GameActivity.companionList.hintsBool){
                if (GameActivity.companionList.buildListBag.isEmpty()) {
                    for (it in GameActivity.companionList.itemList) {
                        if (it == (GameActivity.companionList.stt0) ||
                            it == (GameActivity.companionList.stt1) ||
                            it == (GameActivity.companionList.stt2) ||
                            it == (GameActivity.companionList.stt3) ||
                            it == (GameActivity.companionList.stt4) ||
                            it == (GameActivity.companionList.stt5) ||
                            it == (GameActivity.companionList.stt6) ||
                            it == (GameActivity.companionList.stt7) ||
                            it == (GameActivity.companionList.stt8) ||
                            it == (GameActivity.companionList.stt9)
                        ) {
                            containsStartItemPos = GameActivity.companionList.itemList.indexOf(it)
                            break
                        }
                    }
                }else {
                    containsStartItemPos = 0
                }
            }
            if (GameActivity.companionList.towerList.isEmpty() && containsStartItemPos != -1 && GameActivity.companionList.hintsBool) {

                val posXY = IntArray(2)
                if (GameActivity.companionList.buildListBag.isEmpty()) {
                    recyclerCurrentItem.forEach {
                        var a = recyclerCurrentItem.indexOfChild(it)
                        if (a == containsStartItemPos) it.getLocationOnScreen(posXY)
                    }
                    UiView.xRecyclerTower = posXY[0].toFloat()
                    UiView.yRecyclerTower = posXY[1].toFloat()
                    companionList.invalidate++
                }else {
                    for (recycler in recyclerBagItem) {
                        recycler.getLocationOnScreen(posXY)
                        UiView.xRecyclerTower = posXY[0].toFloat()
                        UiView.yRecyclerTower = posXY[1].toFloat()
                        companionList.invalidate++
                        break
                    }
                }
            }

                       if (companionList.towerClick && companionList.towerList[companionList.towerClickID].itemListBag.size - companionList.towerList[companionList.towerClickID].bagSizeElementCount < 1 && companionList.itemList.isNotEmpty() && companionList.hintsBool) {
                           val posXY = IntArray(2)
                           recyclerCurrentItem.getLocationOnScreen(posXY)
                           UiView.xRecycler = posXY[0].toFloat()
                           UiView.yRecycler = posXY[1].toFloat()
                           companionList.invalidate++
                       }

                       if (companionList.towerClick && companionList.towerList[companionList.towerClickID].talentPoints > 1 && companionList.hintsBool) {
                           val posXY = IntArray(2)
                           talentsBTN.getLocationOnScreen(posXY)
                           UiView.xTalents = posXY[0].toFloat()
                           UiView.yTalents = posXY[1].toFloat()
                           companionList.invalidate++
                       }
            if (companionList.towerClick && companionList.towerList[companionList.towerClickID].itemListBag.size - companionList.towerList[companionList.towerClickID].bagSizeElementCount >= 1 && companionList.hintsBool && companionList.tutorialFirstUseItemBool) {
                companionList.tutorialFirstUseItemCounter++
                if (companionList.tutorialFirstUseItemCounter > 180) companionList.tutorialFirstUseItemBool = false
                val posXY = IntArray(2)
                for (it in recyclerBagItem) {
                    if (recyclerBagItem.indexOfChild(it) == 1){
                        it.getLocationOnScreen(posXY)
                        break
                    }
                }
                UiView.xRecyclerBag = posXY[0].toFloat()
                UiView.yRecyclerBag = posXY[1].toFloat()
                companionList.invalidate++
            }

            if (companionList.spawnBool && companionList.hintsBool && companionList.level != 0) {
                companionList.spawnBoolCounter++
                if (companionList.spawnBoolCounter > 360) companionList.spawnBool = false
                companionList.invalidate++
            }

            if (GameActivity.companionList.enemyList.isEmpty() && GameActivity.companionList.level != 0 && GameActivity.companionList.mapMode == 1 && !GameActivity.companionList.autoSpawn && GameActivity.companionList.hintsBool){
                companionList.invalidate++
            }

            if (companionList.level == 0) {
                if (companionList.levelCount == companionList.levelCountPlace - 1 || companionList.levelCount == companionList.levelCountPlace - 60 || companionList.levelCount == companionList.levelCountPlace - 120 || companionList.levelCount == companionList.levelCountPlace - 180 ||
                    companionList.levelCount == companionList.levelCountPlace - 300 || companionList.levelCount == companionList.levelCountPlace - 340 || companionList.levelCount == companionList.levelCountPlace - 380 || companionList.levelCount == companionList.levelCountPlace - 420
                ) {
                    xLevelCount =
                        (Resources.getSystem().getDisplayMetrics().widthPixels / 2).toFloat()
                    yLevelCount =
                        (Resources.getSystem().getDisplayMetrics().heightPixels / 2).toFloat()
                    companionList.invalidate++
                }
            }

            companionList.uiRefreshCounter++
            if (companionList.invalidate > 0 || companionList.level == 0 || companionList.uiRefreshCounter >= 60) {
                companionList.invalidate = 0
                companionList.uiRefreshCounter = 0
                uiView.invalidate()
            }

        }

        gameEnd()

        //  update game -------------------------------------------------------------
        if (!paused && companionList.end == 0) {
            updateGame()
            // refresh
            companionList.refresh = true
        }
    }

    //----------------------------------------------------------------------------

    private fun startItems() {
        //from StartItems class
        if (StartItems.startItems > 0) {
            adapter.notifyDataSetChanged()
            StartItems.startItems -= 1
        }
    }

    //----------------------------------------------------------------------------

    private fun insertItem() {

            if (companionList.dropItemDarkUltimate > 0) {
                companionList.itemListInsertItem.add(0, Items(6666, 1, 999, 0, 0f, 0, 0f, 0, "Soul Collector", R.drawable.soulpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "Collects Souls", 0f, "", 0f))
                companionList.dropItemDarkUltimate -= 1
                if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
                    companionList.fragmentItemCurrentItem += 1
                }
            }
            // telling adapter to insert item to recycler 1
            if (companionList.itemListInsertItem.isNotEmpty()) {
                var itemPlace = companionList.itemListInsertItem.random()

                companionList.itemList.add(0,itemPlace)
                companionList.itemListInsertItem.remove(itemPlace)

                adapter.notifyItemInserted(0)
            }

            // telling adapter to insert item to recycler 2
            if (companionList.insertItemBag >= 1) {
                companionList.insertItemBag -= 1

                companionList.itemListBagInserter.clear()
                bagAdapter.notifyDataSetChanged()

                if (companionList.towerClick) {
                    companionList.readLockTower.lock()
                    try {
                        var towerListIterator = companionList.towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()
                            if (tower.selected) {

                                companionList.itemListBagInserter.addAll(tower.itemListBag)

                            }
                        }
                    } finally {
                        companionList.readLockTower.unlock()
                    }
                } else {
                    companionList.itemListBagInserter.addAll(companionList.buildListBag)
                }
                bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                recyclerBagItem.smoothScrollToPosition(0)

            }
            // tell adapter news added to radio
            if (companionList.radioAdd >= 1) {
                companionList.radioAdd -= 1
                radioAdapter.notifyDataSetChanged()
                recyclerRadio.smoothScrollToPosition(companionList.radioList.size - 1)
            }

        // tell adapter active abilities to insert into recycler active ability
        if (companionList.insertSpell > 0) {
            activeAbilityAdapter.notifyDataSetChanged()
            companionList.insertSpell = 0
        }
    }

    //----------------------------------------------------------------------------

    fun dropItem (enemy: Enemy) {
        if (companionList.dropItemList >= 1) {
            if (!companionList.itemBeingMoved) {
                repeat(companionList.dropItemList) {
                if (enemy.elementalMob || enemy.name == "boss" || enemy.name == "challenge") {
                    var itemX = getItem2(companionList.towerList[enemy.killerId])
                    if (itemX.id == 7){
                                companionList.upgradePoints++
                                companionList.dmgDisplayDropList.add(DropDisplay(enemy.circle!!.x.toInt(),enemy.circle!!.y.toInt(), "up", 1, 50))
                            }else {
                        companionList.itemListInsertItem.add(itemX)
                    }
                    if (companionList.towerList[enemy.killerId].overallItemChance > 20) {
                        when ((0..3).random()) {
                            0 -> {
                                companionList.dropItemList++
                                mHandler.postDelayed({
                                    dropItem((enemy))
                                }, 50)
                            }
                        }
                    }
                } else {
                    when ((0..999).random()) {
                        in 0..(companionList.towerList[enemy.killerId].overallItemChance * 10).toInt() -> {
                            var itemX = getItem(companionList.towerList[enemy.killerId])
                            if (itemX.id == 6){
                                companionList.gold += (((0..(enemy.overallGold * 10).toInt()).random()) + 0.01f) * 0.1f
                                companionList.dmgDisplayDropList.add(DropDisplay(enemy.circle!!.x.toInt(),enemy.circle!!.y.toInt(), "gold", 1, 50))
                            }else {
                                if (companionList.upgraderBool && itemX.upgrade != 0) itemX.upgrade *= 2
                                companionList.itemListInsertItem.add(itemX)
                            }
                        }
                    }
                }

                if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
                    companionList.fragmentItemCurrentItem += 1
                }
                companionList.dropItemList -= 1
            }
        } else {
                companionList.itemBeingMovedSave++
                companionList.itemBeingMovedSaveList.add(enemy)
            }
        }
    }

    //----------------------------------------------------------------------------

    fun dropItem2 () {
        if (companionList.itemBeingMovedSave > 0 && !companionList.itemBeingMoved) {
            companionList.itemBeingMovedSave -= 1
            var randomDrop = companionList.itemBeingMovedSaveList.random()
            companionList.itemBeingMovedSaveList.remove(randomDrop)
            dropItem(randomDrop)
        }
    }

    //----------------------------------------------------------------------------

    fun unusedItems () {
        // unused items
        if (companionList.itemList.size >= 8) {

            var amount = 0f
            if (companionList.itemListNormal.contains(companionList.itemList[7].id)) amount = 0.5f
            if (companionList.itemListRare.contains(companionList.itemList[7].id)) amount = 1f
            if (companionList.itemListEpic.contains(companionList.itemList[7].id)) amount = 2f
            if (companionList.itemListLegendary.contains(companionList.itemList[7].id)) amount = 3f

            companionList.itemPoints += amount

            companionList.itemList.removeAt(7)

            runOnUiThread {
                companionList.itemListInserter.clear()
                adapter.notifyDataSetChanged()
                companionList.itemListInserter.addAll(companionList.itemList)
                adapter.notifyItemRangeInserted(0, companionList.itemListInserter.size)
                recyclerCurrentItem.smoothScrollToPosition(0)
            }

            val posXY = IntArray(2)
            var height = 0
            var width = 0
            for (it in recyclerCurrentItem) {
                if (recyclerCurrentItem.indexOfChild(it) == 6){
                    it.getLocationOnScreen(posXY)
                    height = it.height
                    width = it.width
                    break
                }
            }

            companionList.dmgDisplayDropList.add(DropDisplay(posXY[0] + height, posXY[1] + width, "ip", 1, 50))

        }
    }

    //----------------------------------------------------------------------------

    private fun gameEnd() {

        if ((companionList.mapMode == 1 && updateViewModel.lives.value == "0" && companionList.end == 0) || (companionList.mapMode == 2 && companionList.lives >= companionList.livesMode2 && companionList.end == 0)) {
            companionList.end = 1
            var editor = sharedPref!!.edit()
            editor.putFloat("Global Music", companionList.globalSoundMusic)
            editor.putFloat("Global Effects", companionList.globalSoundEffects)
            editor.apply()
            intent = Intent(this, GameEnd::class.java)
            startActivity(intent)
        }

        if (gameEnd == 0) {
            gameEnd = 1

            mHandler.postDelayed({
                //  finish()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                exitProcess(0)
            }, 100)
        }
    }

    //----------------------------------------------------------------------------

    override fun onClick(position: Int) {

        companionList.enemyClick = false

        // show item fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragmentItem)
                .addToBackStack(null)
                .commit()
        }

        companionList.fragmentItemCurrentItem = position
        fragmentItem.refresh(supportFragmentManager, fragmentItem)
    }

    //----------------------------------------------------------------------------

    override fun onBagLongClick(position: Int, it: View?) {

        // Creates a new drag event listener
        val dragListen = View.OnDragListener { vv, event ->

            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        companionList.dragStatusDrag = true

                        vv.invalidate()
                        Log.d("drag", "started")
                        true
                    } else {
                        false
                    }
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    vv.invalidate()
                    Log.d("drag", "entered")
                    true
                }

                DragEvent.ACTION_DRAG_LOCATION -> {
                    var scaler = ((companionList.scaleScreen / 10) * GameView.scaleFactor)
                    // (companionList.clipRect.left * scaler)
                    // (GameView.towerBase!!.height / 2)

                    GameView.dragRect = Rect(((event.x / scaler) + (companionList.clipRect.left) - (GameView.towerBase!!.height / 2)).toInt(), ((event.y / scaler) + (companionList.clipRect.top ) - (GameView.towerBase!!.height / 2)).toInt(), ((event.x / scaler) + (companionList.clipRect.left) - (GameView.towerBase!!.height / 2)).toInt(), ((event.y / scaler) + (companionList.clipRect.top) - (GameView.towerBase!!.height / 2)).toInt())
                true
                }

                DragEvent.ACTION_DRAG_EXITED -> {
                    vv.invalidate()
                    Log.d("drag", "exited")
                    true
                }

                DragEvent.ACTION_DROP -> {
                    val item: ClipData.Item = event.clipData.getItemAt(0)
                    val dragData = item.text
                    var build = true


                    if (companionList.level > 0 && position == 0){
                        if (!companionList.day && companionList.moonTalentItemCost > 0){
                            if (companionList.gold >= (companionList.buildListBag[position].goldCost - (companionList.buildListBag[position].goldCost * companionList.moonTalentItemCost))) companionList.gold -= (companionList.buildListBag[position].goldCost - (companionList.buildListBag[position].goldCost * companionList.moonTalentItemCost))
                            else build = false
                        }
                        else if (companionList.midnightMadnessMidasGoldCost > 0){
                            if (companionList.gold >= (companionList.buildListBag[position].goldCost + (companionList.buildListBag[position].goldCost * companionList.midnightMadnessMidasGoldCost))) companionList.gold -= (companionList.buildListBag[position].goldCost + (companionList.buildListBag[position].goldCost * companionList.midnightMadnessMidasGoldCost))
                            else build = false
                        }
                        else {
                            if (companionList.gold >= companionList.buildListBag[position].goldCost) companionList.gold -= companionList.buildListBag[position].goldCost
                            else build = false
                        }
                    }

                    if (build && !companionList.dragStatusCantBuild) {
                        var scaler = ((companionList.scaleScreen / 10) * GameView.scaleFactor)
                        if (companionList.overrideTower){
                            companionList.writeLockTower.lock()
                            try {
                                var towerListIterator = companionList.towerList.listIterator()
                                while (towerListIterator.hasNext()) {
                                    var elementBagFull = false
                                    var it = towerListIterator.next()
                                    var eventX =
                                        (event.x / scaler)  + (companionList.clipRect.left) - ((towerBase!!.height / 2))
                                    var eventY =
                                        (event.y / scaler)  + (companionList.clipRect.top) - ((towerBase!!.height / 2))
                                    var rect = Rect((eventX - 100).toInt(), (eventY - 100).toInt(), (eventX + 100).toInt(), (eventY + 100).toInt())
                                    var rect2 = Rect(it.towerRange.x.toInt(), it.towerRange.y.toInt(), it.towerRange.x.toInt(), it.towerRange.y.toInt())
                                    if (Rect.intersects(rect, rect2)) {
                                        when (companionList.dragTower.id) {
                                            in 2100..2109 -> {
                                                if (it.towerRarity == "basic") {
                                                    it.towerRarityMultiplier += (1.0f - 0.8f)
                                                    it.dmg += (10f - 5f)
                                                    it.speed -= (70f - 60f)
                                                    it.bagSize += (3 - 2)
                                                    it.bagSizeElement += (2 - 1)
                                                }
                                                it.towerRarity = "rare"
                                            }
                                            in 2200..2209 -> {
                                                if (it.towerRarity == "basic") {
                                                    it.towerRarityMultiplier += (1.2f - 0.8f)
                                                    it.dmg += (15f - 5f)
                                                    it.speed -= (70f - 55f)
                                                    it.bagSize += (4 - 2)
                                                    it.bagSizeElement += (2 - 1)
                                                } else if (it.towerRarity == "rare") {
                                                    it.towerRarityMultiplier += (1.2f - 1.0f)
                                                    it.dmg += (15f - 10f)
                                                    it.speed -= (60f - 55f)
                                                    it.bagSize += (3 - 2)
                                                    it.bagSizeElement += (2 - 2)
                                                    elementBagFull = true
                                                }
                                                it.towerRarity = "epic"
                                            }
                                            in 2300..2309 -> {
                                                if (it.towerRarity == "basic") {
                                                    it.towerRarityMultiplier += (1.2f - 0.8f)
                                                    it.dmg += (25f - 5f)
                                                    it.speed -= (70f - 50f)
                                                    it.bagSize += (5 - 2)
                                                    it.bagSizeElement += (3 - 1)
                                                } else if (it.towerRarity == "rare") {
                                                    it.towerRarityMultiplier += (1.2f - 1.0f)
                                                    it.dmg += (25f - 10f)
                                                    it.speed -= (60f - 50f)
                                                    it.bagSize += (5 - 3)
                                                    it.bagSizeElement += (3 - 2)
                                                } else if (it.towerRarity == "epic") {
                                                    it.towerRarityMultiplier += (1.2f - 1.2f)
                                                    it.dmg += (25f - 15f)
                                                    it.speed -= (55f - 50f)
                                                    it.bagSize += (5 - 4)
                                                    it.bagSizeElement += (3 - 2)
                                                }
                                                it.towerRarity = "legendary"
                                            }
                                        }
                                        if (!elementBagFull) {
                                            var itemPlace: Items? = null
                                            when (companionList.dragTower.id) {
                                                2100, 2200, 2300 -> {
                                                    if (!it.itemListBag.contains(companionList.eearth) || !it.itemListBag.contains(companionList.ebutterfly) || !it.itemListBag.contains(companionList.ewind) || !it.itemListBag.contains(companionList.emoon)) it.towerPrimaryElement =
                                                        "earth"
                                                    itemPlace = companionList.eearth
                                                }
                                                2101, 2201, 2301 -> itemPlace =
                                                    companionList.ewizard
                                                2102, 2202, 2302 -> itemPlace = companionList.eice
                                                2103, 2203, 2303 -> {
                                                    if (!it.itemListBag.contains(companionList.eearth) || !it.itemListBag.contains(companionList.ebutterfly) || !it.itemListBag.contains(companionList.ewind) || !it.itemListBag.contains(companionList.emoon)) it.towerPrimaryElement =
                                                        "butterfly"
                                                    itemPlace = companionList.ebutterfly
                                                }
                                                2104, 2204, 2304 -> itemPlace =
                                                    companionList.epoison
                                                2105, 2205, 2305 -> {
                                                    if (!it.itemListBag.contains(companionList.eearth) || !it.itemListBag.contains(companionList.ebutterfly) || !it.itemListBag.contains(companionList.ewind) || !it.itemListBag.contains(companionList.emoon)) it.towerPrimaryElement =
                                                        "moon"
                                                    itemPlace = companionList.emoon
                                                }
                                                2106, 2206, 2306 -> {
                                                    if (!it.itemListBag.contains(companionList.eearth) || !it.itemListBag.contains(companionList.ebutterfly) || !it.itemListBag.contains(companionList.ewind) || !it.itemListBag.contains(companionList.emoon)) it.towerPrimaryElement =
                                                        "wind"
                                                    itemPlace = companionList.ewind
                                                }
                                                2107, 2207, 2307 -> itemPlace = companionList.eutils
                                                2108, 2208, 2308 -> itemPlace = companionList.efire
                                                2109, 2209, 2309 -> itemPlace = companionList.edark
                                            }
                                            if (itemPlace != null) {
                                                if (it.bagSizeElementCount == 0) it.itemListBag.add(0, itemPlace)
                                                else it.itemListBag.add(itemPlace)
                                            }
                                        }

                                            if (companionList.level == 0 || (companionList.level > 0 && position != 0)) {
                                                companionList.buildListBag.removeAt(position)

                                                companionList.itemListBagInserter.clear()
                                                bagAdapter.notifyDataSetChanged()
                                                companionList.itemListBagInserter.addAll(companionList.buildListBag)
                                                bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                                                recyclerBagItem.smoothScrollToPosition(0)
                                            }
                                    }
                                    break
                                }
                                 } finally {
                        companionList.writeLockTower.unlock()
                                }
                        }else {
                            var towerPlace =
                                Tower(companionList.buildListBag[position].dmg, companionList.buildListBag[position].atkDmg, companionList.buildListBag[position].mgcDmg, companionList.buildListBag[position].speed, companionList.buildListBag[position].crit, companionList.buildListBag[position].critDmg)
                            var eventX =
                                (event.x / scaler)  + (companionList.clipRect.left) - ((towerBase!!.height / 2))
                            var eventY =
                                (event.y / scaler)  + (companionList.clipRect.top) - ((towerBase!!.height / 2))
                            towerPlace.towerRange = TowerRadius(eventX, eventY, 300f)
                            towerPlace.bagSize =
                                (companionList.buildListBag[position].specialFloat - 1).toInt()
                            towerPlace.bagSizeElement =
                                (companionList.buildListBag[position].specialFloat2 - 1).toInt()
                            when (companionList.buildListBag[position].id) {
                                2000 -> {
                                    towerPlace.towerRarity = "basic"
                                    towerPlace.towerRarityMultiplier = 0.80f
                                }
                                in 2100..2109 -> {
                                    towerPlace.towerRarity = "rare"
                                    towerPlace.towerRarityMultiplier = 1.0f
                                }
                                in 2200..2209 -> {
                                    towerPlace.towerRarity = "epic"
                                    towerPlace.towerRarityMultiplier = 1.2f
                                }
                                in 2300..2309 -> {
                                    towerPlace.towerRarity = "legendary"
                                    towerPlace.towerRarityMultiplier = 1.5f
                                }
                            }

                            var itemPlace: Items? = null
                            when (companionList.buildListBag[position].id) {
                                2100, 2200, 2300 -> {
                                    towerPlace.towerPrimaryElement = "earth"
                                    itemPlace = companionList.eearth
                                }
                                2101, 2201, 2301 -> itemPlace = companionList.ewizard
                                2102, 2202, 2302 -> itemPlace = companionList.eice
                                2103, 2203, 2303 -> {
                                    towerPlace.towerPrimaryElement = "butterfly"
                                    itemPlace = companionList.ebutterfly
                                }
                                2104, 2204, 2304 -> itemPlace = companionList.epoison
                                2105, 2205, 2305 -> {
                                    towerPlace.towerPrimaryElement = "moon"
                                    itemPlace = companionList.emoon
                                }
                                2106, 2206, 2306 -> {
                                    towerPlace.towerPrimaryElement = "wind"
                                    itemPlace = companionList.ewind
                                }
                                2107, 2207, 2307 -> itemPlace = companionList.eutils
                                2108, 2208, 2308 -> itemPlace = companionList.efire
                                2109, 2209, 2309 -> itemPlace = companionList.edark
                            }
                            if (itemPlace != null) {
                                towerPlace.itemListBag.add(0, itemPlace)
                            }

                            companionList.writeLockTower.lock()
                            try {
                                var towerListIterator = companionList.towerList.listIterator()
                                towerListIterator.add(towerPlace)
                            } finally {
                                companionList.writeLockTower.unlock()
                            }

                            if (companionList.level == 0 || (companionList.level > 0 && position != 0)) {
                                companionList.buildListBag.removeAt(position)

                                companionList.itemListBagInserter.clear()
                                bagAdapter.notifyDataSetChanged()
                                companionList.itemListBagInserter.addAll(companionList.buildListBag)
                                bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                                recyclerBagItem.smoothScrollToPosition(0)
                            }
                        }
                    }
                    vv.invalidate()
                    Log.d("drag", "dropped")
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    vv.invalidate()


                    when(event.result) {
                        true -> {
                            companionList.dragStatusDrag = false
                            // drop was handled
                            Log.d("drag", "ended, success")
                        }
                        else ->{
                            companionList.dragStatusDrag = false
                            // drop didn't work
                            Log.d("drag", "ended, fail")
                        }
                    }
                    paused = false

                    // returns true; the value is ignored.
                    true
                }

                else -> {
                    // An unknown action type was received.
                    false
                }
            }
        }

        gameView.setOnDragListener(dragListen)
    }

    override fun onBagClick(position: Int, it: View?) {

        companionList.enemyClick = false

        // show item update fragment

        if (supportFragmentManager.findFragmentById(R.id.fragment) != fragmentUpgradeItem){
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragmentUpgradeItem)
                    .addToBackStack(null)
                    .commit()
            }
        }

        companionList.itemFragmentEnemyList.removeAll(companionList.itemFragmentEnemyList)

        if (!companionList.towerClick && companionList.buildListBag.size > 0) {
            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, companionList.buildListBag[position].name.toString()))
            if (!companionList.day && companionList.moonTalentItemCost > 0 && companionList.level != 0 && companionList.buildListBag[position] == companionList.buildListBag[0]) {
                if (companionList.buildListBag[position].goldCost > 0) {
                    when (companionList.buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost - (companionList.buildListBag[position].goldCost * companionList.moonTalentItemCost)).toInt()
                            .toString()))
                        in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost - (companionList.buildListBag[position].goldCost * companionList.moonTalentItemCost) / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost - (companionList.buildListBag[position].goldCost * companionList.moonTalentItemCost) / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else if (companionList.midnightMadnessMidasGoldCost > 0 && companionList.level != 0 && companionList.buildListBag[position] == companionList.buildListBag[0]) {
                if (companionList.buildListBag[position].goldCost > 0) {
                    when (companionList.buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost + (companionList.buildListBag[position].goldCost * companionList.midnightMadnessMidasGoldCost)).toInt()
                            .toString()))
                        in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost + (companionList.buildListBag[position].goldCost * companionList.midnightMadnessMidasGoldCost) / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost + (companionList.buildListBag[position].goldCost * companionList.midnightMadnessMidasGoldCost) / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else {
                if (companionList.buildListBag[position].goldCost > 0 && companionList.level != 0 && companionList.buildListBag[position] == companionList.buildListBag[0]) {
                    when (companionList.buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost).toInt()
                            .toString()))
                        in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.buildListBag[position].goldCost / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            }

                        if (companionList.buildListBag[position].upgrade > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, companionList.buildListBag[position].upgrade.toString()))
                        if (companionList.buildListBag[position].dmg > 0) {
                            when ((companionList.buildListBag[position].dmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, companionList.buildListBag[position].dmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (companionList.buildListBag[position].dmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (companionList.buildListBag[position].dmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (companionList.buildListBag[position].atkDmg > 0) {
                            when ((companionList.buildListBag[position].atkDmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, companionList.buildListBag[position].atkDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (companionList.buildListBag[position].atkDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (companionList.buildListBag[position].atkDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (companionList.buildListBag[position].mgcDmg > 0) {
                            when ((companionList.buildListBag[position].mgcDmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, companionList.buildListBag[position].mgcDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (companionList.buildListBag[position].mgcDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (companionList.buildListBag[position].mgcDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (companionList.buildListBag[position].crit > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, companionList.buildListBag[position].crit.round(2)
                            .toString()))
                        if (companionList.buildListBag[position].critDmg > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, companionList.buildListBag[position].critDmg.round(2)
                            .toString()))
                        if (companionList.buildListBag[position].speed > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, companionList.buildListBag[position].speed.round(2)
                            .toString()))
                        if (companionList.buildListBag[position].special.isNotBlank()) {
                            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.buildListBag[position].special.toString()))
                            if (companionList.buildListBag[position].specialFloat != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.buildListBag[position].specialFloat.round(2)
                                .toString()))
                        }
                        if (companionList.buildListBag[position].special2.isNotBlank()) {
                            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.buildListBag[position].special2.toString()))
                            if (companionList.buildListBag[position].specialFloat2 != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.buildListBag[position].specialFloat2.round(2)
                                .toString()))
                        }
            if (companionList.buildListBag[position].id == 2000) {
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 0.8"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 0.8"))
            }
            if (companionList.buildListBag[position].id == 2100 || companionList.buildListBag[position].id == 2101 || companionList.buildListBag[position].id == 2102 || companionList.buildListBag[position].id == 2103 || companionList.buildListBag[position].id == 2104 || companionList.buildListBag[position].id == 2105 || companionList.buildListBag[position].id == 2106 || companionList.buildListBag[position].id == 2107 || companionList.buildListBag[position].id == 2108 || companionList.buildListBag[position].id == 2109){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.0"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.0"))
            }
            if (companionList.buildListBag[position].id == 2200 || companionList.buildListBag[position].id == 2201 || companionList.buildListBag[position].id == 2202 || companionList.buildListBag[position].id == 2203 || companionList.buildListBag[position].id == 2204 || companionList.buildListBag[position].id == 2205 || companionList.buildListBag[position].id == 2206 || companionList.buildListBag[position].id == 2207 || companionList.buildListBag[position].id == 2208 || companionList.buildListBag[position].id == 2209){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.2"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.2"))
            }
            if (companionList.buildListBag[position].id == 2300 || companionList.buildListBag[position].id == 2301 || companionList.buildListBag[position].id == 2302 || companionList.buildListBag[position].id == 2303 || companionList.buildListBag[position].id == 2304 || companionList.buildListBag[position].id == 2305 || companionList.buildListBag[position].id == 2306 || companionList.buildListBag[position].id == 2307 || companionList.buildListBag[position].id == 2308 || companionList.buildListBag[position].id == 2309){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.5"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.5"))
            }
            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.overlaytransparent, "                ".toString()))

        }else {

            companionList.readLockTower.lock()
            try {
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.selected) {

                        companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, tower.itemListBag[position].name.toString()))
                        if (tower.itemListBag[position].upgrade > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, tower.itemListBag[position].upgrade.toString()))
                        if (tower.itemListBag[position].dmg > 0) {
                            when ((tower.itemListBag[position].dmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, tower.itemListBag[position].dmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].atkDmg > 0) {
                            when ((tower.itemListBag[position].atkDmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, tower.itemListBag[position].atkDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].mgcDmg > 0) {
                            when ((tower.itemListBag[position].mgcDmg).toInt()) {
                                in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, tower.itemListBag[position].mgcDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].crit > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, tower.itemListBag[position].crit.round(2)
                            .toString()))
                        if (tower.itemListBag[position].critDmg > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, tower.itemListBag[position].critDmg.round(2)
                            .toString()))
                        if (tower.itemListBag[position].speed > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, tower.itemListBag[position].speed.round(2)
                            .toString()))
                        if (tower.itemListBag[position].special.isNotBlank()) {
                            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special.toString()))
                            if (tower.itemListBag[position].specialFloat != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat.round(2)
                                .toString()))
                        }
                        if (tower.itemListBag[position].special2.isNotBlank()) {
                            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special2.toString()))
                            if (tower.itemListBag[position].specialFloat2 != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat2.round(2)
                                .toString()))
                        }
                        break
                    }
                }
            } finally {
                companionList.readLockTower.unlock()
            }

            fragmentUpgradeItem.refresh2()

            mHandler.postDelayed({ fragmentUpgradeItem.refresh(position) }, 50)

        }
    }

    //----------------------------------------------------------------------------

    override fun onActiveAbilityClick(position: Int) {
        // active ability recycler on click

        if (companionList.activeAbilityList[position].id == 7000) {
            if (companionList.endlessNightBool && companionList.day) {
                companionList.endlessNightActive = true
                companionList.endlessNightBool = false
                companionList.endlessNightButtonCounter = 1
            }
        } else if (companionList.activeAbilityList[position].id == 7002 ) {
            if (companionList.activeAbilityHelpingHandBool && companionList.itemPoints >= 10) {
                companionList.activeAbilityHelpingHandBool = false
                companionList.itemPoints -= 10
                companionList.activeAbilityHelpingHandActiveCounter = 1
                companionList.activeAbilityHelpingHandButtonCounter = 1
            }
        } else if (companionList.activeAbilityList[position].id == 7003 ) {
            if (companionList.activeAbilityHelpingHandBool) {
                companionList.activeAbilityHelpingHandBool = false
                companionList.activeAbilityHelpingHandActiveCounter = 1
                companionList.activeAbilityHelpingHandButtonCounter = 1
            }
        }else if (companionList.activeAbilityList[position].id == 7001 && companionList.bombActiveAbility > 0) {

            companionList.bombActiveAbility -= 1
            companionList.bombsUsedCounter += 1
            companionList.writeLockEnemy.lock()
            companionList.writeLockDisplay.lock()
                try {

                    var enemyListIterator = companionList.enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()

                        // item Detonate
                        var dmg = 0f
                        if (companionList.mapMode == 2) dmg = (it.maxHp * (0.05f + companionList.bombDamage))
                        else if (companionList.mapMode == 1) dmg = (it.maxHp * (0.2f + companionList.bombDamage))

                        it.hp -= dmg
                        var dmgString = "0"
                        when (dmg.toInt()){
                            in 0..999 -> dmgString = dmg.toInt().toString()
                            in 1000..999999 -> dmgString = (dmg / 1000).toInt().toString() + "k"
                            in 1000000..999999999 -> dmgString =
                                (dmg / 1000000).toInt().toString() + "M"
                        }
                        var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                        dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintBombDmgDone, 30, 15))

                        if (it.hp < 0) {
                            it.killerId = companionList.towerList.indexOf(companionList.towerList.random())
                            companionList.enemyKilledList.add(it)
                        }
                    }

                } finally {
                    companionList.writeLockEnemy.unlock()
                    companionList.writeLockDisplay.unlock()
                }

            if (companionList.bombActiveAbility <= 0) {
                companionList.activeAbilityList.removeAt(position)
                activeAbilityAdapter.notifyItemRemoved(position)
            } else {
                companionList.activeAbilityList[position].cd = companionList.bombActiveAbility.toFloat()
                companionList.activeAbilityList[position].cdRemain =
                    companionList.activeAbilityList[position].cd.toInt().toString()
                activeAbilityAdapter.notifyItemChanged(position)
            }
        }
    }

    fun activeAbilities() {
        // update active abilities--------------------------------------------

        // endless night
        if (companionList.activeAbilityList.contains(aAid0) || companionList.activeAbilityList.contains(aAid1) || companionList.activeAbilityList.contains(aAid2) || companionList.activeAbilityList.contains(aAid3)) {

            companionList.activeAbilityPlace ++
            companionList.activeAbilityList.forEach() {
                //  bombActiveAbilityCounterPlace++
                //  if (bombActiveAbilityCounterPlace >= 60) {

                    if (companionList.activeAbilityPlace >= 60) {
                        companionList.activeAbilityPlace = 0

                        if (it.id == 7000) {
                            if (companionList.endlessNightButtonCounter >= it.cd) {  // 900 * 36
                                companionList.endlessNightButtonCounter = 0
                                companionList.endlessNightBool = true
                        }
                        if (!companionList.endlessNightBool && companionList.endlessNight > 0) {
                            it.cdRemain =
                                ((it.cd - companionList.endlessNightButtonCounter) / 60).toInt().toString()
                            activeAbilityAdapter.notifyItemChanged(companionList.activeAbilityList.indexOf(it))
                            if (it.cdRemain == 0.toString()) it.cdRemain = ""
                        } else it.cdRemain = ""
                        }

                        if (it.id == 7002 || it.id == 7003) {
                                if (companionList.activeAbilityHelpingHandButtonCounter >= it.cd) {  // 900 * 36
                                    companionList.activeAbilityHelpingHandButtonCounter = 0
                                    companionList.activeAbilityHelpingHandBool = true
                                }
                            if (companionList.activeAbilityHelpingHandBool) it.cdRemain = ""
                            else if (!companionList.activeAbilityHelpingHandBool) {
                                    it.cdRemain =
                                        ((it.cd - companionList.activeAbilityHelpingHandButtonCounter) / 60).toInt()
                                            .toString()
                                    if (it.cdRemain == 0.toString()) it.cdRemain = ""
                                activeAbilityAdapter.notifyItemChanged(companionList.activeAbilityList.indexOf(it))
                            } else it.cdRemain = ""

                        }

                        if (it.id == 7001 && companionList.insertSpellBomb > 0) {
                            companionList.insertSpellBomb = 0
                            it.cd = companionList.bombActiveAbility.toFloat()
                            it.cdRemain = it.cd.toInt().toString()
                            activeAbilityAdapter.notifyItemChanged(companionList.activeAbilityList.indexOf(it))
                        }
                    }
            }
        }
    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

    fun updateGame() {

        deadSplit()
        onLvlUp ()
        dayNight ()
        midnightEvents ()
        mysteryEvents ()
        updateViewModel ()
        selected ()
        updateRandomThings ()
        towerAttack()
        updateShots()
        updateShit()
        enemyDots()
        items()
        activeAbilitiesEffect()
        dead()
        dead2()

    }

        fun onLvlUp () {
            // on lvl up
            companionList.timer ++
            if (companionList.level > 75) companionList.levelCountBool = true
            if (companionList.mapMode == 2) companionList.levelCountBool = true
            if (companionList.enemyList.isEmpty() && companionList.levelCountSecondBool) {
                companionList.levelCountSecondBool = false
                if (companionList.level == 0 && companionList.hintsBool) companionList.levelCount = companionList.levelCountPlace - 900
                else if (companionList.level == 0) companionList.levelCount = companionList.levelCountPlace - 480
                else companionList.levelCount = companionList.levelCountPlace - 180
                companionList.levelCountBool = true
            }
            if (companionList.enemyList.isEmpty() && !companionList.autoSpawn && companionList.mapMode == 1 && companionList.level != 0) {
                companionList.levelCount = 0
            }
            if (companionList.enemyList.isEmpty() && companionList.spawnEnemy && companionList.mapMode == 1 && companionList.level != 0) {
                companionList.spawnEnemy = false
                companionList.levelCount = companionList.levelCountPlace
            }
            if (companionList.levelCountBool) companionList.levelCount ++

            //normal
            if (companionList.levelCount >= companionList.levelCountPlace) {
                companionList.levelCountBool = false
                companionList.levelCountSecondBool = false
                companionList.level++
                companionList.levelCount = 0
                companionList.enemySpawned = 0
                companionList.levelStatus = "undef"
                companionList.tankBool = true
                companionList.gold *= companionList.interest      // interest

                //    if (level == 1) soundPool.play(soundIds[0], 0f, 0f, 1, -1, 1.0f)

                // level counter

                if (companionList.level > 150) companionList.levelCountPlace * 0.90
                else if (companionList.level > 125) companionList.levelCountPlace * 0.95
                else if (companionList.level > 75) companionList.levelCountPlace * 0.99

                // spawn counter
                if (Utils.divisible(companionList.level, 20)){
                    companionList.enemySpawnedCount++
                }

                // tutorial touch screen

                if (companionList.level == 3 && companionList.mapPick == 0) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, TutorialTouchScreen::class.java)
                        startActivity(intent)
                    }, 50)
                }

                // tutorial enemies
                if (companionList.level == 10 && companionList.mapPick == 0) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, TutorialEnemies::class.java)
                        startActivity(intent)
                    }, 50)
                }

                if (companionList.mapMode == 2) companionList.lvlHp *= (1 + (0.09f) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.033f, 0.033f, 0.033f, 0.075f, 0.15f).random()))
                else companionList.lvlHp *= (1 + (0.1f) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.033f, 0.033f, 0.033f, 0.075f, 0.15f).random()))
                companionList.lvlArmor *= (1 + (0.03f) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.025f, 0.025f, 0.025f, 0.025f, 0.05f).random()))
                companionList.lvlMagicArmor *= (1 + (0.03f) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.025f, 0.025f, 0.025f, 0.025f, 0.05f).random()))
                companionList.lvlEvade *= (1 + (0.01f) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0125f, 0.0125f, 0.0125f, 0.0125f, 0.025f).random()))
                companionList.lvlXp *= 1 + (0.05f)
                companionList.lvlScalerFirst *= 1.05f

                if (companionList.levelList.contains("speed")) companionList.lvlSpd *= (1.0f + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.03f).random()))

                if (companionList.lvlHp > 100000000) {
                    companionList.lvlHp /= 1000
                    companionList.bigNumberScaler /= 1000
                    companionList.lvlXp /= 1000
                    companionList.gold /= 1000

                    companionList.itemList.forEach {
                        it.dmg /= 1000
                        it.atkDmg /= 1000
                        it.mgcDmg /= 1000
                    }
                    companionList.writeLockTower.lock ()
                    try {
                            var towerListIterator = companionList.towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()

                                tower.itemListBag.forEach {
                                    it.dmg /= 1000
                                    it.atkDmg /= 1000
                                    it.mgcDmg /= 1000
                                }

                                // item aqu
                                tower.itemListBag.forEach() {
                                    it.lvlAqu++
                                    if (companionList.mapMode != 2) {
                                        if (it.id == 205) if (Utils.divisible(it.lvlAqu, 10)) companionList.lives += it.specialFloat.toInt()
                                    }
                                    else {
                                        if (it.id == 205) if (Utils.divisible(it.lvlAqu, 10)) companionList.livesMode2 += it.specialFloat.toInt()
                                    }
                                }
                                if (tower.experienceLvl) towerExperience(companionList.towerList.indexOf(tower), 1f)
                            }
                        }finally {
                        companionList.readLockTower.unlock()
                    }

                    companionList.writeLockEnemy.lock ()
                    try {
                        var enemyListIterator = companionList.enemyList.listIterator()
                        while (enemyListIterator.hasNext()) {
                            var it = enemyListIterator.next()

                            it.maxHp /= 1000
                            it.hp /= 1000
                            it.shield /= 1000
                            it.shieldMax /= 1000
                            it.manaShield /= 1000
                            it.manaShieldMax /= 1000
                        }
                    }finally {
                        companionList.writeLockEnemy.unlock()
                    }


                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, CuttingStatsEvent::class.java)
                        startActivity(intent)
                    }, 50)
                }

                companionList.levelScalerCrit *= 0.98f
                companionList.levelScalerSpeed *= 0.99f
                companionList.levelScalerCritDmg *= 0.99f
                companionList.levelScalerItemChance *= 0.995f
                companionList.levelScalerItemQuality *= 0.995f
                companionList.lvlXpConstant *= 0.995f
                companionList.lvlHpReg *= 0.98f

                // picking item
                companionList.lvlScaler = companionList.lvlXp * 1.5f * companionList.lvlXpConstant
                companionList.lvlScalerSecond = companionList.lvlScalerFirst * 1.5f *  companionList.lvlXpConstant
                companionList.costBase *= 1.01f
                companionList.costGrey = companionList.costBase * 0.75f
                companionList.costBlue = companionList.costBase * 1.5f
                companionList.costEpic = companionList.costBase * 2.0f
                companionList.costLegendary = companionList.costBase * 3.0f

                if (companionList.level != 0 && companionList.level != 1){
                        if (companionList.towerClick) {
                            companionList.buildListBag.removeAt(0)
                            companionList.buildListBag.add(0, Items(2000, 0, 999, 0, (companionList.costBase * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                        }else {
                            runOnUiThread() {
                            companionList.buildListBag.removeAt(0)
                                companionList.itemListBagInserter.clear()
                            bagAdapter.notifyDataSetChanged()

                            companionList.buildListBag.add(0, Items(2000, 0, 999, 0, (companionList.costBase * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                                companionList.itemListBagInserter.addAll(companionList.buildListBag)
                            bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                            }
                        }
                } else {
                    if (companionList.towerClick) {
                        companionList.buildListBag.add(0, Items(2000, 0, 999, 0, (companionList.costBase * companionList.lvlXp * (1.5f + (companionList.towerCount / 5))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                    } else {
                        runOnUiThread() {
                            companionList.itemListBagInserter.clear()
                            bagAdapter.notifyDataSetChanged()
                            companionList.buildListBag.add(0, Items(2000, 0, 999, 0, (companionList.costBase * companionList.lvlXp * (1.5f + (companionList.towerCount / 5))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                            companionList.itemListBagInserter.addAll(companionList.buildListBag)
                            bagAdapter.notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                        }
                    }
                }

                if (companionList.level == 1) {
                    companionList.radioList.add(
                        companionList.radioList.size,
                        "lvl " + companionList.level.toString() + " assault started"
                    )
                    companionList.radioAdd += 1
                }

                if (companionList.level > 0 && Utils.divisible(companionList.level, 10) && companionList.levelListReserve.size > 0) {
                    val x = companionList.levelListReserve.random()
                    companionList.levelList.add(x)
                    when (x) {
                        "evade" -> companionList.lvlEvade += 1
                    }
                    companionList.levelListReserve.remove(x)
                    companionList.radioList.add(
                        companionList.radioList.size,
                        "lvl " + companionList.level.toString() + " + " + x + " ability"
                    )
                    companionList.radioAdd += 1
                }

                // reset shield
                companionList.allShieldsBool = false
                companionList.manaShieldBool = false
                companionList.shieldBool = false

                if (companionList.level == 10) companionList.levelListReserve.addAll(listOf("shortcut", "speed", "mass", "regeneration", "immune", "split", "manaShield", "shield", "invu"))
                if (companionList.level == 60) companionList.levelListReserve.addAll(listOf("healer", "tank", "disruptor"))

                if (companionList.level > 0 && Utils.divisible(companionList.level, 50)) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, SecretShop::class.java)
                        startActivity(intent)
                    }, 50)
                }

                addItemsToList ()
                itemsPerRound ()
            }
        }

    fun dayNight () {

        // day night
        if (companionList.enemyList.isEmpty() && !companionList.autoSpawn && companionList.mapMode == 1 && companionList.level != 0) {
        } else companionList.dayNightCounter += 5
        if (companionList.endlessNightButtonCounter > 0) companionList.endlessNightButtonCounter++
        if (companionList.dayNightCounter == 30) {
            companionList.dayNightMinute++
            companionList.dayNightCounter = 0
        }
        if (companionList.dayNightMinute == 60) {
            companionList.dayNightHour++
            companionList.dayNightMinute = 0
        }
        if (companionList.dayNightHour == 24) {
            companionList.dayNightHour = 0
        }
        if (companionList.day && companionList.soundBoolDay) {
            companionList.soundBoolDay = false
            companionList.soundBoolNight = true
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()
                }
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.daymusiccomp)
            mediaPlayer!!.setVolume(companionList.logVolume, companionList.logVolume)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else if (!companionList.day && companionList.soundBoolNight) {
            companionList.soundBoolNight = false
            companionList.soundBoolDay = true
            if (mediaPlayer!!.isPlaying && mediaPlayer != null) {
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.nightmusiccomp)
            mediaPlayer!!.setVolume(companionList.logVolume, companionList.logVolume)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()

        }

        if (companionList.endlessNightActive) {
            companionList.day = false
            companionList.endlessNightActiveCounter++
            if (companionList.endlessNightActiveCounter >= (900 * companionList.endlessNight)) {
                companionList.endlessNightActive = false
                companionList.endlessNightActiveCounter = 0
            }
        } else {
            companionList.day = !(companionList.dayNightHour < companionList.dayNightVariable || companionList.dayNightHour > 19)
        }
    }

        fun midnightEvents () {

            // midnight events ---------------------------------------------------------------------

        if ((companionList.dayNightHour == 0 && companionList.dayNightMinute == 0) && companionList.mapPick != 0 && companionList.secretShopBool) {
            companionList.secretShopBool = false
            runOnUiThread() {
                shopiconmystery.visibility = View.INVISIBLE
            }

            companionList.radioList.remove(companionList.midnightMadnessEventRadio)

            companionList.midnightMadnessEventRadio

            if (companionList.midnightMadnessDisableItems) {
                companionList.midnightMadnessDisableItems = false
                companionList.midnightMadnessSaveList.remove("Darkest Hour")
                companionList.writeLockTower.lock ()
                try {
                        var towerListIterator = companionList.towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()
                            tower.itemListBag.forEach {
                                if (it.crossedOut){
                                    var index = tower.itemListBag.indexOf(it)
                                    tower.itemListBag[index] = tower.itemListDisabled[0]
                                    adapter.statsAdd(index, tower, true)
                                    tower.itemListDisabled.remove(tower.itemListDisabled[0])
                                }
                            }
                        }
                }finally {
                    companionList.writeLockTower.unlock()
                }
                if (companionList.towerClick) {
                    runOnUiThread {
                        bagAdapter.notifyDataSetChanged()
                    }
                }
                Log.d("Midnight Madness", "Remove Complete")
            }
            if (companionList.midnightMadnessMidas){
                companionList.midnightMadnessMidas = false
                companionList.midnightMadnessMidasGold -= 0.2f
                companionList.midnightMadnessMidasGoldCost -= 0.2f
                companionList.midnightMadnessSaveList.remove("Midas")
            }
            if (companionList.midnightMadnessWind) {
                companionList.midnightMadnessWind = false
                companionList.overallSpdMultiplier += 20f
                companionList.lvlSpd += companionList.midnightMadnessWindLvlSpeedReduce
                companionList.midnightMadnessWindLvlSpeedReduce = 0f
                companionList.midnightMadnessSaveList.remove("Winds")
            }
            if (companionList.midnightMadnessExtraSpawnBool){
                companionList.midnightMadnessExtraSpawnBool = false
                companionList.midnightMadnessExtraSpawn -= 1
                companionList.midnightMadnessSaveList.remove("Extra")
            }

            when ((7..7).random()) {
                //nothing
                in 0..2 -> {
                    Log.d("Midnight Madness", "A Quiet Night")
                    paused = true
                    companionList.midnightMadnessEvent = "A Quiet Night"
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "A Quiet Night")
                        intent.putExtra("Description", "Nothing happened...")
                        startActivity(intent)
                    }, 50)
                }

                // extra

                3 -> {
                    Log.d("Midnight Madness", "Extra, Extra!")
                    paused = true
                    companionList.midnightMadnessEvent = "Extra, Extra!"
                    companionList.midnightMadnessExtraSpawnBool = true
                    companionList.midnightMadnessExtraSpawn += 1
                    companionList.midnightMadnessSaveList.add("Extra")
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Extra, Extra!")
                        intent.putExtra("Description", "Each wave contains 1 extra enemy")
                        startActivity(intent)
                    }, 50)
                }

                // upgrade
                4 -> {
                    Log.d("Midnight Madness", "Upgrader")
                    paused = true
                    companionList.midnightMadnessEvent = "Upgrader"

                    companionList.readLockTower.lock ()
                    try {
                            var towerListIterator = companionList.towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()

                                if (tower.itemListBag.size > 0) {
                                    for (it in tower.itemListBag.shuffled()) {
                                        if (it.id == 0 || it.id == 1 || it.id == 2 || it.id == 3 || it.id == 4 || it.id == 100 || it.id == 101 ||
                                            it.id == 102 || it.id == 103 || it.id == 104 || it.id == 200 || it.id == 201 || it.id == 202 || it.id == 203 ||
                                            it.id == 204 || it.id == 300 || it.id == 301 || it.id == 302 || it.id == 303
                                        ) {
                                            it.upgrade += 3
                                            break
                                        }
                                    }
                                }
                            }
                    }finally {
                        companionList.readLockTower.unlock()
                    }

                    runOnUiThread {
                        bagAdapter.notifyDataSetChanged()
                    }
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Upgrader")
                        intent.putExtra("Description", "Grants a random standard item 3 upgrade points.")
                        startActivity(intent)
                    }, 50)
                }

                // heavy winds (cost)
                5 -> {
                    Log.d("Midnight Madness", "Heavy Winds")
                    paused = true
                    companionList.midnightMadnessEvent = "Heavy Winds"
                    companionList.midnightMadnessWind = true
                    companionList.overallSpdMultiplier -= 20f
                    companionList.midnightMadnessWindLvlSpeedReduce = companionList.lvlSpd * 0.2f
                    companionList.lvlSpd -= companionList.midnightMadnessWindLvlSpeedReduce
                    companionList.midnightMadnessSaveList.add("Winds")

                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Heavy Winds")
                        intent.putExtra("Description", "Enemy speed and tower attack speed reduced by 20%")
                        startActivity(intent)
                    }, 50)
                }

                // more gold (cost)
                6 -> {
                    Log.d("Midnight Madness", "Midas")
                    paused = true
                    companionList.midnightMadnessEvent = "Midas"
                    companionList.midnightMadnessMidas = true
                    companionList.midnightMadnessMidasGold += 0.2f
                    companionList.midnightMadnessMidasGoldCost += 0.2f
                    companionList.midnightMadnessSaveList.add("Midas")

                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Midas")
                        intent.putExtra("Description", "Enemies drop 20% extra gold but items cost 20% more.")
                        startActivity(intent)
                    }, 50)
                }

                // items stolen
                7 -> {
                    Log.d("Midnight Madness", "Darkest Hour")
                    paused = true
                    companionList.midnightMadnessEvent = "Darkest Hour"
                    companionList.midnightMadnessSaveList.add("Darkest Hour")
                    companionList.midnightMadnessDisableItems = true
                    companionList.writeLockTower.lock ()
                    try {
                            var towerListIterator = companionList.towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()

                                repeat(2) {
                                    if ((tower.itemListBag.size - tower.bagSizeElementCount) > 0) {
                                        val randomItemIndex: Int = (0 until (tower.itemListBag.size -1)).random()
                                        var randomItem: Items = tower.itemListBag[randomItemIndex]
                                        if (randomItem.crossedOut || randomItem.element) {
                                        } else {
                                            when {
                                                (companionList.greyItems.contains(randomItem.id)) -> {
                                                    when ((0..1).random()) {
                                                        0 -> {
                                                            bagAdapter.statsDelete(randomItemIndex, tower)
                                                            tower.itemListDisabled.add(tower.itemListBag[randomItemIndex])
                                                            var itemReplace = Items (11, 1, 999, 0, 0f, 0, 0f, 0, randomItem.name, randomItem.image, R.drawable.crossedout, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "", 0f, "", 0f)
                                                            itemReplace.crossedOut = true
                                                            itemReplace.goldCost = 0f
                                                            itemReplace.diaCost = 0
                                                            tower.itemListBag[randomItemIndex] =
                                                                itemReplace
                                                        }
                                                    }
                                                }
                                                (companionList.blueItems.contains(randomItem.id)) -> {
                                                    when ((0..3).random()) {
                                                        0 -> {
                                                            bagAdapter.statsDelete(randomItemIndex, tower)
                                                            tower.itemListDisabled.add(tower.itemListBag[randomItemIndex])
                                                            var itemReplace = Items (11, 1, 999, 0, 0f, 0, 0f, 0, randomItem.name, randomItem.image, R.drawable.crossedout, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "", 0f, "", 0f)
                                                            itemReplace.crossedOut = true
                                                            tower.itemListBag[randomItemIndex] =
                                                                itemReplace
                                                        }
                                                    }
                                                }
                                                (companionList.orangeItems.contains(randomItem.id)) -> {
                                                    when ((0..6).random()) {
                                                        0 -> {
                                                            bagAdapter.statsDelete(randomItemIndex, tower)
                                                            tower.itemListDisabled.add(tower.itemListBag[randomItemIndex])
                                                            var itemReplace = Items (11, 1, 999, 0, 0f, 0, 0f, 0, randomItem.name, randomItem.image, R.drawable.crossedout, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "", 0f, "", 0f)
                                                            itemReplace.crossedOut = true
                                                            tower.itemListBag[randomItemIndex] =
                                                                itemReplace
                                                        }
                                                    }
                                                }
                                                (companionList.purpleItems.contains(randomItem.id)) -> {
                                                    when ((0..9).random()) {
                                                        0 -> {
                                                            bagAdapter.statsDelete(randomItemIndex, tower)
                                                            tower.itemListDisabled.add(tower.itemListBag[randomItemIndex])
                                                            var itemReplace = Items (11, 1, 999, 0, 0f, 0, 0f, 0, randomItem.name, randomItem.image, R.drawable.crossedout, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "", 0f, "", 0f)
                                                            itemReplace.crossedOut = true
                                                            tower.itemListBag[randomItemIndex] =
                                                                itemReplace
                                                        }
                                                    }
                                                }
                                            }
                                            if (companionList.towerClick) {
                                                runOnUiThread {
                                                    bagAdapter.notifyDataSetChanged()
                                                }
                                            }
                                        }
                                    }
                            }
                        }
                    }finally {
                        companionList.writeLockTower.unlock()
                    }

                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Darkest Hour")
                        intent.putExtra("Description", "Enemies are disabling some of your items.") // random roll, chance decreases for rare items
                        startActivity(intent)
                    }, 50)
                }

                // get pirate items
                8 -> {
                    Log.d("Midnight Madness", "Treasure Hunt")
                    paused = true
                    companionList.midnightMadnessEvent = "Treasure Hunt"
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Treasure Hunt")
                        intent.putExtra("Description", "A mysterious box appears. It contains...")
                        startActivity(intent)
                    }, 50)
                }

                // secret shop
                9 -> {
                    Log.d("Midnight Madness", "Secret Shop")
                    paused = true
                    companionList.midnightMadnessEvent = "Secret Shop"
                    runOnUiThread() {
                        shopiconmystery.visibility = View.VISIBLE
                    }
                    GameActivity.companionList.secretShopIconBool = true
                    mHandler.postDelayed({
                        intent = Intent(this, SecretShop::class.java)
                        startActivity(intent)
                    }, 50)
                }
            }
            companionList.midnightMadnessEventRadio = "Midnight Madness: "  +  companionList.midnightMadnessEvent
            companionList.radioList.add(
                companionList.radioList.size, companionList.midnightMadnessEventRadio)
            companionList.radioAdd += 1

        }
        if ((companionList.dayNightHour == 0 && companionList.dayNightMinute == 5) && companionList.mapPick != 0 && companionList.level >= 1 && !companionList.secretShopBool) {
            companionList.secretShopBool = true
        }
        }

        fun mysteryEvents () {

        // update mystery points--------------------------------------------


            when {
                companionList.poisonTowerCount > 0 && companionList.wizardTowerCount > 0 && companionList.iceTowerCount > 0 && companionList.fireTowerCount > 0 && companionList.moonTowerCount > 0 &&
                        companionList.windTowerCount > 0 && companionList.utilsTowerCount > 0 && companionList.darkTowerCount > 0 && companionList.butterflyTowerCount > 0 && companionList.earthTowerCount > 0 && companionList.mysteryAllRounderBool -> {
                            companionList.mysteryAllRounderBool = false
                            paused = true
                            mHandler.postDelayed({
                                intent = Intent(this, MysteryMessage::class.java)
                                intent.putExtra("Title", "All-Rounder")
                                intent.putExtra("Description", "Use one of each elements.")
                                startActivity(intent)
                            }, 500)
                        }
                companionList.magicBoxCount > 4 && companionList.mysteryClownBool -> {
                    companionList.mysteryClownBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Clown")
                        intent.putExtra("Description", "Have 5 or more magic boxes in your inventory.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.maceCount > 3 && companionList.mysteryMaceBool -> {
                    companionList.mysteryMaceBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Strong like a Bear")
                        intent.putExtra("Description", "Have 4 or more maces in your inventory.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.bowCount > 3 && companionList.mysteryBowBool -> {
                    companionList.mysteryBowBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Fast as the Wind")
                        intent.putExtra("Description", "Have 4 or more bows in your inventory.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.swordCount > 3 && companionList.mysterySwordBool -> {
                    companionList.mysterySwordBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Sharp as a Knife")
                        intent.putExtra("Description", "Have 4 or more swords in your inventory.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.bombsUsedCounter == 5 && companionList.mysteryBombsUsedBool -> {
                    companionList.mysteryBombsUsedBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Bomberman")
                        intent.putExtra("Description", "Use 5 bombs.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.challengesKilled == 1 && companionList.challengesKilledBool -> {
                    companionList.challengesKilledBool = false
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Challenged")
                        intent.putExtra("Description", "Kill a challenge.")
                        startActivity(intent)
                    }, 150)
                }
                companionList.mysteryWokeCount > 2 && companionList.mysteryWokeBool -> {
                    companionList.mysteryWokeBool = false
                    paused = true
                    companionList.gold * 0.9f
                    mHandler.postDelayed({
                        intent = Intent(this, MysteryMessage::class.java)
                        intent.putExtra("Title", "Get Woke, Go Broke")
                        intent.putExtra("Description", "Have 3 or more Gender neutral items. Lose 10% of your gold.")
                        startActivity(intent)
                    }, 150)
                }
            }

            companionList.readLockTower.lock()
            try {
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    when {
                        tower.fireRow4Item1 == 3 && tower.iceRow4Item1 == 3 && companionList.mysterySongBool -> {
                            companionList.mysterySongBool = false
                            paused = true
                            mHandler.postDelayed({
                                intent = Intent(this, MysteryMessage::class.java)
                                intent.putExtra("Title", "A song of ice and fire")
                                intent.putExtra("Description", "Put 3 talent points in the fire and ice ultimate ability.")
                                startActivity(intent)
                            }, 500)
                        }
                        tower.luckyCharmCount > 2 && companionList.mysteryLuckyCharmBool -> {
                            companionList.mysteryLuckyCharmBool = false
                            paused = true
                            mHandler.postDelayed({
                                intent = Intent(this, MysteryMessage::class.java)
                                intent.putExtra("Title", "Master of the Occult")
                                intent.putExtra("Description", "Have 3 or more lucky charms in your inventory.")
                                startActivity(intent)
                            }, 150)
                        }
                        tower.pirateItemCount > 1 && companionList.mysteryPirateHunterBool -> {
                            companionList.mysteryPirateHunterBool = false
                            paused = true
                            mHandler.postDelayed({
                                intent = Intent(this, MysteryMessage::class.java)
                                intent.putExtra("Title", "Pirate Hunter")
                                intent.putExtra("Description", "Have 2 or more pirate items in your inventory.")
                                startActivity(intent)
                            }, 150)
                        }

                        companionList.livesMpCounter && tower.iceRow4Item1 == 3 && companionList.mysteryColdHeartBool -> {
                            companionList.mysteryColdHeartBool = false
                            paused = true
                            mHandler.postDelayed({
                                intent = Intent(this, MysteryMessage::class.java)
                                intent.putExtra("Title", "Cold Heart")
                                intent.putExtra("Description", "Buy 5 lives and have ice ultimate skilled.")
                                startActivity(intent)
                            }, 150)
                        }
                    }
                }
                    }finally {
            companionList.readLockTower.unlock()
        }
        }

    fun updateViewModel () {

        // update ViewModel--------------------------------------------

        // update gains for UI ----------------------------------------------
        if (companionList.goldGain) companionList.goldGainCount++
        if (companionList.goldGainCount >= 120) {
            companionList.goldGainCount = 0
            companionList.goldGain = false
        }
        if (companionList.diamondsGain) companionList.diamondsGainCount++
        if (companionList.diamondsGainCount >= 120) {
            companionList.diamondsGainCount = 0
            companionList.diamondsGain = false
        }
        if (companionList.interestGain) companionList.interestGainCount++
        if (companionList.interestGainCount >= 120) {
            companionList.interestGainCount = 0
            companionList.interestGain = false
        }
        if (companionList.upgradePointsGain) companionList.upgradePointsGainCount++
        if (companionList.upgradePointsGainCount >= 120) {
            companionList.upgradePointsGainCount = 0
            companionList.upgradePointsGain = false
        }
        if (companionList.itemPointsGain) companionList.itemPointsGainCount++
        if (companionList.itemPointsGainCount >= 120) {
            companionList.itemPointsGainCount = 0
            companionList.itemPointsGain = false
        }
        if (companionList.itemChanceGain) companionList.itemChanceGainCount++
        if (companionList.itemChanceGainCount >= 120) {
            companionList.itemChanceGainCount = 0
            companionList.itemChanceGain = false
        }
        if (companionList.itemQualityGain) companionList.itemQualityGainCount++
        if (companionList.itemQualityGainCount >= 120) {
            companionList.itemQualityGainCount = 0
            companionList.itemQualityGain = false
        }
        if (companionList.bagSizeGain) companionList.bagSizeGainCount++
        if (companionList.bagSizeGainCount >= 120) {
            companionList.bagSizeGainCount = 0
            companionList.bagSizeGain = false
        }
        if (companionList.damageGain) companionList.damageGainCount++
        if (companionList.damageGainCount >= 120) {
            companionList.damageGainCount = 0
            companionList.damageGain = false
        }
        if (companionList.phyDamageGain) companionList.phyDamageGainCount++
        if (companionList.phyDamageGainCount >= 120) {
            companionList.phyDamageGainCount = 0
            companionList.phyDamageGain = false
        }
        if (companionList.mgcDamageGain) companionList.mgcDamageGainCount++
        if (companionList.mgcDamageGainCount >= 120) {
            companionList.mgcDamageGainCount = 0
            companionList.mgcDamageGain = false
        }
        if (companionList.spdGain) companionList.spdGainCount++
        if (companionList.spdGainCount >= 120) {
            companionList.spdGainCount = 0
            companionList.spdGain = false
        }
        if (companionList.critChanceGain) companionList.critChanceGainCount++
        if (companionList.critChanceGainCount >= 120) {
            companionList.critChanceGainCount = 0
            companionList.critChanceGain = false
        }
        if (companionList.critDamageGain) companionList.critDamageGainCount++
        if (companionList.critDamageGainCount >= 120) {
            companionList.critDamageGainCount = 0
            companionList.critDamageGain = false
        }
        if (companionList.multiCritGain) companionList.multiCritGainCount++
        if (companionList.multiCritGainCount >= 120) {
            companionList.multiCritGainCount = 0
            companionList.multiCritGain = false
        }
        if (companionList.hitGain) companionList.hitGainCount++
        if (companionList.hitGainCount >= 120) {
            companionList.hitGainCount = 0
            companionList.hitGain = false
        }
        if (companionList.armorPenGain) companionList.armorPenGainCount++
        if (companionList.armorPenGainCount >= 120) {
            companionList.armorPenGainCount = 0
            companionList.armorPenGain = false
        }
        if (companionList.magicArmorPenGain) companionList.magicArmorPenGainCount++
        if (companionList.magicArmorPenGainCount >= 120) {
            companionList.magicArmorPenGainCount = 0
            companionList.magicArmorPenGain = false
        }

        // update ViewModel--------------------------------------------

        updateViewModel.onLvlUp()
        updateViewModel.update()
        updateViewModel.updateXp()
        updateViewModel.updateLvlStatus()
        updateViewModel.updateCounter()
        updateViewModel.updateLvlStats ()

    }

    fun selected () {

        companionList.readLockEnemy.lock ()
        try {
        if (companionList.enemyClick) {
            var enemyListIterator = companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()
                if (it.selected) {
                    companionList.enemyMaxHp = it.maxHp
                    companionList.enemyHp = it.hp
                    companionList.enemyArmor = it.armor
                    companionList.enemyArmorReduction = 100 - (100 * armorPen(it, Tower(0f,0f, 0f,0f,0f,0f)))
                    companionList.enemyMagicArmor = it.magicArmor
                    companionList.enemyMagicArmorReduction = 100 - (100 * magicPen(it, Tower(0f,0f, 0f,0f,0f,0f)))
                    companionList.enemyEvade = it.evade
                    companionList.enemyHpReg = it.hpReg
                    companionList.enemySpd = it.speed
                    companionList.enemyShield = it.shield
                    companionList.enemyShieldMax = it.shieldMax
                    companionList.enemyManaShield = it.manaShield
                    companionList.enemyManaShieldMax = it.manaShieldMax
                    companionList.enemyType = it.name
                    break
                }
            }
        }
        }finally {
            companionList.readLockEnemy.unlock()
        }
    }



    fun updateRandomThings () {

        companionList.writeLockEnemy.lock ()
        try {

            var enemyListIterator = companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                // enemy hp updates
                var hpDebuffPlace = if (it.hpRegDebuff + companionList.hpRegDebuffGlobal > 0.9f) 0.9f else it.hpRegDebuff + companionList.hpRegDebuffGlobal
                if (it.hpReg > 0 && it.hp < it.maxHp && it.hp > 0) it.hp += (it.hpReg * (1 - hpDebuffPlace))
                if (it.hp > it.maxHp) it.hp = it.maxHp

                // movement speed
                if (it.baseSpeed < 0.2f && !it.entangled && it.throwBoulderHitAlreadyEffected && !it.feared && it.itemLassoAlreadyAffected >= 0 && it.markOfTheButterflyStun < 1) it.baseSpeed =
                    0.2f
                it.speed = (it.baseSpeed + it.extraSpeed)

                // heal
                if (companionList.levelList.contains("healer")) {
                    if (it.name == "healer") {
                        companionList.healCounter ++
                        if (companionList.healCounter >= 30){
                            for (it in companionList.enemyList.shuffled()){
                                if (it.hp < it.maxHp) {
                                    it.hp += (it.maxHp/5) * (1 - it.hpRegDebuff - companionList.hpRegDebuffGlobal)
                                    companionList.healCounter = 0
                                    break
                                }
                            }
                        }
                    }
                }

                // disruptor
                if (companionList.levelList.contains("disruptor")) {
                    if (it.name == "disruptor") {
                        companionList.disruptorCounter ++
                        if (companionList.disruptorCounter >= 90){
                            for (tower in companionList.towerList.shuffled()){
                                companionList.disruptorCounter = 0
                                tower.disrupted = true
                                break
                            }
                        }
                    }
                }

                // dmg debuff
                var butterflyDebuffEnemyDmgAlreadyEffectedPlace = if (it.butterflyDebuffEnemyDmgAlreadyEffected) companionList.towerList[it.butterflyDebuffEnemyDmgTowerId].butterflyDebuffEnemyDmg else 0f

                it.dmgTakenDebuff = 1f + butterflyDebuffEnemyDmgAlreadyEffectedPlace

                // gold and xp debuff
                var butterflyDebuffEnemyGoldXpAlreadyEffectedPlace = if (it.butterflyDebuffEnemyGoldXpAlreadyEffected) companionList.towerList[it.butterflyDebuffEnemyGoldXpTowerId].butterflyDebuffEnemyGoldXp else 0f
                it.xpDropDebuff = 1f + butterflyDebuffEnemyGoldXpAlreadyEffectedPlace
                it.goldDropDebuff = 1f + butterflyDebuffEnemyGoldXpAlreadyEffectedPlace

                it.overallXp = it.xpEnemy * it.xpDropDebuff * companionList.wiseMan
                it.xpDrop = 1 * it.xpDropDebuff * companionList.wiseMan
                it.overallGold = it.xpEnemy * it.goldDropDebuff * companionList.midnightMadnessMidasGold

            }
        }finally {
            companionList.writeLockEnemy.unlock()
        }

        if (companionList.towerList.size > 0) {
            companionList.readLockTower.lock()
            try {
                companionList.poisonTowerHighestDmg.overallTowerSpellDmg = 0f
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.itemListBag.contains(companionList.epoison)){
                        if (tower.overallTowerSpellDmg > companionList.poisonTowerHighestDmg.overallTowerSpellDmg)
                            companionList.poisonTowerHighestDmg = tower
                    }
                    if (tower.itemListBag.contains(companionList.ewizard)){
                        if (tower.overallTowerSpellDmg > companionList.wizardTowerHighestDmg.overallTowerSpellDmg)
                            companionList.wizardTowerHighestDmg = tower
                    }
                }
            } finally {
                companionList.readLockTower.unlock()
            }
        }
    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

    private fun updateShots () {

        // Ice Talent

        if (companionList.towerList.isNotEmpty()) {
            companionList.writeLockTower.lock()
            try {
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.iceShard > 0) {
                        tower.iceShardCounter++
                        if (tower.iceShardCounter > 3) {
                            tower.iceShardCounter = 0
                        var shootListIceIterator = tower.shootListIce.listIterator()
                        while (shootListIceIterator.hasNext()) {
                            var shard = shootListIceIterator.next()
                            when (tower.iceShard) {
                                1 -> {
                                    shard.shardSpeedBasic = 3f
                                }
                                2 -> {
                                    shard.shardSpeedBasic = 4.5f
                                }
                                3 -> {
                                    shard.shardSpeedBasic = 6f
                                }
                            }
                            shard.shardSpeed += 0.01f
                            if (shard.shardSpeed < 0) shard.shardSpeed = 0f
                            if (shard.shardSpeed > shard.shardSpeedBasic) shard.shardSpeed = shard.shardSpeedBasic
                            if (companionList.enemyList.isEmpty()) shard.shardSpeed = shard.shardSpeedBasic

                                if (crossesShard(shard)) {
                                    shard.shard.x = shard.shardStart.x
                                    shard.shard.y = shard.shardStart.y
                                }
                            }
                        }
                        if (tower.shootListIce.isEmpty()) {
                            var times = 0.0f
                            repeat(8) {
                                var shardPlace = ShootIceTalent()
                                shardPlace.iceShardTowerId = companionList.towerList.indexOf(tower)
                                shardPlace.shard =
                                    TowerRadius(tower.towerRange.x, tower.towerRange.y, 10f)
                                shardPlace.shardStart =
                                    TowerRadius(tower.towerRange.x, tower.towerRange.y, 10f)
                                shardPlace.direction = (times)
                                tower.shootListIce.add(shardPlace)
                                times += 1.5f
                            }
                        }
                    }
                }
            } finally {
                companionList.writeLockTower.unlock()
            }
        }

        // poison talent
        if (companionList.poisonCloud > 0 && companionList.poisonTowerCount > 0) {
            companionList.writeLockPoison.lock()
            try {
                companionList.poisonCloudCounter += 1
                if (companionList.poisonCloudCounter == 600) {
                    var shootListPoisonIterator = companionList.shootListPoison.listIterator()
                    var shootpoisontalentbool = ShootPoisonTalent()
                    shootpoisontalentbool.poisonCloud = TowerRadius(companionList.poisonTowerHighestDmg.towerRange.x, companionList.poisonTowerHighestDmg.towerRange.y, 50.0f)
                    shootListPoisonIterator.add(shootpoisontalentbool)
                    companionList.poisonCloudCounter = 0
                }
                if (companionList.poisonCloudCounter == 550 && companionList.shootListPoison.isNotEmpty()) {
                    var shootListPoisonIterator = companionList.shootListPoison.listIterator()
                    while (shootListPoisonIterator.hasNext()) {
                        var it = shootListPoisonIterator.next()
                        it.broken = 1
                    }
                }
                companionList.poisonCloudPlaceholderCounter++
                if (companionList.poisonCloudPlaceholderCounter >= 10) {
                    companionList.poisonCloudPlaceholderCounter = 0
               //     for (it in shootListPoison)
                    var shootListPoisonIterator = companionList.shootListPoison.listIterator()
                    while (shootListPoisonIterator.hasNext()) {
               //         CoroutineScope(Dispatchers.Default).launch {
                            var it = shootListPoisonIterator.next()
                            var poisonCloudPlaceholder = it.poisonCloud
                            crossesPoison(poisonCloudPlaceholder)
                        }
                }
            } finally {
                companionList.writeLockPoison.unlock()
            }
        }

        // Tornado
        if (companionList.tornadoRadius > 0) {
            companionList.writeLockTornado.lock()
            try {
                companionList.tornadoCounter++
                if (companionList.tornadoCounter >= companionList.tornadoTimer && companionList.enemyList.isNotEmpty()) {
                    companionList.tornadoCounter = 0
                    var shootListTornadoIterator = companionList.shootListTornado.listIterator()
                    var shootTornadoTalentPlace = ShootTornadoTalent()
                    shootTornadoTalentPlace.tornadoRadius =
                        TowerRadius((0..1200).random().toFloat(), (0..1600).random()
                            .toFloat(), companionList.tornadoRadius)
                    shootListTornadoIterator.add(shootTornadoTalentPlace)
                }

                if (companionList.shootListTornado.isNotEmpty()) {
                    var shootListTornadoIterator = companionList.shootListTornado.listIterator()
                    while (shootListTornadoIterator.hasNext()) {
                        var it = shootListTornadoIterator.next()
                        if (it.tornadoRadiusPosition == 2) it.tornadoCount++
                        if (it.tornadoCount >= 100) {
                            it.broken = 1
                            it.randomEnemyTornadoBool = true
                        }
                    }
                }

                if (companionList.enemyList.isNotEmpty()) {
                    companionList.tornadoPlaceholderCounter++
                    if (companionList.tornadoPlaceholderCounter >= 3) {
                        companionList.tornadoPlaceholderCounter = 0
                        //     for (it in shootListTornado)
                        //     CoroutineScope(Dispatchers.Default).launch {
                        var shootListTornadoIterator = companionList.shootListTornado.listIterator()
                        while (shootListTornadoIterator.hasNext()) {
                            var it = shootListTornadoIterator.next()
                            var tornadoCloudPlaceholder = it.tornadoRadius
                            crossesTornado(tornadoCloudPlaceholder)
                        }
                    }
                }

            } finally {
                companionList.writeLockTornado.unlock()
            }
        }

        // mine talent
        if (companionList.wizardMine) {
            companionList.writeLockMine.lock()
            try {
                companionList.wizardMineCounter++
                if (companionList.wizardMineCounter >= companionList.wizardMineTimer) {
                    companionList.wizardMineCounter = 0
                    if (companionList.wizardMinePlaced < 21) {
                        var shootListMineIterator = companionList.shootListMine.listIterator()
                        shootListMineIterator.add(ShootMineTalent())
                        companionList.wizardMinePlaced += 1
                    }
                }
                companionList.minePlaceholderCounter++
                if (companionList.minePlaceholderCounter >= 3) {
                    companionList.minePlaceholderCounter = 0
                    var shootListMineIterator = companionList.shootListMine.listIterator()
                    while (shootListMineIterator.hasNext()) {
                        var it = shootListMineIterator.next()
              //          for (it in shootListMine)
              //          CoroutineScope(Dispatchers.Default).launch {
                            if (it.broken == false) {
                                var mineRadiusPlaceholder = it.mineRadius
                                if (crossesMine(mineRadiusPlaceholder)) {
                                    companionList.wizardMinePlaced -= 1
                                    it.broken = true
                                }
                            }
                        }
                }
            } finally {
                companionList.writeLockMine.unlock()
            }
        }

        // update shot
        companionList.writeLockShot.lock()
        try {
                  var shootListIterator = companionList.shootList.listIterator()
                  while (shootListIterator.hasNext()) {
                      var it = shootListIterator.next()
             //   for (it in shootList)
             //   CoroutineScope(Dispatchers.Default).launch {

                      it.collisionCount++

                      if (it.collisionCount >= 3) {
                          it.collisionCount = 0
                          if (it.broken != 1) {
                              if (it.chainLightning) {
                                  if (it.bounceLeft > 0) {
                                      if (crossesChainLightning(it)) {
                                          it.alreadyBouncedResetChain = false
                                          it.alreadyBounced += 1
                                          it.bounceLeft -= 1
                                          if (inBounceRange(it.bullet) < 1) {
                                              it.broken = 1
                                              companionList.towerList[it.towerId].randomEnemyChainBool =
                                                  true
                                          }
                                          if (it.bounceLeft == 0) {
                                              it.broken = 1
                                              companionList.towerList[it.towerId].randomEnemyChainBool =
                                                  true
                                          }
                                      }
                                  } else {
                                      it.broken = 1
                                      companionList.towerList[it.towerId].randomEnemyChainBool =
                                          true
                                  }
                              } else if (companionList.towerList[it.towerId].towerPrimaryElement == "moon") {
                                  if (it.bounceLeft > 0) {
                                      if (crosses2(it)) {
                                          companionList.rotationBulletY = it.bullet.y
                                          companionList.rotationBulletX = it.bullet.x
                                          it.alreadyBouncedReset = false
                                          companionList.towerList[it.towerId].randomEnemyForShotBool =
                                              true
                                          it.alreadyBounced += 1
                                          it.bounceLeft -= 1
                                          if (inBounceRange(it.bullet) <= 1) {
                                              it.broken = 1
                                              companionList.towerList[it.towerId].randomEnemyForShotBool =
                                                  true
                                          }
                                          if (it.bounceLeft == 0) {
                                              it.broken = 1
                                              companionList.towerList[it.towerId].randomEnemyForShotBool =
                                                  true
                                          }
                                      }
                                  } else {
                                      companionList.towerList[it.towerId].randomEnemyForShotBool =
                                          true
                                      it.broken = 1
                                  }
                              } else {

                                  if (crosses2(it)) {
                                      it.broken = 1
                                      companionList.towerList[it.towerId].randomEnemyForShotBool =
                                          true
                                  }


                                  if (companionList.enemyList.isEmpty()) {
                                      it.broken = 1
                                      companionList.towerList[it.towerId].randomEnemyForShotBool =
                                          true
                                  }

                              }
                          }
                      }
                }
        } finally {
            companionList.writeLockShot.unlock()
        }

    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

         private fun updateShit () {

             crossesAll()

             // lives
             if (companionList.mapMode == 2) {
                 companionList.lives = companionList.enemyList.size + companionList.enemySizeBoss
                 companionList.enemySizeBoss = 0
             }

             // -----------------------------------------------------------------------------------------
             // update tower ----------------------------------------------------------------------------

             var poisonTowerCountPlace = 0
             var wizardTowerCountPlace = 0
             var iceTowerCountPlace = 0
             var fireTowerCountPlace = 0
             var moonTowerCountPlace = 0
             var windTowerCountPlace = 0
             var utilsTowerCountPlace = 0
             var darkTowerCountPlace = 0
             var butterflyTowerCountPlace = 0
             var earthTowerCountPlace = 0


             companionList.writeLockTower.lock()
             try {
                 var towerListIterator = companionList.towerList.listIterator()
                 while (towerListIterator.hasNext()) {
                     var it = towerListIterator.next()


                     // tutorial talents
                     if (it.towerLevel == 2 && companionList.hintsBool && companionList.tutorialFirstTowerLevelBool) {
                         companionList.tutorialFirstTowerLevelBool = false
                         paused = true
                         mHandler.postDelayed({
                             intent = Intent(this, TutorialTalents::class.java)
                             startActivity(intent)
                         }, 50)
                     }

                     var bagSizeElementCountPlace = 0

                     if (it.itemListBag.contains(companionList.epoison)) {
                         bagSizeElementCountPlace++
                         poisonTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.ewizard)) {
                         bagSizeElementCountPlace++
                         wizardTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.eice)) {
                         bagSizeElementCountPlace++
                         iceTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.efire)) {
                         bagSizeElementCountPlace++
                         fireTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.emoon)) {
                         bagSizeElementCountPlace++
                         moonTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.ewind)) {
                         bagSizeElementCountPlace++
                         windTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.eutils)) {
                         bagSizeElementCountPlace++
                         utilsTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.edark)) {
                         bagSizeElementCountPlace++
                         darkTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.ebutterfly)) {
                         bagSizeElementCountPlace++
                         butterflyTowerCountPlace++
                     }
                     if (it.itemListBag.contains(companionList.eearth)) {
                         bagSizeElementCountPlace++
                         earthTowerCountPlace++
                     }

                     it.bagSizeElementCount = bagSizeElementCountPlace


                     var extraDmgAura = 0f
                     var extraSpdAura = 0f
                     var extraItemChance = 0f
                     var extraItemQuality = 0f
                     var markOfTheButterflySpdBoostTowersNumberPlace = 0

                     var towerListIteratorZ = companionList.towerList.listIterator()
                     while (towerListIteratorZ.hasNext()) {
                         var tower = towerListIteratorZ.next()
                         if (it != tower) {
                             if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                                 if (tower.earthExtraTowerDmg > 0) extraDmgAura += tower.earthExtraTowerDmg
                                 if (tower.windExtraTowerSpd > 0) extraSpdAura += tower.windExtraTowerSpd
                                 if (tower.itemListBag.contains(companionList.eutils)) extraDmgAura += 0.05f
                                 if (tower.itemListBag.contains(companionList.eutils)) extraSpdAura += 5f
                                 if (tower.itemChanceAura > 0) extraItemChance += tower.itemChanceAura
                                 if (tower.itemQualityAura > 0) extraItemQuality += tower.itemQualityAura
                                 if (it.markOfTheButterflySpdBoost > 0 && it.towerPrimaryElement == "butterfly") markOfTheButterflySpdBoostTowersNumberPlace++
                             }
                         }
                     }


                     it.markOfTheButterflySpdBoostTowersNumber =
                         markOfTheButterflySpdBoostTowersNumberPlace

                     if (it.disrupted) {
                         it.disruptedCounter++
                         if (it.disruptedCounter > 180){
                             it.disruptedCounter = 0
                             it.disrupted = false
                        }
                    }

                     // level
                     when (it.xpTower.toInt()){
                         in 0..it.xpGoal2.toInt() -> {}
                         else -> {
                             it.towerLevel ++
                             it.talentPoints ++
                             it.towerRarityMultiplier += 0.02f
                             it.dmg += (it.towerLevel /2)
                             itemListBagAddMultiplier(it)
                             var xp2 = it.xpGoal2
                             it.xpGoal1 = xp2
                             it.xpGoal2 = xp2 + (xp2 * 1.25f)
                         }
                     }

                     if (it.towerLevelBool){
                         it.towerLevelBool = false
                         var xp2 = it.xpGoal2
                         repeat(3){
                                 _ -> itemListBagAddMultiplier(it)
                             it.xpGoal1 = xp2
                             it.xpGoal2 = xp2 + (xp2 * 1.25f)
                             xp2 = it.xpGoal2
                             it.dmg += (it.towerLevel /2)
                             it.towerLevel ++
                             it.talentPoints ++
                             it.towerRarityMultiplier += 0.02f
                         }
                         it.xpTower = it.xpGoal1
                     }

                     // tower speed
                     if (it.bonusSpeedWindTalent > 0) {
                         if (it.crossesAllList.size == 0) it.bonusSpeedWindTalentPercent = 0.0f
                         if (it.bonusSpeedWindTalentPercent > 100) it.bonusSpeedWindTalentPercent =
                             100f
                     }

                     if (it.markOfTheButterflySpdBoostActive) {
                         it.markOfTheButterflySpdBoostActiveCounter++
                         if (it.markOfTheButterflySpdBoostActiveCounter >= 180) {
                             it.markOfTheButterflySpdBoostActiveCounter = 0
                             it.markOfTheButterflySpdBoostActive = false
                             it.markOfTheButterflySpdBoostActiveNumber = 0f
                         }
                     }

                     it.overallTowerSpd = it.speed - (it.speed * ((companionList.overallSpdMultiplier + extraSpdAura) / 100))

                     var talentBonusSpeed = 0f
                     if (it.itemListBag.contains(companionList.ewind)) talentBonusSpeed += companionList.windTalentBonusSpeed
                     companionList.levelScalerSpeedBool =
                         (it.bonusTowerSpeed + talentBonusSpeed) * it.utilsUltimate * companionList.levelScalerSpeed
                     when {
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 1000 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 4f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 16f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 32f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 64f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 128f)) +
                                             (((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 1000f) / 100f)) / 256f)))
                         }
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 500 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 4f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 16f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 32f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 64f)) +
                                             (((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 500f) / 100f)) / 128f)))
                         }
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 300 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 4f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 16f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 32f)) +
                                             (((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 300f) / 100f)) / 64f)))
                         }
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 200 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 4f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 16f)) +
                                             (((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 200f) / 100f)) / 32f)))
                         }
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 100 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 4f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 100f) / 100f)) / 16f)))
                         }
                         (companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 50 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f) / 4f)) +
                                             ((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 50f) / 100f)) / 8f))
                         }
                         else -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((companionList.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) / 100f)) / 4f)
                         }
                     }

                     if (it.poisonOverload > 0) it.poisonOverloadCounter++
                     if (it.poisonOverload > 0 && it.poisonOverloadCounter >= it.poisonOverload && it.crossesAllList.isNotEmpty()) it.poisonOverloadActive =
                         true
                     if (it.poisonOverloadActive) it.poisonOverloadDuration++
                     if (it.poisonOverloadDuration < 100 && it.poisonOverloadActive) {
                         it.towerAttackSpeed = it.towerAttackSpeed / 2
                     } else if (it.poisonOverloadDuration >= 100) {
                         it.poisonOverloadDuration = 0
                         it.poisonOverloadCounter = 0
                         it.poisonOverloadActive = false
                     }
                     if (it.itemListBag.contains(companionList.eearth)) it.towerAttackSpeed *= 2

                     if (it.disrupted) it.towerAttackSpeed = 999f
                     if (it.towerAttackSpeed <= 3) it.towerAttackSpeed = 3f
                     it.towerAttackSpeedShow = (it.towerAttackSpeed / 60)

                     // tower crit
                     var talentFireBonusCrit= 0f
                     if (it.itemListBag.contains(companionList.efire)) talentFireBonusCrit += 10f
                     var levelScalerCritBool = (it.bonusCrit + talentFireBonusCrit) * it.utilsUltimate * companionList.levelScalerCrit
                     it.overallCrit = ((((it.crit + levelScalerCritBool) * 0.03f) / (1 + (0.03f * (it.crit + levelScalerCritBool + 50)))) * 100)

                     // crit damage
                     var levelScalerCritDmgBool = 0f
                     var talentFireBonusCritDmg = 0f
                     if (it.itemListBag.contains(companionList.efire)) talentFireBonusCritDmg += 0.5f
                     if (it.fireTalentBonusCritDmg > 0) talentFireBonusCritDmg += it.fireTalentBonusCritDmg
                     levelScalerCritDmgBool = (it.bonusCritDmg + talentFireBonusCritDmg) * companionList.levelScalerCritDmg
                     if (it.itemListBag.contains(companionList.efire)) levelScalerCritDmgBool += (it.bonusCritFire * it.critCounter)
                     it.overallCritDmg = it.critDmg + levelScalerCritDmgBool

                     // multicrit
                     if (it.fireRow4Item1 == 3) it.fireUltimateMulticritBonus =
                         (floor(companionList.level * 0.05f)).toInt()
                     it.overallMulticrit =
                         it.bonusmultiCrit + it.fireUltimateMulticritBonus

                     // tower dmg
                     if (companionList.mapMode != 2) it.bonusDamageMultiplyerItemLastStance =
                         ((8f - companionList.lives) * it.itemLastStance)
                     var talentMultiplierPoison = if (companionList.enemiesPoisoned > 0 && it.poisonDmgMultiplier) (companionList.enemiesPoisoned * 3 * 0.01f) else 0f
                     it.overallDamageMultiplyer =
                         1 + it.bonusDamageMultiplyer + it.bonusDamageMultiplyerItemLastStance + companionList.globalDmgMultiplier + talentMultiplierPoison + ( 1 - it.utilsUltimate)

                     if (companionList.day) it.overallTowerDmg =
                         (it.dmg + it.bonusTowerDmg + itemsDmg(it)) * (it.overallDamageMultiplyer + extraDmgAura)
                     else it.overallTowerDmg =
                         (it.dmg + it.bonusTowerDmg + itemsDmg(it)) * (it.overallDamageMultiplyer + extraDmgAura) * it.damageMultiplyerNight
                     it.overallTowerDmgBool = it.overallTowerDmg
                     it.overallTowerDmg *= companionList.bigNumberScaler
                     if (it.disrupted) it.overallTowerDmg = 0f

                     // physical dmg
                     var talentPhysicalDmgMultiplyer = 1f
                     if (it.itemListBag.contains(companionList.eearth)) talentPhysicalDmgMultiplyer += it.earthTalentPhyDmgMultiplier
                     var talentBonusPhysicalDmg = 0f
                     if (it.itemListBag.contains(companionList.eearth)) talentBonusPhysicalDmg += companionList.earthTalentBonusPhysicalDmg
                     it.overallTowerPhysicalDmg =
                         (it.phyDmg + it.overallTowerDmgBool + it.bonusPhysicalDmg + talentBonusPhysicalDmg) * talentPhysicalDmgMultiplyer * companionList.bigNumberScaler
                     if (it.disrupted) it.overallTowerPhysicalDmg = 0f

                     // spell dmg
                     var spellDmgTalentX = 0f
                     if (it.talentWizardLvlToDmg) spellDmgTalentX += ((it.towerLevel * 3) * 0.01f)
                     it.overallTowerSpellDmg =
                         (it.mgcDmg + it.overallTowerDmgBool + (it.overallTowerDmgBool * (companionList.globalBonusSpellDmgPercent * 0.01f)) + it.bonusSpellDamage) * (it.overallSpellDmgMultiplyer + spellDmgTalentX) * companionList.bigNumberScaler
                     if (it.disrupted) it.overallTowerSpellDmg = 0f

                     // hit chance
                     var talentBonusHitChance = 0f
                     if (it.itemListBag.contains(companionList.eearth)) talentBonusHitChance += it.earthTalentBonusHitChance
                     it.hitChance =
                         100.0f + it.itemBonusHitChance + it.bonusHitChance + talentBonusHitChance

                     // item chance
                     it.overallItemChance =
                         (companionList.itemChance + ((companionList.itemChance * ((it.bonusItemChance + companionList.globalBonusItemChance + extraItemChance) * 0.01f)))) * companionList.levelScalerItemChance

                     // item quality
                     it.overallItemQuality = (it.bonusItemQuality + companionList.globalBonusItemQuality + extraItemQuality) * companionList.levelScalerItemQuality

                     // tower range
                     var talentBonusRange = 0f
                     if (it.windTowerBonusTowerRange > 0) talentBonusRange += it.windTowerBonusTowerRange
                     else talentBonusRange += companionList.globalBonusTowerRange
                     it.towerR = it.towerRange.r + talentBonusRange + it.towerBonusRange

                     // armor pen
                     it.overallBonusArmorPen = it.bonusArmorPen + companionList.globalArmorPen

                     // magic pen
                     it.overallBonusMagicPen = it.bonusMagicPen + companionList.globalMagicPen

                     // dmg immune
                     it.overallDmgImmune = 0.01f + it.bonusDmgImmune

                     // evade night
                     if (companionList.day) companionList.evadeNight = 0f
                     else {
                         if (companionList.moonTalentEvadeNight) companionList.evadeNight = 0f
                         else companionList.evadeNight = (ceil(companionList.level * 0.1f))
                     }
                 }

                 companionList.poisonTowerCount = poisonTowerCountPlace
                 companionList.wizardTowerCount = wizardTowerCountPlace
                 companionList.iceTowerCount = iceTowerCountPlace
                 companionList.fireTowerCount = fireTowerCountPlace
                 companionList.moonTowerCount = moonTowerCountPlace
                 companionList.windTowerCount = windTowerCountPlace
                 companionList.utilsTowerCount = utilsTowerCountPlace
                 companionList.darkTowerCount = darkTowerCountPlace
                 companionList.butterflyTowerCount = butterflyTowerCountPlace
                 companionList.earthTowerCount = earthTowerCountPlace

                 } finally {
                 companionList.writeLockTower.unlock()
                 }

         }

    private fun itemListBagAddMultiplier(it:Tower) {

        for (pos in it.itemListBag){

            it.bonusTowerSpeed += (pos.speed * 0.02f)
            pos.speed *= 1.02f
            it.bonusTowerDmg += (pos.dmg * 0.02f)
            pos.dmg *= 1.02f
            it.bonusPhysicalDmg += (pos.atkDmg * 0.02f)
            pos.atkDmg *= 1.02f
            it.bonusSpellDamage += (pos.mgcDmg * 0.02f)
            pos.mgcDmg *= 1.02f
            it.bonusCrit += (pos.crit * 0.02f)
            pos.crit *= 1.02f
            it.bonusCritDmg += (pos.critDmg * 0.02f)
            pos.critDmg *= 1.02f

            if (pos.id == 4) {
                it.bonusItemChance += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }

            if (pos.id == 6) {
                companionList.gold += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }

            if ((pos.id == 100 || pos.id == 102 || pos.id == 103) && pos.special2 == "item find") {

                it.bonusItemChance += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }
            if ((pos.id == 100 || pos.id == 102 || pos.id == 103) && pos.special2 == "item quality") {

                it.bonusItemQuality += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }
            if (pos.id == 104 || pos.id == 204) {

                it.bonusItemQuality += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }
            if ((pos.id == 200 || pos.id == 202 || pos.id == 203) && pos.special == "armor penetration"){

                it.bonusArmorPen += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if ((pos.id == 200 || pos.id == 202 || pos.id == 203) && pos.special == "magic penetration") {

                it.bonusMagicPen += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if ((pos.id == 200 || pos.id == 202 || pos.id == 203) && pos.special == "hit chance"){

                it.itemBonusHitChance += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if (pos.id == 212){

                it.itemBonusHitChance += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if (pos.id == 215){

                it.bonusArmorPen += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if (pos.id == 216){

                it.bonusMagicPen += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if ((pos.id == 300 || pos.id == 302 || pos.id == 303) && pos.special2 == "item find"){

                it.bonusItemChance += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }
            if ((pos.id == 300 || pos.id == 302 || pos.id == 303) && pos.special2 == "item quality"){

                it.bonusItemQuality += (pos.specialFloat2 * 0.02f)
                pos.specialFloat2 *= 1.02f
            }
            if (pos.id == 314) {

                it.armorPenPerHit += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
            if (pos.id == 315) {

                it.magicPenPerHit += (pos.specialFloat * 0.02f)
                pos.specialFloat *= 1.02f
            }
        }
    }


    //----------------------------------------------------------------------------------

        private fun enemyDots () {

            if (companionList.towerList.size > 0) {

                var enemiesPoisonedPlace = 0

                companionList.writeLockEnemy.lock()
                companionList.writeLockTower.lock()
                companionList.writeLockDisplay.lock()
                try {
                    var enemyListIterator = companionList.enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()
                        //       for (it in enemyList)
                        //           CoroutineScope(Dispatchers.Default).launch {

                        // stats ---------------------------------------------------------------------------------------

                        // lives
                        if (companionList.mapMode == 2) {
                            if (it.name == "boss" || it.name == "challenge") {
                                companionList.enemySizeBoss += 3
                            }
                        }

                        //---------------------------------------------------------------------------------------

                        // invu

                        if (it.name == "invu"){
                            when ((0..500).random()){
                                0 -> it.invuTime = 120
                            }
                        } else if (GameActivity.companionList.levelList.contains("invu")){
                             when ((0..2500).random()){
                                0 -> it.invuTime = 90
                            }
                        }

                        if (it.invuTime > 0){
                            it.invu = true
                            it.invuTime--
                            deactivateAllDebuffs(it)
                        } else it.invu = false

                        // ice nova

                        if (it.iceNovaAlreadyAffected) it.iceNovaAlreadyAffectedCounter++
                        if (it.iceNovaAlreadyAffectedCounter >= 75) {
                            iceNovaDeactivate (it)
                        }

                        // ice Debuff

                        if (it.iceDebuff == 1) {
                            if (it.iceAlreadyAffected == 0) {
                                if (it.name == "speed") it.extraSpeed = 0.0f       // counter speed
                                if (it.name == "immune") it.iceSlowEachSpeedReduce =
                                    (it.baseSpeed * (companionList.towerList[it.iceDebuffTowerId].slowEach / 100)) * companionList.towerList[it.iceDebuffTowerId].overallDmgImmune.toFloat()
                                else it.iceSlowEachSpeedReduce =
                                    (it.baseSpeed * (companionList.towerList[it.iceDebuffTowerId].slowEach / 100)).toFloat()
                                it.baseSpeed -= it.iceSlowEachSpeedReduce
                                it.iceDebuff += 1
                                it.iceAlreadyAffected = 1
                                if(companionList.towerList[it.iceDebuffTowerId].slowExtraMgcDmg) it.debuffExtraMgcDmg = true
                                if (companionList.towerList[it.iceDebuffTowerId].experienceSlow) towerExperience(it.iceDebuffTowerId, 0.03f)
                            } else it.iceDebuff += 1
                        } else if (it.iceDebuff > 90) {
                            iceDebuffDeactivate(it)
                        } else if (it.iceDebuff > 0) it.iceDebuff += 1


                        // dark talent dmg debuff

                        if (it.darkMoreDmgDebuff == 1) {
                            it.darkMoreDmgDebuff++
                            it.darkMoreDmgDebuffComplete =
                                it.darkMoreDmgDebuffStacks * companionList.towerList[it.darkMoreDmgDebuffTowerId].darkDmgDebuff
                        } else if (it.darkMoreDmgDebuff >= 180) {
                            darkMoreDmgDebuffDeactivate(it)
                        } else if (it.darkMoreDmgDebuff > 1) {
                            it.darkMoreDmgDebuffComplete =
                                it.darkMoreDmgDebuffStacks * companionList.towerList[it.darkMoreDmgDebuffTowerId].darkDmgDebuff
                            it.darkMoreDmgDebuff++
                        }


                        // antiheal debuffs from items

                        if (it.antihealDebuff == 1) {
                            it.antihealDebuff++
                            it.antihealDebuffActive = companionList.towerList[it.antihealDebuffTowerId].bonusAntiHeal
                        } else if (it.darkMoreDmgDebuff >= 180) {
                            antiHealDebuffDeactivate(it)
                        } else if (it.antihealDebuff > 1) it.antihealDebuff++

                        // ice slow Extra

                        if (companionList.towerList[it.iceDebuffExtraTowerId].slowExtra > 0) {
                            if (it.iceDebuffExtra == 1 && it.iceExtraAlreadyAffected == 0) {
                                when ((0..99).random()) {
                                    in 0..companionList.towerList[it.iceDebuffExtraTowerId].slowExtraChance -> {
                                        if (it.name == "immune") it.iceSlowExtraSpeedReduce =
                                            (it.baseSpeed * (companionList.towerList[it.iceDebuffExtraTowerId].slowExtra / 100)) * it.iceDebuffExtraDR * companionList.towerList[it.iceDebuffExtraTowerId].overallDmgImmune.toFloat()
                                        else it.iceSlowExtraSpeedReduce =
                                            (it.baseSpeed * (companionList.towerList[it.iceDebuffExtraTowerId].slowExtra / 100)) * it.iceDebuffExtraDR.toFloat()
                                        it.baseSpeed -= it.iceSlowExtraSpeedReduce
                                        it.iceExtraAlreadyAffected = 1
                                        if(companionList.towerList[it.iceDebuffExtraTowerId].slowExtraMgcDmg) it.debuffExtraMgcDmg = true
                                        it.iceDebuffExtra += 1
                                        it.iceDebuffExtraDR *= 0.66f
                                        if (it.iceDebuffExtraDR <= 0.1f) it.iceDebuffExtraDR = 0f
                                        if (companionList.towerList[it.iceDebuffExtraTowerId].experienceSlow) towerExperience(it.iceDebuffExtraTowerId, 0.03f)
                                    }
                                    in companionList.towerList[it.iceDebuffExtraTowerId].slowExtraChance..99 -> it.iceDebuffExtra =
                                        0
                                }
                            } else if (it.iceDebuffExtra > 90) {
                                iceSlowExtraDebuffDeactivate(it)
                            } else if (it.iceExtraAlreadyAffected == 1) it.iceDebuffExtra += 1
                            else if (it.iceDebuffExtra > 1) it.iceDebuffExtra += 1
                        }

                        // fire Debuff

                        if (it.fireDebuff == 1 || it.fireDebuff == 21 || it.fireDebuff == 41 || it.fireDebuff == 61 || it.fireDebuff == 81 || it.fireDebuff == 101) {

                            if (it.hp > 0) {
                                var dmg = 0f

                                if (it.name == "boss" || it.name == "challenge") {
                                    dmg =
                                        (((it.maxHp * companionList.towerList[it.fireDebuffTowerId].fireBurnTalentDmg) / 2) * it.winddebuffincreased * it.fireDebuffDR) * magicPen(it, companionList.towerList[it.fireDebuffTowerId])
                                } else {
                                    if (it.name == "immune") dmg =
                                        ((it.maxHp * companionList.towerList[it.fireDebuffTowerId].fireBurnTalentDmg) * it.winddebuffincreased * it.fireDebuffDR * companionList.towerList[it.fireDebuffTowerId].overallDmgImmune) * magicPen(it, companionList.towerList[it.fireDebuffTowerId])
                                    else dmg =
                                        (((it.maxHp * companionList.towerList[it.fireDebuffTowerId].fireBurnTalentDmg)) * it.winddebuffincreased * it.fireDebuffDR) * magicPen(it, companionList.towerList[it.fireDebuffTowerId])
                                }
                                var extraDmgSlow = 1f
                                if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                it.hp -= dmg * extraDmgSlow
                                if (dmg > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when (dmg.toInt()) {
                                        in 0..999 -> dmgString = dmg.toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            (dmg / 1000).toInt().toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            (dmg / 1000000).toInt().toString() + "M"
                                    }
                                    var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, -50, paintBurnDmgDone, -30, -15))
                                }

                                if (it.hp < 0){
                                    it.killerId = it.fireDebuffTowerId
                                    companionList.enemyKilledList.add(it)
                                }
                            }
                            it.fireDebuff += 1
                        } else if (it.fireDebuff >= 102) {
                            fireBurnDebuffDeactivate(it)
                            it.fireDebuffDR *= 0.66f
                            if (it.fireDebuffDR <= 0.1f) it.fireDebuffDR = 0f
                        } else if (it.fireDebuff > 0) it.fireDebuff += 1

                        var sunburnOnlyOne = 0
                        var towerListIterator = companionList.towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()

                            // ice aura

                            if (tower.slowAura > 0) {
                                if (tower.itemListBag.contains(companionList.eice)) {
                                    if (tower.crossesAllList.contains(it) && it.iceAuraAlreadyAffected == 0) {
                                        if (it.name == "immune") it.iceAuraSpeedReduce =
                                            (it.baseSpeed * (tower.slowAura / 100) * tower.overallDmgImmune).toFloat()
                                        else it.iceAuraSpeedReduce =
                                            (it.baseSpeed * (tower.slowAura / 100)).toFloat()
                                        it.baseSpeed -= it.iceAuraSpeedReduce
                                        it.iceAuraAlreadyAffected = 1
                                        if (tower.slowExtraMgcDmg) it.debuffExtraMgcDmg = true
                                    } else if (!tower.crossesAllList.contains(it) && it.iceAuraAlreadyAffected == 1) {
                                        it.iceAuraAlreadyAffected = 0
                                        it.debuffExtraMgcDmg = false
                                        it.baseSpeed += it.iceAuraSpeedReduce
                                        it.iceAuraSpeedReduce = 0f
                                    }
                                }
                            }

                            // item lasso

                            if (tower.itemLasso > 0) {
                                if (it.itemLassoAlreadyAffected > 0 && tower.crossesAllList.contains(it)) it.itemLassoAlreadyAffected++
                                else if (it.itemLassoAlreadyAffected > 0 && !tower.crossesAllList.contains(it)) it.itemLassoAlreadyAffected =
                                    0
                                else if (it.itemLassoAlreadyAffected < 0) it.itemLassoAlreadyAffected--
                                if (it.itemLassoAlreadyAffected >= 50) {
                                    it.itemLassoAlreadyAffected = -1
                                    it.itemLassoSpeedReduce = it.baseSpeed
                                    it.baseSpeed -= it.itemLassoSpeedReduce
                                } else if (it.itemLassoAlreadyAffected <= -80) {
                                    itemLassoDeactivate(it)
                                }
                            }

                            // item frost

                            if (tower.itemFrost > 0) {
                                if (it.itemFrostAlreadyAffected > 0) it.itemFrostAlreadyAffected++
                                if (it.itemFrostAlreadyAffected >= 50) {
                                    itemFrostDeactivate(it)
                                }
                            }

                            // fire talent sun burn

                            if (tower.sunburn > 0 && companionList.day && sunburnOnlyOne < 1) {
                                sunburnOnlyOne++
                                if (companionList.enemyList.contains(it)) {
                                    if (it.hp > 0) {
                                        when ((0..999).random()) {
                                            0 -> it.fireDebuff = 1
                                        }
                                        var dmg = 0f
                                        if (it.fireDebuff > 0) {
                                            if (it.name == "immune") dmg =
                                                (it.maxHp / 100 / 60) * tower.sunburn * (tower.fireBurnTalentDmg / 2) * it.winddebuffincreased * tower.overallDmgImmune
                                            else dmg =
                                                (it.maxHp / 100 / 60) * tower.sunburn * (tower.fireBurnTalentDmg / 2) * it.winddebuffincreased
                                        } else {
                                            if (it.name == "immune") dmg =
                                                (it.maxHp / 100 / 60 / 1.5f) * tower.sunburn * it.winddebuffincreased * tower.overallDmgImmune
                                            else dmg =
                                                (it.maxHp / 100 / 60 / 1.5f) * tower.sunburn * it.winddebuffincreased
                                        }

                                        it.hp -= dmg
                                        if (it.hp < 0) {
                                            it.killerId = companionList.towerList.indexOf(tower)
                                            companionList.enemyKilledList.add(it)
                                        }
                                    }
                                }
                            }

                        }


                        // butterfly slow stun

                        if (it.markOfTheButterflySlowActive == 1 && !it.markOfTheButterflySlowAlreadyAffected && !it.markOfTheButterflyStunAlreadyAffected) {
                            it.markOfTheButterflySlowActive = 0
                            when ((0..2).random()) {
                                in 0..1 -> it.markOfTheButterflySlow = 1
                                2 -> {
                                    if (it.name == "immune") {
                                    } else it.markOfTheButterflyStun = 1
                                }
                            }
                        }

                        if (it.markOfTheButterflySlow == 1) {
                            if (it.name == "speed") it.extraSpeed = 0.0f       // counter speed
                            if (it.name == "immune") it.markOfTheButterflySlowReduce =
                                (it.baseSpeed * (30f / 100f)) * companionList.towerList[it.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
                            else it.markOfTheButterflySlowReduce =
                                (it.baseSpeed * (30f / 100f)).toFloat()
                            it.baseSpeed -= it.markOfTheButterflySlowReduce
                            it.markOfTheButterflySlowAlreadyAffected = true
                            it.markOfTheButterflySlow += 1
                        } else if (it.markOfTheButterflySlow >= 120 * it.markOfTheButterflySlowStunDR) {
                            it.markOfTheButterflySlowStunDR *= 0.66f
                            if (it.markOfTheButterflySlowStunDR <= 0.1f) it.markOfTheButterflySlowStunDR =
                                0f
                            it.markOfTheButterflySlow = 0
                            it.markOfTheButterflySlowAlreadyAffected = false
                            it.baseSpeed += it.markOfTheButterflySlowReduce
                            it.markOfTheButterflySlowReduce = 0f
                        } else if (it.markOfTheButterflySlowAlreadyAffected) it.markOfTheButterflySlow++

                        if (it.markOfTheButterflyStun == 1) {
                            it.markOfTheButterflySlowReduce = it.baseSpeed
                            it.baseSpeed -= it.markOfTheButterflySlowReduce
                            it.markOfTheButterflyStunAlreadyAffected = true
                            it.markOfTheButterflyStun += 1
                        } else if (it.markOfTheButterflyStun >= 20 * it.markOfTheButterflySlowStunDR && (it.name == "boss" || it.name == "challenge")) {
                            it.markOfTheButterflySlowStunDR *= 0.66f
                            if (it.markOfTheButterflySlowStunDR <= 0.1f) it.markOfTheButterflySlowStunDR =
                                0f
                            it.markOfTheButterflyStunAlreadyAffected = false
                            it.markOfTheButterflyStun = 0
                            it.baseSpeed += it.markOfTheButterflySlowReduce
                            it.markOfTheButterflySlowReduce = 0f
                        } else if (it.markOfTheButterflyStun >= 40 * it.markOfTheButterflySlowStunDR) {
                            it.markOfTheButterflySlowStunDR *= 0.66f
                            if (it.markOfTheButterflySlowStunDR <= 0.1f) it.markOfTheButterflySlowStunDR =
                                0f
                            it.markOfTheButterflyStunAlreadyAffected = false
                            it.markOfTheButterflyStun = 0
                            it.baseSpeed += it.markOfTheButterflySlowReduce
                            it.markOfTheButterflySlowReduce = 0f
                        } else if (it.markOfTheButterflyStunAlreadyAffected) it.markOfTheButterflyStun += 1


                        // butterfly debuff dmg + xp

                        if (it.butterflyDebuffEnemyDmg == 1) {
                            it.butterflyDebuffEnemyDmgAlreadyEffected = true
                            it.butterflyDebuffEnemyDmg++
                        } else if (it.butterflyDebuffEnemyDmg >= 300) {
                            butterflyDebuffEnemyDamageDeactivate(it)
                        } else if (it.butterflyDebuffEnemyDmg > 0) {
                            it.butterflyDebuffEnemyDmg++
                        }

                        if (it.butterflyDebuffEnemyGoldXp == 1) {
                            it.butterflyDebuffEnemyGoldXpAlreadyEffected = true
                            it.butterflyDebuffEnemyDmg++
                        } else if (it.butterflyDebuffEnemyGoldXp >= 300) {
                            butterflyDebuffGoldXpDeactivate(it)
                        } else if (it.butterflyDebuffEnemyGoldXp > 0) {
                            it.butterflyDebuffEnemyDmg++
                        }



                        // poison Debuff

                        if (it.poisonDebuff == 1 || it.poisonDebuff == 31 || it.poisonDebuff == 61 || it.poisonDebuff == 91 || it.poisonDebuff == 121
                            || it.poisonDebuff == 151 || it.poisonDebuff == 181 || it.poisonDebuff == 211
                        ) {
                            enemiesPoisonedPlace++
                            if (it.hp > 0) {
                                var dmg = 0f
                                if (it.poisonStack > 100) it.poisonStack = 100f
                                if (it.name == "immune") dmg =
                                    ((it.poisonStack * 1.5f + (it.poisonStack.pow((it.poisonStack / 20)))) * ((companionList.towerList[it.poisonDebuffTowerId].overallTowerSpellDmg) * 0.15f) * companionList.towerList[it.poisonDebuffTowerId].stackablePoison * it.winddebuffincreased * companionList.towerList[it.poisonDebuffTowerId].overallDmgImmune)
                                else dmg =
                                    ((it.poisonStack * 1.5f + (it.poisonStack.pow((it.poisonStack / 20)))) * ((companionList.towerList[it.poisonDebuffTowerId].overallTowerSpellDmg) * 0.15f) * companionList.towerList[it.poisonDebuffTowerId].stackablePoison * it.winddebuffincreased)

                                if (it.manaShield > 0) {
                                    if (it.manaShield > dmg) {
                                        it.manaShield -= dmg
                                        dmg = 0f
                                    } else {
                                        dmg = (dmg - it.manaShield)
                                        it.manaShield = 0f
                                    }
                                }
                                dmg *= magicPen(it, companionList.towerList[it.poisonDebuffTowerId])
                                if (it.shield > 0) dmg = 0f

                                var extraDmgSlow = 1f
                                if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                it.hp -= dmg * extraDmgSlow
                                if (dmg > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when (dmg.toInt()) {
                                        in 0..999 -> dmgString = dmg.toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            (dmg / 1000).toInt().toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            (dmg / 1000000).toInt().toString() + "M"
                                    }
                                    var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, -50, paintPoisonDmgDone, 30, 15))
                                }

                                if (it.hp < 0){
                                    if (companionList.towerList[it.poisonDebuffTowerId].experiencePoisonKill) towerExperience(it.poisonDebuffTowerId, it.xpDrop * 0.25f)
                                    it.killerId = it.poisonDebuffTowerId
                                    companionList.enemyKilledList.add(it)
                                }
                            }
                            it.poisonDebuff += 1
                        } else if (it.poisonDebuff >= 212) {
                            poisonDebuffDeactivate(it)
                        } else if (it.poisonDebuff > 0){
                            enemiesPoisonedPlace++
                            it.poisonDebuff += 1
                        }

                        // poison entangle

                        if (it.entangleOnHit == 1 && it.entangled == false) {
                            when ((0..99).random()) {
                                in 0..companionList.towerList[it.entangleOnHitTowerId].entangle -> {
                                    it.entangleSpeedReduce = it.baseSpeed
                                    it.baseSpeed -= it.entangleSpeedReduce
                                    it.entangled = true
                                }
                            }
                            it.entangleOnHit += 1
                        } else if (it.entangled == true && it.entangleOnHit >= 20 * it.entangleDR && (it.name == "boss" || it.name == "challenge")) {
                            it.entangleDR *= 0.66f
                            if (it.entangleDR <= 0.1f) it.entangleDR = 0f
                            it.entangled = false
                            it.entangleOnHit = 0
                            it.baseSpeed += it.entangleSpeedReduce
                            it.entangleSpeedReduce = 0f
                        } else if (it.entangled == true && it.entangleOnHit >= 40 * it.entangleDR) {
                            poisonEntangleDeactivate(it)
                            it.entangleDR *= 0.66f
                            if (it.entangleDR <= 0.1f) it.entangleDR = 0f
                        } else if (it.entangled == true) it.entangleOnHit += 1


                        // throw boulder
                        if (it.throwBoulderHit >= 40 * it.throwBoulderDR) {
                            it.throwBoulderDR *= 0.66f
                            if (it.throwBoulderDR <= 0.1f) it.throwBoulderDR = 0f
                            throwBoulderDeactivate(it)
                        } else if (it.throwBoulderHitAlreadyEffected) it.throwBoulderHit += 1

                        // dark slow
                        if (it.darkDebuff == true && it.darkSlowAlreadyAffected == false) {
                            it.darkDebuff = false
                            when ((0..99).random()) {
                                in 0..companionList.towerList[it.darkDebuffTowerId].slowExtraChanceDark -> {
                                    if (it.name == "speed") it.extraSpeed =
                                        0.0f       // counter speed
                                    it.darkSlowSpeedReduce = (it.baseSpeed * (companionList.towerList[it.darkDebuffTowerId].slowExtraDark / 100)).toFloat()
                                    it.baseSpeed -= it.darkSlowSpeedReduce
                                    it.darkSlowAlreadyAffected = true
                                }
                            }
                        }

                        // dark fear
                        if (it.fearOnHit >= 1) {
                            if (it.fearOnHit == 1 && it.feared == false) {
                                it.fearSpeedReduce = it.baseSpeed + 2f
                                it.baseSpeed -= (it.fearSpeedReduce)
                                it.feared = true
                                it.fearOnHit += 1
                            } else if (it.feared && it.fearOnHit >= ((10 * companionList.towerList[it.fearTowerId].fearDuration) * it.fearDR) && (it.name == "boss" || it.name == "challenge")) {
                                darkFearDeactivate(it)
                            } else if (it.feared && it.fearOnHit >= (30 * companionList.towerList[it.fearTowerId].fearDuration) * it.fearDR) {
                                it.fearDR *= 0.66f
                                if (it.fearDR <= 0.1f) it.fearDR = 0f
                                darkFearDeactivate(it)
                            } else if (it.feared == true) it.fearOnHit += 1
                        }


                        // talent poison pest
                        if (companionList.poisonTalentPest > 0) {
                            if (it.poisonTalentPestAlreadyAffected > 0) {
                                it.poisonTalentPestAlreadyAffected++
                                it.poisonTalentPestDamage++
                                if (it.poisonTalentPestDamage > 151) {
                                    poisonPestDeactivate(it)
                                }
                                if (it.name == "boss" || it.name == "challenge") {
                                    if (it.poisonTalentPestDamage == 1 || it.poisonTalentPestDamage == 51 || it.poisonTalentPestDamage == 101 || it.poisonTalentPestDamage == 151) {
                                        var dmg = 0f
                                        dmg =
                                            (((it.maxHp / (50..600).random())) * companionList.poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased)
                                        if (it.hp > dmg) it.hp -= dmg
                                        else {
                                            it.hp = 0.1f
                                            when ((0..5).random()) {
                                                0 -> {
                                                }
                                                in 1..5 -> {
                                                    for (itX in companionList.enemyList.shuffled()) {
                                                        if (itX.poisonTalentPestAlreadyAffected > 0 || it.poisonTalentPestImmune) {
                                                        } else {
                                                            itX.poisonTalentPestAlreadyAffected = 1
                                                            break
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (dmg > (it.maxHp / 100)) {
                                            var dmgString = "0"
                                            when (dmg.toInt()) {
                                                in 0..999 -> dmgString = dmg.toInt().toString()
                                                in 1000..999999 -> dmgString =
                                                    (dmg / 1000).toInt().toString() + "k"
                                                in 1000000..999999999 -> dmgString =
                                                    (dmg / 1000000).toInt().toString() + "M"
                                            }
                                            var dmgDisplayListIterator =
                                                companionList.dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, -50, paintPestDmgDone, 0, 0))
                                        }
                                    }
                                } else {
                                    if (it.poisonTalentPestDamage == 1 || it.poisonTalentPestDamage == 51 || it.poisonTalentPestDamage == 101 || it.poisonTalentPestDamage == 151) {
                                        var dmg = 0f
                                        if (it.name == "immune") dmg =
                                            (((it.maxHp / (20..600).random())) * companionList.poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased * companionList.towerList[it.poisonDebuffTowerId].overallDmgImmune) * 10
                                        else dmg =
                                            (((it.maxHp / (20..600).random())) * companionList.poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased)

                                        if (it.manaShield > 0) {
                                            if (it.manaShield > dmg) {
                                                it.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - it.manaShield)
                                                it.manaShield = 0f
                                            }
                                        }
                                        if (it.shield > 0) dmg = 0f
                                        if (it.hp > dmg) it.hp -= dmg
                                        else {
                                            it.hp = 0.1f
                                            when ((0..5).random()) {
                                                0 -> {
                                                }
                                                in 1..5 -> {
                                                    for (itX in companionList.enemyList.shuffled()) {
                                                        if (itX.poisonTalentPestAlreadyAffected > 0 || it.poisonTalentPestImmune) {
                                                        } else {
                                                            itX.poisonTalentPestAlreadyAffected = 1
                                                            break
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (dmg > (it.maxHp / 100)) {
                                            var dmgString = "0"
                                            when (dmg.toInt()) {
                                                in 0..999 -> dmgString = dmg.toInt().toString()
                                                in 1000..999999 -> dmgString =
                                                    (dmg / 1000).toInt().toString() + "k"
                                                in 1000000..999999999 -> dmgString =
                                                    (dmg / 1000000).toInt().toString() + "M"
                                            }
                                            var dmgDisplayListIterator =
                                                companionList.dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, -50, paintPestDmgDone, 0, 0))
                                        }
                                    }
                                }
                            }
                            if (it.poisonTalentPestAlreadyAffected == 100 || it.poisonTalentPestAlreadyAffected == 150) {
                                when ((0..1).random()) {
                                    0 -> it.poisonTalentPestAlreadyAffected = 1
                                    1 -> {
                                        for (itX in companionList.enemyList.shuffled()) {
                                            if (itX.poisonTalentPestAlreadyAffected > 0 || it.poisonTalentPestImmune) {
                                            } else {
                                                it.poisonTalentPestAlreadyAffected = 1
                                                itX.poisonTalentPestAlreadyAffected = 1
                                                break
                                            }
                                        }
                                    }
                                }
                            }
                        }


                        // dark talent laser
                        if (companionList.towerList[it.darkTalentLaserTowerId].darkTalentLaser > 0) {
                            if (it.darkTalentLaserAlreadyAffected >= 250) {
                                darkLaserDeactivate(it)
                            } else if (it.darkTalentLaserAlreadyAffected > 0 && companionList.towerList[it.darkTalentLaserTowerId].crossesAllList.contains(it)) {
                                it.darkTalentLaserAlreadyAffected++
                                if (it.hp > 0) {
                                    it.maxHp -= (it.maxHp * (companionList.towerList[it.darkTalentLaserTowerId].darkTalentLaser / 100))
                                    it.hp -= (it.maxHp * (companionList.towerList[it.darkTalentLaserTowerId].darkTalentLaser / 100))
                                    if (it.hp < 0) {
                                        it.killerId = it.darkTalentLaserTowerId
                                        companionList.enemyKilledList.add(it)
                                    }
                                }
                            } else {
                                it.darkTalentLaserAlreadyAffected = 0
                                it.darkTalentLaserTowerId = 0
                            }
                        }

                        // talent moonlight
                        if (it.talentMoonlightDraw > 0) {
                            it.talentMoonlightDraw++
                            if (it.talentMoonlightDraw > 15) it.talentMoonlightDraw = 0
                        }

                        // wizard talent bomb
                        if (it.wizardBombActive > 0) it.wizardBombActive++
                        if (it.wizardBombActive >= 170) {
                            it.wizardBombActive = 0
                            if (it.hp > 0) {
                                var dmg = 0f
                                dmg =
                                    ((companionList.towerList[it.wizardBombTowerId].wizardBombDmg * companionList.bigNumberScaler) + (companionList.towerList[it.wizardBombTowerId].overallTowerSpellDmg * (0.1f + companionList.wizardBombStartItemDmg)))

                                if (it.manaShield > 0) {
                                    if (it.manaShield > dmg) {
                                        it.manaShield -= dmg
                                        dmg = 0f
                                    } else {
                                        dmg = (dmg - it.manaShield)
                                        it.manaShield = 0f
                                    }
                                }
                                dmg *= magicPen(it, companionList.towerList[it.wizardBombTowerId])
                                if (it.shield > 0) dmg = 0f
                                var extraDmgSlow = 1f
                                if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                it.hp -= dmg * extraDmgSlow
                                if (dmg > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when (dmg.toInt()) {
                                        in 0..999 -> dmgString = dmg.toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            (dmg / 1000).toInt().toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            (dmg / 1000000).toInt().toString() + "M"
                                    }
                                    var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                }

                                if (it.hp < 0){
                                    it.killerId = it.wizardBombTowerId
                                    companionList.enemyKilledList.add(it)
                                }
                            }
                            var enemyListIteratorZ = companionList.enemyList.listIterator()
                            while (enemyListIteratorZ.hasNext()) {
                                var otherEnemy = enemyListIteratorZ.next()
                                if (splash100(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                    if (otherEnemy.hp > 0) {
                                        var dmg = 0f
                                        dmg =
                                            ((companionList.towerList[it.wizardBombTowerId].wizardBombDmg * 0.66f * companionList.bigNumberScaler) + (companionList.towerList[it.wizardBombTowerId].overallTowerSpellDmg * (0.1f + companionList.wizardBombStartItemDmg)))

                                        if (otherEnemy.manaShield > 0) {
                                            if (otherEnemy.manaShield > dmg) {
                                                otherEnemy.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - otherEnemy.manaShield)
                                                otherEnemy.manaShield = 0f
                                            }
                                        }
                                        dmg *= magicPen(it, companionList.towerList[it.wizardBombTowerId])
                                        if (otherEnemy.shield > 0) dmg = 0f
                                        var extraDmgSlow = 1f
                                        if (otherEnemy.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                        otherEnemy.hp -= dmg * extraDmgSlow
                                        if (dmg > (otherEnemy.maxHp / 100)) {
                                            var dmgString = "0"
                                            when (dmg.toInt()) {
                                                in 0..999 -> dmgString = dmg.toInt().toString()
                                                in 1000..999999 -> dmgString =
                                                    (dmg / 1000).toInt().toString() + "k"
                                                in 1000000..999999999 -> dmgString =
                                                    (dmg / 1000000).toInt().toString() + "M"
                                            }
                                            var dmgDisplayListIterator =
                                                companionList.dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                        }
                                        if (otherEnemy.hp < 0) {
                                            otherEnemy.killerId = it.wizardBombTowerId
                                            companionList.enemyKilledList.add(otherEnemy)
                                        }
                                    }
                                }
                            }
                        }


                        // wizard mine
                        if (companionList.wizardMine) {
                            if (it.mineAlreadyAffected) it.mineAlreadyAffectedCounter++
                            if (it.mineAlreadyAffectedCounter >= 180) {
                                wizardMineDeactivate(it)
                            }
                        }

                        // wizard lightning
                        if (it.wizardMissedLightningActiveHit >= 1) it.wizardMissedLightningActiveHit++
                        if (it.wizardMissedLightningActiveHit >= 20) it.wizardMissedLightningActiveHit = 0

                    }
                    // ---------------------------------------------------------------------------------------------------------

                    // talent poison pest
                    if (companionList.poisonTowerCount > 0) {
                        var counter = 250
                        if (companionList.poisonTowerCount == 2) counter = 225
                        else if (companionList.poisonTowerCount == 3) counter = 210
                        else if (companionList.poisonTowerCount == 4) counter = 200
                        else if (companionList.poisonTowerCount >= 5) counter = 195
                        if (companionList.poisonTalentPest > 0) companionList.poisonPestCount++
                        if (companionList.poisonTalentPest > 0 && companionList.poisonPestCount >= counter) {
                            for (it in companionList.enemyList.shuffled()) {
                                if (it.poisonTalentPestAlreadyAffected > 0 || it.poisonTalentPestImmune) {
                                } else {
                                    when ((0..3).random()) {
                                        0 -> it.poisonTalentPestAlreadyAffected = 1
                                    }
                                    companionList.poisonPestCount = 0
                                    break
                                }
                            }
                        }
                    }

                    var towerListIterator = companionList.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()

                        if (tower.spellCastCDCounter > 0) tower.spellCastCDBool = true

                        // wizard chain lightning
                        if (tower.chainLighning) {
                            tower.chainLightningCounter++
                            if (tower.spellCastCDBool) tower.chainLightningCounter += (tower.chainLightningTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            var counter = 0
                            if (companionList.wizardTowerCount == 2) counter = 10
                            else if (companionList.wizardTowerCount == 3) counter = 30
                            else if (companionList.wizardTowerCount == 4) counter = 50
                            else if (companionList.wizardTowerCount >= 5) counter = 80
                            var chainLightningTimerBool = tower.chainLightningTimer - counter
                            if (tower.chainLightningCounter >= chainLightningTimerBool) {
                                tower.chainLightningCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    companionList.addChainLighning++
                                }
                            }
                        }

                        // earth talent throw boulder

                        if (tower.throwBoulder > 0) {
                            tower.throwBoulderCounter++
                            if (tower.spellCastCDBool) tower.throwBoulderCounter += (tower.throwBoulderTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.throwBoulderCounter >= tower.throwBoulderTimer) {
                                tower.throwBoulderCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList.shuffled()) {
                                        if (it.hp > 0) {
                                            var dmg = 0f
                                            dmg = (tower.overallTowerPhysicalDmg) * tower.throwBoulder

                                            if (it.shield > 0) {
                                                if (it.shield > dmg) {
                                                    it.shield -= dmg
                                                    dmg = 0f
                                                } else {
                                                    dmg = (dmg - it.shield)
                                                    it.shield = 0f
                                                }
                                            }
                                            dmg *= armorPen(it, tower)
                                            if (it.manaShield > 0) dmg = 0f
                                            it.hp -= dmg
                                            if (dmg > (it.maxHp / 100)) {
                                                var dmgString = "0"
                                                when (dmg.toInt()) {
                                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                                    in 1000..999999 -> dmgString =
                                                        (dmg / 1000).toInt().toString() + "k"
                                                    in 1000000..999999999 -> dmgString =
                                                        (dmg / 1000000).toInt().toString() + "M"
                                                }
                                                var dmgDisplayListIterator =
                                                    companionList.dmgDisplayList.listIterator()
                                                dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintEarthDmgDone, -30, -15))
                                            }
                                            if (it.hp < 0){
                                                it.killerId = companionList.towerList.indexOf(tower)
                                                companionList.enemyKilledList.add(it)
                                            }
                                        }
                                        if (it.name == "speed") it.extraSpeed =
                                            0.0f       // counter speed
                                        it.throwBoulderSpeedReduce = it.baseSpeed
                                        it.baseSpeed -= it.throwBoulderSpeedReduce
                                        it.throwBoulderHitAlreadyEffected = true
                                        var enemyListIteratorZ = companionList.enemyList.listIterator()
                                        while (enemyListIteratorZ.hasNext()) {
                                            var otherEnemy = enemyListIteratorZ.next()
                                            if (splash60(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                                if (otherEnemy.throwBoulderHitAlreadyEffected) {
                                                } else {
                                                    if (otherEnemy.hp > 0) {
                                                        var dmg = 0f
                                                        dmg = (tower.overallTowerPhysicalDmg) * tower.throwBoulder * 0.75f

                                                        if (otherEnemy.shield > 0) {
                                                            if (otherEnemy.shield > dmg) {
                                                                otherEnemy.shield -= dmg
                                                                dmg = 0f
                                                            } else {
                                                                dmg = (dmg - otherEnemy.shield)
                                                                otherEnemy.shield = 0f
                                                            }
                                                        }
                                                        dmg *= armorPen(it, tower)
                                                        if (otherEnemy.manaShield > 0) dmg = 0f
                                                        otherEnemy.hp -= dmg
                                                        if (dmg > (otherEnemy.maxHp / 100)) {
                                                            var dmgString = "0"
                                                            when (dmg.toInt()) {
                                                                in 0..999 -> dmgString =
                                                                    dmg.toInt().toString()
                                                                in 1000..999999 -> dmgString =
                                                                    (dmg / 1000).toInt()
                                                                        .toString() + "k"
                                                                in 1000000..999999999 -> dmgString =
                                                                    (dmg / 1000000).toInt()
                                                                        .toString() + "M"
                                                            }
                                                            var dmgDisplayListIterator =
                                                                companionList.dmgDisplayList.listIterator()
                                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintEarthDmgDone, -30, -15))
                                                        }

                                                        if (otherEnemy.hp < 0){
                                                            otherEnemy.killerId = companionList.towerList.indexOf(tower)
                                                            companionList.enemyKilledList.add(otherEnemy)
                                                        }

                                                    }
                                                    if (otherEnemy.name == "speed") otherEnemy.extraSpeed =
                                                        0.0f       // counter speed
                                                    otherEnemy.throwBoulderSpeedReduce =
                                                        otherEnemy.baseSpeed
                                                    otherEnemy.baseSpeed -= otherEnemy.throwBoulderSpeedReduce
                                                    otherEnemy.throwBoulderHitAlreadyEffected = true
                                                }
                                            }
                                        }
                                        break
                                    }
                                }
                            }
                        }

                        // item frost
                        if (tower.itemFrost > 0) tower.itemFrostCount++
                        if (tower.itemFrost > 0 && tower.itemFrostCount >= 25) {
                            for (it in tower.crossesAllList.shuffled()) {
                                if (it.itemFrostAlreadyAffected > 0) {
                                } else {
                                    if (it.name == "speed") it.extraSpeed =
                                        0.0f       // counter speed
                                    it.itemFrostSpeedReduce =
                                        (it.baseSpeed * (tower.itemFrost / 100) * it.itemFrostDR).toFloat()
                                    it.baseSpeed -= it.itemFrostSpeedReduce
                                    it.itemFrostAlreadyAffected = 1
                                    tower.itemFrostCount = 0
                                    it.itemFrostDR *= 0.66f
                                    if (it.itemFrostDR <= 0.1f) it.itemFrostDR = 0f
                                    break
                                }
                            }
                        }

                        // ice nova
                        if ((tower.itemListBag.contains(companionList.eice))) {
                            tower.iceNovaCounter++
                            if (tower.itemListBag.contains(companionList.stid6)) companionList.iceNovaTimerStartItem =
                                tower.iceNovaTimer / 2
                            else companionList.iceNovaTimerStartItem = tower.iceNovaTimer
                            if (tower.spellCastCDBool) tower.iceNovaCounter += (companionList.iceNovaTimerStartItem * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.iceNovaCounter >= companionList.iceNovaTimerStartItem) {
                                tower.iceNovaCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList.shuffled()) {
                                        if (tower.iceNovaSpellDmg > 0) {
                                            if (it.hp > 0) {
                                                var dmg = 0f
                                                dmg = ((tower.overallTowerSpellDmg * tower.iceNovaSpellDmg))
                                                if (it.manaShield > 0) {
                                                    if (it.manaShield > dmg) {
                                                        it.manaShield -= dmg
                                                        dmg = 0f
                                                    } else {
                                                        dmg = (dmg - it.manaShield)
                                                        it.manaShield = 0f
                                                    }
                                                }
                                                dmg *= magicPen(it, tower)
                                                if (it.shield > 0) dmg = 0f
                                                var extraDmgSlow = 1f
                                                if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                                it.hp -= dmg * extraDmgSlow
                                                if (dmg > (it.maxHp / 100)) {
                                                    var dmgString = "0"
                                                    when (dmg.toInt()) {
                                                        in 0..999 -> dmgString =
                                                            dmg.toInt().toString()
                                                        in 1000..999999 -> dmgString =
                                                            (dmg / 1000).toInt().toString() + "k"
                                                        in 1000000..999999999 -> dmgString =
                                                            (dmg / 1000000).toInt().toString() + "M"
                                                    }
                                                    var dmgDisplayListIterator =
                                                        companionList.dmgDisplayList.listIterator()
                                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                                                }

                                                if (it.hp < 0){
                                                    it.killerId = companionList.towerList.indexOf(tower)
                                                    companionList.enemyKilledList.add(it)
                                                }
                                            }
                                        }
                                        if (it.iceNovaAlreadyAffected) {
                                            it.baseSpeed += it.iceNovaSpeedReduce
                                            it.iceNovaSpeedReduce = 0f
                                        }
                                        if (it.name == "speed") it.extraSpeed = 0.0f       // counter speed
                                        if (it.name == "immune") it.iceNovaSpeedReduce = (it.baseSpeed * 0.7f * it.iceNovaDR) * companionList.towerList[it.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
                                        else it.iceNovaSpeedReduce = (it.baseSpeed * 0.7f * it.iceNovaDR).toFloat()
                                        it.baseSpeed -= it.iceNovaSpeedReduce
                                        it.iceNovaAlreadyAffected = true
                                        if(tower.slowExtraMgcDmg) it.debuffExtraMgcDmg = true
                                        it.iceNovaDR *= 0.66f
                                        if (it.iceNovaDR <= 0.1f) it.iceNovaDR = 0f
                                        if (tower.experienceSlow) towerExperience(companionList.towerList.indexOf(tower), 0.03f)
                                                    var enemyListIteratorZ = companionList.enemyList.listIterator()
                                                     while (enemyListIteratorZ.hasNext()) {
                                                         var otherEnemy = enemyListIteratorZ.next()
                                            if (splash60(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                                if (otherEnemy.iceNovaAlreadyAffected) {
                                                    otherEnemy.baseSpeed += otherEnemy.iceNovaSpeedReduce
                                                    otherEnemy.iceNovaSpeedReduce = 0f
                                                }
                                                if (tower.iceNovaSpellDmg > 0) {
                                                    if (otherEnemy.hp > 0) {
                                                        var dmg = 0f
                                                        dmg = ((tower.overallTowerSpellDmg * tower.iceNovaSpellDmg) * 0.75f)

                                                        if (otherEnemy.manaShield > 0) {
                                                            if (otherEnemy.manaShield > dmg) {
                                                                otherEnemy.manaShield -= dmg
                                                                dmg = 0f
                                                            } else {
                                                                dmg = (dmg - otherEnemy.manaShield)
                                                                otherEnemy.manaShield = 0f
                                                            }
                                                        }
                                                        dmg *= magicPen(otherEnemy, tower)
                                                        if (otherEnemy.shield > 0) dmg = 0f
                                                        var extraDmgSlow = 1f
                                                        if (otherEnemy.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                                        otherEnemy.hp -= dmg * extraDmgSlow
                                                        if (dmg > (otherEnemy.maxHp / 100)) {
                                                            var dmgString = "0"
                                                            when (dmg.toInt()) {
                                                                in 0..999 -> dmgString =
                                                                    dmg.toInt().toString()
                                                                in 1000..999999 -> dmgString =
                                                                    (dmg / 1000).toInt()
                                                                        .toString() + "k"
                                                                in 1000000..999999999 -> dmgString =
                                                                    (dmg / 1000000).toInt()
                                                                        .toString() + "M"
                                                            }
                                                            var dmgDisplayListIterator =
                                                                companionList.dmgDisplayList.listIterator()
                                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                                                        }

                                                        if (otherEnemy.hp < 0){
                                                            otherEnemy.killerId = companionList.towerList.indexOf(tower)
                                                            companionList.enemyKilledList.add(otherEnemy)
                                                        }

                                                    }
                                                }
                                                if (otherEnemy.name == "speed") otherEnemy.extraSpeed = 0.0f       // counter speed
                                                if (it.name == "immune") otherEnemy.iceNovaSpeedReduce = (otherEnemy.baseSpeed * 0.8f * otherEnemy.iceNovaDR) * companionList.towerList[otherEnemy.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
                                                else otherEnemy.iceNovaSpeedReduce = (otherEnemy.baseSpeed * 0.8f * otherEnemy.iceNovaDR).toFloat()
                                                otherEnemy.baseSpeed -= otherEnemy.iceNovaSpeedReduce
                                                otherEnemy.iceNovaAlreadyAffected = true
                                                if(tower.slowExtraMgcDmg) otherEnemy.debuffExtraMgcDmg = true
                                                otherEnemy.iceNovaDR *= 0.66f
                                                if (otherEnemy.iceNovaDR <= 0.1f) otherEnemy.iceNovaDR = 0f
                                                if (tower.experienceSlow) towerExperience(companionList.towerList.indexOf(tower), 0.03f)
                                            }
                                        }
                                        break
                                    }
                                }
                            }
                        }

                        // wizard talent bomb
                        if (tower.itemListBag.contains(companionList.ewizard)) {
                            tower.wizardBombCounter++
                            if (tower.spellCastCDBool) tower.wizardBombCounter += (tower.wizardBombTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.wizardBombCounter >= tower.wizardBombTimer) {
                                tower.wizardBombCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList.shuffled()) {
                                        it.wizardBombActive = 1
                                        it.wizardBombTowerId = companionList.towerList.indexOf(tower)
                                        break
                                    }
                                }
                            }
                        }

                        // item lasso
                        if (tower.itemLasso > 0) tower.itemLassoCount++
                        if (tower.itemLasso > 0 && tower.itemLassoCount >= (300 / tower.itemLasso).toInt()) {

                            for (it in tower.crossesAllList.shuffled()) {
                                if (it.itemLassoAlreadyAffected != 0) {
                                } else {
                                    it.itemLassoAlreadyAffected = 1
                                    it.itemLassoAlreadyAffectedTowerId = companionList.towerList.indexOf(tower)
                                    tower.itemLassoCount = 0
                                    break
                                }
                            }
                        }

                        // dark talent fear
                        if (tower.darkTalentFear) {
                            tower.fearCounter++
                            if (tower.spellCastCDBool) tower.fearCounter += (tower.fearTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.fearCounter >= tower.fearTimer) {
                                tower.fearCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList) {
                                        when ((0..9).random()) {
                                            0 -> {
                                                it.fearOnHit = 1
                                                it.fearTowerId = companionList.towerList.indexOf(tower)
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // wizard lightning
                        if (tower.wizardMissedLightningActive) {
                            tower.spellCastCDCounterHeap += 1
                            tower.wizardMissedLightningActive = false
                            repeat((2 + companionList.wizardLightningStartItemTargets)) {
                                if (companionList.enemyList.isNotEmpty()) {
                                    for (it in companionList.enemyList.shuffled()) {
                                        if (it.hp > 0) {
                                            var dmg = 0f
                                            dmg =
                                                tower.overallTowerSpellDmg * tower.wizardMissedLightningDmgBoost

                                            if (it.manaShield > 0) {
                                                if (it.manaShield > dmg) {
                                                    it.manaShield -= dmg
                                                    dmg = 0f
                                                } else {
                                                    dmg = (dmg - it.manaShield)
                                                    it.manaShield = 0f
                                                }
                                            }
                                            dmg *= magicPen(it, tower)
                                            if (it.shield > 0) dmg = 0f
                                            var extraDmgSlow = 1f
                                            if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                            it.hp -= dmg * extraDmgSlow
                                            if (dmg > (it.maxHp / 100)) {
                                                var dmgString = "0"
                                                when (dmg.toInt()) {
                                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                                    in 1000..999999 -> dmgString =
                                                        (dmg / 1000).toInt().toString() + "k"
                                                    in 1000000..999999999 -> dmgString =
                                                        (dmg / 1000000).toInt().toString() + "M"
                                                }
                                                var dmgDisplayListIterator =
                                                    companionList.dmgDisplayList.listIterator()
                                                dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                            }

                                            if (it.hp < 0) {
                                                it.killerId = companionList.towerList.indexOf(tower)
                                                companionList.enemyKilledList.add(it)
                                            }

                                        }

                                        it.wizardMissedLightningActiveHit = 1
                                        break
                                    }
                                }
                            }
                        }

                        // dark talent laser
                        if (tower.darkTalentLaser > 0) {
                            tower.darkTalentLaserCount++
                            for (it in tower.crossesAllList) {
                                if (it.darkTalentLaserAlreadyAffected > 0) {
                                    tower.darkTalentLaserAlreadyAffectedCount++
                                    break
                                }
                            }
                            if (tower.darkTalentLaserAlreadyAffectedCount == 0) {
                                tower.darkTalentLaserCount = 301
                            }
                            tower.darkTalentLaserAlreadyAffectedCount = 0
                        }
                        if (tower.darkTalentLaser > 0 && tower.darkTalentLaserCount >= 300) {
                            for (it in tower.crossesAllList.shuffled()) {
                                it.darkTalentLaserAlreadyAffected = 1
                                it.darkTalentLaserTowerId = companionList.towerList.indexOf(tower)
                                tower.darkTalentLaserCount = 0
                                break
                            }
                        }

                        // moon talent moonlight
                        if (tower.itemListBag.contains(companionList.emoon)){
                        if (!companionList.day) tower.moonLightCount++
                        if (tower.spellCastCDBool) tower.moonLightCount += (tower.moonLight * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                        if (tower.moonLightCount >= (90 / tower.moonLight)) {
                            tower.moonLightCount = 0
                            if (tower.crossesAllList.isNotEmpty()) {
                                tower.spellCastCDCounterHeap += 1
                                for (it in tower.crossesAllList.shuffled()) {
                                    if (it.hp > 0) {
                                        if (tower.experienceMoonlight && it.talentMoonlightAlreadyAffected > 0.67f) towerExperience(companionList.towerList.indexOf(tower), 0.1f)
                                        var dmgPlace = (0..(tower.overallTowerSpellDmg * 2).toInt()).random()
                                        var dmg = dmgPlace * 0.1f * (1 + it.talentMoonlightAlreadyAffected)

                                        if (it.manaShield > 0) {
                                            if (it.manaShield > dmg) {
                                                it.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - it.manaShield)
                                                it.manaShield = 0f
                                            }
                                        }
                                        dmg *= magicPen(it, tower)
                                        if (it.shield > 0) dmg = 0f
                                        it.hp -= dmg
                                        if (dmg > (it.maxHp / 100)) {
                                            var dmgString = "0"
                                            when (dmg.toInt()) {
                                                in 0..999 -> dmgString = dmg.toInt().toString()
                                                in 1000..999999 -> dmgString =
                                                    (dmg / 1000).toInt().toString() + "k"
                                                in 1000000..999999999 -> dmgString =
                                                    (dmg / 1000000).toInt().toString() + "M"
                                            }
                                            var dmgDisplayListIterator =
                                                companionList.dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintMoonDmgDone, 30, 15))
                                        }

                                        if (it.hp < 0) {
                                            it.killerId = companionList.towerList.indexOf(tower)
                                            companionList.enemyKilledList.add(it)
                                        }
                                        it.talentMoonlightAlreadyAffected += 0.33f
                                        it.talentMoonlightDraw = 1
                                        break
                                    }
                                }
                            }
                            }
                        }

                        tower.spellCastCDBool = false
                        tower.spellCastCDCounter = tower.spellCastCDCounterHeap
                        tower.spellCastCDCounterHeap = 0
                    }

                } finally {
                    companionList.writeLockEnemy.unlock()
                    companionList.writeLockTower.unlock()
                    companionList.writeLockDisplay.unlock()
                }
                companionList.enemiesPoisoned = enemiesPoisonedPlace
            }
        }

        //----------------------------------------------------------------------------------

    fun deactivateAllDebuffs(it: Enemy){
        darkSlowDeactivate(it)
     iceNovaDeactivate (it)
     iceDebuffDeactivate (it)
     darkMoreDmgDebuffDeactivate(it)
     antiHealDebuffDeactivate(it)
     iceSlowExtraDebuffDeactivate(it)
     fireBurnDebuffDeactivate (it)
     itemLassoDeactivate(it)
     itemFrostDeactivate(it)
     butterflyDebuffEnemyDamageDeactivate(it)
     butterflyDebuffGoldXpDeactivate(it)
     poisonDebuffDeactivate(it)
     poisonEntangleDeactivate(it)
     throwBoulderDeactivate(it)
     darkFearDeactivate(it)
     poisonPestDeactivate(it)
     darkLaserDeactivate (it)
     wizardBombDeactivate(it)
     wizardMineDeactivate (it)
    }

    fun darkSlowDeactivate(it: Enemy){
        it.darkDebuff = false
        it.baseSpeed += it.darkSlowSpeedReduce
        it.darkSlowSpeedReduce = 0f
        it.darkSlowAlreadyAffected = false
    }
    fun iceNovaDeactivate (it: Enemy){
        it.baseSpeed += it.iceNovaSpeedReduce
        it.iceNovaSpeedReduce = 0f
        it.iceNovaAlreadyAffectedCounter = 0
        it.iceNovaAlreadyAffected = false
        it.debuffExtraMgcDmg = false
    }
    fun iceDebuffDeactivate (it: Enemy){
        it.iceDebuff = 0
        it.iceAlreadyAffected = 0
        it.baseSpeed += it.iceSlowEachSpeedReduce
        it.iceSlowEachSpeedReduce = 0f
        it.debuffExtraMgcDmg = false
    }
    fun darkMoreDmgDebuffDeactivate(it: Enemy){
        it.darkMoreDmgDebuff = 0
        it.darkMoreDmgDebuffStacks = 0
    }
    fun antiHealDebuffDeactivate(it: Enemy){
        it.antihealDebuff = 0
        it.antihealDebuffActive = 0f
    }
    fun iceSlowExtraDebuffDeactivate(it: Enemy){
        it.iceDebuffExtra = 0
        it.iceExtraAlreadyAffected = 0
        it.baseSpeed += it.iceSlowExtraSpeedReduce
        it.iceSlowExtraSpeedReduce = 0f
        it.debuffExtraMgcDmg = false
    }
    fun fireBurnDebuffDeactivate (it: Enemy){
        it.fireDebuff = 0
    }
    fun itemLassoDeactivate(it: Enemy){
        it.itemLassoAlreadyAffected = 0
        it.itemLassoAlreadyAffectedTowerId = 0
        it.baseSpeed += it.itemLassoSpeedReduce
        it.itemLassoSpeedReduce = 0f
    }
    fun itemFrostDeactivate(it: Enemy){
        it.itemFrostAlreadyAffected = 0
        it.baseSpeed += it.itemFrostSpeedReduce
        it.itemFrostSpeedReduce = 0f
    }
    fun butterflyDebuffEnemyDamageDeactivate(it: Enemy){
        it.butterflyDebuffEnemyDmg = 0
        it.butterflyDebuffEnemyDmgAlreadyEffected = false
    }
    fun butterflyDebuffGoldXpDeactivate(it: Enemy){
        it.butterflyDebuffEnemyDmg = 0
        it.butterflyDebuffEnemyGoldXpAlreadyEffected = false
    }
    fun poisonDebuffDeactivate(it: Enemy){
        it.poisonDebuff = 0
        it.poisonDebuffTowerId = 0
        it.poisonStack = 0.0f
    }
    fun poisonEntangleDeactivate(it: Enemy){
        it.entangled = false
        it.entangleOnHit = 0
        it.baseSpeed += it.entangleSpeedReduce
        it.entangleSpeedReduce = 0f
    }
    fun throwBoulderDeactivate(it: Enemy){
        it.throwBoulderHitAlreadyEffected = false
        it.throwBoulderHit = 0
        it.baseSpeed += it.throwBoulderSpeedReduce
        it.throwBoulderSpeedReduce = 0f
    }
    fun darkFearDeactivate(it: Enemy){
        it.feared = false
        it.fearOnHit = 0
        if (it.fearSpeedReduce > 0) it.baseSpeed += it.fearSpeedReduce
        it.fearSpeedReduce = 0f
    }
    fun poisonPestDeactivate(it: Enemy){
        it.poisonTalentPestDamage = 0
        it.poisonTalentPestAlreadyAffected = 0
        it.poisonTalentPestImmune = true
    }
    fun darkLaserDeactivate (it: Enemy){
        it.darkTalentLaserAlreadyAffected = 0
        it.darkTalentLaserTowerId = 0
    }
    fun wizardBombDeactivate(it: Enemy){
        it.wizardBombActive = 0
    }
    fun wizardMineDeactivate (it: Enemy){
        it.baseSpeed += it.mineSpeedReduce
        it.mineSpeedReduce = 0f
        it.mineAlreadyAffected = false
    }


    //----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------

        private fun inBounceRange(bullet: TowerRadius): Int {

            companionList.readLockEnemy.lock()
            try {

                var check = 0
                var enemyListIterator = companionList.enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var itX = enemyListIterator.next()


                        val distanceX2 = (bullet.x) - itX.circle!!.x
                        val distanceY2 = (bullet.y) - itX.circle!!.y

                        val squaredDistance2 =
                            (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                        val sumOfRadius2 = (bullet.r + 150) + (itX.circle!!.r - 20)

                        if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                            check += 1

                    }
                }
                return check

            } finally {
                companionList.readLockEnemy.unlock()
            }
        }



    //----------------------------------------------------------------------------------

        private fun towerAttack() {

            companionList.writeLockShot.lock()
            companionList.readLockEnemy.lock()
            companionList.readLockTower.lock()
            try {
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var it = towerListIterator.next()

                    it.shootCounter++
                    it.timertower++

                    // multishot
                    if (it.towerPrimaryElement == "wind") {
                        if (it.timertower >= it.towerAttackSpeed && it.crossesAllList.isNotEmpty()) {
                            it.timertower = 0
                                var crossesAllListIterator = it.crossesAllList.listIterator()
                                while (crossesAllListIterator.hasNext()) {
                                    var enemy = crossesAllListIterator.next()
                                    var shootListIterator = companionList.shootList.listIterator()
                                    var shootListPlace = Shoot()
                                    var times = it.crossesAllList.indexOf(enemy)
                                    var towerindex = companionList.towerList.indexOf(it)
                                    shootListPlace.towerId = towerindex
                                    shootListPlace.id = times
                                    shootListPlace.multiShotBullet = true
                                    shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                                    shootListIterator.add(shootListPlace)  // add new shoot
                                }
                                GlobalScope.launch {
                                    soundPool.play(soundIds[0], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                                }
                        }
                    }
                    // normal attack
                    else {
                        if (it.timertower >= it.towerAttackSpeed && it.crossesAllList.isNotEmpty()) {
                            if (it.firstLastRandom == 0 && !it.crossesAllList.contains(it.randomEnemyForShot)) it.randomEnemyForShotSticky =
                                true
                            var shootListIterator = companionList.shootList.listIterator()
                            var shootListPlace = Shoot()
                            var towerindex = companionList.towerList.indexOf(it)
                            shootListPlace.towerId = towerindex
                            shootListPlace.bounceLeft = it.shotBounceTargets * it.shotBounceTargetsStartItems
                            if (it.towerPrimaryElement == "earth")   shootListPlace.bulletSpeed = 6f
                            else if (it.towerPrimaryElement == "butterfly")   shootListPlace.bulletSpeed = 8f
                            shootListPlace.bullet =
                                TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                            shootListIterator.add(shootListPlace)   // add new shoot
                            GlobalScope.launch {
                                soundPool.play(soundIds[0], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                            }
                            it.timertower = 0
                        }
                    }

                    if (it.chainLighning) {
                        if (companionList.addChainLighning > 0) {
                            companionList.addChainLighning -= 1
                            var shootListPlace = Shoot()
                            shootListPlace.chainLightning = true
                            shootListPlace.color = Color.WHITE
                            shootListPlace.bulletSpeed = 12f
                            var towerindex = companionList.towerList.indexOf(it)
                            shootListPlace.towerId = towerindex
                            shootListPlace.bullet =
                                TowerRadius(it.towerRange.x, it.towerRange.y, 8f)
                            shootListPlace.bounceLeft = it.chainLightningBounceTargets
                            var shootListIterator = companionList.shootList.listIterator()
                            shootListIterator.add(shootListPlace)
                        }
                    }


                    if (it.markOfTheButterflyExtraShot && it.shootCounter >= 3 && companionList.shootButterflyReserve > 0 && it.towerPrimaryElement == "butterfly") {
                        companionList.shootButterflyReserve -= 1
                        it.shootCounter = 0
                        var shootListIterator = companionList.shootList.listIterator()
                        var shootListPlace = Shoot()
                        var towerindex = companionList.towerList.indexOf(it)
                        shootListPlace.towerId = towerindex
                        shootListPlace.butterflyUltimate = true
                        shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                        shootListIterator.add(shootListPlace)   // add new shoot
                        GlobalScope.launch {
                            soundPool.play(soundIds[0], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                        }
                    } else if (it.shootCounter >= 3 && companionList.shootReserve > 0 && it.towerPrimaryElement != "wind") {
                        companionList.shootReserve -= 1
                        it.shootCounter = 0
                        var shootListIterator = companionList.shootList.listIterator()
                        var shootListPlace = Shoot()
                        var towerindex = companionList.towerList.indexOf(it)
                        shootListPlace.towerId = towerindex
                        shootListPlace.bounceLeft = it.shotBounceTargets
                        shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                        shootListIterator.add(shootListPlace)   // add new shoot
                        GlobalScope.launch {
                            soundPool.play(soundIds[0], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                        }
                    }
                }
            }finally {
                companionList.writeLockShot.unlock()
                companionList.readLockEnemy.unlock()
                companionList.readLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun crosses2(bullet: Shoot): Boolean {
            var check = false
            // check if shot crosses enemy
            companionList.readLockEnemy.lock()
            try {
                var sShotList = mutableListOf<Enemy>()
                var mShotList = mutableListOf<Enemy>()
                var enemyListIterator = companionList.enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()

                    val distanceX = (bullet.bullet.x) - it.circle!!.x
                    val distanceY = (bullet.bullet.y) - it.circle!!.y

                    val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                    val sumOfRadius = bullet.bullet.r + (it.circle!!.r - 20.0)

                    if (companionList.towerList[bullet.towerId].towerPrimaryElement == "moon" && companionList.enemyList.indexOf(it) == bullet.hitEnemyId){
                        check = false
                    }else if (squaredDistance <= sumOfRadius * sumOfRadius) {
                        check = true

                        if (companionList.towerList[bullet.towerId].towerPrimaryElement == "earth") {

                            var enemyListIteratorX = companionList.enemyList.listIterator()
                            while (enemyListIteratorX.hasNext()) {
                                var itX = enemyListIteratorX.next()
                                // check if shot crosses other enemies for splash damage
                                val distanceX2 = (bullet.bullet.x) - itX.circle!!.x
                                val distanceY2 = (bullet.bullet.y) - itX.circle!!.y

                                val squaredDistance2 =
                                    (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                                val sumOfRadius2 = (bullet.bullet.r + companionList.towerList[bullet.towerId].splashRange) + (itX.circle!!.r - 20)

                                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                            //        var splashListIterator = splashList.listIterator()
                                    var mShotListIterator = mShotList.listIterator()
                                    mShotListIterator.add(itX)

                                }
                            }
                        } else {
                            var sShotListIterator = sShotList.listIterator()
                            sShotListIterator.add(it)
                  //          var singleShotListIterator = singleShotList.listIterator()
                  //          singleShotListIterator.add(it)
                        }
                        crossedDmgCalc(bullet, sShotList, mShotList, bullet.towerId)      // calculate damage
                    }
                }
                return check
            } finally {
                companionList.readLockEnemy.unlock()
            }
        }

            private fun crossedDmgCalc(bullet: Shoot, singleShotList: MutableList<Enemy>, splashList: MutableList<Enemy>, towerId: Int) {
                // calculate damage for all enemies in splash radius

                companionList.writeLockDisplay.lock()
                try {

                    if (companionList.towerList[towerId].towerPrimaryElement == "earth") {

                        var splashListIterator = splashList.listIterator()
                        while (splashListIterator.hasNext()) {
                            var it = splashListIterator.next()
                            var splash15 = false

                            // dmg -------------------------------------------------------------------

                            if (it.hp > 0) {
                                var dmgDone = 0.0f
                                dmgDone =
                                    if (splash15(it.circle!!.x, it.circle!!.y, it.circle!!.r, bullet.bullet.x, bullet.bullet.y, bullet.bullet.r)) {
                                        splash15 = true // for boulder throw
                                        dmgCalc(it, splash15, bullet)
                                    } else if (splash30(it.circle!!.x, it.circle!!.y, it.circle!!.r, bullet.bullet.x, bullet.bullet.y, bullet.bullet.r)) dmgCalc(it, splash15, bullet) * 0.9f
                                    else if (splash60(it.circle!!.x, it.circle!!.y, it.circle!!.r, bullet.bullet.x, bullet.bullet.y, bullet.bullet.r)) dmgCalc(it, splash15, bullet) * 0.70f
                                    else if (splash100(it.circle!!.x, it.circle!!.y, it.circle!!.r, bullet.bullet.x, bullet.bullet.y, bullet.bullet.r)) dmgCalc(it, splash15, bullet) * 0.5f
                                    else if (splash150(it.circle!!.x, it.circle!!.y, it.circle!!.r, bullet.bullet.x, bullet.bullet.y, bullet.bullet.r)) dmgCalc(it, splash15, bullet) * 0.25f
                                    else 0.0f

                                if (companionList.towerList[towerId].experienceEarthHit && splashList.size > 3 && splash15) {
                                    when ((0..5).random()){
                                        0 -> towerExperience(towerId, 0.5f)
                                    }
                                }

                                if (bullet.sniper && companionList.towerList[towerId].itemSniperPro) dmgDone =
                                    itemSniperPro(dmgDone, it)
                                if (bullet.sniper) {
                                    dmgDone *= 3
                                    bullet.sniper = false
                                }

                                if (it.shield > 0) {
                                    if (it.shield > dmgDone) {
                                        it.shield -= dmgDone
                                        dmgDone = 0f
                                    } else {
                                        dmgDone = (dmgDone - it.shield)
                                        it.shield = 0f
                                    }
                                }

                              if (it.hit && it.shield <= 0f) dmgDone += spellDmg(it, splash15, towerId)

                                if (it.manaShield > 0) dmgDone = 0f

                                it.hp -= (dmgDone + companionList.towerList[towerId].particleDmg)
                                if ((dmgDone + companionList.towerList[towerId].particleDmg) > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when ((dmgDone + companionList.towerList[towerId].particleDmg).toInt()) {
                                        in 0..999 -> dmgString =
                                            (dmgDone + companionList.towerList[towerId].particleDmg).toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            ((dmgDone + companionList.towerList[towerId].particleDmg) / 1000).toInt()
                                                .toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            ((dmgDone + companionList.towerList[towerId].particleDmg) / 1000000).toInt()
                                                .toString() + "M"
                                    }
                                    var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintTowerDmgDone, 0, 0))
                                }

                                companionList.towerList[towerId].particleDmg = 0f
                                if (it.hp < 0) {
                                    if (companionList.towerList[towerId].particleDmgBool) companionList.towerList[towerId].particleDmg += (it.hp * -1)
                                    it.killerId = towerId
                                    companionList.enemyKilledList.add(it)
                                }
                                //    splashListIterator.remove()
                            }
                        }
                    }else {
                        var singleShotListIterator = singleShotList.listIterator()
                        while (singleShotListIterator.hasNext()) {
                            var it = singleShotListIterator.next()

                            if (it.hp > 0) {
                                var dmgDone = 0.0f
                                dmgDone = dmgCalc(it, true, bullet)

                                if (companionList.towerList[towerId].towerPrimaryElement == "moon") {
                                    when (bullet.alreadyBounced) {
                                        0 -> {
                                        }
                                        1 -> dmgDone *= 0.75f
                                        2 -> dmgDone *= 0.5f
                                        3 -> dmgDone *= 0.33f
                                        4 -> dmgDone *= 0.20f
                                        in 5..99 -> dmgDone *= 0.1f
                                    }
                                }
                                if (bullet.sniper && companionList.towerList[towerId].itemSniperPro) dmgDone =
                                    itemSniperPro(dmgDone, it)
                                if (bullet.sniper) {
                                    dmgDone *= 3
                                    bullet.sniper = false
                                }
                                if (companionList.towerList[towerId].markOfTheButterflyDmgBoostActive) {
                                    companionList.towerList[towerId].markOfTheButterflyDmgBoostActive = false
                                    dmgDone *= companionList.towerList[towerId].markOfTheButterfly
                                }

                                if (bullet.butterflyUltimate) dmgDone * companionList.towerList[towerId].markOfTheButterflyExtraShotDmg

                                if (it.shield > 0) {
                                    if (it.shield > dmgDone) {
                                        it.shield -= dmgDone
                                        dmgDone = 0f
                                    } else {
                                        dmgDone = (dmgDone - it.shield)
                                        it.shield = 0f
                                    }
                                }

                              if (it.hit && it.shield <= 0f) dmgDone += spellDmg(it, true, towerId)

                                if (it.manaShield > 0) dmgDone = 0f

                                it.hp -= (dmgDone + companionList.towerList[towerId].particleDmg)

                                if ((dmgDone + companionList.towerList[towerId].particleDmg) > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when ((dmgDone + companionList.towerList[towerId].particleDmg).toInt()) {
                                        in 0..999 -> dmgString =
                                            (dmgDone + companionList.towerList[towerId].particleDmg).toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            ((dmgDone + companionList.towerList[towerId].particleDmg) / 1000).toInt()
                                                .toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            ((dmgDone + companionList.towerList[towerId].particleDmg) / 1000000).toInt()
                                                .toString() + "M"
                                    }
                                    var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintTowerDmgDone, 0, 0))
                                }

                                companionList.towerList[towerId].particleDmg = 0f
                                if (it.hp < 0) {
                                    if (companionList.towerList[towerId].particleDmgBool) companionList.towerList[towerId].particleDmg += (it.hp * -1)
                                    if (companionList.towerList[towerId].darkPermaKill > 0) {
                                            for (item in companionList.towerList[towerId].itemListBag) {
                                                if (item.id == 6666) {
                                                    if (it.name == "boss") {
                                                        companionList.towerList[towerId].bonusPhysicalDmg += (companionList.towerList[towerId].darkPermaKill * 5) * (companionList.lvlScaler / 20)
                                                        item.specialFloat += (companionList.towerList[towerId].darkPermaKill * 5) * (companionList.lvlScaler / 20)
                                                    } else if (it.name == "challenge") {
                                                        companionList.towerList[towerId].bonusPhysicalDmg += (companionList.towerList[towerId].darkPermaKill * 10) * (companionList.lvlScaler / 20)
                                                        item.specialFloat += (companionList.towerList[towerId].darkPermaKill * 10) * (companionList.lvlScaler / 20)
                                                    } else {
                                                        companionList.towerList[towerId].bonusPhysicalDmg += companionList.towerList[towerId].darkPermaKill * (companionList.lvlScaler / 20)
                                                        item.specialFloat += companionList.towerList[towerId].darkPermaKill * (companionList.lvlScaler / 20)
                                                    }
                                                }
                                            }


                                        }
                                    it.killerId = towerId
                                    companionList.enemyKilledList.add(it)
                                }
                            }
                        }
                    }
                    splashList.removeAll(splashList)
                    singleShotList.removeAll(singleShotList)
                }finally {
                    companionList.writeLockDisplay.unlock()
                }
            }

        //------------------------------------------------------------------------------------

        private fun dmgCalc(it: Enemy, splash15: Boolean, bullet: Shoot): Float {
            // calculate damage

                    var autoAttackDmg = 0.0f
                    var dmgDoneX = 0.0f
                    var hitX =
                        if (!companionList.day && companionList.endlessNight > 0) (((companionList.towerList[bullet.towerId].hitChance + (3 * companionList.endlessNight)) - (((100) * (((it.evade + (companionList.evadeNight - (3 * companionList.endlessNight))) * 0.06) / (1 + (0.06 * (it.evade + (companionList.evadeNight - (3 * companionList.endlessNight))))))))) * 10).toInt()
                        else ((companionList.towerList[bullet.towerId].hitChance - (((100) * (((it.evade + companionList.evadeNight) * 0.06) / (1 + (0.06 * (it.evade + companionList.evadeNight))))))) * 10).toInt()       // + 10 evade at night
                    if (hitX > 999) hitX = 998

                    when ((0..999).random()) { //evade
                        in (hitX + 1)..999 -> {
                            dmgDoneX = 0.0f
                            if (companionList.towerList[bullet.towerId].wizardMissedLightning) companionList.towerList[bullet.towerId].wizardMissedLightningActive = true
                        }
                        in 0..hitX -> {
                            when ((0..999).random()) {      //crit
                                in 0..(companionList.towerList[bullet.towerId].overallCrit * 10).toInt() -> {
                                    if (!companionList.towerList[bullet.towerId].itemBoring) {      // item cant crit
                                        autoAttackDmg =
                                            ((companionList.towerList[bullet.towerId].overallTowerPhysicalDmg * companionList.towerList[bullet.towerId].overallCritDmg) * armorPen(it, companionList.towerList[bullet.towerId])) * multicrit(companionList.towerList[bullet.towerId])
                                        if (splash15 && companionList.towerList[bullet.towerId].itemListBag.contains(companionList.efire)) companionList.towerList[bullet.towerId].critCounter =
                                            0  // FireTalent
                                        if (companionList.towerList[bullet.towerId].itemListBag.contains(companionList.efire) && it.fireDebuff == 0 && splash15) {
                                            it.fireDebuff = 1
                                            it.fireDebuffTowerId = bullet.towerId
                                        } // FireTalent
                                        if (companionList.towerList[bullet.towerId].experienceFireCrit)  towerExperience(bullet.towerId, 0.1f)
                                    } else {
                                        autoAttackDmg = companionList.towerList[bullet.towerId].overallTowerPhysicalDmg * armorPen(it, companionList.towerList[bullet.towerId])
                                        if (splash15 && companionList.towerList[bullet.towerId].itemListBag.contains(companionList.efire)) companionList.towerList[bullet.towerId].critCounter += 1 // FireTalent
                                    }
                                }
                                in ((companionList.towerList[bullet.towerId].overallCrit * 10).toInt() + 1)..999 -> {
                                    autoAttackDmg = companionList.towerList[bullet.towerId].overallTowerPhysicalDmg * armorPen(it, companionList.towerList[bullet.towerId])
                                    if (splash15 && companionList.towerList[bullet.towerId].itemListBag.contains(companionList.efire)) companionList.towerList[bullet.towerId].critCounter += 1 // FireTalent
                                }
                            }

                            // all hits ---------------------------------------------------------
                            // all hits ---------------------------------------------------------
                            // all hits ---------------------------------------------------------

                            if (!it.invu) {
                                GlobalScope.launch {
                                    soundPool.play(soundIds[1], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                                }
                                it.hit = true
                                bullet.hitEnemyId = companionList.enemyList.indexOf(it)
                                if (companionList.towerList[bullet.towerId].towerPrimaryElement == "moon") {
                                    bullet.bullet.x = it.circle!!.x
                                    bullet.bullet.y = it.circle!!.y
                                }

                                // talent multi shot
                                if (companionList.towerList[bullet.towerId].towerPrimaryElement == "wind" && splash15) dmgDoneX =
                                    (autoAttackDmg / (companionList.towerList[bullet.towerId].crossesAllList.size + 2))
                                else dmgDoneX = autoAttackDmg
                                if (it.name == "immune" && companionList.towerList[bullet.towerId].earthDmgImmune > 1f) dmgDoneX *= companionList.towerList[bullet.towerId].earthDmgImmune

                                if (companionList.towerList[bullet.towerId].experienceEachHit) towerExperience(bullet.towerId, 0.03f)
                                if (companionList.towerList[bullet.towerId].goldDrop) {
                                    when ((0..299).random()) {
                                        0 -> {
                                            companionList.gold += (((0..(it.overallGold * 10).toInt()).random()) + 0.01f) * 0.1f
                                            companionList.dmgDisplayDropList.add(DropDisplay(companionList.towerList[bullet.towerId].towerRange.x.toInt(),companionList.towerList[bullet.towerId].towerRange.y.toInt(), "gold", 1, 50))
                                        }
                                    }
                                }
                                // pen
                                it.armor -= companionList.towerList[bullet.towerId].armorPenPerHit
                                it.magicArmor -= companionList.towerList[bullet.towerId].magicPenPerHit

                                // utils drop upgrade
                                if (companionList.towerList[bullet.towerId].utilsUpgrader > 0) {
                                    when ((0..200).random()) {
                                        in 0..companionList.towerList[bullet.towerId].utilsUpgrader.toInt() -> {
                                            companionList.upgradePoints++
                                            companionList.dmgDisplayDropList.add(DropDisplay(companionList.towerList[bullet.towerId].towerRange.x.toInt(),companionList.towerList[bullet.towerId].towerRange.y.toInt(), "up", 1, 50))
                                        }
                                    }
                                }

                                // dark talent slow
                                if (companionList.towerList[bullet.towerId].slowExtraDark > 0 && splash15) {
                                    it.darkDebuff = true // dark talent
                                    it.darkDebuffTowerId = bullet.towerId
                                }
                                // dark talent debuff
                                if (companionList.towerList[bullet.towerId].darkDmgDebuff > 0 && splash15) {
                                    it.darkMoreDmgDebuff = 1
                                    it.darkMoreDmgDebuffTowerId = bullet.towerId
                                    it.darkMoreDmgDebuffStacks += 1
                                }
                                // Ice talent slow
                                if (splash15 && companionList.towerList[bullet.towerId].slowEach > 0) {
                                    it.iceDebuffTowerId = bullet.towerId
                                    it.iceDebuff = 1
                                }
                                // Ice talent slow extra
                                if (companionList.towerList[bullet.towerId].slowExtra > 0 && splash15) {
                                    it.iceDebuffExtra = 1
                                    it.iceDebuffExtraTowerId = bullet.towerId
                                }
                                // Poison talent stackable poison
                                if (companionList.towerList[bullet.towerId].itemListBag.contains(companionList.epoison) && splash15) {
                                    if (it.poisonDebuff > 0 && companionList.towerList[bullet.towerId].overallTowerSpellDmg < companionList.towerList[it.poisonDebuffTowerId].overallTowerSpellDmg) {
                                    } else if (it.poisonDebuff > 0 && companionList.towerList[bullet.towerId].overallTowerSpellDmg > companionList.towerList[it.poisonDebuffTowerId].overallTowerSpellDmg) {
                                        it.poisonDebuffTowerId = bullet.towerId
                                    } else if (it.poisonDebuff == 0) it.poisonDebuffTowerId =
                                        bullet.towerId
                                    it.poisonDebuff = 1
                                    if (companionList.towerList[bullet.towerId].itemStartPoison) it.poisonStack += 2.0f
                                    else it.poisonStack += 1.0f
                                }
                                // Nature talent entangle
                                if (companionList.towerList[bullet.towerId].entangle > 0 && it.name != "immune" && splash15) {
                                    it.entangleOnHitTowerId = bullet.towerId
                                    it.entangleOnHit = 1
                                }
                                // Wind talent pushback
                                if (companionList.towerList[bullet.towerId].pushBack > 0 && it.name != "immune" && splash15) pushback(it, bullet)
                                // Wind talent bonus speed
                                if (companionList.towerList[bullet.towerId].bonusSpeedWindTalent > 0 && splash15) companionList.towerList[bullet.towerId].bonusSpeedWindTalentPercent += companionList.towerList[bullet.towerId].bonusSpeedWindTalent
                                // item slow death
                                if (companionList.towerList[bullet.towerId].itemSlowDeath > 0) {
                                    if (it.hp > 0) {
                                        var dmg =
                                            ((it.maxHp * companionList.towerList[bullet.towerId].itemSlowDeath))

                                        if (it.manaShield > 0) {
                                            if (it.manaShield > dmg) {
                                                it.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - it.manaShield)
                                                it.manaShield = 0f
                                            }
                                        }
                                        dmg *= magicPen(it, companionList.towerList[bullet.towerId])
                                        if (it.shield > 0) dmg = 0f

                                        it.hp -= dmg
                                        if (it.hp < 0) {
                                            dmgDoneX = 0f
                                            it.killerId = bullet.towerId
                                            companionList.enemyKilledList.add(it)
                                        }
                                    }
                                }
                                // wind debuff
                                if (companionList.towerList[bullet.towerId].windTalentDebuff > 0) it.winddebuffincreased =
                                    (1 + companionList.towerList[bullet.towerId].windTalentDebuff)

                                // talent wind ultimate
                                if (companionList.towerList[bullet.towerId].windUltimatePercent > 0) {
                                    if (it.hp > 0) {
                                        var dmg =
                                            ((it.maxHp * companionList.towerList[bullet.towerId].windUltimatePercent))

                                        if (it.manaShield > 0) {
                                            if (it.manaShield > dmg) {
                                                it.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - it.manaShield)
                                                it.manaShield = 0f
                                            }
                                        }
                                        dmg *= magicPen(it, companionList.towerList[bullet.towerId])
                                        if (it.shield > 0) dmg = 0f

                                        it.hp -= dmg
                                        if (it.hp < 0) {
                                            dmgDoneX = 0f
                                            it.killerId = bullet.towerId
                                            companionList.enemyKilledList.add(it)
                                        }
                                    }
                                }
                                // anti heal
                                if (companionList.towerList[bullet.towerId].bonusAntiHeal > 0) {
                                    it.antihealDebuff = 1
                                    it.antihealDebuffTowerId = bullet.towerId
                                }
                                // Dark talent instakill
                                if (splash15 && it.name != "boss" && it.name != "challenge" && it.instakillStacks < 11 && companionList.towerList[bullet.towerId].itemListBag.contains(companionList.edark)) {
                                    if (it.hp > 0) {
                                        it.instakillStacks++
                                        when ((0..999).random()) {
                                            in 0..(companionList.instaKill * 10).toInt() -> {
                                                dmgDoneX = 0f
                                                it.hp = -1.0f
                                                it.killerId = bullet.towerId
                                                it.instaKilled = 2
                                                companionList.enemyKilledList.add(it)
                                            }
                                        }
                                    }
                                }
                                // Butterfly talent

                                /*

                                if enemy stack from this tower > 0 -> stack +1
                                rest stacks from this tower 0

                                 */

                                if (companionList.towerList[bullet.towerId].towerPrimaryElement == "butterfly") {
                                    if (companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId < companionList.enemyList.size) {
                                        if (companionList.enemyList.indexOf(it) != companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId && companionList.enemyList[companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId].markOfTheButterflyCounter > 1 && it.markOfTheButterflyTowerCounter.size > 1) {
                                        } else if ((companionList.enemyList.indexOf(it) != companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId)) companionList.enemyList[companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId].markOfTheButterflyCounter =
                                            0
                                        else {
                                        }
                                    }
                                    it.markOfTheButterflyCounter++
                                    if (!it.markOfTheButterflyTowerCounter.contains(bullet.towerId)) it.markOfTheButterflyTowerCounter.add(bullet.towerId)
                                    companionList.towerList[bullet.towerId].markOfTheButterflyEnemyId =
                                        companionList.enemyList.indexOf(it)
                                    it.markOfTheButterflyTowerId = bullet.towerId
                                    if (it.markOfTheButterflyCounter >= 3) {
                                        it.markOfTheButterflyTowerCounter.remove(bullet.towerId)
                                        it.markOfTheButterflyCounter = 0
                                        if (companionList.towerList[bullet.towerId].experienceButterflyPop > 0) towerExperience(bullet.towerId, companionList.towerList[bullet.towerId].experienceButterflyPop)
                                        if (companionList.towerList[bullet.towerId].butterflyDebuffEnemyDmg > 0) {
                                            it.butterflyDebuffEnemyDmgTowerId = bullet.towerId
                                            it.butterflyDebuffEnemyDmg = 1
                                        }
                                        if (companionList.towerList[bullet.towerId].butterflyDebuffEnemyDmg > 0) {
                                            it.butterflyDebuffEnemyGoldXpTowerId = bullet.towerId
                                            it.butterflyDebuffEnemyGoldXp = 1
                                        }
                                        companionList.towerList[bullet.towerId].markOfTheButterflyDmgBoostActive =
                                            true
                                        if (companionList.towerList[bullet.towerId].markOfTheButterflySpdBoost > 0) {
                                            companionList.towerList[bullet.towerId].markOfTheButterflySpdBoostActiveNumber += companionList.towerList[bullet.towerId].markOfTheButterflySpdBoostTowersNumber
                                            companionList.towerList[bullet.towerId].markOfTheButterflySpdBoostActiveCounter =
                                                0
                                            companionList.towerList[bullet.towerId].markOfTheButterflySpdBoostActive =
                                                true
                                        }
                                        if (companionList.towerList[bullet.towerId].markOfTheButterflySlow && splash15) {
                                            it.markOfTheButterflySlowActive = 1
                                            it.markOfTheButterflySlowActiveTowerId = bullet.towerId
                                        }
                                        if (companionList.towerList[bullet.towerId].markOfTheButterflyExtraShot) {
                                            companionList.shootButterflyReserve++
                                        }

                                    }
                                }
                            }

                        }
                    }
            if (it.invu) dmgDoneX = 0f
            return dmgDoneX
        }

    //------------------------------------------------------------------------------------

    private fun crossesChainLightning(bullet: Shoot) : Boolean {
        // check if shot crosses enemy
        var check = false

        companionList.readLockEnemy.lock()
        try {

            var enemyListIterator = companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                val distanceX = (bullet.bullet.x) - it.circle!!.x
                val distanceY = (bullet.bullet.y) - it.circle!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = bullet.bullet.r + (it.circle!!.r - 20.0)

                if (companionList.enemyList.indexOf(it) == bullet.hitEnemyIdChain){
                    check = false
                }else if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true
                    bullet.hitEnemyIdChain = companionList.enemyList.indexOf(it)

                        bullet.bullet.x = it.circle!!.x
                        bullet.bullet.y = it.circle!!.y

                    if (it.hp > 0) {
                        var dmg =
                            (companionList.towerList[bullet.towerId].overallTowerSpellDmg + (companionList.towerList[bullet.towerId].chainLightningBonusDmg * 1.5f * companionList.bigNumberScaler)) * companionList.towerList[bullet.towerId].chainLightningDmg

                        if (it.manaShield > 0) {
                            if (it.manaShield > dmg) {
                                it.manaShield -= dmg
                                dmg = 0f
                            } else {
                                dmg = (dmg - it.manaShield)
                                it.manaShield = 0f
                            }
                        }
                        dmg *= magicPen(it, companionList.towerList[bullet.towerId])
                        if (it.shield > 0) dmg = 0f
                        when (bullet.alreadyBounced) {
                            0 -> {
                            }
                            1 -> dmg *= 0.9f
                            2 -> dmg *= 0.8f
                            3 -> dmg *= 0.7f
                            4 -> dmg *= 0.6f
                            5 -> dmg *= 0.5f
                            6 -> dmg *= 0.4f
                            7 -> dmg *= 0.3f
                            8 -> dmg *= 0.2f
                            in 9..99 -> dmg *= 0.1f
                        }
                        it.hp -= dmg

                        if (dmg > (it.maxHp / 100)) {
                            var dmgString = "0"
                            when (dmg.toInt()) {
                                in 0..999 -> dmgString = dmg.toInt().toString()
                                in 1000..999999 -> dmgString = (dmg / 1000).toInt().toString() + "k"
                                in 1000000..999999999 -> dmgString =
                                    (dmg / 1000000).toInt().toString() + "M"
                            }
                            companionList.writeLockDisplay.lock()
                            try {
                            var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                            dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                            } finally {
                                companionList.writeLockDisplay.unlock()
                            }
                        }

                        if (it.hp < 0) {
                            it.killerId = bullet.towerId
                            companionList.towerList[bullet.towerId].chainLightningBonusDmg += (it.xpEnemy * 0.2f)
                            companionList.enemyKilledList.add(it)
                        }
                    }
                    }
                }
            } finally {
                companionList.readLockEnemy.unlock()
            }

        return check
    }

        //------------------------------------------------------------------------------------

        private fun splash15(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 15 radius

                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = bulletR + (enemyR - 15.0)

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x

        }

        //------------------------------------------------------------------------------------

        private fun splash30(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 30 radius

                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = bulletR + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x

        }

        //------------------------------------------------------------------------------------

        private fun splash60(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 60 radius

                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 30.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x

        }

        //------------------------------------------------------------------------------------

        private fun splash100(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 100 radius

                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 70.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x

        }

        //------------------------------------------------------------------------------------

        private fun splash150(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 150 radius

                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 120.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x

        }

        //------------------------------------------------------------------------------------

        private fun crossesShard(shard: ShootIceTalent): Boolean {
            // ice talent cross check
            companionList.writeLockEnemy.lock()
            try {
                var check = false

                var enemyListIterator = companionList.enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()

                    val distanceX2 = (shard.shard.x) - it.circle!!.x
                    val distanceY2 = (shard.shard.y) - it.circle!!.y

                    val squaredDistance = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)
                    val sumOfRadius: Float
                    if (it.name == "boss") sumOfRadius =
                        shard.shard.r + it.circle!!.r * 1.5f
                    else sumOfRadius = shard.shard.r + it.circle!!.r

                    if (squaredDistance <= sumOfRadius * sumOfRadius) {

                        check = true

                        // dmg -------------------------------------------------------------------
                        Log.d("blabla", "hitnotinvu")
                        if(!it.invu) {
                            Log.d("blabla", "hit")
                            var dmg = 0f
                            if (it.name == "boss" || it.name == "challenge") dmg =
                                (it.maxHp * (0.05f + (0.01f * companionList.iceTowerCount))) * it.iceUltimateDR
                            else dmg =
                                ((it.maxHp * (0.10f + (0.01f * companionList.iceTowerCount))) * magicPen(it, Tower(0f, 0f, 0f, 0f, 0f, 0f))) * it.iceUltimateDR

                            if (it.hp > dmg) it.hp -= dmg
                            else it.hp = 0.1f
                            if (dmg > (it.maxHp / 100)) {
                                var dmgString = "0"
                                when (dmg.toInt()) {
                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                    in 1000..999999 -> dmgString =
                                        (dmg / 1000).toInt().toString() + "k"
                                    in 1000000..999999999 -> dmgString =
                                        (dmg / 1000000).toInt().toString() + "M"
                                }
                                companionList.writeLockDisplay.lock()
                                try {
                                var dmgDisplayListIterator =
                                    companionList.dmgDisplayList.listIterator()
                                dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                                } finally {
                                    companionList.writeLockDisplay.unlock()
                                }
                            }

                            it.iceUltimateDR *= 0.9f
                            shard.shardSpeed -= shard.shardSpeedReduce

                            if (companionList.towerList[it.iceDebuffTowerId].slowEach > 0) {
                                it.iceDebuff = 1
                                it.iceDebuffTowerId = shard.iceShardTowerId
                            }
                            if (companionList.towerList[it.iceDebuffTowerId].slowExtra > 0) {
                                it.iceDebuffExtra = 1
                                it.iceDebuffExtraTowerId = shard.iceShardTowerId
                            }
                        }
                        }
                }

                return check
            } finally {
                companionList.writeLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun crossesPoison(poisonClouds: TowerRadius): Boolean {
            // poison talent cross check
            var check = false

            companionList.writeLockEnemy.lock()

                try {
                    var enemyListIterator = companionList.enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()

                        val distanceX = (poisonClouds.x) - it.circle!!.x
                        val distanceY = (poisonClouds.y) - it.circle!!.y

                        val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                        val sumOfRadius = poisonClouds.r + it.circle!!.r

                        if (squaredDistance <= sumOfRadius * sumOfRadius) {
                            check = true

                            // dmg -------------------------------------------------------------------

                            if (it.poisonDebuff == 0) {
                                it.poisonDebuff = 1
                                it.poisonDebuffTowerId = companionList.towerList.indexOf(companionList.poisonTowerHighestDmg)
                            }
                            it.poisonStack += (0.1f * companionList.poisonCloud) * 10

                            //------------------------------------------------------------------------
                        }
                    }
                } finally {
                    companionList.writeLockEnemy.unlock()
                }
            return check
        }

    //------------------------------------------------------------------------------------

    private fun crossesTornado(tornadoRadius: TowerRadius): Boolean {
        var check = false

        if (companionList.enemyList.isNotEmpty()) {
            GameActivity.companionList.writeLockEnemy.lock()
            try {
                var enemyListIterator = companionList.enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()

                    val distanceX = (tornadoRadius.x) - it.circle!!.x
                    val distanceY = (tornadoRadius.y) - it.circle!!.y

                    val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                    val sumOfRadius = tornadoRadius.r + it.circle!!.r

                    if (squaredDistance <= sumOfRadius * sumOfRadius) {
                        check = true

                        // dmg -------------------------------------------------------------------

                        if (it.name != "immune") {
                            when(it.circleXMovement){
                                "xplus" -> {
                                    if (it.circle!!.x > tornadoRadius.x) it.circle!!.x -= 9
                                    else if (it.circle!!.x < tornadoRadius.x) it.circle!!.x += 3
                                }
                                "xminus" -> {
                                    if (it.circle!!.x > tornadoRadius.x) it.circle!!.x -= 3
                                    else if (it.circle!!.x < tornadoRadius.x) it.circle!!.x += 9
                                }
                            }
                            when(it.circleYMovement){
                                "yplus" -> {
                                    if (it.circle!!.y > tornadoRadius.y) it.circle!!.y -= 9
                                    else if (it.circle!!.y < tornadoRadius.y) it.circle!!.y += 3
                                }
                                "yminus" -> {
                                    if (it.circle!!.y > tornadoRadius.y) it.circle!!.y -= 3
                                    else if (it.circle!!.y < tornadoRadius.y) it.circle!!.y += 9
                                }
                            }
                        }
                        //------------------------------------------------------------------------
                    }
                }
            } finally {
                companionList.writeLockEnemy.unlock()
            }
        }
        return check
    }


    //------------------------------------------------------------------------------------

    private fun crossesMine(mine: TowerRadius): Boolean {
        var check = false

        companionList.writeLockEnemy.lock()
        companionList.writeLockDisplay.lock()
        try {
            var enemyListIterator = companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                val distanceX = (mine.x) - it.circle!!.x
                val distanceY = (mine.y) - it.circle!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = mine.r + it.circle!!.r

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true

                    // dmg -------------------------------------------------------------------

                    var enemyListIteratorZ = companionList.enemyList.listIterator()
                    while (enemyListIteratorZ.hasNext()) {
                        var otherEnemy = enemyListIteratorZ.next()
                        if (splash100(mine.x, mine.y, mine.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                            if (otherEnemy.mineAlreadyAffected) {
                                otherEnemy.baseSpeed += otherEnemy.mineSpeedReduce
                                otherEnemy.mineSpeedReduce = 0f
                            }

                            var dmg = ((3f * companionList.lvlScaler) * magicPen(it, Tower(0f,0f, 0f,  0f, 0f, 0f)))
                            if (otherEnemy.hp > dmg) otherEnemy.hp -= dmg
                            else otherEnemy.hp = 0.1f

                            if (dmg > (otherEnemy.maxHp / 100)) {
                                var dmgString = "0"
                                when (dmg.toInt()) {
                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                    in 1000..999999 -> dmgString =
                                        (dmg / 1000).toInt().toString() + "k"
                                    in 1000000..999999999 -> dmgString =
                                        (dmg / 1000000).toInt().toString() + "M"
                                }
                                var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                            }

                            if (otherEnemy.name == "speed") otherEnemy.extraSpeed = 0.0f       // counter speed
                            otherEnemy.mineSpeedReduce =
                                (otherEnemy.baseSpeed * 0.5f * otherEnemy.mineDR).toFloat()
                            otherEnemy.baseSpeed -= otherEnemy.mineSpeedReduce
                            otherEnemy.mineAlreadyAffected = true
                            otherEnemy.mineDR *= 0.66f
                        }
                    }

                    //------------------------------------------------------------------------
                }
            }
        } finally {
            companionList.writeLockEnemy.unlock()
            companionList.writeLockDisplay.unlock()
        }
        return check
    }
        //------------------------------------------------------------------------------------

        private fun crossesAll() {

            // calculating all enemies in tower range
            companionList.writeLockEnemy.lock()
            companionList.writeLockTower.lock()
            try {
                var enemyListIterator = companionList.enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()
                    if (companionList.towerList.isEmpty()){
                        if (it.dead) {
                            companionList.enemyRemoveList.add(it)
                        }
                    }else{
                    var towerListIterator = Companion.companionList.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var towerZ = towerListIterator.next()
                        val distanceX = (towerZ.towerRange.x) - it.circle!!.x
                        val distanceY = (towerZ.towerRange.y) - it.circle!!.y
                        val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)
                        val sumOfRadius = towerZ.towerR + (it.circle!!.r - 20)
                        if (squaredDistance <= sumOfRadius * sumOfRadius) {

                            if (towerZ.crossesAllList.contains(it)) {
                            } else {
                                if (it == towerZ.randomEnemyForShot) towerZ.randomEnemyForShotSticky =
                                    true
                                if (towerZ.towerPrimaryElement != "wind") {
                                    if (towerZ.firstLastRandom == 1) towerZ.crossesAllList.add(0, it)     // last
                                    else if (towerZ.firstLastRandom == 0 || towerZ.firstLastRandom == 3) towerZ.crossesAllList.add(towerZ.crossesAllList.size, it)      // first
                                    else if (towerZ.firstLastRandom == 2) towerZ.crossesAllList.add((0..towerZ.crossesAllList.size).random(), it)        // random

                                } else {
                                    //if (firstLastRandom == 1) crossesAllList.add(0, it)     // last
                                    //else crossesAllList.add(crossesAllList.size, it)      // first
                                    towerZ.crossesAllList.add(0, it)
                                }
                                if (towerZ.crossesNoneList.contains(it)) towerZ.crossesNoneList.remove(it)
                            }
                        } else {
                            if (towerZ.crossesAllList.contains(it)) towerZ.crossesAllList.remove(it)
                            if (towerZ.crossesNoneList.contains(it)) {
                            } else towerZ.crossesNoneList.add(0, it)
                        }
                        if (it.dead) {
                            if (companionList.dmgDisplayList.isNotEmpty()) {
                                companionList.writeLockDisplay.lock()
                                try {
                                var dmgDisplayListIterator = companionList.dmgDisplayList.listIterator()
                                while (dmgDisplayListIterator.hasNext()) {
                                    var display = dmgDisplayListIterator.next()
                                    if (display.indexx == it) {
                                        display.displayDmgDelete = true
                                    }
                                }
                                    } finally {
                                    companionList.writeLockDisplay.unlock()
                                }
                            }
                            if (it == towerZ.randomEnemyForShot) towerZ.randomEnemyForShotSticky =
                                true
                            towerZ.crossesAllList.remove(it)
                            towerZ.crossesNoneList.remove(it)
                            companionList.enemyRemoveList.add(it)
                        }
                    }
                    }
                }
            } finally {
                companionList.writeLockEnemy.unlock()
                companionList.writeLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun multicrit (tower: Tower): Int {

            // calculating multicrit
            var multicritcounter = 1
            var x = 1

            if (tower.overallMulticrit == 1) return 1

            while (x < tower.overallMulticrit) {
                when ((0..999).random()) {
                    in 0..(tower.overallCrit * 10).toInt() -> {
                        x += 1
                        multicritcounter += 1
                    }
                    in ((tower.overallCrit * 10).toInt() + 1)..999 -> x = tower.overallMulticrit
                }
            }
            if (tower.itemFastDraw && multicritcounter > 1) {
                companionList.shootReserve ++
            }
            return multicritcounter

        }

        //------------------------------------------------------------------------------------

        private fun spellDmg(it: Enemy, splash15: Boolean, towerId: Int): Float {
            // calculating magic damage

                var dmg = 0f

                // ice talent
                if (it.iceAlreadyAffected == 1){
                    dmg += (companionList.towerList[towerId].overallTowerSpellDmg *0.2f) * magicPen(it, companionList.towerList[towerId])
                }

                if (companionList.towerList[towerId].iceExtraDmg > 0 && splash15) {
                    when ((0..9).random()) {
                        0 -> dmg += (companionList.towerList[towerId].overallTowerSpellDmg * magicPen(it, companionList.towerList[towerId])) * companionList.towerList[towerId].iceExtraDmg
                        in 1..9 -> {
                        }
                    }
                }

                // dark talent
                if (companionList.towerList[towerId].darkMagicDmgPercent > 0) {
                    dmg += (companionList.towerList[towerId].overallTowerSpellDmg * companionList.towerList[towerId].darkMagicDmgPercent)
                }

                if  (it.manaShield > 0){
                    if (it.manaShield > dmg) {
                        it.manaShield -= dmg
                        dmg = 0f
                    }else {
                        dmg = (dmg - it.manaShield)
                        it.manaShield = 0f
                    }
                }
                dmg *= magicPen(it, companionList.towerList[towerId])

            var extraDmgSlow = 1f
            if (it.debuffExtraMgcDmg) extraDmgSlow = 1.2f
            dmg *= extraDmgSlow

                if (it.shield > 0) dmg = 0f
                return dmg

        }

        //------------------------------------------------------------------------------------

        private fun pushback(it: Enemy, bullet: Shoot) {
            // Wind talent

            when ((0..999).random()) {
                in 0..(99 * it.pushBackDR).toInt() -> {
                    when (it.circleXMovement) {
                        "xminus" ->
                            it.circle!!.x += (companionList.towerList[bullet.towerId].pushBack)
                        "xplus" ->
                            it.circle!!.x -= (companionList.towerList[bullet.towerId].pushBack)
                    }
                    when (it.circleYMovement) {
                        "yminus" ->
                            it.circle!!.y += (companionList.towerList[bullet.towerId].pushBack)
                        "xminus" ->
                            it.circle!!.y -= (companionList.towerList[bullet.towerId].pushBack)
                    }
                    it.pushBackDR *= 0.66f
                }
                in (99 * it.pushBackDR).toInt() + 1..999 -> {
                }
            }

        }

        //------------------------------------------------------------------------------------

        private fun itemsDmg (tower: Tower) :Float{

            var returnX = 0.0f

            if (tower.itemDisruptor > 0){
                if (companionList.mapMode != 2) returnX += tower.crossesAllList.size * tower.itemDisruptor
                else {
                    var x = tower.crossesAllList.size
                    if (x > 8) x = 8
                    returnX += x * tower.itemDisruptor
                }
            }

            return returnX

        }

        //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------

        fun items () {

            companionList.writeLockTower.lock()
            try {
                    var towerListIterator = companionList.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()

                        if (tower.itemSniper > 0) {
                            tower.itemSniperCount++
                            if (tower.itemSniperCount >= 300 && tower.crossesNoneList.isNotEmpty()) {
                                tower.itemSniperCount = 0
                                var itemSniperPlace = tower.itemSniper
                                while (itemSniperPlace > 0) {
                                    itemSniperPlace--
                                    companionList.writeLockShot.lock()
                                    try {
                                    var shootListPlace = Shoot()
                                    shootListPlace.sniper = true
                                        shootListPlace.bullet = TowerRadius(tower.towerRange.x, tower.towerRange.y, 5.0f)
                                        shootListPlace.bulletSpeed = 12f
                                    var shootListIterator = companionList.shootList.listIterator()
                                    shootListIterator.add(shootListPlace)
                                    } finally {
                                    companionList.writeLockShot.unlock()
                                    }
                                }
                            } else if (tower.itemSniperCount >= 300 && tower.crossesNoneList.isEmpty()) {
                                tower.itemSniperCount = 299
                            }
                        }
                    }
                        } finally {
                companionList.writeLockTower.unlock()
            }
        }


        private fun itemsPerRound () {

            var luckyCharmBool = 0
            var pirateItemBool = 0

            companionList.writeLockTower.lock()
            try {
                var towerListIterator = companionList.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()

                    tower.itemListBag.forEach() {
                        if (it.id == 4 || it.id == 104) {
                            luckyCharmBool++
                        }
                        if (it.id == 1100 || it.id == 1101 || it.id == 1102 || it.id == 1103 || it.id == 1104 || it.id == 1105) {
                            pirateItemBool++
                        }
                        if (it.id == 207 && it.special == "+ X dmg/round") {
                            tower.bonusTowerDmg -= it.specialFloat
                            it.specialFloat = it.lvlAqu.toFloat() * tower.itemPikaDmg
                            tower.bonusTowerDmg += it.specialFloat
                        }
                        if (it.id == 207 && it.special == "+ X spd/round") {
                            tower.bonusTowerSpeed -= it.specialFloat
                            it.specialFloat = it.lvlAqu.toFloat() * tower.itemPikaSpd
                            tower.bonusTowerSpeed += it.specialFloat
                        }
                        if (it.id == 207 && it.special == "+ X crit/round") {
                            tower.bonusCrit -= it.specialFloat
                            it.specialFloat = it.lvlAqu.toFloat() * tower.itemPikaCrit
                            tower.bonusCrit += it.specialFloat
                        }
                        if (it.id == 5000) {
                            tower.bonusTowerDmg -= it.specialFloat
                            it.specialFloat = 2f * companionList.lvlScaler
                            tower.bonusTowerDmg += it.specialFloat
                        }
                        if (it.id == 5001) {
                            tower.bonusTowerSpeed -= it.specialFloat
                            it.specialFloat = (10 + (12f * companionList.lvlScalerSecond))
                            tower.bonusTowerSpeed += it.specialFloat
                        }
                        if (it.id == 5002) {
                            tower.bonusCrit -= it.specialFloat
                            it.specialFloat = (9f * companionList.lvlScalerSecond)
                            tower.bonusCrit += it.specialFloat
                        }
                        if (it.id == 5003) {
                            tower.bonusPhysicalDmg -= it.specialFloat
                            it.specialFloat = 2f * companionList.lvlScaler
                            tower.bonusPhysicalDmg += it.specialFloat
                        }
                        if (it.id == 5004) {
                            tower.bonusSpellDamage -= it.specialFloat
                            it.specialFloat = 2f * companionList.lvlScaler
                            tower.bonusSpellDamage += it.specialFloat
                        }
                        if (it.id == 5005) {
                            tower.bonusCritDmg -= it.bonusCritDmgLevel
                            tower.bonusCrit -= it.specialFloat
                            it.bonusCritDmgLevel = 0.1f * companionList.lvlScalerSecond
                            it.specialFloat = 3f * companionList.lvlScalerSecond * 1.5f
                            tower.bonusCritDmg += it.bonusCritDmgLevel
                            tower.bonusCrit += it.specialFloat
                        }
                        if (it.id == 5006) {
                            tower.bonusSpellDamage -= it.specialFloat
                            it.specialFloat = 3f * companionList.lvlScaler * 0.5f
                            tower.bonusSpellDamage += it.specialFloat
                        }
                    }
                    tower.luckyCharmCount = luckyCharmBool
                    tower.pirateItemCount = pirateItemBool
                }
               } finally {
                companionList.writeLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        fun activeAbilitiesEffect(){

                if (companionList.activeAbilityHelpingHandButtonCounter > 0) companionList.activeAbilityHelpingHandButtonCounter ++

                if (companionList.activeAbilityHelpingHandActiveCounter == 1) {
                    companionList.globalDmgMultiplier += 0.3f
                    companionList.activeAbilityHelpingHandActiveCounter++
                } else if (companionList.activeAbilityHelpingHandActiveCounter >= 300){
                    companionList.globalDmgMultiplier -= 0.3f
                    companionList.activeAbilityHelpingHandActiveCounter = 0
                } else if (companionList.activeAbilityHelpingHandActiveCounter > 1) companionList.activeAbilityHelpingHandActiveCounter++

        }

        //------------------------------------------------------------------------------------


    fun deadSplit () {
        if (companionList.enemyListDeadSplit.isNotEmpty()) {
            companionList.writeLockEnemy.lock()
            try {
                var enemyListDeadSplitRemove = mutableListOf<Enemy>()
                var enemyListIterator = companionList.enemyListDeadSplit.listIterator()
                while (enemyListIterator.hasNext()) {
                    var enemy = enemyListIterator.next()

                    var enemyListIteratorZ = companionList.enemyList.listIterator()
                    var y = 10
                    repeat(2) {
                        var x =
                            Enemy(enemy.maxHp * 0.2f, enemy.maxHp * 0.2f, 0f, 0f, 0f, 0f, enemy.armor, enemy.magicArmor, enemy.evade, enemy.hpReg * 0, (enemy.xpEnemy * 0.5f), companionList.lvlSpd, R.color.splitter)
                        x.circle!!.x = (enemy.circle!!.x + y)
                        x.circle!!.y = enemy.circle!!.y
                        x.baseSpeed = x.speed
                        x.point = enemy.point
                        x.name = "splitter"
                        enemyListIteratorZ.add(x)
                        y -= 20
                    }
                    enemy.dead = true
                    enemyListDeadSplitRemove.add(enemy)
                    }
                companionList.enemyListDeadSplit.removeAll(enemyListDeadSplitRemove)
                } finally {
                    companionList.writeLockEnemy.unlock()
            }
        }
    }

        private fun dead () {

            if (companionList.enemyKilledList.isNotEmpty()) {
                companionList.writeLockEnemy.lock()
                try {
                    var removeKilledPlace = mutableListOf<Enemy>()
                    var enemyListIterator = companionList.enemyKilledList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var enemy = enemyListIterator.next()

                        // enemy DEAD

                        if (enemy.name == "split" && enemy.hp <= 0 && !enemy.dead) {
                            companionList.enemyListDeadSplit.add(enemy)

                        } else if (enemy.hp <= 0 && !enemy.dead) {

                            if (enemy.wizardBombActive > 0) {
                                for (otherEnemy in companionList.enemyList) {
                                    if (enemy != otherEnemy) {
                                        if (splash100(enemy.circle!!.x, enemy.circle!!.y, enemy.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                            var dmg = 0f
                                            dmg =
                                                ((companionList.towerList[enemy.wizardBombTowerId].wizardBombDmg * 0.66f * companionList.bigNumberScaler) + (companionList.towerList[enemy.wizardBombTowerId].overallTowerSpellDmg * (0.1f + companionList.wizardBombStartItemDmg)))

                                            if (otherEnemy.manaShield > 0) {
                                                if (otherEnemy.manaShield > dmg) {
                                                    otherEnemy.manaShield -= dmg
                                                    dmg = 0f
                                                } else {
                                                    dmg = (dmg - otherEnemy.manaShield)
                                                    otherEnemy.manaShield = 0f
                                                }
                                            }
                                            dmg *= magicPen(enemy, companionList.towerList[enemy.wizardBombTowerId])
                                            if (otherEnemy.shield > 0) dmg = 0f
                                            var extraDmgSlow = 1f
                                            if (otherEnemy.debuffExtraMgcDmg) extraDmgSlow = 1.2f
                                            otherEnemy.hp -= dmg * extraDmgSlow
                                            if (dmg > (otherEnemy.maxHp / 100)) {
                                                var dmgString = "0"
                                                when (dmg.toInt()) {
                                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                                    in 1000..999999 -> dmgString =
                                                        (dmg / 1000).toInt().toString() + "k"
                                                    in 1000000..999999999 -> dmgString =
                                                        (dmg / 1000000).toInt().toString() + "M"
                                                }
                                                companionList.writeLockDisplay.lock()
                                                try {
                                                    var dmgDisplayListIterator =
                                                        companionList.dmgDisplayList.listIterator()
                                                    dmgDisplayListIterator.add(DmgDisplay(companionList.enemyList[companionList.enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                                } finally {
                                                    companionList.writeLockDisplay.unlock()
                                                }
                                            }
                                            if (otherEnemy.hp <= 0) {
                                                otherEnemy.killerId = enemy.wizardBombTowerId
                                                if (!companionList.enemyKilledList.contains(otherEnemy)) companionList.enemyKilledList.add(otherEnemy)
                                            }
                                        }
                                    }
                                }
                            }
                            if (companionList.towerList[enemy.killerId].talentFireKill) companionList.towerList[enemy.killerId].bonusCrit += 0.5f

                            if (companionList.towerList[enemy.killerId].darkSoulCollector) {
                                for (item in companionList.towerList[enemy.killerId].itemListBag) {
                                    if (item.id == 6666) {
                                        if (enemy.name == "boss") {
                                            companionList.towerList[enemy.killerId].bonusPhysicalDmg += (companionList.towerList[enemy.killerId].darkPermaKill * 5) * (companionList.lvlScaler / 20)
                                            item.specialFloat += (companionList.towerList[enemy.killerId].darkPermaKill * 5) * (companionList.lvlScaler / 20)
                                        } else if (enemy.name == "challenge") {
                                            companionList.towerList[enemy.killerId].bonusPhysicalDmg += (companionList.towerList[enemy.killerId].darkPermaKill * 10) * (companionList.lvlScaler / 20)
                                            item.specialFloat += (companionList.towerList[enemy.killerId].darkPermaKill * 10) * (companionList.lvlScaler / 20)
                                        } else {
                                            companionList.towerList[enemy.killerId].bonusPhysicalDmg += companionList.towerList[enemy.killerId].darkPermaKill * (companionList.lvlScaler / 20)
                                            item.specialFloat += companionList.towerList[enemy.killerId].darkPermaKill * (companionList.lvlScaler / 20)
                                        }
                                    }
                                }
                            }

                            if (enemy.name == "boss") {         // boss
                                companionList.diamonds++
                                companionList.bossesKilled += 1
                                companionList.dropItemList += 1
                            } else if (enemy.name == "challenge") {    // challenge
                                if (companionList.mapMode != 2) companionList.lives += 5
                                else companionList.livesMode2 += 5
                                companionList.itemListInsertItem.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                                companionList.challengesKilled += 1
                                if (companionList.activeAbilityList.contains(aAid1)) {
                                    companionList.bombActiveAbility += 5
                                    companionList.insertSpellBomb += 1
                                } else {
                                    companionList.activeAbilityList.add(0, aAid1)
                                    companionList.insertSpell += 1
                                    companionList.insertSpellBomb += 1
                                    companionList.bombActiveAbility += 5
                                }
                                companionList.dropItemList += 1
                            }

                            companionList.overallXp += enemy.overallXp
                            companionList.gold += enemy.overallGold * companionList.towerList[enemy.killerId].itemPiggyBank
                            var xpGain: Float =
                                if (enemy.name == "boss") ((enemy.xpDrop * companionList.wiseMan * 5) * (0.2f + companionList.towerList[enemy.killerId].experienceKill))
                                else if (enemy.name == "challenge") (enemy.xpDrop * 8) * (0.2f + companionList.towerList[enemy.killerId].experienceKill)
                                else if (enemy.eliteMob || enemy.elementalMob) (enemy.xpDrop * 2) * (0.2f + companionList.towerList[enemy.killerId].experienceKill)
                                else (enemy.xpDrop) * (0.2f + companionList.towerList[enemy.killerId].experienceKill) * enemy.instaKilled
                            towerExperience(enemy.killerId, xpGain)

                            if (enemy.selected) {
                                supportFragmentManager.beginTransaction().apply {
                                    replace(R.id.fragment, fragmentStats)
                                        .addToBackStack(null)
                                        .commit()
                                }
                            }
                            companionList.dropItemList += 1
                            dropItem(enemy)

                            companionList.enemiesKilled += 1

                            companionList.writeLockTower.lock()
                            try {
                            var towerListIterator = companionList.towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()

                                if (tower.randomEnemyForShotChain == enemy) tower.randomEnemyChainBool =
                                    true
                                if (tower.randomEnemyForShot == enemy) tower.randomEnemyForShotBool =
                                    true
                            }
                                } finally {
                                companionList.writeLockTower.unlock()
                            }

                            enemy.dead = true

                        }
                        removeKilledPlace.add(enemy)
                    }
                    companionList.enemyKilledList.removeAll(removeKilledPlace)
                } finally {
                    companionList.writeLockEnemy.unlock()
                }
            }
        }

    fun dead2 (){
        companionList.writeLockEnemy.lock()
        try {
            var enemyListIterator = companionList.enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var enemy = enemyListIterator.next()
                if (enemy.reachedPortal && !enemy.dead) {
                    if (enemy.name == "challenge") {
                    } else if (enemy.name == "boss") {
                        companionList.bossesLeaked += 1
                        companionList.lives -= 3
                    } else companionList.lives -= 1
                    if (companionList.lives < 0) companionList.lives = 0
                    soundPool.play(soundIds[2], companionList.logVolumeEffects, companionList.logVolumeEffects, 1, 0, 1.0f)
                    enemy.dead = true
                }
            }
            } finally {
            companionList.writeLockEnemy.unlock()
            }
    }



    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private fun getItem(tower: Tower): Items {

        // grey: #DBDBDB (219,219,219)
        // blue: (94, 105, 255)
        // orange: (244, 221, 57)
        // lila: (132,73,228)
        // rot: (221,22,22)
        // green: consumables (128, 255, 128)
        // element: blueish (#ABC3F3, (171,195,243))


        // item value (dmg = 1; crt = 3; spd = 4) pro 15 xp
        // grey cost/dmg * 1,0, rare cost * 1.1 , epic cost * 1.25 , legendary cost* 1.5


        var x = Items(-1, 0, 0, 0, 0f, 0, 0f, 0, "???", R.drawable.questionmarkred, R.drawable.overlaytransparent, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0, "", 0f, "", 0f)

        when ((0..999).random()) {
            in 0..ceil((65 - tower.overallItemQuality) * 10).toInt() ->
                    if (companionList.itemListNormal.isNotEmpty()) {
                        when ((companionList.itemListNormal).random()) {
                            0 -> {
                                when ((0..3).random()) {
                                    in 0..1 -> x =
                                        Items(0, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Mace & Blaze", R.drawable.wandswordgrey, R.drawable.overlaytransparent, (0.75f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                    2 -> x =
                                        Items(0, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Mace", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent, 0f, (1.25f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                    3 -> x =
                                        Items(0, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Wand", R.drawable.zauberstabgrey, R.drawable.overlaytransparent, 0f, 0.0f, (1.25f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                }
                            }
                            1 -> x =
                                Items(1, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Bow", R.drawable.pbowgrey, R.drawable.overlaytransparent, (0.375f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, (2.0f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 1, "", 0f, "", 0f)
                            2 -> {
                                when ((0..2).random()) {
                                    0 -> x =
                                        Items(2, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Sword", R.drawable.pcritgrey, R.drawable.overlaytransparent, (0.375f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, (1.5f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 1, "", 0f, "", 0f)
                                    1,2 -> {
                                        x = Items(6, 1, 999, 0, 0f, 0, 0f, 0, "Gold Digger", R.drawable.goldgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ Gold", (((companionList.costBase * companionList.lvlXp) / 4) * tower.towerRarityMultiplier), "", 0f)
                                    }
                                }
                            }
                            3 -> x =
                                Items(3, 1, 25, 0, 0f, 0, 0f, 0, "Magic Box", R.drawable.magicboxgrey, R.drawable.overlaytransparent, (0.5f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, "no use cost", 0f, "", 0f)
                            4 -> x =
                                Items(4, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Lucky Charm", R.drawable.luckycharmgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 1, "", 0f, "plus % itemchance", 15f + ((ceil(companionList.level / 10.0f)) * 4 * tower.towerRarityMultiplier))
                            5 -> {
                                if (companionList.mapMode == 2) x =
                                    Items(5, 1, 999, 0, ((companionList.costGrey * companionList.lvlXp) / 2), 0, 0f, 0, "Bomb", R.drawable.bombgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: 5% dmg to all enemies", 0f, "", 0f)
                                else x =
                                    Items(5, 1, 999, 0, ((companionList.costGrey * companionList.lvlXp) / 2), 0, 0f, 0, "Bomb", R.drawable.bombgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: 20% dmg to all enemies", 0f, "", 0f)
                            }
                            9 -> x = Items(9, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Experiencer", R.drawable.xpgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain xp", 1+(companionList.level * 0.33f), "", 0f)
                            10 -> x = Items(10, 1, 999, 0, (companionList.costGrey * companionList.lvlXp), 0, 0f, 0, "Lucky Charm", R.drawable.xpgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 1, "", 0f, "plus % xp", 15f + ((ceil(companionList.level / 10.0f)) * 4 * tower.towerRarityMultiplier))
                        }
                    }
            in floor((65 - tower.overallItemQuality) * 10).toInt()..ceil((88 - (tower.overallItemQuality / 1.5)) * 10).toInt() ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (companionList.itemListRare.isNotEmpty()) {
                            when (companionList.itemListRare.random()) {
                                100 -> {
                                    when ((0..7).random()) {
                                        in 0..1 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Mace & Blaze", R.drawable.wandswordblue, R.drawable.overlaytransparent, (2.25f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        in 2..3 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Mace & Blaze", R.drawable.wandswordblue, R.drawable.overlaytransparent, (2.25f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        4 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Mace", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0f, (3.75f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        5 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Mace", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0f, (3.75f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        6 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Wand", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0f, 0.0f, (3.75f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        7 -> x =
                                            Items(100, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Wand", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0f, 0.0f, (3.75f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                101 -> x =
                                    Items(101, 1, 25, 0, 0f, 0, 0f, 0, "Rare Magic Box", R.drawable.magicboxblue, R.drawable.overlaytransparent, (1.5f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "no use cost", 0f, "", 0f)
                                102 -> {
                                    when ((0..1).random()) {
                                        0 -> x =
                                            Items(102, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Bow", R.drawable.pbowblue, R.drawable.overlaytransparent, (1.125f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, (6.0f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x =
                                            Items(102, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Bow", R.drawable.pbowblue, R.drawable.overlaytransparent, (1.125f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, (6.0f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                103 -> {
                                    when ((0..1).random()) {
                                        0 -> x =
                                            Items(103, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Sword", R.drawable.pcritblue, R.drawable.overlaytransparent, (1.125f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, (4.5f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x =
                                            Items(103, 1, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Rare Sword", R.drawable.pcritblue, R.drawable.overlaytransparent, (1.125f * companionList.lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, (4.5f * companionList.lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 2, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                104 -> x =
                                    Items(104, 30, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Lucky Charm", R.drawable.luckycharmblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 2, "", 0f, "item quality", 2f + (ceil(companionList.level / 10.0f)) * tower.towerRarityMultiplier)
                                105 -> x =
                                    Items(105, 1, 999, 0, 0f, 1, 0f, 0, "Piggy Bank", R.drawable.piggybank, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "10% more Gold from enemies", 0f, "", 0f)
                                106 -> x =
                                    Items(106, 10, 999, 0, (companionList.costBlue * companionList.lvlXp), 0, 0f, 0, "Heart", R.drawable.heartgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 live", 1f, "", 0f)
                                107 -> x =
                                    Items(107, 10, 999, 0, (companionList.costBlue * companionList.lvlXp * 0.66f), 0, 0f, 0, "Number One", R.drawable.numberonegreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 talent point", 1f, "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()) {
                            0 -> x = Items(2100, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Earth Tower", R.drawable.towerearthblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            1 -> x = Items(2101, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Wizard Tower", R.drawable.towerwizardblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            2 -> x = Items(2102, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Ice Tower", R.drawable.towericeblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
                            3 -> x = Items(2103, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Butterfly Tower", R.drawable.towerbutterflyblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            4 -> x = Items(2104, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Poison Tower", R.drawable.towerpoisonblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            5 -> x = Items(2105, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Moon Tower", R.drawable.towermoonblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            6 -> x = Items(2106, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Wind Tower", R.drawable.towerwindblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
                            7 -> x = Items(2107, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Utils Tower", R.drawable.towerutilsblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0,"bagspace", 3.0f, "bagspace element", 2f)
                            8 -> x = Items(2108, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Fire Tower", R.drawable.towerfireblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0,"bagspace", 3.0f, "bagspace element", 2f)
                            9 -> x = Items(2109, 0, 999,0,(companionList.costBlue * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Rare Dark Tower", R.drawable.towerdarkblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                        }
                    }
                }
            in floor((88 - (tower.overallItemQuality / 1.5)) * 10).toInt()..ceil((96 - (tower.overallItemQuality / 2)) * 10).toInt() ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (companionList.itemListEpic.isNotEmpty()) {
                            when (companionList.itemListEpic.random()) {
                                200 -> {
                                    when ((0..3).random()) {
                                        in 0..1 -> x =
                                            Items(200, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Mace & Blaze", R.drawable.wandswordorange, R.drawable.overlaytransparent, ((3.0f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPen(), (0.6f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                        2 -> x =
                                            Items(200, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Mace", R.drawable.pdoubleswordsorange, R.drawable.overlaytransparent, 0f, ((5.0f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPenPhy(), (0.6f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                        3 -> x =
                                            Items(200, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Wand", R.drawable.zauberstaborange, R.drawable.overlaytransparent, 0f, 0.0f, ((5.0f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 3, "magic penetration", (0.6f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                    }
                                }
                                201 -> x =
                                    Items(201, 1, 25, 0, 0f, 0, 0f, 0, "Epic Magic Box", R.drawable.magicboxorange, R.drawable.overlaytransparent, ((2.0f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, "no use cost", 0f, "", 0f)
                                202 -> x =
                                    Items(202, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Bow", R.drawable.pboworange, R.drawable.overlaytransparent, ((1.5f * companionList.lvlScaler) + (companionList.level * 0.0375f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((8.0f * companionList.lvlScalerSecond) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 3, specialPen(), (0.6f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                203 -> x =
                                    Items(203, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Sword", R.drawable.pcritorange, R.drawable.overlaytransparent, ((1.5f * companionList.lvlScaler) + (companionList.level * 0.0375f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, ((6.0f * companionList.lvlScalerSecond) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 3, specialPen(), (0.6f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                204 -> x =
                                    Items(204, 30, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Lucky Charm", R.drawable.luckycharmorange, R.drawable.overlaytransparent, (2.0f * companionList.lvlScaler)* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 3, "", 0f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                205 -> x =
                                    Items(205, 10, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Lifeline", R.drawable.lifelineorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 live/10 rounds ", 1f, "", 0f)
                                206 -> x =
                                    Items(206, 1, 30, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "X-Factor", R.drawable.talentorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+5 talent points", 5f, "", 0f)
                                207 -> when ((0..2).random()) {
                                    0 -> x =
                                        Items(207, 1, 30, 0, (companionList.costEpic * companionList.lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X dmg/round", 0.5f* tower.towerRarityMultiplier, "", 0f)
                                    1 -> x =
                                        Items(207, 1, 30, 0, (companionList.costEpic * companionList.lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X spd/round", 2f* tower.towerRarityMultiplier, "", 0f)
                                    2 -> x =
                                        Items(207, 1, 30, 0, (companionList.costEpic * companionList.lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X crit/round", 1.5f* tower.towerRarityMultiplier, "", 0f)
                                }
                                208 -> x =
                                    Items(208, 30, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Freezer", R.drawable.frostorange, R.drawable.overlaytransparent, 0.0f, 0.0f, ((2f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0f, 0.0f, 0.0f, 0, "slows a random unit for 20%", 20.0f, "", 0f)
                                209 -> x =
                                    Items(209, 1, 50, 0, 0f, 1, 0f, 0, "Coin", R.drawable.coininterest, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ 1.5% interest rate", 0.015f, "", 0f)
                                210 -> x =
                                    Items(210, 50, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Lasso", R.drawable.lassoorange, R.drawable.overlaytransparent, ((0.5f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 3, "throws X lassos that stun", 1.0f, "", 0f)
                                211 -> x =
                                    Items(211, 30, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "20/20", R.drawable.critdmgorange, R.drawable.overlaytransparent, ((1.5f * companionList.lvlScaler) + (companionList.level * 0.0375f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, ((4.5f * companionList.lvlScalerSecond) + (companionList.level * 0.015f))* tower.towerRarityMultiplier, (0.01f * companionList.level)* tower.towerRarityMultiplier, 3, "", 0.0f, "", 0f)
                                212 -> x =
                                    Items(212, 40, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "One Punch Thingy", R.drawable.hitorange, R.drawable.overlaytransparent, ((0.5f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X hit", (0.75f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                213 -> x =
                                    if (companionList.mapMode == 2) Items(213, 25, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Last Stance", R.drawable.laststanceorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "+10% dmg/live lost", 0.0f, "", 0f)
                                    else Items(200, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Epic Mace & Blaze", R.drawable.wandswordorange, R.drawable.overlaytransparent, ((3.0f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPen(), (0.75f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                214 -> x =
                                    Items(214, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Sniper", R.drawable.sniperorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "snipes a single target", 0.0f, "", 0f)
                                215 -> x =
                                    Items(215, 40, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Shredder", R.drawable.shredderorange, R.drawable.overlaytransparent, ((0.5f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X Armor Penetration", (0.33f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                216 -> x =
                                    Items(216, 40, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Shield Breaker", R.drawable.magicbrakerorange, R.drawable.overlaytransparent, ((0.5f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X Magic Penetration", (0.33f * companionList.lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                217 -> x =
                                    Items(217, 1, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Snowman", R.drawable.snowmanorange, R.drawable.overlaytransparent, ((0.5f * companionList.lvlScaler) + (companionList.level * 0.05f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((4f * companionList.lvlScalerSecond) + (companionList.level * 0.05f))* tower.towerRarityMultiplier, ((3f * companionList.lvlScalerSecond) + (companionList.level * 0.05f))* tower.towerRarityMultiplier, 0f, 3, "+10% slow on hit", 0f, "", 0f)
                                218 -> x =
                                    Items(218, 20, 999, 0, (companionList.costEpic * companionList.lvlXp), 0, 0f, 0, "Brain Damage", R.drawable.braindamageorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+1% dmg/towerlvl", tower.towerLevel.toFloat(), "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()){
                            0 -> x = Items(2200, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Earth Tower", R.drawable.towerearthorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                            1 -> x = Items(2201, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Wizard Tower", R.drawable.towerwizardorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            2 -> x = Items(2202, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Ice Tower", R.drawable.towericeorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            3 -> x = Items(2203, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Butterfly Tower", R.drawable.towerbutterflyorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            4 -> x = Items(2204, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Poison Tower", R.drawable.towerpoisonorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            5 -> x = Items(2205, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Moon Tower", R.drawable.towermoonorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            6 -> x = Items(2206, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Wind Tower", R.drawable.towerwindorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                            7 -> x = Items(2207, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Utils Tower", R.drawable.towerutilsorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0,"bagspace", 4.0f, "bagspace element", 2f)
                            8 -> x = Items(2208, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Fire Tower", R.drawable.towerfireorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f, 0,"bagspace", 4.0f, "bagspace element", 2f)
                            9 -> x = Items(2209, 0, 999,0,(companionList.costEpic * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Epic Dark Tower", R.drawable.towerdarkorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,55f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                        }
                    }
                }
            in floor((96 - (tower.overallItemQuality / 2)) * 10).toInt()..999 ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (companionList.itemListLegendary.isNotEmpty()) {
                            when (companionList.itemListLegendary.random()) {
                                300 ->
                                    when ((0..11).random()) {
                                        0 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        2 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        3 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        6 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        7 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        8 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        9 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        10 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        11 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        12 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        13 -> x = Items(300, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                301 -> x = Items(301, 1, 25, 0, 0f, 0, 0f, 0, "Legendary Magic Box", R.drawable.magicboxpurple, R.drawable.overlaytransparent, ((3.0f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "no xp cost", 0f, "", 0f)
                                302 ->
                                    when ((0..2).random()) {
                                        0 -> x = Items(302, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((12.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(302, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((12.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        2 -> x = Items(302, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((12.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        3 -> x = Items(302, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((12.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                303 ->
                                    when ((0..2).random()) {
                                        0 -> x = Items(303, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, ((9.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(303, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, ((9.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(companionList.level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        2 -> x = Items(303, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, ((9.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                        3 -> x = Items(303, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, ((9.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(companionList.level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                304 -> x = Items(304, 20, 100, 0, (companionList.costLegendary * companionList.lvlXp * 0.5f), companionList.costDia, 0f, 0, "Frost Aura", R.drawable.iceaurapurple, R.drawable.overlaytransparent, ((1.0f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((1.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0, "Slow aura X%", 20f, "", 0f)
                                305 -> x = Items(305, 1, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Disruptor", R.drawable.disruptorpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "Adds X dmg per enemy in tower range", (2f * companionList.lvlScaler)* tower.towerRarityMultiplier, "", 0f)
                                306 -> x = Items(306, 50, 999, 0, 0f, companionList.costDia, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f)
                                307 -> x = Items(307, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Multibarrel", R.drawable.bouncepurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((4.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "+2 bounce", 2f, "", 0f)
                                308 -> x = Items(308, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Freezer", R.drawable.frostlila, R.drawable.overlaytransparent, ((1.0f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((4.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, ((3.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "slows a random unit for 30%", 30.0f, "", 0f)
                                309 -> x = Items(309, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Lasso", R.drawable.lassopurple, R.drawable.overlaytransparent, ((1.0f * companionList.lvlScaler) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 5, "throws X lassos that stun", 3.0f, "", 0f)
                                310 -> x = Items(310, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "BullZ-I", R.drawable.bullseyepurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, ((6.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0f, 5, "+1 multicrit", 1f, "", 0f)
                                311 -> x = Items(311, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "21/20", R.drawable.critdmgpurple, R.drawable.overlaytransparent, ((2.25f * companionList.lvlScaler) + (companionList.level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, ((6f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, (0.015f * companionList.level)* tower.towerRarityMultiplier, 5, "", 0.0f, "", 0f)
                                312 -> x = Items(312, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Slow Death", R.drawable.slowdeathpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "burns 1% hp as mgc dmg per attack", 0.01f, "", 0f)
                                313 -> x = Items(313, 10, 999, 0, 0f, companionList.costDia, 0f, 0, "Lovely!", R.drawable.heartdiagreen, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "+5 lives", 5f, "", 0f)
                                314 -> x = Items(314, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Shredder", R.drawable.shredderpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, ((6.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0f, 0f, 0, "reduces armor by X per hit", 0.5f* tower.towerRarityMultiplier, "", 0f)
                                315 -> x = Items(315, 30, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Legendary Magic Braker", R.drawable.magicbrakerpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, ((6.0f * companionList.lvlScalerSecond) + (companionList.level * 0.15f))* tower.towerRarityMultiplier, 0f, 0f, 0, "reduces magic armor by X per hit", 0.5f* tower.towerRarityMultiplier, "", 0f)
                                316 -> x = Items(316, 20, 999, 0, (companionList.costLegendary * companionList.lvlXp), companionList.costDia, 0f, 0, "Buddha", R.drawable.buddhapurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+1% dmg/% towerlvl", (tower.xpTower/tower.xpGoal2).toFloat(), "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()){
                            0 -> x = Items(2300, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Earth Tower", R.drawable.towerearthpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            1 -> x = Items(2301, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Wizard Tower", R.drawable.towerwizardpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                            2 -> x = Items(2302, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Ice Tower", R.drawable.towericepurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            3 -> x = Items(2303, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Butterfly Tower", R.drawable.towerbutterflypurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            4 -> x = Items(2304, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Poison Tower", R.drawable.towerpoisonpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            5 -> x = Items(2305, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Moon Tower", R.drawable.towermoonpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            6 -> x = Items(2306, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Wind Tower", R.drawable.towerwindpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                            7 -> x = Items(2307, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Utils Tower", R.drawable.towerutilspurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f,0,"bagspace", 5.0f, "bagspace element", 3f)
                            8 -> x = Items(2308, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Fire Tower", R.drawable.towerfirepurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f,0,"bagspace", 5.0f, "bagspace element", 3f)
                            9 -> x = Items(2309, 0, 999,0,(companionList.costLegendary * companionList.lvlXp * (1.5f + (companionList.towerCount /5))), 0, 0f, 0, "Legendary Dark Tower", R.drawable.towerdarkpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,50f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                        }
                    }
                }
        }

        return x

    }

    private fun getItem2(tower: Tower): Items {

            var x = Items(-1, 0, 0, 0, 0f, 0, 0f, 0, "???", R.drawable.questionmarkred, R.drawable.overlaytransparent, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0, "", 0f, "", 0f)

            when ((0..999).random()) {
                in 0..ceil((65 - tower.overallItemQuality) * 10).toInt() ->
                    when ((0..1).random()) {
                        0 -> x = Items(7, 1, 999, 0, 0f, 0, 0f, 0, "Upgrader", R.drawable.upgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain 1 UP point", 0f, "", 0f)
                        1 -> {
                            when ((0..9).random()){
                                0 -> x = companionList.eearth
                                1 -> x = companionList.ewizard
                                2 -> x = companionList.eice
                                3 -> x = companionList.ebutterfly
                                4 -> x = companionList.epoison
                                5 -> x = companionList.emoon
                                6 -> x = companionList.ewind
                                7 -> x = companionList.eutils
                                8 -> x = companionList.efire
                                9 -> x = companionList.edark
                            }
                        }
                    }
                in floor((65 - tower.overallItemQuality) * 10).toInt()..ceil((88 - (tower.overallItemQuality / 1.5)) * 10).toInt() -> {
                    when ((0..2).random()) {
                        0 -> x =
                            Items(7, 1, 999, 0, 0f, 0, 0f, 0, "Upgrader", R.drawable.upgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain 1 UP point", 0f, "", 0f)
                        1 -> {
                            when ((0..9).random()) {
                                0 -> x = companionList.eearth
                                1 -> x = companionList.ewizard
                                2 -> x = companionList.eice
                                3 -> x = companionList.ebutterfly
                                4 -> x = companionList.epoison
                                5 -> x = companionList.emoon
                                6 -> x = companionList.ewind
                                7 -> x = companionList.eutils
                                8 -> x = companionList.efire
                                9 -> x = companionList.edark
                            }
                        }
                        2 -> {
                            when ((0..9).random()) {
                                0 -> x =
                                    Items(2100, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Earth Tower", R.drawable.towerearthblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                1 -> x =
                                    Items(2101, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Wizard Tower", R.drawable.towerwizardblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                2 -> x =
                                    Items(2102, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Ice Tower", R.drawable.towericeblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                3 -> x =
                                    Items(2103, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Butterfly Tower", R.drawable.towerbutterflyblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                4 -> x =
                                    Items(2104, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Poison Tower", R.drawable.towerpoisonblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                5 -> x =
                                      Items(2105, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Moon Tower", R.drawable.towermoonblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                  6 -> x =
                                      Items(2106, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Wind Tower", R.drawable.towerwindblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                  7 -> x =
                                      Items(2107, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Utils Tower", R.drawable.towerutilsblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                  8 -> x =
                                      Items(2108, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Fire Tower", R.drawable.towerfireblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                                  9 -> x =
                                      Items(2109, 0, 999, 0, (companionList.costBlue * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Rare Dark Tower", R.drawable.towerdarkblue, R.drawable.overlaytransparent, 10f, 0.0f, 0.0f, 60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                              }
                          }
                      }
                    }
                in floor((88 - (tower.overallItemQuality / 1.5)) * 10).toInt()..ceil((96 - (tower.overallItemQuality / 2)) * 10).toInt() -> {
                    when ((0..2).random()) {
                        0 -> x =
                            Items(7, 1, 999, 0, 0f, 0, 0f, 0, "Upgrader", R.drawable.upgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain 1 UP point", 0f, "", 0f)
                        1 -> {
                            when ((0..9).random()) {
                                0 -> x = companionList.eearth
                                1 -> x = companionList.ewizard
                                2 -> x = companionList.eice
                                3 -> x = companionList.ebutterfly
                                4 -> x = companionList.epoison
                                5 -> x = companionList.emoon
                                6 -> x = companionList.ewind
                                7 -> x = companionList.eutils
                                8 -> x = companionList.efire
                                9 -> x = companionList.edark
                            }
                        }
                        2 -> {
                            when ((0..9).random()) {
                                0 -> x =
                                    Items(2200, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Earth Tower", R.drawable.towerearthorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                1 -> x =
                                    Items(2201, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Wizard Tower", R.drawable.towerwizardorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                2 -> x =
                                    Items(2202, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Ice Tower", R.drawable.towericeorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                3 -> x =
                                    Items(2203, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Butterfly Tower", R.drawable.towerbutterflyorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                4 -> x =
                                    Items(2204, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Poison Tower", R.drawable.towerpoisonorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                5 -> x =
                                    Items(2205, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Moon Tower", R.drawable.towermoonorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                6 -> x =
                                    Items(2206, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Wind Tower", R.drawable.towerwindorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                7 -> x =
                                    Items(2207, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Utils Tower", R.drawable.towerutilsorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                8 -> x =
                                    Items(2208, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Fire Tower", R.drawable.towerfireorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                                9 -> x =
                                    Items(2209, 0, 999, 0, (companionList.costEpic * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Epic Dark Tower", R.drawable.towerdarkorange, R.drawable.overlaytransparent, 15f, 0.0f, 0.0f, 55f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                            }
                        }
                    }
                }
                in floor((96 - (tower.overallItemQuality / 2)) * 10).toInt()..999 -> {
                    when ((0..2).random()) {
                        0 -> x =
                            Items(7, 1, 999, 0, 0f, 0, 0f, 0, "Upgrader", R.drawable.upgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain 1 UP point", 0f, "", 0f)
                        1 -> {
                            when ((0..9).random()) {
                                0 -> x = companionList.eearth
                                1 -> x = companionList.ewizard
                                2 -> x = companionList.eice
                                3 -> x = companionList.ebutterfly
                                4 -> x = companionList.epoison
                                5 -> x = companionList.emoon
                                6 -> x = companionList.ewind
                                7 -> x = companionList.eutils
                                8 -> x = companionList.efire
                                9 -> x = companionList.edark
                            }
                        }
                        2 -> {
                            when ((0..9).random()) {
                                0 -> x =
                                    Items(2300, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Earth Tower", R.drawable.towerearthpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                1 -> x =
                                    Items(2301, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Wizard Tower", R.drawable.towerwizardpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                2 -> x =
                                    Items(2302, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Ice Tower", R.drawable.towericepurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                3 -> x =
                                    Items(2303, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Butterfly Tower", R.drawable.towerbutterflypurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                4 -> x =
                                    Items(2304, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Poison Tower", R.drawable.towerpoisonpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                5 -> x =
                                    Items(2305, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Moon Tower", R.drawable.towermoonpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                6 -> x =
                                    Items(2306, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Wind Tower", R.drawable.towerwindpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                7 -> x =
                                    Items(2307, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Utils Tower", R.drawable.towerutilspurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                8 -> x =
                                    Items(2308, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Fire Tower", R.drawable.towerfirepurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                                9 -> x =
                                    Items(2309, 0, 999, 0, (companionList.costLegendary * companionList.lvlXp * (1.2f + (companionList.towerCount / 5))), 0, 0f, 0, "Legendary Dark Tower", R.drawable.towerdarkpurple, R.drawable.overlaytransparent, 25f, 0.0f, 0.0f, 50f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            }
                        }
                    }
                    }
            }

            return x

    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------
    fun specialPen (): String {
        var stats = "hi"
        when ((0..2).random()) {
            0 -> stats = "armor penetration"
            1 -> stats = "magic penetration"
            2 -> stats = "hit chance"
        }
        return stats
    }
    fun specialPenPhy (): String {
        var stats = "hi"
        when ((0..1).random()) {
            0 -> stats = "armor penetration"
            1 -> stats = "hit chance"
        }
        return stats
    }

    fun armorPen(it: Enemy, tower: Tower): Float {
        var armorPenPlace = 0f

        if (tower.overallBonusArmorPen < it.armor) {
            armorPenPlace =
                1- (((it.armor - tower.overallBonusArmorPen) * 0.06f) / (1 + (0.06f * (it.armor - tower.overallBonusArmorPen))))
        }else {
            armorPenPlace = ((2 - 0.94).pow(-(it.armor - tower.overallBonusArmorPen).toDouble())).toFloat()
        }

        return armorPenPlace
    }

    fun magicPen(it: Enemy, tower: Tower): Float {
        var magicArmorPenPlace = 0f

        if (tower.overallBonusMagicPen < it.magicArmor) {
            magicArmorPenPlace =
               1- (((it.magicArmor - tower.overallBonusMagicPen) * 0.06f) / (1 + (0.06f * (it.magicArmor - tower.overallBonusMagicPen))))
        }else {
            magicArmorPenPlace = ((2 - 0.94).pow(-(it.magicArmor - tower.overallBonusMagicPen).toDouble())).toFloat()
        }

        return magicArmorPenPlace
    }

    fun itemSniperPro(dmg: Float, it: Enemy): Float {

            when {
                it.circle!!.x < 600 -> {
                    when (600 - it.circle!!.x.toInt()) {
                        in 0..200 -> dmg * 0.75
                        in 201..300 -> dmg * 1.25
                        else -> dmg * 2
                    }
                }
                it.circle!!.x > 600 -> {
                    when (it.circle!!.x.toInt() - 600) {
                        in 0..200 -> dmg * 0.75
                        in 201..300 -> dmg * 1.25
                        else -> dmg * 2
                    }
                }
                it.circle!!.y < 750 -> {
                    when (750 - it.circle!!.y.toInt()) {
                        in 0..200 -> dmg * 0.75
                        in 201..300 -> dmg * 1.25
                        else -> dmg * 2
                    }
                }
                it.circle!!.y > 750 -> {
                    when (it.circle!!.y.toInt() - 750) {
                        in 0..200 -> dmg * 0.75
                        in 201..300 -> dmg * 1.25
                        else -> dmg * 2
                    }
                }
            }
        return dmg

    }

    fun towerExperienceGainUtilAura(xpGain: Float, killerId: Int) {

            var it = companionList.towerList[killerId]

            var towerListIteratorZ = companionList.towerList.listIterator()
            while (towerListIteratorZ.hasNext()) {
                var tower = towerListIteratorZ.next()
                if (it != tower) {
                    if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                        if (tower.experienceGainUtilsAura) tower.xpTower += xpGain * 0.1f
                    }
                }
            }
    }

    fun towerExperienceShareUtilAura(xpGain: Float, killerId: Int) {

            var it = companionList.towerList[killerId]

            var towerListIteratorZ = companionList.towerList.listIterator()
            while (towerListIteratorZ.hasNext()) {
                var tower = towerListIteratorZ.next()
                if (it != tower) {
                    if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                        tower.xpTower += xpGain * 0.1f
                    }
                }
            }
    }

    fun towerExperience(towerId: Int, xpGain: Float){
        var xpGain2 = xpGain * companionList.towerList[towerId].xpMulti
        if (companionList.towerList[towerId].experienceMoonNight) xpGain2 *= 2
        companionList.towerList[towerId].xpTower += xpGain2
        towerExperienceGainUtilAura(xpGain2, towerId)
        if (companionList.towerList[towerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain2, towerId)
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------

    private fun addItemsToList() {

        companionList.itemListReserveNormal.forEach() {
            if (it.minLvl.toInt() == companionList.level) companionList.itemListNormal.add(it.id)
            if (it.maxLvl.toInt() == companionList.level) companionList.itemListNormal.remove(it.id)
        }
        companionList.itemListReserveRare.forEach() {
            if (it.minLvl.toInt() == companionList.level) companionList.itemListRare.add(it.id)
            if (it.maxLvl.toInt() == companionList.level) companionList.itemListRare.remove(it.id)
        }
        companionList.itemListReserveEpic.forEach() {
            if (it.minLvl.toInt() == companionList.level) companionList.itemListEpic.add(it.id)
            if (it.maxLvl.toInt() == companionList.level) companionList.itemListEpic.remove(it.id)
        }
        companionList.itemListReserveLegendary.forEach() {
            if (it.minLvl.toInt() == companionList.level) companionList.itemListLegendary.add(it.id)
            if (it.maxLvl.toInt() == companionList.level) companionList.itemListLegendary.remove(it.id)
        }

    }

}