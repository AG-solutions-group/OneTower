package com.agsolutions.td.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.earthArmorSmasher
import com.agsolutions.td.Companion.Companion.earthTalentBonusHitChance
import com.agsolutions.td.Companion.Companion.earthTalentBonusPhysicalDmg
import com.agsolutions.td.Companion.Companion.earthTalentPhyDmgMultiplier
import com.agsolutions.td.Companion.Companion.hpRegDebuffGlobal
import com.agsolutions.td.Companion.Companion.shotBounce
import com.agsolutions.td.Companion.Companion.singleTargetBoost
import com.agsolutions.td.Companion.Companion.splashRange
import com.agsolutions.td.Companion.Companion.talentMultishot
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Companion.Companion.throwBoulder
import com.agsolutions.td.Items
import com.agsolutions.td.R
import com.agsolutions.td.Talents.Companion.topBarHeight
import com.agsolutions.td.UiViewTalentEarth
import kotlinx.android.synthetic.main.fragment_earth_talent.*


class EarthTalentFragment : Fragment() {
    companion object {
        var earthRow1Item1 = 0
        var earthRow2Item1 = 0
        var earthRow2Item2 = 0
        var earthRow3Item1 = 0
        var earthRow4Item1 = 0

        fun earthTalentsLoad() {

            if (earthRow1Item1 == 1) {
                earthTalentBonusPhysicalDmg += 2.0f
                earthTalentBonusHitChance += 1.0f
            }
            if (earthRow1Item1 == 2) {
                earthTalentBonusPhysicalDmg += 4.0f
                earthTalentBonusHitChance += 2.0f
            }
            if (earthRow1Item1 == 3) {
                earthTalentBonusPhysicalDmg += 8.0f
                earthTalentBonusHitChance += 3.0f
            }

            if (earthRow2Item1 == 1) {
                earthTalentPhyDmgMultiplier += 1.0f
                com.agsolutions.td.Companion.overallSpdMultiplier -= 100
                splashRange += 75.0f
            }
            if (earthRow2Item1 == 2) {
                earthTalentPhyDmgMultiplier += 1.0f
                com.agsolutions.td.Companion.overallSpdMultiplier -= 100
                splashRange += 100.0f
            }
            if (earthRow2Item1 == 3) {
                earthTalentPhyDmgMultiplier += 1.0f
                com.agsolutions.td.Companion.overallSpdMultiplier -= 100
                splashRange += 150.0f
            }

            if (earthRow2Item2 == 1) {
                hpRegDebuffGlobal += 0.1f
                earthArmorSmasher = 0.9f
            }
            if (earthRow2Item2 == 2) {
                hpRegDebuffGlobal += 0.2f
                earthArmorSmasher = 0.8f
            }
            if (earthRow2Item2 == 3) {
                hpRegDebuffGlobal += 0.3f
                earthArmorSmasher = 0.7f
            }

            if (earthRow3Item1 == 1) {
                throwBoulder = 1.25f
                com.agsolutions.td.Companion.throwBoulderTimer = 360
            }
            if (earthRow3Item1 == 2) {
                throwBoulder = 1.5f
                com.agsolutions.td.Companion.throwBoulderTimer = 300
            }
            if (earthRow3Item1 == 3) {
                throwBoulder = 1.75f
                com.agsolutions.td.Companion.throwBoulderTimer = 240
            }

            if (earthRow4Item1 == 1) earthTalentPhyDmgMultiplier += 0.1f
            if (earthRow4Item1 == 2) earthTalentPhyDmgMultiplier += 0.2f
            if (earthRow4Item1 == 3) earthTalentPhyDmgMultiplier += 0.3f

        }
    }

    var mHandler = Handler ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_earth_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }
        /*
        mainLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

                override fun onGlobalLayout() {
                    mainLayout.viewTreeObserver.removeOnGlobalLayoutListener(this);


                    // measure your views here
                }

        })

         */

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        com.agsolutions.td.Companion.focusEarthFragment = true

        earthRow1Item1ShowTV.text = earthRow1Item1.toString()
        earthRow2Item1ShowTV.text = earthRow2Item1.toString()
        earthRow2Item2ShowTV.text = earthRow2Item2.toString()
        earthRow3Item1ShowTV.text = earthRow3Item1.toString()
        earthRow4Item1ShowTV.text = earthRow4Item1.toString()

        if (earthRow1Item1 >= 3) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (earthRow2Item1 + earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (earthRow3Item1 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        earthRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                earthUpgradeBTN.getLocationInWindow(point)
                UiViewTalentEarth.talentEarthX = point[0].toFloat()
                UiViewTalentEarth.talentEarthY = (point[1] - topBarHeight).toFloat()
                uiViewTalentEarth.invalidate()

            earthNameDisplayTalentTV.text = "Stone Axe"
            earthDisplayTalentTV.text = "Increases hit chance by 1%/2%/3% and physical damage by 2/4/8."

            earthUpgradeBTN.setOnClickListener() {
                com.agsolutions.td.Companion.focusEarthFragment = false
                uiViewTalentEarth.invalidate()
                earthUpgradeBTN.isClickable = true

                if (earthRow1Item1 <= 2 && talentPoints > 0){
                    earthRow1Item1 += 1


                    if (earthRow1Item1 == 1) {
                        earthTalentBonusPhysicalDmg += 2.0f
                        earthTalentBonusHitChance += 1.0f
                    }
                    if (earthRow1Item1 == 2) {
                        earthTalentBonusPhysicalDmg += 2.0f
                        earthTalentBonusHitChance += 1.0f
                    }
                    if (earthRow1Item1 == 3) {
                        earthTalentBonusPhysicalDmg += 4.0f
                        earthTalentBonusHitChance += 1.0f
                    }

                    if (earthRow1Item1 >= 3) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    earthRow1Item1ShowTV.text = earthRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        earthRow2Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Boulder"
            earthDisplayTalentTV.text = "Plus 100% physical damage. Minus 100% attack speed. Adds a small/medium/large area of effect to areas. Does not stack with multishot, bounce or BUTTERFLY single target abilites. Only applies debuffs to main target."
            earthUpgradeBTN.isClickable = false

            if (talentMultishot || shotBounce || singleTargetBoost) {

            }else if (earthRow1Item1 == 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (earthRow2Item1 <= 2 && talentPoints > 0) {
                        earthRow2Item1 += 1

                        if (earthRow2Item1 == 1) {
                            earthTalentPhyDmgMultiplier += 1.0f
                            com.agsolutions.td.Companion.overallSpdMultiplier -= 100
                            splashRange += 75.0f
                        }
                        if (earthRow2Item1 == 2) splashRange += 25.0f
                        if (earthRow2Item1 == 3) splashRange += 50.0f

                        if (earthRow2Item1 + earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item1ShowTV.text = earthRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        earthRow2Item2IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Sharp Stone"
            earthDisplayTalentTV.text = "Decreases armor of enemies with type armor by 10%/20%/30% & reduces hitpoint regeneration by 10/20/30%"
            earthUpgradeBTN.isClickable = false

            if (earthRow1Item1 == 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (earthRow2Item2 <= 2 && talentPoints > 0) {
                        earthRow2Item2 += 1


                        if (earthRow2Item2 == 1) {
                            hpRegDebuffGlobal += 0.1f
                            earthArmorSmasher = 0.9f
                        }
                        if (earthRow2Item2 == 2) {
                            hpRegDebuffGlobal += 0.1f
                            earthArmorSmasher = 0.8f
                        }
                        if (earthRow2Item2 == 3) {
                            hpRegDebuffGlobal += 0.1f
                            earthArmorSmasher = 0.7f
                        }

                        if (earthRow2Item1 + earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item2ShowTV.text = earthRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Boulder Throw"
            earthDisplayTalentTV.text = "Throws a boulder each 6/5/4 seconds that stuns enemies and deals physical dmg equal to tower damage * 0.5/0.66/0.75. Spell."
            earthUpgradeBTN.isClickable = false

            if (earthRow2Item1 + earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (earthRow3Item1 <= 2 && talentPoints > 0) {
                        earthRow3Item1 += 1

                        if (earthRow3Item1 == 1) {
                            throwBoulder = 0.5f
                            com.agsolutions.td.Companion.throwBoulderTimer = 360
                        }
                        if (earthRow3Item1 == 2) {
                            throwBoulder = 0.66f
                            com.agsolutions.td.Companion.throwBoulderTimer = 300
                        }
                        if (earthRow3Item1 == 3) {
                            throwBoulder = 0.75f
                            com.agsolutions.td.Companion.throwBoulderTimer = 240
                        }

                        if (earthRow3Item1 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item1ShowTV.text = earthRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        earthRow4Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Mountaintop"
            earthDisplayTalentTV.text = "Increases physical damage by 10%/20%/30%. +1 bag space at 3/3."
            earthUpgradeBTN.isClickable = false

            if (earthRow3Item1 == 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (earthRow4Item1 <= 2 && talentPoints > 0) {
                        earthRow4Item1 += 1

                        if (earthRow4Item1 == 1) earthTalentPhyDmgMultiplier += 0.1f
                        if (earthRow4Item1 == 2) earthTalentPhyDmgMultiplier += 0.1f
                        if (earthRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            earthTalentPhyDmgMultiplier += 0.1f
                        }

                        earthRow4Item1ShowTV.text = earthRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        update()
    }

    fun update () {
        if (com.agsolutions.td.Companion.focusEarthFragment) {
            if (com.agsolutions.td.Companion.hintsBool) {
                    var point = IntArray(2)
                    earthRow1Item1IB.getLocationInWindow(point)
                UiViewTalentEarth.talentEarthX = point[0].toFloat()
                UiViewTalentEarth.talentEarthY = (point[1] - topBarHeight).toFloat()
                    uiViewTalentEarth.invalidate()
            }
        }
    }
}