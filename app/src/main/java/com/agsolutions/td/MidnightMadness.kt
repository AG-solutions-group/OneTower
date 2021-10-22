package com.agsolutions.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.game_end.endGameBTN
import kotlinx.android.synthetic.main.midnight_madness.*
import kotlinx.android.synthetic.main.mystery_message.titleMysteryTV

class MidnightMadness : AppCompatActivity(), ItemFragmentAdapter.OnStatsClickListener {

    private val showAdapterMM = ItemFragmentAdapter(GameActivity.companionList.itemListMidnightMadness, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.midnight_madness)

        titleMysteryTV.text = intent.getStringExtra("Title")
        descriptionTV.text = intent.getStringExtra("Description")
        titleTVX.setTextSize((companionList.scaleTextMain / companionList.screenDensity).toFloat())
        descriptionTV.setTextSize(( companionList.scaleTextMain / companionList.screenDensity).toFloat())
        titleMysteryTV.setTextSize(( companionList.scaleTextMain * 1.3f / companionList.screenDensity ).toFloat())


        if (titleMysteryTV.text == "Treasure Hunt") {
            window.setLayout((600.0f * ((companionList.scaleScreen) / 10)).toInt(), (1100.0f * ((companionList.scaleScreen) / 10)).toInt())
            window.setElevation(10F)
            imageView5.visibility = View.VISIBLE
            showItemStatsMMRecycler.visibility = View.VISIBLE

            var a = Items(1100, 1, 999,0,0f, 0, 0f, 0,"Pirate Parrot", R.drawable.pirateparrotred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot. +15 range.", 0f, "", 0f)
            var b = Items(1101, 1, 999,0,0f, 0, 0f, 0,"Pirate Flag", R.drawable.pirateflagred, R.drawable.overlaytransparent,((2.0f * companionList.lvlScaler) + (companionList.level * 0.075f)), 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
            var c = Items(1102, 1, 999,0,0f, 0, 0f, 0,"Pirate Hat", R.drawable.piratehatred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,((8.0f * companionList.lvlScaler) + (companionList.level * 0.075f)), 0.0f, 0.0f, 0, "Free Slot", 0f, "", 0f)
            var d = Items(1103, 1, 999,0,0f, 0, 0f, 0,"Pirate Hook", R.drawable.piratehookred, R.drawable.overlaytransparent,0.0f, 0.0f,0.0f,0.0f, ((6.0f * companionList.lvlScaler) + (companionList.level * 0.075f)), 0.0f, 0, "Free Slot", 0f, "", 0f)
            var e = Items(1104, 1, 999,0,0f, 0, 0f, 0,"Pirate Leg", R.drawable.piratelegred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0.0f, 0.0f, 0.0f, 0, "Free Slot; +20% DMG; -10% SPD", 0f, "", 0f)
            var f = Items(1105, 1, 999,0,0f, 0, 0f, 0,"Pirate Saber", R.drawable.piratesaberred, R.drawable.overlaytransparent,0f, 0.0f,0.0f,0f, 0.0f, 0.0f, 0, "Free Slot; -10% DMG; +20% SPD", 0f, "", 0f)

            var midnightMadnessList = mutableListOf<Items>(a, b, c, d, e, f)

            val mmItem: Items = midnightMadnessList.random()

            showItemStatsMMRecycler.adapter = showAdapterMM
            showItemStatsMMRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            showItemStatsMMRecycler.setHasFixedSize(true)

            companionList.itemListMidnightMadness.removeAll(companionList.itemListMidnightMadness)

            imageView5.setImageResource(mmItem.image)
            companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.nameicon, mmItem.name.toString()))
            if (mmItem.mpCost > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.mpicon, mmItem.mpCost.toString()))
            if (mmItem.ipCost > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.itempointsicon, mmItem.ipCost.round(2)
                .toString()))
            if (mmItem.upgrade > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.upgradepointsicon, mmItem.upgrade.toString()))
            if (mmItem.dmg > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.swordandwandicon, mmItem.dmg.round(2).toString()))
            if (mmItem.crit > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.criticon, mmItem.crit.round(2).toString()))
            if (mmItem.critDmg > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.critdmgicon, mmItem.critDmg.round(2).toString()))
            if (mmItem.speed > 0) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.bowicon, mmItem.speed.round(2).toString()))
            if (mmItem.special.isNotBlank()) {
                companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.specialicon, mmItem.special.toString()))
                if (mmItem.specialFloat != 0f) companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.specialicon, mmItem.specialFloat.round(2).toString()))
            }
            if (mmItem.special2.isNotBlank()) {
                companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.specialicon, mmItem.special2.toString()))
                if (mmItem.specialFloat2 != 0f)companionList.itemListMidnightMadness.add(ItemFragmentStrings(R.drawable.specialicon, mmItem.specialFloat2.round(2).toString()))
            }
            showAdapterMM.notifyDataSetChanged()

            companionList.itemListInsertItem.add( mmItem)

        }else {
            window.setLayout((600.0f * ((companionList.scaleScreen.toFloat()) / 10)).toInt(), (700.0f * ((companionList.scaleScreen.toFloat()) /10)).toInt())
            window.setElevation(10F)
        }

        endGameBTN.setOnClickListener() {
            GameActivity.paused = false
                finish()
        }
    }

    override fun onBackPressed() {
    }

    override fun onStatsClick(position: Int) {
        if (companionList.itemListMidnightMadness[position].name == R.drawable.nameicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Item Name"
        }else if (companionList.itemListMidnightMadness[position].name == R.drawable.swordandwandicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Damage - bonus physical and spell damage"
        }else if (companionList.itemListMidnightMadness[position].name == R.drawable.swordicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Physical Damage - bonus physical damage"
        }else if (companionList.itemListMidnightMadness[position].name == R.drawable.wandicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Spell Damage - bonus spell damage"
        }else if (companionList.itemListMidnightMadness[position].name == R.drawable.bowicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Attack Speed - frequency of tower attacks"
        }else if (companionList.itemListMidnightMadness[position].name == R.drawable.specialicon) {
            companionList.toastGlobal = true
            companionList.toastText = "Special Attributes"
        }
    }
}



