package com.agsolutions.td.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agsolutions.td.Companion
import com.agsolutions.td.Companion.Companion.chainLighning
import com.agsolutions.td.Companion.Companion.chainLightningBounceTargets
import com.agsolutions.td.Companion.Companion.chainLightningDmg
import com.agsolutions.td.Companion.Companion.evadeGlobal
import com.agsolutions.td.Companion.Companion.hpRegDebuffGlobal
import com.agsolutions.td.Companion.Companion.spellCastCD
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Companion.Companion.wizardBomb
import com.agsolutions.td.Companion.Companion.wizardBombDmg
import com.agsolutions.td.Companion.Companion.wizardBombTimer
import com.agsolutions.td.Companion.Companion.wizardMagicArmorSmasher
import com.agsolutions.td.Companion.Companion.wizardMine
import com.agsolutions.td.Companion.Companion.wizardMineTimer
import com.agsolutions.td.Companion.Companion.wizardMissedLightning
import com.agsolutions.td.Companion.Companion.wizardMissedLightningDmgBoost
import com.agsolutions.td.Items
import com.agsolutions.td.R
import kotlinx.android.synthetic.main.fragment_wizard_talent.*


class WizardTalentFragment : Fragment() {
companion object {
    var wizardRow1Item1 = 0
    var wizardRow2Item1 = 0
    var wizardRow2Item2 = 0
    var wizardRow3Item1 = 0
    var wizardRow3Item2 = 0
    var wizardRow4Item1 = 0

    fun wizardTalentsLoad() {

        if (wizardRow1Item1 == 1) {
            wizardBomb = true
            wizardBombTimer = 300
            wizardBombDmg = 7.5f
        }
        if (wizardRow1Item1 == 2) {
            wizardBomb = true
            wizardBombTimer = 240
            wizardBombDmg = 15f
        }
        if (wizardRow1Item1 == 3) {
            wizardBomb = true
            wizardBombTimer = 180
            wizardBombDmg = 30f
        }

        if (wizardRow2Item1 == 1) {
            evadeGlobal += 1
            wizardMissedLightning = true
            wizardMissedLightningDmgBoost += 0.5f
        }
        if (wizardRow2Item1 == 2) {
            evadeGlobal += 2
            wizardMissedLightning = true
            wizardMissedLightningDmgBoost += 0.5f
        }
        if (wizardRow2Item1 == 3) {
            evadeGlobal += 3
            wizardMissedLightning = true
            wizardMissedLightningDmgBoost += 0.5f
        }

        if (wizardRow2Item2 == 1) {
            hpRegDebuffGlobal += 0.1f
            wizardMagicArmorSmasher = 0.9f
        }
        if (wizardRow2Item2 == 2) {
            hpRegDebuffGlobal += 0.2f
            wizardMagicArmorSmasher = 0.8f
        }
        if (wizardRow2Item2 == 3) {
            hpRegDebuffGlobal += 0.3f
            wizardMagicArmorSmasher = 0.7f
        }

        if (wizardRow3Item1 == 1) spellCastCD += 0.03f
        if (wizardRow3Item1 == 2) spellCastCD += 0.03f
        if (wizardRow3Item1 == 3) spellCastCD += 0.03f


        if (wizardRow3Item2 ==1) {
            wizardMine = true
            wizardMineTimer = 480f
        }
        if (wizardRow3Item2 ==1) {
            wizardMine = true
            wizardMineTimer = 420f
        }
        if (wizardRow3Item2 ==1) {
            wizardMine = true
            wizardMineTimer = 360f
        }

        if (wizardRow4Item1 == 1) {
            chainLighning = true
            chainLightningBounceTargets += 2
            chainLightningDmg = 0.9f
            com.agsolutions.td.Companion.chainLightningTimer = 240f
        }
        if (wizardRow4Item1 == 2) {
            chainLighning = true
            chainLightningBounceTargets += 4
            chainLightningDmg = 1.0f
            com.agsolutions.td.Companion.chainLightningTimer = 210f
        }
        if (wizardRow4Item1 == 3) {
            chainLighning = true
            chainLightningBounceTargets += 6
            chainLightningDmg = 1.1f
            com.agsolutions.td.Companion.chainLightningTimer = 180f
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
        return inflater.inflate(R.layout.fragment_wizard_talent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wizardRow1Item1ShowTV.text = wizardRow1Item1.toString()
        wizardRow2Item1ShowTV.text = wizardRow2Item1.toString()
        wizardRow2Item2ShowTV.text = wizardRow2Item2.toString()
        wizardRow3Item1ShowTV.text = wizardRow3Item1.toString()
        wizardRow3Item2ShowTV.text = wizardRow3Item2.toString()
        wizardRow4Item1ShowTV.text = wizardRow4Item1.toString()

        if (wizardRow1Item1 >= 3) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)
        if (wizardRow2Item1 + wizardRow2Item2 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)
        if (wizardRow3Item1 + wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

        wizardRow1Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Bombshell"
            wizardDisplayTalentTV.text = "Places a bomb on a random target each 5/4/3 seconds that deals 7.5/15/30 (+10% magic damage) aoe damage after a couple of seconds. Ignores armor."

            wizardUpgradeBTN.setOnClickListener() {
                wizardUpgradeBTN.isClickable = true

                if (wizardRow1Item1 <= 2 && talentPoints > 0) {
                    wizardRow1Item1 += 1


                    if (wizardRow1Item1 == 1) {
                        wizardBomb = true
                        wizardBombTimer = 300
                        wizardBombDmg = 7.5f
                    }
                    if (wizardRow1Item1 == 2) {
                        wizardBomb = true
                        wizardBombTimer = 240
                        wizardBombDmg = 15f
                    }
                    if (wizardRow1Item1 == 3) {
                        wizardBomb = true
                        wizardBombTimer = 180
                        wizardBombDmg = 30f
                    }

                    if (wizardRow1Item1 >= 3) backgroundWizardRow2.setBackgroundResource(R.drawable.backgroundplankslight)

                    wizardRow1Item1ShowTV.text = wizardRow1Item1.toString()
                    talentPoints -= 1
                }
            }
        }

        wizardRow2Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Lightning Bolt"
            wizardDisplayTalentTV.text = "Throws a lightning bolt on 2 enemies for each missed tower attack for 150/200/250% spelldamage. + 1/2/3 evade."
            wizardUpgradeBTN.isClickable = false

            if (wizardRow1Item1 == 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (wizardRow2Item1 <= 2 && talentPoints > 0) {
                        wizardRow2Item1 += 1

                        if (wizardRow2Item1 == 1) {
                            evadeGlobal += 1
                            wizardMissedLightning = true
                            wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (wizardRow2Item1 == 2) {
                            evadeGlobal += 1
                            wizardMissedLightning = true
                            wizardMissedLightningDmgBoost += 0.5f
                        }
                        if (wizardRow2Item1 == 3) {
                            evadeGlobal += 1
                            wizardMissedLightning = true
                            wizardMissedLightningDmgBoost += 0.5f
                        }

                        if (wizardRow2Item1 + wizardRow2Item2 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item1ShowTV.text = wizardRow2Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        wizardRow2Item2IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Dispel"
            wizardDisplayTalentTV.text = "Decreases magic armor of enemies with type magic armor by 10%/20%/30% & reduces hitpoint regeneration by 10/20/30%"
            wizardUpgradeBTN.isClickable = false

            if (wizardRow1Item1 == 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (wizardRow2Item2 <= 2 && talentPoints > 0) {
                        wizardRow2Item2 += 1


                        if (wizardRow2Item2 == 1) {
                            hpRegDebuffGlobal += 0.1f
                            wizardMagicArmorSmasher = 0.9f
                        }
                        if (wizardRow2Item2 == 2) {
                            hpRegDebuffGlobal += 0.1f
                            wizardMagicArmorSmasher = 0.8f
                        }
                        if (wizardRow2Item2 == 3) {
                            hpRegDebuffGlobal += 0.1f
                            wizardMagicArmorSmasher = 0.7f
                        }

                        if (wizardRow2Item1 + wizardRow2Item2 >= 3) backgroundWizardRow3.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow2Item2ShowTV.text = wizardRow2Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Cool Down!"
            wizardDisplayTalentTV.text = "Each spellcast decreases cooldown of all spellcasts by 3/6/9%."
            wizardUpgradeBTN.isClickable = false

            if (wizardRow2Item1 + wizardRow2Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (wizardRow3Item1 <= 2 && talentPoints > 0) {
                        wizardRow3Item1 += 1

                        if (wizardRow3Item1 == 1) spellCastCD += 0.03f
                        if (wizardRow3Item1 == 2) spellCastCD += 0.03f
                        if (wizardRow3Item1 == 3) spellCastCD += 0.03f


                        if (wizardRow3Item1 + wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item1ShowTV.text = wizardRow3Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        wizardRow3Item2IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Mine Crafter"
            wizardDisplayTalentTV.text = "Plants a mine at a random location every 8/7/6 seconds. Mines deals a small amount of damage and slow enemies by 50% for 1.5 seconds-"
            wizardUpgradeBTN.isClickable = false

            if (wizardRow2Item1 + wizardRow2Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (wizardRow3Item2 <= 2 && talentPoints > 0) {
                        wizardRow3Item2 += 1

                        if (wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer = 480f
                        }
                        if (wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer = 420f
                        }
                        if (wizardRow3Item2 ==1) {
                            wizardMine = true
                            wizardMineTimer = 360f
                        }

                        if (wizardRow3Item1 + wizardRow3Item2 >= 3) backgroundWizardRow4.setBackgroundResource(R.drawable.backgroundplankslight)

                        wizardRow3Item2ShowTV.text = wizardRow3Item2.toString()
                        talentPoints -= 1
                    }
                }
            }
        }

        wizardRow4Item1IB.setOnClickListener() {
            wizardNameDisplayTalentTV.text = "Chain Lightning"
            wizardDisplayTalentTV.text = "Casts a chain lightning that jumps to 2/4/6 targets. Increases danage for each enemy killed by chain lightning. +1 bag space at 3/3."
            wizardUpgradeBTN.isClickable = false

            if (wizardRow3Item1 + wizardRow3Item2 >= 3) {
                wizardUpgradeBTN.isClickable = true

                wizardUpgradeBTN.setOnClickListener() {
                    if (wizardRow4Item1 <= 2 && talentPoints > 0) {
                        wizardRow4Item1 += 1

                        if (wizardRow4Item1 == 1) {
                            chainLighning = true
                            chainLightningBounceTargets += 2
                            chainLightningDmg = 0.9f
                            com.agsolutions.td.Companion.chainLightningTimer = 240f
                        }
                        if (wizardRow4Item1 == 2) {
                            chainLighning = true
                            chainLightningBounceTargets += 2
                            chainLightningDmg = 1.0f
                            com.agsolutions.td.Companion.chainLightningTimer = 210f
                        }
                        if (wizardRow4Item1 == 3) {
                            com.agsolutions.td.Companion.itemList.add(Items(306, 0, 999, 0, 0f, 0, 0f, 0, "Beggar", R.drawable.bagicon3, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, "+1 bag slot", 1f, "", 0f))
                            com.agsolutions.td.Companion.insertItem += 1
                            chainLighning = true
                            chainLightningBounceTargets += 2
                            chainLightningDmg = 1.1f
                            com.agsolutions.td.Companion.chainLightningTimer = 180f
                        }

                        wizardRow4Item1ShowTV.text = wizardRow4Item1.toString()
                        talentPoints -= 1
                    }
                }
            }
        }
    }
}