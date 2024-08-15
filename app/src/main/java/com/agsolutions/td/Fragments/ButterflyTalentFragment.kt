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
import com.agsolutions.td.databinding.FragmentButterflyTalentBinding
import com.agsolutions.td.databinding.FragmentStatsTowerBinding

class ButterflyTalentFragment : Fragment() {


    private var _binding: FragmentButterflyTalentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButterflyTalentBinding.inflate(inflater, container, false)
        val view = binding.root
        view.doOnLayout {
            update()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companionList.focusTalentFragment = true

        with(binding) {
            butterflyRow1Item1ShowTV.text =
                GameActivity.companionList.towerList[companionList.towerClickID].butterflyRow1Item1.toString()
            butterflyRow1Item2ShowTV.text =
                GameActivity.companionList.towerList[companionList.towerClickID].butterflyRow1Item2.toString()
            butterflyRow2Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].butterflyRow2Item1.toString()
            butterflyRow2Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].butterflyRow2Item2.toString()
            butterflyRow3Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].butterflyRow3Item1.toString()
            butterflyRow3Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].butterflyRow3Item2.toString()
            butterflyRow4Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].butterflyRow4Item1.toString()

            if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1) {
                butterflyRow1Item2IB.isClickable = false
                butterflyRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) {
                butterflyRow1Item1IB.isClickable = false
                butterflyRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }

            if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 + companionList.towerList[companionList.towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 + companionList.towerList[companionList.towerClickID].butterflyRow3Item2 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

            butterflyRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                butterflyUpgradeBTN.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentButterfly.invalidate()

                setImagePick(11)

                butterflyNameDisplayTalentTV.text = "Killer"
                butterflyDisplayTalentTV.text =
                    "Increases experience gain from kills increased by 10%."
                butterflyUpgradeBTN.isClickable = true

                butterflyUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentButterfly.invalidate()

                    if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 + companionList.towerList[companionList.towerClickID].butterflyRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].butterflyRow1Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceKill += 0.1f
                            butterflyRow1Item2IB.isClickable = false
                            butterflyRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        butterflyRow1Item1ShowTV.text =
                            companionList.towerList[companionList.towerClickID].butterflyRow1Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            butterflyRow1Item2IB.setOnClickListener() {

                setImagePick(12)

                butterflyNameDisplayTalentTV.text = "Bubble Pop"
                butterflyDisplayTalentTV.text =
                    "Gain 10% of enemies experience each time Mark of the Butterfly is being triggered."
                butterflyUpgradeBTN.isClickable = true

                butterflyUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentButterfly.invalidate()

                    if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 + companionList.towerList[companionList.towerClickID].butterflyRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].butterflyRow1Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceButterflyPop += 0.1f
                            butterflyRow1Item1IB.isClickable = false
                            butterflyRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        butterflyRow1Item2ShowTV.text =
                            companionList.towerList[companionList.towerClickID].butterflyRow1Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            butterflyRow2Item1IB.setOnClickListener() {

                setImagePick(21)

                butterflyNameDisplayTalentTV.text = "Sting of the Butterfly"
                butterflyDisplayTalentTV.text =
                    "Target receives 175/200/225% damage at the 3rd consecutive hit. (150% basic)"
                butterflyUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].butterflyRow2Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 == 1) companionList.towerList[companionList.towerClickID].markOfTheButterfly += 0.25f
                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 == 2) companionList.towerList[companionList.towerClickID].markOfTheButterfly += 0.25f
                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 == 3) companionList.towerList[companionList.towerClickID].markOfTheButterfly += 0.25f

                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 + companionList.towerList[companionList.towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].butterflyRow2Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow2Item2IB.setOnClickListener() {

                setImagePick(22)

                butterflyNameDisplayTalentTV.text = "Bite of the Butterfly"
                butterflyDisplayTalentTV.text = "At mark 3/3: Attacks can stun or slow target."
                butterflyUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].butterflyRow2Item2 <= 0 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].butterflyRow2Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item2 == 1) companionList.towerList[companionList.towerClickID].markOfTheButterflySlow =
                                true

                            if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 + companionList.towerList[companionList.towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].butterflyRow2Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow3Item1IB.setOnClickListener() {

                setImagePick(31)

                butterflyNameDisplayTalentTV.text = "Wing Flap"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Attackspeed increased by 0,5%/1%/1,5% for each tower in 150 range for 3 seconds. Can stack and refreshes on hit."
                butterflyUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 + companionList.towerList[companionList.towerClickID].butterflyRow2Item2 >= 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].butterflyRow3Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 == 1) companionList.towerList[companionList.towerClickID].markOfTheButterflySpdBoost += 0.5f
                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 == 2) companionList.towerList[companionList.towerClickID].markOfTheButterflySpdBoost += 0.5f
                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 == 3) companionList.towerList[companionList.towerClickID].markOfTheButterflySpdBoost += 0.5f

                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 + companionList.towerList[companionList.towerClickID].butterflyRow3Item2 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow3Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].butterflyRow3Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow3Item2IB.setOnClickListener() {

                setImagePick(32)

                butterflyNameDisplayTalentTV.text = "Hive Mind"
                butterflyDisplayTalentTV.text =
                    "Triggered Mark of the Butterfly debuffs the ememy, taking + 5%/10%/15% damage and giving + 5%/10%/15% gold and xp for 5 seconds. Does refresh. Does not stack."
                butterflyUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].butterflyRow2Item1 + companionList.towerList[companionList.towerClickID].butterflyRow2Item2 >= 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {

                        if (companionList.towerList[companionList.towerClickID].butterflyRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].butterflyRow3Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item2 == 1) {
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyDmg += 0.05f
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                            }
                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item2 == 2) {
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyDmg += 0.05f
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                            }
                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item2 == 3) {
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyDmg += 0.05f
                                companionList.towerList[companionList.towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                            }

                            if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 + companionList.towerList[companionList.towerClickID].butterflyRow3Item2 >= 3) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow3Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].butterflyRow3Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow4Item1IB.setOnClickListener() {

                setImagePick(41)

                butterflyNameDisplayTalentTV.text = "Double up!"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Shoots an additional bullet for 75/100/125% damage. +1 bag space item at 3/3."
                butterflyUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].butterflyRow3Item1 + companionList.towerList[companionList.towerClickID].butterflyRow3Item2 >= 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].butterflyRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].butterflyRow4Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].butterflyRow4Item1 == 1) {
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShot =
                                    true
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShotDmg =
                                    0.75f
                            }
                            if (companionList.towerList[companionList.towerClickID].butterflyRow4Item1 == 2) {
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShot =
                                    true
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShotDmg =
                                    1.0f
                            }
                            if (companionList.towerList[companionList.towerClickID].butterflyRow4Item1 == 3) {
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShot =
                                    true
                                companionList.towerList[companionList.towerClickID].markOfTheButterflyExtraShotDmg =
                                    1.25f
                                companionList.itemListInsertItem.add(0, Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            }

                            butterflyRow4Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].butterflyRow4Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
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

                with(binding) {
                    butterflyRow1Item1IB.getLocationInWindow(point)
                    UiViewTalentWindow.talentX = point[0].toFloat()
                    UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                    uiViewTalentButterfly.invalidate()
                }
            }
        }
    }

    fun setImagePick (pick: Int) {

        with(binding) {
            butterflyRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            butterflyRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

            when (pick) {
                11 -> butterflyRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                12 -> butterflyRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                21 -> butterflyRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                22 -> butterflyRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                31 -> butterflyRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                32 -> butterflyRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                41 -> butterflyRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            }

            if (companionList.towerList[companionList.towerClickID].butterflyRow1Item1 == 1) {
                butterflyRow1Item2IB.isClickable = false
                butterflyRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].butterflyRow1Item2 == 1) {
                butterflyRow1Item1IB.isClickable = false
                butterflyRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }
        }
    }
}