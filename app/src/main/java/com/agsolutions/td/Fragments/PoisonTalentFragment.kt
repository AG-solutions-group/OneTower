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


class PoisonTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_poison_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        poisonRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow1Item1.toString()
        poisonRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow1Item2.toString()
        poisonRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow2Item1.toString()
        poisonRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow2Item2.toString()
        poisonRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow3Item1.toString()
        poisonRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow3Item2.toString()
        poisonRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1) {
            poisonRow1Item2IB.isClickable = false
            poisonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1) {
            poisonRow1Item1IB.isClickable = false
            poisonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 + companionList.towerList[companionList.towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 + companionList.towerList[companionList.towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        poisonRow1Item1IB.setOnClickListener() {

            var point = IntArray(2)
            poisonUpgradeBTN.getLocationInWindow(point)
            UiViewTalentWindow.talentX = point[0].toFloat()
            UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
            uiViewTalentPoison.invalidate()

            poisonUpgradeBTN.isClickable = true
            setImagePick(11)

            poisonNameDisplayTalentTV.text = "Poison"
            poisonDisplayTalentTV.text = "Gain experience when enemies die from poison (25% of enemy)."

            poisonUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentPoison.invalidate()

                if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 + companionList.towerList[companionList.towerClickID].poisonRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].poisonRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].experiencePoisonKill = true
                        poisonRow1Item2IB.isClickable = false
                        poisonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    poisonRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        poisonRow1Item2IB.setOnClickListener() {

            poisonUpgradeBTN.isClickable = true
            setImagePick(12)

            poisonNameDisplayTalentTV.text = "Poisoned Well"
            poisonDisplayTalentTV.text = "Increases global dmg percent multiplier by number of enemies poisoned * 3."

            poisonUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentPoison.invalidate()

                if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 + companionList.towerList[companionList.towerClickID].poisonRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].poisonRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1){
                        companionList.towerList[companionList.towerClickID].poisonDmgMultiplier = true
                        poisonRow1Item1IB.isClickable = false
                        poisonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                        if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    poisonRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        poisonRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            poisonNameDisplayTalentTV.text = "Death Cap"
            poisonDisplayTalentTV.text = "Increases the damage of stackable poison debuff by 100%/200%/300%. Max 100 stacks."
            poisonUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1 ) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].poisonRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 == 1) companionList.towerList[companionList.towerClickID].stackablePoison += 0.1f
                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 == 2) companionList.towerList[companionList.towerClickID].stackablePoison += 0.1f
                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 == 3) companionList.towerList[companionList.towerClickID].stackablePoison += 0.1f

                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 + companionList.towerList[companionList.towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            poisonNameDisplayTalentTV.text = "Poison Overload"
            poisonDisplayTalentTV.text = "tower increases attack speed for 1.66 seconds every 40/30/20 seconds"
            poisonUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1 ) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].poisonRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].poisonRow2Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item2 == 1) companionList.towerList[companionList.towerClickID].poisonOverload = 2400
                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item2 == 2) companionList.towerList[companionList.towerClickID].poisonOverload = 1800
                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item2 == 3) companionList.towerList[companionList.towerClickID].poisonOverload = 1200

                        if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 + companionList.towerList[companionList.towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            poisonNameDisplayTalentTV.text = "Entangle"
            poisonDisplayTalentTV.text = "Chance to entangle hit enemies. 50% entangle time for bosses."
            poisonUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 + companionList.towerList[companionList.towerClickID].poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].poisonRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 == 1) companionList.towerList[companionList.towerClickID].entangle += 5
                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 == 2) companionList.towerList[companionList.towerClickID].entangle += 5
                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 == 3) companionList.towerList[companionList.towerClickID].entangle += 5

                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 + companionList.towerList[companionList.towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            poisonNameDisplayTalentTV.text = "Pestilence"
            poisonDisplayTalentTV.text = "Infects a random enemy with pestilence. Has a chance to spread to nearby enemies. Deals flat damage on boss and challenge, % damage on all other enemies."
            poisonUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].poisonRow2Item1 + companionList.towerList[companionList.towerClickID].poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].poisonRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].poisonRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item2 == 1) companionList.poisonTalentPest += 0.2f
                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item2 == 2) companionList.poisonTalentPest += 0.2f
                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item2 == 3) companionList.poisonTalentPest += 0.2f

                        if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 + companionList.towerList[companionList.towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            poisonNameDisplayTalentTV.text = "Poison Cloud"
            poisonDisplayTalentTV.text = "Releases a poison cloud over a small area where enemies periodically get poison stacks. +1 bag space item at 3/3."
            poisonUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].poisonRow3Item1 + companionList.towerList[companionList.towerClickID].poisonRow3Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].poisonRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].poisonRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].poisonRow4Item1 == 1) companionList.poisonCloud += 1
                        if (companionList.towerList[companionList.towerClickID].poisonRow4Item1 == 2) companionList.poisonCloud += 1
                        if (companionList.towerList[companionList.towerClickID].poisonRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.poisonCloud += 1
                        }

                        poisonRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].poisonRow4Item1.toString()
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
                poisonRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentPoison.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        poisonRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        poisonRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> poisonRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> poisonRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> poisonRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> poisonRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> poisonRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> poisonRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> poisonRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }
        if (companionList.towerList[companionList.towerClickID].poisonRow1Item1 == 1) {
            poisonRow1Item2IB.isClickable = false
            poisonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].poisonRow1Item2 == 1) {
            poisonRow1Item1IB.isClickable = false
            poisonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }
    }
}