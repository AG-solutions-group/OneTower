package com.agsolutions.td

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.agsolutions.td.UiViewStartItems.Companion.talentRecyclerElementX
import com.agsolutions.td.UiViewStartItems.Companion.talentRecyclerPickX
import com.agsolutions.td.UiViewStartItems.Companion.talentRecyclerX
import com.agsolutions.td.Utils.round
import com.agsolutions.td.databinding.ActivityAboutBinding
import com.agsolutions.td.databinding.StartItemsBinding


class StartItems : AppCompatActivity(), StartItemAdapter.OnClickListener, StartTowerAdapter.OnClickListener, ItemFragmentAdapter.OnStatsClickListener {
    companion object {
        var startItems = 0

    }
    private val adapter = StartItemAdapter(GameActivity.companionList.startItemList, this) {

    }
    private val towerAdapter = StartTowerAdapter(GameActivity.companionList.startTowerList, this) {

    }

    private val showAdapter = ItemFragmentAdapter(GameActivity.companionList.itemListStartItems, this)

    private lateinit var binding: StartItemsBinding

    var clicks1 = false
    var clicks2 = false
    var position1 = 0
    var position2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.setLayout((600.0f * (GameActivity.companionList.scaleScreen /10)).toInt(), (1200.0f * (GameActivity.companionList.scaleScreen /10)).toInt())
        window.setElevation(10F)

        with(binding) {
            recyclerStartItem.adapter = adapter
            recyclerStartItem.layoutManager =
                GridLayoutManager(this@StartItems, 3)
            recyclerStartItem.setHasFixedSize(true)

            recyclerStartTower.adapter = towerAdapter
            recyclerStartTower.layoutManager =
                GridLayoutManager(this@StartItems, 3)
            recyclerStartTower.setHasFixedSize(true)

            showStartItemStatsRecycler.adapter = showAdapter

            val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0) {
                        return 2
                    } else {
                        if (GameActivity.companionList.itemListStartItems[position].stats.length > 10) return 2
                        else return 1
                    }
                }
            }

            val glm = GridLayoutManager(this@StartItems, 2)

            glm.spanSizeLookup = spanSizeLookup
            showStartItemStatsRecycler.layoutManager = glm

            closeStartItemsBTN.visibility = View.INVISIBLE

            recyclerStartItem.doOnLayout {
                val posXY = IntArray(2)
                recyclerStartItem.getChildAt(0).getLocationInWindow(posXY)
                talentRecyclerX = posXY[0].toFloat()
                UiViewStartItems.talentRecyclerY =
                    (posXY[1] + (50 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toFloat()
                uiViewStartItem.invalidate()
            }

            recyclerStartTower.doOnLayout {
                val posXY = IntArray(2)
                recyclerStartTower.getChildAt(0).getLocationInWindow(posXY)
                talentRecyclerElementX = posXY[0].toFloat()
                UiViewStartItems.talentRecyclerElementY =
                    (posXY[1] + (50 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toFloat()
                uiViewStartItem.invalidate()
            }
        }
    }

    override fun onClick(position: Int) {

        GameActivity.companionList.startItemList.forEach(){
            it.imageOverlay = R.drawable.overlaytransparent
        }

        GameActivity.companionList.startItemList[position].imageOverlay = R.drawable.overlaypick
        adapter.notifyDataSetChanged()

        GameActivity.companionList.itemListStartItems.removeAll(GameActivity.companionList.itemListStartItems)

        GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.startItemList[position].name.toString()))
            if (GameActivity.companionList.startItemList[position].dmg > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.startItemList[position].dmg.round(2)
                .toString()))
            if (GameActivity.companionList.startItemList[position].crit > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.startItemList[position].crit.round(2)
                .toString()))
            if (GameActivity.companionList.startItemList[position].critDmg > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.startItemList[position].critDmg.round(2)
                .toString()))
            if (GameActivity.companionList.startItemList[position].speed > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.startItemList[position].speed.round(2)
                .toString()))
            if (GameActivity.companionList.startItemList[position].special.isNotBlank()) {
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].special.toString()))
                if (GameActivity.companionList.startItemList[position].specialFloat != 0f) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].specialFloat.round(2)
                    .toString()))
            }
            if (GameActivity.companionList.startItemList[position].special2.isNotBlank()) {
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].special2.toString()))
                if (GameActivity.companionList.startItemList[position].specialFloat2 != 0f) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startItemList[position].specialFloat2.round(2)
                    .toString()))
        }

        showAdapter.notifyDataSetChanged()

        clicks1 = true
        talentRecyclerX = 0f

        with(binding) {
            uiViewStartItem.invalidate()
            position1 = position
            if (clicks1 && clicks2) {
                closeStartItemsBTN.visibility = View.VISIBLE
                val posXY = IntArray(2)
                closeStartItemsBTN.getLocationInWindow(posXY)
                talentRecyclerPickX = posXY[0].toFloat()
                UiViewStartItems.talentRecyclerPickY =
                    (posXY[1] + (50 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toFloat()
                uiViewStartItem.invalidate()

                closeStartItemsBTN.setOnClickListener() {
                    close()
                }
            }
        }
    }

    override fun onStatsClick(position: Int) {
        if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.nameicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Item Name"
        }else if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.swordandwandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Damage - bonus physical and spell damage"
        }else if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.swordicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Physical Damage - bonus physical damage"
        }else if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.wandicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Spell Damage - bonus spell damage"
        }else if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.bowicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Attack Speed - frequency of tower attacks"
        }else if (GameActivity.companionList.itemListStartItems[position].name == R.drawable.specialicon) {
            GameActivity.companionList.toastGlobal = true
            GameActivity.companionList.toastText = "Special Attributes"
        }
    }

    override fun onTowerClick(position: Int) {

        GameActivity.companionList.startTowerList.forEach(){
            it.imageOverlay = R.drawable.overlaytransparent
        }

        GameActivity.companionList.startTowerList[position].imageOverlay = R.drawable.overlaypick
        towerAdapter.notifyDataSetChanged()

        GameActivity.companionList.itemListStartItems.removeAll(GameActivity.companionList.itemListStartItems)

        GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.nameicon, GameActivity.companionList.startTowerList[position].name.toString()))
        when(GameActivity.companionList.startTowerList[position].id){
            2100 -> { //earth
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Easy"))
            }
            2101 -> { // wizard
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Easy"))
            }
            2102 -> { // ice
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Medium"))
            }
            2103 -> { // butterfly
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Medium"))
            }
            2104 -> { // poison
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Easy"))
            }
            2105 -> { // moon
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Easy"))
            }
            2106 -> { // wind
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Hard"))
            }
            2107 -> { // utils
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Extreme"))
            }
            2108 -> { // fire
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Medium"))
            }
            2109 -> { // dark
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Difficulty"))
                GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, "Hard"))
            }
        }
        if (GameActivity.companionList.startTowerList[position].dmg > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.swordandwandicon, GameActivity.companionList.startTowerList[position].dmg.round(2)
            .toString()))
        if (GameActivity.companionList.startTowerList[position].crit > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.criticon, GameActivity.companionList.startTowerList[position].crit.round(2)
            .toString()))
        if (GameActivity.companionList.startTowerList[position].critDmg > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.critdmgicon, GameActivity.companionList.startTowerList[position].critDmg.round(2)
            .toString()))
        if (GameActivity.companionList.startTowerList[position].speed > 0) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.bowicon, GameActivity.companionList.startTowerList[position].speed.round(2)
            .toString()))
        if (GameActivity.companionList.startTowerList[position].special.isNotBlank()) {
            GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startTowerList[position].special.toString()))
            if (GameActivity.companionList.startTowerList[position].specialFloat != 0f) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startTowerList[position].specialFloat.round(2)
                .toString()))
        }
        if (GameActivity.companionList.startTowerList[position].special2.isNotBlank()) {
            GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startTowerList[position].special2.toString()))
            if (GameActivity.companionList.startTowerList[position].specialFloat2 != 0f) GameActivity.companionList.itemListStartItems.add(ItemFragmentStrings(R.drawable.specialicon, GameActivity.companionList.startTowerList[position].specialFloat2.round(2)
                .toString()))
        }
        showAdapter.notifyDataSetChanged()

        clicks2 = true
        talentRecyclerElementX = 0f

        with(binding) {
            uiViewStartItem.invalidate()
            position2 = position
            if (clicks1 && clicks2) {
                closeStartItemsBTN.visibility = View.VISIBLE
                val posXY = IntArray(2)
                closeStartItemsBTN.getLocationInWindow(posXY)
                talentRecyclerPickX = posXY[0].toFloat()
                UiViewStartItems.talentRecyclerPickY =
                    (posXY[1] + (50 * ((GameActivity.companionList.scaleScreen / 10) * GameView.scaleFactor))).toFloat()
                uiViewStartItem.invalidate()


                closeStartItemsBTN.setOnClickListener() {
                    close()
                }
            }
        }
    }

    fun close (){
        GameActivity.companionList.startItemList[position1].imageOverlay = R.drawable.overlaytransparent
        GameActivity.companionList.startTowerList[position2].imageOverlay = R.drawable.overlaytransparent
        when (GameActivity.companionList.startTowerList[position2].id){
            2100 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerearthblue
            2101 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerwizardblue
            2102 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towericeblue
            2103 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerbutterflyblue
            2104 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerpoisonblue
            2105 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towermoonblue
            2106 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerwindblue
            2107 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerutilsblue
            2108 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerfireblue
            2109 -> GameActivity.companionList.startTowerList[position2].image = R.drawable.towerdarkblue
        }

        GameActivity.companionList.itemList.add(0, GameActivity.companionList.startItemList[position1])
        GameActivity.companionList.itemList.add(1, GameActivity.companionList.startTowerList[position2])

        StartItems.startItems += 1
        GameActivity.paused = false
        finish()
    }
}