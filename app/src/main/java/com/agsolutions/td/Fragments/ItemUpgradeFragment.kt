package com.agsolutions.td.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.agsolutions.td.*
import com.agsolutions.td.Companion.Companion.itemFragmentEnemyList
import com.agsolutions.td.Companion.Companion.upgradePoints
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_upgrade_item.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemUpgradeFragment : Fragment(), ItemFragmentAdapter.OnStatsClickListener {

    val itemFragmentAdapter = ItemFragmentAdapter(itemFragmentEnemyList, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var mLeak = inflater.inflate(R.layout.fragment_upgrade_item, container, false)
        return mLeak
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upBTN.visibility = View.INVISIBLE
        upIV.visibility = View.INVISIBLE

        itemBagFragmentRecycler.adapter = itemFragmentAdapter

        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0) {
                    return 2
                } else {
                    if (itemFragmentEnemyList[position].stats.length > 12) return 2
                    else return 1
                }
            }
        }

        val glm = GridLayoutManager(activity, 2)

        glm.spanSizeLookup = spanSizeLookup
        itemBagFragmentRecycler.layoutManager = glm
        itemBagFragmentRecycler.setHasFixedSize(true)

        refresh2 ()

    }

    fun refresh2 () {
        itemFragmentAdapter.notifyDataSetChanged()
    }

    fun refresh (position: Int) {

        Companion.readLockTower.lock ()
        try {
            var towerListIterator = Companion.towerList.listIterator()
            while (towerListIterator.hasNext()) {
                var tower = towerListIterator.next()
                if (tower.selected) {

                    if (tower.itemListBag[position].upgrade < 1 || upgradePoints <= 0) {
                        upBTN.visibility = View.INVISIBLE
                        upIV.visibility = View.INVISIBLE
                    } else {
                        upBTN.visibility = View.VISIBLE
                        upIV.visibility = View.VISIBLE
                    }

                    upBTN.setOnClickListener() {

                        itemFragmentEnemyList.removeAll(itemFragmentEnemyList)

                        if (tower.itemListBag[position].upgrade > 0 && upgradePoints > 0) {
                            tower.itemListBag[position].upgrade -= 1
                            upgradePoints -= 1

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
                                            com.agsolutions.td.Companion.antiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.1f)
                                            tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.1f)
                                        } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                            com.agsolutions.td.Companion.dmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.1f)
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
                                            com.agsolutions.td.Companion.antiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.15f)
                                            tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.15f)
                                        } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                            com.agsolutions.td.Companion.dmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.15f)
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
                                            com.agsolutions.td.Companion.antiHeal += ((tower.itemListBag[position].specialFloat / 100) * 0.2f)
                                            tower.itemListBag[position].specialFloat += (tower.itemListBag[position].specialFloat * 0.2f)
                                        } else if ((tower.itemListBag[position].id == 300 || tower.itemListBag[position].id == 302 || tower.itemListBag[position].id == 303) && tower.itemListBag[position].special == "extra dmg immune") {
                                            com.agsolutions.td.Companion.dmgImmune += ((tower.itemListBag[position].specialFloat / 100) * 0.2f)
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
                        }

                        itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, tower.itemListBag[position].name.toString()))
                        if (tower.itemListBag[position].upgrade > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, tower.itemListBag[position].upgrade.toString()))
                        if (tower.itemListBag[position].dmg > 0) {
                            when (tower.itemListBag[position].dmg.toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, tower.itemListBag[position].dmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (tower.itemListBag[position].dmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].atkDmg > 0) {
                            when (tower.itemListBag[position].atkDmg.toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, tower.itemListBag[position].atkDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (tower.itemListBag[position].atkDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].mgcDmg > 0) {
                            when (tower.itemListBag[position].mgcDmg.toInt()) {
                                in 0..999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, tower.itemListBag[position].mgcDmg.round(2)
                                    .toString()))
                                in 1000..999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000).round(2)
                                    .toString() + "k"))
                                in 1000000..999999999 -> itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (tower.itemListBag[position].mgcDmg / 1000000).round(2)
                                    .toString() + "M"))
                            }
                        }
                        if (tower.itemListBag[position].crit > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, tower.itemListBag[position].crit.round(2)
                            .toString()))
                        if (tower.itemListBag[position].critDmg > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, tower.itemListBag[position].critDmg.round(2)
                            .toString()))
                        if (tower.itemListBag[position].speed > 0) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, tower.itemListBag[position].speed.round(2)
                            .toString()))
                        if (tower.itemListBag[position].special.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special.toString()))
                            if (tower.itemListBag[position].specialFloat != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat.round(2)
                                .toString()))
                        }
                        if (tower.itemListBag[position].special2.isNotBlank()) {
                            itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].special2.toString()))
                            if (tower.itemListBag[position].specialFloat2 != 0f) itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, tower.itemListBag[position].specialFloat2.round(2)
                                .toString()))
                        }

                        itemFragmentAdapter.notifyDataSetChanged()

                        if (tower.itemListBag[position].upgrade < 1 || upgradePoints <= 0) {
                            upBTN.visibility = View.INVISIBLE
                            upIV.visibility = View.INVISIBLE
                        } else {
                            upBTN.visibility = View.VISIBLE
                            upIV.visibility = View.VISIBLE
                        }
                    }
                }
            }
                }finally {
                    Companion.readLockTower.unlock()
                }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onStatsClick(position: Int) {
        if (itemFragmentEnemyList[position].name == R.drawable.nameicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Name"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.swordandwandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Damage - bonus physical and spell damage"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.swordicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Physical Damage - bonus physical damage"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.wandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Spell Damage - bonus spell damage"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.bowicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Attack Speed - frequency of tower attacks"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.specialicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Special Attributes"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.upgradepointsicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Upgrade Points - used to upgrade items"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.itemfindicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Find - chance to find items in percent"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.itemqualityicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Quality - rating to find better items"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.criticon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage Chance - chance of hitting a critical attack"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.critdmgicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Critical Damage - damage multiplier of critical hits"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.multicriticon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Multi Crit - gives critical hits the chance to multiply critical damage"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.hiticon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Hit Chance - chance to hit enemies in percent"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.armorpenicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Armor Penetration - rating to reduce enemies armor, making them more vulnerable to physical attacks"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.magicpenicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText =
                "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
    }
}

