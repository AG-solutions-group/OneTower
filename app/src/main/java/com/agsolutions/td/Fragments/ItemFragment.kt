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
import com.agsolutions.td.GameActivity.Companion.companionList
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
class ItemFragment : Fragment(), ItemFragmentAdapter.OnStatsClickListener{

    val itemFragmentAdapter = ItemFragmentAdapter(GameActivity.companionList.itemFragmentEnemyList, this )

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

        itemFragmentRecycler.adapter = itemFragmentAdapter

        val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0) {
                    return 2
                } else {
                    if (GameActivity.companionList.itemFragmentEnemyList[position].stats.length > 15) return 2
                    else return 1
                }
            }
        }

        val glm = GridLayoutManager(activity, 2)

        glm.spanSizeLookup = spanSizeLookup
        itemFragmentRecycler.layoutManager = glm

    }

    fun refresh (supportFragmentManager: FragmentManager, fragmentItem:ItemFragment) {

        if (GameActivity.companionList.itemFragmentEnemyList.isNotEmpty()) {
            GameActivity.companionList.itemFragmentEnemyList.removeAll(GameActivity.companionList.itemFragmentEnemyList)
        }

        GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].name.toString()))
            if (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].diaCost > 0) GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.diamondicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].diaCost.toString() + " / " + GameActivity.companionList.diamonds.toInt()
                .toString())))
            if (!GameActivity.companionList.day && GameActivity.companionList.moonTalentItemCost > 0) {
                if (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost > 0) {
                    when (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost.toInt()) {
                        in 0..999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost - (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.moonTalentItemCost)).toInt()
                            .toString()))
                        in 1000..999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost - (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.moonTalentItemCost) / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost - (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.moonTalentItemCost) / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else if (GameActivity.companionList.midnightMadnessMidasGoldCost > 0) {
                if (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost > 0) {
                    when (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost.toInt()) {
                        in 0..999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost + (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.midnightMadnessMidasGoldCost)).toInt()
                            .toString()))
                        in 1000..999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost + (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.midnightMadnessMidasGoldCost) / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> GameActivity.companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost + (GameActivity.companionList.itemList[GameActivity.companionList.fragmentItemCurrentItem].goldCost * GameActivity.companionList.midnightMadnessMidasGoldCost) / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            } else {
                if (companionList.itemList[companionList.fragmentItemCurrentItem].goldCost > 0) {
                    when (companionList.itemList[companionList.fragmentItemCurrentItem].goldCost.toInt()) {
                        in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.itemList[companionList.fragmentItemCurrentItem].goldCost).toInt()
                            .toString()))
                        in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.itemList[companionList.fragmentItemCurrentItem].goldCost / 1000).round(1)
                            .toString() + "k"))
                        in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.goldicon, (companionList.itemList[companionList.fragmentItemCurrentItem].goldCost / 1000000).round(1)
                            .toString() + "M"))
                    }
                }
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].upgrade > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.upgradepointsicon, companionList.itemList[companionList.fragmentItemCurrentItem].upgrade.toString()))
            if (companionList.itemList[companionList.fragmentItemCurrentItem].dmg > 0) {
                when (companionList.itemList[companionList.fragmentItemCurrentItem].dmg.toInt()) {
                    in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, companionList.itemList[companionList.fragmentItemCurrentItem].dmg.round(2)
                        .toString()))
                    in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (companionList.itemList[companionList.fragmentItemCurrentItem].dmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordandwandicon, (companionList.itemList[companionList.fragmentItemCurrentItem].dmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].atkDmg > 0) {
                when (companionList.itemList[companionList.fragmentItemCurrentItem].atkDmg.toInt()) {
                    in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, companionList.itemList[companionList.fragmentItemCurrentItem].atkDmg.round(2)
                        .toString()))
                    in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (companionList.itemList[companionList.fragmentItemCurrentItem].atkDmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.swordicon, (companionList.itemList[companionList.fragmentItemCurrentItem].atkDmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].mgcDmg > 0) {
                when (companionList.itemList[companionList.fragmentItemCurrentItem].mgcDmg.toInt()) {
                    in 0..999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, companionList.itemList[companionList.fragmentItemCurrentItem].mgcDmg.round(2)
                        .toString()))
                    in 1000..999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (companionList.itemList[companionList.fragmentItemCurrentItem].mgcDmg / 1000).round(2)
                        .toString() + "k"))
                    in 1000000..999999999 -> companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.wandicon, (companionList.itemList[companionList.fragmentItemCurrentItem].mgcDmg / 1000000).round(2)
                        .toString() + "M"))
                }
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].crit > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.criticon, companionList.itemList[companionList.fragmentItemCurrentItem].crit.round(2)
                .toString()))
            if (companionList.itemList[companionList.fragmentItemCurrentItem].critDmg > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.critdmgicon, companionList.itemList[companionList.fragmentItemCurrentItem].critDmg.round(2)
                .toString()))
            if (companionList.itemList[companionList.fragmentItemCurrentItem].speed > 0) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.bowicon, companionList.itemList[companionList.fragmentItemCurrentItem].speed.round(2)
                .toString()))
            if (companionList.itemList[companionList.fragmentItemCurrentItem].special.isNotBlank()) {
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.itemList[companionList.fragmentItemCurrentItem].special.toString()))
                if (companionList.itemList[companionList.fragmentItemCurrentItem].specialFloat != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.itemList[companionList.fragmentItemCurrentItem].specialFloat.round(2)
                    .toString()))
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].special2.isNotBlank()) {
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.itemList[companionList.fragmentItemCurrentItem].special2.toString()))
                if (companionList.itemList[companionList.fragmentItemCurrentItem].specialFloat2 != 0f) companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, companionList.itemList[companionList.fragmentItemCurrentItem].specialFloat2.round(2)
                    .toString()))
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].id == 2000 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2001 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2002 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2003 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2004 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2005 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2006 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2007 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2008 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2009) {
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 0.85"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 0.85"))
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].id == 2100 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2101 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2102 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2103 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2104 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2105 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2106 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2107 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2108 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2109){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.0"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.0"))
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].id == 2200 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2201 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2202 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2203 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2204 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2205 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2206 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2207 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2208 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2209){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.15"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.15"))
            }
            if (companionList.itemList[companionList.fragmentItemCurrentItem].id == 2300 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2301 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2302 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2303 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2304 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2305 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2306 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2307 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2308 || companionList.itemList[companionList.fragmentItemCurrentItem].id == 2309){
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "dropped item value * 1.4"))
                companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.specialicon, "tower item value * 1.4"))
            }
            companionList.itemFragmentEnemyList.add(ItemFragmentStrings(R.drawable.overlaytransparent, "                ".toString()))

        if (supportFragmentManager.findFragmentById(R.id.fragment) == fragmentItem) itemFragmentAdapter.notifyDataSetChanged()

    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onStatsClick(position: Int) {
        if (companionList.itemFragmentEnemyList[position].name == R.drawable.nameicon) {
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
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.goldicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Gold - primary currency to buy items"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.upgradepointsicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Upgrade Points - used to upgrade items"
        }else if (companionList.itemFragmentEnemyList[position].name == R.drawable.diamondicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Diamonds - secondary currency to buy items"
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
            companionList.toastText = "Magic Armor Penetration - rating to reduce enemies magic armor, making them more vulnerable to physical attacks"
        }
    }
}
