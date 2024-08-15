package com.agsolutions.td

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.GameActivity.Companion.paused
import com.agsolutions.td.Main.MainActivity
import com.agsolutions.td.databinding.GameMenuBinding
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
    private lateinit var binding: GameMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GameMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPref = getSharedPreferences("Global", PRIVATE_MODE)


        window.setLayout((400.0f * ((companionList.scaleScreen) /10)).toInt(), (800.0f * ((companionList.scaleScreen) /10)).toInt())
        window.setElevation(10F)

        if (companionList.level > 0 && companionList.mapPick != 0) binding.saveAndQuitBtn.visibility = View.VISIBLE

        binding.quitBtn.setOnClickListener(){
            var editor = sharedPref!!.edit()
            editor.putBoolean("continueGame", false)
            editor.apply()

            GameActivity.gameEnd = 0
            mHandler.postDelayed({
                finish()
            }, 50)
        }

        binding.saveAndQuitBtn.setOnClickListener() {
            paused = true
            var editor = sharedPref!!.edit()
            editor.putBoolean("continueGame", true)
            editor.putInt("continueGameMapPick", companionList.mapPick)
            editor.putInt("continueGameMapMode", companionList.mapMode)
            editor.apply()

            companionList.shootList.removeAll(companionList.shootList)
            companionList.shootListPoison.removeAll(companionList.shootListPoison)
            companionList.shootListTornado.removeAll(companionList.shootListTornado)
            companionList.dmgDisplayList.removeAll(companionList.dmgDisplayList)
            companionList.dmgDisplayDropList.removeAll(companionList.dmgDisplayDropList)
            companionList.soundBoolDay = true
            companionList.soundBoolNight = true

            val textFile = File("$filesDir/saveGame.dat")
            if (!textFile.exists()) {
                textFile.createNewFile()
            } else {
                textFile.delete()
                textFile.createNewFile()
            }
            val fos = FileOutputStream(textFile)
            val oos = ObjectOutputStream(fos)

            var writeList = ArrayList(Arrays.asList(companionList))
            oos.writeObject(writeList)
            oos.close()
            fos.close()

            GameActivity.gameEnd = 0
            mHandler.postDelayed({
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

        binding.resumeBtn.setOnClickListener(){
            GameActivity.paused = false
            finish()
        }

        binding.settingsBtn.setOnClickListener(){
            intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }

        binding.wikiBtn.setOnClickListener(){
            companionList.toastGlobal = true
            companionList.toastText = "Coming Soon"
        }
    }
}