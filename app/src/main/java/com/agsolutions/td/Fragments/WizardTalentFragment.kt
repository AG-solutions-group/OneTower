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
import kotlinx.android.synthetic.main.fragment_wizard_talent.*


class WizardTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mainLayout = inflater.inflate(R.layout.fragment_wizard_talent, container, false)

        mainLayout.doOnLayout {
            update()
        }

        return mainLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        wizardRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow1Item1.toString()
        wizardRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow1Item2.toString()
        wizardRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item1.toString()
        wizardRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item2.toString()
        wizardRow2Item3ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item3.toString()
        wizardRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow3Item1.toString()
        wizardRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow3Item2.toString()
        wizardRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1) {
            wizardRow1Item2IB.isClickable = false
            wizardRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) {
            wizardRow1Item1IB.isClickable = false
            wizardRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }

        if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 + companionList.towerList[companionList.towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        wizardRow1Item1IB.setOnClickListener() {

            var point = IntArray(2)
            wizardUpgradeBTN.getLocationInWindow(point)
            UiViewTalentWindow.talentX = point[0].toFloat()
            UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
            uiViewTalentWizard.invalidate()

            wizardUpgradeBTN.isClickable = true
            setImagePick(11)

            wizardNameDisplayTalentTV.text = "Caster"
            wizardDisplayTalentTV.text = "Gain experience for each tower cast (5% of enemy)."

            wizardUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentWizard.invalidate()

                if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 + companionList.towerList[companionList.towerClickID].wizardRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].wizardRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1){
                        companionList.towerList[companionList.towerClickID].experienceCast = true
                        wizardRow1Item2IB.isClickable = false
                        wizardRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    wizardRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        wizardRow1Item2IB.setOnClickListener() {

            wizardUpgradeBTN.isClickable = true
            setImagePick(12)

            wizardNameDisplayTalentTV.text = "Overflowing Magic"
            wizardDisplayTalentTV.text = "Improve tower spell damage by tower level (3% per tower level)."

            wizardUpgradeBTN.setOnClickListener() {

                companionList.focusTalentFragment = false
                uiViewTalentWizard.invalidate()

                if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 + companionList.towerList[companionList.towerClickID].wizardRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].wizardRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1){
                        companionList.towerList[companionList.towerClickID].talentWizardLvlToDmg = true
                        wizardRow1Item1IB.isClickable = false
                        wizardRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                    }

                    if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    wizardRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        wizardRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            wizardNameDisplayTalentTV.text = "Lightning Bolt"
            wizardDisplayTalentTV.text = "Throws a lightning bolt on 2 enemies for each missed tower attack for 150/200/250% spelldamage."
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].wizardMissedLightning = true
                            companionList.towerList[companionList.towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].wizardMissedLightning = true
                            companionList.towerList[companionList.towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].wizardMissedLightning = true
                            companionList.towerList[companionList.towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }

                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            wizardNameDisplayTalentTV.text = "Dispel"
            wizardDisplayTalentTV.text = "Decreases magic armor of enemies with type magic armor by 3%/6%/9% & reduces hitpoint regeneration by 3%/6%/9%%. Global."
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow2Item2 += 1


                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item2 == 1) {
                            companionList.hpRegDebuffGlobal += 0.03f
                            companionList.wizardMagicArmorSmasher -= 0.03f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item2 == 2) {
                            companionList.hpRegDebuffGlobal += 0.03f
                            companionList.wizardMagicArmorSmasher -= 0.03f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item2 == 3) {
                            companionList.hpRegDebuffGlobal += 0.03f
                            companionList.wizardMagicArmorSmasher -= 0.03f
                        }

                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow2Item3IB.setOnClickListener() {

            setImagePick(23)

            wizardNameDisplayTalentTV.text = "Bombshell"
            wizardDisplayTalentTV.text = "Reduces the cooldown of bomb ability by 1/2/3 seconds and increases damage by 5/10/15 (+10% magic damage)."
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow2Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow2Item3 += 1

                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item3 == 1) {
                            companionList.towerList[companionList.towerClickID].wizardBombTimer -= 60
                            companionList.towerList[companionList.towerClickID].wizardBombDmg += 5f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item3 == 2) {
                            companionList.towerList[companionList.towerClickID].wizardBombTimer -= 60
                            companionList.towerList[companionList.towerClickID].wizardBombDmg += 5f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item3 == 3) {
                            companionList.towerList[companionList.towerClickID].wizardBombTimer -= 60
                            companionList.towerList[companionList.towerClickID].wizardBombDmg += 5f
                        }

                        if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3>= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item3ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow2Item3.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            wizardNameDisplayTalentTV.text = "Cool Down!"
            wizardDisplayTalentTV.text = "Each spellcast decreases cooldown of all spellcasts by 3/6/9%."
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 == 1) companionList.towerList[companionList.towerClickID].spellCastCD += 0.03f
                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 == 2) companionList.towerList[companionList.towerClickID].spellCastCD += 0.03f
                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 == 3) companionList.towerList[companionList.towerClickID].spellCastCD += 0.03f


                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 + companionList.towerList[companionList.towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            wizardNameDisplayTalentTV.text = "Mine Crafter"
            wizardDisplayTalentTV.text = "Plants a mine at a random location. CD reduced per level. Mines deals a small amount of damage and slow enemies by 50% for 1.5 seconds-"
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow2Item1 + companionList.towerList[companionList.towerClickID].wizardRow2Item2 + companionList.towerList[companionList.towerClickID].wizardRow2Item3 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item2 ==1) {
                            companionList.wizardMine = true
                            companionList.wizardMineTimer -= 20f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item2 ==1) {
                            companionList.wizardMine = true
                            companionList.wizardMineTimer -= 20f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item2 ==1) {
                            companionList.wizardMine = true
                            companionList.wizardMineTimer -= 20f
                        }

                        if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 + companionList.towerList[companionList.towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            wizardNameDisplayTalentTV.text = "Chain Lightning"
            wizardDisplayTalentTV.text = "Casts a chain lightning that jumps to 2/4/6 targets. Increases danage for each enemy killed by chain lightning. +1 bag space item at 3/3." +
                    "Spell CD reduced for each tower with wizard element."
            wizardUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].wizardRow3Item1 + companionList.towerList[companionList.towerClickID].wizardRow3Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].wizardRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].wizardRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].wizardRow4Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].chainLighning = true
                            companionList.towerList[companionList.towerClickID].chainLightningBounceTargets += 2
                            companionList.towerList[companionList.towerClickID].chainLightningDmg = 0.9f
                            companionList.towerList[companionList.towerClickID].chainLightningTimer = 240f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow4Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].chainLighning = true
                            companionList.towerList[companionList.towerClickID].chainLightningBounceTargets += 2
                            companionList.towerList[companionList.towerClickID].chainLightningDmg = 1.0f
                            companionList.towerList[companionList.towerClickID].chainLightningTimer = 210f
                        }
                        if (companionList.towerList[companionList.towerClickID].wizardRow4Item1 == 3) {
                            companionList.itemListInsertItem.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.towerList[companionList.towerClickID].chainLighning = true
                            companionList.towerList[companionList.towerClickID].chainLightningBounceTargets += 2
                            companionList.towerList[companionList.towerClickID].chainLightningDmg = 1.1f
                            companionList.towerList[companionList.towerClickID].chainLightningTimer = 180f
                        }

                        wizardRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].wizardRow4Item1.toString()
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
                wizardRow1Item1IB.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentWizard.invalidate()
            }
        }
    }

    fun setImagePick (pick: Int) {

        wizardRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow2Item3IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        wizardRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> wizardRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> wizardRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> wizardRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> wizardRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            23 -> wizardRow2Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> wizardRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> wizardRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> wizardRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }
        if (companionList.towerList[companionList.towerClickID].wizardRow1Item1 == 1) {
            wizardRow1Item2IB.isClickable = false
            wizardRow1Item2IBPick.setImageResource(R.drawable.crossedout)
        }
        if (companionList.towerList[companionList.towerClickID].wizardRow1Item2 == 1) {
            wizardRow1Item1IB.isClickable = false
            wizardRow1Item1IBPick.setImageResource(R.drawable.crossedout)
        }
    }
}