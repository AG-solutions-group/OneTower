package com.agsolutions.td

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import com.agsolutions.td.Companion.Companion.focusMainWindow
import com.agsolutions.td.Companion.Companion.focusTalentWindow
import com.agsolutions.td.Companion.Companion.showHelpTalent
import com.agsolutions.td.Companion.Companion.towerClick
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Fragments.*
import kotlinx.android.synthetic.main.talents.*

class Talents : AppCompatActivity() {
    companion object {
        var topBarHeight = 0f
    }

    var fireFragment = FireTalentFragment ()
    var iceFragment = IceTalentFragment ()
    var darkFragment = DarkTalentFragment ()
    var utilsFragment = UtilsTalentFragment ()
    var windFragment = WindTalentFragment ()
    var poisonFragment = PoisonTalentFragment ()
    var earthFragment = EarthTalentFragment ()
    var moonFragment = MoonTalentFragment ()
    var wizardFragment = WizardTalentFragment ()
    var butterflyFragment = ButterflyTalentFragment ()
    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.talents)

        var screenwidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        var screenheight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()

        window.setLayout((screenwidth).toInt(), (screenheight).toInt())
        window.setElevation(10F)

        com.agsolutions.td.Companion.focusTalentWindow = true
        focusMainWindow = false

        earthTalentBTN.doOnLayout {
            UiViewTalent.talentX = earthTalentBTN.x
            UiViewTalent.talentY = earthTalentBTN.y
        }
        topBarLayout.doOnLayout {
            topBarHeight = topBarLayout.height.toFloat()
        }

        initviews()
        fragments()
      //  update()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
      //  update()

    }

    fun update () {

        if (com.agsolutions.td.Companion.focusEarthFragment) {
        }else if(focusTalentWindow) {
            if ( com.agsolutions.td.Companion.hintsBool) {
                    //     var point = IntArray(2)
                    //     earthTalentBTN.getLocationInWindow(point)
                    //     UiViewTalent.talentX = point[0].toFloat()
                    //     UiViewTalent.talentY = point[1].toFloat()
                    UiViewTalent.talentX = earthTalentBTN.x
                    UiViewTalent.talentY = earthTalentBTN.y
            }
            uiViewTalent.invalidate()
        }
    }

    override fun onBackPressed() {
    }

    private fun initviews() {

        earthTalentBTN.visibility = View.INVISIBLE
        wizardTalentBTN.visibility = View.INVISIBLE
        iceTalentBTN.visibility = View.INVISIBLE
        butterflyTalentBTN.visibility = View.INVISIBLE
        poisonTalentBTN.visibility = View.INVISIBLE
        moonTalentBTN.visibility = View.INVISIBLE
        windTalentBTN.visibility = View.INVISIBLE
        utilsTalentBTN.visibility = View.INVISIBLE
        fireTalentBTN.visibility = View.INVISIBLE
        darkTalentBTN.visibility = View.INVISIBLE


        if (towerClick){
            if (towerList[towerClickID].bagSizeElementCount > 0){
                if (towerList[towerClickID].itemListBag.contains(Items.eearth)) earthTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.ewizard)) wizardTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.eice)) iceTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.ebutterfly)) butterflyTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.epoison)) poisonTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.emoon)) moonTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.ewind)) windTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.eutils)) utilsTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.efire)) fireTalentBTN.visibility = View.VISIBLE
                if (towerList[towerClickID].itemListBag.contains(Items.edark)) darkTalentBTN.visibility = View.VISIBLE
            }
        }

        saveTalentsBTN.setOnClickListener() {
            com.agsolutions.td.Companion.focusTalentWindow = false
            focusMainWindow = true
            com.agsolutions.td.Companion.focusEarthFragment = false
            showHelpTalent = false
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }
    }

    private fun fragments() {
        fireTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, fireFragment)
                commit()
            }
        }
        iceTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, iceFragment)
                commit()
            }
        }
        darkTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, darkFragment)
                commit()
            }
        }
        utilsTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, utilsFragment)
                commit()
            }
        }
        poisonTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, poisonFragment)
                commit()
            }
        }
        windTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, windFragment)
                commit()
            }
        }
        earthTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                com.agsolutions.td.Companion.focusTalentWindow = false
                uiViewTalent.invalidate()
                replace(R.id.talentsFragment, earthFragment)
                commit()
            }
        }
        moonTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, moonFragment)
                commit()
            }
        }
        wizardTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, wizardFragment)
                commit()
            }
        }
        butterflyTalentBTN.setOnClickListener() {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.talentsFragment, butterflyFragment)
                commit()
            }
        }
    }
}