package com.agsolutions.td.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.agsolutions.td.*
import com.agsolutions.td.GameActivity.Companion.companionList
import kotlinx.android.synthetic.main.fragment_poison_talent.*
import kotlinx.android.synthetic.main.fragment_wind_talent.*


class WindTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_wind_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        windRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow1Item1.toString()
        windRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow1Item2.toString()
        windRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow2Item1.toString()
        windRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow2Item2.toString()
        windRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item1.toString()
        windRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item2.toString()
        windRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item3.toString()
        windRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1) {
            windRow1Item2IB.isClickable = false
            windRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) {
            windRow1Item1IB.isClickable = false
            windRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].windRow3Item1 + companionList.towerList[companionList.towerClickID].windRow3Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        windRow1Item1IB.setOnClickListener() {

            var point = IntArray(2)
            windUpgradeBTN.getLocationInWindow(point)
            UiViewTalentWindow.talentX = point[0].toFloat()
            UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
            uiViewTalentWind.invalidate()

            setImagePick(11)

            windNameDisplayTalentTV.text = "Speedy Recovery "
            windDisplayTalentTV.text = "Gain experience for each hit (3% of enemy)."
            windUpgradeBTN.isClickable = true

            windUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentWind.invalidate()

                if (companionList.towerList[companionList.towerClickID].windRow1Item1 + companionList.towerList[companionList.towerClickID].windRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].windRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].experienceEachHit = true
                        windRow1Item2IB.isClickable = false
                        windRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    windRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        windRow1Item2IB.setOnClickListener() {

            setImagePick(12)

            windNameDisplayTalentTV.text = "Air Drop"
            windDisplayTalentTV.text = "Each hit has a chance to grant a small amount of gold"
            windUpgradeBTN.isClickable = true

            windUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentWind.invalidate()


                if (companionList.towerList[companionList.towerClickID].windRow1Item1 + companionList.towerList[companionList.towerClickID].windRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].windRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].windRow1Item2 == 1){
                        companionList.towerList[companionList.towerClickID].goldDrop = true
                        windRow1Item1IB.isClickable = false
                        windRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    windRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        windRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            windNameDisplayTalentTV.text = "Multishot"
            windDisplayTalentTV.text = "Debuff damage increased permanently by 10/20/30% after hit."
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow2Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].windTalentDebuff += 0.1f
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow2Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].windTalentDebuff += 0.1f
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow2Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].windTalentDebuff += 0.1f
                        }

                        if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            windRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            windNameDisplayTalentTV.text = "High Ground"
            windDisplayTalentTV.text = "Increases Wind tower range by 10/20/30 units and all towers by 3/6/9 units."
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow2Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow2Item2 == 1) {
                            companionList.globalBonusTowerRange += 3
                            companionList.towerList[companionList.towerClickID].windTowerBonusTowerRange += 10
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow2Item2 == 2) {
                            companionList.globalBonusTowerRange += 3
                            companionList.towerList[companionList.towerClickID].windTowerBonusTowerRange += 10
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow2Item2 == 3) {
                            companionList.globalBonusTowerRange += 3
                            companionList.towerList[companionList.towerClickID].windTowerBonusTowerRange += 10
                        }

                        if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            windNameDisplayTalentTV.text = "Pushback"
            windDisplayTalentTV.text = "Pushes enemies back 80/120/160 units."
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 == 1) companionList.towerList[companionList.towerClickID].pushBack = 80.0f
                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 == 2) companionList.towerList[companionList.towerClickID].pushBack = 120.0f
                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 == 3) companionList.towerList[companionList.towerClickID].pushBack = 160.0f

                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 + companionList.towerList[companionList.towerClickID].windRow3Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            windNameDisplayTalentTV.text = "Tornado"
            windDisplayTalentTV.text = "Summons a tornado."
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow3Item2 == 1) companionList.tornadoRadius = 60.0f
                        if (companionList.towerList[companionList.towerClickID].windRow3Item2 == 2) {
                            companionList.tornadoRadius = 70.0f
                            companionList.tornadoTimer -= 50
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow3Item2 == 3) {
                            companionList.tornadoRadius = 80.0f
                            companionList.tornadoTimer -= 50
                        }

                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 + companionList.towerList[companionList.towerClickID].windRow3Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item3IB.setOnClickListener() {

            setImagePick(33)

            windNameDisplayTalentTV.text = "Winds of Change"
            windDisplayTalentTV.text = "Increases attack speed of nearby towers by an additional 5/10/15%"
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow2Item1 + companionList.towerList[companionList.towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow3Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow3Item3 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow3Item3 == 1) companionList.towerList[companionList.towerClickID].windExtraTowerSpd += 5f
                        if (companionList.towerList[companionList.towerClickID].windRow3Item3 == 2) companionList.towerList[companionList.towerClickID].windExtraTowerSpd += 5f
                        if (companionList.towerList[companionList.towerClickID].windRow3Item3 == 3) companionList.towerList[companionList.towerClickID].windExtraTowerSpd += 5f

                        if (companionList.towerList[companionList.towerClickID].windRow3Item1 + companionList.towerList[companionList.towerClickID].windRow3Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].windRow3Item3.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            windNameDisplayTalentTV.text = "Speed of Light"
            windDisplayTalentTV.text = "Increases attack speed by 0.5%/1%/1.5% each hit until there is no target in range (Max 100%). Burns 1/2/3% max HP each hit as magic damage. +1 bag space item at 3/3."
            windUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].windRow3Item1 + companionList.towerList[companionList.towerClickID].windRow3Item2 + companionList.towerList[companionList.towerClickID].windRow3Item3 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].windRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].windRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].windRow4Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].bonusSpeedWindTalent += 0.50f
                            companionList.towerList[companionList.towerClickID].windUltimatePercent += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow4Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].bonusSpeedWindTalent += 0.50f
                            companionList.towerList[companionList.towerClickID].windUltimatePercent += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].windRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.towerList[companionList.towerClickID].bonusSpeedWindTalent += 0.50f
                            companionList.towerList[companionList.towerClickID].windUltimatePercent += 0.01f
                        }

                        windRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].windRow4Item1.toString()
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
                windRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentWind.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        windRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow3Item3IBPick.setImageResource(R.drawable.overlaytransparent)
        windRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> windRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> windRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> windRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> windRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> windRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> windRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            33 -> windRow3Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> windRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }
        if (companionList.towerList[companionList.towerClickID].windRow1Item1 == 1) {
            windRow1Item2IB.isClickable = false
            windRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].windRow1Item2 == 1) {
            windRow1Item1IB.isClickable = false
            windRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }
    }
}