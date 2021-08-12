package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.ActiveAbility
import com.agsolutions.td.Companion.Companion.activeAbilityList
import com.agsolutions.td.Companion.Companion.damageMultiplyerNight
import com.agsolutions.td.Companion.Companion.dayNightVariable
import com.agsolutions.td.Companion.Companion.endlessNight
import com.agsolutions.td.Companion.Companion.insertSpell
import com.agsolutions.td.Companion.Companion.itemStartBounce
import com.agsolutions.td.Companion.Companion.moonLight
import com.agsolutions.td.Companion.Companion.moonTalentEvadeNight
import com.agsolutions.td.Companion.Companion.moonTalentItemCost
import com.agsolutions.td.Companion.Companion.shotBounce
import com.agsolutions.td.Companion.Companion.shotBounceTargets
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_moon_talent.*


class MoonTalentFragment : Fragment() {
companion object {
    var moonRow1Item1 = 0
    var moonRow2Item1 = 0
    var moonRow2Item2 = 0
    var moonRow3Item1 = 0
    var moonRow4Item1 = 0

    fun moonTalentsLoad() {

        if (moonRow1Item1 == 1) damageMultiplyerNight += 0.1f
        if (moonRow1Item1 == 2) damageMultiplyerNight += 0.2f
        if (moonRow1Item1 == 3) damageMultiplyerNight += 0.3f

        if (moonRow2Item1 == 1){
            dayNightVariable += 1
            moonTalentItemCost += 0.05f
        }
        if (moonRow2Item1 == 2){
            dayNightVariable += 2
            moonTalentItemCost += 0.1f
        }
        if (moonRow2Item1 == 3){
            dayNightVariable += 3
            moonTalentItemCost += 0.2f
        }

        if (moonRow2Item2 == 1) {
            shotBounce = true
            if (itemStartBounce) shotBounceTargets += 2
            else shotBounceTargets += 1
        }
        if (moonRow2Item2 == 2) {
            shotBounce = true
            if (itemStartBounce) shotBounceTargets += 4
            else shotBounceTargets += 2
        }
        if (moonRow2Item2 == 3) {
            shotBounce = true
            if (itemStartBounce) shotBounceTargets += 6
            else shotBounceTargets += 3
        }

        if (moonRow3Item1 == 1) moonLight += 1
        if (moonRow3Item1 == 2) moonLight += 2
        if (moonRow3Item1 == 3) moonLight += 3

        if (moonRow4Item1 == 1) {
            endlessNight += 1
            activeAbilityList.add(0, ActiveAbility.aAid0)
            insertSpell +=1
        }
        if (moonRow4Item1 == 2) {
            endlessNight += 2
            activeAbilityList.add(0, ActiveAbility.aAid0)
            insertSpell +=1
        }
        if (moonRow4Item1 == 3) {
            endlessNight += 3
            activeAbilityList.add(0, ActiveAbility.aAid0)
            insertSpell +=1
            moonTalentEvadeNight = true
        }

    }
}

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

        moonRow1Item1ShowTV.text = moonRow1Item1.toString()
        moonRow2Item1ShowTV.text = moonRow2Item1.toString()
        moonRow2Item2ShowTV.text = moonRow2Item2.toString()
        moonRow3Item1ShowTV.text = moonRow3Item1.toString()
        moonRow4Item1ShowTV.text = moonRow4Item1.toString()

        if (moonRow1Item1 >= 3) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (moonRow2Item1 + moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (moonRow3Item1 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        moonRow1Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Blood Moon"
            moonDisplayTalentTV.text = "Increases damage at night time by 10%/20%/30%."

            moonUpgradeBTN.setOnClickListener() {
                moonUpgradeBTN.isClickable = true

                if (moonRow1Item1 <= 2 && talentPoints > 0) {
                    moonRow1Item1 += 1

                    if (moonRow1Item1 == 1) damageMultiplyerNight += 0.1f
                    if (moonRow1Item1 == 2) damageMultiplyerNight += 0.1f
                    if (moonRow1Item1 == 3) damageMultiplyerNight += 0.1f

                    if (moonRow1Item1 >= 3) backgroundMoonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    moonRow1Item1ShowTV.text = moonRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        moonRow2Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Full Moon"
            moonDisplayTalentTV.text = "Increases night time by 1 hour. Gold cost reduced by 5%/10%/20% at night."
            moonUpgradeBTN.isClickable = false

            if (moonRow1Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (moonRow2Item1 <= 2 && talentPoints > 0) {
                        moonRow2Item1 += 1

                        if (moonRow2Item1 == 1){
                            dayNightVariable += 1
                            moonTalentItemCost += 0.05f
                        }
                        if (moonRow2Item1 == 2){
                            dayNightVariable += 1
                            moonTalentItemCost += 0.05f
                        }
                        if (moonRow2Item1 == 3){
                            dayNightVariable += 1
                            moonTalentItemCost += 0.1f
                        }

                        if (moonRow2Item1 + moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow2Item1ShowTV.text = moonRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        moonRow2Item2IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Bounce"
            moonDisplayTalentTV.text = "Bullets can bounce to 1/2/3 targets dealing 50% damage each target. Does not stack with multishot, splash or BUTTERFLY single target abilities."
            moonUpgradeBTN.isClickable = false

            if (com.agsolutions.td.Companion.talentMultishot || com.agsolutions.td.Companion.splashRange > 0 || com.agsolutions.td.Companion.singleTargetBoost) {

            }else if (moonRow1Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (moonRow2Item2 <= 2 && talentPoints > 0) {
                        moonRow2Item2 += 1

                        if (moonRow2Item2 == 1) {
                            shotBounce = true
                            shotBounceTargets += 1
                        }
                        if (moonRow2Item2 == 2) {
                            shotBounce = true
                            if (itemStartBounce) shotBounceTargets += 2
                            else shotBounceTargets += 1
                        }
                        if (moonRow2Item2 == 3) {
                            shotBounce = true
                            if (itemStartBounce) shotBounceTargets += 2
                            else shotBounceTargets += 1
                        }

                        if (moonRow2Item1 + moonRow2Item2 >= 3) backgroundMoonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow2Item2ShowTV.text = moonRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        moonRow3Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Moonlight"
            moonDisplayTalentTV.text = "If night: Smites an random enemy with random damage every couple of seconds. Consecutive hits damage is multiplied."
            moonUpgradeBTN.isClickable = false

            if (moonRow2Item1 + moonRow2Item2 >= 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (moonRow3Item1 <= 2 && talentPoints > 0) {
                        moonRow3Item1 += 1

                        if (moonRow3Item1 == 1) moonLight += 1
                        if (moonRow3Item1 == 2) moonLight += 1
                        if (moonRow3Item1 == 3) moonLight += 1

                        if (moonRow3Item1 >= 3) backgroundMoonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        moonRow3Item1ShowTV.text = moonRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        moonRow4Item1IB.setOnClickListener() {
            moonNameDisplayTalentTV.text = "Endless Night"
            moonDisplayTalentTV.text = "Active: Endless night. 3 min CD. Passive: Increases hit chance by 3/6/9 at night. At 3: removes extra evade at night. +1 bag space at 3/3."
            moonUpgradeBTN.isClickable = false

            if (moonRow3Item1 == 3) {
                moonUpgradeBTN.isClickable = true

                moonUpgradeBTN.setOnClickListener() {
                    if (moonRow4Item1 <= 2 && talentPoints > 0) {
                        moonRow4Item1 += 1

                        if (moonRow4Item1 == 1) {
                            endlessNight += 1
                            activeAbilityList.add(0, ActiveAbility.aAid0)
                            insertSpell +=1
                        }
                        if (moonRow4Item1 == 2) endlessNight += 1
                        if (moonRow4Item1 == 3) {
                            moonTalentEvadeNight = true
                            endlessNight += 1
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                        }

                        moonRow4Item1ShowTV.text = moonRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

    }

}