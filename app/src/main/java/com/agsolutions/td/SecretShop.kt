package com.agsolutions.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.secret_shop.*


class SecretShop : AppCompatActivity(), StartItemAdapter.OnClickListener, ItemFragmentAdapter.OnStatsClickListener {

    private val adapter = StartItemAdapter(GameActivity.companionList.secretShopList, this) {

    }
    private val showAdapter = ItemFragmentAdapter(GameActivity.companionList.itemListSecretShop, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secret_shop)

        var sid4 = Items(1004, 1, 999,0,0f, 0, 0f, 1,"Particle Collector", R.drawable.particlered, R.drawable.overlaytransparent, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0, "Free Slot, collects excess dmg", 0f, "", 0f)
        var sid5 = Items(1005, 1, 999,0,0f, 0, 0f, 1, "True Sniper", R.drawable.sniperred, R.drawable.overlaytransparent,((1.0f * GameActivity.companionList.lvlScaler) + (GameActivity.companionList.level * 0.15f)),0.0f,0.0f, 0f, 0f, 0f, 0, "snipes a single target with bonus dmg for distance", 0.0f, "", 0f)
        var sid6 = Items(1006, 1, 999,0,0f, 0, 0f, 1, "Fast Draw", R.drawable.itemadditionalbulletred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, ((1.0f * GameActivity.companionList.lvlScaler) + (GameActivity.companionList.level * 0.15f)), 0f, 0, "each multicrit spawns one additional bullet", 0.0f, "", 0f)
        var sid7 = Items(1007, 1, 999,0,0f, 0, 0f, 1, "Helping Hand", R.drawable.helpinghandred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "Free Slot. +30% DMG for 5 sec. Cost: 10 IP. 1 min CD.", 0.0f, "", 0f)
        var sid8 = Items(1008, 1, 999,0,0f, 0, 0f, 1, "Boring", R.drawable.itemboringred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "+20% DMG & SPD; cannot CRIT", 0.0f, "", 0f)

        var secretShopRandomList = mutableListOf<Items>(sid4, sid5, sid6, sid7, sid8)

        window.setLayout((600.0f * (GameActivity.companionList.scaleScreen / 10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) / 10)).toInt())
        window.setElevation(10F)

        textView6.text = "Secret Shop"

        if (GameActivity.companionList.secretShopIconBool) {
            GameActivity.companionList.secretShopIconBool = false
            GameActivity.companionList.secretShopList.removeAll(GameActivity.companionList.secretShopList)

            GameActivity.companionList.secretShopList.add(Items(6, 1, 999, 0, 0f, 0, 0f, 1, "Gold Digger", R.drawable.goldgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ Gold", ((8 * GameActivity.companionList.lvlXp) * 0.25f), "", 0f))
            GameActivity.companionList.secretShopList.add(Items(8, 1, 999, 0, 0f, 0, 0f, 1, "Shiny Thing", R.drawable.diamondgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ DIA", 1f, "", 0f))

            var x = 0
            while (x < 2) {
                var item = secretShopRandomList.random()
                if (GameActivity.companionList.secretShopList.contains(item)) {
                } else {
                    GameActivity.companionList.secretShopList.add(item)
                    x++
                }
            }
        }

        recyclerSecretShop.adapter = adapter
        recyclerSecretShop.layoutManager =
            GridLayoutManager(this, 4)
        recyclerSecretShop.setHasFixedSize(true)

        showItemStatsRecycler.adapter = showAdapter
        showItemStatsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        showItemStatsRecycler.setHasFixedSize(true)

        ipShowTV.text = GameActivity.companionList.itemPoints.toInt().toString()
        mpShowTV.text = GameActivity.companionList.mysteryPoints.toString()

        buyBTN.visibility = View.INVISIBLE

        exitBTN.setOnClickListener() {
            GameActivity.paused = false
            finish()
        }
    }
    override fun onBackPressed() {
    }


    override fun onClick(position: Int) {

        buyBTN.visibility = View.INVISIBLE

        GameActivity.companionList.itemListSecretShop.removeAll(GameActivity.companionList.itemListSecretShop)


        GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.secretShopList[position].name.toString()))
        if (GameActivity.companionList.secretShopList[position].mpCost > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.mpicon, GameActivity.companionList.secretShopList[position].mpCost.toString()))
        if (GameActivity.companionList.secretShopList[position].ipCost > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.itempointsicon, GameActivity.companionList.secretShopList[position].ipCost.round(2)
            .toString()))
        if (GameActivity.companionList.secretShopList[position].upgrade > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.upgradepointsicon, GameActivity.companionList.secretShopList[position].upgrade.toString()))
        if (GameActivity.companionList.secretShopList[position].dmg > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.secretShopList[position].dmg.round(2).toString()))
        if (GameActivity.companionList.secretShopList[position].crit > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.secretShopList[position].crit.round(2).toString()))
        if (GameActivity.companionList.secretShopList[position].critDmg > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.secretShopList[position].critDmg.round(2).toString()))
        if (GameActivity.companionList.secretShopList[position].speed > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.secretShopList[position].speed.round(2).toString()))
        if (GameActivity.companionList.secretShopList[position].special.isNotBlank()) {
            GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.secretShopList[position].special.toString()))
           if (GameActivity.companionList.secretShopList[position].specialFloat != 0f) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.secretShopList[position].specialFloat.round(2).toString()))
        }
        if (GameActivity.companionList.secretShopList[position].special2.isNotBlank()) {
            GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.secretShopList[position].special2.toString()))
            if (GameActivity.companionList.secretShopList[position].specialFloat2 != 0f) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.secretShopList[position].specialFloat2.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()

        if (GameActivity.companionList.secretShopList[position].mpCost <= GameActivity.companionList.mysteryPoints && GameActivity.companionList.secretShopList[position].ipCost <= GameActivity.companionList.itemPoints) buyBTN.visibility = View.VISIBLE

        buyBTN.setOnClickListener() {

            GameActivity.companionList.mysteryPoints -= GameActivity.companionList.secretShopList[position].mpCost
            GameActivity.companionList.itemPoints -= GameActivity.companionList.secretShopList[position].ipCost
            GameActivity.companionList.itemListInsertItem.add( GameActivity.companionList.secretShopList[position])
            GameActivity.companionList.secretShopList.removeAll(GameActivity.companionList.secretShopList)
            GameActivity.paused = false
            finish()
        }

        exitBTN.setOnClickListener() {
            GameActivity.companionList.secretShopList.removeAll(GameActivity.companionList.secretShopList)
            GameActivity.paused = false
            finish()
        }
    }

    override fun onStatsClick(position: Int) {
        if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.nameicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Item Name"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.swordandwandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Damage - bonus physical and spell damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.swordicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Physical Damage - bonus physical damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.wandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Spell Damage - bonus spell damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.bowicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Attack Speed - frequency of tower attacks"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.specialicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Special Attributes"
        }
    }


}

class Shop : AppCompatActivity(), StartItemAdapter.OnClickListener, ItemFragmentAdapter.OnStatsClickListener {

    private val adapter = StartItemAdapter(GameActivity.companionList.shopList, this) {

    }
    private val showAdapter = ItemFragmentAdapter(GameActivity.companionList.itemListSecretShop, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secret_shop)

        window.setLayout((600.0f * (GameActivity.companionList.scaleScreen / 10)).toInt(), (1000.0f * ((GameActivity.companionList.scaleScreen) / 10)).toInt())
        window.setElevation(10F)

        textView6.text = "Shop"

        GameActivity.companionList.shopList.removeAll(GameActivity.companionList.shopList)

        GameActivity.companionList.shopList.add(Items(6, 1, 999, 0, 0f, 0, 10f, 0, "Gold Digger", R.drawable.goldgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ Gold", ((4 * GameActivity.companionList.lvlXp)), "", 0f))
        GameActivity.companionList.shopList.add(Items(8, 1, 999, 0, 0f, 0, 20f, 0, "Shiny Thing", R.drawable.diamondgreen, R.drawable.overlaytransparent, 0.0f, 0.0f, 0.0f, 0.0f, 0f, 0.0f, 0, "+ DIA", 1f, "", 0f))

        GameActivity.companionList.shopList.add(Items(1000, 1, 999,0,0f, 0, 10f, 0,"Cannon", R.drawable.canongrey, R.drawable.overlaytransparent,5.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f))
        GameActivity.companionList.shopList.add(Items(1001, 1, 999,0,0f, 0, 25f, 0,"Rare Cannon", R.drawable.canonblue, R.drawable.overlaytransparent,25.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f))
        GameActivity.companionList.shopList.add(Items(1002, 1, 999,0,0f, 0, 50f, 0,"Epic Cannon", R.drawable.canonorange, R.drawable.overlaytransparent,75.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f))
        GameActivity.companionList.shopList.add(Items(1003, 1, 999,0,0f, 0, 100f, 0,"Legendary Cannon", R.drawable.canonpurple, R.drawable.overlaytransparent,200.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "", 0f, "", 0f))


        recyclerSecretShop.adapter = adapter
        recyclerSecretShop.layoutManager =
            GridLayoutManager(this, 4)
        recyclerSecretShop.setHasFixedSize(true)

        showItemStatsRecycler.adapter = showAdapter
        showItemStatsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        showItemStatsRecycler.setHasFixedSize(true)

        ipShowTV.text = GameActivity.companionList.itemPoints.toInt().toString()
        mpShowTV.text = GameActivity.companionList.mysteryPoints.toString()

        buyBTN.visibility = View.INVISIBLE

        exitBTN.setOnClickListener() {
            GameActivity.paused = false
            finish()
        }
    }
    override fun onBackPressed() {
    }


    override fun onClick(position: Int) {

        buyBTN.visibility = View.INVISIBLE

        GameActivity.companionList.itemListSecretShop.removeAll(GameActivity.companionList.itemListSecretShop)


        GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.shopList[position].name.toString()))
        if (GameActivity.companionList.shopList[position].mpCost > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.mpicon, GameActivity.companionList.shopList[position].mpCost.toString()))
        if (GameActivity.companionList.shopList[position].ipCost > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.itempointsicon, GameActivity.companionList.shopList[position].ipCost.round(2)
            .toString()))
        if (GameActivity.companionList.shopList[position].upgrade > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.upgradepointsicon, GameActivity.companionList.shopList[position].upgrade.toString()))
        if (GameActivity.companionList.shopList[position].dmg > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.shopList[position].dmg.round(2).toString()))
        if (GameActivity.companionList.shopList[position].crit > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.shopList[position].crit.round(2).toString()))
        if (GameActivity.companionList.shopList[position].critDmg > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.shopList[position].critDmg.round(2).toString()))
        if (GameActivity.companionList.shopList[position].speed > 0) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.shopList[position].speed.round(2).toString()))
        if (GameActivity.companionList.shopList[position].special.isNotBlank()) {
            GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.shopList[position].special.toString()))
            if (GameActivity.companionList.shopList[position].specialFloat != 0f) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.shopList[position].specialFloat.round(2).toString()))
        }
        if (GameActivity.companionList.shopList[position].special2.isNotBlank()) {
            GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.shopList[position].special2.toString()))
            if (GameActivity.companionList.shopList[position].specialFloat2 != 0f) GameActivity.companionList.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.shopList[position].specialFloat2.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()

        if (GameActivity.companionList.shopList[position].mpCost <= GameActivity.companionList.mysteryPoints && GameActivity.companionList.shopList[position].ipCost <= GameActivity.companionList.itemPoints) buyBTN.visibility = View.VISIBLE

        buyBTN.setOnClickListener() {

            GameActivity.companionList.mysteryPoints -= GameActivity.companionList.shopList[position].mpCost
            GameActivity.companionList.itemPoints -= GameActivity.companionList.shopList[position].ipCost
            GameActivity.companionList.itemListInsertItem.add(0, GameActivity.companionList.shopList[position])
            GameActivity.companionList.shopList.removeAll(GameActivity.companionList.shopList)
            GameActivity.paused = false
            finish()
        }

        exitBTN.setOnClickListener() {
            GameActivity.companionList.shopList.removeAll(GameActivity.companionList.shopList)
            GameActivity.paused = false
            finish()
        }
    }

    override fun onStatsClick(position: Int) {
        if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.nameicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Item Name"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.swordandwandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Damage - bonus physical and spell damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.swordicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Physical Damage - bonus physical damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.wandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Spell Damage - bonus spell damage"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.bowicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Attack Speed - frequency of tower attacks"
        }else if (GameActivity.companionList.itemListSecretShop[position].name == R.drawable.specialicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Special Attributes"
        }
    }


}