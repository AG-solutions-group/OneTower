package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.additionalDia
import com.agsolutions.td.Companion.Companion.additionalUpgrade
import com.agsolutions.td.Companion.Companion.globalBonusItemChance
import com.agsolutions.td.Companion.Companion.globalBonusItemQuality
import com.agsolutions.td.Companion.Companion.interest
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_utils_talent.*


class UtilsTalentFragment : Fragment() {
companion object {
    var utilsRow1Item1 = 0
    var utilsRow1Item2 = 0
    var utilsRow2Item1 = 0
    var utilsRow2Item2 = 0
    var utilsRow3Item1 = 0

    fun utilsTalentsLoad() {

        if (utilsRow1Item1 == 1) globalBonusItemChance += 20f
        if (utilsRow1Item1 == 2) globalBonusItemChance += 50f

        if (utilsRow1Item2 == 1) globalBonusItemQuality += 2f
        if (utilsRow1Item2 == 1) globalBonusItemQuality += 5f

        if (utilsRow2Item1 == 1) additionalUpgrade = 15
        if (utilsRow2Item1 == 2) additionalUpgrade = 10
        if (utilsRow2Item1 == 3) additionalUpgrade = 5

        if (utilsRow2Item2 == 1) additionalDia = 30
        if (utilsRow2Item2 == 2) additionalDia = 25
        if (utilsRow2Item2 == 3) additionalDia = 20

        if (utilsRow3Item1 == 1) interest += 0.01f
        if (utilsRow3Item1 == 2) interest += 0.02f
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
        return inflater.inflate(R.layout.fragment_utils_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        utilsRow1Item1ShowTV.text = utilsRow1Item1.toString()
        utilsRow1Item2ShowTV.text = utilsRow1Item2.toString()
        utilsRow2Item1ShowTV.text = utilsRow2Item1.toString()
        utilsRow2Item2ShowTV.text = utilsRow2Item2.toString()
        utilsRow3Item1ShowTV.text = utilsRow3Item1.toString()


        utilsRow1Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Clover"
            utilsDisplayTalentTV.text = "Increases item chance by 20/50%"
            utilsUpgradeBTN.isClickable = true

            utilsUpgradeBTN.setOnClickListener() {


                if (utilsRow1Item1 <= 1 && talentPoints > 0) {
                    utilsRow1Item1 += 1

                    if (utilsRow1Item1 == 1) globalBonusItemChance += 20f
                    if (utilsRow1Item1 == 2) globalBonusItemChance += 30f


                    utilsRow1Item1ShowTV.text = utilsRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        utilsRow1Item2IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Collector"
            utilsDisplayTalentTV.text = "Increases item quality by 2/5."
            utilsUpgradeBTN.isClickable = true

            utilsUpgradeBTN.setOnClickListener() {


                if (utilsRow1Item2 <= 1 && talentPoints > 0) {
                    utilsRow1Item2 += 1

                    if (utilsRow1Item2 == 1) globalBonusItemQuality += 2f
                    if (utilsRow1Item2 == 1) globalBonusItemQuality += 3f


                    utilsRow1Item2ShowTV.text = utilsRow1Item2.toString()
                    talentPoints -= 1
                }
            }
        }

        utilsRow2Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Enhancer"
            utilsDisplayTalentTV.text = "Plus 1 upgrade point each 15/10/5 levels"

                utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (utilsRow2Item1 <= 2 && talentPoints > 0) {
                        utilsRow2Item1 += 1

                        if (utilsRow2Item1 == 1) additionalUpgrade = 15
                        if (utilsRow2Item1 == 2) additionalUpgrade = 10
                        if (utilsRow2Item1 == 3) additionalUpgrade = 5

                        utilsRow2Item1ShowTV.text = utilsRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }

        utilsRow2Item2IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Miner"
            utilsDisplayTalentTV.text = "Plus 1 diamond each 30/25/20 levels"
            utilsUpgradeBTN.isClickable = true

                utilsUpgradeBTN.setOnClickListener() {
                    if (utilsRow2Item2 <= 2 && talentPoints > 0) {
                        utilsRow2Item2 += 1

                        if (utilsRow2Item2 == 1) additionalDia = 30
                        if (utilsRow2Item2 == 2) additionalDia = 25
                        if (utilsRow2Item2 == 3) additionalDia = 20

                        utilsRow2Item2ShowTV.text = utilsRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }

        utilsRow3Item1IB.setOnClickListener() {
            utilsNameDisplayTalentTV.text = "Banker"
            utilsDisplayTalentTV.text = "Increases interest rate on gold by 1/2%."

            utilsUpgradeBTN.isClickable = true

            utilsUpgradeBTN.setOnClickListener() {
                if (utilsRow3Item1 <= 1 && talentPoints > 0) {
                    utilsRow3Item1 += 1

                    if (utilsRow3Item1 == 1) interest += 0.01f
                    if (utilsRow3Item1 == 2) interest += 0.01f

                    utilsRow3Item1ShowTV.text = utilsRow3Item1.toString()
                    talentPoints -= 1
                }
            }
        }



    }

}