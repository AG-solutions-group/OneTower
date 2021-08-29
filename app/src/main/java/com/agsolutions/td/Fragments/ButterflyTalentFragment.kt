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
import kotlinx.android.synthetic.main.fragment_butterfly_talent.*


class ButterflyTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_butterfly_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        butterflyRow1Item1ShowTV.text = towerList[towerClickID].butterflyRow1Item1.toString()
        butterflyRow1Item2ShowTV.text = towerList[towerClickID].butterflyRow1Item2.toString()
        butterflyRow2Item1ShowTV.text = towerList[towerClickID].butterflyRow2Item1.toString()
        butterflyRow2Item2ShowTV.text = towerList[towerClickID].butterflyRow2Item2.toString()
        butterflyRow3Item1ShowTV.text = towerList[towerClickID].butterflyRow3Item1.toString()
        butterflyRow3Item2ShowTV.text = towerList[towerClickID].butterflyRow3Item2.toString()
        butterflyRow4Item1ShowTV.text = towerList[towerClickID].butterflyRow4Item1.toString()

        if (towerList[towerClickID].butterflyRow1Item1 == 1 || towerList[towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].butterflyRow2Item1 + towerList[towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].butterflyRow3Item1 + towerList[towerClickID].butterflyRow3Item2 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        butterflyRow1Item1IB.setOnClickListener() {
            butterflyNameDisplayTalentTV.text = "Killer"
            butterflyDisplayTalentTV.text =
                "Increases experience gain from kills increased by 100%. Base 50% of enemy."
            butterflyUpgradeBTN.isClickable = true

                butterflyUpgradeBTN.setOnClickListener() {

                    if (towerList[towerClickID].butterflyRow1Item1 + towerList[towerClickID].butterflyRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].butterflyRow1Item1 += 1

                        if (towerList[towerClickID].butterflyRow1Item1 == 1) towerList[towerClickID].experienceKill += 0.5f

                        if (towerList[towerClickID].butterflyRow1Item1 == 1 || towerList[towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        butterflyRow1Item1ShowTV.text = towerList[towerClickID].butterflyRow1Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }

        butterflyRow1Item2IB.setOnClickListener() {
            butterflyNameDisplayTalentTV.text = "Bubble Pop"
            butterflyDisplayTalentTV.text =
                "Gain 10% of enemies experience each time Mark of the Butterfly is being triggered."
            butterflyUpgradeBTN.isClickable = true

            butterflyUpgradeBTN.setOnClickListener() {

                if (towerList[towerClickID].butterflyRow1Item1 + towerList[towerClickID].butterflyRow1Item2 < 1  && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].butterflyRow1Item2 += 1

                    if (towerList[towerClickID].butterflyRow1Item2 == 1) towerList[towerClickID].experienceButterflyPop += 0.1f


                    if (towerList[towerClickID].butterflyRow1Item1 == 1 || towerList[towerClickID].butterflyRow1Item2 == 1) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    butterflyRow1Item1ShowTV.text = towerList[towerClickID].butterflyRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

            butterflyRow2Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Sting of the Butterfly"
                butterflyDisplayTalentTV.text =
                    "Target receives 175/200/225% damage at the 3rd consecutive hit. (150% basic)"
                butterflyUpgradeBTN.isClickable = false

                if (towerList[towerClickID].butterflyRow1Item1 == 1 || towerList[towerClickID].butterflyRow1Item2 == 1) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (towerList[towerClickID].butterflyRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                            towerList[towerClickID].butterflyRow2Item1 += 1

                            if (towerList[towerClickID].butterflyRow2Item1 == 1) towerList[towerClickID].markOfTheButterfly += 0.25f
                            if (towerList[towerClickID].butterflyRow2Item1 == 2) towerList[towerClickID].markOfTheButterfly += 0.25f
                            if (towerList[towerClickID].butterflyRow2Item1 == 3) towerList[towerClickID].markOfTheButterfly += 0.25f

                            if (towerList[towerClickID].butterflyRow2Item1 + towerList[towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item1ShowTV.text = towerList[towerClickID].butterflyRow2Item1.toString()
                            towerList[towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow2Item2IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Bite of the Butterfly"
                butterflyDisplayTalentTV.text = "At mark 3/3: Attacks can stun or slow target."
                butterflyUpgradeBTN.isClickable = false

                if (towerList[towerClickID].butterflyRow1Item1 == 1 || towerList[towerClickID].butterflyRow1Item2 == 1) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (towerList[towerClickID].butterflyRow2Item2 <= 0 && towerList[towerClickID].talentPoints > 0) {
                                towerList[towerClickID].butterflyRow2Item2 += 1

                            if (towerList[towerClickID].butterflyRow2Item2 == 1) towerList[towerClickID].markOfTheButterflySlow = true

                            if (towerList[towerClickID].butterflyRow2Item1 + towerList[towerClickID].butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item2ShowTV.text = towerList[towerClickID].butterflyRow2Item2.toString()
                            towerList[towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow3Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Wing Flap"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Attackspeed increased by 0,5%/1%/1,5% for each tower in 150 range for 3 seconds. Can stack and refreshes on hit."
                butterflyUpgradeBTN.isClickable = false

                if (towerList[towerClickID].butterflyRow2Item1 + towerList[towerClickID].butterflyRow2Item2 >= 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (towerList[towerClickID].butterflyRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                            towerList[towerClickID].butterflyRow3Item1 += 1

                            if (towerList[towerClickID].butterflyRow3Item1 == 1) towerList[towerClickID].markOfTheButterflySpdBoost += 0.5f
                            if (towerList[towerClickID].butterflyRow3Item1 == 2) towerList[towerClickID].markOfTheButterflySpdBoost += 0.5f
                            if (towerList[towerClickID].butterflyRow3Item1 == 3) towerList[towerClickID].markOfTheButterflySpdBoost += 0.5f

                            if (towerList[towerClickID].butterflyRow3Item1 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow3Item1ShowTV.text = towerList[towerClickID].butterflyRow3Item1.toString()
                            towerList[towerClickID].talentPoints -= 1
                        }
                    }
                }
            }

        butterflyRow3Item2IB.setOnClickListener() {
            butterflyNameDisplayTalentTV.text = "Hive Mind"
            butterflyDisplayTalentTV.text =
                "Triggered Mark of the Butterfly debuffs the ememy, taking + 5%/10%/15% damage and giving + 5%/10%/15% gold and xp for 5 seconds. Does refresh. Does not stack."
            butterflyUpgradeBTN.isClickable = false

            if (towerList[towerClickID].butterflyRow2Item1 + towerList[towerClickID].butterflyRow2Item2 >= 3) {
                butterflyUpgradeBTN.isClickable = true

                butterflyUpgradeBTN.setOnClickListener() {

                    if (towerList[towerClickID].butterflyRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].butterflyRow3Item2 += 1

                        if (towerList[towerClickID].butterflyRow3Item2 == 1) {
                            towerList[towerClickID].butterflyDebuffEnemyDmg += 0.05f
                            towerList[towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                        }
                        if (towerList[towerClickID].butterflyRow3Item2 == 2) {
                            towerList[towerClickID].butterflyDebuffEnemyDmg += 0.05f
                            towerList[towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                        }
                        if (towerList[towerClickID].butterflyRow3Item2 == 3) {
                            towerList[towerClickID].butterflyDebuffEnemyDmg += 0.05f
                            towerList[towerClickID].butterflyDebuffEnemyGoldXp += 0.05f
                        }

                        if (towerList[towerClickID].butterflyRow3Item2 >= 3) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        butterflyRow3Item2ShowTV.text =
                            towerList[towerClickID].butterflyRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

            butterflyRow4Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Double up!"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Shoots an additional bullet for 75/100/125% damage. +1 bag space item at 3/3."
                butterflyUpgradeBTN.isClickable = false

                if (towerList[towerClickID].butterflyRow3Item1 == 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (towerList[towerClickID].butterflyRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                            towerList[towerClickID].butterflyRow4Item1 += 1

                            if (towerList[towerClickID].butterflyRow4Item1 == 1) {
                                towerList[towerClickID].markOfTheButterflyExtraShot = true
                                towerList[towerClickID].markOfTheButterflyExtraShotDmg = 0.75f
                            }
                            if (towerList[towerClickID].butterflyRow4Item1 == 2) {
                                towerList[towerClickID].markOfTheButterflyExtraShot = true
                                towerList[towerClickID].markOfTheButterflyExtraShotDmg = 1.0f
                            }
                            if (towerList[towerClickID].butterflyRow4Item1 == 3) {
                                towerList[towerClickID].markOfTheButterflyExtraShot = true
                                towerList[towerClickID].markOfTheButterflyExtraShotDmg = 1.25f
                                com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                                com.agsolutions.td.Companion.insertItem += 1
                            }

                            butterflyRow4Item1ShowTV.text = towerList[towerClickID].butterflyRow4Item1.toString()
                            towerList[towerClickID].talentPoints -= 1
                        }
                    }
                }

            }
        }

}