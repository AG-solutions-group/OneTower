package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.globalBonusTowerRange
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_wind_talent.*


class WindTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wind_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        windRow1Item1ShowTV.text = towerList[towerClickID].windRow1Item1.toString()
        windRow1Item1ShowTV.text = towerList[towerClickID].windRow1Item2.toString()
        windRow2Item1ShowTV.text = towerList[towerClickID].windRow2Item1.toString()
        windRow2Item2ShowTV.text = towerList[towerClickID].windRow2Item2.toString()
        windRow3Item1ShowTV.text = towerList[towerClickID].windRow3Item1.toString()
        windRow3Item2ShowTV.text = towerList[towerClickID].windRow3Item2.toString()
        windRow4Item1ShowTV.text = towerList[towerClickID].windRow4Item1.toString()

        if (towerList[towerClickID].windRow1Item1 == 1 || towerList[towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].windRow3Item1 + towerList[towerClickID].windRow3Item2 + towerList[towerClickID].windRow3Item2>= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        windRow1Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Speedy Recovery "
            windDisplayTalentTV.text = "Gain experience for each hit (3% of enemy)."
            windUpgradeBTN.isClickable = true

            windUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].windRow1Item1 + towerList[towerClickID].windRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].windRow1Item1 += 1

                    if (towerList[towerClickID].windRow1Item1 == 1) towerList[towerClickID].experienceEachHit = true

                    if (towerList[towerClickID].windRow1Item1 == 1 || towerList[towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    windRow1Item1ShowTV.text = towerList[towerClickID].windRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        windRow1Item2IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Air Drop"
            windDisplayTalentTV.text = "Each hit has a chance to drop an item that grants experience"
            windUpgradeBTN.isClickable = true

            windUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].windRow1Item1 + towerList[towerClickID].windRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].windRow1Item2 += 1

                    if (towerList[towerClickID].windRow1Item2 == 1) towerList[towerClickID].experienceDrop = true

                    if (towerList[towerClickID].windRow1Item1 == 1 || towerList[towerClickID].windRow1Item2 == 1) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    windRow1Item2ShowTV.text = towerList[towerClickID].windRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        windRow2Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Multishot"
            windDisplayTalentTV.text = "Debuff damage increased permanently by 10/20/30% after hit."
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow1Item1 == 1 || towerList[towerClickID].windRow1Item2 == 1) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow2Item1 += 1

                        if (towerList[towerClickID].windRow2Item1 == 1) {
                            towerList[towerClickID].windTalentDebuff += 0.1f
                        }
                        if (towerList[towerClickID].windRow2Item1 == 2) {
                            towerList[towerClickID].windTalentDebuff += 0.1f
                        }
                        if (towerList[towerClickID].windRow2Item1 == 3) {
                            towerList[towerClickID].windTalentDebuff += 0.1f
                        }

                        if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            windRow2Item1ShowTV.text = towerList[towerClickID].windRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow2Item2IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "High Ground"
            windDisplayTalentTV.text = "Increases Wind tower range by 15/30/45 units and all towers by 5/10/15."
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow1Item1 == 1 || towerList[towerClickID].windRow1Item2 == 1) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow2Item2 += 1

                        if (towerList[towerClickID].windRow2Item2 == 1) {
                            globalBonusTowerRange += 5
                            towerList[towerClickID].windTowerBonusTowerRange += 15
                        }
                        if (towerList[towerClickID].windRow2Item2 == 2) {
                            globalBonusTowerRange += 5
                            towerList[towerClickID].windTowerBonusTowerRange += 15
                        }
                        if (towerList[towerClickID].windRow2Item2 == 3) {
                            globalBonusTowerRange += 5
                            towerList[towerClickID].windTowerBonusTowerRange += 15
                        }

                        if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow2Item2ShowTV.text = towerList[towerClickID].windRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Pushback"
            windDisplayTalentTV.text = "Pushes enemies back 80/120/160 units."
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow3Item1 += 1

                        if (towerList[towerClickID].windRow3Item1 == 1) towerList[towerClickID].pushBack = 80.0f
                        if (towerList[towerClickID].windRow3Item1 == 2) towerList[towerClickID].pushBack = 120.0f
                        if (towerList[towerClickID].windRow3Item1 == 3) towerList[towerClickID].pushBack = 160.0f

                        if (towerList[towerClickID].windRow3Item1 + towerList[towerClickID].windRow3Item2 + towerList[towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item1ShowTV.text = towerList[towerClickID].windRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item2IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Tornado"
            windDisplayTalentTV.text = "Summons a tornado."
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow3Item2 += 1

                        if (towerList[towerClickID].windRow3Item2 == 1) towerList[towerClickID].tornadoRadius = 60.0f
                        if (towerList[towerClickID].windRow3Item2 == 2) towerList[towerClickID].tornadoRadius = 70.0f
                        if (towerList[towerClickID].windRow3Item2 == 3) towerList[towerClickID].tornadoRadius = 80.0f

                        if (towerList[towerClickID].windRow3Item1 + towerList[towerClickID].windRow3Item2 + towerList[towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item2ShowTV.text = towerList[towerClickID].windRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item3IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Winds of Change"
            windDisplayTalentTV.text = "Increases attack speed of nearby towers by an additional 5/10/15%"
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow2Item1 + towerList[towerClickID].windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow3Item3 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow3Item3 += 1

                        if (towerList[towerClickID].windRow3Item3 == 1) towerList[towerClickID].windExtraTowerSpd += 5f
                        if (towerList[towerClickID].windRow3Item3 == 2) towerList[towerClickID].windExtraTowerSpd += 5f
                        if (towerList[towerClickID].windRow3Item3 == 3) towerList[towerClickID].windExtraTowerSpd += 5f

                        if (towerList[towerClickID].windRow3Item1 + towerList[towerClickID].windRow3Item2 + towerList[towerClickID].windRow3Item3 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item3ShowTV.text = towerList[towerClickID].windRow3Item3.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        windRow4Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Speed of Light"
            windDisplayTalentTV.text = "Increases attack speed by 0.5%/1%/1.5% each hit until there is no target in range (Max 100%). Burns 1/2/3% max HP each hit as magic damage. +1 bag space item at 3/3."
            windUpgradeBTN.isClickable = false

            if (towerList[towerClickID].windRow3Item1 + towerList[towerClickID].windRow3Item2 + towerList[towerClickID].windRow3Item3 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].windRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].windRow4Item1 += 1

                        if (towerList[towerClickID].windRow4Item1 == 1) {
                            towerList[towerClickID].bonusSpeedWindTalent += 0.50f
                            towerList[towerClickID].windUltimatePercent += 0.01f
                        }
                        if (towerList[towerClickID].windRow4Item1 == 2) {
                            towerList[towerClickID].bonusSpeedWindTalent += 0.50f
                            towerList[towerClickID].windUltimatePercent += 0.01f
                        }
                        if (towerList[towerClickID].windRow4Item1 == 3) {
                            itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            towerList[towerClickID].bonusSpeedWindTalent += 0.50f
                            towerList[towerClickID].windUltimatePercent += 0.01f
                        }

                        windRow4Item1ShowTV.text = towerList[towerClickID].windRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

    }

}