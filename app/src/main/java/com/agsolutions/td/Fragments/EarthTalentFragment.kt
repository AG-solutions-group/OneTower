package com.agsolutions.td.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import com.agsolutions.td.Talents.Companion.topBarHeight
import com.agsolutions.td.UiViewTalentWindow
import kotlinx.android.synthetic.main.fragment_earth_talent.*


class EarthTalentFragment : Fragment() {

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

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        earthRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow1Item1.toString()
        earthRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow1Item2.toString()
        earthRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow2Item1.toString()
        earthRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow2Item2.toString()
        earthRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item1.toString()
        earthRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item2.toString()
        earthRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item3.toString()
        earthRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1) {
            earthRow1Item2IB.isClickable = false
            earthRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) {
            earthRow1Item1IB.isClickable = false
            earthRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 + companionList.towerList[companionList.towerClickID].earthRow3Item2 + companionList.towerList[companionList.towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        earthRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                earthUpgradeBTN.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - topBarHeight).toFloat()
                uiViewTalentEarth.invalidate()

            earthUpgradeBTN.isClickable = true
            setImagePick(11)

            earthNameDisplayTalentTV.text = "Killer"
            earthDisplayTalentTV.text = "Increases experience gain from kills by 10%."

            earthUpgradeBTN.setOnClickListener() {
                companionList.focusTalentFragment = false
                uiViewTalentEarth.invalidate()

                if (companionList.towerList[companionList.towerClickID].earthRow1Item1 + companionList.towerList[companionList.towerClickID].earthRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0){
                    companionList.towerList[companionList.towerClickID].earthRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].experienceKill += 0.25f
                        earthRow1Item2IB.isClickable = false
                        earthRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    earthRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        earthRow1Item2IB.setOnClickListener() {

            earthUpgradeBTN.isClickable = true
            setImagePick(12)

            earthNameDisplayTalentTV.text = "Earthly Wisdom"
            earthDisplayTalentTV.text = "Occasionally gain experience when shots hit more than 3 enemies. "

            earthUpgradeBTN.setOnClickListener() {
                companionList.focusTalentFragment = false
                uiViewTalentEarth.invalidate()

                if (companionList.towerList[companionList.towerClickID].earthRow1Item1 + companionList.towerList[companionList.towerClickID].earthRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0){
                    companionList.towerList[companionList.towerClickID].earthRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) {
                        companionList.towerList[companionList.towerClickID].experienceEarthHit = true
                        earthRow1Item1IB.isClickable = false
                        earthRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    earthRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        earthRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            earthNameDisplayTalentTV.text = "Boulder"
            earthDisplayTalentTV.text = "Extends the area of effect to hits. Does not stack with multishot, bounce or BUTTERFLY single target abilites. Only applies debuffs to main target."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 == 1) companionList.towerList[companionList.towerClickID].splashRange += 20.0f
                        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 == 2) companionList.towerList[companionList.towerClickID].splashRange += 20.0f
                        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 == 3) companionList.towerList[companionList.towerClickID].splashRange += 20.0f

                        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            earthNameDisplayTalentTV.text = "Stone Axe"
            earthDisplayTalentTV.text = "Increases hit chance by 1%/2%/3% and global physical damage by 4/8/12."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow2Item2 += 1


                        if (companionList.towerList[companionList.towerClickID].earthRow2Item2 == 1) {
                            companionList.earthTalentBonusPhysicalDmg += 4.0f
                            companionList.towerList[companionList.towerClickID].earthTalentBonusHitChance += 1.0f
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow2Item2 == 2) {
                            companionList.earthTalentBonusPhysicalDmg += 4.0f
                            companionList.towerList[companionList.towerClickID].earthTalentBonusHitChance += 1.0f
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow2Item2 == 3) {
                            companionList.earthTalentBonusPhysicalDmg += 4.0f
                            companionList.towerList[companionList.towerClickID].earthTalentBonusHitChance += 1.0f
                        }

                        if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            earthNameDisplayTalentTV.text = "Boulder Throw"
            earthDisplayTalentTV.text = "Throws a boulder each 6/5/4 seconds that stuns enemies and deals physical dmg equal to tower damage * 0.5/0.66/0.75. Spell."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].throwBoulder = 0.5f
                            companionList.towerList[companionList.towerClickID].throwBoulderTimer = 360
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].throwBoulder = 0.66f
                            companionList.towerList[companionList.towerClickID].throwBoulderTimer = 300
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].throwBoulder = 0.75f
                            companionList.towerList[companionList.towerClickID].throwBoulderTimer = 240
                        }

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 + companionList.towerList[companionList.towerClickID].earthRow3Item2 + companionList.towerList[companionList.towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            earthNameDisplayTalentTV.text = "Rock Solid"
            earthDisplayTalentTV.text = "Increases damage against immune by 10%/20%/30%."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].earthDmgImmune += 0.1f
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item2 == 2) {
                            companionList.towerList[companionList.towerClickID].earthDmgImmune += 0.1f
                        }
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item2 == 3) {
                            companionList.towerList[companionList.towerClickID].earthDmgImmune += 0.1f
                        }

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 + companionList.towerList[companionList.towerClickID].earthRow3Item2 + companionList.towerList[companionList.towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item3IB.setOnClickListener() {

            setImagePick(33)

            earthNameDisplayTalentTV.text = "Enhancer"
            earthDisplayTalentTV.text = "Increases damage of nearby towers by 5%/10%/15%."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow2Item1 + companionList.towerList[companionList.towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow3Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow3Item3 += 1

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item3 == 1) companionList.towerList[companionList.towerClickID].earthExtraTowerDmg += 0.05f
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item3 == 2) companionList.towerList[companionList.towerClickID].earthExtraTowerDmg += 0.05f
                        if (companionList.towerList[companionList.towerClickID].earthRow3Item3 == 3) companionList.towerList[companionList.towerClickID].earthExtraTowerDmg += 0.05f

                        if (companionList.towerList[companionList.towerClickID].earthRow3Item1 + companionList.towerList[companionList.towerClickID].earthRow3Item2 + companionList.towerList[companionList.towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow3Item3.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            earthNameDisplayTalentTV.text = "Mountaintop"
            earthDisplayTalentTV.text = "Increases physical damage by 10%/20%/30%. +1 bag space item at 3/3."
            earthUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].earthRow3Item1 + companionList.towerList[companionList.towerClickID].earthRow3Item2 + companionList.towerList[companionList.towerClickID].earthRow3Item3 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].earthRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].earthRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].earthRow4Item1 == 1) companionList.towerList[companionList.towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        if (companionList.towerList[companionList.towerClickID].earthRow4Item1 == 2) companionList.towerList[companionList.towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        if (companionList.towerList[companionList.towerClickID].earthRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.towerList[companionList.towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        }

                        earthRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].earthRow4Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
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
        if (GameActivity.companionList.focusTalentFragment) {
            if (GameActivity.companionList.hintsBool) {
                    var point = IntArray(2)
                    earthRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - topBarHeight).toFloat()
                    uiViewTalentEarth.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        earthRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow3Item3IBPick.setImageResource(R.drawable.overlaytransparent)
        earthRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> earthRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> earthRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> earthRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> earthRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> earthRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> earthRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            33 -> earthRow3Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> earthRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }

        if (companionList.towerList[companionList.towerClickID].earthRow1Item1 == 1) {
            earthRow1Item2IB.isClickable = false
            earthRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].earthRow1Item2 == 1) {
            earthRow1Item1IB.isClickable = false
            earthRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }
    }
}