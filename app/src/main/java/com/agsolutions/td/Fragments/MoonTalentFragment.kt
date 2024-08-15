package com.agsolutions.td.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.agsolutions.td.ActiveAbility
import com.agsolutions.td.GameActivity
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import com.agsolutions.td.Talents
import com.agsolutions.td.UiViewTalentWindow
import com.agsolutions.td.databinding.FragmentMoonTalentBinding

class MoonTalentFragment : Fragment() {

    private var _binding: FragmentMoonTalentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoonTalentBinding.inflate(inflater, container, false)
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
            moonRow1Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow1Item1.toString()
            moonRow1Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow1Item2.toString()
            moonRow2Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow2Item1.toString()
            moonRow2Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow2Item2.toString()
            moonRow3Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow3Item1.toString()
            moonRow3Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow3Item2.toString()
            moonRow4Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].moonRow4Item1.toString()

            if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1) {
                moonRow1Item2IB.isClickable = false
                moonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) {
                moonRow1Item1IB.isClickable = false
                moonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }

            if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 + companionList.towerList[companionList.towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 + companionList.towerList[companionList.towerClickID].moonRow3Item2 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

            moonRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                moonUpgradeBTN.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentMoon.invalidate()

                setImagePick(11)

                moonNameDisplayTalentTV.text = "Smiter"
                moonDisplayTalentTV.text =
                    "Gain experience for each target hit by moonlight at least 3 times. (10% of enemy)"
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentMoon.invalidate()

                    if (companionList.towerList[companionList.towerClickID].moonRow1Item1 + companionList.towerList[companionList.towerClickID].moonRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].moonRow1Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceMoonlight =
                                true
                            moonRow1Item2IB.isClickable = false
                            moonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow1Item1ShowTV.text =
                            companionList.towerList[companionList.towerClickID].moonRow1Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            moonRow1Item2IB.setOnClickListener() {

                setImagePick(12)

                moonNameDisplayTalentTV.text = "Eclipse"
                moonDisplayTalentTV.text = "Gain double experience at night."
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentMoon.invalidate()

                    if (companionList.towerList[companionList.towerClickID].moonRow1Item1 + companionList.towerList[companionList.towerClickID].moonRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].moonRow1Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceMoonNight =
                                true
                            moonRow1Item1IB.isClickable = false
                            moonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow1Item2ShowTV.text =
                            companionList.towerList[companionList.towerClickID].moonRow1Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            moonRow2Item1IB.setOnClickListener() {

                setImagePick(21)

                moonNameDisplayTalentTV.text = "Full Moon"
                moonDisplayTalentTV.text =
                    "Increases night time by 1 hour at 3/3. Gold cost reduced by 3%/6%/10% at night. Does stack."
                moonUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) {
                    moonUpgradeBTN.isClickable = true

                    moonUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].moonRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].moonRow2Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 == 1) {
                                companionList.moonTalentItemCost += 0.03f
                            }
                            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 == 2) {
                                companionList.moonTalentItemCost += 0.03f
                            }
                            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 == 3) {
                                companionList.dayNightVariable += 1
                                companionList.moonTalentItemCost += 0.04f
                            }

                            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 + companionList.towerList[companionList.towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            moonRow2Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].moonRow2Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            moonRow2Item2IB.setOnClickListener() {

                setImagePick(22)

                moonNameDisplayTalentTV.text = "Bounce"
                moonDisplayTalentTV.text =
                    "Bullets can bounce to 1/2/3 additional targets dealing 50% damage each target. Does not stack with multishot, splash or BUTTERFLY single target abilities."
                moonUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) {
                    moonUpgradeBTN.isClickable = true

                    moonUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].moonRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].moonRow2Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].moonRow2Item2 == 1) {
                                companionList.towerList[companionList.towerClickID].shotBounceTargets += 1
                            }
                            if (companionList.towerList[companionList.towerClickID].moonRow2Item2 == 2) {
                                if (companionList.itemStartBounce) companionList.towerList[companionList.towerClickID].shotBounceTargets += 2
                                else companionList.towerList[companionList.towerClickID].shotBounceTargets += 1
                            }
                            if (companionList.towerList[companionList.towerClickID].moonRow2Item2 == 3) {
                                if (companionList.itemStartBounce) companionList.towerList[companionList.towerClickID].shotBounceTargets += 2
                                else companionList.towerList[companionList.towerClickID].shotBounceTargets += 1
                            }

                            if (companionList.towerList[companionList.towerClickID].moonRow2Item1 + companionList.towerList[companionList.towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            moonRow2Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].moonRow2Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            moonRow3Item1IB.setOnClickListener() {

                setImagePick(31)

                moonNameDisplayTalentTV.text = "Moonlight"
                moonDisplayTalentTV.text =
                    "If night: Smites an random enemy with random damage every couple of seconds. Consecutive hits damage is multiplied."
                moonUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].moonRow2Item1 + companionList.towerList[companionList.towerClickID].moonRow2Item2 >= 3) {
                    moonUpgradeBTN.isClickable = true

                    moonUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].moonRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].moonRow3Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 == 1) companionList.towerList[companionList.towerClickID].moonLight += 1f
                            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 == 2) companionList.towerList[companionList.towerClickID].moonLight += 1f
                            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 == 3) companionList.towerList[companionList.towerClickID].moonLight += 1f

                            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 + companionList.towerList[companionList.towerClickID].moonRow3Item2 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            moonRow3Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].moonRow3Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            moonRow3Item2IB.setOnClickListener() {

                setImagePick(32)

                moonNameDisplayTalentTV.text = "Blood Moon"
                moonDisplayTalentTV.text = "Increases damage at night time by 10%/20%/30%."
                moonUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].moonRow2Item1 + companionList.towerList[companionList.towerClickID].moonRow2Item2 >= 3) {
                    moonUpgradeBTN.isClickable = true

                    moonUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].moonRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].moonRow3Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].moonRow3Item2 == 1) companionList.towerList[companionList.towerClickID].damageMultiplyerNight += 0.1f
                            if (companionList.towerList[companionList.towerClickID].moonRow3Item2 == 2) companionList.towerList[companionList.towerClickID].damageMultiplyerNight += 0.1f
                            if (companionList.towerList[companionList.towerClickID].moonRow3Item2 == 3) companionList.towerList[companionList.towerClickID].damageMultiplyerNight += 0.1f

                            if (companionList.towerList[companionList.towerClickID].moonRow3Item1 + companionList.towerList[companionList.towerClickID].moonRow3Item2 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            moonRow3Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].moonRow3Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            moonRow4Item1IB.setOnClickListener() {

                setImagePick(41)

                moonNameDisplayTalentTV.text = "Endless Night"
                moonDisplayTalentTV.text =
                    "Active: Endless night. 3 min CD. Passive: Increases hit chance by 3/6/9 at night. At 3: removes extra evade at night. +1 bag space item at 3/3."
                moonUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].moonRow3Item1 + companionList.towerList[companionList.towerClickID].moonRow3Item2 >= 3) {
                    moonUpgradeBTN.isClickable = true

                    moonUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].moonRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].moonRow4Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].moonRow4Item1 == 1) {
                                if (companionList.endlessNight < 1) {
                                    companionList.activeAbilityList.add(0, ActiveAbility.aAid0)
                                    companionList.insertSpell += 1
                                }
                                if (companionList.endlessNight < 3) companionList.endlessNight += 1
                            }
                            if (companionList.towerList[companionList.towerClickID].moonRow4Item1 == 2) if (companionList.endlessNight < 3) companionList.endlessNight += 1
                            if (companionList.towerList[companionList.towerClickID].moonRow4Item1 == 3) {
                                companionList.moonTalentEvadeNight = true
                                if (companionList.endlessNight < 3) companionList.endlessNight += 1
                                companionList.itemListInsertItem.add(0, Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            }

                            moonRow4Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].moonRow4Item1.toString()
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
                    moonRow1Item1IB.getLocationInWindow(point)
                    UiViewTalentWindow.talentX = point[0].toFloat()
                    UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                    uiViewTalentMoon.invalidate()
                }
            }
        }
    }

    fun setImagePick (pick: Int) {

        with(binding) {

            moonRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            moonRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

            when (pick) {
                11 -> moonRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                12 -> moonRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                21 -> moonRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                22 -> moonRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                31 -> moonRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                32 -> moonRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                41 -> moonRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            }
            if (companionList.towerList[companionList.towerClickID].moonRow1Item1 == 1) {
                moonRow1Item2IB.isClickable = false
                moonRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].moonRow1Item2 == 1) {
                moonRow1Item1IB.isClickable = false
                moonRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }
        }
    }
}