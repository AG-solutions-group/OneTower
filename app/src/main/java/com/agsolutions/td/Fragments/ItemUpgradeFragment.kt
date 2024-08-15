package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.agsolutions.td.*
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Utils.round
import com.agsolutions.td.databinding.FragmentItemBinding
import com.agsolutions.td.databinding.FragmentUpgradeItemBinding

class ItemUpgradeFragment : Fragment(), ItemFragmentAdapter.OnStatsClickListener {

    val itemFragmentAdapter = ItemFragmentAdapter(GameActivity.companionList.itemFragmentEnemyList, this)

    private var _binding: FragmentUpgradeItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpgradeItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            upBTN.visibility = View.INVISIBLE

            itemBagFragmentRecycler.adapter = itemFragmentAdapter

            val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0) {
                        return 2
                    } else {
                        if (GameActivity.companionList.itemFragmentEnemyList[position].stats.length > 10) return 2
                        else return 1
                    }
                }
            }

            val glm = GridLayoutManager(activity, 2)

            glm.spanSizeLookup = spanSizeLookup
            itemBagFragmentRecycler.layoutManager = glm
            itemBagFragmentRecycler.setHasFixedSize(true)

        }

        refresh2 ()
    }

    fun refresh2 () {
        itemFragmentAdapter.notifyDataSetChanged()
    }

    fun refresh (position: Int) {

        if (!GameActivity.companionList.towerClick){

        }else {

            GameActivity.companionList.readLockTower.lock()
            try {
                    var tower = GameActivity.companionList.towerList[GameActivity.companionList.towerClickID]


                        if (tower.itemListBag[position].upgrade < 1 || GameActivity.companionList.upgradePoints <= 0) {
                            binding.upBTN.visibility = View.INVISIBLE
                        } else {
                            binding.upBTN.visibility = View.VISIBLE
                        }

                    binding.upBTN.setOnClickListener() {

                            GameActivity.companionList.itemFragmentEnemyList.removeAll(GameActivity.companionList.itemFragmentEnemyList)

                            if (tower.itemListBag[position].upgrade > 0 && GameActivity.companionList.upgradePoints > 0) {
                                tower.itemListBag[position].upgrade -= 1
                                GameActivity.companionList.upgradePoints -= 1

                                if (tower.itemListBag[position].dmg > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusTowerDmg += (tower.itemListBag[position].dmg * 0.1f)
                                            tower.itemListBag[position].dmg += (tower.itemListBag[position].dmg * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusTowerDmg += (tower.itemListBag[position].dmg * 0.15f)
                                            tower.itemListBag[position].dmg += (tower.itemListBag[position].dmg * 0.15f)

                                        }
                                        in 89..99 -> {
                                            tower.bonusTowerDmg += (tower.itemListBag[position].dmg * 0.2f)
                                            tower.itemListBag[position].dmg += (tower.itemListBag[position].dmg * 0.2f)

                                        }
                                    }
                                }
                                if (tower.itemListBag[position].atkDmg > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusPhysicalDmg += (tower.itemListBag[position].atkDmg * 0.1f)
                                            tower.itemListBag[position].atkDmg += (tower.itemListBag[position].atkDmg * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusPhysicalDmg += (tower.itemListBag[position].atkDmg * 0.15f)
                                            tower.itemListBag[position].atkDmg += (tower.itemListBag[position].atkDmg * 0.15f)

                                        }
                                        in 89..99 -> {
                                            tower.bonusPhysicalDmg += (tower.itemListBag[position].atkDmg * 0.2f)
                                            tower.itemListBag[position].atkDmg += (tower.itemListBag[position].atkDmg * 0.2f)

                                        }
                                    }
                                }
                                if (tower.itemListBag[position].mgcDmg > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusSpellDamage += (tower.itemListBag[position].mgcDmg * 0.1f)
                                            tower.itemListBag[position].mgcDmg += (tower.itemListBag[position].mgcDmg * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusSpellDamage += (tower.itemListBag[position].mgcDmg * 0.15f)
                                            tower.itemListBag[position].mgcDmg += (tower.itemListBag[position].mgcDmg * 0.15f)

                                        }
                                        in 89..99 -> {
                                            tower.bonusSpellDamage += (tower.itemListBag[position].mgcDmg * 0.2f)
                                            tower.itemListBag[position].mgcDmg += (tower.itemListBag[position].mgcDmg * 0.2f)

                                        }
                                    }
                                }
                                if (tower.itemListBag[position].speed > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusTowerSpeed += (tower.itemListBag[position].speed * 0.1f)
                                            tower.itemListBag[position].speed += (tower.itemListBag[position].speed * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusTowerSpeed += (tower.itemListBag[position].speed * 0.15f)
                                            tower.itemListBag[position].speed += (tower.itemListBag[position].speed * 0.15f)

                                        }

                                        in 89..99 -> {
                                            tower.bonusTowerSpeed += (tower.itemListBag[position].speed * 0.2f)
                                            tower.itemListBag[position].speed += (tower.itemListBag[position].speed * 0.2f)

                                        }

                                    }
                                }
                                if (tower.itemListBag[position].crit > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusCrit += (tower.itemListBag[position].crit * 0.1f)
                                            tower.itemListBag[position].crit += (tower.itemListBag[position].crit * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusCrit += (tower.itemListBag[position].crit * 0.15f)
                                            tower.itemListBag[position].crit += (tower.itemListBag[position].crit * 0.15f)

                                        }
                                        in 89..99 -> {
                                            tower.bonusCrit += (tower.itemListBag[position].crit * 0.2f)
                                            tower.itemListBag[position].crit += (tower.itemListBag[position].crit * 0.2f)

                                        }

                                    }
                                }
                                if (tower.itemListBag[position].critDmg > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusCritDmg += (tower.itemListBag[position].critDmg * 0.1f)
                                            tower.itemListBag[position].critDmg += (tower.itemListBag[position].critDmg * 0.1f)

                                        }
                                        in 66..88 -> {
                                            tower.bonusCritDmg += (tower.itemListBag[position].critDmg * 0.15f)
                                            tower.itemListBag[position].critDmg += (tower.itemListBag[position].critDmg * 0.15f)

                                        }
                                        in 89..99 -> {
                                            tower.bonusCritDmg += (tower.itemListBag[position].critDmg * 0.2f)
                                            tower.itemListBag[position].critDmg += (tower.itemListBag[position].critDmg * 0.2f)
                                        }
                                    }
                                }
                                if (tower.itemListBag[position].specialFloat > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "armor penetration") {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "magic penetration") {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "hit chance") {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            }
                                            if (tower.itemListBag[position].id == 212) {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            }
                                            if (tower.itemListBag[position].id == 215) {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            }
                                            if (tower.itemListBag[position].id == 216) {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            }
                                            if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "anti-heal") {
                                                tower.bonusAntiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                                tower.bonusDmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.1f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                            }
                                        }
                                        in 66..88 -> {
                                            if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "armor penetration") {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "magic penetration") {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "hit chance") {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            }
                                            if (tower.itemListBag[position].id == 212) {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            }
                                            if (tower.itemListBag[position].id == 215) {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            }
                                            if (tower.itemListBag[position].id == 216) {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            }
                                            if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "anti-heal") {
                                                tower.bonusAntiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                                tower.bonusDmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.15f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                            }
                                        }
                                        in 89..99 -> {
                                            if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "armor penetration") {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "magic penetration") {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            } else if ((tower.itemListBag[position].id == 200 || tower.itemListBag[position].id == 202 || tower.itemListBag[position].id == 203) && tower.itemListBag[position].special == "hit chance") {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            }
                                            if (tower.itemListBag[position].id == 212) {
                                                tower.itemBonusHitChance += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            }
                                            if (tower.itemListBag[position].id == 215) {
                                                tower.bonusArmorPen += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            }
                                            if (tower.itemListBag[position].id == 216) {
                                                tower.bonusMagicPen += (tower.itemListBag[position].specialFloat * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            }
                                            if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "anti-heal") {
                                                tower.bonusAntiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                                tower.bonusDmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.2f)
                                                tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                            }
                                        }
                                    }
                                }
                                if (tower.itemListBag[position].specialFloat2 > 0) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            if (tower.itemListBag[position].id == 4) {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2 * 0.1f)
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.1f)
                                            }
                                            if (tower.itemListBag[position].special2 == "item find") {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2) * 0.1f
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.1f)
                                            } else if (tower.itemListBag[position].special2 == "item quality") {
                                                tower.bonusItemQuality += ((tower.itemListBag[position].specialFloat2 * 0.1f))
                                                tower.itemListBag[position].specialFloat2 += ((tower.itemListBag[position].specialFloat2 * 0.1f))
                                            }
                                        }
                                        in 66..88 -> {
                                            if (tower.itemListBag[position].id == 4) {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2 * 0.15f)
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.15f)
                                            }
                                            if (tower.itemListBag[position].special2 == "item find") {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2) * 0.15f
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.15f)
                                            }
                                            if (tower.itemListBag[position].special2 == "item quality") {
                                                tower.bonusItemQuality += ((tower.itemListBag[position].specialFloat2 * 0.15f))
                                                tower.itemListBag[position].specialFloat2 += ((tower.itemListBag[position].specialFloat2 * 0.15f))
                                            }
                                        }
                                        in 89..99 -> {
                                            if (tower.itemListBag[position].id == 4) {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2 * 0.2f)
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.2f)
                                            }
                                            if (tower.itemListBag[position].special2 == "item find") {
                                                tower.bonusItemChance += (tower.itemListBag[position].specialFloat2) * 0.2f
                                                tower.itemListBag[position].specialFloat2 += (tower.itemListBag[position].specialFloat2 * 0.2f)
                                            }
                                            if (tower.itemListBag[position].special2 == "item quality") {
                                                tower.bonusItemQuality += ((tower.itemListBag[position].specialFloat2 * 0.2f))
                                                tower.itemListBag[position].specialFloat2 += ((tower.itemListBag[position].specialFloat2 * 0.2f))
                                            }
                                        }
                                    }
                                }
                                if (tower.itemListBag[position].itemChance > 0f) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                                tower.bonusItemChance += (tower.itemListBag[position].itemChance * 0.1f)
                                                tower.itemListBag[position].itemChance += (tower.itemListBag[position].itemChance * 0.1f)
                                            }
                                        in 66..88 -> {
                                                tower.bonusItemChance += (tower.itemListBag[position].itemChance * 0.15f)
                                                tower.itemListBag[position].itemChance += (tower.itemListBag[position].itemChance * 0.15f)
                                            }
                                        in 89..99 -> {
                                                tower.bonusItemChance += (tower.itemListBag[position].itemChance * 0.2f)
                                                tower.itemListBag[position].itemChance += (tower.itemListBag[position].itemChance * 0.2f)
                                            }
                                    }
                                }
                                if (tower.itemListBag[position].itemQuality > 0f) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusItemQuality += (tower.itemListBag[position].itemQuality * 0.1f)
                                            tower.itemListBag[position].itemQuality += (tower.itemListBag[position].itemQuality * 0.1f)
                                        }
                                        in 66..88 -> {
                                            tower.bonusItemQuality += (tower.itemListBag[position].itemQuality * 0.15f)
                                            tower.itemListBag[position].itemQuality += (tower.itemListBag[position].itemQuality * 0.15f)
                                        }
                                        in 89..99 -> {
                                            tower.bonusItemQuality += (tower.itemListBag[position].itemQuality * 0.2f)
                                            tower.itemListBag[position].itemQuality += (tower.itemListBag[position].itemQuality * 0.2f)
                                        }
                                    }
                                }
                                if (tower.itemListBag[position].xpGain > 0f) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusXpMultiplier += (tower.itemListBag[position].xpGain * 0.1f)
                                            tower.itemListBag[position].xpGain += (tower.itemListBag[position].xpGain * 0.1f)
                                        }
                                        in 66..88 -> {
                                            tower.bonusXpMultiplier += (tower.itemListBag[position].xpGain * 0.15f)
                                            tower.itemListBag[position].xpGain += (tower.itemListBag[position].xpGain * 0.15f)
                                        }
                                        in 89..99 -> {
                                            tower.bonusXpMultiplier += (tower.itemListBag[position].xpGain * 0.2f)
                                            tower.itemListBag[position].xpGain += (tower.itemListBag[position].xpGain * 0.2f)
                                        }
                                    }
                                }
                                if (tower.itemListBag[position].goldIncome > 0f) {
                                    when ((0..99).random()) {
                                        in 0..65 -> {
                                            tower.bonusGoldMultiplier += (tower.itemListBag[position].goldIncome * 0.1f)
                                            tower.itemListBag[position].goldIncome += (tower.itemListBag[position].goldIncome * 0.1f)
                                        }
                                        in 66..88 -> {
                                            tower.bonusGoldMultiplier += (tower.itemListBag[position].goldIncome * 0.15f)
                                            tower.itemListBag[position].goldIncome += (tower.itemListBag[position].goldIncome * 0.15f)
                                        }
                                        in 89..99 -> {
                                            tower.bonusGoldMultiplier += (tower.itemListBag[position].goldIncome * 0.2f)
                                            tower.itemListBag[position].goldIncome += (tower.itemListBag[position].goldIncome * 0.2f)
                                        }
                                    }
                                }
                            }

                            GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, tower.itemListBag[position].name.toString()))
                            if (tower.itemListBag[position].upgrade > 0) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, tower.itemListBag[position].upgrade.toString()))
                            if (tower.itemListBag[position].dmg > 0) {
                                when (tower.itemListBag[position].dmg.toInt()) {
                                    in 0..999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, tower.itemListBag[position].dmg.round(2)
                                        .toString()))
                                    in 1000..999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000).round(2)
                                        .toString() + "k"))
                                    in 1000000..999999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000000).round(2)
                                        .toString() + "M"))
                                }
                            }
                            if (tower.itemListBag[position].atkDmg > 0) {
                                when (tower.itemListBag[position].atkDmg.toInt()) {
                                    in 0..999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, tower.itemListBag[position].atkDmg.round(2)
                                        .toString()))
                                    in 1000..999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000).round(2)
                                        .toString() + "k"))
                                    in 1000000..999999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000000).round(2)
                                        .toString() + "M"))
                                }
                            }
                            if (tower.itemListBag[position].mgcDmg > 0) {
                                when (tower.itemListBag[position].mgcDmg.toInt()) {
                                    in 0..999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, tower.itemListBag[position].mgcDmg.round(2)
                                        .toString()))
                                    in 1000..999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000).round(2)
                                        .toString() + "k"))
                                    in 1000000..999999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000000).round(2)
                                        .toString() + "M"))
                                }
                            }
                            if (tower.itemListBag[position].crit > 0) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, tower.itemListBag[position].crit.round(2)
                                .toString()))
                            if (tower.itemListBag[position].critDmg > 0) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, tower.itemListBag[position].critDmg.round(2)
                                .toString()))
                            if (tower.itemListBag[position].speed > 0) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, tower.itemListBag[position].speed.round(2)
                                .toString()))
                            if (tower.itemListBag[position].special.isNotBlank()) {
                                GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special.toString()))
                                if (tower.itemListBag[position].specialFloat != 0f) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat.round(2)
                                    .toString()))
                            }
                            if (tower.itemListBag[position].special2.isNotBlank()) {
                                GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special2.toString()))
                                if (tower.itemListBag[position].specialFloat2 != 0f) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat2.round(2)
                                    .toString()))
                            }
                            if (tower.itemListBag[position].itemChance > 0f) {
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "Item Chance"))
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].itemChance.round(2).toString()))
                            }
                            if (tower.itemListBag[position].itemQuality > 0f) {
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "Item Quality"))
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].itemQuality.round(2).toString()))
                            }
                            if (tower.itemListBag[position].xpGain > 0f) {
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "XP Gain"))
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].xpGain.round(2).toString()))
                            }
                            if (tower.itemListBag[position].goldIncome > 0f) {
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "Gold Drop"))
                                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].goldIncome.round(2).toString()))
                            }

                            itemFragmentAdapter.notifyDataSetChanged()

                            if (tower.itemListBag[position].upgrade < 1 || GameActivity.companionList.upgradePoints <= 0) {
                                binding.upBTN.visibility = View.INVISIBLE
                            } else {
                                binding.upBTN.visibility = View.VISIBLE
                            }
                }
            } finally {
                GameActivity.companionList.readLockTower.unlock()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onStatsClick(position: Int) {
        if (GameActivity.companionList.itemFragmentEnemyList[position].name == R.drawable.nameicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Item Name"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.swordandwandicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Damage - bonus physical and spell damage"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.swordicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Physical Damage - bonus physical damage"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.wandicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Spell Damage - bonus spell damage"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.bowicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Attack Speed - frequency of tower attacks"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.specialicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Special Attributes"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.upgradepointsicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Upgrade Points - used to upgrade items"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.itemfindicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Item Find - chance to find items in percent"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.itemqualityicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Item Quality - rating to find better items"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.criticon) {
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.critdmgicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Critical Damage - damage multiplier of critical hits"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.multicriticon) {
            companionList.toastGlobal = true
            companionList.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.hiticon) {
            companionList.toastGlobal = true
            companionList.toastText = "Hit Chance - chance to hit enemies in percent"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.armorpenicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.magicpenicon) {
            companionList.toastGlobal = true
            companionList.toastText =
                "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
    }
}

