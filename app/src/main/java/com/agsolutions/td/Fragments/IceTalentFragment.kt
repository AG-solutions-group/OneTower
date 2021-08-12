package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion
import com.agsolutions.td.Companion.Companion.iceExtraDmg
import com.agsolutions.td.Companion.Companion.iceNova
import com.agsolutions.td.Companion.Companion.iceNovaTimer
import com.agsolutions.td.Companion.Companion.iceShardSpeed
import com.agsolutions.td.Companion.Companion.slowAura
import com.agsolutions.td.Companion.Companion.slowEach
import com.agsolutions.td.Companion.Companion.slowExtra
import com.agsolutions.td.Companion.Companion.slowExtraChance
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_ice_talent.*


class IceTalentFragment : Fragment() {
companion object {
    var iceRow1Item1 = 0
    var iceRow2Item1 = 0
    var iceRow2Item2 = 0
    var iceRow3Item1 = 0
    var iceRow3Item2 = 0
    var iceRow4Item1 = 0

    fun iceTalentsLoad() {

        if (iceRow1Item1 == 1) slowEach += 10.0f
        if (iceRow1Item1 == 2) slowEach += 15.0f
        if (iceRow1Item1 == 3) slowEach += 20.0f

        if (iceRow2Item1 == 1) {
            iceNova = true
            iceNovaTimer = 300
        }
        if (iceRow2Item1 == 2) {
            iceNova = true
            iceNovaTimer = 240
        }
        if (iceRow2Item1 == 3) {
            iceNova = true
            iceNovaTimer = 180
        }

        if (iceRow2Item2 == 1) {
            slowExtra += 40.0f
            slowExtraChance += 10
        }
        if (iceRow2Item2 == 2) {
            slowExtra += 50.0f
            slowExtraChance += 15
        }
        if (iceRow2Item2 == 3) {
            slowExtra += 60.0f
            slowExtraChance += 20
        }

        if (iceRow3Item1 == 1) iceExtraDmg += 0.75f
        if (iceRow3Item1 == 2) iceExtraDmg += 1.0f
        if (iceRow3Item1 == 3) iceExtraDmg += 1.25f

        if (iceRow3Item2 == 1) slowAura += 10.0f
        if (iceRow3Item2 == 2) slowAura += 5.0f
        if (iceRow3Item2 == 3) slowAura += 5.0f

        if (iceRow4Item1 == 1) iceShardSpeed = 100
        if (iceRow4Item1 == 2) iceShardSpeed = 75
        if (iceRow4Item1 == 3) iceShardSpeed = 50

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
        return inflater.inflate(R.layout.fragment_ice_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iceRow1Item1ShowTV.text = iceRow1Item1.toString()
        iceRow2Item1ShowTV.text = iceRow2Item1.toString()
        iceRow2Item2ShowTV.text = iceRow2Item2.toString()
        iceRow3Item1ShowTV.text = iceRow3Item1.toString()
        iceRow3Item2ShowTV.text = iceRow3Item2.toString()
        iceRow4Item1ShowTV.text = iceRow4Item1.toString()

        if (iceRow1Item1 >= 3) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (iceRow2Item1 + iceRow2Item2 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (iceRow3Item1 + iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)


        iceRow1Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Icy Arrow"
            iceDisplayTalentTV.text = "Slows enemy hit by 10%/15%/20%. Does not stack. +20% spelldmg if already slowed. "
            iceUpgradeBTN.isClickable = true

            iceUpgradeBTN.setOnClickListener() {
                if (iceRow1Item1 <= 2 && talentPoints > 0) {
                    iceRow1Item1 += 1

                    if (iceRow1Item1 == 1) slowEach += 10.0f
                    if (iceRow1Item1 == 2) slowEach += 5.0f
                    if (iceRow1Item1 == 3) slowEach += 5.0f

                    if (iceRow1Item1 >= 3) backgroundIceRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    iceRow1Item1ShowTV.text = iceRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        iceRow2Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Ice Nova"
            iceDisplayTalentTV.text = "Blasts the target and nearby enemies for spell damage each 5/4/3 seconds and slows them for a short duration."
            iceUpgradeBTN.isClickable = false

            if (iceRow1Item1 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (iceRow2Item1 <= 2 && talentPoints > 0) {
                        iceRow2Item1 += 1

                        if (iceRow2Item1 == 1) {
                            iceNova = true
                            iceNovaTimer = 300
                        }
                        if (iceRow2Item1 == 2) iceNovaTimer = 240
                        if (iceRow2Item1 == 3) iceNovaTimer = 180

                        if (iceRow2Item1 + iceRow2Item2 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item1ShowTV.text = iceRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }


        iceRow2Item2IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Cold Feet"
            iceDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 40%/50%/60%"
            iceUpgradeBTN.isClickable = false

            if (iceRow1Item1 == 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (iceRow2Item2 <= 2 && talentPoints > 0) {
                        iceRow2Item2 += 1

                        if (iceRow2Item2 == 1) {
                            slowExtra += 40.0f
                            slowExtraChance += 10
                        }
                        if (iceRow2Item2 == 2) {
                            slowExtra += 10.0f
                            slowExtraChance += 5
                        }
                        if (iceRow2Item2 == 3) {
                            slowExtra += 10.0f
                            slowExtraChance += 5
                        }

                        if (iceRow2Item1 + iceRow2Item2 >= 3) backgroundIceRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow2Item2ShowTV.text = iceRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        iceRow3Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Snowball"
            iceDisplayTalentTV.text = "10% chance to do additional magic damage equal to spell damage * 0.75/1/1.25"
            iceUpgradeBTN.isClickable = false

            if (iceRow2Item1 + iceRow2Item2 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (iceRow3Item1 <= 2 && talentPoints > 0) {
                        iceRow3Item1 += 1

                        if (iceRow3Item1 == 1) iceExtraDmg += 0.75f
                        if (iceRow3Item1 == 2) iceExtraDmg += 0.25f
                        if (iceRow3Item1 == 3) iceExtraDmg += 0.25f

                        if (iceRow3Item1 + iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item1ShowTV.text = iceRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }


        iceRow3Item2IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Cold Front"
            iceDisplayTalentTV.text = "Slows all enemies in range by 10%/15%/20%"
            iceUpgradeBTN.isClickable = false

            if (iceRow2Item1 + iceRow2Item2 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (iceRow3Item2 <= 2 && talentPoints > 0) {
                        iceRow3Item2 += 1

                        if (iceRow3Item2 == 1) slowAura += 10.0f
                        if (iceRow3Item2 == 2) slowAura += 5.0f
                        if (iceRow3Item2 == 3) slowAura += 5.0f


                        if (iceRow3Item1 + iceRow3Item2 >= 3) backgroundIceRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        iceRow3Item2ShowTV.text = iceRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        iceRow4Item1IB.setOnClickListener() {
            iceNameDisplayTalentTV.text = "Blizzard"
            iceDisplayTalentTV.text = "Sends out icy shards into all directions that hit enemies for 10% (5% for boss and challenge) of maximum HP as magic dmg + spell dmg and applies all frost debuffs. +1 bag space at 3/3."
            iceUpgradeBTN.isClickable = false

            if (iceRow3Item1 + iceRow3Item2 >= 3) {
                iceUpgradeBTN.isClickable = true

                iceUpgradeBTN.setOnClickListener() {
                    if (iceRow4Item1 <= 2 && talentPoints > 0) {
                        iceRow4Item1 += 1

                        if (iceRow4Item1 == 1) iceShardSpeed = 100
                        if (iceRow4Item1 == 2) iceShardSpeed = 75
                        if (iceRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            iceShardSpeed = 50
                        }

                        iceRow4Item1ShowTV.text = iceRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

    }

}