package com.agsolutions.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.scaleScreen
import com.agsolutions.td.GameActivity.PlayPause.paused
import com.agsolutions.td.UiViewStartItems.Companion.talentRecyclerX
import com.agsolutions.td.Utils.round
import kotlinx.android.synthetic.main.start_items.*


class StartItems : AppCompatActivity(), StartItemAdapter.OnClickListener, StartTowerAdapter.OnClickListener, ItemFragmentAdapter.OnStatsClickListener {
    companion object {
        var startItems = 0

    }
    private val adapter = StartItemAdapter(Items.startItemList, this) {

    }
    private val towerAdapter = StartTowerAdapter(Items.startTowerList, this) {

    }

    private val showAdapter = ItemFragmentAdapter(com.agsolutions.td.Companion.itemListStartItems, this)

    var clicks1 = false
    var clicks2 = false
    var position1 = 0
    var position2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_items)

        window.setLayout((600.0f * (com.agsolutions.td.Companion.scaleScreen /10)).toInt(), (1200.0f * (com.agsolutions.td.Companion.scaleScreen /10)).toInt())
        window.setElevation(10F)

        recyclerStartItem.adapter = adapter
        recyclerStartItem.layoutManager =
            GridLayoutManager(this, 3)
        recyclerStartItem.setHasFixedSize(true)

        recyclerStartTower.adapter = towerAdapter
        recyclerStartTower.layoutManager =
            GridLayoutManager(this, 3)
        recyclerStartTower.setHasFixedSize(true)

        showStartItemStatsRecycler.adapter = showAdapter
        showStartItemStatsRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        showStartItemStatsRecycler.setHasFixedSize(true)

        closeStartItemsBTN.visibility = View.INVISIBLE

        recyclerStartItem.doOnLayout {
            val posXY = IntArray(2)
            recyclerStartItem.getChildAt(0).getLocationInWindow(posXY)
            talentRecyclerX = posXY[0].toFloat()
            UiViewStartItems.talentRecyclerY = (posXY[1] + (50 * (scaleScreen / 10))).toFloat()
            uiViewStartItem.invalidate()
        }

        items()


    }
    override fun onBackPressed() {
    }

    private fun items() {

    }

    override fun onClick(position: Int) {

        Items.startItemList.forEach(){
            it.imageOverlay = R.drawable.overlaytransparent
        }

        Items.startItemList[position].imageOverlay = R.drawable.overlaypick
        adapter.notifyDataSetChanged()

        com.agsolutions.td.Companion.itemListStartItems.removeAll(com.agsolutions.td.Companion.itemListStartItems)

            com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.nameicon, Items.startItemList[position].name.toString()))
            if (Items.startItemList[position].dmg > 0) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, Items.startItemList[position].dmg.round(2)
                .toString()))
            if (Items.startItemList[position].crit > 0) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.criticon, Items.startItemList[position].crit.round(2)
                .toString()))
            if (Items.startItemList[position].critDmg > 0) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.critdmgicon, Items.startItemList[position].critDmg.round(2)
                .toString()))
            if (Items.startItemList[position].speed > 0) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.bowicon, Items.startItemList[position].speed.round(2)
                .toString()))
            if (Items.startItemList[position].special.isNotBlank()) {
                com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].special.toString()))
                if (Items.startItemList[position].specialFloat != 0f) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].specialFloat.round(2)
                    .toString()))
            }
            if (Items.startItemList[position].special2.isNotBlank()) {
                com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].special2.toString()))
                if (Items.startItemList[position].specialFloat2 != 0f) com.agsolutions.td.Companion.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, Items.startItemList[position].specialFloat2.round(2)
                    .toString()))
        }

        showAdapter.notifyDataSetChanged()

        clicks1 = true
        position1 = position
        if (clicks1 && clicks2) {
            closeStartItemsBTN.visibility = View.VISIBLE
            val posXY = IntArray(2)
            closeStartItemsBTN.getLocationInWindow(posXY)
            talentRecyclerX = posXY[0].toFloat()
            UiViewStartItems.talentRecyclerY = (posXY[1] + (50 * (scaleScreen / 10))).toFloat()
            uiViewStartItem.invalidate()


            closeStartItemsBTN.setOnClickListener() {
                itemList.add(0, Items.startItemList[position1])
                itemList.add(1, Items.startTowerList[position2])
                StartItems.startItems += 1
                paused = false
                finish()
            }
        }
    }

    override fun onStatsClick(position: Int) {
        if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.nameicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Item Name"
        }else if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.swordandwandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Damage - bonus physical and spell damage"
        }else if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.swordicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Physical Damage - bonus physical damage"
        }else if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.wandicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Spell Damage - bonus spell damage"
        }else if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.bowicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Attack Speed - frequency of tower attacks"
        }else if (com.agsolutions.td.Companion.itemListStartItems[position].name == R.drawable.specialicon) {
            com.agsolutions.td.Companion.toastGlobal = true
            com.agsolutions.td.Companion.toastText = "Special Attributes"
        }
    }

    override fun onTowerClick(position: Int) {

        Items.startTowerList.forEach(){
            it.imageOverlay = R.drawable.overlaytransparent
        }

        Items.startTowerList[position].imageOverlay = R.drawable.overlaypick
        towerAdapter.notifyDataSetChanged()

        clicks2 = true
        position2 = position
        if (clicks1 && clicks2) {
            closeStartItemsBTN.visibility = View.VISIBLE
            val posXY = IntArray(2)
            closeStartItemsBTN.getLocationInWindow(posXY)
            talentRecyclerX = posXY[0].toFloat()
            UiViewStartItems.talentRecyclerY = (posXY[1] + (50 * (scaleScreen / 10))).toFloat()
            uiViewStartItem.invalidate()


            closeStartItemsBTN.setOnClickListener() {
                itemList.add(0, Items.startItemList[position1])
                itemList.add(1, Items.startTowerList[position2])
                StartItems.startItems += 1
                paused = false
                finish()
            }
        }


    }


}