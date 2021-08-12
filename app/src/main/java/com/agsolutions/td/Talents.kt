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

        if (ButterflyTalentFragment.butterflyRow4Item1 == 3 || DarkTalentFragment.darkRow4Item1 == 3 || EarthTalentFragment.earthRow4Item1 == 3 || FireTalentFragment.fireRow4Item1 == 3 || IceTalentFragment.iceRow4Item1 == 3 ||
            MoonTalentFragment.moonRow4Item1 == 3 || PoisonTalentFragment.poisonRow4Item1 == 3 || WindTalentFragment.windRow4Item1 == 3 || WizardTalentFragment.wizardRow4Item1 == 3) {
            earthTalentBTN.visibility = View.VISIBLE
            wizardTalentBTN.visibility = View.VISIBLE
            iceTalentBTN.visibility = View.VISIBLE
            butterflyTalentBTN.visibility = View.VISIBLE
            poisonTalentBTN.visibility = View.VISIBLE
            moonTalentBTN.visibility = View.VISIBLE
            windTalentBTN.visibility = View.VISIBLE
            utilsTalentBTN.visibility = View.VISIBLE
            fireTalentBTN.visibility = View.VISIBLE
            darkTalentBTN.visibility = View.VISIBLE
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