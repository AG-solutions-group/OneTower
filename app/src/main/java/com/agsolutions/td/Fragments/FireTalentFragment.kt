package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.dayNightVariable
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
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
        return inflater.inflate(R.layout.fragment_fire_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireRow1Item1ShowTV.text = towerList[towerClickID].fireRow1Item1.toString()
        fireRow1Item2ShowTV.text = towerList[towerClickID].fireRow1Item2.toString()
        fireRow2Item1ShowTV.text = towerList[towerClickID].fireRow2Item1.toString()
        fireRow2Item2ShowTV.text = towerList[towerClickID].fireRow2Item2.toString()
        fireRow3Item1ShowTV.text = towerList[towerClickID].fireRow3Item1.toString()
        fireRow3Item2ShowTV.text = towerList[towerClickID].fireRow3Item2.toString()
        fireRow4Item1ShowTV.text = towerList[towerClickID].fireRow4Item1.toString()

        if (towerList[towerClickID].fireRow1Item1 == 1 || towerList[towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].fireRow2Item1 + towerList[towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].fireRow3Item1 + towerList[towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        fireRow1Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Candle"
            fireDisplayTalentTV.text = "Gain Experience for each critical strike (10% of enemy)."
            fireUpgradeBTN.isClickable = true

            fireUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].fireRow1Item1 + towerList[towerClickID].fireRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].fireRow1Item1 += 1

                    if (towerList[towerClickID].fireRow1Item1 == 1) towerList[towerClickID].experienceFireCrit = true

                    if (towerList[towerClickID].fireRow1Item1 == 1 || towerList[towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    fireRow1Item1ShowTV.text = towerList[towerClickID].fireRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        fireRow1Item2IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Hell"
            fireDisplayTalentTV.text = "Get 1 critical strike rating for each kill."
            fireUpgradeBTN.isClickable = true

            fireUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].fireRow1Item1 + towerList[towerClickID].fireRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].fireRow1Item2 += 1

                    if (towerList[towerClickID].fireRow1Item2 == 1) towerList[towerClickID].talentFireKill = true

                    if (towerList[towerClickID].fireRow1Item1 == 1 || towerList[towerClickID].fireRow1Item2 == 1) backgroundFireRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    fireRow1Item2ShowTV.text = towerList[towerClickID].fireRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        fireRow2Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Campfire"
            fireDisplayTalentTV.text = "Increases critical strike damage by 0.15."
            fireUpgradeBTN.isClickable = false

            if (towerList[towerClickID].fireRow1Item1 == 1 || towerList[towerClickID].fireRow1Item2 == 1) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].fireRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].fireRow2Item1 += 1

                        if (towerList[towerClickID].fireRow2Item1 == 1) towerList[towerClickID].fireTalentBonusCritDmg += 0.15f
                        if (towerList[towerClickID].fireRow2Item1 == 2) towerList[towerClickID].fireTalentBonusCritDmg += 0.15f
                        if (towerList[towerClickID].fireRow2Item1 == 3) towerList[towerClickID].fireTalentBonusCritDmg += 0.15f

                        if (towerList[towerClickID].fireRow2Item1 + towerList[towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item1ShowTV.text = towerList[towerClickID].fireRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow2Item2IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Burn"
            fireDisplayTalentTV.text = "Burns enemies critically hit for additional 2%/4%/6% of maximum hitpoints as magic damage over 2.4 seconds. Ignores shields. 50% damage to bosses."
            fireUpgradeBTN.isClickable = false

            if (towerList[towerClickID].fireRow1Item1 == 1 || towerList[towerClickID].fireRow1Item2 == 1) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].fireRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].fireRow2Item2 += 1

                        if (towerList[towerClickID].fireRow2Item2 == 1) {
                            towerList[towerClickID].fireBurnTalentDmg += 0.002f
                        }
                        if (towerList[towerClickID].fireRow2Item2 == 2){
                            towerList[towerClickID].fireBurnTalentDmg += 0.002f
                        }
                        if (towerList[towerClickID].fireRow2Item2 == 3){
                            towerList[towerClickID].fireBurnTalentDmg += 0.002f
                        }

                        if (towerList[towerClickID].fireRow2Item1 + towerList[towerClickID].fireRow2Item2 >= 3) backgroundFireRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow2Item2ShowTV.text = towerList[towerClickID].fireRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Lava"
            fireDisplayTalentTV.text = "Each consecutive non-critical hit increases critical damage by 0.05/0.075/0.1. Resets after critical hit."
            fireUpgradeBTN.isClickable = false

            if (towerList[towerClickID].fireRow2Item1 + towerList[towerClickID].fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].fireRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].fireRow3Item1 += 1

                        if (towerList[towerClickID].fireRow3Item1 == 1) towerList[towerClickID].bonusCritFire = 0.05f
                        if (towerList[towerClickID].fireRow3Item1 == 2) towerList[towerClickID].bonusCritFire = 0.075f
                        if (towerList[towerClickID].fireRow3Item1 == 3) towerList[towerClickID].bonusCritFire = 0.1f

                        if (towerList[towerClickID].fireRow3Item1 + towerList[towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item1ShowTV.text = towerList[towerClickID].fireRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow3Item2IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Sunburn"
            fireDisplayTalentTV.text = "During day: enemies build up sunburn stacks, which burns enemies. Stacks with Burn ability. Increases day time by 1 hour at 3/3."
            fireUpgradeBTN.isClickable = false

            if (towerList[towerClickID].fireRow2Item1 + towerList[towerClickID].fireRow2Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].fireRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].fireRow3Item2 += 1

                        if (towerList[towerClickID].fireRow3Item2 == 1) towerList[towerClickID].sunburn += 1
                        if (towerList[towerClickID].fireRow3Item2 == 2) towerList[towerClickID].sunburn += 1
                        if (towerList[towerClickID].fireRow3Item2 == 3) {
                            towerList[towerClickID].sunburn += 1
                            dayNightVariable -= 1
                        }

                        if (towerList[towerClickID].fireRow3Item1 + towerList[towerClickID].fireRow3Item2 >= 3) backgroundFireRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        fireRow3Item2ShowTV.text = towerList[towerClickID].fireRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        fireRow4Item1IB.setOnClickListener() {
            fireNameDisplayTalentTV.text = "Eternal Flame"
            fireDisplayTalentTV.text = "Increases multicrit count by 0/1/2. Plus 1 multicrit each 50 levels at 3/3. +1 bag space item at 3/3."
            fireUpgradeBTN.isClickable = false

            if (towerList[towerClickID].fireRow3Item1 + towerList[towerClickID].fireRow3Item2 >= 3) {
                fireUpgradeBTN.isClickable = true

                fireUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].fireRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].fireRow4Item1 += 1

                        if (towerList[towerClickID].fireRow4Item1 == 2) towerList[towerClickID].bonusmultiCrit += 1
                        if (towerList[towerClickID].fireRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            towerList[towerClickID].bonusmultiCrit += 1
                        }

                        fireRow4Item1ShowTV.text = towerList[towerClickID].fireRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

    }

}