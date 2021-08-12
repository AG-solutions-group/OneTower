package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.bonusCritFire
import com.agsolutions.td.Companion.Companion.dayNightVariable
import com.agsolutions.td.Companion.Companion.fireBurnTalent
import com.agsolutions.td.Companion.Companion.fireTalentBonusCrit
import com.agsolutions.td.Companion.Companion.fireTalentBonusCritDmg
import com.agsolutions.td.Companion.Companion.globalMultiCrit
import com.agsolutions.td.Companion.Companion.sunburn
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_fire_talent.*


class FireTalentFragment : Fragment() {
companion object {
    var fireRow1Item1 = 0
    var fireRow2Item1 = 0
    var fireRow2Item2 = 0
    var fireRow3Item1 = 0
    var fireRow3Item2 = 0
    var fireRow4Item1 = 0

    fun fireTalentsLoad() {

        if (fireRow1Item1 == 1) fireTalentBonusCrit += 3.0f
        if (fireRow1Item1 == 2) fireTalentBonusCrit += 6.0f
        if (fireRow1Item1 == 3) fireTalentBonusCrit += 9.0f

        if (fireRow2Item1 == 1) fireTalentBonusCritDmg += 0.15f
        if (fireRow2Item1 == 2) fireTalentBonusCritDmg += 0.3f
        if (fireRow2Item1 == 3) fireTalentBonusCritDmg += 0.45f

        if (fireRow2Item2 == 1) fireBurnTalent += 1
        if (fireRow2Item2 == 2) fireBurnTalent += 2
        if (fireRow2Item2 == 3) fireBurnTalent += 3

        if (fireRow3Item1 == 1) bonusCritFire = 0.05f
        if (fireRow3Item1 == 2) bonusCritFire = 0.075f
        if (fireRow3Item1 == 3) bonusCritFire = 0.1f

        if (fireRow3Item2 == 1) sunburn += 1
        if (fireRow3Item2 == 2) sunburn += 2
        if (fireRow3Item2 == 3) sunburn += 3

        if (fireRow4Item1 == 1) dayNightVariable -= 1
        if (fireRow4Item1 == 2) {
            dayNightVariable -= 1
            globalMultiCrit += 1
        }
        if (fireRow4Item1 == 3) {
            dayNightVariable -= 1
            globalMultiCrit += 2
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
        return inflater.inflate(R.layout.fragment_fire_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireRow1Item1ShowTV.text = fireRow1Item1.toString()
        fireRow2Item1ShowTV.text = fireRow2Item1.toString()
        fireRow2Item2ShowTV.text = fireRow2Item2.toString()
        fireRow3Item1ShowTV.text = fireRow3Item1.toString()
        fireRow3Item2ShowTV.text = fireRow3Item2.toString()
        fireRow4Item1ShowTV.text = fireRow4Item1.toString()

        if (fireRow1Item1 >= 3) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (fireRow2Item1 + fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (fireRow3Item1 + fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        fireRow1Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Candle"
            fireDisplayTalentTV.text = "Increases critical strike chance by 3. "

            fireUpgradeBTN.setOnClickListener() {
                fireUpgradeBTN.isClickable = true

                if (fireRow1Item1 <= 2 && talentPoints > 0) {
                    fireRow1Item1 += 1

                    if (fireRow1Item1 == 1) fireTalentBonusCrit += 3.0f
                    if (fireRow1Item1 == 2) fireTalentBonusCrit += 3.0f
                    if (fireRow1Item1 == 3) fireTalentBonusCrit += 3.0f

                    if (fireRow1Item1 >= 3) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    fireRow1Item1ShowTV.text = fireRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        fireRow2Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Campfire"
            fireDisplayTalentTV.text = "Increases critical strike damage by 0.15."
            fireUpgradeBTN.isClickable = false

            if (fireRow1Item1 == 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (fireRow2Item1 <= 2 && talentPoints > 0) {
                        fireRow2Item1 += 1

                        if (fireRow2Item1 == 1) fireTalentBonusCritDmg += 0.15f
                        if (fireRow2Item1 == 2) fireTalentBonusCritDmg += 0.15f
                        if (fireRow2Item1 == 3) fireTalentBonusCritDmg += 0.15f

                        if (fireRow2Item1 + fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item1ShowTV.text = fireRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        fireRow2Item2IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Burn"
            fireDisplayTalentTV.text = "Burns enemies critically hit for 4.5%/6.75%/9% of maximum hitpoints as magic damage over 2.4 seconds. Ignores shields. 50% damage to bosses."
            fireUpgradeBTN.isClickable = false

            if (fireRow1Item1 == 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (fireRow2Item2 <= 2 && talentPoints > 0) {
                        fireRow2Item2 += 1

                        if (fireRow2Item2 == 1) fireBurnTalent += 1
                        if (fireRow2Item2 == 2) fireBurnTalent += 1
                        if (fireRow2Item2 == 3) fireBurnTalent += 1

                        if (fireRow2Item1 + fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item2ShowTV.text = fireRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Lava"
            fireDisplayTalentTV.text = "Each consecutive non-critical hit increases critical damage by 0.05/0.075/0.1. Resets after critical hit."
            fireUpgradeBTN.isClickable = false

            if (fireRow2Item1 + fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (fireRow3Item1 <= 2 && talentPoints > 0) {
                        fireRow3Item1 += 1

                        if (fireRow3Item1 == 1) bonusCritFire = 0.05f
                        if (fireRow3Item1 == 2) bonusCritFire = 0.075f
                        if (fireRow3Item1 == 3) bonusCritFire = 0.1f

                        if (fireRow3Item1 + fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item1ShowTV.text = fireRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item2IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Sunburn"
            fireDisplayTalentTV.text = "During day: enemies build up sunburn stacks, which burns enemies. Stacks with Burn ability."
            fireUpgradeBTN.isClickable = false

            if (fireRow2Item1 + fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (fireRow3Item2 <= 2 && talentPoints > 0) {
                        fireRow3Item2 += 1

                        if (fireRow3Item2 == 1) sunburn += 1
                        if (fireRow3Item2 == 2) sunburn += 1
                        if (fireRow3Item2 == 3) sunburn += 1

                        if (fireRow3Item1 + fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item2ShowTV.text = fireRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        fireRow4Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Eternal Flame"
            fireDisplayTalentTV.text = "Increases multicrit count by 0/1/2. Increases day time by 1 hour. Plus 1 multicrit each 50 levels at 3/3. +1 bag space at 3/3."
            fireUpgradeBTN.isClickable = false

            if (fireRow3Item1 + fireRow3Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (fireRow4Item1 <= 2 && talentPoints > 0) {
                        fireRow4Item1 += 1

                        if (fireRow4Item1 == 1) dayNightVariable -= 1
                        if (fireRow4Item1 == 2) globalMultiCrit += 1
                        if (fireRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            globalMultiCrit += 1
                        }

                        fireRow4Item1ShowTV.text = fireRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

    }

}