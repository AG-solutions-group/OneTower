package com.agsolutions.td

import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
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

        GameActivity.companionList.focusTalentWindow = true
        GameActivity.companionList.focusMainWindow = false

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

        if (GameActivity.companionList.focusEarthFragment) {
        }else if(GameActivity.companionList.focusTalentWindow) {
            if ( GameActivity.companionList.hintsBool) {
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


        if (GameActivity.companionList.towerClick){
            if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount > 0){
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.eearth)) earthTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.ewizard)) wizardTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.eice)) iceTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.ebutterfly)) butterflyTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.epoison)) poisonTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.emoon)) moonTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.ewind)) windTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.eutils)) utilsTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.efire)) fireTalentBTN.visibility = View.VISIBLE
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.edark)) darkTalentBTN.visibility = View.VISIBLE
            }
        }

        saveTalentsBTN.setOnClickListener() {
            GameActivity.companionList.focusTalentWindow = false
            GameActivity.companionList.focusMainWindow = true
            GameActivity.companionList.focusEarthFragment = false
            GameActivity.companionList.showHelpTalent = false
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
                GameActivity.companionList.focusTalentWindow = false
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