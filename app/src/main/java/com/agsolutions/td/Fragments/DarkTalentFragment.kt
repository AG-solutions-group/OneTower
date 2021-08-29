package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_dark_talent.*


class DarkTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dark_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        darkRow1Item1ShowTV.text = towerList[towerClickID].darkRow1Item1.toString()
        darkRow1Item2ShowTV.text = towerList[towerClickID].darkRow1Item2.toString()
        darkRow2Item1ShowTV.text = towerList[towerClickID].darkRow2Item1.toString()
        darkRow2Item2ShowTV.text = towerList[towerClickID].darkRow2Item2.toString()
        darkRow3Item1ShowTV.text = towerList[towerClickID].darkRow3Item1.toString()
        darkRow3Item2ShowTV.text = towerList[towerClickID].darkRow3Item2.toString()
        darkRow3Item3ShowTV.text = towerList[towerClickID].darkRow3Item3.toString()
        darkRow4Item1ShowTV.text = towerList[towerClickID].darkRow4Item1.toString()

        if (towerList[towerClickID].darkRow1Item1 == 1 || towerList[towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].darkRow3Item1 + towerList[towerClickID].darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        darkRow1Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Head Start"
            darkDisplayTalentTV.text = "Gain 5 tower levels."
            darkUpgradeBTN.isClickable = true

            darkUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].darkRow1Item1 + towerList[towerClickID].darkRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].darkRow1Item1 += 1

                    if (towerList[towerClickID].darkRow1Item1 == 1) towerList[towerClickID].towerLevelBool = true

                    if (towerList[towerClickID].darkRow1Item1 == 1 || towerList[towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    darkRow1Item1ShowTV.text = towerList[towerClickID].darkRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        darkRow1Item2IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Leveler"
            darkDisplayTalentTV.text = "Gain 1 enemy worth of experience per level."
            darkUpgradeBTN.isClickable = true

            darkUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].darkRow1Item1 + towerList[towerClickID].darkRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].darkRow1Item2 += 1

                    if (towerList[towerClickID].darkRow1Item2 == 1) towerList[towerClickID].experienceLvl = true

                    if (towerList[towerClickID].darkRow1Item1 == 1 || towerList[towerClickID].darkRow1Item2 == 1) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    darkRow1Item2ShowTV.text = towerList[towerClickID].darkRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        darkRow2Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Fear"
            darkDisplayTalentTV.text = "Casts a spell that sometimes causes enemies to run away in fear. Points decrease spell cooldown and increase fear duration. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow1Item1 == 1 || towerList[towerClickID].darkRow1Item2 == 1) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].darkRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow2Item1 += 1

                        if (towerList[towerClickID].darkRow2Item1 == 1) {
                            towerList[towerClickID].darkTalentFear = true
                            towerList[towerClickID].fearDuration += 1.0f
                            towerList[towerClickID].fearTimer = 150
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow2Item1 == 2) {
                            towerList[towerClickID].darkTalentFear = true
                            towerList[towerClickID].fearDuration += 0.5f
                            towerList[towerClickID].fearTimer = 120
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow2Item1 == 3) {
                            towerList[towerClickID].darkTalentFear = true
                            towerList[towerClickID].fearDuration += 0.5f
                            towerList[towerClickID].fearTimer = 90
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item1ShowTV.text = towerList[towerClickID].darkRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow2Item2IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Cold Sweat"
            darkDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 20%/25%/30% permanently. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow1Item1 == 1 || towerList[towerClickID].darkRow1Item2 == 1) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].darkRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow2Item2 += 1

                        if (towerList[towerClickID].darkRow2Item2 == 1) {
                            towerList[towerClickID].slowExtraDark = 20.0f
                            towerList[towerClickID].slowExtraChanceDark = 10
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow2Item2 == 2) {
                            towerList[towerClickID].slowExtraDark = 25.0f
                            towerList[towerClickID].slowExtraChanceDark = 15
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow2Item2 == 3) {
                            towerList[towerClickID].slowExtraDark = 30.0f
                            towerList[towerClickID].slowExtraChanceDark = 20
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item2ShowTV.text = towerList[towerClickID].darkRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Soul Crusher"
            darkDisplayTalentTV.text = "Adds 10%/15%/20% magic damage each hit. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].darkRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow3Item1 += 1

                        if (towerList[towerClickID].darkRow3Item1 == 1) {
                            towerList[towerClickID].darkMagicDmgPercent = 0.1f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item1 == 2) {
                            towerList[towerClickID].darkMagicDmgPercent = 0.15f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item1 == 3) {
                            towerList[towerClickID].darkMagicDmgPercent = 0.2f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (towerList[towerClickID].darkRow3Item1 + towerList[towerClickID].darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item1ShowTV.text = towerList[towerClickID].darkRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item2IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Dark Hand"
            darkDisplayTalentTV.text = "Grabs a random unit and reduces maximum hitpoints by 6%/12%/18% per second. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].darkRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow3Item2 += 1

                        if (towerList[towerClickID].darkRow3Item2 == 1) {
                            towerList[towerClickID].darkTalentLaser += 0.1f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item2 == 2) {
                            towerList[towerClickID].darkTalentLaser += 0.1f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item2 == 3) {
                            towerList[towerClickID].darkTalentLaser += 0.1f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (towerList[towerClickID].darkRow3Item1 + towerList[towerClickID].darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item2ShowTV.text = towerList[towerClickID].darkRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item3IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Dark Curse"
            darkDisplayTalentTV.text =
                "Enemies hit take + 0.2%/0.35%/0.5% bonus dmg for 3 sec. Does stack. +1%/2%/3% anti-immune. "

            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow2Item1 + towerList[towerClickID].darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {

                    if (towerList[towerClickID].darkRow3Item3 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow3Item3 += 1

                        if (towerList[towerClickID].darkRow3Item3 == 1) {
                            towerList[towerClickID].darkDmgDebuff = 0.2f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item3 == 2) {
                            towerList[towerClickID].darkDmgDebuff = 0.35f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow3Item3 == 3) {
                            towerList[towerClickID].darkDmgDebuff = 0.5f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        if (towerList[towerClickID].darkRow3Item3 >= 3) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow1Item1ShowTV.text = towerList[towerClickID].darkRow3Item3.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        darkRow4Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Soul Collector"
            darkDisplayTalentTV.text = "Receive an item that collects souls adding permanent physical damage for each kill. +1%/2%/3% anti-immune. +1 bag space item at 3/3."
            darkUpgradeBTN.isClickable = false

            if (towerList[towerClickID].darkRow3Item1 + towerList[towerClickID].darkRow3Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].darkRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].darkRow4Item1 += 1


                        if (towerList[towerClickID].darkRow4Item1 == 1){
                            com.agsolutions.td.Companion.dropItemDarkUltimate += 1
                            towerList[towerClickID].darkPermaKill += 0.25f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow4Item1 == 2) {
                            towerList[towerClickID].darkPermaKill += 0.25f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }
                        if (towerList[towerClickID].darkRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            towerList[towerClickID].darkPermaKill += 0.25f
                            towerList[towerClickID].bonusDmgImmune += 0.01f
                        }

                        darkRow4Item1ShowTV.text = towerList[towerClickID].darkRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }
    }
}