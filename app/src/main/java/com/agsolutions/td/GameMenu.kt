package com.agsolutions.td

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.Companion.Companion.enemyList
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.mapPick
import com.agsolutions.td.Companion.Companion.upgradePoints
import com.agsolutions.td.Main.MainActivity
import kotlinx.android.synthetic.main.game_menu.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.util.*


class GameMenu : AppCompatActivity() {
    companion object {
    }
    var mHandler = Handler ()
    private var PRIVATE_MODE = 0
    var sharedPref: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_menu)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)


        window.setLayout((400.0f * ((com.agsolutions.td.Companion.scaleScreen) /10)).toInt(), (800.0f * ((com.agsolutions.td.Companion.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        if (level > 0 && mapPick != 0) saveAndQuitBtn.visibility = View.VISIBLE

        quitBtn.setOnClickListener(){
            var editor = sharedPref!!.edit()
            editor.putBoolean("continueGame", false)
            editor.apply()

            GameActivity.gameEnd = 0
            mHandler.postDelayed({
                finish()
            }, 50)
        }

        saveAndQuitBtn.setOnClickListener() {
            var editor = sharedPref!!.edit()
            editor.putBoolean("continueGame", true)
            editor.putInt("continueGameMapPick", mapPick)
            editor.putInt("continueGameMapMode", mapMode)
            editor.apply()

            var livesX = 0
                if (mapMode == 2) livesX = com.agsolutions.td.Companion.livesMode2
                else livesX = com.agsolutions.td.Companion.lives

            var saveDataList = arrayListOf<Int>(level, livesX , com.agsolutions.td.Companion.dayNightHour, com.agsolutions.td.Companion.dayNightMinute)
            // TODO com.agsolutions.td.Companion.chainLightningBonusDmg.toInt()
            var saveDataListCurrency = arrayListOf<Int>(com.agsolutions.td.Companion.gold.toInt(), com.agsolutions.td.Companion.diamonds, com.agsolutions.td.Companion.itemPoints.toInt(), com.agsolutions.td.Companion.mysteryPoints, upgradePoints, com.agsolutions.td.Companion.bombActiveAbility, com.agsolutions.td.Companion.levelCount, com.agsolutions.td.Companion.enemySpawned, com.agsolutions.td.Companion.timer)
            var saveDataListLevelInfo = arrayListOf<Float>(com.agsolutions.td.Companion.lvlHp, com.agsolutions.td.Companion.lvlArmor, com.agsolutions.td.Companion.lvlMagicArmor, com.agsolutions.td.Companion.lvlEvade, com.agsolutions.td.Companion.lvlXp, com.agsolutions.td.Companion.bigNumberScaler, com.agsolutions.td.Companion.levelScalerCrit,
                com.agsolutions.td.Companion.levelScalerSpeed,
                com.agsolutions.td.Companion.levelScalerCritDmg,
                com.agsolutions.td.Companion.levelScalerItemChance,
                com.agsolutions.td.Companion.lvlXpConstant,
                com.agsolutions.td.Companion.lvlHpReg,
                com.agsolutions.td.Companion.lvlSpd,
                com.agsolutions.td.Companion.levelScalerItemQuality
            )
            var saveDataListItems = arrayListOf(Items.itemListNormal, Items.itemListRare, Items.itemListEpic, Items.itemListLegendary)
        //    var saveEnemyList = arrayListOf<Enemy>()
        //    enemyList.forEach {
        //        saveEnemyList.add(it)
        //    }
            var mysteryPointsList = arrayListOf<Boolean>(com.agsolutions.td.Companion.mysteryAllRounderBool, com.agsolutions.td.Companion.mysterySongBool, com.agsolutions.td.Companion.mysteryClownBool, com.agsolutions.td.Companion.mysteryMaceBool, com.agsolutions.td.Companion.mysteryBowBool, com.agsolutions.td.Companion.mysterySwordBool, com.agsolutions.td.Companion.mysteryLuckyCharmBool, com.agsolutions.td.Companion.mysteryPirateHunterBool,
                com.agsolutions.td.Companion.mysteryBombsUsedBool, com.agsolutions.td.Companion.mysteryColdHeartBool, com.agsolutions.td.Companion.challengesKilledBool)

            /*
            var gameTalentButterfly = arrayListOf<Int>(ButterflyTalentFragment.butterflyRow1Item1, ButterflyTalentFragment.butterflyRow2Item1, ButterflyTalentFragment.butterflyRow2Item2, ButterflyTalentFragment.butterflyRow3Item1, ButterflyTalentFragment.butterflyRow4Item1)
            var gameTalentDark = arrayListOf<Int>(DarkTalentFragment.darkRow1Item1, DarkTalentFragment.darkRow2Item1, DarkTalentFragment.darkRow2Item2, DarkTalentFragment.darkRow3Item1, DarkTalentFragment.darkRow3Item2, DarkTalentFragment.darkRow4Item1)
            var gameTalentEarth = arrayListOf<Int>(EarthTalentFragment.earthRow1Item1, EarthTalentFragment.earthRow2Item1, EarthTalentFragment.earthRow2Item2, EarthTalentFragment.earthRow3Item1, EarthTalentFragment.earthRow4Item1)
            var gameTalentFire = arrayListOf<Int>(FireTalentFragment.fireRow1Item1, FireTalentFragment.fireRow2Item1, FireTalentFragment.fireRow2Item2, FireTalentFragment.fireRow3Item1, FireTalentFragment.fireRow3Item2, FireTalentFragment.fireRow4Item1)
            var gameTalentIce = arrayListOf<Int>(IceTalentFragment.iceRow1Item1, IceTalentFragment.iceRow2Item1, IceTalentFragment.iceRow2Item2, IceTalentFragment.iceRow3Item1, IceTalentFragment.iceRow3Item2, IceTalentFragment.iceRow4Item1)
            var gameTalentMoon = arrayListOf<Int>(MoonTalentFragment.moonRow1Item1, MoonTalentFragment.moonRow2Item1, MoonTalentFragment.moonRow2Item2, MoonTalentFragment.moonRow3Item1, MoonTalentFragment.moonRow4Item1)
            var gameTalentPoison = arrayListOf<Int>(PoisonTalentFragment.poisonRow1Item1, PoisonTalentFragment.poisonRow2Item1, PoisonTalentFragment.poisonRow2Item2, PoisonTalentFragment.poisonRow3Item1, PoisonTalentFragment.poisonRow3Item2, PoisonTalentFragment.poisonRow4Item1)
            var gameTalentUtils = arrayListOf<Int>(UtilsTalentFragment.utilsRow1Item1, UtilsTalentFragment.utilsRow1Item2, UtilsTalentFragment.utilsRow2Item1, UtilsTalentFragment.utilsRow2Item2, UtilsTalentFragment.utilsRow3Item1)
            var gameTalentWind = arrayListOf<Int>(WindTalentFragment.windRow1Item1, WindTalentFragment.windRow2Item1, WindTalentFragment.windRow2Item2, WindTalentFragment.windRow3Item1, WindTalentFragment.windRow3Item2, WindTalentFragment.windRow4Item1)
            var gameTalentWizard = arrayListOf<Int>(WizardTalentFragment.wizardRow1Item1, WizardTalentFragment.wizardRow2Item1, WizardTalentFragment.wizardRow2Item2, WizardTalentFragment.wizardRow3Item1, WizardTalentFragment.wizardRow3Item2, WizardTalentFragment.wizardRow4Item1)

            var saveDataListTalents = arrayListOf(gameTalentButterfly,gameTalentDark, gameTalentEarth,gameTalentFire,gameTalentIce,gameTalentMoon,gameTalentPoison, gameTalentUtils, gameTalentWind, gameTalentWizard)

             */
            val textFile = File("$filesDir/saveGame.dat")
            if (!textFile.exists()) {
                textFile.createNewFile()
            } else {
                textFile.delete()
                textFile.createNewFile()
            }
            val fos = FileOutputStream(textFile)
            val oos = ObjectOutputStream(fos)
            // saveDataListTalents
            var writeList = ArrayList(Arrays.asList(mysteryPointsList, com.agsolutions.td.Companion.globalItemListBag, com.agsolutions.td.Companion.levelList, com.agsolutions.td.Companion.levelListReserve,
                saveDataList, saveDataListCurrency, saveDataListLevelInfo, saveDataListItems, com.agsolutions.td.Companion.midnightMadnessSaveList, com.agsolutions.td.Companion.itemListBagMysteryEventCancelled, enemyList))
            oos.writeObject(writeList)
            oos.close()
            fos.close()

            // TODO itemlistbag for each tower

            GameActivity.gameEnd = 0
            mHandler.postDelayed({
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

        resumeBtn.setOnClickListener(){
            GameActivity.paused = false
            finish()
        }

        settingsBtn.setOnClickListener(){
            intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }

        wikiBtn.setOnClickListener(){
            intent = Intent(this, GameWiki::class.java)
            startActivity(intent)
        }


    }
    override fun onBackPressed() {
    }
}