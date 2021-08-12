package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.entangle
import com.agsolutions.td.Companion.Companion.globalBonusSpellDmgPercent
import com.agsolutions.td.Companion.Companion.poisonCloud
import com.agsolutions.td.Companion.Companion.poisonOverload
import com.agsolutions.td.Companion.Companion.poisonTalentPest
import com.agsolutions.td.Companion.Companion.stackablePoison
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_poison_talent.*


class PoisonTalentFragment : Fragment() {
companion object {
    var poisonRow1Item1 = 0
    var poisonRow2Item1 = 0
    var poisonRow2Item2 = 0
    var poisonRow3Item1 = 0
    var poisonRow3Item2 = 0
    var poisonRow4Item1 = 0

    fun poisonTalentsLoad() {

        if (poisonRow1Item1 == 1) globalBonusSpellDmgPercent += 5.0f
        if (poisonRow1Item1 == 2) globalBonusSpellDmgPercent += 10.0f
        if (poisonRow1Item1 == 3) globalBonusSpellDmgPercent += 15.0f

        if (poisonRow2Item1 == 1) stackablePoison += 0.1f
        if (poisonRow2Item1 == 2) stackablePoison += 0.2f
        if (poisonRow2Item1 == 3) stackablePoison += 0.3f

        if (poisonRow2Item2 == 1) poisonOverload = 2400
        if (poisonRow2Item2 == 2) poisonOverload = 1800
        if (poisonRow2Item2 == 3) poisonOverload = 1200

        if (poisonRow3Item1 == 1) entangle += 5
        if (poisonRow3Item1 == 2) entangle += 10
        if (poisonRow3Item1 == 3) entangle += 15

        if (poisonRow3Item2 == 1) poisonTalentPest = 1f
        if (poisonRow3Item2 == 2) poisonTalentPest = 1.5f
        if (poisonRow3Item2 == 3) poisonTalentPest = 2f

        if (poisonRow4Item1 == 1) poisonCloud += 1
        if (poisonRow4Item1 == 2) poisonCloud += 2
        if (poisonRow4Item1 == 3) poisonCloud += 3
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
        return inflater.inflate(R.layout.fragment_poison_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poisonRow1Item1ShowTV.text = poisonRow1Item1.toString()
        poisonRow2Item1ShowTV.text = poisonRow2Item1.toString()
        poisonRow2Item2ShowTV.text = poisonRow2Item2.toString()
        poisonRow3Item1ShowTV.text = poisonRow3Item1.toString()
        poisonRow3Item2ShowTV.text = poisonRow3Item2.toString()
        poisonRow4Item1ShowTV.text = poisonRow4Item1.toString()

        if(poisonRow1Item1 == 3) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (poisonRow2Item1 + poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (poisonRow3Item1 + poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        poisonRow1Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Growth Enhancer"
            poisonDisplayTalentTV.text = "Improves spell damage by 5%/10%/15%"

            poisonUpgradeBTN.setOnClickListener() {
                poisonUpgradeBTN.isClickable = true

                if (poisonRow1Item1 <= 2 && talentPoints > 0) {
                    poisonRow1Item1 += 1

                    if (poisonRow1Item1 == 1) globalBonusSpellDmgPercent += 5.0f
                    if (poisonRow1Item1 == 2) globalBonusSpellDmgPercent += 5.0f
                    if (poisonRow1Item1 == 3) globalBonusSpellDmgPercent += 5.0f

                    if(poisonRow1Item1 == 3) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    poisonRow1Item1ShowTV.text = poisonRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        poisonRow2Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Death Cap"
            poisonDisplayTalentTV.text = "Applies a stackable poison debuff that deals magic damage over time."
            poisonUpgradeBTN.isClickable = false

            if (poisonRow1Item1 == 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (poisonRow2Item1 <= 2 && talentPoints > 0) {
                        poisonRow2Item1 += 1

                        if (poisonRow2Item1 == 1) stackablePoison += 0.1f
                        if (poisonRow2Item1 == 2) stackablePoison += 0.1f
                        if (poisonRow2Item1 == 3) stackablePoison += 0.1f

                        if (poisonRow2Item1 + poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item1ShowTV.text = poisonRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        poisonRow2Item2IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Poison Overload"
            poisonDisplayTalentTV.text = "tower increases attack speed for 1.66 seconds every 40/30/20 seconds"
            poisonUpgradeBTN.isClickable = false

            if (poisonRow1Item1 == 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (poisonRow2Item2 <= 2 && talentPoints > 0) {
                        poisonRow2Item2 += 1

                        if (poisonRow2Item2 == 1) poisonOverload = 2400
                        if (poisonRow2Item2 == 2) poisonOverload = 1800
                        if (poisonRow2Item2 == 3) poisonOverload = 1200

                        if (poisonRow2Item1 + poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item2ShowTV.text = poisonRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Entangle"
            poisonDisplayTalentTV.text = "Chance to entangle hit enemies. 50% entangle time for bosses."
            poisonUpgradeBTN.isClickable = false

            if (poisonRow2Item1 + poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (poisonRow3Item1 <= 2 && talentPoints > 0) {
                        poisonRow3Item1 += 1

                        if (poisonRow3Item1 == 1) entangle += 5
                        if (poisonRow3Item1 == 2) entangle += 5
                        if (poisonRow3Item1 == 3) entangle += 5

                        if (poisonRow3Item1 + poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item1ShowTV.text = poisonRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item2IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Pestilence"
            poisonDisplayTalentTV.text = "Infects a random enemy with pestilence. Has a chance to spread to nearby enemies. Deals flat damage on boss and challenge, % damage on all other enemies."
            poisonUpgradeBTN.isClickable = false

            if (poisonRow2Item1 + poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (poisonRow3Item2 <= 2 && talentPoints > 0) {
                        poisonRow3Item2 += 1

                        if (poisonRow3Item2 == 1) poisonTalentPest = 1f
                        if (poisonRow3Item2 == 2) poisonTalentPest = 1.5f
                        if (poisonRow3Item2 == 3) poisonTalentPest = 2f

                        if (poisonRow3Item1 + poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item2ShowTV.text = poisonRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        poisonRow4Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Poison Cloud"
            poisonDisplayTalentTV.text = "Releases a poison cloud over a small area where enemies periodically get poison stacks. +1 bag space at 3/3."
            poisonUpgradeBTN.isClickable = false

            if (poisonRow3Item1 + poisonRow3Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (poisonRow4Item1 <= 2 && talentPoints > 0) {
                        poisonRow4Item1 += 1

                        if (poisonRow4Item1 == 1) poisonCloud += 1
                        if (poisonRow4Item1 == 2) poisonCloud += 1
                        if (poisonRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            poisonCloud += 1
                        }

                        poisonRow4Item1ShowTV.text = poisonRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

    }

}