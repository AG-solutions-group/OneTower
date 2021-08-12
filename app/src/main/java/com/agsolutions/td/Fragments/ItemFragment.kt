package com.agsolutions.td.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.agsolutions.td.*
import com.agsolutions.td.Companion.Companion.fragmentItemCurrentItem
import com.agsolutions.td.Companion.Companion.itemFragmentEnemyList
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.scrollOldPositionX
import com.agsolutions.td.Companion.Companion.scrollOldPositionY
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_stats_tower.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment(), ItemFragmentAdapter.OnStatsClickListener,
    View.OnScrollChangeListener {

    val itemFragmentAdapter = ItemFragmentAdapter(com.agsolutions.td.Companion.itemFragmentEnemyList, this )

    var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mLeak = inflater.inflate(R.layout.fragment_item, container, false)
        return mLeak
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemFragmentRecycler.setOnScrollChangeListener(this)
        itemFragmentRecycler.adapter = itemFragmentAdapter

        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0) {
                    return 2
                } else {
                    if (itemFragmentEnemyList[position].stats.length > 15) return 2
                    else return 1
                }
            }
        }

        val glm = GridLayoutManager(activity, 2)

        glm.spanSizeLookup = spanSizeLookup
        itemFragmentRecycler.layoutManager = glm

    }

    fun refresh (supportFragmentManager: FragmentManager, fragmentItem:ItemFragment) {

        if (itemFragmentEnemyList.isNotEmpty()) {
            Companion.itemFragmentEnemyList.removeAll(Companion.itemFragmentEnemyList)
        }

            Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, itemList[fragmentItemCurrentItem].name.toString()))
            if (itemList[fragmentItemCurrentItem].diaCost > 0) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.diamondicon, (itemList[fragmentItemCurrentItem].diaCost.toString() + " / " + Companion.diamonds.toInt()
                .toString())))
            if (!Companion.day && Companion.moonTalentItemCost > 0) {
                if (itemList[fragmentItemCurrentItem].xpCost > 0) {
                    when (itemList[fragmentItemCurrentItem].xpCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost - (itemList[fragmentItemCurrentItem].xpCost * Companion.moonTalentItemCost)).toInt()
                            .toString() + " / " + Companion.xp.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost - (itemList[fragmentItemCurrentItem].xpCost * Companion.moonTalentItemCost) / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.xp / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost - (itemList[fragmentItemCurrentItem].xpCost * Companion.moonTalentItemCost) / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.xp / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else if (Companion.midnightMadnessMidasGoldCost > 0) {
                if (itemList[fragmentItemCurrentItem].xpCost > 0) {
                    when (itemList[fragmentItemCurrentItem].xpCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost + (itemList[fragmentItemCurrentItem].xpCost * Companion.midnightMadnessMidasGoldCost)).toInt()
                            .toString() + " / " + Companion.xp.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost + (itemList[fragmentItemCurrentItem].xpCost * Companion.midnightMadnessMidasGoldCost) / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.xp / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost + (itemList[fragmentItemCurrentItem].xpCost * Companion.midnightMadnessMidasGoldCost) / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.xp / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else {
                if (itemList[fragmentItemCurrentItem].xpCost > 0) {
                    when (itemList[fragmentItemCurrentItem].xpCost.toInt()) {
                        in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost).toInt()
                            .toString() + " / " + Companion.xp.toInt().toString()))
                        in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost / 1000).round(1)
                            .toString() + "k" + " / " + (Companion.xp / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (itemList[fragmentItemCurrentItem].xpCost / 1000000).round(1)
                            .toString() + "M" + " / " + (Companion.xp / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            }
            if (itemList[fragmentItemCurrentItem].upgrade > 0) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, itemList[fragmentItemCurrentItem].upgrade.toString()))
            if (itemList[fragmentItemCurrentItem].dmg > 0) {
                when (itemList[fragmentItemCurrentItem].dmg.toInt()) {
                    in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, itemList[fragmentItemCurrentItem].dmg.round(2)
                        .toString()))
                    in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (itemList[fragmentItemCurrentItem].dmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (itemList[fragmentItemCurrentItem].dmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (itemList[fragmentItemCurrentItem].atkDmg > 0) {
                when (itemList[fragmentItemCurrentItem].atkDmg.toInt()) {
                    in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, itemList[fragmentItemCurrentItem].atkDmg.round(2)
                        .toString()))
                    in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (itemList[fragmentItemCurrentItem].atkDmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (itemList[fragmentItemCurrentItem].atkDmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (itemList[fragmentItemCurrentItem].mgcDmg > 0) {
                when (itemList[fragmentItemCurrentItem].mgcDmg.toInt()) {
                    in 0..999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, itemList[fragmentItemCurrentItem].mgcDmg.round(2)
                        .toString()))
                    in 1000..999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (itemList[fragmentItemCurrentItem].mgcDmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (itemList[fragmentItemCurrentItem].mgcDmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (itemList[fragmentItemCurrentItem].crit > 0) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, itemList[fragmentItemCurrentItem].crit.round(2)
                .toString()))
            if (itemList[fragmentItemCurrentItem].critDmg > 0) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, itemList[fragmentItemCurrentItem].critDmg.round(2)
                .toString()))
            if (itemList[fragmentItemCurrentItem].speed > 0) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, itemList[fragmentItemCurrentItem].speed.round(2)
                .toString()))
            if (itemList[fragmentItemCurrentItem].special.isNotBlank()) {
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, itemList[fragmentItemCurrentItem].special.toString()))
                if (itemList[fragmentItemCurrentItem].specialFloat != 0f) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, itemList[fragmentItemCurrentItem].specialFloat.round(2)
                    .toString()))
            }
            if (itemList[fragmentItemCurrentItem].special2.isNotBlank()) {
                Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, itemList[fragmentItemCurrentItem].special2.toString()))
                if (itemList[fragmentItemCurrentItem].specialFloat2 != 0f) Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, itemList[fragmentItemCurrentItem].specialFloat2.round(2)
                    .toString()))
            }
            Companion.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.overlaytransparent, "                ".toString()))

        if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) itemFragmentAdapter.notifyDataSetChanged()

        if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) {
            itemFragmentRecycler.smoothScrollBy(scrollOldPositionX, scrollOldPositionY)
        }
    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onStatsClick(position: Int) {
        if (Companion.itemFragmentEnemyList[position].name == R.drawable.nameicon) {
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
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.goldicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Gold - primary currency to buy items"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.upgradepointsicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Upgrade Points - used to upgrade items"
        }else if (com.agsolutions.td.Companion.itemFragmentEnemyList[position].name == R.drawable.diamondicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Diamonds - secondary currency to buy items"
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

    override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        scrollOldPositionX = scrollX
        scrollOldPositionY = scrollY
    }
}
