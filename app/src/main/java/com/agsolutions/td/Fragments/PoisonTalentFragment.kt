package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.poisonCloud
import com.agsolutions.td.Companion.Companion.poisonTalentPest
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
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
        return inflater.inflate(R.layout.fragment_poison_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poisonRow1Item1ShowTV.text = towerList[towerClickID].poisonRow1Item1.toString()
        poisonRow1Item2ShowTV.text = towerList[towerClickID].poisonRow1Item2.toString()
        poisonRow2Item1ShowTV.text = towerList[towerClickID].poisonRow2Item1.toString()
        poisonRow2Item2ShowTV.text = towerList[towerClickID].poisonRow2Item2.toString()
        poisonRow3Item1ShowTV.text = towerList[towerClickID].poisonRow3Item1.toString()
        poisonRow3Item2ShowTV.text = towerList[towerClickID].poisonRow3Item2.toString()
        poisonRow4Item1ShowTV.text = towerList[towerClickID].poisonRow4Item1.toString()

        if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].poisonRow2Item1 + towerList[towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].poisonRow3Item1 + towerList[towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        poisonRow1Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Poison"
            poisonDisplayTalentTV.text = "Gain experience when enemies die from poison (200%)."

            poisonUpgradeBTN.setOnClickListener() {
                poisonUpgradeBTN.isClickable = true

                if (towerList[towerClickID].poisonRow1Item1 + towerList[towerClickID].poisonRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].poisonRow1Item1 += 1

                    if (towerList[towerClickID].poisonRow1Item1 == 1) towerList[towerClickID].experiencePoisonKill = true

                    if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    poisonRow1Item1ShowTV.text = towerList[towerClickID].poisonRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        poisonRow1Item2IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = ""
            poisonDisplayTalentTV.text = ""

            poisonUpgradeBTN.setOnClickListener() {
                poisonUpgradeBTN.isClickable = true

                if (towerList[towerClickID].poisonRow1Item1 + towerList[towerClickID].poisonRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].poisonRow1Item2 += 1

                    if (towerList[towerClickID].poisonRow1Item2 == 1)

                        if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    poisonRow1Item2ShowTV.text = towerList[towerClickID].poisonRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        poisonRow2Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Death Cap"
            poisonDisplayTalentTV.text = "Increases the damage of stackable poison debuff by 100%/200%/300%."
            poisonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1 ) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].poisonRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].poisonRow2Item1 += 1

                        if (towerList[towerClickID].poisonRow2Item1 == 1) towerList[towerClickID].stackablePoison += 0.1f
                        if (towerList[towerClickID].poisonRow2Item1 == 2) towerList[towerClickID].stackablePoison += 0.1f
                        if (towerList[towerClickID].poisonRow2Item1 == 3) towerList[towerClickID].stackablePoison += 0.1f

                        if (towerList[towerClickID].poisonRow2Item1 + towerList[towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item1ShowTV.text = towerList[towerClickID].poisonRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow2Item2IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Poison Overload"
            poisonDisplayTalentTV.text = "tower increases attack speed for 1.66 seconds every 40/30/20 seconds"
            poisonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1 ) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].poisonRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].poisonRow2Item2 += 1

                        if (towerList[towerClickID].poisonRow2Item2 == 1) towerList[towerClickID].poisonOverload = 2400
                        if (towerList[towerClickID].poisonRow2Item2 == 2) towerList[towerClickID].poisonOverload = 1800
                        if (towerList[towerClickID].poisonRow2Item2 == 3) towerList[towerClickID].poisonOverload = 1200

                        if (towerList[towerClickID].poisonRow2Item1 + towerList[towerClickID].poisonRow2Item2 >= 3) backgroundPoisonRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow2Item2ShowTV.text = towerList[towerClickID].poisonRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Entangle"
            poisonDisplayTalentTV.text = "Chance to entangle hit enemies. 50% entangle time for bosses."
            poisonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].poisonRow2Item1 + towerList[towerClickID].poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].poisonRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].poisonRow3Item1 += 1

                        if (towerList[towerClickID].poisonRow3Item1 == 1) towerList[towerClickID].entangle += 5
                        if (towerList[towerClickID].poisonRow3Item1 == 2) towerList[towerClickID].entangle += 5
                        if (towerList[towerClickID].poisonRow3Item1 == 3) towerList[towerClickID].entangle += 5

                        if (towerList[towerClickID].poisonRow3Item1 + towerList[towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item1ShowTV.text = towerList[towerClickID].poisonRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow3Item2IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Pestilence"
            poisonDisplayTalentTV.text = "Infects a random enemy with pestilence. Has a chance to spread to nearby enemies. Deals flat damage on boss and challenge, % damage on all other enemies."
            poisonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].poisonRow2Item1 + towerList[towerClickID].poisonRow2Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].poisonRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].poisonRow3Item2 += 1

                        if (towerList[towerClickID].poisonRow3Item2 == 1) poisonTalentPest += 0.2f
                        if (towerList[towerClickID].poisonRow3Item2 == 2) poisonTalentPest += 0.2f
                        if (towerList[towerClickID].poisonRow3Item2 == 3) poisonTalentPest += 0.2f

                        if (towerList[towerClickID].poisonRow3Item1 + towerList[towerClickID].poisonRow3Item2 >= 3) backgroundPoisonRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        poisonRow3Item2ShowTV.text = towerList[towerClickID].poisonRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        poisonRow4Item1IB.setOnClickListener() {
            poisonNameDisplayTalentTV.text = "Poison Cloud"
            poisonDisplayTalentTV.text = "Releases a poison cloud over a small area where enemies periodically get poison stacks. +1 bag space item at 3/3."
            poisonUpgradeBTN.isClickable = false

            if (towerList[towerClickID].poisonRow3Item1 + towerList[towerClickID].poisonRow3Item2 >= 3) {
                poisonUpgradeBTN.isClickable = true

                poisonUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].poisonRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].poisonRow4Item1 += 1

                        if (towerList[towerClickID].poisonRow4Item1 == 1) poisonCloud += 1
                        if (towerList[towerClickID].poisonRow4Item1 == 2) poisonCloud += 1
                        if (towerList[towerClickID].poisonRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            poisonCloud += 1
                        }

                        poisonRow4Item1ShowTV.text = towerList[towerClickID].poisonRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

    }

}