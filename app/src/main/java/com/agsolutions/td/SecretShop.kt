package com.agsolutions.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.itemListSecretShop
import com.agsolutions.td.Companion.Companion.itemPoints
import com.agsolutions.td.Companion.Companion.mysteryPoints
import com.agsolutions.td.GameActivity.PlayPause.paused
import com.agsolutions.td.Items.Companion.secretShopList
import com.agsolutions.td.Items.Companion.secretShopListBase
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.secret_shop.*


class SecretShop : AppCompatActivity(), StartItemAdapter.OnClickListener, ItemFragmentAdapter.OnStatsClickListener {

    private val adapter = StartItemAdapter(secretShopList, this) {

    }
    private val showAdapter = ItemFragmentAdapter(itemListSecretShop, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secret_shop)

        var sid4 = Items(1004, 1, 999,0,0f, 0, 0f, 1,"Particle Collector", R.drawable.particlered, R.drawable.overlaytransparent, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0, "Free Slot, collects excess dmg", 0f, "", 0f)
        var sid5 = Items(1005, 1, 999,0,0f, 0, 0f, 1, "True Sniper", R.drawable.sniperred, R.drawable.overlaytransparent,((1.0f * com.agsolutions.td.Companion.lvlScaler) + (Companion.level * 0.15f)),0.0f,0.0f, 0f, 0f, 0f, 0, "snipes a single target with bonus dmg for distance", 0.0f, "", 0f)
        var sid6 = Items(1006, 1, 999,0,0f, 0, 0f, 1, "Fast Draw", R.drawable.itemadditionalbulletred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, ((1.0f * com.agsolutions.td.Companion.lvlScaler) + (Companion.level * 0.15f)), 0f, 0, "each multicrit spawns one additional bullet", 0.0f, "", 0f)
        var sid7 = Items(1007, 1, 999,0,0f, 0, 0f, 1, "Helping Hand", R.drawable.helpinghandred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "Free Slot. +30% DMG for 5 sec. Cost: 10 IP. 1 min CD.", 0.0f, "", 0f)
        var sid8 = Items(1008, 1, 999,0,0f, 0, 0f, 1, "Boring", R.drawable.itemboringred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0f, 0f, 0, "+20% DMG & SPD; cannot CRIT", 0.0f, "", 0f)

        var secretShopRandomList = mutableListOf<Items>(sid4, sid5, sid6, sid7, sid8)

        window.setLayout((600.0f * (Companion.scaleScreen / 10)).toInt(), (1000.0f * ((Companion.scaleScreen) / 10)).toInt())
        window.setElevation(10F)

        secretShopList.removeAll(secretShopList)

        secretShopList.addAll(secretShopListBase)
        var x = 0
        while( x < 2){
            var item = secretShopRandomList.random()
            if (secretShopList.contains(item)) {
            }else {
                secretShopList.add(item)
                x++
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

        ipShowTV.text = itemPoints.toInt().toString()
        mpShowTV.text = mysteryPoints.toString()

        buyBTN.visibility = View.INVISIBLE

        exitBTN.setOnClickListener() {
            paused = false
            finish()
        }
    }
    override fun onBackPressed() {
    }


    override fun onClick(position: Int) {

        buyBTN.visibility = View.INVISIBLE

        itemListSecretShop.removeAll(itemListSecretShop)


        com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.nameicon, secretShopList[position].name.toString()))
        if (secretShopList[position].mpCost > 0) Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.mpicon, secretShopList[position].mpCost.toString()))
        if (secretShopList[position].ipCost > 0) Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.itempointsicon, secretShopList[position].ipCost.round(2)
            .toString()))
        if (secretShopList[position].upgrade > 0) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.upgradepointsicon, secretShopList[position].upgrade.toString()))
        if (secretShopList[position].dmg > 0) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.swordandwandicon, secretShopList[position].dmg.round(2).toString()))
        if (secretShopList[position].crit > 0) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.criticon, secretShopList[position].crit.round(2).toString()))
        if (secretShopList[position].critDmg > 0) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.critdmgicon, secretShopList[position].critDmg.round(2).toString()))
        if (secretShopList[position].speed > 0) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.bowicon, secretShopList[position].speed.round(2).toString()))
        if (secretShopList[position].special.isNotBlank()) {
            com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, secretShopList[position].special.toString()))
           if (secretShopList[position].specialFloat != 0f) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, secretShopList[position].specialFloat.round(2).toString()))
        }
        if (secretShopList[position].special2.isNotBlank()) {
            com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, secretShopList[position].special2.toString()))
            if (secretShopList[position].specialFloat2 != 0f) com.agsolutions.td.Companion.itemListSecretShop.add(ItemFragmentStrings(R.drawable.specialicon, secretShopList[position].specialFloat2.round(2).toString()))
        }

        showAdapter.notifyDataSetChanged()

        if (secretShopList[position].mpCost <= mysteryPoints && secretShopList[position].ipCost <= itemPoints) buyBTN.visibility = View.VISIBLE

        buyBTN.setOnClickListener() {

            mysteryPoints -= secretShopList[position].mpCost
            itemPoints -= secretShopList[position].ipCost
            itemList.add(0, secretShopList[position])
            StartItems.startItems += 1
            secretShopList.removeAll(secretShopList)
            paused = false
            finish()
        }

        exitBTN.setOnClickListener() {
            secretShopList.removeAll(secretShopList)
            paused = false
            finish()
        }
    }

    override fun onStatsClick(position: Int) {
        if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.nameicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Name"
        }else if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.swordandwandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Damage - bonus physical and spell damage"
        }else if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.swordicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Physical Damage - bonus physical damage"
        }else if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.wandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Spell Damage - bonus spell damage"
        }else if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.bowicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Attack Speed - frequency of tower attacks"
        }else if (com.agsolutions.td.Companion.itemListSecretShop[position].name == R.drawable.specialicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Special Attributes"
        }
    }


}