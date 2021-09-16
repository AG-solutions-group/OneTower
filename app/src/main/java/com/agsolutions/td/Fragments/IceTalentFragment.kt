package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_ice_talent.*


class IceTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ice_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iceRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow1Item1.toString()
        iceRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow1Item2.toString()
        iceRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item1.toString()
        iceRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item2.toString()
        iceRow2Item3ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item3.toString()
        iceRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow3Item1.toString()
        iceRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow3Item2.toString()
        iceRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow4Item1.toString()

        if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 + companionList.towerList[companionList.towerClickID].iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)


        iceRow1Item1IB.setOnClickListener() {

            setImagePick(11)

            iceNameDisplayTalentTV.text = "Cold Approach"
            iceDisplayTalentTV.text =
                "Gain experience for each target slowed by ice attacks (3% of enemy)."
            iceUpgradeBTN.isClickable = true

            iceUpgradeBTN.setOnClickListener() {
                if (companionList.towerList[companionList.towerClickID].iceRow1Item1 + companionList.towerList[companionList.towerClickID].iceRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].iceRow1Item1 += 1

                    if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1) companionList.towerList[companionList.towerClickID].experienceSlow = true

                    if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    iceRow1Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow1Item1.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        iceRow1Item2IB.setOnClickListener() {

            setImagePick(12)

            iceNameDisplayTalentTV.text = "Deep Freeze."
            iceDisplayTalentTV.text = "Slowed enemies take +20% extra spelldmg."
            iceUpgradeBTN.isClickable = true

            iceUpgradeBTN.setOnClickListener() {
                if (companionList.towerList[companionList.towerClickID].iceRow1Item1 + companionList.towerList[companionList.towerClickID].iceRow1Item2 < 1 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                    companionList.towerList[companionList.towerClickID].iceRow1Item2 += 1

                    if (companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) companionList.towerList[companionList.towerClickID].slowExtraMgcDmg = true

                        if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    iceRow1Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow1Item2.toString()
                    companionList.towerList[companionList.towerClickID].talentPoints -= 1
                }
            }
        }

        iceRow2Item1IB.setOnClickListener() {

            setImagePick(21)

            iceNameDisplayTalentTV.text = "Ice Nova"
            iceDisplayTalentTV.text =
                "Reduces the cooldown of Ice Nova by 0.5/1/1.5 seconds and adds 33%/66%/100% spell damage."
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow2Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow2Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].iceNovaSpellDmg += 0.33f
                            companionList.towerList[companionList.towerClickID].iceNovaTimer -= 30
                        }
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].iceNovaSpellDmg += 0.33f
                            companionList.towerList[companionList.towerClickID].iceNovaTimer -= 30
                        }
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].iceNovaSpellDmg += 0.34f
                            companionList.towerList[companionList.towerClickID].iceNovaTimer -= 30
                        }

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow2Item2IB.setOnClickListener() {

            setImagePick(22)

            iceNameDisplayTalentTV.text = "Snowball"
            iceDisplayTalentTV.text =
                "10% chance to do additional magic damage equal to spell damage * 1.5/2/3"
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow2Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow2Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item2 == 1) companionList.towerList[companionList.towerClickID].iceExtraDmg += 1.5f
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item2 == 2) companionList.towerList[companionList.towerClickID].iceExtraDmg += 0.5f
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item2 == 3) companionList.towerList[companionList.towerClickID].iceExtraDmg += 1f

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow2Item3IB.setOnClickListener() {

            setImagePick(23)

            iceNameDisplayTalentTV.text = "Icy Arrow"
            iceDisplayTalentTV.text =
                "Slows enemy hit by 10%/15%/20%. Does not stack."
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow1Item1 == 1 || companionList.towerList[companionList.towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow2Item3 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow2Item3 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item3 == 1) companionList.towerList[companionList.towerClickID].slowEach += 10.0f
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item3 == 2) companionList.towerList[companionList.towerClickID].slowEach += 5.0f
                        if (companionList.towerList[companionList.towerClickID].iceRow2Item3 == 3) companionList.towerList[companionList.towerClickID].slowEach += 5.0f

                        if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item3ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow2Item3.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow3Item1IB.setOnClickListener() {

            setImagePick(31)

            iceNameDisplayTalentTV.text = "Cold Feet"
            iceDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 40%/50%/60%"
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow3Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow3Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 == 1) {
                            companionList.towerList[companionList.towerClickID].slowExtra += 40.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChance += 10
                        }
                        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 == 2) {
                            companionList.towerList[companionList.towerClickID].slowExtra += 10.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChance += 5
                        }
                        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 == 3) {
                            companionList.towerList[companionList.towerClickID].slowExtra += 10.0f
                            companionList.towerList[companionList.towerClickID].slowExtraChance += 5
                        }

                        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 + companionList.towerList[companionList.towerClickID].iceRow3Item2 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow3Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow3Item2IB.setOnClickListener() {

            setImagePick(32)

            iceNameDisplayTalentTV.text = "Cold Front"
            iceDisplayTalentTV.text = "Slows all enemies in range by 10%/15%/20%"
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow2Item1 + companionList.towerList[companionList.towerClickID].iceRow2Item2 + companionList.towerList[companionList.towerClickID].iceRow2Item3 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow3Item2 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow3Item2 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow3Item2 == 1) companionList.towerList[companionList.towerClickID].slowAura += 10.0f
                        if (companionList.towerList[companionList.towerClickID].iceRow3Item2 == 2) companionList.towerList[companionList.towerClickID].slowAura += 5.0f
                        if (companionList.towerList[companionList.towerClickID].iceRow3Item2 == 3) companionList.towerList[companionList.towerClickID].slowAura += 5.0f


                        if (companionList.towerList[companionList.towerClickID].iceRow3Item1 + companionList.towerList[companionList.towerClickID].iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item2ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow3Item2.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow4Item1IB.setOnClickListener() {

            setImagePick(41)

            iceNameDisplayTalentTV.text = "Blizzard"
            iceDisplayTalentTV.text =
                "Sends out icy shards into all directions that hit enemies for 10% (5% for boss and challenge) of maximum HP as magic dmg + spell dmg and applies all frost debuffs. +1 bag space at 3/3."
            iceUpgradeBTN.isClickable = false

            if (companionList.towerList[companionList.towerClickID].iceRow3Item1 + companionList.towerList[companionList.towerClickID].iceRow3Item2 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (companionList.towerList[companionList.towerClickID].iceRow4Item1 <= 2 && companionList.towerList[companionList.towerClickID].talentPoints > 0) {
                        companionList.towerList[companionList.towerClickID].iceRow4Item1 += 1

                        if (companionList.towerList[companionList.towerClickID].iceRow4Item1 == 1) {
                            companionList.iceShardTowerId = companionList.towerClickID
                            companionList.iceShard = true
                            companionList.iceShardSpeed -= 15
                        }
                        if (companionList.towerList[companionList.towerClickID].iceRow4Item1 == 2) companionList.iceShardSpeed -= 15
                        if (companionList.towerList[companionList.towerClickID].iceRow4Item1 == 3) {
                            companionList.itemList.add(0,Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            companionList.insertItem += 1
                            companionList.iceShardSpeed -= 15
                        }

                        iceRow4Item1ShowTV.text = companionList.towerList[companionList.towerClickID].iceRow4Item1.toString()
                        companionList.towerList[companionList.towerClickID].talentPoints -= 1
                    }
                }
            }
        }
    }

    fun setImagePick (pick: Int) {

        iceRow1Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow1Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow2Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow2Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow2Item3IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow3Item1IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow3Item2IBPick.setImageResource(R.drawable.overlaytransparent)
        iceRow4Item1IBPick.setImageResource(R.drawable.overlaytransparent)

        when(pick){
            11 -> iceRow1Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            12 -> iceRow1Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            21 -> iceRow2Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            22 -> iceRow2Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            23 -> iceRow2Item3IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            31 -> iceRow3Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            32 -> iceRow3Item2IBPick.setImageResource(R.drawable.backgroundsymbolpick)
            41 -> iceRow4Item1IBPick.setImageResource(R.drawable.backgroundsymbolpick)
        }
    }
}