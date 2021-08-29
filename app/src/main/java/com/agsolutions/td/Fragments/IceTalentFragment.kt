package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.iceShard
import com.agsolutions.td.Companion.Companion.iceShardSpeed
import com.agsolutions.td.Companion.Companion.iceShardTowerId
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
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

        iceRow1Item1ShowTV.text = towerList[towerClickID].iceRow1Item1.toString()
        iceRow1Item2ShowTV.text = towerList[towerClickID].iceRow1Item2.toString()
        iceRow2Item1ShowTV.text = towerList[towerClickID].iceRow2Item1.toString()
        iceRow2Item2ShowTV.text = towerList[towerClickID].iceRow2Item2.toString()
        iceRow2Item3ShowTV.text = towerList[towerClickID].iceRow2Item3.toString()
        iceRow3Item1ShowTV.text = towerList[towerClickID].iceRow3Item1.toString()
        iceRow3Item2ShowTV.text = towerList[towerClickID].iceRow3Item2.toString()
        iceRow4Item1ShowTV.text = towerList[towerClickID].iceRow4Item1.toString()

        if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].iceRow3Item1 + towerList[towerClickID].iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)


        iceRow1Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Cold Approach"
            iceDisplayTalentTV.text =
                "Gain experience for each target slowed by ice attacks (1% of enemy)."
            iceUpgradeBTN.isClickable = true

            iceUpgradeBTN.setOnClickListener() {
                if (towerList[towerClickID].iceRow1Item1 + towerList[towerClickID].iceRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].iceRow1Item1 += 1

                    if (towerList[towerClickID].iceRow1Item1 == 1) towerList[towerClickID].experienceSlow =
                        true

                    if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    iceRow1Item1ShowTV.text = towerList[towerClickID].iceRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        iceRow1Item2IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = ""
            iceDisplayTalentTV.text = ""
            iceUpgradeBTN.isClickable = true

            iceUpgradeBTN.setOnClickListener() {
                if (towerList[towerClickID].iceRow1Item1 + towerList[towerClickID].iceRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].iceRow1Item2 += 1

                    if (towerList[towerClickID].iceRow1Item2 == 1)

                        if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    iceRow1Item2ShowTV.text = towerList[towerClickID].iceRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        iceRow2Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Ice Nova"
            iceDisplayTalentTV.text =
                "Reduces the cooldown of Ice Nova by 0.5/1/1.5 seconds and adds 33%/66%/100% spell damage."
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow2Item1 += 1

                        if (towerList[towerClickID].iceRow2Item1 == 1) {
                            towerList[towerClickID].iceNovaSpellDmg += 0.33f
                            towerList[towerClickID].iceNovaTimer -= 30
                        }
                        if (towerList[towerClickID].iceRow2Item1 == 2) {
                            towerList[towerClickID].iceNovaSpellDmg += 0.33f
                            towerList[towerClickID].iceNovaTimer -= 30
                        }
                        if (towerList[towerClickID].iceRow2Item1 == 3) {
                            towerList[towerClickID].iceNovaSpellDmg += 0.34f
                            towerList[towerClickID].iceNovaTimer -= 30
                        }

                        if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item1ShowTV.text = towerList[towerClickID].iceRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow2Item2IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Snowball"
            iceDisplayTalentTV.text =
                "10% chance to do additional magic damage equal to spell damage * 0.75/1/1.25"
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow2Item2 += 1

                        if (towerList[towerClickID].iceRow2Item2 == 1) towerList[towerClickID].iceExtraDmg += 0.75f
                        if (towerList[towerClickID].iceRow2Item2 == 2) towerList[towerClickID].iceExtraDmg += 0.25f
                        if (towerList[towerClickID].iceRow2Item2 == 3) towerList[towerClickID].iceExtraDmg += 0.25f

                        if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item2ShowTV.text = towerList[towerClickID].iceRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow2Item3IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Icy Arrow"
            iceDisplayTalentTV.text =
                "Slows enemy hit by 10%/15%/20%. Does not stack. +20% spelldmg if already slowed. "
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow1Item1 == 1 || towerList[towerClickID].iceRow1Item2 == 1) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow2Item3 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow2Item3 += 1

                        if (towerList[towerClickID].iceRow2Item3 == 1) towerList[towerClickID].slowEach += 10.0f
                        if (towerList[towerClickID].iceRow2Item3 == 2) towerList[towerClickID].slowEach += 5.0f
                        if (towerList[towerClickID].iceRow2Item3 == 3) towerList[towerClickID].slowEach += 5.0f

                        if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item3ShowTV.text = towerList[towerClickID].iceRow2Item3.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow3Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Cold Feet"
            iceDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 40%/50%/60%"
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow3Item1 += 1

                        if (towerList[towerClickID].iceRow3Item1 == 1) {
                            towerList[towerClickID].slowExtra += 40.0f
                            towerList[towerClickID].slowExtraChance += 10
                        }
                        if (towerList[towerClickID].iceRow3Item1 == 2) {
                            towerList[towerClickID].slowExtra += 10.0f
                            towerList[towerClickID].slowExtraChance += 5
                        }
                        if (towerList[towerClickID].iceRow3Item1 == 3) {
                            towerList[towerClickID].slowExtra += 10.0f
                            towerList[towerClickID].slowExtraChance += 5
                        }

                        if (towerList[towerClickID].iceRow3Item1 + towerList[towerClickID].iceRow3Item2 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item1ShowTV.text = towerList[towerClickID].iceRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow3Item2IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Cold Front"
            iceDisplayTalentTV.text = "Slows all enemies in range by 10%/15%/20%"
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow2Item1 + towerList[towerClickID].iceRow2Item2 + towerList[towerClickID].iceRow2Item3 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow3Item2 += 1

                        if (towerList[towerClickID].iceRow3Item2 == 1) towerList[towerClickID].slowAura += 10.0f
                        if (towerList[towerClickID].iceRow3Item2 == 2) towerList[towerClickID].slowAura += 5.0f
                        if (towerList[towerClickID].iceRow3Item2 == 3) towerList[towerClickID].slowAura += 5.0f


                        if (towerList[towerClickID].iceRow3Item1 + towerList[towerClickID].iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item2ShowTV.text = towerList[towerClickID].iceRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        iceRow4Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Blizzard"
            iceDisplayTalentTV.text =
                "Sends out icy shards into all directions that hit enemies for 10% (5% for boss and challenge) of maximum HP as magic dmg + spell dmg and applies all frost debuffs. +1 bag space at 3/3."
            iceUpgradeBTN.isClickable = false

            if (towerList[towerClickID].iceRow3Item1 + towerList[towerClickID].iceRow3Item2 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].iceRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].iceRow4Item1 += 1

                        if (towerList[towerClickID].iceRow4Item1 == 1) {
                            iceShardTowerId = towerClickID
                            iceShard = true
                            iceShardSpeed -= 15
                        }
                        if (towerList[towerClickID].iceRow4Item1 == 2) iceShardSpeed -= 15
                        if (towerList[towerClickID].iceRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            iceShardSpeed -= 15
                        }

                        iceRow4Item1ShowTV.text = towerList[towerClickID].iceRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }
    }
}