package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.interest
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_poison_talent.*
import kotlinx.android.synthetic.main.fragment_utils_talent.*


class UtilsTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_utils_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        utilsRow1Item1ShowTV.text = towerList[towerClickID].utilsRow1Item1.toString()
        utilsRow1Item2ShowTV.text = towerList[towerClickID].utilsRow1Item2.toString()
        utilsRow2Item1ShowTV.text = towerList[towerClickID].utilsRow2Item1.toString()
        utilsRow2Item2ShowTV.text = towerList[towerClickID].utilsRow2Item2.toString()
        utilsRow3Item1ShowTV.text = towerList[towerClickID].utilsRow3Item1.toString()
        utilsRow3Item2ShowTV.text = towerList[towerClickID].utilsRow3Item2.toString()
        utilsRow4Item1ShowTV.text = towerList[towerClickID].utilsRow4Item1.toString()

        if (towerList[towerClickID].utilsRow1Item1 == 1 || towerList[towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].utilsRow2Item1 + towerList[towerClickID].utilsRow2Item2 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].utilsRow3Item1 + towerList[towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        if (towerList[towerClickID].poisonRow1Item1 == 1 || towerList[towerClickID].poisonRow1Item2 == 1) backgroundPoisonRow2.setBackgroundResource(R.drawable.backgroundplankslight)

        utilsRow1Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Marriage"
            utilsDisplayTalentTV.text = "Gain a small amount of experience each time a tower in 15ß range gains experience."
            utilsUpgradeBTN.isClickable = true

            utilsUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].utilsRow1Item1 + towerList[towerClickID].utilsRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].utilsRow1Item1 += 1

                    if (towerList[towerClickID].utilsRow1Item1 == 1) towerList[towerClickID].experienceGainUtilsAura = true

                    if (towerList[towerClickID].utilsRow1Item1 == 1 || towerList[towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    utilsRow1Item1ShowTV.text = towerList[towerClickID].utilsRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        utilsRow1Item2IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Divorce"
            utilsDisplayTalentTV.text = "Each time this tower gains experience it shares 10% to all towers in 15ß range."
            utilsUpgradeBTN.isClickable = true

            utilsUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].utilsRow1Item1 + towerList[towerClickID].utilsRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].utilsRow1Item2 += 1

                    if (towerList[towerClickID].utilsRow1Item2 == 1) towerList[towerClickID].experienceShareUtilsAura = true

                    if (towerList[towerClickID].utilsRow1Item1 == 1 || towerList[towerClickID].utilsRow1Item2 == 1) backgroundUtilsRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    utilsRow1Item2ShowTV.text = towerList[towerClickID].utilsRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        utilsRow2Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = ""
            utilsDisplayTalentTV.text = ""
            utilsUpgradeBTN.isClickable = false

            if (towerList[towerClickID].utilsRow1Item1 == 1 || towerList[towerClickID].utilsRow1Item2 == 1) {
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].utilsRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].utilsRow2Item1 += 1

                                if (towerList[towerClickID].utilsRow2Item1 == 1)
                                if (towerList[towerClickID].utilsRow2Item1 == 2)
                                if (towerList[towerClickID].utilsRow2Item1 == 3)

                                    if (towerList[towerClickID].utilsRow2Item1 + towerList[towerClickID].utilsRow2Item2 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow2Item1ShowTV.text =
                            towerList[towerClickID].utilsRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        utilsRow2Item2IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = ""
            utilsDisplayTalentTV.text = ""
            utilsUpgradeBTN.isClickable = false

            if (towerList[towerClickID].utilsRow1Item1 == 1 || towerList[towerClickID].utilsRow1Item2 == 1) {
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].utilsRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].utilsRow2Item2 += 1

                        if (towerList[towerClickID].utilsRow2Item2 == 1)
                            if (towerList[towerClickID].utilsRow2Item2 == 2)
                                if (towerList[towerClickID].utilsRow2Item2 == 3)

                                    if (towerList[towerClickID].utilsRow2Item1 + towerList[towerClickID].utilsRow2Item2 >= 3) backgroundUtilsRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow2Item2ShowTV.text = towerList[towerClickID].utilsRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        utilsRow3Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Dowsing Stick"
            utilsDisplayTalentTV.text = "Increases item chance of nearby towers by 20%/35%/50%"
            utilsUpgradeBTN.isClickable = false

            if (towerList[towerClickID].utilsRow2Item1 + towerList[towerClickID].utilsRow2Item2 >= 3) {
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].utilsRow3Item1 <= 1 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].utilsRow3Item1 += 1

                        if (towerList[towerClickID].utilsRow3Item1 == 1) towerList[towerClickID].itemChanceAura += 0.2f
                        if (towerList[towerClickID].utilsRow3Item1 == 2) towerList[towerClickID].itemChanceAura += 0.35f
                        if (towerList[towerClickID].utilsRow3Item1 == 3) towerList[towerClickID].itemChanceAura += 0.5f

                        if (towerList[towerClickID].utilsRow3Item1 + towerList[towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow3Item1ShowTV.text =
                            towerList[towerClickID].utilsRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        utilsRow3Item2IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Made in Germany"
            utilsDisplayTalentTV.text = "Increases item quality of nearby towers by 2%/4%/6%"
            utilsUpgradeBTN.isClickable = false

            if (towerList[towerClickID].utilsRow2Item1 + towerList[towerClickID].utilsRow2Item2 >= 3) {
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].utilsRow3Item2 <= 1 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].utilsRow3Item2 += 1

                        if (towerList[towerClickID].utilsRow3Item2 == 1) towerList[towerClickID].itemQualityAura += 2f
                        if (towerList[towerClickID].utilsRow3Item2 == 1) towerList[towerClickID].itemQualityAura += 2f
                        if (towerList[towerClickID].utilsRow3Item2 == 1) towerList[towerClickID].itemQualityAura += 2f

                        if (towerList[towerClickID].utilsRow3Item1 + towerList[towerClickID].utilsRow3Item2 >= 3) backgroundUtilsRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        utilsRow3Item2ShowTV.text =
                            towerList[towerClickID].utilsRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        utilsRow4Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Banker"
            utilsDisplayTalentTV.text =
                "Increases global interest rate on gold by 1%/2%/3%. +1 bag space item at 3/3."
            utilsUpgradeBTN.isClickable = false

            if (towerList[towerClickID].utilsRow3Item1 + towerList[towerClickID].utilsRow3Item2 >= 3) {
                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].utilsRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].utilsRow4Item1 += 1

                        if (towerList[towerClickID].utilsRow4Item1 == 1) interest += 0.01f
                        if (towerList[towerClickID].utilsRow4Item1 == 2) interest += 0.01f
                        if (towerList[towerClickID].utilsRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            interest += 0.01f
                        }

                        utilsRow4Item1ShowTV.text =
                            towerList[towerClickID].utilsRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }
    }
}