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
import kotlinx.android.synthetic.main.fragment_dark_talent.*
import kotlinx.android.synthetic.main.fragment_earth_talent.*


class DarkTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_dark_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        darkRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow1Item1.toString()
        darkRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow1Item2.toString()
        darkRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow2Item1.toString()
        darkRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow2Item2.toString()
        darkRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item1.toString()
        darkRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item2.toString()
        darkRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item3.toString()
        darkRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1) {
            darkRow1Item2IB.isClickable = false
            darkRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) {
            darkRow1Item1IB.isClickable = false
            darkRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 + companionList.towerList[companionList.towerClickID].darkRow3Item2 + companionList.towerList[companionList.towerClickID].darkRow3Item3 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        darkRow1Item1IB.setOnClickListener() {

            var point = IntArray(2)
            darkUpgradeBTN.getLocationInWindow(point)
            UiViewTalentWindow.talentX = point[0].toFloat()
            UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
            uiViewTalentDark.invalidate()

            setImagePick(11)

            darkNameDisplayTalentTV.text = "Head Start"
            darkDisplayTalentTV.text = "Gain 3 tower levels."
            darkUpgradeBTN.isClickable = true

            darkUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentDark.invalidate()

                if (companionList.towerList[companionList.towerClickID].darkRow1Item1 + companionList.towerList[companionList.towerClickID].darkRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].darkRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].towerLevelBool = true
                        companionList.towerList[companionList.towerClickID].talentPoints +=3
                        darkRow1Item2IB.isClickable = false
                        darkRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    darkRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        darkRow1Item2IB.setOnClickListener() {

            setImagePick(12)

            darkNameDisplayTalentTV.text = "Leveler"
            darkDisplayTalentTV.text = "Gain 1 enemy worth of experience per level."
            darkUpgradeBTN.isClickable = true

            darkUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentDark.invalidate()

                if (companionList.towerList[companionList.towerClickID].darkRow1Item1 + companionList.towerList[companionList.towerClickID].darkRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].darkRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) {
                        companionList.towerList[companionList.towerClickID].experienceLvl = true
                        darkRow1Item1IB.isClickable = false
                        darkRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    darkRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        darkRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            darkNameDisplayTalentTV.text = "Fear"
            darkDisplayTalentTV.text = "Casts a spell that sometimes causes enemies to run away in fear. Points decrease spell cooldown and increase fear duration. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].darkRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].darkTalentFear = true
                            companionList.towerList[companionList.towerClickID].fearDuration += 1.0f
                            companionList.towerList[companionList.towerClickID].fearTimer = 150
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].darkTalentFear = true
                            companionList.towerList[companionList.towerClickID].fearDuration += 0.5f
                            companionList.towerList[companionList.towerClickID].fearTimer = 120
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].darkTalentFear = true
                            companionList.towerList[companionList.towerClickID].fearDuration += 0.5f
                            companionList.towerList[companionList.towerClickID].fearTimer = 90
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            darkNameDisplayTalentTV.text = "Cold Sweat"
            darkDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 20%/25%/30% permanently. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].darkRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow2Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].darkRow2Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].slowExtraDark = 20.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChanceDark = 10
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow2Item2 == 2) {
                            companionList.towerList[companionList.towerClickID].slowExtraDark = 25.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChanceDark = 15
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow2Item2 == 3) {
                            companionList.towerList[companionList.towerClickID].slowExtraDark = 30.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChanceDark = 20
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            darkNameDisplayTalentTV.text = "Soul Crusher"
            darkDisplayTalentTV.text = "Adds 10%/15%/20% magic damage each hit. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].darkRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].darkMagicDmgPercent = 0.1f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].darkMagicDmgPercent = 0.15f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].darkMagicDmgPercent = 0.2f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 + companionList.towerList[companionList.towerClickID].darkRow3Item2 + companionList.towerList[companionList.towerClickID].darkRow3Item3 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            darkNameDisplayTalentTV.text = "Dark Hand"
            darkDisplayTalentTV.text = "Grabs a random unit and reduces maximum hitpoints by 6%/12%/18% per second. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].darkRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].darkTalentLaser += 0.1f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item2 == 2) {
                            companionList.towerList[companionList.towerClickID].darkTalentLaser += 0.1f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item2 == 3) {
                            companionList.towerList[companionList.towerClickID].darkTalentLaser += 0.1f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 + companionList.towerList[companionList.towerClickID].darkRow3Item2 + companionList.towerList[companionList.towerClickID].darkRow3Item3 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item3IB.setOnClickListener() {

            setImagePick(33)

            darkNameDisplayTalentTV.text = "Dark Curse"
            darkDisplayTalentTV.text =
                "Enemies hit take + 0.2%/0.35%/0.5% bonus dmg for 3 sec. Does stack. +1%/2%/3% anti-immune. "

            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow2Item1 + companionList.towerList[companionList.towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {

                    if (companionList.towerList[companionList.towerClickID].darkRow3Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow3Item3 += 1

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item3 == 1) {
                            companionList.towerList[companionList.towerClickID].darkDmgDebuff = 0.2f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item3 == 2) {
                            companionList.towerList[companionList.towerClickID].darkDmgDebuff = 0.35f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow3Item3 == 3) {
                            companionList.towerList[companionList.towerClickID].darkDmgDebuff = 0.5f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (companionList.towerList[companionList.towerClickID].darkRow3Item1 + companionList.towerList[companionList.towerClickID].darkRow3Item2 + companionList.towerList[companionList.towerClickID].darkRow3Item3 >= 3) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item3ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow3Item3.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            darkNameDisplayTalentTV.text = "Soul Collector"
            darkDisplayTalentTV.text = "Receive an item that collects souls adding permanent physical damage for each kill. +1%/2%/3% anti-immune. +1 bag space item at 3/3."
            darkUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].darkRow3Item1 + companionList.towerList[companionList.towerClickID].darkRow3Item2 + companionList.towerList[companionList.towerClickID].darkRow3Item3 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].darkRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].darkRow4Item1 += 1


                        if (companionList.towerList[companionList.towerClickID].darkRow4Item1 == 1){
                            companionList.dropItemDarkUltimate += 1
                            companionList.towerList[companionList.towerClickID].darkPermaKill += 0.25f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow4Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].darkPermaKill += 0.25f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (companionList.towerList[companionList.towerClickID].darkRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.towerList[companionList.towerClickID].darkPermaKill += 0.25f
                            companionList.towerList[companionList.towerClickID].bonusDmgImmune += 0.01f
                        }

                        darkRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].darkRow4Item1.toString()
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
                darkRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentDark.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        darkRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow3Item3IBPick.setImageResource(R.drawable.overlaytransparent)
        darkRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> darkRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> darkRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> darkRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> darkRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            //    23 -> darkRow2Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> darkRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> darkRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            33 -> darkRow3Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> darkRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }

        if (companionList.towerList[companionList.towerClickID].darkRow1Item1 == 1) {
            darkRow1Item2IB.isClickable = false
            darkRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].darkRow1Item2 == 1) {
            darkRow1Item1IB.isClickable = false
            darkRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }
    }

}