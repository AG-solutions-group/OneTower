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
import kotlinx.android.synthetic.main.fragment_earth_talent.*
import kotlinx.android.synthetic.main.fragment_fire_talent.*


class FireTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_fire_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        fireRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow1Item1.toString()
        fireRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow1Item2.toString()
        fireRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow2Item1.toString()
        fireRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow2Item2.toString()
        fireRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow3Item1.toString()
        fireRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow3Item2.toString()
        fireRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1) {
            fireRow1Item2IB.isClickable = false
            fireRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) {
            fireRow1Item1IB.isClickable = false
            fireRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 + companionList.towerList[companionList.towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 + companionList.towerList[companionList.towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        fireRow1Item1IB.setOnClickListener() {

            var point = IntArray(2)
            fireUpgradeBTN.getLocationInWindow(point)
            UiViewTalentWindow.talentX = point[0].toFloat()
            UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
            uiViewTalentFire.invalidate()

           setImagePick(11)

            fireNameDisplayTalentTV.text = "Candle"
            fireDisplayTalentTV.text = "Gain Experience for each critical strike (10% of enemy)."
            fireUpgradeBTN.isClickable = true

            fireUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentFire.invalidate()

                if (companionList.towerList[companionList.towerClickID].fireRow1Item1 + companionList.towerList[companionList.towerClickID].fireRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].fireRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].experienceFireCrit = true
                        fireRow1Item2IB.isClickable = false
                        fireRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    fireRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        fireRow1Item2IB.setOnClickListener() {

            setImagePick(12)

            fireNameDisplayTalentTV.text = "Hell"
            fireDisplayTalentTV.text = "Get 0,5 critical strike rating for each kill."
            fireUpgradeBTN.isClickable = true

            fireUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentFire.invalidate()

                if (companionList.towerList[companionList.towerClickID].fireRow1Item1 + companionList.towerList[companionList.towerClickID].fireRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].fireRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1){
                        companionList.towerList[companionList.towerClickID].talentFireKill = true
                        fireRow1Item1IB.isClickable = false
                        fireRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    fireRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        fireRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            fireNameDisplayTalentTV.text = "Campfire"
            fireDisplayTalentTV.text = "Increases critical strike damage by 0.15."
            fireUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].fireRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].fireRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 == 1) companionList.towerList[companionList.towerClickID].fireTalentBonusCritDmg += 0.15f
                        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 == 2) companionList.towerList[companionList.towerClickID].fireTalentBonusCritDmg += 0.15f
                        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 == 3) companionList.towerList[companionList.towerClickID].fireTalentBonusCritDmg += 0.15f

                        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 + companionList.towerList[companionList.towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            fireNameDisplayTalentTV.text = "Burn"
            fireDisplayTalentTV.text = "Burns enemies critically hit for additional 2%/4%/6% of maximum hitpoints as magic damage over 2.4 seconds. Ignores shields. 50% damage to bosses."
            fireUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].fireRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].fireRow2Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].fireRow2Item2 == 1) companionList.towerList[companionList.towerClickID].fireBurnTalentDmg += 0.002f
                        if (companionList.towerList[companionList.towerClickID].fireRow2Item2 == 2) companionList.towerList[companionList.towerClickID].fireBurnTalentDmg += 0.002f
                        if (companionList.towerList[companionList.towerClickID].fireRow2Item2 == 3) companionList.towerList[companionList.towerClickID].fireBurnTalentDmg += 0.002f


                        if (companionList.towerList[companionList.towerClickID].fireRow2Item1 + companionList.towerList[companionList.towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            fireNameDisplayTalentTV.text = "Lava"
            fireDisplayTalentTV.text = "Each consecutive non-critical hit increases critical damage by 0.05/0.075/0.1. Resets after critical hit."
            fireUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].fireRow2Item1 + companionList.towerList[companionList.towerClickID].fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].fireRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].fireRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 == 1) companionList.towerList[companionList.towerClickID].bonusCritFire = 0.05f
                        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 == 2) companionList.towerList[companionList.towerClickID].bonusCritFire = 0.075f
                        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 == 3) companionList.towerList[companionList.towerClickID].bonusCritFire = 0.1f

                        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 + companionList.towerList[companionList.towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            fireNameDisplayTalentTV.text = "Sunburn"
            fireDisplayTalentTV.text = "During day: enemies build up sunburn stacks, which burns enemies. Stacks with Burn ability. Increases day time by 1 hour at 3/3."
            fireUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].fireRow2Item1 + companionList.towerList[companionList.towerClickID].fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].fireRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].fireRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].fireRow3Item2 == 1) companionList.towerList[companionList.towerClickID].sunburn += 1
                        if (companionList.towerList[companionList.towerClickID].fireRow3Item2 == 2) companionList.towerList[companionList.towerClickID].sunburn += 1
                        if (companionList.towerList[companionList.towerClickID].fireRow3Item2 == 3) {
                            companionList.towerList[companionList.towerClickID].sunburn += 1
                            companionList.dayNightVariable -= 1
                        }

                        if (companionList.towerList[companionList.towerClickID].fireRow3Item1 + companionList.towerList[companionList.towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            fireNameDisplayTalentTV.text = "Eternal Flame"
            fireDisplayTalentTV.text = "Increases multicrit count by 0/1/2. Plus 1 multicrit each 20 levels at 3/3. +1 bag space item at 3/3."
            fireUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].fireRow3Item1 + companionList.towerList[companionList.towerClickID].fireRow3Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].fireRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].fireRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].fireRow4Item1 == 2) companionList.towerList[companionList.towerClickID].bonusmultiCrit += 1
                        if (companionList.towerList[companionList.towerClickID].fireRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.towerList[companionList.towerClickID].bonusmultiCrit += 1
                        }

                        fireRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].fireRow4Item1.toString()
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
                fireRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentFire.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        fireRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        fireRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> fireRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> fireRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> fireRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> fireRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        //    23 -> fireRow2Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> fireRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> fireRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        //    33 -> fireRow3Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> fireRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }
        if (companionList.towerList[companionList.towerClickID].fireRow1Item1 == 1) {
            fireRow1Item2IB.isClickable = false
            fireRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].fireRow1Item2 == 1) {
            fireRow1Item1IB.isClickable = false
            fireRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

    }

}