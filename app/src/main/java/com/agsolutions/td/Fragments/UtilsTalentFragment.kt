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
import com.agsolutions.td.databinding.FragmentPoisonTalentBinding
import com.agsolutions.td.databinding.FragmentUtilsTalentBinding


class UtilsTalentFragment : Fragment() {

    private var _binding: FragmentUtilsTalentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUtilsTalentBinding.inflate(inflater, container, false)
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

            utilsRow1Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow1Item1.toString()
            utilsRow1Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow1Item2.toString()
            utilsRow2Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow2Item1.toString()
            utilsRow2Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow2Item2.toString()
            utilsRow2Item3ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow2Item3.toString()
            utilsRow3Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow3Item1.toString()
            utilsRow3Item2ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow3Item2.toString()
            utilsRow4Item1ShowTV.text =
                companionList.towerList[companionList.towerClickID].utilsRow4Item1.toString()

            if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1) {
                utilsRow1Item2IB.isClickable = false
                utilsRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                utilsRow1Item1IB.isClickable = false
                utilsRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }

            if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)
            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 + companionList.towerList[companionList.towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

            utilsRow1Item1IB.setOnClickListener() {

                var point = IntArray(2)
                utilsUpgradeBTN.getLocationInWindow(point)
                UiViewTalentWindow.talentX = point[0].toFloat()
                UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                uiViewTalentUtils.invalidate()

                setImagePick(11)

                utilsNameDisplayTalentTV.text = "Marriage"
                utilsDisplayTalentTV.text =
                    "Gain a small amount of experience each time a tower in 150 range gains experience."
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentUtils.invalidate()

                    if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 + companionList.towerList[companionList.towerClickID].utilsRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].utilsRow1Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceGainUtilsAura =
                                true
                            utilsRow1Item2IB.isClickable = false
                            utilsRow1Item2IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow1Item1ShowTV.text =
                            companionList.towerList[companionList.towerClickID].utilsRow1Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            utilsRow1Item2IB.setOnClickListener() {

                setImagePick(12)

                utilsNameDisplayTalentTV.text = "Divorce"
                utilsDisplayTalentTV.text =
                    "Each time this tower gains experience it shares 10% to all towers in 150 range."
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {

                    companionList.focusTalentFragment = false
                    uiViewTalentUtils.invalidate()

                    if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 + companionList.towerList[companionList.towerClickID].utilsRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].utilsRow1Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                            companionList.towerList[companionList.towerClickID].experienceShareUtilsAura =
                                true
                            utilsRow1Item1IB.isClickable = false
                            utilsRow1Item1IBPick.setImageResource(R.drawable.crossedout)
                        }

                        if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow1Item2ShowTV.text =
                            companionList.towerList[companionList.towerClickID].utilsRow1Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }

            utilsRow2Item1IB.setOnClickListener() {

                setImagePick(21)

                utilsNameDisplayTalentTV.text = "Banker"
                utilsDisplayTalentTV.text = "Increases global interest rate on gold by 1%/2%/3%."
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow2Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 == 1) companionList.interest += 0.01f
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 == 2) companionList.interest += 0.01f
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 == 3) companionList.interest += 0.01f

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            utilsRow2Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow2Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            utilsRow2Item2IB.setOnClickListener() {

                setImagePick(22)

                utilsNameDisplayTalentTV.text = "Upgrader"
                utilsDisplayTalentTV.text =
                    "Each hit has a chance (0,5%/1%/1,5%) to grant one upgrade point."
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow2Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item2 == 1) companionList.towerList[companionList.towerClickID].utilsUpgrader += 1f
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item2 == 2) companionList.towerList[companionList.towerClickID].utilsUpgrader += 1f
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item2 == 3) companionList.towerList[companionList.towerClickID].utilsUpgrader += 1f

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            utilsRow2Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow2Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            utilsRow2Item3IB.setOnClickListener() {

                setImagePick(23)

                utilsNameDisplayTalentTV.text = "Coal"
                utilsDisplayTalentTV.text = "Gain 1 diamond."
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow2Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow2Item3 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item3 == 1) companionList.diamonds++
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item3 == 2) companionList.diamonds++
                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item3 == 3) companionList.diamonds++

                            if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            utilsRow2Item3ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow2Item3.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            utilsRow3Item1IB.setOnClickListener() {

                setImagePick(31)

                utilsNameDisplayTalentTV.text = "Dowsing Stick"
                utilsDisplayTalentTV.text = "Increases item chance of nearby towers by 20%/35%/50%"
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow3Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 == 1) companionList.towerList[companionList.towerClickID].itemChanceAura += 20f
                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 == 2) companionList.towerList[companionList.towerClickID].itemChanceAura += 15f
                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 == 3) companionList.towerList[companionList.towerClickID].itemChanceAura += 15f

                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 + companionList.towerList[companionList.towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            utilsRow3Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow3Item1.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            utilsRow3Item2IB.setOnClickListener() {

                setImagePick(32)

                utilsNameDisplayTalentTV.text = "Made in Germany"
                utilsDisplayTalentTV.text = "Increases item quality of nearby towers by 2%/4%/6%"
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow2Item1 + companionList.towerList[companionList.towerClickID].utilsRow2Item2 + companionList.towerList[companionList.towerClickID].utilsRow2Item3 >= 3) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow3Item2 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item2 == 1) companionList.towerList[companionList.towerClickID].itemQualityAura += 2f
                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item2 == 1) companionList.towerList[companionList.towerClickID].itemQualityAura += 2f
                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item2 == 1) companionList.towerList[companionList.towerClickID].itemQualityAura += 2f

                            if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 + companionList.towerList[companionList.towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            utilsRow3Item2ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow3Item2.toString()
                            companionList.towerList[companionList.towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            utilsRow4Item1IB.setOnClickListener() {

                setImagePick(41)

                utilsNameDisplayTalentTV.text = "Thank You"
                utilsDisplayTalentTV.text =
                    "Increases bonus damage, speed and critical chance by 25%/50%/75%. +1 bag space item at 3/3."
                utilsUpgradeBTN.isClickable = false

                if (companionList.towerList[companionList.towerClickID].utilsRow3Item1 + companionList.towerList[companionList.towerClickID].utilsRow3Item2 >= 3) {
                    utilsUpgradeBTN.isClickable = true

                    utilsUpgradeBTN.setOnClickListener() {
                        if (companionList.towerList[companionList.towerClickID].utilsRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                            companionList.towerList[companionList.towerClickID].utilsRow4Item1 += 1

                            if (companionList.towerList[companionList.towerClickID].utilsRow4Item1 == 1) companionList.towerList[companionList.towerClickID].utilsUltimate += 0.25f
                            if (companionList.towerList[companionList.towerClickID].utilsRow4Item1 == 2) companionList.towerList[companionList.towerClickID].utilsUltimate += 0.25f
                            if (companionList.towerList[companionList.towerClickID].utilsRow4Item1 == 3) {
                                companionList.itemListInsertItem.add(0, Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.itembag, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                                companionList.towerList[companionList.towerClickID].utilsUltimate += 0.25f
                            }

                            utilsRow4Item1ShowTV.text =
                                companionList.towerList[companionList.towerClickID].utilsRow4Item1.toString()
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
                    utilsRow1Item1IB.getLocationInWindow(point)
                    UiViewTalentWindow.talentX = point[0].toFloat()
                    UiViewTalentWindow.talentY = (point[1] - Talents.topBarHeight).toFloat()
                    uiViewTalentUtils.invalidate()
                }
            }
        }
    }

    fun setImagePick (pick: Int) {

        with(binding) {
            utilsRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow2Item3IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
            utilsRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

            when (pick) {
                11 -> utilsRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                12 -> utilsRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                21 -> utilsRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                22 -> utilsRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                23 -> utilsRow2Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                31 -> utilsRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                32 -> utilsRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
                41 -> utilsRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            }
            if (companionList.towerList[companionList.towerClickID].utilsRow1Item1 == 1) {
                utilsRow1Item2IB.isClickable = false
                utilsRow1Item2IBPick.setImageResource(R.drawable.crossedout)
            }
            if (companionList.towerList[companionList.towerClickID].utilsRow1Item2 == 1) {
                utilsRow1Item1IB.isClickable = false
                utilsRow1Item1IBPick.setImageResource(R.drawable.crossedout)
            }
        }
    }
}