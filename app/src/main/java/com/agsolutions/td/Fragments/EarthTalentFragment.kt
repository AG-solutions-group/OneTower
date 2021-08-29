package com.agsolutions.td.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.earthTalentBonusPhysicalDmg
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import com.agsolutions.td.Talents.Companion.topBarHeight
import com.agsolutions.td.UiViewTalentEarth
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

        com.agsolutions.td.Companion.focusEarthFragment = true

        earthRow1Item1ShowTV.text = towerList[towerClickID].earthRow1Item1.toString()
        earthRow1Item2ShowTV.text = towerList[towerClickID].earthRow1Item2.toString()
        earthRow2Item1ShowTV.text = towerList[towerClickID].earthRow2Item1.toString()
        earthRow2Item2ShowTV.text = towerList[towerClickID].earthRow2Item2.toString()
        earthRow3Item1ShowTV.text = towerList[towerClickID].earthRow3Item1.toString()
        earthRow3Item2ShowTV.text = towerList[towerClickID].earthRow3Item2.toString()
        earthRow3Item3ShowTV.text = towerList[towerClickID].earthRow3Item3.toString()
        earthRow4Item1ShowTV.text = towerList[towerClickID].earthRow4Item1.toString()

        if (towerList[towerClickID].earthRow1Item1 == 1 || towerList[towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].earthRow3Item1 + towerList[towerClickID].earthRow3Item2 + towerList[towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        earthRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                earthUpgradeBTN.getLocationInWindow(point)
                UiViewTalentEarth.talentEarthX = point[0].toFloat()
                UiViewTalentEarth.talentEarthY = (point[1] - topBarHeight).toFloat()
                uiViewTalentEarth.invalidate()

            earthNameDisplayTalentTV.text = "Killer"
            earthDisplayTalentTV.text = "Increases experience gain from kills increased by 100%. Base 50% of enemy."

            earthUpgradeBTN.setOnClickListener() {
                com.agsolutions.td.Companion.focusEarthFragment = false
                uiViewTalentEarth.invalidate()
                earthUpgradeBTN.isClickable = true

                if (towerList[towerClickID].earthRow1Item1 + towerList[towerClickID].earthRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0){
                    towerList[towerClickID].earthRow1Item1 += 1

                    if (towerList[towerClickID].earthRow1Item1 == 1) towerList[towerClickID].experienceKill += 0.5f

                    if (towerList[towerClickID].earthRow1Item1 == 1 || towerList[towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    earthRow1Item1ShowTV.text = towerList[towerClickID].earthRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        earthRow1Item2IB.setOnClickListener() {

            earthNameDisplayTalentTV.text = "Earthly Wisdom"
            earthDisplayTalentTV.text = "Occasionally gain experience when shots hit more than 3 enemies. "

            earthUpgradeBTN.setOnClickListener() {
                com.agsolutions.td.Companion.focusEarthFragment = false
                uiViewTalentEarth.invalidate()
                earthUpgradeBTN.isClickable = true


                if (towerList[towerClickID].earthRow1Item1 + towerList[towerClickID].earthRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0){
                    towerList[towerClickID].earthRow1Item2 += 1

                    if (towerList[towerClickID].earthRow1Item2 == 1) towerList[towerClickID].experienceEarthHit = true

                    if (towerList[towerClickID].earthRow1Item1 == 1 || towerList[towerClickID].earthRow1Item2 == 1) backgroundEarthRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    earthRow1Item2ShowTV.text = towerList[towerClickID].earthRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        earthRow2Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Boulder"
            earthDisplayTalentTV.text = "Extends the area of effect to hits. Does not stack with multishot, bounce or BUTTERFLY single target abilites. Only applies debuffs to main target."
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow1Item1 == 1 || towerList[towerClickID].earthRow1Item2 == 1) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow2Item1 += 1

                        if (towerList[towerClickID].earthRow2Item1 == 1) towerList[towerClickID].splashRange += 50.0f

                        if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item1ShowTV.text = towerList[towerClickID].earthRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow2Item2IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Stone Axe"
            earthDisplayTalentTV.text = "Increases hit chance by 1%/2%/3% and global physical damage by 1/3/5."
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow1Item1 == 1 || towerList[towerClickID].earthRow1Item2 == 1) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow2Item2 += 1


                        if (towerList[towerClickID].earthRow2Item2 == 1) {
                            earthTalentBonusPhysicalDmg += 4.0f
                            towerList[towerClickID].earthTalentBonusHitChance += 1.0f
                        }
                        if (towerList[towerClickID].earthRow2Item2 == 2) {
                            earthTalentBonusPhysicalDmg += 4.0f
                            towerList[towerClickID].earthTalentBonusHitChance += 1.0f
                        }
                        if (towerList[towerClickID].earthRow2Item2 == 3) {
                            earthTalentBonusPhysicalDmg += 4.0f
                            towerList[towerClickID].earthTalentBonusHitChance += 1.0f
                        }

                        if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) backgroundEarthRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow2Item2ShowTV.text = towerList[towerClickID].earthRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Boulder Throw"
            earthDisplayTalentTV.text = "Throws a boulder each 6/5/4 seconds that stuns enemies and deals physical dmg equal to tower damage * 0.5/0.66/0.75. Spell."
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow3Item1 += 1

                        if (towerList[towerClickID].earthRow3Item1 == 1) {
                            towerList[towerClickID].throwBoulder = 0.5f
                            towerList[towerClickID].throwBoulderTimer = 360
                        }
                        if (towerList[towerClickID].earthRow3Item1 == 2) {
                            towerList[towerClickID].throwBoulder = 0.66f
                            towerList[towerClickID].throwBoulderTimer = 300
                        }
                        if (towerList[towerClickID].earthRow3Item1 == 3) {
                            towerList[towerClickID].throwBoulder = 0.75f
                            towerList[towerClickID].throwBoulderTimer = 240
                        }

                        if (towerList[towerClickID].earthRow3Item1 + towerList[towerClickID].earthRow3Item2 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item1ShowTV.text = towerList[towerClickID].earthRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item2IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = ""
            earthDisplayTalentTV.text = ""
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow3Item2 += 1

                        if (towerList[towerClickID].earthRow3Item2 == 1) {

                        }
                        if (towerList[towerClickID].earthRow3Item2 == 2) {

                        }
                        if (towerList[towerClickID].earthRow3Item2 == 3) {

                        }

                        if (towerList[towerClickID].earthRow3Item1 + towerList[towerClickID].earthRow3Item2 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item2ShowTV.text = towerList[towerClickID].earthRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow3Item3IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Enhancer"
            earthDisplayTalentTV.text = "Increases attack damage of nearby towers by an additional 5/10/15%"
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow2Item1 + towerList[towerClickID].earthRow2Item2 >= 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow3Item3 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow3Item3 += 1

                        if (towerList[towerClickID].earthRow3Item3 == 1) towerList[towerClickID].earthExtraTowerDmg += 0.05f
                        if (towerList[towerClickID].earthRow3Item3 == 2) towerList[towerClickID].earthExtraTowerDmg += 0.05f
                        if (towerList[towerClickID].earthRow3Item3 == 3) towerList[towerClickID].earthExtraTowerDmg += 0.05f

                        if (towerList[towerClickID].earthRow3Item1 + towerList[towerClickID].earthRow3Item2 + towerList[towerClickID].earthRow3Item3 >= 3) backgroundEarthRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        earthRow3Item3ShowTV.text = towerList[towerClickID].earthRow3Item3.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        earthRow4Item1IB.setOnClickListener() {
            earthNameDisplayTalentTV.text = "Mountaintop"
            earthDisplayTalentTV.text = "Increases physical damage by 10%/20%/30%. +1 bag space item at 3/3."
            earthUpgradeBTN.isClickable = false

            if (towerList[towerClickID].earthRow3Item1 == 3) {
                earthUpgradeBTN.isClickable = true

                earthUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].earthRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].earthRow4Item1 += 1

                        if (towerList[towerClickID].earthRow4Item1 == 1) towerList[towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        if (towerList[towerClickID].earthRow4Item1 == 2) towerList[towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        if (towerList[towerClickID].earthRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            towerList[towerClickID].earthTalentPhyDmgMultiplier += 0.1f
                        }

                        earthRow4Item1ShowTV.text = towerList[towerClickID].earthRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
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