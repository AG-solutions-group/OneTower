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
import androidx.core.view.setPadding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.ActiveAbility.Companion.aAid0
import com.agsolutions.td.ActiveAbility.Companion.aAid1
import com.agsolutions.td.ActiveAbility.Companion.aAid2
import com.agsolutions.td.ActiveAbility.Companion.aAid3
import com.agsolutions.td.Companion.Companion.activeAbilityHelpingHandActiveCounter
import com.agsolutions.td.Companion.Companion.activeAbilityHelpingHandBool
import com.agsolutions.td.Companion.Companion.activeAbilityHelpingHandButtonCounter
import com.agsolutions.td.Companion.Companion.activeAbilityList
import com.agsolutions.td.Companion.Companion.activeAbilityPlace
import com.agsolutions.td.Companion.Companion.allShieldsBool
import com.agsolutions.td.Companion.Companion.armorPenGain
import com.agsolutions.td.Companion.Companion.armorPenGainCount
import com.agsolutions.td.Companion.Companion.autoSpawn
import com.agsolutions.td.Companion.Companion.autoSpawnCount
import com.agsolutions.td.Companion.Companion.autoSpawnCountBool
import com.agsolutions.td.Companion.Companion.bagSizeGain
import com.agsolutions.td.Companion.Companion.bagSizeGainCount
import com.agsolutions.td.Companion.Companion.bigNumberScaler
import com.agsolutions.td.Companion.Companion.bombActiveAbility
import com.agsolutions.td.Companion.Companion.bombDamage
import com.agsolutions.td.Companion.Companion.bombsUsedCounter
import com.agsolutions.td.Companion.Companion.bossesKilled
import com.agsolutions.td.Companion.Companion.bossesLeaked
import com.agsolutions.td.Companion.Companion.build
import com.agsolutions.td.Companion.Companion.buildClickBool
import com.agsolutions.td.Companion.Companion.buildListBag
import com.agsolutions.td.Companion.Companion.butterflyTowerCount
import com.agsolutions.td.Companion.Companion.challengesKilled
import com.agsolutions.td.Companion.Companion.continueGame
import com.agsolutions.td.Companion.Companion.costBase
import com.agsolutions.td.Companion.Companion.costBlue
import com.agsolutions.td.Companion.Companion.costDia
import com.agsolutions.td.Companion.Companion.costEpic
import com.agsolutions.td.Companion.Companion.costLegendary
import com.agsolutions.td.Companion.Companion.critChanceGain
import com.agsolutions.td.Companion.Companion.critChanceGainCount
import com.agsolutions.td.Companion.Companion.critDamageGain
import com.agsolutions.td.Companion.Companion.critDamageGainCount
import com.agsolutions.td.Companion.Companion.damageGain
import com.agsolutions.td.Companion.Companion.damageGainCount
import com.agsolutions.td.Companion.Companion.darkTowerCount
import com.agsolutions.td.Companion.Companion.day
import com.agsolutions.td.Companion.Companion.dayNightCounter
import com.agsolutions.td.Companion.Companion.dayNightHour
import com.agsolutions.td.Companion.Companion.dayNightMinute
import com.agsolutions.td.Companion.Companion.dayNightVariable
import com.agsolutions.td.Companion.Companion.diamonds
import com.agsolutions.td.Companion.Companion.diamondsGain
import com.agsolutions.td.Companion.Companion.diamondsGainCount
import com.agsolutions.td.Companion.Companion.dmgDisplayList
import com.agsolutions.td.Companion.Companion.dragRect
import com.agsolutions.td.Companion.Companion.dragRectList
import com.agsolutions.td.Companion.Companion.dragStatusCantBuild
import com.agsolutions.td.Companion.Companion.dragStatusDrag
import com.agsolutions.td.Companion.Companion.dropItemDarkUltimate
import com.agsolutions.td.Companion.Companion.dropItemList
import com.agsolutions.td.Companion.Companion.earthTalentBonusPhysicalDmg
import com.agsolutions.td.Companion.Companion.earthTowerCount
import com.agsolutions.td.Companion.Companion.end
import com.agsolutions.td.Companion.Companion.endlessNight
import com.agsolutions.td.Companion.Companion.endlessNightActive
import com.agsolutions.td.Companion.Companion.endlessNightActiveCounter
import com.agsolutions.td.Companion.Companion.endlessNightBool
import com.agsolutions.td.Companion.Companion.endlessNightButtonCounter
import com.agsolutions.td.Companion.Companion.enemiesKilled
import com.agsolutions.td.Companion.Companion.enemyArmor
import com.agsolutions.td.Companion.Companion.enemyArmorReduction
import com.agsolutions.td.Companion.Companion.enemyClick
import com.agsolutions.td.Companion.Companion.enemyEvade
import com.agsolutions.td.Companion.Companion.enemyHp
import com.agsolutions.td.Companion.Companion.enemyHpReg
import com.agsolutions.td.Companion.Companion.enemyList
import com.agsolutions.td.Companion.Companion.enemyMagicArmor
import com.agsolutions.td.Companion.Companion.enemyMagicArmorReduction
import com.agsolutions.td.Companion.Companion.enemyManaShield
import com.agsolutions.td.Companion.Companion.enemyManaShieldMax
import com.agsolutions.td.Companion.Companion.enemyMaxHp
import com.agsolutions.td.Companion.Companion.enemySelectedBool
import com.agsolutions.td.Companion.Companion.enemyShield
import com.agsolutions.td.Companion.Companion.enemyShieldMax
import com.agsolutions.td.Companion.Companion.enemySizeBoss
import com.agsolutions.td.Companion.Companion.enemySpawned
import com.agsolutions.td.Companion.Companion.enemySpd
import com.agsolutions.td.Companion.Companion.enemyType
import com.agsolutions.td.Companion.Companion.evadeNight
import com.agsolutions.td.Companion.Companion.fireTowerCount
import com.agsolutions.td.Companion.Companion.fragmentItemCurrentItem
import com.agsolutions.td.Companion.Companion.globalArmorPen
import com.agsolutions.td.Companion.Companion.globalBonusItemChance
import com.agsolutions.td.Companion.Companion.globalBonusItemQuality
import com.agsolutions.td.Companion.Companion.globalBonusSpellDmgPercent
import com.agsolutions.td.Companion.Companion.globalBonusTowerRange
import com.agsolutions.td.Companion.Companion.globalDmgMultiplier
import com.agsolutions.td.Companion.Companion.globalItemListBag
import com.agsolutions.td.Companion.Companion.globalMagicPen
import com.agsolutions.td.Companion.Companion.globalSoundEffects
import com.agsolutions.td.Companion.Companion.globalSoundMusic
import com.agsolutions.td.Companion.Companion.gold
import com.agsolutions.td.Companion.Companion.goldGain
import com.agsolutions.td.Companion.Companion.goldGainCount
import com.agsolutions.td.Companion.Companion.healCounter
import com.agsolutions.td.Companion.Companion.highscore
import com.agsolutions.td.Companion.Companion.hintsBool
import com.agsolutions.td.Companion.Companion.hitGain
import com.agsolutions.td.Companion.Companion.hitGainCount
import com.agsolutions.td.Companion.Companion.hpRegDebuffGlobal
import com.agsolutions.td.Companion.Companion.iceNovaTimerStartItem
import com.agsolutions.td.Companion.Companion.iceShardCounter
import com.agsolutions.td.Companion.Companion.iceShardCounter2
import com.agsolutions.td.Companion.Companion.iceShardCounter3
import com.agsolutions.td.Companion.Companion.iceShardCounter4
import com.agsolutions.td.Companion.Companion.iceShardSpeed
import com.agsolutions.td.Companion.Companion.iceShardTowerId
import com.agsolutions.td.Companion.Companion.iceTowerCount
import com.agsolutions.td.Companion.Companion.insertSpell
import com.agsolutions.td.Companion.Companion.insertSpellBomb
import com.agsolutions.td.Companion.Companion.instaKill
import com.agsolutions.td.Companion.Companion.interest
import com.agsolutions.td.Companion.Companion.interestGain
import com.agsolutions.td.Companion.Companion.interestGainCount
import com.agsolutions.td.Companion.Companion.invalidate
import com.agsolutions.td.Companion.Companion.itemBoring
import com.agsolutions.td.Companion.Companion.itemChance
import com.agsolutions.td.Companion.Companion.itemChanceGain
import com.agsolutions.td.Companion.Companion.itemChanceGainCount
import com.agsolutions.td.Companion.Companion.itemFastDraw
import com.agsolutions.td.Companion.Companion.itemFragmentEnemyList
import com.agsolutions.td.Companion.Companion.itemLassoCount
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.itemListBagInserter
import com.agsolutions.td.Companion.Companion.itemListBagMysteryEventCancelled
import com.agsolutions.td.Companion.Companion.itemListInserter
import com.agsolutions.td.Companion.Companion.itemPick
import com.agsolutions.td.Companion.Companion.itemPoints
import com.agsolutions.td.Companion.Companion.itemPointsGain
import com.agsolutions.td.Companion.Companion.itemPointsGainCount
import com.agsolutions.td.Companion.Companion.itemQualityGain
import com.agsolutions.td.Companion.Companion.itemQualityGainCount
import com.agsolutions.td.Companion.Companion.itemSniperPro
import com.agsolutions.td.Companion.Companion.itemUsed
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.levelCount
import com.agsolutions.td.Companion.Companion.levelCountBool
import com.agsolutions.td.Companion.Companion.levelCountPlace
import com.agsolutions.td.Companion.Companion.levelCountSecondBool
import com.agsolutions.td.Companion.Companion.levelList
import com.agsolutions.td.Companion.Companion.levelListReserve
import com.agsolutions.td.Companion.Companion.levelScalerCrit
import com.agsolutions.td.Companion.Companion.levelScalerCritDmg
import com.agsolutions.td.Companion.Companion.levelScalerItemChance
import com.agsolutions.td.Companion.Companion.levelScalerItemQuality
import com.agsolutions.td.Companion.Companion.levelScalerSpeed
import com.agsolutions.td.Companion.Companion.levelStatus
import com.agsolutions.td.Companion.Companion.lives
import com.agsolutions.td.Companion.Companion.livesMode2
import com.agsolutions.td.Companion.Companion.logVolume
import com.agsolutions.td.Companion.Companion.logVolumeEffects
import com.agsolutions.td.Companion.Companion.lvlArmor
import com.agsolutions.td.Companion.Companion.lvlEvade
import com.agsolutions.td.Companion.Companion.lvlHp
import com.agsolutions.td.Companion.Companion.lvlHpReg
import com.agsolutions.td.Companion.Companion.lvlMagicArmor
import com.agsolutions.td.Companion.Companion.lvlScaler
import com.agsolutions.td.Companion.Companion.lvlScalerFirst
import com.agsolutions.td.Companion.Companion.lvlScalerSecond
import com.agsolutions.td.Companion.Companion.lvlSpd
import com.agsolutions.td.Companion.Companion.lvlXp
import com.agsolutions.td.Companion.Companion.lvlXpConstant
import com.agsolutions.td.Companion.Companion.magicArmorPenGain
import com.agsolutions.td.Companion.Companion.magicArmorPenGainCount
import com.agsolutions.td.Companion.Companion.manaShieldBool
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.mapPick
import com.agsolutions.td.Companion.Companion.maxVolume
import com.agsolutions.td.Companion.Companion.mgcDamageGain
import com.agsolutions.td.Companion.Companion.mgcDamageGainCount
import com.agsolutions.td.Companion.Companion.midnightMadnessDisableItems
import com.agsolutions.td.Companion.Companion.midnightMadnessEvent
import com.agsolutions.td.Companion.Companion.midnightMadnessEventRadio
import com.agsolutions.td.Companion.Companion.midnightMadnessExtraSpawn
import com.agsolutions.td.Companion.Companion.midnightMadnessExtraSpawnBool
import com.agsolutions.td.Companion.Companion.midnightMadnessMidas
import com.agsolutions.td.Companion.Companion.midnightMadnessMidasGold
import com.agsolutions.td.Companion.Companion.midnightMadnessMidasGoldCost
import com.agsolutions.td.Companion.Companion.midnightMadnessSaveList
import com.agsolutions.td.Companion.Companion.midnightMadnessWind
import com.agsolutions.td.Companion.Companion.midnightMadnessWindLvlSpeedReduce
import com.agsolutions.td.Companion.Companion.minePlaceholderCounter
import com.agsolutions.td.Companion.Companion.moonTowerCount
import com.agsolutions.td.Companion.Companion.multiCritGain
import com.agsolutions.td.Companion.Companion.multiCritGainCount
import com.agsolutions.td.Companion.Companion.overallSpdMultiplier
import com.agsolutions.td.Companion.Companion.overallXp
import com.agsolutions.td.Companion.Companion.personalHighscore
import com.agsolutions.td.Companion.Companion.phyDamageGain
import com.agsolutions.td.Companion.Companion.phyDamageGainCount
import com.agsolutions.td.Companion.Companion.poisonCloud
import com.agsolutions.td.Companion.Companion.poisonCloudCounter
import com.agsolutions.td.Companion.Companion.poisonCloudPlaceholderCounter
import com.agsolutions.td.Companion.Companion.poisonPestCount
import com.agsolutions.td.Companion.Companion.poisonTalentPest
import com.agsolutions.td.Companion.Companion.poisonTowerCount
import com.agsolutions.td.Companion.Companion.poisonTowerHighestDmg
import com.agsolutions.td.Companion.Companion.radioAdd
import com.agsolutions.td.Companion.Companion.radioList
import com.agsolutions.td.Companion.Companion.readLockEnemy
import com.agsolutions.td.Companion.Companion.readLockIce
import com.agsolutions.td.Companion.Companion.readLockMine
import com.agsolutions.td.Companion.Companion.readLockPoison
import com.agsolutions.td.Companion.Companion.readLockTornado
import com.agsolutions.td.Companion.Companion.readLockTower
import com.agsolutions.td.Companion.Companion.refresh
import com.agsolutions.td.Companion.Companion.rotationBulletX
import com.agsolutions.td.Companion.Companion.rotationBulletY
import com.agsolutions.td.Companion.Companion.scaleScreen
import com.agsolutions.td.Companion.Companion.scaleTextBig
import com.agsolutions.td.Companion.Companion.scaleTextButton
import com.agsolutions.td.Companion.Companion.scaleTextMain
import com.agsolutions.td.Companion.Companion.scaleTextNews
import com.agsolutions.td.Companion.Companion.scaleTextStats
import com.agsolutions.td.Companion.Companion.scaler
import com.agsolutions.td.Companion.Companion.screenDensity
import com.agsolutions.td.Companion.Companion.screenDensityHeight
import com.agsolutions.td.Companion.Companion.screenDensityWidth
import com.agsolutions.td.Companion.Companion.secretShopBool
import com.agsolutions.td.Companion.Companion.shieldBool
import com.agsolutions.td.Companion.Companion.shootButterflyReserve
import com.agsolutions.td.Companion.Companion.shootList
import com.agsolutions.td.Companion.Companion.shootListIce
import com.agsolutions.td.Companion.Companion.shootListMine
import com.agsolutions.td.Companion.Companion.shootListPoison
import com.agsolutions.td.Companion.Companion.shootListTornado
import com.agsolutions.td.Companion.Companion.shootReserve
import com.agsolutions.td.Companion.Companion.soundBoolDay
import com.agsolutions.td.Companion.Companion.soundBoolNight
import com.agsolutions.td.Companion.Companion.spawnEnemy
import com.agsolutions.td.Companion.Companion.spdGain
import com.agsolutions.td.Companion.Companion.spdGainCount
import com.agsolutions.td.Companion.Companion.tankBool
import com.agsolutions.td.Companion.Companion.timer
import com.agsolutions.td.Companion.Companion.toastGlobal
import com.agsolutions.td.Companion.Companion.toastText
import com.agsolutions.td.Companion.Companion.tornadoPlaceholderCounter
import com.agsolutions.td.Companion.Companion.towerClick
import com.agsolutions.td.Companion.Companion.towerClickBool
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerCount
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Companion.Companion.tutorialFirstUseItemBool
import com.agsolutions.td.Companion.Companion.tutorialFirstUseItemCounter
import com.agsolutions.td.Companion.Companion.uiRefreshCounter
import com.agsolutions.td.Companion.Companion.upgradePointsGain
import com.agsolutions.td.Companion.Companion.upgradePointsGainCount
import com.agsolutions.td.Companion.Companion.userLevel
import com.agsolutions.td.Companion.Companion.utilsTowerCount
import com.agsolutions.td.Companion.Companion.windTowerCount
import com.agsolutions.td.Companion.Companion.wizardBombStartItemDmg
import com.agsolutions.td.Companion.Companion.wizardLightningStartItemTargets
import com.agsolutions.td.Companion.Companion.wizardMine
import com.agsolutions.td.Companion.Companion.wizardMineCounter
import com.agsolutions.td.Companion.Companion.wizardMinePlaced
import com.agsolutions.td.Companion.Companion.wizardTowerCount
import com.agsolutions.td.Companion.Companion.wizardTowerHighestDmg
import com.agsolutions.td.Companion.Companion.writeLockDisplay
import com.agsolutions.td.Companion.Companion.writeLockEnemy
import com.agsolutions.td.Companion.Companion.writeLockIce
import com.agsolutions.td.Companion.Companion.writeLockMine
import com.agsolutions.td.Companion.Companion.writeLockPoison
import com.agsolutions.td.Companion.Companion.writeLockShot
import com.agsolutions.td.Companion.Companion.writeLockTornado
import com.agsolutions.td.Companion.Companion.writeLockTower
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
import com.agsolutions.td.Items.Companion.blueItems
import com.agsolutions.td.Items.Companion.ebutterfly
import com.agsolutions.td.Items.Companion.edark
import com.agsolutions.td.Items.Companion.eearth
import com.agsolutions.td.Items.Companion.efire
import com.agsolutions.td.Items.Companion.eice
import com.agsolutions.td.Items.Companion.emoon
import com.agsolutions.td.Items.Companion.epoison
import com.agsolutions.td.Items.Companion.eutils
import com.agsolutions.td.Items.Companion.ewind
import com.agsolutions.td.Items.Companion.ewizard
import com.agsolutions.td.Items.Companion.greyItems
import com.agsolutions.td.Items.Companion.itemListEpic
import com.agsolutions.td.Items.Companion.itemListLegendary
import com.agsolutions.td.Items.Companion.itemListNormal
import com.agsolutions.td.Items.Companion.itemListRare
import com.agsolutions.td.Items.Companion.itemListReserveEpic
import com.agsolutions.td.Items.Companion.itemListReserveLegendary
import com.agsolutions.td.Items.Companion.itemListReserveNormal
import com.agsolutions.td.Items.Companion.itemListReserveRare
import com.agsolutions.td.Items.Companion.orangeItems
import com.agsolutions.td.Items.Companion.purpleItems
import com.agsolutions.td.LogIn.HttpTask
import com.agsolutions.td.Main.MainActivity
import com.agsolutions.td.Main.StartItemsMenu
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
    companion object PlayPause {
        var paused = false
        var gameEnd = 1
    }

    private val adapter = ItemAdapter(itemList, this)
    var updateViewModel: UpdateViewModel = UpdateViewModel()
    var bagAdapter = ItemBagAdapter(itemListBagInserter, this, this)
    private val activeAbilityAdapter = ActiveAbilityAdapter(activeAbilityList, this)
    private val radioAdapter = RadioAdapter(radioList)
    var mHandler = Handler()
    var itemTouchHelper = ItemTouchHelper(SwipeItem(adapter))
    var itemTouchHelper2 = ItemTouchHelper(SwipeItemBag(bagAdapter))
    private val fragmentStats = StatsFragment(updateViewModel)
    private val fragmentStatsTower = StatsTowerFragment(updateViewModel)
    private val fragmentStatsEnemy = StatsEnemyFragment(updateViewModel)
    private val fragmentItem = ItemFragment()
    private val fragmentUpgradeItem = ItemUpgradeFragment()
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
        recycler()
        initialize()
        update()
        fragments()

        ActivityThread.runningActivity = true
        activityThread.start()

        // show start item screen at start
        if (continueGame) {
            loadGame()
        } else {
                if (mapMode == 2) lvlHp * 1.3f
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, TutorialStart::class.java)
                    startActivity(intent)
                }, 1000)
        }
    }

    //----------------------------------------------------------------------------

    override fun onPause() {
        super.onPause()
       activityThread.interrupt()

    }

    override fun onResume() {
        super.onResume()
        ActivityThread.runningActivity = true
        globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
        globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)
    }

     override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            globalSoundMusic = 0f
            globalSoundEffects = 0f
        }else {
            globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
            globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)
        }

    }


    //----------------------------------------------------------------------------

    override fun onBackPressed() {
    }

    //----------------------------------------------------------------------------

    fun loadBasics () {

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)
        userLevel = sharedPref!!.getInt("userLevel", 0)

        mapPick = intent.getIntExtra("pickMap", 0)
        mapMode = intent.getIntExtra("pickMode", 1)
        itemPick = intent.getIntExtra("pickMap", 0)
        continueGame = intent.getBooleanExtra("LoadGame", false)
        var usernameX = sharedPref!!.getString("username", "user")

        if (isOnline(this)) {
            HttpTask {
                // progressMainBar.visibility = View.INVISIBLE
                if (it == null) {
                    println("connection error")
                    return@HttpTask
                }
                println(it)
                val jsonRes = JSONObject(it)
                var startItemHiddenListRemove = mutableListOf<Items>()
                if (jsonRes.getString("status") == "true") {
                    var jsonArray = JSONArray(jsonRes.getString("data"))
                    for (i in 0 until jsonArray.length()) {
                        var itemlist = jsonArray.getJSONObject(i)
                        var itemid = itemlist.getInt("itemid")
                        Items.startItemHiddenList.forEach() {
                            if (itemid == it.id) {
                                Items.startItemList.add(it)
                                startItemHiddenListRemove.add(it)
                            }
                        }
                    }
                    Items.startItemHiddenList.removeAll(StartItemsMenu.startItemHiddenListRemove)
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
            var startItemHiddenListRemove = mutableListOf<Items>()
            for (itemid in itemListPlace) {
                Items.startItemHiddenList.forEach() {
                    if (itemid == it.id) {
                        Items.startItemList.add(it)
                        startItemHiddenListRemove.add(it)
                    }
                }
            }
            Items.startItemHiddenList.removeAll(StartItemsMenu.startItemHiddenListRemove)
        }

       if (isOnline(this)) {

           if (mapMode == 1) {

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
                               highscore = highscoreX

                               break
                           }
                       }else highscore = 0

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
                               Companion.personalHighscore = highscoreY

                               break
                           }
                       }else personalHighscore = 0
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
                               highscore = highscoreX

                               break
                           }
                       }else highscore = 0
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
                               Companion.personalHighscore = highscoreY

                               break
                           }
                       }else personalHighscore = 0
                       Log.d("userdata Data:::::::", "worked")

                   } else {
                       Log.d("post Data:::::::", jsonRes.getString("message"))
                   }
               }.execute("GET", "http://s100019391.ngcobalt394.manitu.net/ag-solutions-group.com/get_user_highscore_map_12.php?username=" + (usernameX))

           }
        } else {
            if (mapMode == 1) {
                highscore = 0
                personalHighscore = sharedPref!!.getInt("GetHighscoreMap11", 0)
            } else {
                highscore = 0
                personalHighscore = sharedPref!!.getInt("GetHighscoreMap12", 0)
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

    fun loadGame (){

        /*

        //   var editor = sharedPref!!.edit()
       // editor.putBoolean("continueGame", false)
       // editor.apply()

        progressGameBar.visibility = View.VISIBLE

        levelCount = 720

        var fis: InputStream? = null
        var ois: InputStream? = null

        try {
            var textFile = File("$filesDir/saveGame.dat")
            fis = FileInputStream(textFile)
            ois = ObjectInputStream(fis)
            var itemListPlace = ois.readObject() as ArrayList<Arrays>
            var bombActive = 0

            var mysteryPointsList = itemListPlace[0] as ArrayList<Boolean>
            if (mysteryPointsList != null) {
                com.agsolutions.td.Companion.mysteryAllRounderBool = mysteryPointsList[0]
                com.agsolutions.td.Companion.mysterySongBool = mysteryPointsList[1]
                com.agsolutions.td.Companion.mysteryClownBool = mysteryPointsList[2]
                com.agsolutions.td.Companion.mysteryMaceBool = mysteryPointsList[3]
                com.agsolutions.td.Companion.mysteryBowBool = mysteryPointsList[4]
                com.agsolutions.td.Companion.mysterySwordBool = mysteryPointsList[5]
                com.agsolutions.td.Companion.mysteryLuckyCharmBool = mysteryPointsList[6]
                com.agsolutions.td.Companion.mysteryPirateHunterBool = mysteryPointsList[7]
                com.agsolutions.td.Companion.mysteryBombsUsedBool = mysteryPointsList[8]
                com.agsolutions.td.Companion.mysteryColdHeartBool = mysteryPointsList[9]
                com.agsolutions.td.Companion.challengesKilledBool = mysteryPointsList[10]
            }

            var itemListX = itemListPlace!![1] as ArrayList<Items>
            var stringList = itemListPlace[2] as ArrayList<String>
            var stringListReserve = itemListPlace[3] as ArrayList<String>

                      var gameDataList = itemListPlace[4] as ArrayList<Int>

                      if (gameDataList != null) {
                          level = gameDataList[0]
                          if (mapMode == 2) livesMode2 = gameDataList[1]
                          else lives = gameDataList[1]
                          dayNightHour = gameDataList[2]
                          dayNightMinute = gameDataList[3]
                      }

                   var gameCurrencyList = itemListPlace[5] as ArrayList<Int>
                  if (gameCurrencyList != null) {
                   gold = gameCurrencyList[0].toFloat()
                   diamonds = gameCurrencyList[1]
                  itemPoints = gameCurrencyList[2].toFloat()
                  mysteryPoints = gameCurrencyList[3]
                      upgradePoints = gameCurrencyList[4]
                      bombActive = gameCurrencyList[5]
                      chainLightningBonusDmg = gameCurrencyList[6].toFloat()
                      levelCount = gameCurrencyList[7]
                      enemySpawned = gameCurrencyList[8]
                      timer = gameCurrencyList[9]
                   }

                     var gameLevelInfoList = itemListPlace[6] as ArrayList<Float>
                      if (gameLevelInfoList != null) {
                     lvlHp = gameLevelInfoList[0]
                     lvlArmor = gameLevelInfoList[1]
                     lvlMagicArmor = gameLevelInfoList[2]
                     lvlEvade = gameLevelInfoList[3]
                     lvlXp = gameLevelInfoList[4]
                          bigNumberScaler = gameLevelInfoList[5]
                          levelScalerCrit = gameLevelInfoList[6]
                          levelScalerSpeed = gameLevelInfoList[7]
                          levelScalerCritDmg = gameLevelInfoList[8]
                          levelScalerItemChance = gameLevelInfoList[9]
                          lvlXpConstant = gameLevelInfoList[10]
                          lvlHpReg = gameLevelInfoList[11]
                          lvlSpd = gameLevelInfoList[12]
                          levelScalerItemQuality = gameLevelInfoList[13]
                     }

                      var gameListItems = itemListPlace[7] as ArrayList<Arrays>
                      if (gameListItems != null) {
                          var gameListItemsNormal = gameListItems[0] as ArrayList<Int>
                          itemListNormal.removeAll(itemListNormal)
                          gameListItemsNormal.forEach() {
                              itemListNormal.add(it)
                          }

                          var gameListItemsRare = gameListItems[1] as ArrayList<Int>
                          itemListRare.removeAll(itemListRare)
                          gameListItemsRare.forEach() {
                              itemListRare.add(it)
                          }
                          var gameListItemsEpic = gameListItems[2] as ArrayList<Int>
                          itemListEpic.removeAll(itemListEpic)
                          gameListItemsEpic.forEach() {
                              itemListEpic.add(it)
                          }
                          var gameListItemsLegendary = gameListItems[3] as ArrayList<Int>
                          itemListLegendary.removeAll(itemListLegendary)
                          gameListItemsLegendary.forEach() {
                              itemListLegendary.add(it)
                          }
                      }

                     var gameListTalents = itemListPlace[8] as ArrayList<Arrays>

                     var gameTalentButterfly = gameListTalents[0] as ArrayList<Int>
                     var gameTalentDark = gameListTalents[1] as ArrayList<Int>
                     var gameTalentEarth = gameListTalents[2] as ArrayList<Int>
                     var gameTalentFire = gameListTalents[3] as ArrayList<Int>
                     var gameTalentIce = gameListTalents[4] as ArrayList<Int>
                     var gameTalentMoon = gameListTalents[5] as ArrayList<Int>
                     var gameTalentPoison = gameListTalents[6] as ArrayList<Int>
                     var gameTalentUtils = gameListTalents[7] as ArrayList<Int>
                     var gameTalentWind = gameListTalents[8] as ArrayList<Int>
                     var gameTalentWizard = gameListTalents[9] as ArrayList<Int>


                     ButterflyTalentFragment.butterflyRow1Item1 += gameTalentButterfly[0]
                     ButterflyTalentFragment.butterflyRow2Item1 += gameTalentButterfly[1]
                     ButterflyTalentFragment.butterflyRow2Item2 += gameTalentButterfly[2]
                     ButterflyTalentFragment.butterflyRow3Item1 += gameTalentButterfly[3]
                     ButterflyTalentFragment.butterflyRow4Item1 += gameTalentButterfly[4]
                     butterflyTalentsLoad()

                     DarkTalentFragment.darkRow1Item1 += gameTalentDark[0]
                     DarkTalentFragment.darkRow2Item1 += gameTalentDark[1]
                     DarkTalentFragment.darkRow2Item2 += gameTalentDark[2]
                     DarkTalentFragment.darkRow3Item1 += gameTalentDark[3]
                     DarkTalentFragment.darkRow3Item2 += gameTalentDark[4]
                     DarkTalentFragment.darkRow4Item1 += gameTalentDark[5]
                     darkTalentsLoad()

                     EarthTalentFragment.earthRow1Item1 += gameTalentEarth[0]
                     EarthTalentFragment.earthRow2Item1 += gameTalentEarth[1]
                     EarthTalentFragment.earthRow2Item2 += gameTalentEarth[2]
                     EarthTalentFragment.earthRow3Item1 += gameTalentEarth[3]
                     EarthTalentFragment.earthRow4Item1 += gameTalentEarth[4]
                     earthTalentsLoad()

                     FireTalentFragment.fireRow1Item1 += gameTalentFire[0]
                     FireTalentFragment.fireRow2Item1 += gameTalentFire[1]
                     FireTalentFragment.fireRow2Item2 += gameTalentFire[2]
                     FireTalentFragment.fireRow3Item1 += gameTalentFire[3]
                     FireTalentFragment.fireRow3Item2 += gameTalentFire[4]
                     FireTalentFragment.fireRow4Item1 += gameTalentFire[5]
                     fireTalentsLoad()

                     IceTalentFragment.iceRow1Item1 += gameTalentIce[0]
                     IceTalentFragment.iceRow2Item1 += gameTalentIce[1]
                     IceTalentFragment.iceRow2Item2 += gameTalentIce[2]
                     IceTalentFragment.iceRow3Item1 += gameTalentIce[3]
                     IceTalentFragment.iceRow3Item2 += gameTalentIce[4]
                     IceTalentFragment.iceRow4Item1 += gameTalentIce[5]
                     iceTalentsLoad()

                     MoonTalentFragment.moonRow1Item1 += gameTalentMoon[0]
                     MoonTalentFragment.moonRow2Item1 += gameTalentMoon[1]
                     MoonTalentFragment.moonRow2Item2 += gameTalentMoon[2]
                     MoonTalentFragment.moonRow3Item1 += gameTalentMoon[3]
                     MoonTalentFragment.moonRow4Item1 += gameTalentMoon[4]
                     moonTalentsLoad()

                     PoisonTalentFragment.poisonRow1Item1 += gameTalentPoison[0]
                     PoisonTalentFragment.poisonRow2Item1 += gameTalentPoison[1]
                     PoisonTalentFragment.poisonRow2Item2 += gameTalentPoison[2]
                     PoisonTalentFragment.poisonRow3Item1 += gameTalentPoison[3]
                     PoisonTalentFragment.poisonRow3Item2 += gameTalentPoison[4]
                     PoisonTalentFragment.poisonRow4Item1 += gameTalentPoison[5]
                     poisonTalentsLoad()

                     UtilsTalentFragment.utilsRow1Item1 += gameTalentUtils[0]
                     UtilsTalentFragment.utilsRow1Item2 += gameTalentUtils[1]
                     UtilsTalentFragment.utilsRow2Item1 += gameTalentUtils[2]
                     UtilsTalentFragment.utilsRow2Item2 += gameTalentUtils[3]
                     UtilsTalentFragment.utilsRow3Item1 += gameTalentUtils[4]
                     utilsTalentsLoad()

                     WindTalentFragment.windRow1Item1 += gameTalentWind[0]
                     WindTalentFragment.windRow2Item1 += gameTalentWind[1]
                     WindTalentFragment.windRow2Item2 += gameTalentWind[2]
                     WindTalentFragment.windRow3Item1 += gameTalentWind[3]
                     WindTalentFragment.windRow3Item2 += gameTalentWind[4]
                     WindTalentFragment.windRow4Item1 += gameTalentWind[5]
                     windTalentsLoad()

                     WizardTalentFragment.wizardRow1Item1 += gameTalentWizard[0]
                     WizardTalentFragment.wizardRow2Item1 += gameTalentWizard[1]
                     WizardTalentFragment.wizardRow2Item2 += gameTalentWizard[2]
                     WizardTalentFragment.wizardRow3Item1 += gameTalentWizard[3]
                     WizardTalentFragment.wizardRow3Item2 += gameTalentWizard[4]
                     WizardTalentFragment.wizardRow4Item1 += gameTalentWizard[5]
                     wizardTalentsLoad()

            var itemListMysteryEventList = itemListPlace!![9] as ArrayList<String>
            var itemListMysteryEvent = itemListPlace!![10] as ArrayList<Items>

            midnightMadnessSaveList.addAll(itemListMysteryEventList)
            if (midnightMadnessSaveList.contains("Midas")) {
                midnightMadnessMidas = true
                midnightMadnessMidasGold += 0.2f
                midnightMadnessMidasGoldCost += 0.2f
            } else if (midnightMadnessSaveList.contains("Darkest Hour")) {
                    midnightMadnessDisableItems = true
                    itemListBagMysteryEventCancelled.addAll(itemListMysteryEvent)
            } else if (midnightMadnessSaveList.contains("Winds")) {
                midnightMadnessWind = true
                overallSpdMultiplier -= 20f
                midnightMadnessWindLvlSpeedReduce = lvlSpd / 0.2f
            } else if (midnightMadnessSaveList.contains("Extra")) {
                midnightMadnessExtraSpawnBool = true
                midnightMadnessExtraSpawn += 1
            }

            var a = 0

            writeLockTower.lock()
            try {
                var towerListIterator = Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()

                    itemListX.forEach() {
                        tower.bonusTowerSpeed += it.speed
                        tower.bonusTowerDmg += it.dmg
                        tower.bonusPhysicalDmg += it.atkDmg
                        tower.bonusSpellDamage += it.mgcDmg
                        tower.bonusCrit += it.crit
                        tower.bonusCritDmg += it.critDmg

                        if (it.id == 0 || it.id == 100 || it.id == 200 || it.id == 300) maceCount += 1
                        if (it.id == 1 || it.id == 102 || it.id == 202 || it.id == 302) bowCount += 1
                        if (it.id == 2 || it.id == 103 || it.id == 203 || it.id == 303) swordCount += 1
                        if (it.id == 3 || it.id == 101 || it.id == 201 || it.id == 301) magicBoxCount += 1
                        if (it.id == 4 || it.id == 104 || it.id == 204) luckyCharmCount += 1
                        if (it.id == 4) tower.bonusItemChance += it.specialFloat2


                        if ((it.id == 100 || it.id == 102 || it.id == 103) && it.special2 == "item find") tower.bonusItemChance += it.specialFloat2
                        if ((it.id == 100 || it.id == 102 || it.id == 103) && it.special2 == "item quality") tower.bonusItemQuality += (it.specialFloat2).toInt()
                        if (it.id == 104 || it.id == 204) tower.bonusItemQuality += it.specialFloat2
                        if (it.id == 105) itemPiggyBank += 0.1f
                        if (it.id == 106) {
                            if (mapMode != 2) lives += 1
                            else livesMode2 += 1
                        }
                        if (it.id == 107) talentPoints += 1

                        if ((it.id == 200 || it.id == 202 || it.id == 203) && it.special == "armor penetration") tower.bonusArmorPen += it.specialFloat
                        if ((it.id == 200 || it.id == 202 || it.id == 203) && it.special == "magic penetration") tower.bonusMagicPen += it.specialFloat
                        if ((it.id == 200 || it.id == 202 || it.id == 203) && it.special == "hit chance") tower.itemBonusHitChance += it.specialFloat
                        if (it.id == 208 || it.id == 308) tower.itemFrost += it.specialFloat
                        if (it.id == 209) interest += it.specialFloat
                        if (it.id == 210 || it.id == 309) tower.itemLasso += it.specialFloat.toInt()
                        if (it.id == 212) tower.itemBonusHitChance += it.specialFloat.toInt()
                        if (it.id == 213) tower.itemLastStance += 0.1f
                        if (it.id == 214) tower.itemSniper += 1
                        if (it.id == 215) tower.bonusArmorPen += it.specialFloat.toInt()
                        if (it.id == 216) tower.bonusMagicPen += it.specialFloat.toInt()
                        if (it.id == 217) Companion.slowEach += 10.0f

                        if ((it.id == 300 || it.id == 302 || it.id == 303) && it.special == "anti-heal") antiHeal += it.specialFloat / 100
                        if ((it.id == 300 || it.id == 302 || it.id == 303) && it.special == "extra dmg immune") dmgImmune += it.specialFloat / 100
                        if ((it.id == 300 || it.id == 302 || it.id == 303) && it.special2 == "item find") tower.bonusItemChance += it.specialFloat2
                        if ((it.id == 300 || it.id == 302 || it.id == 303) && it.special2 == "item quality") tower.bonusItemQuality += (it.specialFloat2).toInt()
                        if (it.id == 304) Companion.slowAura += it.specialFloat
                        if (it.id == 305) tower.itemDisruptor += it.specialFloat
                        if (it.id == 206) talentPoints += it.specialFloat.toInt()
                //TODO        if (it.id == 307 && !multishot && splashRange == 0f) {
                //TODO            Companion.shotBounceTargets += it.specialFloat.toInt()
                // TODO            shotBounce = true
                // TODO        }
                        if (it.id == 310) globalMultiCrit += it.specialFloat.toInt()
                        if (it.id == 312) tower.itemSlowDeath += it.specialFloat
                        if (it.id == 313) {
                            livesMpCounter = true
                            if (mapMode != 2) lives += 5
                            else livesMode2 += 5
                        }
                        if (it.id == 314) tower.armorPenPerHit += it.specialFloat
                        if (it.id == 315) tower.magicPenPerHit += it.specialFloat

                        if (it.id == 1004) particleDmgBool = true
                        if (it.id == 1005) {
                            tower.itemSniper += 1
                            itemSniperPro = true
                        }
                        if (it.id == 1006) itemFastDraw = true
                        if (it.id == 1007) {
                            activeAbilityList.add(0, ActiveAbility.aAid2)
                            Companion.insertSpell += 1
                        }
                        if (it.id == 1008) {
                            tower.bonusDamageMultiplyer += 0.2f
                            Companion.overallSpdMultiplier += 20
                            itemBoring = true
                        }

                        if (it.id == 1100 || it.id == 1101 || it.id == 1102 || it.id == 1103 || it.id == 1104 || it.id == 1105) pirateItemCount += 1
                        if (it.id == 1100) {
                            tower.towerBonusRange += 15
                        }
                        if (it.id == 1104) {
                            tower.bonusDamageMultiplyer += 0.2f
                            Companion.overallSpdMultiplier -= 10
                        }
                        if (it.id == 1105) {
                            tower.bonusDamageMultiplyer -= 0.1f
                            Companion.overallSpdMultiplier += 20
                        }

                        if (it.id == 5005) globalMultiCrit += 1
                        if (it.id == 5003) tower.bonusDamageMultiplyer += 0.1f
                        if (it.id == 5004) tower.overallSpellDmgMultiplyer += 0.1f
                        if (it.id == 5006) {
                            slowAura += 10f
                            tower.bonusItemChance += 10f
                        }
                        if (it.id == 5007) {
                            interest += 0.01f
                            tower.bonusItemChance += 10f
                        }
                        if (it.id == 5008) Companion.shieldBrakerItem = 0
                        if (it.id == 5009) {
                            itemStartPoison = true
                        }
                        if (it.id == 5011) {
                            activeAbilityList.add(0, ActiveAbility.aAid3)
                            Companion.insertSpell += 1
                        }
                        if (it.id == 5012) {
                            if (mapMode == 2) bombDamage += 0.025f
                            else bombDamage += 0.1f
                            Companion.bombCost = true
                        }
                        if (it.id == 5013) {
                            Companion.itemStartTalentPoints = true
                        }
                        if (it.id == 5015) {
                            wizardBombStartItemDmg += 0.4f
                            wizardLightningStartItemTargets += 1
                        }
                        if (it.id == 5016) {
                            Companion.itemStartBounce = true
                            MoonTalentFragment.moonRow2Item2 += 1

                            if (MoonTalentFragment.moonRow2Item2 == 1) {
                                if (Companion.itemStartBounce) Companion.shotBounceTargets += 2
                                else Companion.shotBounceTargets += 1
                            }
                            if (MoonTalentFragment.moonRow2Item2 == 2) {
                                if (Companion.itemStartBounce) Companion.shotBounceTargets += 4
                                else Companion.shotBounceTargets += 2
                            }
                            if (MoonTalentFragment.moonRow2Item2 == 3) {
                                if (Companion.itemStartBounce) Companion.shotBounceTargets += 6
                                else Companion.shotBounceTargets += 3
                            }
                        }
                        if (it.id == 5017) Companion.upgraderBool = true

                        if (it.id == 6666) {
                            tower.bonusTowerDmg += it.specialFloat
                        }

                        tower.itemListBag.add(a, it)
                        a++
                    }
                }
                   }finally {
                        writeLockTower.unlock()
                   }

            if (bombActive > 0) {
                if (activeAbilityList.contains(aAid1)) {
                    bombActiveAbility = bombActive
                    Companion.insertSpellBomb += 1
                } else {
                    activeAbilityList.add(0, aAid1)
                    Companion.insertSpell += 1
                    Companion.insertSpellBomb += 1
                    bombActiveAbility = bombActive
                }
            }

            enemyList = itemListPlace!![11] as ArrayList<Enemy>

            bagAdapter.notifyDataSetChanged()
            recyclerBagItem.smoothScrollToPosition(0)

            levelList.removeAll(levelList)
            stringList.forEach {
                levelList.add(it)
            }
            levelListReserve.removeAll(levelListReserve)
            stringListReserve.forEach(){
                levelListReserve.add(it)
            }

           // TODO randomEnemyForShotBool = true
           // TODO Companion.randomEnemyForShotSticky = true
            paused = true

        } catch (e: IOException) {
        } finally {
            if (fis != null) {
                fis.close()
                ois!!.close()
            }
        }

        progressGameBar.visibility = View.INVISIBLE

         */
    }

    //----------------------------------------------------------------------------

    private fun sound() {
        globalSoundMusic = sharedPref!!.getFloat("Global Music", 30f)
        globalSoundEffects = sharedPref!!.getFloat("Global Effects", 30f)

        soundIds[0] = soundPool.load(baseContext, R.raw.shoot, 1)
        soundIds[1] = soundPool.load(baseContext, R.raw.explosionsoundleise, 1)
        soundIds[2] = soundPool.load(baseContext, R.raw.leak, 1)
        logVolume = (1f-(log10(maxVolume - globalSoundMusic) / log10(maxVolume)))
        logVolumeEffects = (1f-(log10(maxVolume - globalSoundEffects) / log10(maxVolume)))

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

        screenDensity = getResources().getDisplayMetrics().density
        screenDensityWidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        screenDensityHeight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()


        scaleScreen = sharedPref!!.getFloat("ScaleBackground", 10f)
        scaleTextMain = sharedPref!!.getFloat("ScaleTextMain", 9f)
        scaleTextNews = sharedPref!!.getFloat("ScaleTextNews", 8f)
        scaleTextStats = sharedPref!!.getFloat("ScaleTextStats", 8f)
        scaleTextButton = sharedPref!!.getFloat("ScaleTextButton", 8f)
        scaleTextBig = scaleTextMain * 1.25f

        Companion.hintsBool = sharedPref!!.getBoolean("Global Hints", true)

        firstLastRandomBtn.text = "first"
        if (mapMode == 2){
            livesTV.text = "NME"
        }

        gameSpeedBtn.setOnClickListener {
            if (gameSpeedBtn.text == "1x"){
                gameSpeedBtn.text = "1.5x"
                Companion.gameSpeedAdjuster = 1.5f
            }else if (gameSpeedBtn.text == "1.5x") {
                gameSpeedBtn.text = "2x"
                Companion.gameSpeedAdjuster = 2f
            }else if (gameSpeedBtn.text == "2x") {
                gameSpeedBtn.text = "1x"
                Companion.gameSpeedAdjuster = 1f
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

            if (towerClick) {
                writeLockTower.lock()
                try {
                    var towerListIterator = towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()
                        if (tower.selected) {
                            if (tower.firstLastRandom == 0) {
                                firstLastRandomBtn.text = "last"
                                tower.firstLastRandom = 1
                                tower.randomEnemyForShotBool = true
                                tower.randomEnemyForShotSticky = true
                            } else if (tower.firstLastRandom == 1) {
                                firstLastRandomBtn.text = "random"
                                tower.firstLastRandom = 2
                                tower.randomEnemyForShotBool = true
                                tower.randomEnemyForShotSticky = true
                            } else if (tower.firstLastRandom == 2) {
                                firstLastRandomBtn.text = "first"
                                tower.firstLastRandom = 3
                                tower.randomEnemyForShotBool = true
                                tower.randomEnemyForShotSticky = true
                            } else if (tower.firstLastRandom == 3) {
                                firstLastRandomBtn.text = "sticky"
                                tower.firstLastRandom = 0
                                tower.randomEnemyForShotBool = true
                                tower.randomEnemyForShotSticky = true
                            }
                        }
                    }
                } finally {
                    writeLockTower.unlock()
                }
            }
        }

        talentsBTN.setOnClickListener() {
            paused = true
            intent = Intent(this, Talents::class.java)
            startActivity(intent)
        }

    }

    //----------------------------------------------------------------------------

    fun update() {

        logVolume = (1f-(log10(maxVolume - globalSoundMusic) / log10(maxVolume)))
        logVolumeEffects = (1f-(log10(maxVolume - globalSoundEffects) / log10(maxVolume)))
        if (mediaPlayer != null) mediaPlayer!!.setVolume(logVolume, logVolume)

        runOnUiThread {
            startItems()
            insertItem()
            unusedItems()
            activeAbilities()
            if (paused) playPauseBtn.setImageResource(R.drawable.play)
            else playPauseBtn.setImageResource(R.drawable.pause)

                if (towerClick && towerClickBool) {
                    towerClickBool = false
                    enemyClick = false
                    talentsBTN.visibility = View.VISIBLE
                    firstLastRandomBtn.visibility = View.VISIBLE

                    itemListBagInserter.clear()
                    bagAdapter.notifyDataSetChanged()

                    readLockTower.lock ()
                    try {
                    var towerListIterator = towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()
                        if (tower.selected) {

                            itemListBagInserter.addAll(tower.itemListBag)

                        }
                    }
                        }finally {
                        readLockTower.unlock()
                    }
                    bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                    recyclerBagItem.smoothScrollToPosition(0)

                    if (buildBtn.background != getResources().getDrawable(R.drawable.bagicon3))
                    buildBtn.background = getResources().getDrawable(R.drawable.bagicon3)

                    if (supportFragmentManager.findFragmentById(R.id.fragment) != fragmentStatsTower)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, fragmentStatsTower)
                                .addToBackStack(null)
                                .commit()
                        }

                }


            if (build && buildClickBool){
                buildClickBool = false
                talentsBTN.visibility = View.INVISIBLE
                firstLastRandomBtn.visibility = View.INVISIBLE

                itemListBagInserter.clear()
                bagAdapter.notifyDataSetChanged()
                itemListBagInserter.addAll(buildListBag)
                bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                recyclerBagItem.smoothScrollToPosition(0)

                if (buildBtn.background != getResources().getDrawable(R.drawable.talentsutils))
                    buildBtn.background = getResources().getDrawable(R.drawable.talentsutils)
            }

            if (enemyClick && enemySelectedBool) {
                enemySelectedBool = false
                towerClick = false
                talentsBTN.visibility = View.INVISIBLE
                firstLastRandomBtn.visibility = View.INVISIBLE

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment, fragmentStatsEnemy)
                        .addToBackStack(null)
                        .commit()
                }
            }

            if (Companion.towerStatsClick) {
                Companion.towerStatsClick = false
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment, fragmentStats)
                        .addToBackStack(null)
                        .commit()
                }
            }

            if (Companion.spawnDoubleClick) {
                if (autoSpawn) {
                    var toast = Toast.makeText(this,
                        "Autospawn on", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                    if (level != 0 && enemyList.isEmpty()) levelCount = levelCountPlace
                } else if (!autoSpawn) {
                    var toast = Toast.makeText(this,
                        "Autospawn off", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                }
                Companion.spawnDoubleClick = false
            }

            if (toastGlobal) {
                toastGlobal = false
                var toast = Toast.makeText(this,
                    toastText, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                toast.show()
            }

            // UI Draw ----------------------------------------------------------------

            if (dragStatusDrag){
                var count = 0
                dragRectList.forEach(){
                    if (Rect.intersects(it, dragRect)){
                        count++
                    }
                }
                dragStatusCantBuild = count > 0
                invalidate++
            }

            if (com.agsolutions.td.Companion.globalItemListBag.isEmpty() && itemList.isNotEmpty() && hintsBool) {
                val posXY = IntArray(2)
                recyclerCurrentItem.getLocationOnScreen(posXY)
                UiView.xRecycler = posXY[0].toFloat()
                UiView.yRecycler = posXY[1].toFloat()
                invalidate++
            }
            if (towerClick && towerList[towerClickID].talentPoints > 1 && hintsBool) {
                val posXY = IntArray(2)
                talentsBTN.getLocationOnScreen(posXY)
                UiView.xTalents = posXY[0].toFloat()
                UiView.yTalents = posXY[1].toFloat()
                invalidate++
            }
            if (com.agsolutions.td.Companion.globalItemListBag.isNotEmpty() && hintsBool && tutorialFirstUseItemBool) {
                tutorialFirstUseItemCounter++
                if (tutorialFirstUseItemCounter > 180) tutorialFirstUseItemBool = false
                val posXY = IntArray(2)
                recyclerBagItem.getLocationOnScreen(posXY)
                UiView.xRecyclerBag = posXY[0].toFloat()
                UiView.yRecyclerBag = posXY[1].toFloat()
                invalidate++
            }
            if (!com.agsolutions.td.Companion.autoSpawn && com.agsolutions.td.Companion.autoSpawnCount > 180 && com.agsolutions.td.Companion.hintsBool && com.agsolutions.td.Companion.enemyList.isEmpty()) {
                invalidate++
            }

            if (level == 0) {
                if (levelCount == levelCountPlace - 1 || levelCount == levelCountPlace - 60 || levelCount == levelCountPlace - 120 || levelCount == levelCountPlace - 180 ||
                    levelCount == levelCountPlace - 300 || levelCount == levelCountPlace - 340 || levelCount == levelCountPlace - 380 || levelCount == levelCountPlace - 420
                ) {
                    xLevelCount =
                        (Resources.getSystem().getDisplayMetrics().widthPixels / 2).toFloat()
                    yLevelCount =
                        (Resources.getSystem().getDisplayMetrics().heightPixels / 2).toFloat()
                    invalidate++
                }
            }

            uiRefreshCounter++
            if (invalidate > 0 || level == 0 || uiRefreshCounter >= 60) {
                invalidate = 0
                uiRefreshCounter = 0
                uiView.invalidate()
            }

            when (supportFragmentManager.findFragmentById(R.id.fragment)) {
                fragmentItem -> {
                    coinIV.setPadding((25 * (scaleScreen / 10)).toInt())
                    dmgIV.setPadding((35 * (scaleScreen / 10)).toInt())
                }
                fragmentStatsTower -> {
                    coinIV.setPadding((25 * (scaleScreen / 10)).toInt())
                    dmgIV.setPadding((10 * (scaleScreen / 10)).toInt())
                }
                fragmentStats -> {
                    coinIV.setPadding(0)
                    dmgIV.setPadding((35 * (scaleScreen / 10)).toInt())
                }
                fragmentUpgradeItem -> {
                    coinIV.setPadding((25 * (scaleScreen / 10)).toInt())
                    dmgIV.setPadding((35 * (scaleScreen / 10)).toInt())
                }
                fragmentStatsEnemy -> {
                    coinIV.setPadding((25 * (scaleScreen / 10)).toInt())
                    dmgIV.setPadding((35 * (scaleScreen / 10)).toInt())
                }
            }
        }

        gameEnd()

        // on Touch -------------------------------------------------------------
        if (Companion.spawnDoubleClickCounter > 0) Companion.spawnDoubleClickCounter++
        if (Companion.spawnDoubleClickCounter > 21) {
            if (!autoSpawn && enemyList.isEmpty()) spawnEnemy = true
            Companion.spawnDoubleClickCounter = 0
        }

        //  update game -------------------------------------------------------------
        if (!paused && end == 0) {
            updateGame()
            // refresh
            refresh = true
        }
    }

    //----------------------------------------------------------------------------

    private fun fragments() {

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragmentStats)
                .addToBackStack(null)
                .commit()
        }

        coinIV.setOnClickListener(View.OnClickListener { v ->

            towerClick = false
            enemyClick = false

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragmentStats)
                    .addToBackStack(null)
                    .commit()
            }
        })

        dmgIV.setOnClickListener {

            towerClick = false
            enemyClick = false

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragmentStatsTower)
                    .addToBackStack(null)
                    .commit()
            }
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
        // tutorial
        if (itemPick == 0) {
            if (enemiesKilled == 4) {
                itemList.add(0, Items(0, 1, 999, 0, 4f, 0, 0f, 0, "Mace", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent, (3.0f * lvlScaler), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f))
                adapter.notifyItemInserted(0)
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, TutorialItems::class.java)
                    startActivity(intent)
                }, 50)
                dropItemList = 0
                itemPick = 1
                if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
                    fragmentItemCurrentItem += 1
                }
            }
            // add items
        } else {
            if (dropItemDarkUltimate > 0) {
                itemList.add(0, Items(6666, 1, 999, 0, 0f, 0, 0f, 0, "Soul Collector", R.drawable.soulpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "Collects Souls", 0f, "", 0f))
                adapter.notifyItemInserted(0)
                dropItemDarkUltimate -= 1
                if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
                    fragmentItemCurrentItem += 1
                }
            }
            // telling adapter to insert item to recycler 1
            if (Companion.insertItem >= 1) {
                Companion.insertItem -= 1

                adapter.notifyItemInserted(0)
                recyclerCurrentItem.smoothScrollToPosition(0)
            }

            // telling adapter to insert item to recycler 2
            if (Companion.insertItemBag >= 1) {
                Companion.insertItemBag -= 1

                itemListBagInserter.clear()
                bagAdapter.notifyDataSetChanged()

                if (towerClick) {
                    readLockTower.lock()
                    try {
                        var towerListIterator = towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()
                            if (tower.selected) {

                                itemListBagInserter.addAll(tower.itemListBag)

                            }
                        }
                    } finally {
                        readLockTower.unlock()
                    }
                } else {
                    itemListBagInserter.addAll(buildListBag)
                }
                bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                recyclerBagItem.smoothScrollToPosition(0)

            }
            // tell adapter news added to radio
            if (radioAdd >= 1) {
                radioAdd -= 1
                radioAdapter.notifyDataSetChanged()
                recyclerRadio.smoothScrollToPosition(radioList.size - 1)
            }
        }
        // tell adapter active abilities to insert into recycler active ability
        if (insertSpell > 0) {
            activeAbilityAdapter.notifyDataSetChanged()
            insertSpell = 0
        }

        if (itemUsed && supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
            supportFragmentManager.beginTransaction().apply {
                            replace(R.id.fragment, fragmentStats)
                                .addToBackStack(null)
                                .commit()
                        }
        }
    }

    //----------------------------------------------------------------------------

    fun dropItem (enemy: Enemy){
        readLockTower.lock()
        try {
        if (dropItemList >= 1) {
            when ((0..999).random()) {
                in 0..(towerList[enemy.killerId].overallItemChance * 10).toInt() -> {
                    var itemX = getItem(towerList[enemy.killerId])
                    // TODO
               //     if (Companion.upgraderBool && itemX.upgrade != 0) itemX.upgrade *= 2
                    itemList.add(0, itemX)

                    runOnUiThread {
                        itemListInserter.clear()
                        adapter.notifyDataSetChanged()
                        itemListInserter.addAll(itemList)
                        adapter.notifyItemRangeInserted(0, itemListInserter.size)
                        recyclerCurrentItem.smoothScrollToPosition(0)
                    }

                    if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
                        fragmentItemCurrentItem += 1
                    }
                }
            }
            dropItemList -= 1
        }
            } finally {
                        readLockTower.unlock()
            }
    }

    //----------------------------------------------------------------------------

    fun unusedItems () {
        // unused items
        if (itemList.size >= 8) {

            if (itemListNormal.contains(itemList[7].id)) itemPoints += 0.5f
            if (itemListRare.contains(itemList[7].id)) itemPoints += 1f
            if (itemListEpic.contains(itemList[7].id)) itemPoints += 2f
            if (itemListLegendary.contains(itemList[7].id)) itemPoints += 3f

            if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem && fragmentItemCurrentItem >= 7) {
                supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragmentStats)
                .addToBackStack(null)
                .commit()
                }
            }

            itemList.removeAt(7)

            runOnUiThread {
                itemListInserter.clear()
                adapter.notifyDataSetChanged()
                itemListInserter.addAll(itemList)
                adapter.notifyItemRangeInserted(0, itemListInserter.size)
                recyclerCurrentItem.smoothScrollToPosition(0)
            }

        }
    }

    //----------------------------------------------------------------------------

    private fun gameEnd() {

        if ((mapMode == 1 && updateViewModel.lives.value == "0" && end == 0) || (mapMode == 2 && lives >= livesMode2 && end == 0)) {
            end = 1
            var editor = sharedPref!!.edit()
            editor.putFloat("Global Music", globalSoundMusic)
            editor.putFloat("Global Effects", globalSoundEffects)
            editor.apply()
            intent = Intent(this, GameEnd::class.java)
            startActivity(intent)
        }
        if (mapPick == 0 && level == 10 && (bossesKilled == 1 || bossesLeaked == 1) && end == 0) {
            end = 1
            intent = Intent(this, TutorialEnd::class.java)
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

        towerClick = false
        enemyClick = false

        // show item fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragmentItem)
                .addToBackStack(null)
                .commit()
        }

        fragmentItemCurrentItem = position
        fragmentItem.refresh(supportFragmentManager, fragmentItem)
    }

    //----------------------------------------------------------------------------

    override fun onBagLongClick(position: Int, it: View?) {

        // Creates a new drag event listener
        val dragListen = View.OnDragListener { vv, event ->

            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        dragStatusDrag = true

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
                    dragRect = Rect(event.x.toInt(), event.y.toInt(), event.x.toInt(), event.y.toInt())

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

                    if (level > 0 && position == 0){
                        if (!Companion.day && Companion.moonTalentItemCost > 0){
                            if (Companion.gold >= (buildListBag[position].goldCost - (buildListBag[position].goldCost * Companion.moonTalentItemCost))) Companion.gold -= (buildListBag[position].goldCost - (buildListBag[position].goldCost * Companion.moonTalentItemCost))
                            else build = false
                        }
                        else if (Companion.midnightMadnessMidasGoldCost > 0){
                            if (Companion.gold >= (buildListBag[position].goldCost + (buildListBag[position].goldCost * Companion.midnightMadnessMidasGoldCost))) Companion.gold -= (buildListBag[position].goldCost + (buildListBag[position].goldCost * Companion.midnightMadnessMidasGoldCost))
                            else build = false
                        }
                        else {
                            if (Companion.gold >= buildListBag[position].goldCost) Companion.gold -= buildListBag[position].goldCost
                            else build = false
                        }
                    }

                    if (build && !dragStatusCantBuild) {
                        var towerPlace =
                            Tower(buildListBag[position].dmg, buildListBag[position].atkDmg, buildListBag[position].mgcDmg, buildListBag[position].speed, buildListBag[position].crit, buildListBag[position].critDmg)
                        towerPlace.towerRange = TowerRadius(event.x - 32, event.y - 32, 300f)
                        dragRectList.add(Rect((event.x - 80).toInt(), (event.y - 80).toInt(), (event.x + 80).toInt(), (event.y + 80).toInt()))
                        towerPlace.firstLastRandom = 3
                        towerPlace.bagSize = (buildListBag[position].specialFloat - 1).toInt()
                        towerPlace.bagSizeElement =
                            (buildListBag[position].specialFloat2 - 1).toInt()
                        when (buildListBag[position].id) {
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
                        when (buildListBag[position].id) {
                            2100, 2200, 2300 -> {
                                towerPlace.towerPrimaryElement = "earth"
                                itemPlace = Items.eearth
                            }
                            2101, 2201, 2301 -> itemPlace = Items.ewizard
                            2102, 2202, 2302 -> itemPlace = Items.eice
                            2103, 2203, 2303 -> {
                                towerPlace.towerPrimaryElement = "butterfly"
                                itemPlace = Items.ebutterfly
                            }
                            2104, 2204, 2304 -> itemPlace = Items.epoison
                            2105, 2205, 2305 -> {
                                towerPlace.towerPrimaryElement = "moon"
                                itemPlace = Items.emoon
                            }
                            2106, 2206, 2306 -> {
                                towerPlace.towerPrimaryElement = "wind"
                                itemPlace = Items.ewind
                            }
                            2107, 2207, 2307 -> itemPlace = Items.eutils
                            2108, 2208, 2308 -> itemPlace = Items.efire
                            2109, 2209, 2309 -> itemPlace = Items.edark
                        }
                        if (itemPlace != null) {
                            towerPlace.itemListBag.add(0, itemPlace)
                        }

                        writeLockTower.lock()
                        try {
                            var towerListIterator = towerList.listIterator()
                            towerListIterator.add(towerPlace)
                        } finally {
                            writeLockTower.unlock()
                        }

                        if (level == 0 || (level > 0 && position != 0)) {
                            buildListBag.removeAt(position)

                            itemListBagInserter.clear()
                            bagAdapter.notifyDataSetChanged()
                            itemListBagInserter.addAll(buildListBag)
                            bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                            recyclerBagItem.smoothScrollToPosition(0)
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
                            dragStatusDrag = false
                            // drop was handled
                            Log.d("drag", "ended, success")
                        }
                        else ->{
                            dragStatusDrag = false
                            // drop didn't work
                            Log.d("drag", "ended, fail")
                        }
                    }

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

            enemyClick = false

            // show item update fragment

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment, fragmentUpgradeItem)
                    .addToBackStack(null)
                    .commit()
            }

            itemFragmentEnemyList.removeAll(itemFragmentEnemyList)

        if (!towerClick && buildListBag.size > 0) {
                        itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, buildListBag[position].name.toString()))
            if (!Companion.day && Companion.moonTalentItemCost > 0 && level != 0 && buildListBag[position] == buildListBag[0]) {
                if (buildListBag[position].goldCost > 0) {
                    when (buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost - (buildListBag[position].goldCost * Companion.moonTalentItemCost)).toInt()
                            .toString() + " / " + Companion.gold.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost - (buildListBag[position].goldCost * Companion.moonTalentItemCost) / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.gold / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost - (buildListBag[position].goldCost * Companion.moonTalentItemCost) / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.gold / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else if (Companion.midnightMadnessMidasGoldCost > 0 && level != 0 && buildListBag[position] == buildListBag[0]) {
                if (buildListBag[position].goldCost > 0) {
                    when (buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost + (buildListBag[position].goldCost * Companion.midnightMadnessMidasGoldCost)).toInt()
                            .toString() + " / " + Companion.gold.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost + (buildListBag[position].goldCost * Companion.midnightMadnessMidasGoldCost) / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.gold / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost + (buildListBag[position].goldCost * Companion.midnightMadnessMidasGoldCost) / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.gold / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else {
                if (buildListBag[position].goldCost > 0 && level != 0 && buildListBag[position] == buildListBag[0]) {
                    when (buildListBag[position].goldCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost).toInt()
                            .toString() + " / " + Companion.gold.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.gold / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (buildListBag[position].goldCost / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.gold / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            }

                        if (buildListBag[position].upgrade > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, buildListBag[position].upgrade.toString()))
                        if (buildListBag[position].dmg > 0) {
                            when ((buildListBag[position].dmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, buildListBag[position].dmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (buildListBag[position].dmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (buildListBag[position].dmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (buildListBag[position].atkDmg > 0) {
                            when ((buildListBag[position].atkDmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, buildListBag[position].atkDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (buildListBag[position].atkDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (buildListBag[position].atkDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (buildListBag[position].mgcDmg > 0) {
                            when ((buildListBag[position].mgcDmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, buildListBag[position].mgcDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (buildListBag[position].mgcDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (buildListBag[position].mgcDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (buildListBag[position].crit > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, buildListBag[position].crit.round(2)
                            .toString()))
                        if (buildListBag[position].critDmg > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, buildListBag[position].critDmg.round(2)
                            .toString()))
                        if (buildListBag[position].speed > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, buildListBag[position].speed.round(2)
                            .toString()))
                        if (buildListBag[position].special.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, buildListBag[position].special.toString()))
                            if (buildListBag[position].specialFloat != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, buildListBag[position].specialFloat.round(2)
                                .toString()))
                        }
                        if (buildListBag[position].special2.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, buildListBag[position].special2.toString()))
                            if (buildListBag[position].specialFloat2 != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, buildListBag[position].specialFloat2.round(2)
                                .toString()))
                        }
            if (buildListBag[position].id == 2000) {
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 0.9"))
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 0.9"))
            }
            if (buildListBag[position].id == 2100 || buildListBag[position].id == 2101 || buildListBag[position].id == 2102 || buildListBag[position].id == 2103 || buildListBag[position].id == 2104 || buildListBag[position].id == 2105 || buildListBag[position].id == 2106 || buildListBag[position].id == 2107 || buildListBag[position].id == 2108 || buildListBag[position].id == 2109){
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.0"))
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.0"))
            }
            if (buildListBag[position].id == 2200 || buildListBag[position].id == 2201 || buildListBag[position].id == 2202 || buildListBag[position].id == 2203 || buildListBag[position].id == 2204 || buildListBag[position].id == 2205 || buildListBag[position].id == 2206 || buildListBag[position].id == 2207 || buildListBag[position].id == 2208 || buildListBag[position].id == 2209){
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.1"))
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.1"))
            }
            if (buildListBag[position].id == 2300 || buildListBag[position].id == 2301 || buildListBag[position].id == 2302 || buildListBag[position].id == 2303 || buildListBag[position].id == 2304 || buildListBag[position].id == 2305 || buildListBag[position].id == 2306 || buildListBag[position].id == 2307 || buildListBag[position].id == 2308 || buildListBag[position].id == 2309){
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.25"))
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.25"))
            }
            Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.overlaytransparent, "                ".toString()))

        }else {

            readLockTower.lock()
            try {
                var towerListIterator = towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.selected) {

                        itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, tower.itemListBag[position].name.toString()))
                        if (tower.itemListBag[position].upgrade > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, tower.itemListBag[position].upgrade.toString()))
                        if (tower.itemListBag[position].dmg > 0) {
                            when ((tower.itemListBag[position].dmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, tower.itemListBag[position].dmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].atkDmg > 0) {
                            when ((tower.itemListBag[position].atkDmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, tower.itemListBag[position].atkDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].mgcDmg > 0) {
                            when ((tower.itemListBag[position].mgcDmg).toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, tower.itemListBag[position].mgcDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].crit > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, tower.itemListBag[position].crit.round(2)
                            .toString()))
                        if (tower.itemListBag[position].critDmg > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, tower.itemListBag[position].critDmg.round(2)
                            .toString()))
                        if (tower.itemListBag[position].speed > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, tower.itemListBag[position].speed.round(2)
                            .toString()))
                        if (tower.itemListBag[position].special.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special.toString()))
                            if (tower.itemListBag[position].specialFloat != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat.round(2)
                                .toString()))
                        }
                        if (tower.itemListBag[position].special2.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special2.toString()))
                            if (tower.itemListBag[position].specialFloat2 != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat2.round(2)
                                .toString()))
                        }
                        break
                    }
                }
            } finally {
                readLockTower.unlock()
            }

            fragmentUpgradeItem.refresh2()

            mHandler.postDelayed({ fragmentUpgradeItem.refresh(position) }, 50)

        }
    }

    //----------------------------------------------------------------------------

    override fun onActiveAbilityClick(position: Int) {
        // active ability recycler on click

        if (activeAbilityList[position].id == 7000) {
            if (endlessNightBool && day) {
                endlessNightActive = true
                endlessNightBool = false
                endlessNightButtonCounter = 1
            }
        } else if (activeAbilityList[position].id == 7002 ) {
            if (activeAbilityHelpingHandBool && itemPoints >= 10) {
                activeAbilityHelpingHandBool = false
                itemPoints -= 10
                activeAbilityHelpingHandActiveCounter = 1
                activeAbilityHelpingHandButtonCounter = 1
            }
        } else if (activeAbilityList[position].id == 7003 ) {
            if (activeAbilityHelpingHandBool) {
                activeAbilityHelpingHandBool = false
                activeAbilityHelpingHandActiveCounter = 1
                activeAbilityHelpingHandButtonCounter = 1
            }
        }else if (activeAbilityList[position].id == 7001 && bombActiveAbility > 0) {

            bombActiveAbility -= 1
            bombsUsedCounter += 1
                writeLockEnemy.lock()
                writeLockDisplay.lock()
                try {

                    var enemyListIterator = enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()

                        // item Detonate
                        var dmg = 0f
                        if (mapMode == 2) dmg = (it.maxHp * (0.05f + bombDamage))
                        else if (mapMode == 1) dmg = (it.maxHp * (0.2f + bombDamage))

                        it.hp -= dmg
                        var dmgString = "0"
                        when (dmg.toInt()){
                            in 0..999 -> dmgString = dmg.toInt().toString()
                            in 1000..999999 -> dmgString = (dmg / 1000).toInt().toString() + "k"
                            in 1000000..999999999 -> dmgString =
                                (dmg / 1000000).toInt().toString() + "M"
                        }
                        var dmgDisplayListIterator = dmgDisplayList.listIterator()
                        dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintBombDmgDone, 30, 15))

                        if (it.hp < 0) it.hp = 0f
                    }

                } finally {
                    writeLockEnemy.unlock()
                    writeLockDisplay.unlock()
                }

            if (bombActiveAbility <= 0) {
                activeAbilityList.removeAt(position)
                activeAbilityAdapter.notifyItemRemoved(position)
            } else {
                activeAbilityList[position].cd = bombActiveAbility.toFloat()
                activeAbilityList[position].cdRemain =
                    activeAbilityList[position].cd.toInt().toString()
                activeAbilityAdapter.notifyItemChanged(position)
            }
        }
    }

    fun activeAbilities() {
        // update active abilities--------------------------------------------

        // endless night
        if (activeAbilityList.contains(aAid0) || activeAbilityList.contains(aAid1) || activeAbilityList.contains(aAid2) || activeAbilityList.contains(aAid3)) {

            activeAbilityPlace ++
            activeAbilityList.forEach() {
                //  bombActiveAbilityCounterPlace++
                //  if (bombActiveAbilityCounterPlace >= 60) {

                    if (activeAbilityPlace >= 60) {
                        activeAbilityPlace = 0

                        if (it.id == 7000) {
                            if (endlessNightButtonCounter >= it.cd) {  // 900 * 36
                            endlessNightButtonCounter = 0
                            endlessNightBool = true
                        }
                        if (!endlessNightBool && endlessNight > 0) {
                            it.cdRemain =
                                ((it.cd - endlessNightButtonCounter) / 60).toInt().toString()
                            activeAbilityAdapter.notifyItemChanged(activeAbilityList.indexOf(it))
                            if (it.cdRemain == 0.toString()) it.cdRemain = ""
                        } else it.cdRemain = ""
                        }

                        if (it.id == 7002 || it.id == 7003) {
                                if (activeAbilityHelpingHandButtonCounter >= it.cd) {  // 900 * 36
                                    activeAbilityHelpingHandButtonCounter = 0
                                    activeAbilityHelpingHandBool = true
                                }
                            if (activeAbilityHelpingHandBool) it.cdRemain = ""
                            else if (!activeAbilityHelpingHandBool) {
                                    it.cdRemain =
                                        ((it.cd - activeAbilityHelpingHandButtonCounter) / 60).toInt()
                                            .toString()
                                    if (it.cdRemain == 0.toString()) it.cdRemain = ""
                                activeAbilityAdapter.notifyItemChanged(activeAbilityList.indexOf(it))
                            } else it.cdRemain = ""

                        }

                        if (it.id == 7001 && insertSpellBomb > 0) {
                            insertSpellBomb = 0
                            it.cd = bombActiveAbility.toFloat()
                            it.cdRemain = it.cd.toInt().toString()
                            activeAbilityAdapter.notifyItemChanged(activeAbilityList.indexOf(it))
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
        dead2()

    }

        fun onLvlUp () {
            // on lvl up
            timer ++
            if (level > 75) levelCountBool = true
            if (mapMode == 2) levelCountBool = true
            if (enemyList.isEmpty() && levelCountSecondBool) {
                levelCountSecondBool = false
                if (level == 0 && hintsBool) levelCount = levelCountPlace - 900
                else if (level == 0) levelCount = levelCountPlace - 480
                else levelCount = levelCountPlace - 180
                levelCountBool = true
            }
            if (enemyList.isEmpty() && !autoSpawn && mapMode == 1 && level != 0) {
                levelCount = 0
                if (autoSpawnCountBool){
                    autoSpawnCountBool = false
                    autoSpawnCount = 1
                }
            }
            if (enemyList.isEmpty() && spawnEnemy && mapMode == 1 && level != 0) {
                spawnEnemy = false
                levelCount = levelCountPlace
                autoSpawnCount = 0
            }
            if (autoSpawnCount > 0) autoSpawnCount++
            if (levelCountBool) levelCount ++

            //normal
            if (levelCount >= levelCountPlace) {
                autoSpawnCountBool = true
                levelCountBool = false
                levelCountSecondBool = false
                level++
                levelCount = 0
                enemySpawned = 0
                levelStatus = "undef"
                tankBool = true
                gold *= interest      // interest

                //    if (level == 1) soundPool.play(soundIds[0], 0f, 0f, 1, -1, 1.0f)

                // level counter

                if (level > 150) levelCountPlace * 0.90
                else if (level > 125) levelCountPlace * 0.95
                else if (level > 75) levelCountPlace * 0.99

                // tutorial touch screen

                if (level == 3 && mapPick == 0) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, TutorialTouchScreen::class.java)
                        startActivity(intent)
                    }, 50)
                }

                // tutorial talents
                if (level == 4 && hintsBool) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, TutorialTalents::class.java)
                        startActivity(intent)
                    }, 50)
                }

                // tutorial enemies
                if (level == 10 && mapPick == 0) {
                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, TutorialEnemies::class.java)
                        startActivity(intent)
                    }, 50)
                }

                if (mapMode == 2) lvlHp *= (1 + (0.09f * scaler) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.033f, 0.033f, 0.033f, 0.075f, 0.15f).random()))
                else lvlHp *= (1 + (0.1f * scaler) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.033f, 0.033f, 0.033f, 0.075f, 0.15f).random()))
                lvlArmor *= (1 + (0.03f *scaler) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.025f, 0.025f, 0.025f, 0.025f, 0.05f).random()))
                lvlMagicArmor *= (1 + (0.03f * scaler) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.025f, 0.025f, 0.025f, 0.025f, 0.05f).random()))
                lvlEvade *= (1 + (0.01f *scaler) + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0125f, 0.0125f, 0.0125f, 0.0125f, 0.025f).random()))
                lvlXp *= 1 + (0.05f*scaler)
                lvlScalerFirst *= (1.05f * scaler)

                if (levelList.contains("speed")) lvlSpd *= (1.0f + (listOf<Float>(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.03f).random()))
                if (level > 0 && Utils.divisible(level, 50)) costDia ++

                if (lvlHp > 100000000) {
                    lvlHp /= 1000
                    bigNumberScaler /= 1000
                    lvlXp /= 1000
                    gold /= 1000

                    itemList.forEach {
                        it.dmg /= 1000
                        it.atkDmg /= 1000
                        it.mgcDmg /= 1000
                    }
                    readLockTower.lock ()
                    try {
                            var towerListIterator = towerList.listIterator()
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
                                    if (mapMode != 2) {
                                        if (it.id == 205) if (Utils.divisible(it.lvlAqu, 10)) lives += it.specialFloat.toInt()
                                    }
                                    else {
                                        if (it.id == 205) if (Utils.divisible(it.lvlAqu, 10)) livesMode2 += it.specialFloat.toInt()
                                    }
                                }
                                if (tower.experienceLvl) {
                                    var xpGain = 1f
                                    tower.xpTower += xpGain
                                    towerExperienceGainUtilAura(xpGain, towerList.indexOf(tower))
                                    if (tower.experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, towerList.indexOf(tower))
                                }
                            }
                        }finally {
                        readLockTower.unlock()
                    }

                    writeLockEnemy.lock ()
                    try {
                        var enemyListIterator = enemyList.listIterator()
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
                        writeLockEnemy.unlock()
                    }


                    paused = true
                    mHandler.postDelayed({
                        intent = Intent(this, CuttingStatsEvent::class.java)
                        startActivity(intent)
                    }, 50)
                }

                levelScalerCrit *= 0.98f
                levelScalerSpeed *= 0.99f
                levelScalerCritDmg *= 0.99f
                levelScalerItemChance *= 0.995f
                levelScalerItemQuality *= 0.995f
                lvlXpConstant *= 0.995f
                lvlHpReg *= 0.98f

                // picking item
                lvlScaler = lvlXp * lvlXpConstant
                lvlScalerSecond = lvlScalerFirst * lvlXpConstant
                costBase *= 1.01f
                costBlue = costBase * 2.0f
                costEpic = costBase * 3.0f
                costLegendary = costBase * 5.0f

                if (level != 0 && level != 1){
                        if (towerClick) {
                            buildListBag.removeAt(0)
                            buildListBag.add(0, Items(2000, 0, 999, 0, (costBase * lvlXp * (1.5f + (towerCount / 10))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                        }else {
                            runOnUiThread() {
                            buildListBag.removeAt(0)
                            itemListBagInserter.clear()
                            bagAdapter.notifyDataSetChanged()

                            buildListBag.add(0, Items(2000, 0, 999, 0, (costBase * lvlXp * (1.5f + (towerCount / 10))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                            itemListBagInserter.addAll(buildListBag)
                            bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                            }
                        }
                } else {
                    runOnUiThread() {
                        itemListBagInserter.clear()
                        bagAdapter.notifyDataSetChanged()
                        buildListBag.add(0, Items(2000, 0, 999, 0, (costBase * lvlXp * (1.5f + (towerCount / 10))), 0, 0f, 0, "Basic Tower", R.drawable.towerallgrey, R.drawable.overlaytransparent, 5f, 0.0f, 0.0f, 70f, 1f, 1.5f, 0, "bagspace", 2.0f, "bagspace element", 1f))
                        itemListBagInserter.addAll(buildListBag)
                        bagAdapter.notifyItemRangeInserted(0, itemListBagInserter.size)
                    }
                }

                if (level == 1) {
                    radioList.add(
                        radioList.size,
                        "lvl " + level.toString() + " assault started"
                    )
                    radioAdd += 1
                }

                if (level > 0 && Utils.divisible(level, 10) && levelListReserve.size > 0) {
                    val x = levelListReserve.random()
                    levelList.add(x)
                    when (x) {
                        "armor" -> lvlArmor += 1
                        "magic armor" -> lvlMagicArmor += 1
                        "evade" -> lvlEvade += 1
                    }
                    levelListReserve.remove(x)
                    radioList.add(
                        radioList.size,
                        "lvl " + level.toString() + " + " + x + " ability"
                    )
                    radioAdd += 1
                }

                // reset shield
                allShieldsBool = false
                manaShieldBool = false
                shieldBool = false

                if (level == 30) levelListReserve.addAll(listOf("speed", "mass", "regeneration", "immune", "split", "manaShield", "shield"))
                if (level == 75) levelListReserve.addAll(listOf("healer", "tank"))

                if (level > 0 && Utils.divisible(level, 50)) {
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
        if (enemyList.isEmpty() && !autoSpawn && mapMode == 1 && level != 0) {
        } else dayNightCounter += 5
        if (endlessNightButtonCounter > 0) endlessNightButtonCounter++
        if (dayNightCounter == 30) {
            dayNightMinute++
            dayNightCounter = 0
        }
        if (dayNightMinute == 60) {
            dayNightHour++
            dayNightMinute = 0
        }
        if (dayNightHour == 24) {
            dayNightHour = 0
        }
        if (day && soundBoolDay) {
            soundBoolDay = false
            soundBoolNight = true
            if (mediaPlayer != null) {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()
                }
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.daymusiccomp)
            mediaPlayer!!.setVolume(logVolume, logVolume)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        } else if (!day && soundBoolNight) {
            soundBoolNight = false
            soundBoolDay = true
            if (mediaPlayer!!.isPlaying && mediaPlayer != null) {
                mediaPlayer!!.stop()
                mediaPlayer!!.release()
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.nightmusiccomp)
            mediaPlayer!!.setVolume(logVolume, logVolume)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()

        }

        if (endlessNightActive) {
            day = false
            endlessNightActiveCounter++
            if (endlessNightActiveCounter >= (900 * endlessNight)) {
                endlessNightActive = false
                endlessNightActiveCounter = 0
            }
        } else {
            if (dayNightVariable < -5) dayNightVariable = -5
            if (dayNightVariable > 5) dayNightVariable = 5
            day = !(dayNightHour < dayNightVariable || dayNightHour > 19)
        }
    }

        fun midnightEvents () {

            // midnight events ---------------------------------------------------------------------

        if ((dayNightHour == 0 && dayNightMinute == 0) && mapPick != 0 && secretShopBool) {
            secretShopBool = false

            radioList.remove(midnightMadnessEventRadio)

            midnightMadnessEventRadio

            if (midnightMadnessDisableItems) {
                midnightMadnessDisableItems = false
                midnightMadnessSaveList.remove("Darkest Hour")
                readLockTower.lock ()
                try {
                        var towerListIterator = towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()

                            tower.itemListBag.forEach {
                                if (it.crossedOut){
                                    var x = tower.itemListBag.indexOf(it)
                                    var itemListBagIterator = itemListBagMysteryEventCancelled.listIterator()
                                    while (itemListBagIterator.hasNext()) {
                                        var cancelled = itemListBagIterator.next()
                                        if (it.id == cancelled.id) tower.itemListBag.set(x, cancelled)
                                        itemListBagIterator.remove()
                                        break
                                    }
                                    it.crossedOut = false
                                    it.imageOverlay = R.drawable.overlaytransparent
                                }
                            }


                        }
                }finally {
                    readLockTower.unlock()
                }

                runOnUiThread {
                    bagAdapter.notifyDataSetChanged()
                }
            }
            if (midnightMadnessMidas){
                midnightMadnessMidas = false
                midnightMadnessMidasGold -= 0.2f
                midnightMadnessMidasGoldCost -= 0.2f
                midnightMadnessSaveList.remove("Midas")
            }
            if (midnightMadnessWind) {
                midnightMadnessWind = false
                overallSpdMultiplier += 20f
                lvlSpd += midnightMadnessWindLvlSpeedReduce
                midnightMadnessWindLvlSpeedReduce = 0f
                midnightMadnessSaveList.remove("Winds")
            }
            if (midnightMadnessExtraSpawnBool){
                midnightMadnessExtraSpawnBool = false
                midnightMadnessExtraSpawn -= 1
                midnightMadnessSaveList.remove("Extra")
            }


            when ((0..9).random()) {
                //nothing
                in 0..2 -> {
                    paused = true
                    midnightMadnessEvent = "A Quiet Night"
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "A Quiet Night")
                        intent.putExtra("Description", "Nothing happened...")
                        startActivity(intent)
                    }, 50)
                }

                // extra

                3 -> {
                    paused = true
                    midnightMadnessEvent = "Extra, Extra!"
                    midnightMadnessExtraSpawnBool = true
                    midnightMadnessExtraSpawn += 1
                    midnightMadnessSaveList.add("Extra")
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Extra, Extra!")
                        intent.putExtra("Description", "Each wave contains 1 extra enemy")
                        startActivity(intent)
                    }, 50)
                }

                // upgrade
                4 -> {
                    paused = true
                    midnightMadnessEvent = "Upgrader"

                    readLockTower.lock ()
                    try {
                            var towerListIterator = towerList.listIterator()
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
                        readLockTower.unlock()
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
                    paused = true
                    midnightMadnessEvent = "Heavy Winds"
                    midnightMadnessWind = true
                    overallSpdMultiplier -= 20f
                    midnightMadnessWindLvlSpeedReduce = lvlSpd * 0.2f
                    lvlSpd -= midnightMadnessWindLvlSpeedReduce
                    midnightMadnessSaveList.add("Winds")

                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Heavy Winds")
                        intent.putExtra("Description", "Enemy speed and tower attack speed reduced by 20%")
                        startActivity(intent)
                    }, 50)
                }

                // more gold (cost)
                6 -> {
                    paused = true
                    midnightMadnessEvent = "Midas"
                    midnightMadnessMidas = true
                    midnightMadnessMidasGold += 0.2f
                    midnightMadnessMidasGoldCost += 0.2f
                    midnightMadnessSaveList.add("Midas")

                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Midas")
                        intent.putExtra("Description", "Enemies drop 20% extra gold but items cost 20% more.")
                        startActivity(intent)
                    }, 50)
                }

                // items stolen
                7 -> {
                    paused = true
                    midnightMadnessEvent = "Darkest Hour"
                    midnightMadnessSaveList.add("Darkest Hour")
                    midnightMadnessDisableItems = true
                    readLockTower.lock ()
                    try {
                            var towerListIterator = towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()

                                repeat(2) {
                                    if (tower.itemListBag.size > 0) {
                                        val randomItem: Int = (0 until tower.itemListBag.size).random()
                                        if (tower.itemListBag[randomItem].crossedOut || tower.itemListBag[randomItem].element) {
                                        } else {
                                            when {
                                                (greyItems.contains(tower.itemListBag[randomItem].id)) -> {
                                                    when ((0..1).random()) {
                                                        0 -> {
                                                            tower.itemListBag[randomItem].crossedOut = true
                                                            var listPlace = tower.itemListBag.toMutableList()
                                                            itemListBagMysteryEventCancelled.add(0, listPlace[randomItem])
                                                            bagAdapter.replaceCrossedOut(randomItem)
                                                            tower.itemListBag[randomItem].imageOverlay =
                                                                R.drawable.crossedout
                                                        }
                                                    }
                                                }
                                                (blueItems.contains(tower.itemListBag[randomItem].id)) -> {
                                                    when ((0..4).random()) {
                                                        0 -> {
                                                            tower.itemListBag[randomItem].crossedOut = true
                                                            var listPlace = tower.itemListBag.toMutableList()
                                                            itemListBagMysteryEventCancelled.add(0, listPlace[randomItem])
                                                            bagAdapter.replaceCrossedOut(randomItem)
                                                            tower.itemListBag[randomItem].imageOverlay =
                                                                R.drawable.crossedout
                                                        }
                                                    }
                                                }
                                                (orangeItems.contains(tower.itemListBag[randomItem].id)) -> {
                                                    when ((0..9).random()) {
                                                        0 -> {
                                                            tower.itemListBag[randomItem].crossedOut = true
                                                            var listPlace = tower.itemListBag.toMutableList()
                                                            itemListBagMysteryEventCancelled.add(0, listPlace[randomItem])
                                                            bagAdapter.replaceCrossedOut(randomItem)
                                                            tower.itemListBag[randomItem].imageOverlay =
                                                                R.drawable.crossedout
                                                        }
                                                    }
                                                }
                                                (purpleItems.contains(tower.itemListBag[randomItem].id)) -> {
                                                    when ((0..19).random()) {
                                                        0 -> {
                                                            tower.itemListBag[randomItem].crossedOut = true
                                                            var listPlace = tower.itemListBag.toMutableList()
                                                            itemListBagMysteryEventCancelled.add(0, listPlace[randomItem])
                                                            bagAdapter.replaceCrossedOut(randomItem)
                                                            tower.itemListBag[randomItem].imageOverlay =
                                                                R.drawable.crossedout
                                                        }
                                                    }
                                                }
                                            }

                                            runOnUiThread {
                                                bagAdapter.notifyDataSetChanged()
                                            }
                                        }
                                    }
                            }
                        }
                    }finally {
                        readLockTower.unlock()
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
                    paused = true
                    midnightMadnessEvent = "Treasure Hunt"
                    mHandler.postDelayed({
                        intent = Intent(this, MidnightMadness::class.java)
                        intent.putExtra("Title", "Treasure Hunt")
                        intent.putExtra("Description", "A mysterious box appears. It contains...")
                        startActivity(intent)
                    }, 50)
                }

                // secret shop
                9 -> {
                    paused = true
                    midnightMadnessEvent = "Secret Shop"
                    mHandler.postDelayed({
                        intent = Intent(this, SecretShop::class.java)
                        startActivity(intent)
                    }, 50)
                }
            }
            midnightMadnessEventRadio = "Midnight Madness: "  +  midnightMadnessEvent
            radioList.add(
                radioList.size, midnightMadnessEventRadio)
            radioAdd += 1

        }
        if ((dayNightHour == 0 && dayNightMinute == 5) && mapPick != 0 && level >= 1 && !secretShopBool) {
        secretShopBool = true
        }
        }

        fun mysteryEvents () {

        // update mystery points--------------------------------------------

            // TODO
            /*
        when {
            darkRow1Item1 == 1 && earthRow1Item1 == 1 && fireRow1Item1 == 1 && iceRow1Item1 == 1 && windRow1Item1 == 1 && moonRow1Item1 == 1 && poisonRow1Item1 == 1 && mysteryAllRounderBool-> {
                mysteryAllRounderBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "All-Rounder")
                    intent.putExtra("Description", "Put 1 TP in each element.")
                    startActivity(intent)
                }, 500)
            }
            fireRow4Item1 == 3 && iceRow4Item1 == 3 && mysterySongBool -> {
                mysterySongBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "A song of ice and fire")
                    intent.putExtra("Description", "Put 3 talent points in the fire and ice ultimate ability.")
                    startActivity(intent)
                }, 500)
            }
            magicBoxCount > 3 && mysteryClownBool-> {
                mysteryClownBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Clown")
                    intent.putExtra("Description", "Have 4 or more magic boxes in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            maceCount > 3 && mysteryMaceBool-> {
                mysteryMaceBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Strong like a Bear")
                    intent.putExtra("Description", "Have 4 or more maces in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            bowCount > 3 && mysteryBowBool-> {
                mysteryBowBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Fast as the Wind")
                    intent.putExtra("Description", "Have 4 or more bows in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            swordCount > 3 && mysterySwordBool-> {
                mysterySwordBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Sharp as a Knife")
                    intent.putExtra("Description", "Have 4 or more swords in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            luckyCharmCount > 2 && mysteryLuckyCharmBool-> {
                mysteryLuckyCharmBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Master of the Occult")
                    intent.putExtra("Description", "Have 3 or more lucky charms in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            pirateItemCount > 2 && mysteryPirateHunterBool -> {
                mysteryPirateHunterBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Pirate Hunter")
                    intent.putExtra("Description", "Have 3 or more pirate items in your inventory.")
                    startActivity(intent)
                }, 150)
            }
            bombsUsedCounter == 5 && mysteryBombsUsedBool -> {
                mysteryBombsUsedBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Bomberman")
                    intent.putExtra("Description", "Use 5 bombs.")
                    startActivity(intent)
                }, 150)
            }
            livesMpCounter && iceRow4Item1 == 3 && mysteryColdHeartBool -> {
                mysteryColdHeartBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Cold Heart")
                    intent.putExtra("Description", "Buy 5 lives and have ice ultimate skilled.")
                    startActivity(intent)
                }, 150)
            }
            challengesKilled == 3 && challengesKilledBool -> {
                challengesKilledBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Challenged")
                    intent.putExtra("Description", "Kill 3 challenges.")
                    startActivity(intent)
                }, 150)
            }
            mysteryWokeCount > 2 && mysteryWokeBool -> {
                mysteryWokeBool = false
                paused = true
                mHandler.postDelayed({
                    intent = Intent(this, MysteryMessage::class.java)
                    intent.putExtra("Title", "Get Woke, Go Broke")
                    intent.putExtra("Description", "Welcome to Wokistan (it/zher).")
                    startActivity(intent)
                }, 150)
            }
        }

             */
        }

    fun updateViewModel () {

        // update ViewModel--------------------------------------------

        // update gains for UI ----------------------------------------------
        if (goldGain) goldGainCount++
        if (goldGainCount >= 120) {
            goldGainCount = 0
            goldGain = false
        }
        if (diamondsGain) diamondsGainCount++
        if (diamondsGainCount >= 120) {
            diamondsGainCount = 0
            diamondsGain = false
        }
        if (interestGain) interestGainCount++
        if (interestGainCount >= 120) {
            interestGainCount = 0
            interestGain = false
        }
        if (upgradePointsGain) upgradePointsGainCount++
        if (upgradePointsGainCount >= 120) {
            upgradePointsGainCount = 0
            upgradePointsGain = false
        }
        if (itemPointsGain) itemPointsGainCount++
        if (itemPointsGainCount >= 120) {
            itemPointsGainCount = 0
            itemPointsGain = false
        }
        if (itemChanceGain) itemChanceGainCount++
        if (itemChanceGainCount >= 120) {
            itemChanceGainCount = 0
            itemChanceGain = false
        }
        if (itemQualityGain) itemQualityGainCount++
        if (itemQualityGainCount >= 120) {
            itemQualityGainCount = 0
            itemQualityGain = false
        }
        if (bagSizeGain) bagSizeGainCount++
        if (bagSizeGainCount >= 120) {
            bagSizeGainCount = 0
            bagSizeGain = false
        }
        if (damageGain) damageGainCount++
        if (damageGainCount >= 120) {
            damageGainCount = 0
            damageGain = false
        }
        if (phyDamageGain) phyDamageGainCount++
        if (phyDamageGainCount >= 120) {
            phyDamageGainCount = 0
            phyDamageGain = false
        }
        if (mgcDamageGain) mgcDamageGainCount++
        if (mgcDamageGainCount >= 120) {
            mgcDamageGainCount = 0
            mgcDamageGain = false
        }
        if (spdGain) spdGainCount++
        if (spdGainCount >= 120) {
            spdGainCount = 0
            spdGain = false
        }
        if (critChanceGain) critChanceGainCount++
        if (critChanceGainCount >= 120) {
            critChanceGainCount = 0
            critChanceGain = false
        }
        if (critDamageGain) critDamageGainCount++
        if (critDamageGainCount >= 120) {
            critDamageGainCount = 0
            critDamageGain = false
        }
        if (multiCritGain) multiCritGainCount++
        if (multiCritGainCount >= 120) {
            multiCritGainCount = 0
            multiCritGain = false
        }
        if (hitGain) hitGainCount++
        if (hitGainCount >= 120) {
            hitGainCount = 0
            hitGain = false
        }
        if (armorPenGain) armorPenGainCount++
        if (armorPenGainCount >= 120) {
            armorPenGainCount = 0
            armorPenGain = false
        }
        if (magicArmorPenGain) magicArmorPenGainCount++
        if (magicArmorPenGainCount >= 120) {
            magicArmorPenGainCount = 0
            magicArmorPenGain = false
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

         readLockEnemy.lock ()
        try {
        if (enemyClick) {
            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()
                if (it.selected) {
                    enemyMaxHp = it.maxHp
                    enemyHp = it.hp
                    enemyArmor = it.armor
                    // TODO it.armorReduced
                    enemyArmorReduction = 100 - (100 * armorPen(it, Tower(0f,0f, 0f,0f,0f,0f)))
                    enemyMagicArmor = it.magicArmor
                    enemyMagicArmorReduction = 100 - (100 * magicPen(it, Tower(0f,0f, 0f,0f,0f,0f)))
                    enemyEvade = it.evade
                    enemyHpReg = it.hpReg
                    enemySpd = it.speed
                    enemyShield = it.shield
                    enemyShieldMax = it.shieldMax
                    enemyManaShield = it.manaShield
                    enemyManaShieldMax = it.manaShieldMax
                    enemyType = it.name
                    break
                }
            }
        }
        }finally {
            readLockEnemy.unlock()
        }

    }



    fun updateRandomThings () {

        writeLockEnemy.lock ()
        try {

            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                // enemy hp updates
                var hpDebuffPlace = if (it.hpRegDebuff + hpRegDebuffGlobal > 0.9f) 0.9f else it.hpRegDebuff + hpRegDebuffGlobal
                if (it.hpReg > 0 && it.hp < it.maxHp && it.hp > 0) it.hp += (it.hpReg * (1 - hpDebuffPlace))
                if (it.hp > it.maxHp) it.hp = it.maxHp

                // movement speed
                if (it.baseSpeed < 0.2f && !it.entangled && it.throwBoulderHitAlreadyEffected && !it.feared && it.itemLassoAlreadyAffected >= 0 && it.markOfTheButterflyStun < 1) it.baseSpeed =
                    0.2f
                it.speed = (it.baseSpeed + it.extraSpeed)

                // heal
                if (levelList.contains("healer")) {
                    if (it.name == "healer") {
                        healCounter ++
                        if (healCounter >= 50){
                            for (it in enemyList.shuffled()){
                                if (it.hp < it.maxHp) {
                                    it.hp += (it.maxHp/5) * (1 - it.hpRegDebuff - hpRegDebuffGlobal)
                                    healCounter = 0
                                    break
                                }
                            }
                        }

                    }
                }

                // dmg debuff
                var butterflyDebuffEnemyDmgAlreadyEffectedPlace = if (it.butterflyDebuffEnemyDmgAlreadyEffected) towerList[it.butterflyDebuffEnemyDmgTowerId].butterflyDebuffEnemyDmg else 0f

                it.dmgTakenDebuff = 1f + butterflyDebuffEnemyDmgAlreadyEffectedPlace

                // gold and xp debuff
                var butterflyDebuffEnemyGoldXpAlreadyEffectedPlace = if (it.butterflyDebuffEnemyGoldXpAlreadyEffected) towerList[it.butterflyDebuffEnemyGoldXpTowerId].butterflyDebuffEnemyGoldXp else 0f
                it.xpDropDebuff = 1f + butterflyDebuffEnemyGoldXpAlreadyEffectedPlace
                it.goldDropDebuff = 1f + butterflyDebuffEnemyGoldXpAlreadyEffectedPlace

                it.overallXp = it.xpEnemy * it.xpDropDebuff
                it.overallGold = it.xpEnemy * it.goldDropDebuff * midnightMadnessMidasGold

            }
        }finally {
            writeLockEnemy.unlock()
        }

        if (towerList.size > 0) {
            readLockTower.lock()
            try {
                poisonTowerHighestDmg.overallTowerSpellDmg = 0f
                var towerListIterator = towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.itemListBag.contains(epoison)){
                        if (tower.overallTowerSpellDmg > poisonTowerHighestDmg.overallTowerSpellDmg)
                        poisonTowerHighestDmg = tower
                    }
                    if (tower.itemListBag.contains(ewizard)){
                        if (tower.overallTowerSpellDmg > wizardTowerHighestDmg.overallTowerSpellDmg)
                            wizardTowerHighestDmg = tower
                    }
                }
            } finally {
                readLockTower.unlock()
            }
        }
    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

    private fun updateShots () {

        // Ice Talent

        if (Companion.iceShard) {
            when (Companion.iceShardSpeed) {
                135 -> Companion.iceShardCounter3 = 1
                120 -> iceShardCounter3 = 2
                105 -> iceShardCounter3 = 3
                90 -> iceShardCounter3 = 4
                75 -> iceShardCounter3 = 5
            }
        }
        if (Companion.iceShard && iceShardCounter2 < iceShardCounter3) {
            writeLockIce.lock()
            try {
                iceShardCounter += 1

                if (iceShardCounter == iceShardSpeed) {
                    shootListIce.add(ShootIceTalent(0.0))
                    shootListIce.add(ShootIceTalent(1.5))
                    shootListIce.add(ShootIceTalent(3.0))
                    shootListIce.add(ShootIceTalent(4.5))
                    shootListIce.add(ShootIceTalent(6.0))
                    shootListIce.add(ShootIceTalent(7.5))
                    shootListIce.add(ShootIceTalent(9.0))
                    shootListIce.add(ShootIceTalent(10.5))
                    iceShardCounter = 0
                    iceShardCounter2 += 1
                }
            } finally {
                writeLockIce.unlock()
            }
        }

        if (Companion.iceShard) {
            iceShardCounter4++
            if (iceShardCounter4 >= 3) {
                writeLockIce.lock()
                try {
                    var shootListIceIterator = shootListIce.listIterator()
                    while (shootListIceIterator.hasNext()) {
             //    for (it in shootListIce)
              //          CoroutineScope(Dispatchers.Default).launch {
                        var it = shootListIceIterator.next()
                        if (!it.hit) crossesShard(it)
                        }
                } finally {
                    writeLockIce.unlock()
                }
            }
        }

        // poison talent
        if (poisonCloud > 0 && poisonTowerCount > 0) {
            writeLockPoison.lock()
            try {
                poisonCloudCounter += 1
                if (poisonCloudCounter == 600) {
                    var shootListPoisonIterator = shootListPoison.listIterator()
                    var shootpoisontalentbool = ShootPoisonTalent()
                    shootpoisontalentbool.poisonCloud = TowerRadius(poisonTowerHighestDmg.towerRange.x, poisonTowerHighestDmg.towerRange.y, 50.0f)
                    shootListPoisonIterator.add(shootpoisontalentbool)
                    poisonCloudCounter = 0
                }
                if (poisonCloudCounter == 550 && shootListPoison.isNotEmpty()) {
                    var shootListPoisonIterator = shootListPoison.listIterator()
                    while (shootListPoisonIterator.hasNext()) {
                        var it = shootListPoisonIterator.next()
                        it.broken = 1
                    }
                }
                poisonCloudPlaceholderCounter++
                if (poisonCloudPlaceholderCounter >= 10) {
                    poisonCloudPlaceholderCounter = 0
               //     for (it in shootListPoison)
                    var shootListPoisonIterator = shootListPoison.listIterator()
                    while (shootListPoisonIterator.hasNext()) {
               //         CoroutineScope(Dispatchers.Default).launch {
                            var it = shootListPoisonIterator.next()
                            var poisonCloudPlaceholder = it.poisonCloud
                            crossesPoison(poisonCloudPlaceholder)
                        }
                }
            } finally {
                writeLockPoison.unlock()
            }
        }

        // Tornado

        writeLockTornado.lock()
        readLockTower.lock()
        try {
            var towerListIterator = towerList.listIterator()
            while (towerListIterator.hasNext()) {
                var tower = towerListIterator.next()
                if (tower.tornadoRadius > 0) {

                    tower.tornadoCounter++
                    if (tower.tornadoCounter >= 300) {
                        tower.tornadoCounter = 0
                        if (enemyList.isNotEmpty()) {
                            var shootListTornadoIterator = shootListTornado.listIterator()
                            var shootTornadoTalentPlace = ShootTornadoTalent()
                            shootTornadoTalentPlace.tornadoRadius =
                                TowerRadius(tower.towerRange.x, tower.towerRange.y, tower.tornadoRadius)
                            shootListTornadoIterator.add(shootTornadoTalentPlace)
                        }
                    } else if (tower.tornadoCounter == 100 && shootListTornado.isNotEmpty()) {
                        var shootListTornadoIterator = shootListTornado.listIterator()
                        while (shootListTornadoIterator.hasNext()) {
                            var it = shootListTornadoIterator.next()
                            it.broken = 1
                            it.randomEnemyTornadoBool = true
                        }
                    }

                    tornadoPlaceholderCounter++
                    if (tornadoPlaceholderCounter >= 2) {
                        tornadoPlaceholderCounter = 0
                        //     for (it in shootListTornado)
                        //     CoroutineScope(Dispatchers.Default).launch {
                        var shootListTornadoIterator = shootListTornado.listIterator()
                        while (shootListTornadoIterator.hasNext()) {
                            var it = shootListTornadoIterator.next()
                            var tornadoCloudPlaceholder = it.tornadoRadius
                            crossesTornado(tornadoCloudPlaceholder)
                        }
                    }
                }
            }
            } finally {
                writeLockTornado.unlock()
                readLockTower.unlock()
            }



        // mine talent
        if (wizardMine) {
            writeLockMine.lock()
            try {
                wizardMineCounter++
                if (wizardMineCounter >= Companion.wizardMineTimer) {
                    wizardMineCounter = 0
                    if (wizardMinePlaced < 21) {
                        var shootListMineIterator = shootListMine.listIterator()
                        shootListMineIterator.add(ShootMineTalent())
                        wizardMinePlaced += 1
                    }
                }
                minePlaceholderCounter++
                if (minePlaceholderCounter >= 3) {
                    minePlaceholderCounter = 0
                    var shootListMineIterator = shootListMine.listIterator()
                    while (shootListMineIterator.hasNext()) {
                        var it = shootListMineIterator.next()
              //          for (it in shootListMine)
              //          CoroutineScope(Dispatchers.Default).launch {
                            if (it.broken == false) {
                                var mineRadiusPlaceholder = it.mineRadius
                                if (crossesMine(mineRadiusPlaceholder)) {
                                    wizardMinePlaced -= 1
                                    it.broken = true
                                }
                            }
                        }
                }
            } finally {
                writeLockMine.unlock()
            }
        }


        // update shot
        writeLockShot.lock()
        readLockTower.lock()
        try {
                  var shootListIterator = shootList.listIterator()
                  while (shootListIterator.hasNext()) {
                      var it = shootListIterator.next()
             //   for (it in shootList)
             //   CoroutineScope(Dispatchers.Default).launch {

                    if (it.broken != 1) {
                        if (it.chainLightning) {
                            if (it.bounceLeft > 0) {
                                if (crossesChainLightning(it)) {
                                    it.alreadyBouncedReset = false
                                    it.alreadyBounced += 1
                                    it.bounceLeft -= 1
                                    if (inBounceRange(it.bullet) <= 1) it.broken = 1
                                    if (it.bounceLeft == 0) it.broken = 1
                                }
                            }
                        } else if (towerList[it.towerId].towerPrimaryElement == "moon") {
                            if (it.bounceLeft > 0) {
                                    if (crosses2(it)) {
                                        rotationBulletY = it.bullet.y
                                        rotationBulletX = it.bullet.x
                                        it.alreadyBouncedReset = false
                                        towerList[it.towerId].randomEnemyForShotBool = true
                                        it.alreadyBounced += 1
                                        it.bounceLeft -= 1
                                        if (inBounceRange(it.bullet) <= 1) it.broken = 1
                                        if (it.bounceLeft == 0) it.broken = 1
                                    }
                            } else {
                                it.broken = 1
                            }
                        } else {

                            if (crosses2(it)) {
                                it.broken = 1
                                towerList[it.towerId].randomEnemyForShotBool = true
                            }


                            if (enemyList.isEmpty()) {
                                it.broken = 1
                                towerList[it.towerId].randomEnemyForShotBool = true
                            }

                        }
                        }
                }
        } finally {
            writeLockShot.unlock()
            readLockTower.unlock()
        }

    }

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

         private fun updateShit () {


             crossesAll()

             // lives
             if (mapMode == 2) {
                 lives = enemyList.size + enemySizeBoss
                 enemySizeBoss = 0
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


             writeLockTower.lock()
             try {
                 var towerListIterator = Companion.towerList.listIterator()
                 while (towerListIterator.hasNext()) {
                     var it = towerListIterator.next()

                     var bagSizeElementCountPlace = 0

                     if (it.itemListBag.contains(epoison)) {
                         bagSizeElementCountPlace ++
                         poisonTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(ewizard)) {
                         bagSizeElementCountPlace++
                         wizardTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(eice)) {
                         bagSizeElementCountPlace++
                         iceTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(efire)) {
                         bagSizeElementCountPlace++
                         fireTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(emoon)) {
                         bagSizeElementCountPlace++
                         moonTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(ewind)) {
                         bagSizeElementCountPlace++
                         windTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(eutils)) {
                         bagSizeElementCountPlace++
                         utilsTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(edark)) {
                         bagSizeElementCountPlace++
                         darkTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(ebutterfly)) {
                         bagSizeElementCountPlace++
                         butterflyTowerCountPlace ++
                     }
                     if (it.itemListBag.contains(eearth)) {
                         bagSizeElementCountPlace++
                         earthTowerCountPlace ++
                     }

                     it.bagSizeElementCount = bagSizeElementCountPlace


                     var extraDmgAura = 0f
                     var extraSpdAura = 0f
                     var extraItemChance = 0f
                     var extraItemQuality = 0f
                     var markOfTheButterflySpdBoostTowersNumberPlace = 0

                     var towerListIteratorZ = Companion.towerList.listIterator()
                     while (towerListIteratorZ.hasNext()) {
                         var tower = towerListIteratorZ.next()
                         if (it != tower) {
                             if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                                 if (tower.earthExtraTowerDmg > 0) extraDmgAura += tower.earthExtraTowerDmg
                                 if (tower.windExtraTowerSpd > 0) extraSpdAura += tower.windExtraTowerSpd
                                 if (tower.itemChanceAura > 0) extraItemChance += tower.itemChanceAura
                                 if (tower.itemQualityAura > 0) extraItemQuality += tower.itemQualityAura
                                 if (it.markOfTheButterflySpdBoost > 0 && it.towerPrimaryElement == "butterfly") markOfTheButterflySpdBoostTowersNumberPlace ++

                             }
                         }
                     }
                     it.markOfTheButterflySpdBoostTowersNumber = markOfTheButterflySpdBoostTowersNumberPlace

                     // level
                     when (it.xpTower.toInt()){
                         in 0..it.xpGoal2.toInt() -> {}
                         else -> {
                             it.towerLevel ++
                             it.talentPoints ++
                             it.towerRarityMultiplier += 0.02f
                             itemListBagAddMultiplier(it)
                             var xp2 = it.xpGoal2
                             it.xpGoal1 = xp2
                             it.xpGoal2 = (xp2 * 1.75f)
                         }
                     }

                     if (it.towerLevelBool){
                         it.towerLevelBool = false
                         it.towerLevel += 5
                         it.talentPoints += 5
                         it.towerRarityMultiplier += 0.1f
                         repeat(5){
                                 _ -> itemListBagAddMultiplier(it)
                         }
                         var xp2 = it.xpGoal2
                         it.xpGoal1 = (xp2 * 1.75f) * 4
                         it.xpGoal2 = (xp2 * 1.75f) * 5
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

                     it.overallTowerSpd = it.speed - (it.speed * ((overallSpdMultiplier + extraSpdAura) / 100))

                     var talentBonusSpeed = 0f
                     if (it.itemListBag.contains(ewind)) talentBonusSpeed += Companion.windTalentBonusSpeed
                     Companion.levelScalerSpeedBool =
                         (it.bonusTowerSpeed + talentBonusSpeed) * levelScalerSpeed
                     when {
                         (Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 300 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 2f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 4f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 16f)) +
                                             (((it.overallTowerSpd * ((Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 300f) / 100f)) / 32f)))
                         }
                         (Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 200 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 2f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 4f)) +
                                             (((it.overallTowerSpd * ((100f) / 100f)) / 8f)) +
                                             (((it.overallTowerSpd * ((Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 200f) / 100f)) / 16f)))
                         }
                         (Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 100 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f)) / 2f) +
                                             (((it.overallTowerSpd * ((50f) / 100f)) / 4f)) +
                                             (((it.overallTowerSpd * ((Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 100f) / 100f)) / 8f)))
                         }
                         (Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) > 50 -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((50f / 100f) / 2f)) +
                                             ((it.overallTowerSpd * ((Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber - 50f) / 100f)) / 4f))
                         }
                         else -> {
                             it.towerAttackSpeed = it.overallTowerSpd -
                                     ((it.overallTowerSpd * ((Companion.levelScalerSpeedBool + it.bonusSpeedWindTalentPercent + it.markOfTheButterflySpdBoostActiveNumber) / 100f)) / 2f)
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
                     if (it.itemListBag.contains(eearth)) it.towerAttackSpeed * 2

                     if (it.towerAttackSpeed <= 3) it.towerAttackSpeed = 3f
                     it.towerAttackSpeedShow = (it.towerAttackSpeed / 60)

                     // tower crit
                     var talentFireBonusCrit= 0f
                     if (it.itemListBag.contains(efire)) talentFireBonusCrit += 10f
                     var levelScalerCritBool = (it.bonusCrit + talentFireBonusCrit) * levelScalerCrit
                     it.overallCrit = ((((it.crit + levelScalerCritBool) * 0.03f) / (1 + (0.03f * (it.crit + levelScalerCritBool + 50)))) * 100)

                     // crit damage
                     var levelScalerCritDmgBool = 0f
                     var talentFireBonusCritDmg = 0f
                     if (it.itemListBag.contains(efire)) talentFireBonusCritDmg += 0.5f
                     if (it.fireTalentBonusCritDmg > 0) talentFireBonusCritDmg += it.fireTalentBonusCritDmg
                     levelScalerCritDmgBool = (it.bonusCritDmg + talentFireBonusCritDmg) * levelScalerCritDmg
                     if (it.itemListBag.contains(efire)) levelScalerCritDmgBool += (it.bonusCritFire * it.critCounter)
                     it.overallCritDmg = it.critDmg + levelScalerCritDmgBool

                     // multicrit
                     if (it.fireRow4Item1 == 3) it.fireUltimateMulticritBonus =
                         (floor(level / 50f)).toInt()
                     it.overallMulticrit =
                         it.bonusmultiCrit + it.fireUltimateMulticritBonus

                     // tower dmg
                     if (mapMode != 2) it.bonusDamageMultiplyerItemLastStance =
                         ((8f - lives) * it.itemLastStance)
                     it.overallDamageMultiplyer =
                         1 + it.bonusDamageMultiplyer + it.bonusDamageMultiplyerItemLastStance + globalDmgMultiplier

                     if (day) it.overallTowerDmg =
                         (it.dmg + it.bonusTowerDmg + itemsDmg(it)) * (it.overallDamageMultiplyer + extraDmgAura)
                     else it.overallTowerDmg =
                         (it.dmg + it.bonusTowerDmg + itemsDmg(it)) * (it.overallDamageMultiplyer + extraDmgAura) * it.damageMultiplyerNight
                     it.overallTowerDmgBool = it.overallTowerDmg
                     it.overallTowerDmg *= bigNumberScaler

                     // physical dmg
                     var talentPhysicalDmgMultiplyer = 1f
                     if (it.itemListBag.contains(eearth)) talentPhysicalDmgMultiplyer += it.earthTalentPhyDmgMultiplier
                     var talentBonusPhysicalDmg = 0f
                     if (it.itemListBag.contains(eearth)) talentBonusPhysicalDmg += earthTalentBonusPhysicalDmg
                     it.overallTowerPhysicalDmg =
                         (it.phyDmg + it.overallTowerDmgBool + it.bonusPhysicalDmg + talentBonusPhysicalDmg) * talentPhysicalDmgMultiplyer * bigNumberScaler

                     // spell dmg
                     var spellDmgTalent = 0f
                     if (it.talentWizardLvlToDmg) spellDmgTalent += ((it.towerLevel * 3) /100)
                     it.overallTowerSpellDmg =
                         (it.mgcDmg + it.overallTowerDmgBool + (it.overallTowerDmgBool * (globalBonusSpellDmgPercent / 100)) + it.bonusSpellDamage) * (it.overallSpellDmgMultiplyer + spellDmgTalent) * bigNumberScaler

                     // hit chance
                     var talentBonusHitChance = 0f
                     if (it.itemListBag.contains(eearth)) talentBonusHitChance += it.earthTalentBonusHitChance
                     it.hitChance =
                         100.0f + it.itemBonusHitChance + it.bonusHitChance + talentBonusHitChance

                     // item chance
                     it.overallItemChance =
                         (itemChance + ((itemChance * ((it.bonusItemChance + globalBonusItemChance + extraItemChance) / 100)))) * levelScalerItemChance

                     // item quality
                     it.overallItemQuality = (it.bonusItemQuality + globalBonusItemQuality + extraItemQuality) * levelScalerItemQuality

                     // tower range
                     var talentBonusRange = 0f
                     if (it.windTowerBonusTowerRange > 0) talentBonusRange += it.windTowerBonusTowerRange
                     else talentBonusRange += globalBonusTowerRange
                     it.towerR = it.towerRange.r + talentBonusRange + it.towerBonusRange

                     // armor pen
                     it.overallBonusArmorPen = it.bonusArmorPen + globalArmorPen

                     // magic pen
                     it.overallBonusMagicPen = it.bonusMagicPen + globalMagicPen

                     // dmg immune
                     it.overallDmgImmune = 1 + (it.bonusDmgImmune)

                     // evade night
                     if (day) evadeNight = 0f
                     else {
                         if (Companion.moonTalentEvadeNight) evadeNight = 0f
                         else evadeNight = (ceil(level / 10.0f))
                     }

                     // TODO            // single target
                     // TODO            if (!multishot && !shotBounce && splashRange < 1) isSingleTarget = true
                     // TODO             else isSingleTarget = false

                 }

                 poisonTowerCount = poisonTowerCountPlace
                 wizardTowerCount = wizardTowerCountPlace
                 iceTowerCount = iceTowerCountPlace
                 fireTowerCount = fireTowerCountPlace
                 moonTowerCount = moonTowerCountPlace
                 windTowerCount = windTowerCountPlace
                 utilsTowerCount = utilsTowerCountPlace
                 darkTowerCount = darkTowerCountPlace
                 butterflyTowerCount = butterflyTowerCountPlace
                 earthTowerCount = earthTowerCountPlace

                 } finally {
                     writeLockTower.unlock()
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
                gold += (pos.specialFloat * 0.02f)
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

            if (towerList.size > 0) {

                writeLockEnemy.lock()
                writeLockTower.lock()
                Companion.writeLockDisplay.lock()
                try {
                    var enemyListIterator = enemyList.listIterator()
                    while (enemyListIterator.hasNext()) {
                        var it = enemyListIterator.next()
                        //       for (it in enemyList)
                        //           CoroutineScope(Dispatchers.Default).launch {

                        // stats ---------------------------------------------------------------------------------------

                        // lives
                        if (mapMode == 2) {
                            if (it.name == "boss" || it.name == "challenge") {
                                enemySizeBoss += 3
                            }
                        }

                        //---------------------------------------------------------------------------------------


                        // ice nova

                        if (it.iceNovaAlreadyAffected) it.iceNovaAlreadyAffectedCounter++
                        if (it.iceNovaAlreadyAffectedCounter >= 75) {
                            it.baseSpeed += it.iceNovaSpeedReduce
                            it.iceNovaSpeedReduce = 0f
                            it.iceNovaAlreadyAffectedCounter = 0
                            it.iceNovaAlreadyAffected = false
                        }

                        // ice Debuff

                        if (it.iceDebuff == 1) {
                            if (it.iceAlreadyAffected == 0) {
                                if (it.name == "speed") it.extraSpeed = 0.0f       // counter speed
                                if (it.name == "immune") it.iceSlowEachSpeedReduce =
                                    (it.baseSpeed * (towerList[it.iceDebuffTowerId].slowEach / 100)) * towerList[it.iceDebuffTowerId].overallDmgImmune.toFloat()
                                else it.iceSlowEachSpeedReduce =
                                    (it.baseSpeed * (towerList[it.iceDebuffTowerId].slowEach / 100)).toFloat()
                                it.baseSpeed -= it.iceSlowEachSpeedReduce
                                it.iceDebuff += 1
                                it.iceAlreadyAffected = 1
                                if (towerList[it.iceDebuffTowerId].experienceSlow) {
                                    var xpGain = (1 / 100f)
                                    towerList[it.iceDebuffTowerId].xpTower += xpGain
                                    towerExperienceGainUtilAura(xpGain, it.iceDebuffTowerId)
                                    if (towerList[it.iceDebuffTowerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, it.iceDebuffTowerId)
                                }
                            } else it.iceDebuff += 1
                        } else if (it.iceDebuff > 90) {
                            it.iceDebuff = 0
                            it.iceAlreadyAffected = 0
                            it.baseSpeed += it.iceSlowEachSpeedReduce
                            it.iceSlowEachSpeedReduce = 0f
                        } else if (it.iceDebuff > 0) it.iceDebuff += 1

                        // dark talent dmg debuff
                        if (it.darkMoreDmgDebuff == 1) {
                            it.darkMoreDmgDebuff++
                            it.darkMoreDmgDebuffComplete =
                                it.darkMoreDmgDebuffStacks * towerList[it.darkMoreDmgDebuffTowerId].darkDmgDebuff
                        } else if (it.darkMoreDmgDebuff >= 180) {
                            it.darkMoreDmgDebuff = 0
                            it.darkMoreDmgDebuffStacks = 0
                        } else if (it.darkMoreDmgDebuff > 1) {
                            it.darkMoreDmgDebuffComplete =
                                it.darkMoreDmgDebuffStacks * towerList[it.darkMoreDmgDebuffTowerId].darkDmgDebuff
                            it.darkMoreDmgDebuff++
                        }

                        // antiheal debuffs from items
                        if (it.antihealDebuff == 1) {
                            it.antihealDebuff++
                            it.antihealDebuffActive = towerList[it.antihealDebuffTowerId].bonusAntiHeal
                        } else if (it.darkMoreDmgDebuff >= 180) {
                            it.antihealDebuff = 0
                            it.antihealDebuffActive = 0f
                        } else if (it.antihealDebuff > 1) it.antihealDebuff++

                        // ice slow Extra

                        if (towerList[it.iceDebuffExtraTowerId].slowExtra > 0) {
                            if (it.iceDebuffExtra == 1 && it.iceExtraAlreadyAffected == 0) {
                                when ((0..99).random()) {
                                    in 0..towerList[it.iceDebuffExtraTowerId].slowExtraChance -> {
                                        if (it.name == "immune") it.iceSlowExtraSpeedReduce =
                                            (it.baseSpeed * (towerList[it.iceDebuffExtraTowerId].slowExtra / 100)) * it.iceDebuffExtraDR * towerList[it.iceDebuffExtraTowerId].overallDmgImmune.toFloat()
                                        else it.iceSlowExtraSpeedReduce =
                                            (it.baseSpeed * (towerList[it.iceDebuffExtraTowerId].slowExtra / 100)) * it.iceDebuffExtraDR.toFloat()
                                        it.baseSpeed -= it.iceSlowExtraSpeedReduce
                                        it.iceExtraAlreadyAffected = 1
                                        it.iceDebuffExtra += 1
                                        it.iceDebuffExtraDR *= 0.66f
                                        if (it.iceDebuffExtraDR <= 0.1f) it.iceDebuffExtraDR = 0f
                                        if (towerList[it.iceDebuffExtraTowerId].experienceSlow) {
                                            var xpGain = (1 / 100f)
                                            towerList[it.iceDebuffExtraTowerId].xpTower += xpGain
                                            towerExperienceGainUtilAura(xpGain, it.iceDebuffExtraTowerId)
                                            if (towerList[it.iceDebuffExtraTowerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, it.iceDebuffExtraTowerId)
                                        }
                                    }
                                    in towerList[it.iceDebuffExtraTowerId].slowExtraChance..99 -> it.iceDebuffExtra =
                                        0
                                }
                            } else if (it.iceDebuffExtra > 90) {
                                it.iceDebuffExtra = 0
                                it.iceExtraAlreadyAffected = 0
                                it.baseSpeed += it.iceSlowExtraSpeedReduce
                                it.iceSlowExtraSpeedReduce = 0f
                            } else if (it.iceExtraAlreadyAffected == 1) it.iceDebuffExtra += 1
                            else if (it.iceDebuffExtra > 1) it.iceDebuffExtra += 1
                        }

                        // fire Debuff

                        if (it.fireDebuff == 1 || it.fireDebuff == 21 || it.fireDebuff == 41 || it.fireDebuff == 61 || it.fireDebuff == 81 || it.fireDebuff == 101) {

                            if (it.hp > 0) {
                                var dmg = 0f

                                if (it.name == "boss" || it.name == "challenge") {
                                    dmg =
                                        (((it.maxHp * towerList[it.fireDebuffTowerId].fireBurnTalentDmg) / 2) * it.winddebuffincreased * it.fireDebuffDR * 2) * magicPen(it, towerList[it.fireDebuffTowerId])
                                } else {
                                    if (it.name == "immune") dmg =
                                        ((it.maxHp * towerList[it.fireDebuffTowerId].fireBurnTalentDmg) * it.winddebuffincreased * it.fireDebuffDR * towerList[it.fireDebuffTowerId].overallDmgImmune * 2) * magicPen(it, towerList[it.fireDebuffTowerId])
                                    else dmg =
                                        (((it.maxHp * towerList[it.fireDebuffTowerId].fireBurnTalentDmg)) * it.winddebuffincreased * it.fireDebuffDR * 2) * magicPen(it, towerList[it.fireDebuffTowerId])
                                }
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
                                    var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, -50, paintBurnDmgDone, -30, -15))
                                }

                                if (it.hp < 0){
                                    it.killerId = it.fireDebuffTowerId
                                    dead(it)
                                }
                            }
                            it.fireDebuff += 1
                        } else if (it.fireDebuff >= 102) {
                            it.fireDebuffDR *= 0.75f
                            if (it.fireDebuffDR <= 0.1f) it.fireDebuffDR = 0f
                            it.fireDebuff = 0
                        } else if (it.fireDebuff > 0) it.fireDebuff += 1


                        var sunburnOnlyOne = 0
                        var towerListIterator = Companion.towerList.listIterator()
                        while (towerListIterator.hasNext()) {
                            var tower = towerListIterator.next()

                            // ice aura
                            if (tower.slowAura > 0) {
                                if (tower.itemListBag.contains(eice)) {
                                    if (tower.crossesAllList.contains(it) && it.iceAuraAlreadyAffected == 0) {
                                        if (it.name == "immune") it.iceAuraSpeedReduce =
                                            (it.baseSpeed * (tower.slowAura / 100) * tower.overallDmgImmune).toFloat()
                                        else it.iceAuraSpeedReduce =
                                            (it.baseSpeed * (tower.slowAura / 100)).toFloat()
                                        it.baseSpeed -= it.iceAuraSpeedReduce
                                        it.iceAuraAlreadyAffected = 1
                                    } else if (!tower.crossesAllList.contains(it) && it.iceAuraAlreadyAffected == 1) {
                                        it.iceAuraAlreadyAffected = 0
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
                                    it.itemLassoAlreadyAffected = 0
                                    it.itemLassoAlreadyAffectedTowerId = 0
                                    it.baseSpeed += it.itemLassoSpeedReduce
                                    it.itemLassoSpeedReduce = 0f
                                }
                            }

                            // item frost
                            if (tower.itemFrost > 0) {
                                if (it.itemFrostAlreadyAffected > 0) it.itemFrostAlreadyAffected++
                                if (it.itemFrostAlreadyAffected >= 50) {
                                    it.itemFrostAlreadyAffected = 0
                                    it.baseSpeed += it.itemFrostSpeedReduce
                                    it.itemFrostSpeedReduce = 0f
                                }
                            }

                            // fire talent sun burn
                            if (tower.sunburn > 0 && day && sunburnOnlyOne < 1) {
                                sunburnOnlyOne++
                                if (enemyList.contains(it)) {
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

                                    if (it.hp > dmg) it.hp -= dmg
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
                                (it.baseSpeed * (30f / 100f)) * towerList[it.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
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
                            it.butterflyDebuffEnemyDmg = 0
                            it.butterflyDebuffEnemyDmgAlreadyEffected = false
                        } else if (it.butterflyDebuffEnemyDmg > 0) {
                            it.butterflyDebuffEnemyDmg++
                        }

                        if (it.butterflyDebuffEnemyGoldXp == 1) {
                            it.butterflyDebuffEnemyGoldXpAlreadyEffected = true
                            it.butterflyDebuffEnemyDmg++
                        } else if (it.butterflyDebuffEnemyGoldXp >= 300) {
                            it.butterflyDebuffEnemyDmg = 0
                            it.butterflyDebuffEnemyGoldXpAlreadyEffected = false
                        } else if (it.butterflyDebuffEnemyGoldXp > 0) {
                            it.butterflyDebuffEnemyDmg++
                        }

                        // poison Debuff

                        if (it.poisonDebuff == 1 || it.poisonDebuff == 31 || it.poisonDebuff == 61 || it.poisonDebuff == 91 || it.poisonDebuff == 121
                            || it.poisonDebuff == 151 || it.poisonDebuff == 181 || it.poisonDebuff == 211
                        ) {
                            if (it.hp > 0) {
                                var dmg = 0f
                                if (it.name == "immune") dmg =
                                    ((it.poisonStack * 2f + (it.poisonStack.pow((it.poisonStack / 20)))) * ((towerList[it.poisonDebuffTowerId].overallTowerSpellDmg * magicPen(it, towerList[it.poisonDebuffTowerId])) * 0.15f) * towerList[it.poisonDebuffTowerId].stackablePoison * it.winddebuffincreased * towerList[it.poisonDebuffTowerId].overallDmgImmune)
                                else dmg =
                                    ((it.poisonStack * 2f + (it.poisonStack.pow((it.poisonStack / 20)))) * ((towerList[it.poisonDebuffTowerId].overallTowerSpellDmg * magicPen(it, towerList[it.poisonDebuffTowerId])) * 0.15f) * towerList[it.poisonDebuffTowerId].stackablePoison * it.winddebuffincreased)

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
                                    var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, -50, paintPoisonDmgDone, 30, 15))
                                }

                                if (it.hp < 0){
                                    it.killerId = it.poisonDebuffTowerId
                                    dead(it)
                                }
                            }
                            it.poisonDebuff += 1
                        } else if (it.poisonDebuff >= 212) {
                            it.poisonDebuff = 0
                            it.poisonDebuffTowerId = 0
                            it.poisonStack = 0.0f
                        } else if (it.poisonDebuff > 0) it.poisonDebuff += 1


                        // poison entangle

                        if (it.entangleOnHit == 1 && it.entangled == false) {
                            when ((0..99).random()) {
                                in 0..towerList[it.entangleOnHitTowerId].entangle -> {
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
                            it.entangleDR *= 0.66f
                            if (it.entangleDR <= 0.1f) it.entangleDR = 0f
                            it.entangled = false
                            it.entangleOnHit = 0
                            it.baseSpeed += it.entangleSpeedReduce
                            it.entangleSpeedReduce = 0f
                        } else if (it.entangled == true) it.entangleOnHit += 1

                        // throw boulder
                        if (it.throwBoulderHit >= 40 * it.throwBoulderDR) {
                            it.throwBoulderDR *= 0.66f
                            if (it.throwBoulderDR <= 0.1f) it.throwBoulderDR = 0f
                            it.throwBoulderHitAlreadyEffected = false
                            it.throwBoulderHit = 0
                            it.baseSpeed += it.throwBoulderSpeedReduce
                            it.throwBoulderSpeedReduce = 0f
                        } else if (it.throwBoulderHitAlreadyEffected) it.throwBoulderHit += 1

                        // dark slow
                        if (it.darkDebuff == true && it.darkSlowAlreadyAffected == false) {
                            it.darkDebuff = false
                            when ((0..99).random()) {
                                in 0..towerList[it.darkDebuffTowerId].slowExtraChanceDark -> {
                                    if (it.name == "speed") it.extraSpeed =
                                        0.0f       // counter speed
                                    it.baseSpeed -= (it.baseSpeed * (towerList[it.darkDebuffTowerId].slowExtraDark / 100)).toFloat()
                                    it.darkSlowAlreadyAffected = true
                                }
                            }
                        }

                        // dark fear
                        if (it.fearOnHit >= 1) {
                            if (it.fearOnHit == 1 && it.feared == false) {
                                it.fearSpeedReduce = it.baseSpeed
                                it.baseSpeed -= (it.fearSpeedReduce + 2f)
                                it.feared = true
                                it.fearOnHit += 1
                            } else if (it.feared == true && it.fearOnHit >= ((10 * towerList[it.fearTowerId].fearDuration) * it.fearDR) && (it.name == "boss" || it.name == "challenge")) {
                                it.fearDR *= 0.66f
                                if (it.fearDR <= 0.1f) it.fearDR = 0f
                                it.feared = false
                                it.fearOnHit = 0
                                it.baseSpeed += (it.fearSpeedReduce + 2f)
                                it.fearSpeedReduce = 0f
                            } else if (it.feared && it.fearOnHit >= (30 * towerList[it.fearTowerId].fearDuration) * it.fearDR) {
                                it.fearDR *= 0.66f
                                if (it.fearDR <= 0.1f) it.fearDR = 0f
                                it.feared = false
                                it.fearOnHit = 0
                                it.baseSpeed += (it.fearSpeedReduce + 2f)
                                it.fearSpeedReduce = 0f
                            } else if (it.feared == true) it.fearOnHit += 1
                        }

                        // talent poison pest
                        if (poisonTalentPest > 0) {
                            if (it.poisonTalentPestAlreadyAffected > 0) {
                                it.poisonTalentPestAlreadyAffected++
                                it.poisonTalentPestDamage++
                                if (it.poisonTalentPestDamage > 151) {
                                    it.poisonTalentPestDamage = 0
                                    it.poisonTalentPestAlreadyAffected = 0
                                    it.poisonTalentPestImmune = true
                                }
                                if (it.name == "boss" || it.name == "challenge") {
                                    if (it.poisonTalentPestDamage == 1 || it.poisonTalentPestDamage == 51 || it.poisonTalentPestDamage == 101 || it.poisonTalentPestDamage == 151) {
                                        var dmg = 0f
                                        dmg =
                                            (((it.maxHp / (50..300).random())) * poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased) * 10
                                        if (it.hp > dmg) it.hp -= dmg
                                        else {
                                            when ((0..5).random()) {
                                                0 -> {
                                                }
                                                in 1..5 -> {
                                                    for (itX in enemyList.shuffled()) {
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
                                                dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, -50, paintPestDmgDone, 0, 0))
                                        }
                                    }
                                } else {
                                    if (it.poisonTalentPestDamage == 1 || it.poisonTalentPestDamage == 51 || it.poisonTalentPestDamage == 101 || it.poisonTalentPestDamage == 151) {
                                        var dmg = 0f
                                        if (it.name == "immune") dmg =
                                            (((it.maxHp / (50..300).random())) * poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased * towerList[it.poisonDebuffTowerId].overallDmgImmune) * 30
                                        else dmg =
                                            (((it.maxHp / (50..300).random())) * poisonTalentPest * (it.poisonTalentPestDamage / 15) * it.winddebuffincreased) * 30

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
                                            when ((0..5).random()) {
                                                0 -> {
                                                }
                                                in 1..5 -> {
                                                    for (itX in enemyList.shuffled()) {
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
                                                dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, -50, paintPestDmgDone, 0, 0))
                                        }
                                    }
                                }
                            }
                            if (it.poisonTalentPestAlreadyAffected == 100 || it.poisonTalentPestAlreadyAffected == 150) {
                                when ((0..1).random()) {
                                    0 -> it.poisonTalentPestAlreadyAffected = 1
                                    1 -> {
                                        for (itX in enemyList.shuffled()) {
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
                        if (towerList[it.darkTalentLaserTowerId].darkTalentLaser > 0) {
                            if (it.darkTalentLaserAlreadyAffected >= 250) {
                                it.darkTalentLaserAlreadyAffected = 0
                                it.darkTalentLaserTowerId = 0
                            } else if (it.darkTalentLaserAlreadyAffected > 0 && towerList[it.darkTalentLaserTowerId].crossesAllList.contains(it)) {
                                it.darkTalentLaserAlreadyAffected++
                                if (it.hp > 0) {
                                    it.maxHp -= (it.maxHp * (towerList[it.darkTalentLaserTowerId].darkTalentLaser / 100))
                                    it.hp -= (it.maxHp * (towerList[it.darkTalentLaserTowerId].darkTalentLaser / 100))
                                    if (it.hp < 0) {
                                        it.killerId = it.darkTalentLaserTowerId
                                        dead(it)
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
                                    ((towerList[it.wizardBombTowerId].wizardBombDmg * bigNumberScaler) + (towerList[it.wizardBombTowerId].overallTowerSpellDmg * (0.1f + wizardBombStartItemDmg)))

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
                                    var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                }

                                if (it.hp < 0){
                                    it.killerId = it.wizardBombTowerId
                                    dead(it)
                                }
                            }
                            var enemyListIteratorZ = enemyList.listIterator()
                            while (enemyListIteratorZ.hasNext()) {
                                var otherEnemy = enemyListIteratorZ.next()
                                if (splash100(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                    if (otherEnemy.hp > 0) {
                                        var dmg = 0f
                                        dmg =
                                            ((towerList[it.wizardBombTowerId].wizardBombDmg * 0.66f * bigNumberScaler) + (towerList[it.wizardBombTowerId].overallTowerSpellDmg * (0.1f + wizardBombStartItemDmg)))

                                        if (otherEnemy.manaShield > 0) {
                                            if (otherEnemy.manaShield > dmg) {
                                                otherEnemy.manaShield -= dmg
                                                dmg = 0f
                                            } else {
                                                dmg = (dmg - otherEnemy.manaShield)
                                                otherEnemy.manaShield = 0f
                                            }
                                        }
                                        if (otherEnemy.shield > 0) dmg = 0f
                                        otherEnemy.hp -= dmg
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
                                                dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                        }
                                        if (otherEnemy.hp < 0) {
                                            otherEnemy.killerId = it.wizardBombTowerId
                                            dead(otherEnemy)
                                        }
                                    }
                                }
                            }
                        }

                        // wizard mine
                        if (wizardMine) {
                            if (it.mineAlreadyAffected) it.mineAlreadyAffectedCounter++
                            if (it.mineAlreadyAffectedCounter >= 180) {
                                it.baseSpeed += it.mineSpeedReduce
                                it.mineSpeedReduce = 0f
                                it.mineAlreadyAffected = false
                            }
                        }

                        // wizard lightning
                        if (it.wizardMissedLightningActiveHit >= 1) it.wizardMissedLightningActiveHit++
                        if (it.wizardMissedLightningActiveHit >= 20) it.wizardMissedLightningActiveHit =
                            0

                    }
                    // ---------------------------------------------------------------------------------------------------------

                    // talent poison pest
                    if (poisonTowerCount > 0) {
                        var counter = 250
                        if (poisonTowerCount == 2) counter = 225
                        else if (poisonTowerCount == 3) counter = 210
                        else if (poisonTowerCount == 4) counter = 200
                        else if (poisonTowerCount >= 5) counter = 195
                        if (poisonTalentPest > 0) poisonPestCount++
                        if (poisonTalentPest > 0 && poisonPestCount >= counter) {
                            for (it in enemyList.shuffled()) {
                                if (it.poisonTalentPestAlreadyAffected > 0 || it.poisonTalentPestImmune) {
                                } else {
                                    when ((0..3).random()) {
                                        0 -> it.poisonTalentPestAlreadyAffected = 1
                                    }
                                    poisonPestCount = 0
                                    break
                                }
                            }
                        }
                    }

                    var towerListIterator = Companion.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()

                        if (tower.spellCastCDCounter > 0) tower.spellCastCDBool = true

                        // wizard chain lightning
                        if (tower.chainLighning) {
                            tower.chainLightningCounter++
                            if (tower.spellCastCDBool) tower.chainLightningCounter += (tower.chainLightningTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            var counter = 0
                            if (wizardTowerCount == 2) counter = 10
                            else if (wizardTowerCount == 3) counter = 30
                            else if (wizardTowerCount == 4) counter = 50
                            else if (wizardTowerCount >= 5) counter = 80
                            var chainLightningTimerBool = tower.chainLightningTimer - counter
                            if (tower.chainLightningCounter >= chainLightningTimerBool) {
                                tower.chainLightningCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    var shootListPlace = Shoot()
                                    shootListPlace.chainLightning = true
                                    shootListPlace.paint.color = Color.WHITE
                                    shootListPlace.bulletSpeed = 12f
                                    shootListPlace.bullet =
                                        TowerRadius(tower.towerRange.x, tower.towerRange.y, 8f)
                                    shootListPlace.bounceLeft = tower.chainLightningBounceTargets
                                    var shootListIterator = shootList.listIterator()
                                    shootListIterator.add(shootListPlace)
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
                                            dmg =
                                                (tower.overallTowerPhysicalDmg * armorPen(it, tower)) * tower.throwBoulder

                                            if (it.shield > 0) {
                                                if (it.shield > dmg) {
                                                    it.shield -= dmg
                                                    dmg = 0f
                                                } else {
                                                    dmg = (dmg - it.shield)
                                                    it.shield = 0f
                                                }
                                            }
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
                                                    dmgDisplayList.listIterator()
                                                dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintEarthDmgDone, -30, -15))
                                            }
                                            if (it.hp < 0){
                                                it.killerId = towerList.indexOf(tower)
                                                dead(it)
                                            }
                                        }
                                        if (it.name == "speed") it.extraSpeed =
                                            0.0f       // counter speed
                                        it.throwBoulderSpeedReduce = it.baseSpeed
                                        it.baseSpeed -= it.throwBoulderSpeedReduce
                                        it.throwBoulderHitAlreadyEffected = true
                                        var enemyListIteratorZ = enemyList.listIterator()
                                        while (enemyListIteratorZ.hasNext()) {
                                            var otherEnemy = enemyListIteratorZ.next()
                                            if (splash60(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                                if (otherEnemy.throwBoulderHitAlreadyEffected) {
                                                } else {
                                                    if (otherEnemy.hp > 0) {
                                                        var dmg = 0f
                                                        dmg =
                                                            (tower.overallTowerPhysicalDmg * armorPen(it, tower)) * tower.throwBoulder * 0.75f

                                                        if (otherEnemy.shield > 0) {
                                                            if (otherEnemy.shield > dmg) {
                                                                otherEnemy.shield -= dmg
                                                                dmg = 0f
                                                            } else {
                                                                dmg = (dmg - otherEnemy.shield)
                                                                otherEnemy.shield = 0f
                                                            }
                                                        }
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
                                                                dmgDisplayList.listIterator()
                                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintEarthDmgDone, -30, -15))
                                                        }

                                                        if (otherEnemy.hp < 0){
                                                            otherEnemy.killerId = towerList.indexOf(tower)
                                                            dead(otherEnemy)
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
                        if ((tower.itemListBag.contains(eice))) {
                            tower.iceNovaCounter++
                            if (globalItemListBag.contains(Items.stid6)) iceNovaTimerStartItem =
                                tower.iceNovaTimer / 2
                            else iceNovaTimerStartItem = tower.iceNovaTimer
                            if (tower.spellCastCDBool) tower.iceNovaCounter += (iceNovaTimerStartItem * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.iceNovaCounter >= iceNovaTimerStartItem) {
                                tower.iceNovaCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList.shuffled()) {
                                        if (tower.iceNovaSpellDmg > 0) {
                                            if (it.hp > 0) {
                                                var dmg = 0f
                                                dmg =
                                                    ((tower.overallTowerSpellDmg * tower.iceNovaSpellDmg) * magicPen(it, tower))
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
                                                it.hp -= dmg
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
                                                        dmgDisplayList.listIterator()
                                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                                                }

                                                if (it.hp < 0){
                                                    it.killerId = towerList.indexOf(tower)
                                                    dead(it)
                                                }
                                            }
                                        }
                                        if (it.iceNovaAlreadyAffected) {
                                            it.baseSpeed += it.iceNovaSpeedReduce
                                            it.iceNovaSpeedReduce = 0f
                                        }
                                        if (it.name == "speed") it.extraSpeed = 0.0f       // counter speed
                                        if (it.name == "immune") it.iceNovaSpeedReduce = (it.baseSpeed * 0.7f * it.iceNovaDR) * towerList[it.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
                                        else it.iceNovaSpeedReduce = (it.baseSpeed * 0.7f * it.iceNovaDR).toFloat()
                                        it.baseSpeed -= it.iceNovaSpeedReduce
                                        it.iceNovaAlreadyAffected = true
                                        it.iceNovaDR *= 0.66f
                                        if (it.iceNovaDR <= 0.1f) it.iceNovaDR = 0f
                                        if (tower.experienceSlow) {
                                            var xpGain = (1 / 100f)
                                            tower.xpTower += xpGain
                                            towerExperienceGainUtilAura(xpGain, towerList.indexOf(tower))
                                            if (tower.experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, towerList.indexOf(tower))
                                        }
                                        //              var enemyListIteratorZ = enemyList.listIterator()
                                        //              while (enemyListIteratorZ.hasNext()) {
                                        //                  var otherEnemy = enemyListIteratorZ.next()
                                        for (otherEnemy in enemyList) {
                                            if (splash60(it.circle!!.x, it.circle!!.y, it.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                                if (otherEnemy.iceNovaAlreadyAffected) {
                                                    otherEnemy.baseSpeed += otherEnemy.iceNovaSpeedReduce
                                                    otherEnemy.iceNovaSpeedReduce = 0f
                                                }
                                                if (tower.iceNovaSpellDmg > 0) {
                                                    if (otherEnemy.hp > 0) {
                                                        var dmg = 0f
                                                        dmg =
                                                            ((tower.overallTowerSpellDmg * tower.iceNovaSpellDmg) * magicPen(otherEnemy, tower) * 0.75f)

                                                        if (otherEnemy.manaShield > 0) {
                                                            if (otherEnemy.manaShield > dmg) {
                                                                otherEnemy.manaShield -= dmg
                                                                dmg = 0f
                                                            } else {
                                                                dmg = (dmg - otherEnemy.manaShield)
                                                                otherEnemy.manaShield = 0f
                                                            }
                                                        }
                                                        if (otherEnemy.shield > 0) dmg = 0f
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
                                                                dmgDisplayList.listIterator()
                                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                                                        }

                                                        if (otherEnemy.hp < 0){
                                                            otherEnemy.killerId = towerList.indexOf(tower)
                                                            dead(otherEnemy)
                                                        }

                                                    }
                                                }
                                                if (otherEnemy.name == "speed") otherEnemy.extraSpeed = 0.0f       // counter speed
                                                if (it.name == "immune") otherEnemy.iceNovaSpeedReduce = (otherEnemy.baseSpeed * 0.8f * otherEnemy.iceNovaDR) * towerList[otherEnemy.markOfTheButterflySlowActiveTowerId].overallDmgImmune.toFloat()
                                                else otherEnemy.iceNovaSpeedReduce = (otherEnemy.baseSpeed * 0.8f * otherEnemy.iceNovaDR).toFloat()
                                                otherEnemy.baseSpeed -= otherEnemy.iceNovaSpeedReduce
                                                otherEnemy.iceNovaAlreadyAffected = true
                                                otherEnemy.iceNovaDR *= 0.66f
                                                if (otherEnemy.iceNovaDR <= 0.1f) otherEnemy.iceNovaDR = 0f
                                                if (tower.experienceSlow) {
                                                    var xpGain = (1 / 100f)
                                                    tower.xpTower += xpGain
                                                    towerExperienceGainUtilAura(xpGain, towerList.indexOf(tower))
                                                    if (tower.experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, towerList.indexOf(tower))
                                                }
                                            }
                                        }
                                        break
                                    }
                                }
                            }
                        }

                        // wizard talent bomb
                        if (tower.itemListBag.contains(Items.ewizard)) {
                            tower.wizardBombCounter++
                            if (tower.spellCastCDBool) tower.wizardBombCounter += (tower.wizardBombTimer * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                            if (tower.wizardBombCounter >= tower.wizardBombTimer) {
                                tower.wizardBombCounter = 0
                                if (tower.crossesAllList.isNotEmpty()) {
                                    tower.spellCastCDCounterHeap += 1
                                    for (it in tower.crossesAllList.shuffled()) {
                                        it.wizardBombActive = 1
                                        it.wizardBombTowerId = towerList.indexOf(tower)
                                        break
                                    }
                                }
                            }
                        }

                        // item lasso
                        if (tower.itemLasso > 0) itemLassoCount++
                        if (tower.itemLasso > 0 && itemLassoCount >= (300 / tower.itemLasso).toInt()) {

                            for (it in tower.crossesAllList.shuffled()) {
                                if (it.itemLassoAlreadyAffected != 0) {
                                } else {
                                    it.itemLassoAlreadyAffected = 1
                                    it.itemLassoAlreadyAffectedTowerId = towerList.indexOf(tower)
                                    itemLassoCount = 0
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
                                                it.fearTowerId = towerList.indexOf(tower)
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
                            repeat((2 + wizardLightningStartItemTargets)) {
                                if (enemyList.isNotEmpty()) {
                                    for (it in enemyList.shuffled()) {
                                        if (it.hp > 0) {
                                            var dmg = 0f
                                            dmg =
                                                tower.overallTowerSpellDmg * magicPen(it, tower) * tower.wizardMissedLightningDmgBoost

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
                                                    dmgDisplayList.listIterator()
                                                dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                            }

                                            if (it.hp < 0) {
                                                it.killerId = towerList.indexOf(tower)
                                                dead(it)
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
                                it.darkTalentLaserTowerId = towerList.indexOf(tower)
                                tower.darkTalentLaserCount = 0
                                break
                            }
                        }

                        // moon talent moonlight
                        if (tower.moonLight > 0 && !day && (tower.itemListBag.contains(emoon))) tower.moonLightCount++
                        if (tower.spellCastCDBool) tower.moonLightCount += (tower.moonLight * (tower.spellCastCD * tower.spellCastCDCounter)).toInt()
                        if (tower.moonLight > 0 && tower.moonLightCount >= (60 / tower.moonLight)) {
                            tower.moonLightCount = 0
                            if (tower.crossesAllList.isNotEmpty()) {
                                tower.spellCastCDCounterHeap += 1
                                for (it in tower.crossesAllList.shuffled()) {
                                    if (it.hp > 0) {
                                        if (tower.experienceMoonlight) {
                                            var xpGain = (1 * 0.05f)
                                            tower.xpTower += xpGain
                                            towerExperienceGainUtilAura(xpGain, towerList.indexOf(tower))
                                            if (tower.experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, towerList.indexOf(tower))
                                        }

                                        var dmg =
                                            ceil((0..(tower.overallTowerSpellDmg * magicPen(it, tower)).toInt()).random() / 10f) * (1 + it.talentMoonlightAlreadyAffected)

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
                                                dmgDisplayList.listIterator()
                                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintMoonDmgDone, 30, 15))
                                        }

                                        if (it.hp < 0){
                                            it.killerId = towerList.indexOf(tower)
                                            dead(it)
                                        }
                                    }
                                    it.talentMoonlightAlreadyAffected += 0.33f
                                    it.talentMoonlightDraw = 1
                                    break
                                }
                            }
                        }
                        tower.spellCastCDBool = false
                        tower.spellCastCDCounter = tower.spellCastCDCounterHeap
                        tower.spellCastCDCounterHeap = 0
                    }

                } finally {
                    writeLockEnemy.unlock()
                    writeLockTower.unlock()
                    Companion.writeLockDisplay.unlock()
                }
            }
        }

        //----------------------------------------------------------------------------------


        private fun inBounceRange(bullet: TowerRadius): Int {

            readLockEnemy.lock()
            try {

                var check = 0
                var enemyListIterator = enemyList.listIterator()
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
                readLockEnemy.unlock()
            }
        }



    //----------------------------------------------------------------------------------

        private fun towerAttack() {

            writeLockShot.lock()
            readLockEnemy.lock()
            readLockTower.lock()
            try {
                var towerListIterator = Companion.towerList.listIterator()
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
                                    var shootListIterator = shootList.listIterator()
                                    var shootListPlace = Shoot()
                                    var times = it.crossesAllList.indexOf(enemy)
                                    var towerindex = towerList.indexOf(it)
                                    shootListPlace.towerId = towerindex
                                    shootListPlace.id = times
                                    shootListPlace.multiShotBullet = true
                                    shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                                    shootListIterator.add(shootListPlace)  // add new shoot
                                }
                                GlobalScope.launch {
                                    soundPool.play(soundIds[0], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                                }
                        }
                    }
                    // normal attack
                    else {
                        if (it.timertower >= it.towerAttackSpeed && it.crossesAllList.isNotEmpty()) {
                            if (it.firstLastRandom == 0 && !it.crossesAllList.contains(it.randomEnemyForShot)) it.randomEnemyForShotSticky =
                                true
                            var shootListIterator = shootList.listIterator()
                            var shootListPlace = Shoot()
                            var towerindex = towerList.indexOf(it)
                            shootListPlace.towerId = towerindex
                            shootListPlace.bounceLeft = it.shotBounceTargets
                            shootListPlace.bullet =
                                TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                            shootListIterator.add(shootListPlace)   // add new shoot
                            GlobalScope.launch {
                                soundPool.play(soundIds[0], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                            }
                            it.timertower = 0
                        }
                    }


                    if (it.markOfTheButterflyExtraShot && it.shootCounter >= 3 && shootButterflyReserve > 0 && it.towerPrimaryElement == "butterfly") {
                        shootButterflyReserve -= 1
                        it.shootCounter = 0
                        var shootListIterator = shootList.listIterator()
                        var shootListPlace = Shoot()
                        var towerindex = towerList.indexOf(it)
                        shootListPlace.towerId = towerindex
                        shootListPlace.butterflyUltimate = true
                        shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                        shootListIterator.add(shootListPlace)   // add new shoot
                        GlobalScope.launch {
                            soundPool.play(soundIds[0], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                        }
                    } else if (it.shootCounter >= 3 && shootReserve > 0 && it.towerPrimaryElement != "wind") {
                        shootReserve -= 1
                        it.shootCounter = 0
                        var shootListIterator = shootList.listIterator()
                        var shootListPlace = Shoot()
                        var towerindex = towerList.indexOf(it)
                        shootListPlace.towerId = towerindex
                        shootListPlace.bounceLeft = it.shotBounceTargets
                        shootListPlace.bullet = TowerRadius(it.towerRange.x, it.towerRange.y, 5f)
                        shootListIterator.add(shootListPlace)   // add new shoot
                        GlobalScope.launch {
                            soundPool.play(soundIds[0], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                        }
                    }
                }
            }finally {
                writeLockShot.unlock()
                readLockEnemy.unlock()
                readLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun crosses2(bullet: Shoot): Boolean {
            var check = false
            // check if shot crosses enemy
            readLockEnemy.lock()
            writeLockShot.lock()
            try {
                var sShotList = mutableListOf<Enemy>()
                var mShotList = mutableListOf<Enemy>()
                var enemyListIterator = enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()

                    val distanceX = (bullet.bullet.x) - it.circle!!.x
                    val distanceY = (bullet.bullet.y) - it.circle!!.y

                    val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                    val sumOfRadius = bullet.bullet.r + (it.circle!!.r - 20.0)

                    if (squaredDistance <= sumOfRadius * sumOfRadius) {
                        check = true
                        if (towerList[bullet.towerId].towerPrimaryElement == "moon") {
                            it.talentMoonAlreadyHit += 1
                            bullet.bullet.x = it.circle!!.x
                            bullet.bullet.y = it.circle!!.y
                        }

                        if (towerList[bullet.towerId].towerPrimaryElement == "earth") {

                            var enemyListIteratorX = enemyList.listIterator()
                            while (enemyListIteratorX.hasNext()) {
                                var itX = enemyListIteratorX.next()
                                // check if shot crosses other enemies for splash damage
                                val distanceX2 = (bullet.bullet.x) - itX.circle!!.x
                                val distanceY2 = (bullet.bullet.y) - itX.circle!!.y

                                val squaredDistance2 =
                                    (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                                val sumOfRadius2 = (bullet.bullet.r + towerList[bullet.towerId].splashRange) + (itX.circle!!.r - 20)

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
                readLockEnemy.unlock()
                writeLockShot.unlock()
            }
        }

            private fun crossedDmgCalc(bullet: Shoot, singleShotList: MutableList<Enemy>, splashList: MutableList<Enemy>, towerId: Int) {
                // calculate damage for all enemies in splash radius

                readLockEnemy.lock()
                writeLockShot.lock()
                Companion.writeLockDisplay.lock()
                try {

                    if (towerList[towerId].towerPrimaryElement == "earth") {

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

                                if (towerList[towerId].experienceEarthHit && splashList.size > 3 && splash15) {
                                    when ((0..5).random()){
                                        0 -> {
                                            var xpGain = (1f / 2f)
                                            towerList[towerId].xpTower += xpGain
                                            towerExperienceGainUtilAura(xpGain, towerId)
                                            if (towerList[towerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, towerId)
                                        }
                                    }
                                }

                                if (bullet.sniper && itemSniperPro) dmgDone =
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

                                it.hp -= (dmgDone + towerList[towerId].particleDmg)
                                if ((dmgDone + towerList[towerId].particleDmg) > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when ((dmgDone + towerList[towerId].particleDmg).toInt()) {
                                        in 0..999 -> dmgString =
                                            (dmgDone + towerList[towerId].particleDmg).toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            ((dmgDone + towerList[towerId].particleDmg) / 1000).toInt()
                                                .toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            ((dmgDone + towerList[towerId].particleDmg) / 1000000).toInt()
                                                .toString() + "M"
                                    }
                                    var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintTowerDmgDone, 0, 0))
                                }

                                towerList[towerId].particleDmg = 0f
                                if (it.hp < 0) {
                                    if (towerList[towerId].particleDmgBool) towerList[towerId].particleDmg += (it.hp * -1)
                                    if (towerList[towerId].darkPermaKill > 0) {
                                            for (item in towerList[towerId].itemListBag) {
                                                if (item.id == 6666) {
                                                    if (it.name == "boss") {
                                                        towerList[towerId].bonusPhysicalDmg += (towerList[towerId].darkPermaKill * 5) * (lvlScaler / 20)
                                                        item.specialFloat += (towerList[towerId].darkPermaKill * 5) * (lvlScaler / 20)
                                                    } else if (it.name == "challenge") {
                                                        towerList[towerId].bonusPhysicalDmg += (towerList[towerId].darkPermaKill * 10) * (lvlScaler / 20)
                                                        item.specialFloat += (towerList[towerId].darkPermaKill * 10) * (lvlScaler / 20)
                                                    } else {
                                                        towerList[towerId].bonusPhysicalDmg += towerList[towerId].darkPermaKill * (lvlScaler / 20)
                                                        item.specialFloat += towerList[towerId].darkPermaKill * (lvlScaler / 20)
                                                    }
                                                }
                                            }
                                    }
                                    it.killerId = towerId
                                    dead(it)
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

                                if (towerList[towerId].towerPrimaryElement == "moon") {
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
                                if (bullet.sniper && itemSniperPro) dmgDone =
                                    itemSniperPro(dmgDone, it)
                                if (bullet.sniper) {
                                    dmgDone *= 3
                                    bullet.sniper = false
                                }
                                if (towerList[towerId].markOfTheButterflyDmgBoostActive) {
                                    towerList[towerId].markOfTheButterflyDmgBoostActive = false
                                    dmgDone *= towerList[towerId].markOfTheButterfly
                                }

                                if (bullet.butterflyUltimate) dmgDone * towerList[towerId].markOfTheButterflyExtraShotDmg

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

                                it.hp -= (dmgDone + towerList[towerId].particleDmg)

                                if ((dmgDone + towerList[towerId].particleDmg) > (it.maxHp / 100)) {
                                    var dmgString = "0"
                                    when ((dmgDone + towerList[towerId].particleDmg).toInt()) {
                                        in 0..999 -> dmgString =
                                            (dmgDone + towerList[towerId].particleDmg).toInt().toString()
                                        in 1000..999999 -> dmgString =
                                            ((dmgDone + towerList[towerId].particleDmg) / 1000).toInt()
                                                .toString() + "k"
                                        in 1000000..999999999 -> dmgString =
                                            ((dmgDone + towerList[towerId].particleDmg) / 1000000).toInt()
                                                .toString() + "M"
                                    }
                                    var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                    dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintTowerDmgDone, 0, 0))
                                }

                                towerList[towerId].particleDmg = 0f
                                if (it.hp <= 0) {
                                    if (towerList[towerId].particleDmgBool) towerList[towerId].particleDmg += (it.hp * -1)
                                    if (towerList[towerId].darkPermaKill > 0) {
                                            for (item in towerList[towerId].itemListBag) {
                                                if (item.id == 6666) {
                                                    if (it.name == "boss") {
                                                        towerList[towerId].bonusPhysicalDmg += (towerList[towerId].darkPermaKill * 5) * (lvlScaler / 20)
                                                        item.specialFloat += (towerList[towerId].darkPermaKill * 5) * (lvlScaler / 20)
                                                    } else if (it.name == "challenge") {
                                                        towerList[towerId].bonusPhysicalDmg += (towerList[towerId].darkPermaKill * 10) * (lvlScaler / 20)
                                                        item.specialFloat += (towerList[towerId].darkPermaKill * 10) * (lvlScaler / 20)
                                                    } else {
                                                        towerList[towerId].bonusPhysicalDmg += towerList[towerId].darkPermaKill * (lvlScaler / 20)
                                                        item.specialFloat += towerList[towerId].darkPermaKill * (lvlScaler / 20)
                                                    }
                                                }
                                            }


                                        }
                                    it.killerId = towerId
                                    dead(it)
                                }
                            }
                        }
                    }
                    splashList.removeAll(splashList)
                    singleShotList.removeAll(singleShotList)
                }finally {
                    writeLockShot.unlock()
                    readLockEnemy.unlock()
                    Companion.writeLockDisplay.unlock()
                }
            }

        //------------------------------------------------------------------------------------

        private fun dmgCalc(it: Enemy, splash15: Boolean, bullet: Shoot): Float {
            // calculate damage

                    var autoAttackDmg = 0.0f
                    var dmgDoneX = 0.0f
                    var hitX =
                        if (!day && endlessNight > 0) (((towerList[bullet.towerId].hitChance + (3 * endlessNight)) - (((100) * (((it.evade + (evadeNight - (3 * endlessNight))) * 0.06) / (1 + (0.06 * (it.evade + (evadeNight - (3 * endlessNight))))))))) * 10).toInt()
                        else ((towerList[bullet.towerId].hitChance - (((100) * (((it.evade + evadeNight) * 0.06) / (1 + (0.06 * (it.evade + evadeNight))))))) * 10).toInt()       // + 10 evade at night
                    if (hitX > 999) hitX = 998

                    when ((0..999).random()) { //evade
                        in (hitX + 1)..999 -> {
                            dmgDoneX = 0.0f
                            if (towerList[bullet.towerId].wizardMissedLightning) towerList[bullet.towerId].wizardMissedLightningActive = true
                        }
                        in 0..hitX -> {
                            when ((0..999).random()) {      //crit
                                in 0..(towerList[bullet.towerId].overallCrit * 10).toInt() -> {
                                    if (!itemBoring) {      // item cant crit
                                        autoAttackDmg =
                                            ((towerList[bullet.towerId].overallTowerPhysicalDmg * towerList[bullet.towerId].overallCritDmg) * armorPen(it, towerList[bullet.towerId])) * multicrit(towerList[bullet.towerId])
                                        if (splash15 && towerList[bullet.towerId].itemListBag.contains(Items.efire)) towerList[bullet.towerId].critCounter =
                                            0  // FireTalent
                                        if (towerList[bullet.towerId].itemListBag.contains(Items.efire) && it.fireDebuff == 0 && splash15) {
                                            it.fireDebuff = 1
                                            it.fireDebuffTowerId = bullet.towerId
                                        } // FireTalent
                                        if (towerList[bullet.towerId].experienceFireCrit) {
                                            var xpGain = (1 / 10f)
                                            towerList[bullet.towerId].xpTower += xpGain
                                            towerExperienceGainUtilAura(xpGain, bullet.towerId)
                                            if (towerList[bullet.towerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, bullet.towerId)
                                        }
                                    } else {
                                        autoAttackDmg = towerList[bullet.towerId].overallTowerPhysicalDmg * armorPen(it, towerList[bullet.towerId])
                                        if (splash15 && towerList[bullet.towerId].itemListBag.contains(Items.efire)) towerList[bullet.towerId].critCounter += 1 // FireTalent
                                    }
                                }
                                in ((towerList[bullet.towerId].overallCrit * 10).toInt() + 1)..999 -> {
                                    autoAttackDmg = towerList[bullet.towerId].overallTowerPhysicalDmg * armorPen(it, towerList[bullet.towerId])
                                    if (splash15 && towerList[bullet.towerId].itemListBag.contains(Items.efire)) towerList[bullet.towerId].critCounter += 1 // FireTalent
                                }
                            }

                            // all hits ---------------------------------------------------------
                            // all hits ---------------------------------------------------------
                            // all hits ---------------------------------------------------------


                            GlobalScope.launch {
                                soundPool.play(soundIds[1], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                            }
                            it.hit = true

                            // talent multi shot
                            if (towerList[bullet.towerId].towerPrimaryElement == "wind" && splash15) dmgDoneX =
                                (autoAttackDmg / (towerList[bullet.towerId].crossesAllList.size + 2))
                            else dmgDoneX = autoAttackDmg

                           if (towerList[bullet.towerId].experienceEachHit) {
                               var xpGain = (1 * 0.03f)
                               towerList[bullet.towerId].xpTower += xpGain
                               towerExperienceGainUtilAura(xpGain, bullet.towerId)
                               if (towerList[bullet.towerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, bullet.towerId)
                           }
                           if (towerList[bullet.towerId].experienceDrop) {
                               when ((0..99).random()) {
                                   0 -> {
                                       itemList.add(Items(6, 1, 999, 0, 0f, 0, 0f, 0, "Experiencer", R.drawable.xpgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: gain xp", it.overallXp, "", 0f))
                                       Companion.insertItem += 1
                                   }
                               }
                           }
                           // pen
                           it.armor -= towerList[bullet.towerId].armorPenPerHit
                           it.magicArmor -= towerList[bullet.towerId].magicPenPerHit

                           // dark talent slow
                           if (towerList[bullet.towerId].slowExtraDark > 0 && splash15) {
                               it.darkDebuff = true // dark talent
                               it.darkDebuffTowerId = bullet.towerId
                           }
                           // dark talent debuff
                           if (towerList[bullet.towerId].darkDmgDebuff > 0 && splash15) {
                               it.darkMoreDmgDebuff = 1
                               it.darkMoreDmgDebuffTowerId = 1
                               it.darkMoreDmgDebuffStacks += 1
                           }
                           // Ice talent slow
                           if (splash15 && towerList[bullet.towerId].slowEach > 0) {
                               it.iceDebuffTowerId = bullet.towerId
                               it.iceDebuff = 1
                           }
                           // Ice talent slow extra
                           if (towerList[bullet.towerId].slowExtra > 0 && splash15){
                               it.iceDebuffExtra = 1
                               it.iceDebuffExtraTowerId = bullet.towerId
                           }
                           // Poison talent stackable poison
                           if (towerList[bullet.towerId].itemListBag.contains(Items.epoison) && splash15) {
                               if (it.poisonDebuff > 0 && towerList[bullet.towerId].overallTowerSpellDmg > towerList[it.poisonDebuffTowerId].overallTowerSpellDmg) {
                                   it.poisonDebuffTowerId = bullet.towerId
                               }else if (it.poisonDebuff == 0) it.poisonDebuffTowerId = bullet.towerId
                               it.poisonDebuff = 1
                               if (towerList[bullet.towerId].itemStartPoison) it.poisonStack += 2.0f
                               else it.poisonStack += 1.0f
                           }
                           // Nature talent entangle
                           if (towerList[bullet.towerId].entangle > 0 && it.name != "immune" && splash15) {
                               it.entangleOnHitTowerId = bullet.towerId
                               it.entangleOnHit = 1
                           }
                           // Wind talent pushback
                           if (towerList[bullet.towerId].pushBack > 0 && it.name != "immune" && splash15) pushback(it, bullet)
                           // Wind talent bonus speed
                           if (towerList[bullet.towerId].bonusSpeedWindTalent > 0 && splash15) towerList[bullet.towerId].bonusSpeedWindTalentPercent += towerList[bullet.towerId].bonusSpeedWindTalent
                           // item slow death
                           if (towerList[bullet.towerId].itemSlowDeath > 0) {
                               var dmg = ((it.maxHp * towerList[bullet.towerId].itemSlowDeath) * magicPen(it, towerList[bullet.towerId]))

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

                               it.hp -= dmg
                               if (it.hp < 0) {
                                   it.killerId = bullet.towerId
                                   dead(it)
                               }
                           }
                           // wind debuff
                           if (towerList[bullet.towerId].windTalentDebuff > 0) it.winddebuffincreased = (1 + towerList[bullet.towerId].windTalentDebuff)

                           // talent wind ultimate
                           if (towerList[bullet.towerId].windUltimatePercent > 0) {
                               var dmg = ((it.maxHp * towerList[bullet.towerId].windUltimatePercent) * magicPen(it, towerList[bullet.towerId]))

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

                               it.hp -= dmg
                               if (it.hp < 0) {
                                   it.killerId = bullet.towerId
                                   dead(it)
                               }
                           }
                           // anti heal
                           if (towerList[bullet.towerId].bonusAntiHeal > 0){
                               it.antihealDebuff = 1
                               it.antihealDebuffTowerId = bullet.towerId
                           }
                           // Dark talent instakill
                           if (splash15 && it.name != "boss" && it.name != "challenge" && it.instakillStacks < 11 && towerList[bullet.towerId].itemListBag.contains(edark)) {
                               it.instakillStacks++
                                   when ((0..999).random()) {
                                       in 0..(instaKill * 10).toInt() -> {
                                           it.hp = -1.0f
                                           it.killerId = bullet.towerId
                                           it.instaKilled = 2
                                           dead(it)
                                       }
                               }
                           }
                           // Butterfly talent
                           if (towerList[bullet.towerId].towerPrimaryElement == "butterfly") {
                               if (towerList[bullet.towerId].markOfTheButterflyEnemyId < enemyList.size) {
                                   if (enemyList.indexOf(it) != towerList[bullet.towerId].markOfTheButterflyEnemyId && enemyList[towerList[bullet.towerId].markOfTheButterflyEnemyId].markOfTheButterflyCounter > 1 && it.markOfTheButterflyTowerCounter.size > 1) {}
                                   else enemyList[towerList[bullet.towerId].markOfTheButterflyEnemyId].markOfTheButterflyCounter = 0
                               }
                               it.markOfTheButterflyCounter++
                               if (!it.markOfTheButterflyTowerCounter.contains(bullet.towerId))it.markOfTheButterflyTowerCounter.add(bullet.towerId)
                               towerList[bullet.towerId].markOfTheButterflyEnemyId = enemyList.indexOf(it)
                               it.markOfTheButterflyTowerId = bullet.towerId
                               if (it.markOfTheButterflyCounter >= 3) {
                                   it.markOfTheButterflyTowerCounter.remove(bullet.towerId)
                                   it.markOfTheButterflyCounter = 0
                                   if (towerList[bullet.towerId].experienceButterflyPop > 0) {
                                       var xpGain = (1f * towerList[bullet.towerId].experienceButterflyPop)
                                       towerList[bullet.towerId].xpTower += xpGain
                                       towerExperienceGainUtilAura(xpGain, bullet.towerId)
                                       if (towerList[bullet.towerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, bullet.towerId)
                                   }
                                   if (towerList[bullet.towerId].butterflyDebuffEnemyDmg > 0) {
                                       it.butterflyDebuffEnemyDmgTowerId = bullet.towerId
                                       it.butterflyDebuffEnemyDmg = 1
                                   }
                                   if (towerList[bullet.towerId].butterflyDebuffEnemyDmg > 0) {
                                       it.butterflyDebuffEnemyGoldXpTowerId = bullet.towerId
                                       it.butterflyDebuffEnemyGoldXp = 1
                                   }
                                   towerList[bullet.towerId].markOfTheButterflyDmgBoostActive = true
                                   if (towerList[bullet.towerId].markOfTheButterflySpdBoost > 0) {
                                       towerList[bullet.towerId].markOfTheButterflySpdBoostActiveNumber += towerList[bullet.towerId].markOfTheButterflySpdBoostTowersNumber
                                       towerList[bullet.towerId].markOfTheButterflySpdBoostActiveCounter = 0
                                       towerList[bullet.towerId].markOfTheButterflySpdBoostActive = true
                                   }
                                   if (towerList[bullet.towerId].markOfTheButterflySlow && splash15) {
                                       it.markOfTheButterflySlowActive = 1
                                       it.markOfTheButterflySlowActiveTowerId = bullet.towerId
                                   }
                                   if (towerList[bullet.towerId].markOfTheButterflyExtraShot) {
                                       shootButterflyReserve++
                                   }

                                }
                            }

                        }
                    }
                return dmgDoneX
        }

    //------------------------------------------------------------------------------------

    private fun crossesChainLightning(bullet: Shoot) : Boolean {
        // check if shot crosses enemy
        var check = false
        writeLockEnemy.lock()
        readLockTower.lock()
        Companion.writeLockDisplay.lock()
        try {
            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                val distanceX = (bullet.bullet.x) - it.circle!!.x
                val distanceY = (bullet.bullet.y) - it.circle!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = bullet.bullet.r + (it.circle!!.r - 20.0)

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true
                        it.talentMoonAlreadyHit += 1
                        bullet.bullet.x = it.circle!!.x
                        bullet.bullet.y = it.circle!!.y

                    if (it.hp > 0) {
                        var dmg =
                            (towerList[bullet.towerId].overallTowerSpellDmg + (towerList[bullet.towerId].chainLightningBonusDmg * bigNumberScaler)) * magicPen(it, towerList[bullet.towerId]) * towerList[bullet.towerId].chainLightningDmg

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
                            var dmgDisplayListIterator = dmgDisplayList.listIterator()
                            dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                        }

                        if (it.hp < 0) {
                            it.killerId = bullet.towerId
                            towerList[bullet.towerId].chainLightningBonusDmg += (it.xpEnemy / 10)
                            dead(it)
                        }
                    }
                    }
                }
        } finally {
            writeLockEnemy.unlock()
            readLockTower.unlock()
            Companion.writeLockDisplay.unlock()
        }
        return check
    }

        //------------------------------------------------------------------------------------

        private fun splash15(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 15 radius
            readLockEnemy.lock()
            try {
                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = bulletR + (enemyR - 15.0)

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x
            } finally {
                readLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun splash30(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 30 radius
            readLockEnemy.lock()
            try {
                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = bulletR + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x
            } finally {
                readLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun splash60(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 60 radius
            readLockEnemy.lock()
            try {
                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 30.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x
            } finally {
                readLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun splash100(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 100 radius
            readLockEnemy.lock()
            try {
                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 70.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x
            } finally {
                readLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun splash150(enemyX: Float, enemyY: Float, enemyR: Float, bulletX: Float, bulletY: Float, bulletR: Float): Boolean {
            // check if enemy in splash 150 radius
            readLockEnemy.lock()
            try {
                var x = false
                val distanceX2 = (bulletX) - enemyX
                val distanceY2 = (bulletY) - enemyY

                val squaredDistance2 = (distanceX2 * distanceX2) + (distanceY2 * distanceY2)

                val sumOfRadius2 = (bulletR + 120.0) + enemyR

                if (squaredDistance2 <= sumOfRadius2 * sumOfRadius2) {
                    x = true
                }
                return x
            } finally {
                readLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun crossesShard(shard: ShootIceTalent): Boolean {
            // ice talent cross check
            writeLockEnemy.lock()
            readLockIce.lock()
            Companion.writeLockDisplay.lock()
            try {
                var check = false

                var enemyListIterator = enemyList.listIterator()
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

                            var dmg = 0f
                            if (it.name == "boss" || it.name == "challenge") dmg =
                                (it.maxHp * (0.05f + (0.01f * iceTowerCount))) * it.iceUltimateDR
                            else dmg =
                                ((it.maxHp * (0.10f + (0.01f * iceTowerCount))) * magicPen(it, Tower(0f, 0f, 0f, 0f, 0f, 0f))) * it.iceUltimateDR

                            if (it.hp > dmg) it.hp -= dmg
                            else it.hp = 1f
                            if (dmg > (it.maxHp / 100)) {
                                var dmgString = "0"
                                when (dmg.toInt()) {
                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                    in 1000..999999 -> dmgString =
                                        (dmg / 1000).toInt().toString() + "k"
                                    in 1000000..999999999 -> dmgString =
                                        (dmg / 1000000).toInt().toString() + "M"
                                }
                                var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintIceDmgDone, 30, 15))
                            }

                            it.iceUltimateDR *= 0.9f

                            if (towerList[it.iceDebuffTowerId].slowEach > 0) {
                                it.iceDebuff = 1
                                it.iceDebuffTowerId = iceShardTowerId
                            }
                            if (towerList[it.iceDebuffTowerId].slowExtra > 0) {
                                it.iceDebuffExtra = 1
                                it.iceDebuffExtraTowerId = iceShardTowerId
                            }

                            shard.color = Color.TRANSPARENT
                            shard.hit = true
                        }
                }

                return check
            } finally {
                writeLockEnemy.unlock()
                readLockIce.unlock()
                Companion.writeLockDisplay.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun crossesPoison(poisonClouds: TowerRadius): Boolean {
            // poison talent cross check
            var check = false

            writeLockEnemy.lock()
            readLockPoison.lock()

                try {
                    var enemyListIterator = enemyList.listIterator()
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
                                it.poisonDebuffTowerId = towerList.indexOf(poisonTowerHighestDmg)
                            }
                            it.poisonStack += (0.1f * poisonCloud) * 10

                            //------------------------------------------------------------------------
                        }
                    }
                } finally {
                    writeLockEnemy.unlock()
                    readLockPoison.unlock()
                }
            return check
        }

    //------------------------------------------------------------------------------------

    private fun crossesTornado(tornadoRadius: TowerRadius): Boolean {
        // poison talent cross check
        var check = false

        writeLockEnemy.lock()
        readLockTornado.lock()

        try {
            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                val distanceX = (tornadoRadius.x) - it.circle!!.x
                val distanceY = (tornadoRadius.y) - it.circle!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = tornadoRadius.r + it.circle!!.r

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true

                    // dmg -------------------------------------------------------------------

                    when (it.passed) {
                        1.toByte() -> {
                            if (it.circle!!.x > tornadoRadius.x) it.circle!!.x -= 3
                            else if (it.circle!!.x < tornadoRadius.x) it.circle!!.x += 9
                        }
                        2.toByte() -> {
                            if (it.circle!!.y > tornadoRadius.y) it.circle!!.y -= 3
                            else if (it.circle!!.y > tornadoRadius.y) it.circle!!.y += 9
                        }
                        3.toByte() -> {
                            if (it.circle!!.x > tornadoRadius.x) it.circle!!.x -= 9
                            else if (it.circle!!.x < tornadoRadius.x) it.circle!!.x += 3
                        }
                        4.toByte() -> {
                            if (it.circle!!.y > tornadoRadius.y) it.circle!!.y -= 9
                            else if (it.circle!!.y > tornadoRadius.y) it.circle!!.y += 3
                        }
                    }
                    //------------------------------------------------------------------------
                }
            }
        } finally {
            writeLockEnemy.unlock()
            readLockTornado.unlock()
        }
        return check
    }


    //------------------------------------------------------------------------------------

    private fun crossesMine(mine: TowerRadius): Boolean {
        // poison talent cross check
        var check = false

        writeLockEnemy.lock()
        readLockMine.lock()
        Companion.writeLockDisplay.lock()
        try {
            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var it = enemyListIterator.next()

                val distanceX = (mine.x) - it.circle!!.x
                val distanceY = (mine.y) - it.circle!!.y

                val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)

                val sumOfRadius = mine.r + it.circle!!.r

                if (squaredDistance <= sumOfRadius * sumOfRadius) {
                    check = true

                    // dmg -------------------------------------------------------------------

                    var enemyListIteratorZ = enemyList.listIterator()
                    while (enemyListIteratorZ.hasNext()) {
                        var otherEnemy = enemyListIteratorZ.next()
                        if (splash100(mine.x, mine.y, mine.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                            if (otherEnemy.mineAlreadyAffected) {
                                otherEnemy.baseSpeed += otherEnemy.mineSpeedReduce
                                otherEnemy.mineSpeedReduce = 0f
                            }

                            var dmg = ((3f * lvlScaler) * magicPen(it, Tower(0f,0f, 0f,  0f, 0f, 0f)))
                            if (otherEnemy.hp > dmg) otherEnemy.hp -= dmg
                            else otherEnemy.hp = 1f

                            if (dmg > (otherEnemy.maxHp / 100)) {
                                var dmgString = "0"
                                when (dmg.toInt()) {
                                    in 0..999 -> dmgString = dmg.toInt().toString()
                                    in 1000..999999 -> dmgString =
                                        (dmg / 1000).toInt().toString() + "k"
                                    in 1000000..999999999 -> dmgString =
                                        (dmg / 1000000).toInt().toString() + "M"
                                }
                                var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(it)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
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
            writeLockEnemy.unlock()
            readLockMine.unlock()
            Companion.writeLockDisplay.unlock()
        }
        return check
    }
        //------------------------------------------------------------------------------------

        private fun crossesAll() {

            // calculating all enemies in tower range
            writeLockEnemy.lock()
            writeLockTower.lock()
            try {
                var enemyListIterator = enemyList.listIterator()
                while (enemyListIterator.hasNext()) {
                    var it = enemyListIterator.next()
                    var towerListIterator = Companion.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var towerZ = towerListIterator.next()
                        val distanceX = (towerZ.towerRange.x) - it.circle!!.x
                        val distanceY = (towerZ.towerRange.y) - it.circle!!.y
                        val squaredDistance = (distanceX * distanceX) + (distanceY * distanceY)
                        val sumOfRadius = towerZ.towerR + (it.circle!!.r - 20)
                        if (squaredDistance <= sumOfRadius * sumOfRadius) {

                            if (towerZ.crossesAllList.contains(it)) {
                            } else {
                                if (it == towerZ.randomEnemyForShot) towerZ.randomEnemyForShotSticky = true
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
                        if (it.dead == 1) {
                            if (dmgDisplayList.isNotEmpty()) {
                                var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                while (dmgDisplayListIterator.hasNext()) {
                                    var display = dmgDisplayListIterator.next()
                                    if (display.indexx == it) {
                                        display.displayDmgDelete = true
                                    }
                                }
                            }
                            if (it == towerZ.randomEnemyForShot) towerZ.randomEnemyForShotSticky = true
                            towerZ.crossesAllList.remove(it)
                            towerZ.crossesNoneList.remove(it)
                        }
                    }
                }
                var enemyListIteratorZ = enemyList.listIterator()
                while (enemyListIteratorZ.hasNext()) {
                    var itZ = enemyListIteratorZ.next()
                    if (itZ.dead == 1) enemyListIteratorZ.remove()
                }
            } finally {
                writeLockEnemy.unlock()
                writeLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun multicrit (tower: Tower): Int {
            readLockTower.lock()
            try {
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
            if (itemFastDraw && multicritcounter > 1) {
                shootReserve ++
            }
            return multicritcounter
                 } finally {
                readLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun spellDmg(it: Enemy, splash15: Boolean, towerId: Int): Float {
            // calculating magic damage

                var dmg = 0f

                // ice talent

                if (it.iceAlreadyAffected == 1){
                    dmg += (towerList[towerId].overallTowerSpellDmg *0.2f) * magicPen(it, towerList[towerId])
                }

                if (towerList[towerId].iceExtraDmg > 0 && splash15) {
                    when ((0..9).random()) {
                        0 -> dmg += (towerList[towerId].overallTowerSpellDmg * magicPen(it, towerList[towerId])) * towerList[towerId].iceExtraDmg
                        in 1..9 -> {
                        }
                    }
                }

                // dark talent
                if (towerList[towerId].darkMagicDmgPercent > 0) {
                    dmg += (towerList[towerId].overallTowerSpellDmg * magicPen(it, towerList[towerId])) * towerList[towerId].darkMagicDmgPercent
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
                if (it.shield > 0) dmg = 0f
                return dmg

        }

        //------------------------------------------------------------------------------------

        private fun pushback(it: Enemy, bullet: Shoot) {
            // Wind talent
            writeLockEnemy.lock()
            try {
            when ((0..999).random()) {
                in 0..(99 * it.pushBackDR).toInt() -> {
                    when (it.passed) {
                        1.toByte() ->
                            it.circle!!.x += towerList[bullet.towerId].pushBack
                        2.toByte() ->
                            it.circle!!.y += towerList[bullet.towerId].pushBack
                        3.toByte() ->
                            it.circle!!.x -= towerList[bullet.towerId].pushBack
                        4.toByte() ->
                            it.circle!!.y -= towerList[bullet.towerId].pushBack
                    }
                    it.pushBackDR * 0.66
                }
                in (99 * it.pushBackDR).toInt() + 1..999 -> {
                }
            }
            } finally {
                writeLockEnemy.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        private fun itemsDmg (tower: Tower) :Float{
            readLockTower.lock()
            try {
            var returnX = 0.0f

            if (tower.itemDisruptor > 0){
                if (mapMode != 2) returnX += tower.crossesAllList.size * tower.itemDisruptor
                else {
                    var x = tower.crossesAllList.size
                    if (x > 8) x = 8
                    returnX += x * tower.itemDisruptor
                }
            }

            return returnX

                } finally {
                readLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------

        fun items () {

            writeLockTower.lock()
            try {
                    var towerListIterator = Companion.towerList.listIterator()
                    while (towerListIterator.hasNext()) {
                        var tower = towerListIterator.next()

                        if (tower.itemSniper > 0) {
                            tower.itemSniperCount++
                            if (tower.itemSniperCount >= 300 && tower.crossesNoneList.isNotEmpty()) {
                                tower.itemSniperCount = 0
                                var itemSniperPlace = tower.itemSniper
                                while (itemSniperPlace > 0) {
                                    itemSniperPlace--
                                    var shootListPlace = Shoot()
                                    shootListPlace.sniper = true
                                    var shootListIterator = shootList.listIterator()
                                    shootListIterator.add(shootListPlace)
                                }
                            } else if (tower.itemSniperCount >= 300 && tower.crossesNoneList.isEmpty()) {
                                tower.itemSniperCount = 299
                            }
                        }
                    }
                        } finally {
                writeLockTower.unlock()
            }
        }


        private fun itemsPerRound () {

            writeLockTower.lock()
            try {
                var towerListIterator = Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()

                    tower.itemListBag.forEach() {

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
                            it.specialFloat = 2f * lvlScaler
                            tower.bonusTowerDmg += it.specialFloat
                        }
                        if (it.id == 5001) {
                            tower.bonusTowerSpeed -= it.specialFloat
                            it.specialFloat = (10 + (12f * lvlScalerSecond))
                            tower.bonusTowerSpeed += it.specialFloat
                        }
                        if (it.id == 5002) {
                            tower.bonusCrit -= it.specialFloat
                            it.specialFloat = (9f * lvlScalerSecond)
                            tower.bonusCrit += it.specialFloat
                        }
                        if (it.id == 5003) {
                            tower.bonusPhysicalDmg -= it.specialFloat
                            it.specialFloat = 2f * lvlScaler
                            tower.bonusPhysicalDmg += it.specialFloat
                        }
                        if (it.id == 5004) {
                            tower.bonusSpellDamage -= it.specialFloat
                            it.specialFloat = 2f * lvlScaler
                            tower.bonusSpellDamage += it.specialFloat
                        }
                        if (it.id == 5005) {
                            tower.bonusCritDmg -= it.bonusCritDmgLevel
                            tower.bonusCrit -= it.specialFloat
                            it.bonusCritDmgLevel = 0.1f * lvlScalerSecond
                            it.specialFloat = 3f * lvlScalerSecond * 1.5f
                            tower.bonusCritDmg += it.bonusCritDmgLevel
                            tower.bonusCrit += it.specialFloat
                        }
                        if (it.id == 5006) {
                            tower.bonusSpellDamage -= it.specialFloat
                            it.specialFloat = 3f * lvlScaler * 0.5f
                            tower.bonusSpellDamage += it.specialFloat
                        }
                    }
                }
               } finally {
                writeLockTower.unlock()
            }
        }

        //------------------------------------------------------------------------------------

        fun activeAbilitiesEffect(){

                if (activeAbilityHelpingHandButtonCounter > 0) activeAbilityHelpingHandButtonCounter ++

                if (activeAbilityHelpingHandActiveCounter == 1) {
                    globalDmgMultiplier += 0.3f
                    activeAbilityHelpingHandActiveCounter++
                } else if (activeAbilityHelpingHandActiveCounter >= 300){
                    globalDmgMultiplier -= 0.3f
                    activeAbilityHelpingHandActiveCounter = 0
                } else if (activeAbilityHelpingHandActiveCounter > 1) activeAbilityHelpingHandActiveCounter++

        }

        //------------------------------------------------------------------------------------

        private fun dead (enemy: Enemy) {

            // enemy DEAD

                    if (enemy.name == "split" && enemy.hp < 0 && enemy.dead == 0){
                        Companion.writeLockEnemy.lock()
                        try {
                        var enemyListIterator = enemyList.listIterator()
                        var y = 10
                                repeat(2) {
                                    var x =
                                        Enemy(enemy.maxHp * 0.2f, enemy.maxHp * 0.2f, 0f, 0f, 0f, 0f, enemy.armor, enemy.magicArmor, enemy.evade, enemy.hpReg * 0, (enemy.xpEnemy * 0.5f), lvlSpd, R.color.splitter)
                                    x.circle!!.x = (enemy.circle!!.x + y)
                                    x.circle!!.y = enemy.circle!!.y
                                    x.baseSpeed = x.speed
                                    x.passed = enemy.passed
                                    x.name = "splitter"
                                    enemyListIterator.add(x)
                                    y -= 20
                                }
                        enemy.passed = 5.toByte()
                        enemy.dead = 1
                        } finally {
                            writeLockEnemy.unlock()
                        }
                    } else if (enemy.hp <= 0 && enemy.dead == 0) {

                        if (enemy.wizardBombActive > 0){
                            for (otherEnemy in enemyList) {
                                if (splash100(enemy.circle!!.x, enemy.circle!!.y, enemy.circle!!.r, otherEnemy.circle!!.x, otherEnemy.circle!!.y, otherEnemy.circle!!.r)) {
                                    var dmg = 0f
                                    dmg = ((towerList[enemy.wizardBombTowerId].wizardBombDmg * 0.66f *bigNumberScaler) + (towerList[enemy.wizardBombTowerId].overallTowerSpellDmg * (0.1f + wizardBombStartItemDmg)))

                                    if  (otherEnemy.manaShield > 0){
                                        if (otherEnemy.manaShield > dmg) {
                                            otherEnemy.manaShield -= dmg
                                            dmg = 0f
                                        }else {
                                            dmg = (dmg - otherEnemy.manaShield)
                                            otherEnemy.manaShield = 0f
                                        }
                                    }
                                    if (otherEnemy.shield > 0) dmg = 0f
                                    otherEnemy.hp -= dmg
                                    if (dmg > (otherEnemy.maxHp / 100)) {
                                        var dmgString = "0"
                                        when (dmg.toInt()) {
                                            in 0..999 -> dmgString = dmg.toInt().toString()
                                            in 1000..999999 -> dmgString =
                                                (dmg / 1000).toInt().toString() + "k"
                                            in 1000000..999999999 -> dmgString =
                                                (dmg / 1000000).toInt().toString() + "M"
                                        }
                                        Companion.writeLockDisplay.lock()
                                        try {
                                        var dmgDisplayListIterator = dmgDisplayList.listIterator()
                                        dmgDisplayListIterator.add(DmgDisplay(enemyList[enemyList.indexOf(otherEnemy)], dmgString, 1, 50, paintWizardDmgDone, -30, -15))
                                        } finally {
                                        Companion.writeLockDisplay.unlock()
                                             }
                                        }
                                }
                            }
                        }
                        if (towerList[enemy.killerId].talentFireKill) towerList[enemy.killerId].bonusCrit += 1f

                        if (enemy.name == "boss") {         // boss
                            diamonds ++
                            bossesKilled += 1
                        }
                        if (enemy.name == "challenge") {    // challenge
                            if (mapMode != 2) lives += 5
                            else livesMode2 += 5
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            challengesKilled += 1
                            if (activeAbilityList.contains(aAid1)) {
                                bombActiveAbility += 5
                                Companion.insertSpellBomb += 1
                            }
                            else {
                                activeAbilityList.add(0, aAid1)
                                Companion.insertSpell += 1
                                Companion.insertSpellBomb +=1
                                bombActiveAbility += 5
                            }
                        }

                        overallXp += enemy.overallXp
                        gold += enemy.overallGold * towerList[enemy.killerId].itemPiggyBank
                        var xpGain : Float =
                            if (enemy.name == "boss") ((1 * 3) * (0.75f + towerList[enemy.killerId].experienceKill))
                            else if (enemy.name == "challenge") (1 * 5) * (0.75f + towerList[enemy.killerId].experienceKill)
                            else (1) * (0.75f + towerList[enemy.killerId].experienceKill) * enemy.instaKilled
                        towerList[enemy.killerId].xpTower += xpGain
                        towerExperienceGainUtilAura(xpGain, enemy.killerId)
                        if (towerList[enemy.killerId].experienceShareUtilsAura) towerExperienceShareUtilAura(xpGain, enemy.killerId)

                        if (enemy.selected){
                            supportFragmentManager.beginTransaction().apply {
                                replace(R.id.fragment, fragmentStats)
                                    .addToBackStack(null)
                                    .commit()
                            }
                        }
                        dropItemList += 1
                        dropItem(enemy)

                        enemiesKilled += 1
                        enemy.dead = 1
                        enemy.passed = 5.toByte()

                        }

        }

    fun dead2 (){
        Companion.readLockEnemy.lock()
        try {
            var enemyListIterator = enemyList.listIterator()
            while (enemyListIterator.hasNext()) {
                var enemy = enemyListIterator.next()
                if (enemy.passed == 5.toByte() && enemy.dead != 1) {
                    if (enemy.name == "challenge") {
                    } else if (enemy.name == "boss") {
                        bossesLeaked += 1
                        lives -= 3
                    } else lives -= 1
                    if (lives < 0) lives = 0
                    soundPool.play(soundIds[2], logVolumeEffects, logVolumeEffects, 1, 0, 1.0f)
                    enemy.dead = 1
                }
            }
            } finally {
                readLockEnemy.unlock()
            }
    }



    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private fun getItem(tower: Tower): Items {
        readLockTower.lock()
        try {

        // grey: #DBDBDB (219,219,219)
        // blue: (94, 105, 255)
        // orange: (244, 221, 57)
        // lila: (132,73,228)
        // rot: (221,22,22)
        // green: consumables (128, 255, 128)

        // item value (dmg = 1; crt = 3; spd = 4) pro 15 xp
        // grey cost/dmg * 1,0, rare cost * 1.1 , epic cost * 1.25 , legendary cost* 1.5


        var x = Items(-1, 0, 0, 0, 0f, 0, 0f, 0, "???", R.drawable.questionmarkred, R.drawable.overlaytransparent, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0, "", 0f, "", 0f)

        when ((0..999).random()) {
            in 0..ceil((65 - tower.overallItemQuality) * 10).toInt() ->
                when ((0..8).random()) {
                    in 0..6 ->
                    if (itemListNormal.isNotEmpty()) {
                        when ((Items.itemListNormal).random()) {
                            0 -> {
                                when ((0..3).random()) {
                                    in 0..1 -> x =
                                        Items(0, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Mace & Blaze", R.drawable.wandswordgrey, R.drawable.overlaytransparent, (0.75f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                    2 -> x =
                                        Items(0, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Mace", R.drawable.pdoubleswordsgrey, R.drawable.overlaytransparent, 0f, (1.25f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                    3 -> x =
                                        Items(0, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Wand", R.drawable.zauberstabgrey, R.drawable.overlaytransparent, 0f, 0.0f, (1.25f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 1, "", 0f, "", 0f)
                                }
                            }
                            1 -> x =
                                Items(1, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Bow", R.drawable.pbowgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, (4.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 1, "", 0f, "", 0f)
                            2 -> {
                                when ((0..99).random()) {
                                    in 0..65 -> x =
                                        Items(2, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Sword", R.drawable.pcritgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, (3.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 1, "", 0f, "", 0f)
                                    in 66..99 -> x =
                                        Items(6, 1, 999, 0, 0f, 0, 0f, 0, "Gold Digger", R.drawable.goldgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ Gold", (((costBase * lvlXp) / 4)*tower.towerRarityMultiplier), "", 0f)
                                }
                            }
                            3 -> x =
                                Items(3, 1, 25, 0, 0f, 0, 0f, 0, "Magic Box", R.drawable.magicboxgrey, R.drawable.overlaytransparent, (0.5f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1, "no use cost", 0f, "", 0f)
                            4 -> x =
                                Items(4, 1, 999, 0, (costBase * lvlXp), 0, 0f, 0, "Lucky Charm", R.drawable.luckycharmgrey, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 1, "", 0f, "plus itemchance", 15f + ((ceil(level / 10.0f)) * 4 * tower.towerRarityMultiplier))
                            5 -> {
                                if (mapMode == 2) x =
                                    Items(5, 1, 999, 0, ((costBase * lvlXp) / 2), 0, 0f, 0, "Bomb", R.drawable.bombgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: 5% dmg to all enemies", 0f, "", 0f)
                                else x =
                                    Items(5, 1, 999, 0, ((costBase * lvlXp) / 2), 0, 0f, 0, "Bomb", R.drawable.bombgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "Consumable: 20% dmg to all enemies", 0f, "", 0f)
                            }
                        }
                    }
                    7, 8 -> {
                        when ((0..9).random()){
                            0 -> x = Items.eearth
                            1 -> x = Items.ewizard
                            2 -> x = Items.eice
                            3 -> x = Items.ebutterfly
                            4 -> x = Items.epoison
                            5 -> x = Items.emoon
                            6 -> x = Items.ewind
                            7 -> x = Items.eutils
                            8 -> x = Items.efire
                            9 -> x = Items.edark
                        }
                    }
                }
            in floor((65 - tower.overallItemQuality) * 10).toInt()..ceil((88 - (tower.overallItemQuality / 1.5)) * 10).toInt() ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (itemListRare.isNotEmpty()) {
                            when (Items.itemListRare.random()) {
                                100 -> {
                                    when ((0..7).random()) {
                                        in 0..1 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Mace & Blaze", R.drawable.wandswordblue, R.drawable.overlaytransparent, (2.25f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        in 2..3 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Mace & Blaze", R.drawable.wandswordblue, R.drawable.overlaytransparent, (2.25f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        4 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Mace", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0f, (3.75f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        5 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Mace", R.drawable.pdoubleswordsblue, R.drawable.overlaytransparent, 0f, (3.75f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        6 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Wand", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0f, 0.0f, (3.75f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        7 -> x =
                                            Items(100, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Wand", R.drawable.zauberstabblue, R.drawable.overlaytransparent, 0f, 0.0f, (3.75f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                101 -> x =
                                    Items(101, 1, 25, 0, 0f, 0, 0f, 0, "Rare Magic Box", R.drawable.magicboxblue, R.drawable.overlaytransparent, (1.5f * lvlScaler * tower.towerRarityMultiplier), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, "no use cost", 0f, "", 0f)
                                102 -> {
                                    when ((0..1).random()) {
                                        0 -> x =
                                            Items(102, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Bow", R.drawable.pbowblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, (12.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x =
                                            Items(102, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Bow", R.drawable.pbowblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, (12.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 0.0f, 2, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                103 -> {
                                    when ((0..1).random()) {
                                        0 -> x =
                                            Items(103, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Sword", R.drawable.pcritblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, (9.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 2, "", 0f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x =
                                            Items(103, 1, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Rare Sword", R.drawable.pcritblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, (9.0f * lvlScalerSecond * tower.towerRarityMultiplier), 0.0f, 2, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                }
                                104 -> x =
                                    Items(104, 30, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Lucky Charm", R.drawable.luckycharmblue, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "", 0f, "item quality", 2f + (ceil(level / 10.0f)) * 2 * tower.towerRarityMultiplier)
                                105 -> x =
                                    Items(105, 1, 999, 0, 0f, 1, 0f, 0, "Piggy Bank", R.drawable.piggybank, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "10% more Gold from enemies", 0f, "", 0f)
                                106 -> x =
                                    Items(106, 10, 999, 0, (costBlue * lvlXp), 0, 0f, 0, "Heart", R.drawable.heartgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 live", 1f, "", 0f)
                                107 -> x =
                                    Items(107, 10, 999, 0, (costBlue * lvlXp * 0.66f), 0, 0f, 0, "Number One", R.drawable.numberonegreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 talent point", 1f, "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()) {
                            0 -> x = Items(2100, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Earth Tower", R.drawable.towerearthblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            1 -> x = Items(2101, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Wizard Tower", R.drawable.towerwizardblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            2 -> x = Items(2102, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Ice Tower", R.drawable.towericeblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
                            3 -> x = Items(2103, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Butterfly Tower", R.drawable.towerbutterflyblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            4 -> x = Items(2104, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Poison Tower", R.drawable.towerpoisonblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            5 -> x = Items(2105, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Moon Tower", R.drawable.towermoonblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                            6 -> x = Items(2106, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Wind Tower", R.drawable.towerwindblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0, "bagspace", 3.0f, "bagspace element", 2f)
                            7 -> x = Items(2107, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Utils Tower", R.drawable.towerutilsblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f,0,"bagspace", 3.0f, "bagspace element", 2f)
                            8 -> x = Items(2108, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Fire Tower", R.drawable.towerfireblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0,"bagspace", 3.0f, "bagspace element", 2f)
                            9 -> x = Items(2109, 0, 999,0,(costBlue * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Rare Dark Tower", R.drawable.towerdarkblue, R.drawable.overlaytransparent,10f, 0.0f,0.0f,60f, 1f, 1.5f, 0, "bagspace", 3.0f, "bagspace element", 2f)
                        }
                    }
                }
            in floor((88 - (tower.overallItemQuality / 1.5)) * 10).toInt()..ceil((96 - (tower.overallItemQuality / 2)) * 10).toInt() ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (itemListEpic.isNotEmpty()) {
                            when (Items.itemListEpic.random()) {
                                200 -> {
                                    when ((0..3).random()) {
                                        in 0..1 -> x =
                                            Items(200, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Mace & Blaze", R.drawable.wandswordorange, R.drawable.overlaytransparent, ((3.0f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPen(), (0.9f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                        2 -> x =
                                            Items(200, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Mace", R.drawable.pdoubleswordsorange, R.drawable.overlaytransparent, 0f, ((5.0f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPenPhy(), (0.9f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                        3 -> x =
                                            Items(200, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Wand", R.drawable.zauberstaborange, R.drawable.overlaytransparent, 0f, 0.0f, ((5.0f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 3, "magic penetration", (0.9f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                    }
                                }
                                201 -> x =
                                    Items(201, 1, 25, 0, 0f, 0, 0f, 0, "Epic Magic Box", R.drawable.magicboxorange, R.drawable.overlaytransparent, ((2.0f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, "no use cost", 0f, "", 0f)
                                202 -> x =
                                    Items(202, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Bow", R.drawable.pboworange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((16.0f * lvlScalerSecond) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 3, specialPen(), (0.9f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                203 -> x =
                                    Items(203, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Sword", R.drawable.pcritorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, ((12.0f * lvlScalerSecond) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 3, specialPen(), (0.9f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                204 -> x =
                                    Items(204, 30, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Lucky Charm", R.drawable.luckycharmorange, R.drawable.overlaytransparent, (2.0f * lvlScaler)* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 3, "", 0f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                205 -> x =
                                    Items(205, 10, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Lifeline", R.drawable.lifelineorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+1 live/10 rounds ", 1f, "", 0f)
                                206 -> x =
                                    Items(206, 1, 30, 0, (costEpic * lvlXp), 0, 0f, 0, "X-Factor", R.drawable.talentorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+3 talent points", 3f, "", 0f)
                                207 -> when ((0..2).random()) {
                                    0 -> x =
                                        Items(207, 1, 30, 0, (costEpic * lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X dmg/round", 0.5f* tower.towerRarityMultiplier, "", 0f)
                                    1 -> x =
                                        Items(207, 1, 30, 0, (costEpic * lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X spd/round", 2f* tower.towerRarityMultiplier, "", 0f)
                                    2 -> x =
                                        Items(207, 1, 30, 0, (costEpic * lvlXp) / 2, 0, 0f, 0, "Battery", R.drawable.batteryorange, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ X crit/round", 1.5f* tower.towerRarityMultiplier, "", 0f)
                                }
                                208 -> x =
                                    Items(208, 30, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Freezer", R.drawable.frostorange, R.drawable.overlaytransparent, 0.0f, 0.0f, ((2f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0f, 0.0f, 0.0f, 0, "slows a random unit for 20%", 20.0f, "", 0f)
                                209 -> x =
                                    Items(209, 1, 50, 0, 0f, 1, 0f, 0, "Coin", R.drawable.coininterest, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 0, "+ 1.5% interest rate", 0.015f, "", 0f)
                                210 -> x =
                                    Items(210, 50, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Lasso", R.drawable.lassoorange, R.drawable.overlaytransparent, ((0.5f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 3, "throws X lassos that stun", 1.0f, "", 0f)
                                211 -> x =
                                    Items(211, 30, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "20/20", R.drawable.critdmgorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, ((9f * lvlScalerSecond) + (level * 0.015f))* tower.towerRarityMultiplier, (0.01f * level)* tower.towerRarityMultiplier, 3, "", 0.0f, "", 0f)
                                212 -> x =
                                    Items(212, 40, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "One Punch Thingy", R.drawable.hitorange, R.drawable.overlaytransparent, ((0.5f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X hit", (1.5f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                213 -> x =
                                    if (mapMode == 2) Items(213, 25, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Last Stance", R.drawable.laststanceorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "+10% dmg/live lost", 0.0f, "", 0f)
                                    else Items(200, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Epic Mace & Blaze", R.drawable.wandswordorange, R.drawable.overlaytransparent, ((3.0f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, specialPen(), (1f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                214 -> x =
                                    Items(214, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Sniper", R.drawable.sniperorange, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "snipes a single target", 0.0f, "", 0f)
                                215 -> x =
                                    Items(215, 40, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Shredder", R.drawable.shredderorange, R.drawable.overlaytransparent, ((0.5f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X Armor Penetration", (0.5f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                216 -> x =
                                    Items(216, 40, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Shield Breaker", R.drawable.magicbrakerorange, R.drawable.overlaytransparent, ((0.5f * lvlScaler) + (level * 0.075f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0f, 0f, 3, "+ X Magic Penetration", (0.5f * lvlScalerSecond)* tower.towerRarityMultiplier, "", 0f)
                                217 -> x =
                                    Items(217, 1, 999, 0, (costEpic * lvlXp), 0, 0f, 0, "Snowman", R.drawable.snowmanorange, R.drawable.overlaytransparent, ((0.5f * lvlScaler) + (level * 0.05f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((4f * lvlScalerSecond) + (level * 0.05f))* tower.towerRarityMultiplier, ((3f * lvlScalerSecond) + (level * 0.05f))* tower.towerRarityMultiplier, 0f, 3, "+10% slow on hit", 0f, "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()){
                            0 -> x = Items(2200, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Earth Tower", R.drawable.towerearthorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                            1 -> x = Items(2201, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Wizard Tower", R.drawable.towerwizardorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            2 -> x = Items(2202, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Ice Tower", R.drawable.towericeorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            3 -> x = Items(2203, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Butterfly Tower", R.drawable.towerbutterflyorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            4 -> x = Items(2204, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Poison Tower", R.drawable.towerpoisonorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            5 -> x = Items(2205, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Moon Tower", R.drawable.towermoonorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                            6 -> x = Items(2206, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Wind Tower", R.drawable.towerwindorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f, 0, "bagspace", 4.0f, "bagspace element", 2f)
                            7 -> x = Items(2207, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Utils Tower", R.drawable.towerutilsorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0,"bagspace", 4.0f, "bagspace element", 2f)
                            8 -> x = Items(2208, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Fire Tower", R.drawable.towerfireorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f, 0,"bagspace", 4.0f, "bagspace element", 2f)
                            9 -> x = Items(2209, 0, 999,0,(costEpic * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Epic Dark Tower", R.drawable.towerdarkorange, R.drawable.overlaytransparent,15f, 0.0f,0.0f,50f, 1f, 1.75f,  0, "bagspace", 4.0f, "bagspace element", 2f)
                        }
                    }
                }
            in floor((96 - (tower.overallItemQuality / 2)) * 10).toInt()..999 ->
                when ((0..8).random()) {
                    in 0..6 ->
                        if (itemListLegendary.isNotEmpty()) {
                            when (Items.itemListLegendary.random()) {
                                300 ->
                                    when ((0..11).random()) {
                                        0 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        2 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        3 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace & Blaze", R.drawable.wandswordpurple, R.drawable.overlaytransparent, ((4.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        6 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        7 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        8 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        9 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Mace", R.drawable.pdoubleswordspurple, R.drawable.overlaytransparent, 0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        10 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        11 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        12 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        13 -> x = Items(300, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Wand", R.drawable.zauberstabpurple, R.drawable.overlaytransparent, 0f, 0.0f, ((7.5f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                301 -> x = Items(301, 1, 25, 0, 0f, 0, 0f, 0, "Legendary Magic Box", R.drawable.magicboxpurple, R.drawable.overlaytransparent, ((3.0f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 5, "no xp cost", 0f, "", 0f)
                                302 ->
                                    when ((0..2).random()) {
                                        0 -> x = Items(302, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((24.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(302, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((24.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        2 -> x = Items(302, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((24.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        3 -> x = Items(302, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Bow", R.drawable.pbowpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((24.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                303 ->
                                    when ((0..2).random()) {
                                        0 -> x = Items(303, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, ((18.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "anti-heal", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        1 -> x = Items(303, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, ((18.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "extra dmg immune", 10f, "item find", 7.5f + ((ceil(level / 10.0f)) * 2)* tower.towerRarityMultiplier)
                                        2 -> x = Items(303, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, ((18.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "anti-heal", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                        3 -> x = Items(303, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Sword", R.drawable.pcritpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, ((18.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "extra dmg immune", 10f, "item quality", 1f + (ceil(level / 10.0f))* tower.towerRarityMultiplier)
                                    }
                                304 -> x = Items(304, 20, 100, 0, (costLegendary * lvlXp * 0.5f), costDia, 0f, 0, "Frost Aura", R.drawable.iceaurapurple, R.drawable.overlaytransparent, ((1.0f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((1.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0, "Slow aura X%", 20f, "", 0f)
                                305 -> x = Items(305, 1, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Disruptor", R.drawable.disruptorpurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "Adds X dmg per enemy in tower range", (2f * lvlScaler)* tower.towerRarityMultiplier, "", 0f)
                                306 -> x = Items(306, 50, 999, 0, 0f, costDia, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f)
                                307 -> x = Items(307, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Multibarrel", R.drawable.bouncepurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, ((4.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 5, "+2 bounce", 2f, "", 0f)
                                308 -> x = Items(308, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Freezer", R.drawable.frostlila, R.drawable.overlaytransparent, ((1.0f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, ((4.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, ((3.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 5, "slows a random unit for 30%", 30.0f, "", 0f)
                                309 -> x = Items(309, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Lasso", R.drawable.lassopurple, R.drawable.overlaytransparent, ((1.0f * lvlScaler) + (level * 0.15f))* tower.towerRarityMultiplier, 0.0f, 0.0f, 0f, 0.0f, 0.0f, 5, "throws X lassos that stun", 3.0f, "", 0f)
                                310 -> x = Items(310, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "BullZ-I", R.drawable.bullseyepurple, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0f, ((12.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0f, 5, "+1 multicrit", 1f, "", 0f)
                                311 -> x = Items(311, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "21/20", R.drawable.critdmgpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, ((12f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, (0.015f * level)* tower.towerRarityMultiplier, 5, "", 0.0f, "", 0f)
                                312 -> x = Items(312, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Slow Death", R.drawable.slowdeathpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "burns 1% hp as mgc dmg per attack", 0.01f, "", 0f)
                                313 -> x = Items(313, 10, 999, 0, 0f, costDia, 0f, 0, "Lovely!", R.drawable.heartdiagreen, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, 0f, 0f, 0f, 0, "+5 lives", 5f, "", 0f)
                                314 -> x = Items(314, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Shredder", R.drawable.shredderpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, ((6.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0f, 0f, 0, "reduces armor by X per hit", 0.5f* tower.towerRarityMultiplier, "", 0f)
                                315 -> x = Items(315, 30, 999, 0, (costLegendary * lvlXp), costDia, 0f, 0, "Legendary Magic Braker", R.drawable.magicbrakerpurple, R.drawable.overlaytransparent, 0f, 0.0f, 0.0f, ((6.0f * lvlScalerSecond) + (level * 0.15f))* tower.towerRarityMultiplier, 0f, 0f, 0, "reduces magic armor by X per hit", 0.5f* tower.towerRarityMultiplier, "", 0f)
                            }
                        }
                    7,8 -> {
                        when ((0..9).random()){
                            0 -> x = Items(2300, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Earth Tower", R.drawable.towerearthpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            1 -> x = Items(2301, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Wizard Tower", R.drawable.towerwizardpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                            2 -> x = Items(2302, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Ice Tower", R.drawable.towericepurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            3 -> x = Items(2303, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Butterfly Tower", R.drawable.towerbutterflypurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            4 -> x = Items(2304, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Poison Tower", R.drawable.towerpoisonpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            5 -> x = Items(2305, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Moon Tower", R.drawable.towermoonpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f, 0, "bagspace", 5.0f, "bagspace element", 3f)
                            6 -> x = Items(2306, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Wind Tower", R.drawable.towerwindpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                            7 -> x = Items(2307, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Utils Tower", R.drawable.towerutilspurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f,0,"bagspace", 5.0f, "bagspace element", 3f)
                            8 -> x = Items(2308, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Fire Tower", R.drawable.towerfirepurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f,0,"bagspace", 5.0f, "bagspace element", 3f)
                            9 -> x = Items(2309, 0, 999,0,(costLegendary * lvlXp * (1.5f + (towerCount /10))), 0, 0f, 0, "Legendary Dark Tower", R.drawable.towerdarkpurple, R.drawable.overlaytransparent,25f, 0.0f,0.0f,40f, 10f, 2f,0, "bagspace", 5.0f, "bagspace element", 3f)
                        }
                    }
                }
        }

        return x
        } finally {
            readLockTower.unlock()
        }
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

            var it = towerList[killerId]

            var towerListIteratorZ = Companion.towerList.listIterator()
            while (towerListIteratorZ.hasNext()) {
                var tower = towerListIteratorZ.next()
                if (it != tower) {
                    if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                        if (tower.experienceGainUtilsAura) tower.xpTower += xpGain / 10
                    }
                }
            }
    }

    fun towerExperienceShareUtilAura(xpGain: Float, killerId: Int) {

            var it = towerList[killerId]

            var towerListIteratorZ = Companion.towerList.listIterator()
            while (towerListIteratorZ.hasNext()) {
                var tower = towerListIteratorZ.next()
                if (it != tower) {
                    if (splash150(it.towerRange.x, it.towerRange.y, it.towerR, tower.towerRange.x, tower.towerRange.y, tower.towerR)) {
                        tower.xpTower += xpGain / 10
                    }
                }
            }
    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------

    private fun addItemsToList() {

        itemListReserveNormal.forEach() {
            if (it.minLvl.toInt() == level) itemListNormal.add(it.id)
            if (it.maxLvl.toInt() == level) itemListNormal.remove(it.id)
        }
        itemListReserveRare.forEach() {
            if (it.minLvl.toInt() == level) itemListRare.add(it.id)
            if (it.maxLvl.toInt() == level) itemListRare.remove(it.id)
        }
        itemListReserveEpic.forEach() {
            if (it.minLvl.toInt() == level) itemListEpic.add(it.id)
            if (it.maxLvl.toInt() == level) itemListEpic.remove(it.id)
        }
        itemListReserveLegendary.forEach() {
            if (it.minLvl.toInt() == level) itemListLegendary.add(it.id)
            if (it.maxLvl.toInt() == level) itemListLegendary.remove(it.id)
        }

    }

}