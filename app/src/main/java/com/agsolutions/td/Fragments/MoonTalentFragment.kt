package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.ActiveAbility
import com.agsolutions.td.Companion.Companion.activeAbilityList
import com.agsolutions.td.Companion.Companion.dayNightVariable
import com.agsolutions.td.Companion.Companion.endlessNight
import com.agsolutions.td.Companion.Companion.insertSpell
import com.agsolutions.td.Companion.Companion.itemStartBounce
import com.agsolutions.td.Companion.Companion.moonTalentEvadeNight
import com.agsolutions.td.Companion.Companion.moonTalentItemCost
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_moon_talent.*


class MoonTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moon_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moonRow1Item1ShowTV.text = towerList[towerClickID].moonRow1Item1.toString()
        moonRow1Item2ShowTV.text = towerList[towerClickID].moonRow1Item2.toString()
        moonRow2Item1ShowTV.text = towerList[towerClickID].moonRow2Item1.toString()
        moonRow2Item2ShowTV.text = towerList[towerClickID].moonRow2Item2.toString()
        moonRow3Item1ShowTV.text = towerList[towerClickID].moonRow3Item1.toString()
        moonRow3Item2ShowTV.text = towerList[towerClickID].moonRow3Item2.toString()
        moonRow4Item1ShowTV.text = towerList[towerClickID].moonRow4Item1.toString()

        if (towerList[towerClickID].moonRow1Item1 == 1 || towerList[towerClickID].moonRow1Item1 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].moonRow2Item1 + towerList[towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].moonRow3Item1 + towerList[towerClickID].moonRow3Item2 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        moonRow1Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Smiter"
            moonDisplayTalentTV.text = "Gain experience for each taget hit by moonlight at least 3 times. (5% of enemy)"
            moonUpgradeBTN.isClickable = true

            moonUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].moonRow1Item1 + towerList[towerClickID].moonRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].moonRow1Item1 += 1

                    if (towerList[towerClickID].moonRow1Item1 == 1) towerList[towerClickID].experienceMoonlight = true

                    if (towerList[towerClickID].moonRow1Item1 == 1 || towerList[towerClickID].moonRow1Item2 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    moonRow1Item1ShowTV.text = towerList[towerClickID].moonRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        moonRow1Item2IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = " "
            moonDisplayTalentTV.text = ""
            moonUpgradeBTN.isClickable = true

            moonUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].moonRow1Item1 + towerList[towerClickID].moonRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].moonRow1Item2 += 1

                    if (towerList[towerClickID].moonRow1Item2 == 1)

                    if (towerList[towerClickID].moonRow1Item1 == 1 || towerList[towerClickID].moonRow1Item2 == 1) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    moonRow1Item2ShowTV.text = towerList[towerClickID].moonRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        moonRow2Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Full Moon"
            moonDisplayTalentTV.text = "Increases night time by 1 hour at 3/3. Gold cost reduced by 3%/6%/10% at night. Does stack."
            moonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].moonRow1Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].moonRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].moonRow2Item1 += 1

                        if (towerList[towerClickID].moonRow2Item1 == 1){
                            moonTalentItemCost += 0.03f
                        }
                        if (towerList[towerClickID].moonRow2Item1 == 2){
                            moonTalentItemCost += 0.03f
                        }
                        if (towerList[towerClickID].moonRow2Item1 == 3){
                            dayNightVariable += 1
                            moonTalentItemCost += 0.4f
                        }

                        if (towerList[towerClickID].moonRow2Item1 + towerList[towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow2Item1ShowTV.text = towerList[towerClickID].moonRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        moonRow2Item2IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Bounce"
            moonDisplayTalentTV.text = "Bullets can bounce to 1/2/3 additional targets dealing 50% damage each target. Does not stack with multishot, splash or BUTTERFLY single target abilities."
            moonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].moonRow1Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].moonRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].moonRow2Item2 += 1

                        if (towerList[towerClickID].moonRow2Item2 == 1) {
                            towerList[towerClickID].shotBounceTargets += 1
                        }
                        if (towerList[towerClickID].moonRow2Item2 == 2) {
                            if (itemStartBounce) towerList[towerClickID].shotBounceTargets += 2
                            else towerList[towerClickID].shotBounceTargets += 1
                        }
                        if (towerList[towerClickID].moonRow2Item2 == 3) {
                            if (itemStartBounce) towerList[towerClickID].shotBounceTargets += 2
                            else towerList[towerClickID].shotBounceTargets += 1
                        }

                        if (towerList[towerClickID].moonRow2Item1 + towerList[towerClickID].moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow2Item2ShowTV.text = towerList[towerClickID].moonRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        moonRow3Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Moonlight"
            moonDisplayTalentTV.text = "If night: Smites an random enemy with random damage every couple of seconds. Consecutive hits damage is multiplied."
            moonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].moonRow2Item1 + towerList[towerClickID].moonRow2Item2 >= 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].moonRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].moonRow3Item1 += 1

                        if (towerList[towerClickID].moonRow3Item1 == 1) towerList[towerClickID].moonLight += 1
                        if (towerList[towerClickID].moonRow3Item1 == 2) towerList[towerClickID].moonLight += 1
                        if (towerList[towerClickID].moonRow3Item1 == 3) towerList[towerClickID].moonLight += 1

                        if (towerList[towerClickID].moonRow3Item1 + towerList[towerClickID].moonRow3Item2 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow3Item1ShowTV.text = towerList[towerClickID].moonRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        moonRow3Item2IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Blood Moon"
            moonDisplayTalentTV.text = "Increases damage at night time by 10%/20%/30%."
            moonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].moonRow2Item1 + towerList[towerClickID].moonRow2Item2 >= 3) {
                moonUpgradeBTN.isClickable = true

                if (towerList[towerClickID].moonRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].moonRow3Item2 += 1

                    if (towerList[towerClickID].moonRow3Item2 == 1) towerList[towerClickID].damageMultiplyerNight += 0.1f
                    if (towerList[towerClickID].moonRow3Item2 == 2) towerList[towerClickID].damageMultiplyerNight += 0.1f
                    if (towerList[towerClickID].moonRow3Item2 == 3) towerList[towerClickID].damageMultiplyerNight += 0.1f

                    if (towerList[towerClickID].moonRow3Item1 + towerList[towerClickID].moonRow3Item2 >= 3) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    moonRow3Item2ShowTV.text = towerList[towerClickID].moonRow3Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        moonRow4Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Endless Night"
            moonDisplayTalentTV.text = "Active: Endless night. 3 min CD. Passive: Increases hit chance by 3/6/9 at night. At 3: removes extra evade at night. +1 bag space item at 3/3."
            moonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].moonRow3Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].moonRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].moonRow4Item1 += 1

                        if (towerList[towerClickID].moonRow4Item1 == 1) {
                            if (endlessNight < 3) endlessNight += 1
                            if (endlessNight < 1) {
                                activeAbilityList.add(0, ActiveAbility.aAid0)
                                insertSpell += 1
                            }
                        }
                        if (towerList[towerClickID].moonRow4Item1 == 2) if (endlessNight < 3) endlessNight += 1
                        if (towerList[towerClickID].moonRow4Item1 == 3) {
                            moonTalentEvadeNight = true
                            if (endlessNight < 3) endlessNight += 1
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                        }

                        moonRow4Item1ShowTV.text = towerList[towerClickID].moonRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

    }

}