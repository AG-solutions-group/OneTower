package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.agsolutions.td.Companion.Companion.hpRegDebuffGlobal
import com.agsolutions.td.Companion.Companion.towerClickID
import com.agsolutions.td.Companion.Companion.towerList
import com.agsolutions.td.Companion.Companion.wizardMagicArmorSmasher
import com.agsolutions.td.Companion.Companion.wizardMine
import com.agsolutions.td.Companion.Companion.wizardMineTimer
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_wizard_talent.*


class WizardTalentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wizard_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wizardRow1Item1ShowTV.text = towerList[towerClickID].wizardRow1Item1.toString()
        wizardRow1Item2ShowTV.text = towerList[towerClickID].wizardRow1Item2.toString()
        wizardRow2Item1ShowTV.text = towerList[towerClickID].wizardRow2Item1.toString()
        wizardRow2Item2ShowTV.text = towerList[towerClickID].wizardRow2Item2.toString()
        wizardRow2Item3ShowTV.text = towerList[towerClickID].wizardRow2Item3.toString()
        wizardRow3Item1ShowTV.text = towerList[towerClickID].wizardRow3Item1.toString()
        wizardRow3Item2ShowTV.text = towerList[towerClickID].wizardRow3Item2.toString()
        wizardRow4Item1ShowTV.text = towerList[towerClickID].wizardRow4Item1.toString()

        if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (towerList[towerClickID].wizardRow3Item1 + towerList[towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        wizardRow1Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Caster"
            wizardDisplayTalentTV.text = "Gain experience for each tower cast (5% of enemy)."

            wizardUpgradeBTN.setOnClickListener() {
                wizardUpgradeBTN.isClickable = true

                if (towerList[towerClickID].wizardRow1Item1 + towerList[towerClickID].wizardRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].wizardRow1Item1 += 1

                    if (towerList[towerClickID].wizardRow1Item1 == 1) towerList[towerClickID].experienceCast = true

                    if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    wizardRow1Item1ShowTV.text = towerList[towerClickID].wizardRow1Item1.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        wizardRow1Item2IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Overflowing Magic"
            wizardDisplayTalentTV.text = "Improve tower spell damage by tower level (3% per tower level)."

            wizardUpgradeBTN.setOnClickListener() {
                wizardUpgradeBTN.isClickable = true

                if (towerList[towerClickID].wizardRow1Item1 + towerList[towerClickID].wizardRow1Item2 < 1 && towerList[towerClickID].talentPoints > 0) {
                    towerList[towerClickID].wizardRow1Item2 += 1

                    if (towerList[towerClickID].wizardRow1Item2 == 1) towerList[towerClickID].talentWizardLvlToDmg = true

                    if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    wizardRow1Item2ShowTV.text = towerList[towerClickID].wizardRow1Item2.toString()
                    towerList[towerClickID].talentPoints -= 1
                }
            }
        }

        wizardRow2Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Lightning Bolt"
            wizardDisplayTalentTV.text = "Throws a lightning bolt on 2 enemies for each missed tower attack for 150/200/250% spelldamage."
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow2Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow2Item1 += 1

                        if (towerList[towerClickID].wizardRow2Item1 == 1) {
                            towerList[towerClickID].wizardMissedLightning = true
                            towerList[towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (towerList[towerClickID].wizardRow2Item1 == 2) {
                            towerList[towerClickID].wizardMissedLightning = true
                            towerList[towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (towerList[towerClickID].wizardRow2Item1 == 3) {
                            towerList[towerClickID].wizardMissedLightning = true
                            towerList[towerClickID].wizardMissedLightningDmgBoost += 0.5f
                        }

                        if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item1ShowTV.text = towerList[towerClickID].wizardRow2Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow2Item2IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Dispel"
            wizardDisplayTalentTV.text = "Decreases magic armor of enemies with type magic armor by 3%/6%/9% & reduces hitpoint regeneration by 3%/6%/9%%. Global."
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow2Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow2Item2 += 1


                        if (towerList[towerClickID].wizardRow2Item2 == 1) {
                            hpRegDebuffGlobal += 0.03f
                            wizardMagicArmorSmasher -= 0.03f
                        }
                        if (towerList[towerClickID].wizardRow2Item2 == 2) {
                            hpRegDebuffGlobal += 0.03f
                            wizardMagicArmorSmasher -= 0.03f
                        }
                        if (towerList[towerClickID].wizardRow2Item2 == 3) {
                            hpRegDebuffGlobal += 0.03f
                            wizardMagicArmorSmasher -= 0.03f
                        }

                        if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item3 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item2ShowTV.text = towerList[towerClickID].wizardRow2Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow2Item3IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Bombshell"
            wizardDisplayTalentTV.text = "Reduces the cooldown of bomb ability by 1/2/3 seconds and increases damage by 5/10/15 (+10% magic damage)."
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow1Item1 == 1 || towerList[towerClickID].wizardRow1Item2 == 1) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow2Item3 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow2Item3 += 1

                        if (towerList[towerClickID].wizardRow2Item3 == 1) {
                            towerList[towerClickID].wizardBombTimer -= 60
                            towerList[towerClickID].wizardBombDmg += 5f
                        }
                        if (towerList[towerClickID].wizardRow2Item3 == 2) {
                            towerList[towerClickID].wizardBombTimer -= 60
                            towerList[towerClickID].wizardBombDmg += 5f
                        }
                        if (towerList[towerClickID].wizardRow2Item3 == 3) {
                            towerList[towerClickID].wizardBombTimer -= 60
                            towerList[towerClickID].wizardBombDmg += 5f
                        }

                        if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item3>= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item3ShowTV.text = towerList[towerClickID].wizardRow2Item3.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Cool Down!"
            wizardDisplayTalentTV.text = "Each spellcast decreases cooldown of all spellcasts by 3/6/9%."
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item3 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow3Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow3Item1 += 1

                        if (towerList[towerClickID].wizardRow3Item1 == 1) towerList[towerClickID].spellCastCD += 0.03f
                        if (towerList[towerClickID].wizardRow3Item1 == 2) towerList[towerClickID].spellCastCD += 0.03f
                        if (towerList[towerClickID].wizardRow3Item1 == 3) towerList[towerClickID].spellCastCD += 0.03f


                        if (towerList[towerClickID].wizardRow3Item1 + towerList[towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item1ShowTV.text = towerList[towerClickID].wizardRow3Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item2IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Mine Crafter"
            wizardDisplayTalentTV.text = "Plants a mine at a random location. CD reduced per level. Mines deals a small amount of damage and slow enemies by 50% for 1.5 seconds-"
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow2Item1 + towerList[towerClickID].wizardRow2Item2 + towerList[towerClickID].wizardRow2Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow3Item2 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow3Item2 += 1

                        if (towerList[towerClickID].wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer -= 20f
                        }
                        if (towerList[towerClickID].wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer -= 20f
                        }
                        if (towerList[towerClickID].wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer -= 20f
                        }

                        if (towerList[towerClickID].wizardRow3Item1 + towerList[towerClickID].wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item2ShowTV.text = towerList[towerClickID].wizardRow3Item2.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }

        wizardRow4Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Chain Lightning"
            wizardDisplayTalentTV.text = "Casts a chain lightning that jumps to 2/4/6 targets. Increases danage for each enemy killed by chain lightning. +1 bag space item at 3/3." +
                    "Spell CD reduced for each tower with wizard element."
            wizardUpgradeBTN.isClickable = false

            if (towerList[towerClickID].wizardRow3Item1 + towerList[towerClickID].wizardRow3Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (towerList[towerClickID].wizardRow4Item1 <= 2 && towerList[towerClickID].talentPoints > 0) {
                        towerList[towerClickID].wizardRow4Item1 += 1

                        if (towerList[towerClickID].wizardRow4Item1 == 1) {
                            towerList[towerClickID].chainLighning = true
                            towerList[towerClickID].chainLightningBounceTargets += 2
                            towerList[towerClickID].chainLightningDmg = 0.9f
                            towerList[towerClickID].chainLightningTimer = 240f
                        }
                        if (towerList[towerClickID].wizardRow4Item1 == 2) {
                            towerList[towerClickID].chainLighning = true
                            towerList[towerClickID].chainLightningBounceTargets += 2
                            towerList[towerClickID].chainLightningDmg = 1.0f
                            towerList[towerClickID].chainLightningTimer = 210f
                        }
                        if (towerList[towerClickID].wizardRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            towerList[towerClickID].chainLighning = true
                            towerList[towerClickID].chainLightningBounceTargets += 2
                            towerList[towerClickID].chainLightningDmg = 1.1f
                            towerList[towerClickID].chainLightningTimer = 180f
                        }

                        wizardRow4Item1ShowTV.text = towerList[towerClickID].wizardRow4Item1.toString()
                        towerList[towerClickID].talentPoints -= 1
                    }
                }
            }
        }
    }
}