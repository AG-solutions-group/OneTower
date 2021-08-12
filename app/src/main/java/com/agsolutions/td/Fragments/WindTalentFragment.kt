package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.bonusSpeedWindTalent
import com.agsolutions.td.Companion.Companion.globalBonusTowerRange
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.pushBack
import com.agsolutions.td.Companion.Companion.talentMultishot
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Companion.Companion.tornadoRadius
import com.agsolutions.td.Companion.Companion.windBonusDamageMultiplyer
import com.agsolutions.td.Companion.Companion.windTalentBonusSpeed
import com.agsolutions.td.Companion.Companion.windTalentDebuff
import com.agsolutions.td.Companion.Companion.windTowerBonusTowerRange
import com.agsolutions.td.Companion.Companion.windUltimatePercent
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_wind_talent.*


class WindTalentFragment : Fragment() {
companion object {
    var windRow1Item1 = 0
    var windRow2Item1 = 0
    var windRow2Item2 = 0
    var windRow3Item1 = 0
    var windRow3Item2 = 0
    var windRow4Item1 = 0

    fun windTalentsLoad() {

        if (windRow1Item1 == 1) windTalentBonusSpeed += 5.0f
        if (windRow1Item1 == 2) windTalentBonusSpeed += 10.0f
        if (windRow1Item1 == 3) windTalentBonusSpeed += 15.0f

        if (windRow2Item1 == 1) {
            windBonusDamageMultiplyer -= 0.5f
            talentMultishot = true
            windTalentDebuff += 0.1f
        }
        if (windRow2Item1 == 2) {
            windBonusDamageMultiplyer -= 0.4f
            talentMultishot = true
            windTalentDebuff += 0.2f
        }
        if (windRow2Item1 == 3) {
            windBonusDamageMultiplyer -= 0.3f
            talentMultishot = true
            windTalentDebuff += 0.3f
        }

        if (windRow2Item2 == 1) {
            globalBonusTowerRange += 5
            windTowerBonusTowerRange += 15
        }
        if (windRow2Item2 == 2) {
            globalBonusTowerRange += 10
            windTowerBonusTowerRange += 30
        }
        if (windRow2Item2 == 3) {
            globalBonusTowerRange += 15
            windTowerBonusTowerRange += 45
        }

        if (windRow3Item1 == 1) pushBack = 60.0f
        if (windRow3Item1 == 2) pushBack = 70.0f
        if (windRow3Item1 == 3) pushBack = 80.0f

        if (windRow4Item1 == 1) {
            bonusSpeedWindTalent += 0.50f
            windUltimatePercent += 0.01f
        }
        if (windRow4Item1 == 2) {
            bonusSpeedWindTalent += 1f
            windUltimatePercent += 0.02f
        }
        if (windRow4Item1 == 3) {
            bonusSpeedWindTalent += 1.50f
            windUltimatePercent += 0.03f
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
        return inflater.inflate(R.layout.fragment_wind_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        windRow1Item1ShowTV.text = windRow1Item1.toString()
        windRow2Item1ShowTV.text = windRow2Item1.toString()
        windRow2Item2ShowTV.text = windRow2Item2.toString()
        windRow3Item1ShowTV.text = windRow3Item1.toString()
        windRow3Item2ShowTV.text = windRow3Item2.toString()
        windRow4Item1ShowTV.text = windRow4Item1.toString()

        if (windRow1Item1 >= 3) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (windRow2Item1 + windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (windRow3Item1 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        windRow1Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Speedy Gonzales"
            windDisplayTalentTV.text = "Increses attack speed by 5%/10%/15%."

            windUpgradeBTN.setOnClickListener() {
                windUpgradeBTN.isClickable = true

                if (windRow1Item1 <= 2 && talentPoints > 0) {
                    windRow1Item1 += 1

                    if (windRow1Item1 == 1) windTalentBonusSpeed += 5.0f
                    if (windRow1Item1 == 2) windTalentBonusSpeed += 5.0f
                    if (windRow1Item1 == 3) windTalentBonusSpeed += 5.0f

                    if (windRow1Item1 >= 3) backgroundWindRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    windRow1Item1ShowTV.text = windRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        windRow2Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Multishot"
            windDisplayTalentTV.text = "Multishot hits all targets. Damage is divided by target count. Damage is reduced by 50/40/30%. Debuff damage increased by 10/20/30%. Does not stack with bounce, splash or BUTTERFLY single target abilities."
            windUpgradeBTN.isClickable = false

            if (com.agsolutions.td.Companion.shotBounce || com.agsolutions.td.Companion.splashRange > 0 || com.agsolutions.td.Companion.singleTargetBoost) {

            } else if (windRow1Item1 == 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (windRow2Item1 <= 2 && talentPoints > 0) {
                        windRow2Item1 += 1

                        if (windRow2Item1 == 1) {
                            windBonusDamageMultiplyer -= 0.5f
                            windTalentDebuff += 0.1f
                            talentMultishot = true
                        }
                        if (windRow2Item1 == 2) {
                            windBonusDamageMultiplyer += 0.1f
                            windTalentDebuff += 0.1f
                            talentMultishot = true
                        }
                        if (windRow2Item1 == 3) {
                            windBonusDamageMultiplyer += 0.1f
                            windTalentDebuff += 0.1f
                            talentMultishot = true
                        }

                        if (windRow2Item1 + windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            windRow2Item1ShowTV.text = windRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        windRow2Item2IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "High Ground"
            windDisplayTalentTV.text = "Increases Wind tower range by 15/30/45 units and all towers by 5/10/15."
            windUpgradeBTN.isClickable = false

            if (windRow1Item1 == 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (windRow2Item2 <= 2 && talentPoints > 0) {
                        windRow2Item2 += 1

                        if (windRow2Item2 == 1) {
                            globalBonusTowerRange += 5
                            windTowerBonusTowerRange += 15
                        }
                        if (windRow2Item2 == 2) {
                            globalBonusTowerRange += 5
                            windTowerBonusTowerRange += 15
                        }
                        if (windRow2Item2 == 3) {
                            globalBonusTowerRange += 5
                            windTowerBonusTowerRange += 15
                        }

                        if (windRow2Item1 + windRow2Item2 >= 3) backgroundWindRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow2Item2ShowTV.text = windRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Pushback"
            windDisplayTalentTV.text = "Pushes enemies back 80/120/160 units."
            windUpgradeBTN.isClickable = false

            if (windRow2Item1 + windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (windRow3Item1 <= 2 && talentPoints > 0) {
                        windRow3Item1 += 1

                        if (windRow3Item1 == 1) pushBack = 80.0f
                        if (windRow3Item1 == 2) pushBack = 120.0f
                        if (windRow3Item1 == 3) pushBack = 160.0f

                        if (windRow3Item1 + windRow3Item2 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item1ShowTV.text = windRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        windRow3Item2IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Tornado"
            windDisplayTalentTV.text = "Summons a tornado."
            windUpgradeBTN.isClickable = false

            if (windRow2Item1 + windRow2Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (windRow3Item2 <= 2 && talentPoints > 0) {
                        windRow3Item2 += 1

                        if (windRow3Item2 == 1) tornadoRadius = 60.0f
                        if (windRow3Item2 == 2) tornadoRadius = 70.0f
                        if (windRow3Item2 == 3) tornadoRadius = 80.0f

                        if (windRow3Item1 + windRow3Item2 >= 3) backgroundWindRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        windRow3Item2ShowTV.text = windRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        windRow4Item1IB.setOnClickListener() {
            windNameDisplayTalentTV.text = "Speed of Light"
            windDisplayTalentTV.text = "Increases attack speed by 0.5%/1%/1.5% each hit until there is no target in range. Max 100%) Burns 1/2/3% max HP each hit as magic damage. +1 bag space at 3/3."
            windUpgradeBTN.isClickable = false

            if (windRow3Item1 + windRow3Item2 >= 3) {
                windUpgradeBTN.isClickable = true

                windUpgradeBTN.setOnClickListener() {
                    if (windRow4Item1 <= 2 && talentPoints > 0) {
                        windRow4Item1 += 1

                        if (windRow4Item1 == 1) {
                            bonusSpeedWindTalent += 0.50f
                            windUltimatePercent += 0.01f
                        }
                        if (windRow4Item1 == 2) {
                            bonusSpeedWindTalent += 0.50f
                            windUltimatePercent += 0.01f
                        }
                        if (windRow4Item1 == 3) {
                            itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            bonusSpeedWindTalent += 0.50f
                            windUltimatePercent += 0.01f
                        }

                        windRow4Item1ShowTV.text = windRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

    }

}