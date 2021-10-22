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
    var one = 0
    var two = 0
    var three = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.talents)

        var screenwidth = (Resources.getSystem().displayMetrics.widthPixels * 0.9).toInt()
        var screenheight = (Resources.getSystem().displayMetrics.heightPixels * 0.8).toInt()


        window.setLayout((screenwidth).toInt(), (screenheight).toInt())
        window.setElevation(10F)

        GameActivity.companionList.focusTalentWindow = true
        GameActivity.companionList.focusMainWindow = false

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

    /*
    fun update () {

        if (GameActivity.companionList.focusTalentFragment) {
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

     */

    override fun onBackPressed() {
    }

    private fun initviews() {

        firstTalentBTN.visibility = View.INVISIBLE
        secondTalentBTN.visibility = View.INVISIBLE
        thirdTalentBTN.visibility = View.INVISIBLE

        var count = 1

        if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount > 0){
            GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.asReversed().forEach {
                if (it.element){
                    when (count){
                        1 -> one = GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.indexOf(it)
                        2 -> two = GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.indexOf(it)
                        3 -> three = GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.indexOf(it)
                    }
                    count++
                }
            }

        }

        if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount > 0){
                firstTalentBTN.visibility = View.VISIBLE
                when(GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[one]){
                    GameActivity.companionList.eearth -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementearth)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, earthFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.ewizard -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementwizard)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, wizardFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.eice -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementice)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, iceFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.ebutterfly -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementbutterfly)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, butterflyFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.epoison -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementpoison)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, poisonFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.emoon -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementmoon)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, moonFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.ewind -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementwind)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, windFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.eutils -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementutils)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, utilsFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.efire -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementfire)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, fireFragment)
                            commit()
                        }
                    }
                    GameActivity.companionList.edark -> {
                        firstTalentBTN.setBackgroundResource(R.drawable.elementdark)
                        supportFragmentManager.beginTransaction().apply {
                            replace(R.id.talentsFragment, darkFragment)
                            commit()
                        }
                    }
                }
            }
        if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount > 1) {
                secondTalentBTN.visibility = View.VISIBLE
                when (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[two]) {
                    GameActivity.companionList.eearth -> secondTalentBTN.setBackgroundResource(R.drawable.elementearth)
                    GameActivity.companionList.ewizard -> secondTalentBTN.setBackgroundResource(R.drawable.elementwizard)
                    GameActivity.companionList.eice -> secondTalentBTN.setBackgroundResource(R.drawable.elementice)
                    GameActivity.companionList.ebutterfly -> secondTalentBTN.setBackgroundResource(R.drawable.elementbutterfly)
                    GameActivity.companionList.epoison -> secondTalentBTN.setBackgroundResource(R.drawable.elementpoison)
                    GameActivity.companionList.emoon -> secondTalentBTN.setBackgroundResource(R.drawable.elementmoon)
                    GameActivity.companionList.ewind -> secondTalentBTN.setBackgroundResource(R.drawable.elementwind)
                    GameActivity.companionList.eutils -> secondTalentBTN.setBackgroundResource(R.drawable.elementutils)
                    GameActivity.companionList.efire -> secondTalentBTN.setBackgroundResource(R.drawable.elementfire)
                    GameActivity.companionList.edark -> secondTalentBTN.setBackgroundResource(R.drawable.elementdark)
                }
            }
        if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount > 2) {
                thirdTalentBTN.visibility = View.VISIBLE
                when(GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[three]){
                    GameActivity.companionList.eearth -> thirdTalentBTN.setBackgroundResource(R.drawable.elementearth)
                    GameActivity.companionList.ewizard -> thirdTalentBTN.setBackgroundResource(R.drawable.elementwizard)
                    GameActivity.companionList.eice -> thirdTalentBTN.setBackgroundResource(R.drawable.elementice)
                    GameActivity.companionList.ebutterfly -> thirdTalentBTN.setBackgroundResource(R.drawable.elementbutterfly)
                    GameActivity.companionList.epoison -> thirdTalentBTN.setBackgroundResource(R.drawable.elementpoison)
                    GameActivity.companionList.emoon -> thirdTalentBTN.setBackgroundResource(R.drawable.elementmoon)
                    GameActivity.companionList.ewind -> thirdTalentBTN.setBackgroundResource(R.drawable.elementwind)
                    GameActivity.companionList.eutils -> thirdTalentBTN.setBackgroundResource(R.drawable.elementutils)
                    GameActivity.companionList.efire -> thirdTalentBTN.setBackgroundResource(R.drawable.elementfire)
                    GameActivity.companionList.edark -> thirdTalentBTN.setBackgroundResource(R.drawable.elementdark)
                }
            }

        saveTalentsBTN.setOnClickListener() {
            GameActivity.companionList.focusTalentWindow = false
            GameActivity.companionList.focusMainWindow = true
            GameActivity.companionList.focusTalentFragment = false
            GameActivity.companionList.showHelpTalent = false
            GameActivity.paused = false
            mHandler.postDelayed({
                finish()
            }, 50)
        }
    }

    private fun fragments() {
        firstTalentBTN.setOnClickListener() {
            when(GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[one]){
                GameActivity.companionList.eearth -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, earthFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewizard -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, wizardFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eice -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, iceFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ebutterfly -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, butterflyFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.epoison -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, poisonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.emoon -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, moonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewind -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, windFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eutils -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, utilsFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.efire -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, fireFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.edark -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, darkFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
            }
        }
        secondTalentBTN.setOnClickListener() {
            when(GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[two]){
                GameActivity.companionList.eearth -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, earthFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewizard -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, wizardFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eice -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, iceFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ebutterfly -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, butterflyFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.epoison -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, poisonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.emoon -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, moonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewind -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, windFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eutils -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, utilsFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.efire -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, fireFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.edark -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, darkFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
            }
        }
        thirdTalentBTN.setOnClickListener() {
            when(GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[three]){
                GameActivity.companionList.eearth -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, earthFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewizard -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, wizardFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eice -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, iceFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ebutterfly -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, butterflyFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.epoison -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, poisonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.emoon -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, moonFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.ewind -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, windFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.eutils -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, utilsFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.efire -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, fireFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
                GameActivity.companionList.edark -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.talentsFragment, darkFragment)
                        GameActivity.companionList.focusTalentWindow = false
                        commit()
                    }
                }
            }
        }
    }
}