package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.markOfTheButterfly
import com.agsolutions.td.Companion.Companion.markOfTheButterflyExtraShot
import com.agsolutions.td.Companion.Companion.markOfTheButterflyExtraShotDmg
import com.agsolutions.td.Companion.Companion.markOfTheButterflySlow
import com.agsolutions.td.Companion.Companion.markOfTheButterflySpdBoost
import com.agsolutions.td.Companion.Companion.shotBounce
import com.agsolutions.td.Companion.Companion.singleTargetBoost
import com.agsolutions.td.Companion.Companion.singleTargetMultiplyer
import com.agsolutions.td.Companion.Companion.splashRange
import com.agsolutions.td.Companion.Companion.talentMultishot
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_butterfly_talent.*


class ButterflyTalentFragment : Fragment() {
    companion object {
        var butterflyRow1Item1 = 0
        var butterflyRow2Item1 = 0
        var butterflyRow2Item2 = 0
        var butterflyRow3Item1 = 0
        var butterflyRow4Item1 = 0

        fun butterflyTalentsLoad() {

            if (butterflyRow1Item1 == 1) {
                singleTargetBoost = true
                singleTargetMultiplyer += 0.5f
            }
            if (butterflyRow1Item1 == 2) {
                singleTargetBoost = true
                singleTargetMultiplyer += 1f
            }
            if (butterflyRow1Item1 == 3) {
                singleTargetBoost = true
                singleTargetMultiplyer += 1.5f
            }

            if (butterflyRow2Item1 == 1) markOfTheButterfly += 0.5f
            if (butterflyRow2Item1 == 2) markOfTheButterfly += 1f
            if (butterflyRow2Item1 == 3) markOfTheButterfly += 1.5f


            if (butterflyRow2Item2 == 1) markOfTheButterflySlow = true

            if (butterflyRow3Item1 == 1) markOfTheButterflySpdBoost += 3f
            if (butterflyRow3Item1 == 2) markOfTheButterflySpdBoost += 6f
            if (butterflyRow3Item1 == 3) markOfTheButterflySpdBoost += 9f

            if (butterflyRow4Item1 == 1) {
                markOfTheButterflyExtraShot = true
                markOfTheButterflyExtraShotDmg = 0.75f
            }
            if (butterflyRow4Item1 == 2) {
                markOfTheButterflyExtraShot = true
                markOfTheButterflyExtraShotDmg = 1.0f
            }
            if (butterflyRow4Item1 == 3) {
                markOfTheButterflyExtraShot = true
                markOfTheButterflyExtraShotDmg = 1.25f
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
        return inflater.inflate(R.layout.fragment_butterfly_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        butterflyRow1Item1ShowTV.text = butterflyRow1Item1.toString()
        butterflyRow2Item1ShowTV.text = butterflyRow2Item1.toString()
        butterflyRow2Item2ShowTV.text = butterflyRow2Item2.toString()
        butterflyRow3Item1ShowTV.text = butterflyRow3Item1.toString()
        butterflyRow4Item1ShowTV.text = butterflyRow4Item1.toString()

        if (butterflyRow1Item1 >= 3) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (butterflyRow2Item1 + butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (butterflyRow3Item1 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        butterflyRow1Item1IB.setOnClickListener() {
            butterflyNameDisplayTalentTV.text = "Self-Partnered"
            butterflyDisplayTalentTV.text =
                "Increases tower damage against single targets by 50/100/150%. Does not stack with multishot, bounce or splash abilities."

            if (talentMultishot || shotBounce || splashRange > 1) {

            } else {
                butterflyUpgradeBTN.isClickable = true

                butterflyUpgradeBTN.setOnClickListener() {

                    if (butterflyRow1Item1 <= 2 && talentPoints > 0) {
                        butterflyRow1Item1 += 1

                        if (butterflyRow1Item1 == 1) {
                            singleTargetBoost = true
                            singleTargetMultiplyer += 0.5f
                        }
                        if (butterflyRow1Item1 == 2) {
                            singleTargetBoost = true
                            singleTargetMultiplyer += 0.5f
                        }
                        if (butterflyRow1Item1 == 3) {
                            singleTargetBoost = true
                            singleTargetMultiplyer += 0.5f
                        }

                        if (butterflyRow1Item1 >= 3) backgroundButterflyRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                        butterflyRow1Item1ShowTV.text = butterflyRow1Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

            butterflyRow2Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Mark of the Butterfly"
                butterflyDisplayTalentTV.text =
                    "Marks a target. Target receives 150/200/250% damage at the 3rd consecutive hit."
                butterflyUpgradeBTN.isClickable = false

                if (butterflyRow1Item1 == 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (butterflyRow2Item1 <= 2 && talentPoints > 0) {
                            butterflyRow2Item1 += 1

                            if (butterflyRow2Item1 == 1) markOfTheButterfly += 0.5f
                            if (butterflyRow2Item1 == 2) markOfTheButterfly += 0.5f
                            if (butterflyRow2Item1 == 3) markOfTheButterfly += 0.5f

                            if (butterflyRow2Item1 + butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item1ShowTV.text = butterflyRow2Item1.toString()
                            talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow2Item2IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Bite of the Butterfly"
                butterflyDisplayTalentTV.text = "At mark 3/3: Attacks can stun or slow target."
                butterflyUpgradeBTN.isClickable = false

                if (butterflyRow1Item1 == 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (butterflyRow2Item2 <= 0 && talentPoints > 0) {
                            butterflyRow2Item2 += 1

                            if (butterflyRow2Item2 == 1) markOfTheButterflySlow = true

                            if (butterflyRow2Item1 + butterflyRow2Item2 >= 3) backgroundButterflyRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow2Item2ShowTV.text = butterflyRow2Item2.toString()
                            talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow3Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Wing Flap"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Attackspeed increased by 3/6/9% for 3 seconds. Can stack and refreshes on hit."
                butterflyUpgradeBTN.isClickable = false

                if (butterflyRow2Item1 + butterflyRow2Item2 >= 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (butterflyRow3Item1 <= 2 && talentPoints > 0) {
                            butterflyRow3Item1 += 1

                            if (butterflyRow3Item1 == 1) markOfTheButterflySpdBoost += 3f
                            if (butterflyRow3Item1 == 2) markOfTheButterflySpdBoost += 3f
                            if (butterflyRow3Item1 == 3) markOfTheButterflySpdBoost += 3f

                            if (butterflyRow3Item1 >= 3) backgroundButterflyRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                            butterflyRow3Item1ShowTV.text = butterflyRow3Item1.toString()
                            talentPoints -= 1
                        }
                    }
                }
            }

            butterflyRow4Item1IB.setOnClickListener() {
                butterflyNameDisplayTalentTV.text = "Double up!"
                butterflyDisplayTalentTV.text =
                    "At mark 3/3: Shoots an additional bullet for 75/100/125% damage. Increases damage against single targets by 20% each 50 levels.+1 bag space at 3/3."
                butterflyUpgradeBTN.isClickable = false

                if (butterflyRow3Item1 == 3) {
                    butterflyUpgradeBTN.isClickable = true

                    butterflyUpgradeBTN.setOnClickListener() {
                        if (butterflyRow4Item1 <= 2 && talentPoints > 0) {
                            butterflyRow4Item1 += 1

                            if (butterflyRow4Item1 == 1) {
                                markOfTheButterflyExtraShot = true
                                markOfTheButterflyExtraShotDmg = 0.75f
                            }
                            if (butterflyRow4Item1 == 2) {
                                markOfTheButterflyExtraShot = true
                                markOfTheButterflyExtraShotDmg = 1.0f
                            }
                            if (butterflyRow4Item1 == 3) {
                                markOfTheButterflyExtraShot = true
                                markOfTheButterflyExtraShotDmg = 1.25f
                                com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                                com.agsolutions.td.Companion.insertItem += 1
                            }

                            butterflyRow4Item1ShowTV.text = butterflyRow4Item1.toString()
                            talentPoints -= 1
                        }
                    }
                }

            }
        }

}