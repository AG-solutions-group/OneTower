package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion
import com.agsolutions.td.Companion.Companion.darkMagicDmgPercent
import com.agsolutions.td.Companion.Companion.darkPermaKill
import com.agsolutions.td.Companion.Companion.darkTalentFear
import com.agsolutions.td.Companion.Companion.darkTalentLaser
import com.agsolutions.td.Companion.Companion.dmgImmune
import com.agsolutions.td.Companion.Companion.fearDuration
import com.agsolutions.td.Companion.Companion.fearTimer
import com.agsolutions.td.Companion.Companion.instaKill
import com.agsolutions.td.Companion.Companion.slowExtraChanceDark
import com.agsolutions.td.Companion.Companion.slowExtraDark
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_dark_talent.*


class DarkTalentFragment : Fragment() {
companion object {
    var darkRow1Item1 = 0
    var darkRow2Item1 = 0
    var darkRow2Item2 = 0
    var darkRow3Item1 = 0
    var darkRow3Item2 = 0
    var darkRow4Item1 = 0

    fun darkTalentsLoad() {

        if (darkRow1Item1 == 1) {
            instaKill += 0.5f
            dmgImmune += 0.01f
        }
        if (darkRow1Item1 == 2) {
            instaKill += 1f
            dmgImmune += 0.02f
        }
        if (darkRow1Item1 == 3) {
            instaKill += 1.5f
            dmgImmune += 0.03f
        }

        if (darkRow2Item1 == 1) {
            darkTalentFear = true
            fearDuration += 1.0f
            fearTimer = 150
            dmgImmune += 0.01f
        }
        if (darkRow2Item1 == 2) {
            darkTalentFear = true
            fearDuration += 1.5f
            fearTimer = 120
            dmgImmune += 0.02f
        }
        if (darkRow2Item1 == 3) {
            darkTalentFear = true
            fearDuration += 2.0f
            fearTimer = 90
            dmgImmune += 0.03f
        }

        if (darkRow2Item2 == 1) {
            slowExtraDark = 20.0f
            slowExtraChanceDark = 10
            dmgImmune += 0.01f
        }
        if (darkRow2Item2 == 2) {
            slowExtraDark = 25.0f
            slowExtraChanceDark = 15
            dmgImmune += 0.02f
        }
        if (darkRow2Item2 == 3) {
            slowExtraDark = 30.0f
            slowExtraChanceDark = 20
            dmgImmune += 0.03f
        }

        if (darkRow3Item1 == 1) {
            darkMagicDmgPercent = 0.1f
            dmgImmune += 0.01f
        }
        if (darkRow3Item1 == 2) {
            darkMagicDmgPercent = 0.15f
            dmgImmune += 0.02f
        }
        if (darkRow3Item1 == 3) {
            darkMagicDmgPercent = 0.2f
            dmgImmune += 0.03f
        }

        if (darkRow3Item2 == 1) {
            darkTalentLaser += 0.1f
            dmgImmune += 0.01f
        }
        if (darkRow3Item2 == 2) {
            darkTalentLaser += 0.2f
            dmgImmune += 0.02f
        }
        if (darkRow3Item2 == 3) {
            darkTalentLaser += 0.3f
            dmgImmune += 0.03f
        }

        if (darkRow4Item1 == 1) darkPermaKill += 0.25f
        if (darkRow4Item1 == 2) darkPermaKill += 0.5f
        if (darkRow4Item1 == 3) darkPermaKill += 0.75f

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
        return inflater.inflate(R.layout.fragment_dark_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        darkRow1Item1ShowTV.text = darkRow1Item1.toString()
        darkRow2Item1ShowTV.text = darkRow2Item1.toString()
        darkRow2Item2ShowTV.text = darkRow2Item2.toString()
        darkRow3Item1ShowTV.text = darkRow3Item1.toString()
        darkRow3Item2ShowTV.text = darkRow3Item2.toString()
        darkRow4Item1ShowTV.text = darkRow4Item1.toString()

        if (darkRow1Item1 >= 3) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (darkRow2Item1 + darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (darkRow3Item1 + darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        darkRow1Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Reaper"
            darkDisplayTalentTV.text = "Each hit has a 0.5%/1%/1.5% chance to instantly kill the target. Chance doubled for single target. +200% bonus XP. Does not work on bosses. Immunity after 10 hits. +1%/2%/3% anti-immune. "

            darkUpgradeBTN.setOnClickListener() {
                darkUpgradeBTN.isClickable = true

                if (darkRow1Item1 <= 2 && talentPoints > 0) {
                    darkRow1Item1 += 1

                    if (darkRow1Item1 == 1) {
                        instaKill += 0.5f
                        dmgImmune += 0.01f
                    }
                    if (darkRow1Item1 == 2) {
                        instaKill += 0.5f
                        dmgImmune += 0.01f
                    }
                    if (darkRow1Item1 == 3) {
                        instaKill += 0.5f
                        dmgImmune += 0.01f
                    }

                    if (darkRow1Item1 >= 3) backgroundDarkRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    darkRow1Item1ShowTV.text = darkRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        darkRow2Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Fear"
            darkDisplayTalentTV.text = "Casts a spell that sometimes causes enemies to run away in fear. Points decrease spell cooldown and increase fear duration. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (darkRow1Item1 == 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (darkRow2Item1 <= 2 && talentPoints > 0) {
                        darkRow2Item1 += 1

                        if (darkRow2Item1 == 1) {
                            darkTalentFear = true
                            fearDuration += 1.0f
                            fearTimer = 150
                            dmgImmune += 0.01f
                        }
                        if (darkRow2Item1 == 2) {
                            darkTalentFear = true
                            fearDuration += 0.5f
                            fearTimer = 120
                            dmgImmune += 0.01f
                        }
                        if (darkRow2Item1 == 3) {
                            darkTalentFear = true
                            fearDuration += 0.5f
                            fearTimer = 90
                            dmgImmune += 0.01f
                        }

                        if (darkRow2Item1 + darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item1ShowTV.text = darkRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        darkRow2Item2IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Cold Sweat"
            darkDisplayTalentTV.text = "10/15/20% Chance on hit to slow target by 20%/25%/30% permanently. Works against immune. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (darkRow1Item1 == 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (darkRow2Item2 <= 2 && talentPoints > 0) {
                        darkRow2Item2 += 1

                        if (darkRow2Item2 == 1) {
                            slowExtraDark = 20.0f
                            slowExtraChanceDark = 10
                            dmgImmune += 0.01f
                        }
                        if (darkRow2Item2 == 2) {
                            slowExtraDark = 25.0f
                            slowExtraChanceDark = 15
                            dmgImmune += 0.01f
                        }
                        if (darkRow2Item2 == 3) {
                            slowExtraDark = 30.0f
                            slowExtraChanceDark = 20
                            dmgImmune += 0.01f
                        }

                        if (darkRow2Item1 + darkRow2Item2 >= 3) backgroundDarkRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow2Item2ShowTV.text = darkRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Soul Crusher"
            darkDisplayTalentTV.text = "Adds 10%/15%/20% magic damage each hit. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (darkRow2Item1 + darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (darkRow3Item1 <= 2 && talentPoints > 0) {
                        darkRow3Item1 += 1

                        if (darkRow3Item1 == 1) {
                            darkMagicDmgPercent = 0.1f
                            dmgImmune += 0.01f
                        }
                        if (darkRow3Item1 == 2) {
                            darkMagicDmgPercent = 0.15f
                            dmgImmune += 0.01f
                        }
                        if (darkRow3Item1 == 3) {
                            darkMagicDmgPercent = 0.2f
                            dmgImmune += 0.01f
                        }

                        if (darkRow3Item1 + darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item1ShowTV.text = darkRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        darkRow3Item2IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Dark Hand"
            darkDisplayTalentTV.text = "Grabs a random unit and reduces maximum hitpoints by 6%/12%/18% per second. +1%/2%/3% anti-immune."
            darkUpgradeBTN.isClickable = false

            if (darkRow2Item1 + darkRow2Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (darkRow3Item2 <= 2 && talentPoints > 0) {
                        darkRow3Item2 += 1

                        if (darkRow3Item2 == 1) {
                            darkTalentLaser += 0.1f
                            dmgImmune += 0.01f
                        }
                        if (darkRow3Item2 == 2) {
                            darkTalentLaser += 0.1f
                            dmgImmune += 0.01f
                        }
                        if (darkRow3Item2 == 3) {
                            darkTalentLaser += 0.1f
                            dmgImmune += 0.01f
                        }

                        if (darkRow3Item1 + darkRow3Item2 >= 3) backgroundDarkRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        darkRow3Item2ShowTV.text = darkRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        darkRow4Item1IB.setOnClickListener() {
            darkNameDisplayTalentTV.text = "Soul Collector"
            darkDisplayTalentTV.text = "Receive an item that collects souls adding permanent physical damage for each kill. +1 bag space at 3/3."
            darkUpgradeBTN.isClickable = false

            if (darkRow3Item1 + darkRow3Item2 >= 3) {
                darkUpgradeBTN.isClickable = true

                darkUpgradeBTN.setOnClickListener() {
                    if (darkRow4Item1 <= 2 && talentPoints > 0) {
                        darkRow4Item1 += 1


                        if (darkRow4Item1 == 1){
                            com.agsolutions.td.Companion.dropItemDarkUltimate += 1
                            darkPermaKill += 0.25f
                        }
                        if (darkRow4Item1 == 2) darkPermaKill += 0.25f
                        if (darkRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            darkPermaKill += 0.25f
                        }

                        darkRow4Item1ShowTV.text = darkRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }
    }
}