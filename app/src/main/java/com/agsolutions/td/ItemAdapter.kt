package com.agsolutions.td

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.ActiveAbility.Companion.aAid1
import com.agsolutions.td.GameActivity.Companion.companionList
import kotlinx.android.synthetic.main.item.view.*

class ItemAdapter (
    private val itemList: MutableList<Items>,
    private val listener: OnClickListener
  //  private var callback:()->Unit
    ) :
    RecyclerView.Adapter<ItemAdapter.ExampleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
                parent, false)

            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = itemList[position]
            holder.imageView.setImageResource(currentItem.image)
            holder.imageViewOverlay.setImageResource(currentItem.imageOverlay)

        }

    fun deleteItem (pos: Int) {
        if (companionList.itemList[pos].id == 2100 || companionList.itemList[pos].id == 2101 || companionList.itemList[pos].id == 2102 || companionList.itemList[pos].id == 2103 || companionList.itemList[pos].id == 2104 || companionList.itemList[pos].id == 2105 || companionList.itemList[pos].id == 2106 || companionList.itemList[pos].id == 2107 || companionList.itemList[pos].id == 2108 || companionList.itemList[pos].id == 2109 ||
            companionList.itemList[pos].id == 2200 || companionList.itemList[pos].id == 2201 || companionList.itemList[pos].id == 2202 || companionList.itemList[pos].id == 2203 || companionList.itemList[pos].id == 2204 || companionList.itemList[pos].id == 2205 || companionList.itemList[pos].id == 2206 || companionList.itemList[pos].id == 2207 || companionList.itemList[pos].id == 2208 || companionList.itemList[pos].id == 2209 ||
            companionList.itemList[pos].id == 2300 || companionList.itemList[pos].id == 2301 || companionList.itemList[pos].id == 2302 || companionList.itemList[pos].id == 2303 || companionList.itemList[pos].id == 2304 || companionList.itemList[pos].id == 2305 || companionList.itemList[pos].id == 2306 || companionList.itemList[pos].id == 2307 || companionList.itemList[pos].id == 2308 || companionList.itemList[pos].id == 2309) {
            if (!companionList.day && companionList.moonTalentItemCost > 0) companionList.gold -= (companionList.itemList[pos].goldCost - (companionList.itemList[pos].goldCost * companionList.moonTalentItemCost))
            else if (companionList.midnightMadnessMidasGoldCost > 0) companionList.gold -= (companionList.itemList[pos].goldCost + (companionList.itemList[pos].goldCost * companionList.midnightMadnessMidasGoldCost))
            else companionList.gold -= companionList.itemList[pos].goldCost
            companionList.diamonds -= companionList.itemList[pos].diaCost

            companionList.itemList[pos].goldCost = 0f
            companionList.itemList[pos].diaCost = 0

            if (companionList.level == 0) companionList.buildListBag.add(0, companionList.itemList[pos])
            else companionList.buildListBag.add(1, companionList.itemList[pos])
            companionList.insertItemBag += 1

            companionList.fragmentItemCurrentItem = -1

            companionList.itemList.removeAt(pos)
            notifyItemRemoved(pos)
        } else {

            if (companionList.towerClick) {

                    var it = companionList.towerList[companionList.towerClickID]

                        if (!companionList.day && companionList.moonTalentItemCost > 0) companionList.gold -= (companionList.itemList[pos].goldCost - (companionList.itemList[pos].goldCost * companionList.moonTalentItemCost))
                        else if (companionList.midnightMadnessMidasGoldCost > 0) companionList.gold -= (companionList.itemList[pos].goldCost + (companionList.itemList[pos].goldCost * companionList.midnightMadnessMidasGoldCost))
                        else companionList.gold -= companionList.itemList[pos].goldCost
                        companionList.diamonds -= companionList.itemList[pos].diaCost

                        companionList.itemList[pos].goldCost = 0f
                        companionList.itemList[pos].diaCost = 0

                        statsAdd(pos, it, false)

                        if ((companionList.itemList[pos] == companionList.eearth || companionList.itemList[pos] == companionList.ebutterfly || companionList.itemList[pos] == companionList.ewind || companionList.itemList[pos] == companionList.emoon)) {
                            if (it.itemListBag.contains(companionList.eearth) || it.itemListBag.contains(companionList.ebutterfly) || it.itemListBag.contains(companionList.ewind) || it.itemListBag.contains(companionList.emoon) ){

                            }else {
                                when (companionList.itemList[pos]){
                                    companionList.eearth -> it.towerPrimaryElement = "earth"
                                    companionList.ebutterfly -> it.towerPrimaryElement = "butterfly"
                                    companionList.ewind -> it.towerPrimaryElement = "wind"
                                    companionList.emoon -> it.towerPrimaryElement = "moon"
                                }
                            }
                        }
                        if ((companionList.itemList[pos] == companionList.eearth || companionList.itemList[pos] == companionList.ebutterfly || companionList.itemList[pos] == companionList.ewind || companionList.itemList[pos] == companionList.emoon || companionList.itemList[pos] == companionList.epoison ||
                                    companionList.itemList[pos] == companionList.eice || companionList.itemList[pos] == companionList.efire || companionList.itemList[pos] == companionList.edark || companionList.itemList[pos] == companionList.eutils || companionList.itemList[pos] == companionList.ewizard)) {
                                        it.talentPoints++
                                        companionList.itemList[pos].element = true
                        }

                        companionList.fragmentItemCurrentItem = -1

                        if (companionList.itemList[pos].id == 306 || companionList.itemList[pos].id == 5 || companionList.itemList[pos].id == 6 || companionList.itemList[pos].id == 7 || companionList.itemList[pos].id == 8 || companionList.itemList[pos].id == 9 || companionList.itemList[pos].id == 106 || companionList.itemList[pos].id == 313 || companionList.itemList[pos].id == 107 || companionList.itemList[pos].id == 5010 || companionList.itemList[pos].id == 5014) {
                        } else {
                            if (it.bagSizeElementCount == 0) it.itemListBag.add(0, companionList.itemList[pos])
                            else it.itemListBag.add(1, companionList.itemList[pos])
                            companionList.insertItemBag += 1
                        }

                        companionList.itemList.removeAt(pos)
                        notifyDataSetChanged()
            } else {
                if (companionList.itemList[pos].id == 5) {
                    if (companionList.activeAbilityList.contains(aAid1)) {
                        companionList.bombActiveAbility += 1
                        companionList.insertSpellBomb += 1
                    } else {
                        companionList.activeAbilityList.add(0, aAid1)
                        companionList.insertSpell += 1
                        companionList.insertSpellBomb += 1
                        companionList.bombActiveAbility += 1
                    }
                    if (companionList.bombCost) companionList.gold += (companionList.itemList[pos].goldCost * 0.5f)

                    companionList.itemList.removeAt(pos)
                    notifyDataSetChanged()
                }
            }
        }
    }

    fun statsAdd (pos: Int, it: Tower, midnightMadness: Boolean){
        var itemListPos = if (midnightMadness) it.itemListBag else companionList.itemList

        itemListPos[pos].speed *= it.towerRarityMultiplier
        it.bonusTowerSpeed += itemListPos[pos].speed
        itemListPos[pos].dmg *= it.towerRarityMultiplier
        it.bonusTowerDmg += itemListPos[pos].dmg
        itemListPos[pos].atkDmg *= it.towerRarityMultiplier
        it.bonusPhysicalDmg += itemListPos[pos].atkDmg
        itemListPos[pos].mgcDmg *= it.towerRarityMultiplier
        it.bonusSpellDamage += itemListPos[pos].mgcDmg
        itemListPos[pos].crit *= it.towerRarityMultiplier
        it.bonusCrit += itemListPos[pos].crit
        itemListPos[pos].critDmg *= it.towerRarityMultiplier
        it.bonusCritDmg += itemListPos[pos].critDmg
        itemListPos[pos].itemChance *= it.towerRarityMultiplier
        it.bonusItemChance += itemListPos[pos].itemChance
        itemListPos[pos].itemQuality *= it.towerRarityMultiplier
        it.bonusItemQuality += itemListPos[pos].itemQuality
        itemListPos[pos].xpGain *= it.towerRarityMultiplier
        it.bonusXpMultiplier += itemListPos[pos].xpGain
        itemListPos[pos].goldIncome *= it.towerRarityMultiplier
        it.bonusGoldMultiplier += itemListPos[pos].goldIncome

        if (itemListPos[pos].id == 3 || itemListPos[pos].id == 101 || itemListPos[pos].id == 201 || itemListPos[pos].id == 301) companionList.magicBoxCount += 1
        if (itemListPos[pos].id == 0 || itemListPos[pos].id == 100 || itemListPos[pos].id == 200 || itemListPos[pos].id == 300) companionList.maceCount += 1
        if (itemListPos[pos].id == 1 || itemListPos[pos].id == 102 || itemListPos[pos].id == 202 || itemListPos[pos].id == 302) companionList.bowCount += 1
        if (itemListPos[pos].id == 2 || itemListPos[pos].id == 103 || itemListPos[pos].id == 203 || itemListPos[pos].id == 303) companionList.swordCount += 1

        if (itemListPos[pos].id == 4) {
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemChance += itemListPos[pos].specialFloat2
        }
        if (itemListPos[pos].id == 5) {
            if (companionList.activeAbilityList.contains(aAid1)) {
                companionList.bombActiveAbility += 1
                companionList.insertSpellBomb += 1
            } else {
                companionList.activeAbilityList.add(0, aAid1)
                companionList.insertSpell += 1
                companionList.insertSpellBomb += 1
                companionList.bombActiveAbility += 1
            }
            if (companionList.bombCost) companionList.gold += (itemListPos[pos].goldCost * 0.5f)
        }
        if (itemListPos[pos].id == 6) {
            companionList.gold += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 7) {
            companionList.upgradePoints++
        }
        if (itemListPos[pos].id == 8) {
            companionList.diamonds++
        }
        if (itemListPos[pos].id == 9) {
            it.xpTower += itemListPos[pos].specialFloat
        }

        if ((itemListPos[pos].id == 100 || itemListPos[pos].id == 102 || itemListPos[pos].id == 103) && itemListPos[pos].special2 == "item find") {
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemChance += itemListPos[pos].specialFloat2
        }
        if ((itemListPos[pos].id == 100 || itemListPos[pos].id == 102 || itemListPos[pos].id == 103) && itemListPos[pos].special2 == "item quality") {
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemQuality += itemListPos[pos].specialFloat2
        }
        if (itemListPos[pos].id == 104 || itemListPos[pos].id == 204) {
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemQuality += itemListPos[pos].specialFloat2
        }
        if (itemListPos[pos].id == 106) {
            if (companionList.mapMode != 2) companionList.lives += 1
            else companionList.livesMode2 += 1
        }
        if (itemListPos[pos].id == 107) {
            it.talentPoints += 1
        }

        if ((itemListPos[pos].id == 200 || itemListPos[pos].id == 202 || itemListPos[pos].id == 203) && itemListPos[pos].special == "armor penetration"){
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusArmorPen += itemListPos[pos].specialFloat
        }
        if ((itemListPos[pos].id == 200 || itemListPos[pos].id == 202 || itemListPos[pos].id == 203) && itemListPos[pos].special == "magic penetration") {
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusMagicPen += itemListPos[pos].specialFloat
        }
        if ((itemListPos[pos].id == 200 || itemListPos[pos].id == 202 || itemListPos[pos].id == 203) && itemListPos[pos].special == "hit chance"){
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.itemBonusHitChance += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 207 && itemListPos[pos].special == "+ X dmg/round") {
            it.itemPikaDmg = itemListPos[pos].specialFloat
            itemListPos[pos].specialFloat = 0f
        }
        if (itemListPos[pos].id == 207 && itemListPos[pos].special == "+ X spd/round") {
            it.itemPikaSpd = itemListPos[pos].specialFloat
            itemListPos[pos].specialFloat = 0f
        }
        if (itemListPos[pos].id == 207 && itemListPos[pos].special == "+ X crit/round") {
            it.itemPikaCrit = itemListPos[pos].specialFloat
            itemListPos[pos].specialFloat = 0f
        }
        if (itemListPos[pos].id == 208 || itemListPos[pos].id == 308) it.itemFrost += itemListPos[pos].specialFloat
        if (itemListPos[pos].id == 209) {
            companionList.interest += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 210 || itemListPos[pos].id == 309) it.itemLasso += itemListPos[pos].specialFloat.toInt()
        if (itemListPos[pos].id == 212){
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.itemBonusHitChance += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 213) it.itemLastStance += 0.1f
        if (itemListPos[pos].id == 214) it.itemSniper += 1
        if (itemListPos[pos].id == 215){
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusArmorPen += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 216){
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusMagicPen += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 217) {
            companionList.mysteryWokeCount += 1
            it.slowEach += 10.0f
        }
        if (itemListPos[pos].id == 218) it.bonusDamageMultiplyer += (itemListPos[pos].specialFloat * 0.01f)

        if ((itemListPos[pos].id == 300 || itemListPos[pos].id == 302 || itemListPos[pos].id == 303) && itemListPos[pos].special == "anti-heal") {
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusAntiHeal += itemListPos[pos].specialFloat / 100
        }
        if ((itemListPos[pos].id == 300 || itemListPos[pos].id == 302 || itemListPos[pos].id == 303) && itemListPos[pos].special == "extra dmg immune") {
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.bonusDmgImmune += itemListPos[pos].specialFloat / 100
        }
        if ((itemListPos[pos].id == 300 || itemListPos[pos].id == 302 || itemListPos[pos].id == 303) && itemListPos[pos].special2 == "item find"){
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemChance += itemListPos[pos].specialFloat2
        }
        if ((itemListPos[pos].id == 300 || itemListPos[pos].id == 302 || itemListPos[pos].id == 303) && itemListPos[pos].special2 == "item quality"){
            itemListPos[pos].specialFloat2 *= it.towerRarityMultiplier
            it.bonusItemQuality += itemListPos[pos].specialFloat2
        }
        if (itemListPos[pos].id == 304) it.slowAura += itemListPos[pos].specialFloat
        if (itemListPos[pos].id == 305) it.itemDisruptor += itemListPos[pos].specialFloat
        if (itemListPos[pos].id == 306) {
            it.bagSize += itemListPos[pos].specialFloat.toInt()
        }
        if (itemListPos[pos].id == 206) {
            it.talentPoints += itemListPos[pos].specialFloat.toInt()
        }
        if (itemListPos[pos].id == 307) {
            it.shotBounceTargets += itemListPos[pos].specialFloat.toInt()
        }
        if (itemListPos[pos].id == 310) it.bonusmultiCrit += itemListPos[pos].specialFloat.toInt()
        if (itemListPos[pos].id == 312) it.itemSlowDeath += itemListPos[pos].specialFloat
        if (itemListPos[pos].id == 313) {
            companionList.livesMpCounter = true
            if (companionList.mapMode != 2) companionList.lives += 5
            else companionList.livesMode2 += 5
        }
        if (itemListPos[pos].id == 314) {
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.armorPenPerHit += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 315) {
            itemListPos[pos].specialFloat *= it.towerRarityMultiplier
            it.magicPenPerHit += itemListPos[pos].specialFloat
        }
        if (itemListPos[pos].id == 316) it.bonusDamageMultiplyer += itemListPos[pos].specialFloat

        if (itemListPos[pos].id == 1004) it.particleDmgBool = true
        if (itemListPos[pos].id == 1005) {
            it.itemSniper += 1
            it.itemSniperPro = true
        }
        if (itemListPos[pos].id == 1006) it.itemFastDraw = true
        if (itemListPos[pos].id == 1007) {
            companionList.activeAbilityList.add(0, ActiveAbility.aAid2)
            companionList.insertSpell += 1
        }
        if (itemListPos[pos].id == 1008) {
            it.bonusDamageMultiplyer += 0.2f
            companionList.overallSpdMultiplier += 20
            it.itemBoring = true
        }

        if (itemListPos[pos].id == 1100 || itemListPos[pos].id == 1101 || itemListPos[pos].id == 1102 || itemListPos[pos].id == 1103 || itemListPos[pos].id == 1104 || itemListPos[pos].id == 1105 || itemListPos[pos].id == 1007) it.bagSize += 1
        if (itemListPos[pos].id == 1100) {
            it.towerBonusRange += 15
        }
        if (itemListPos[pos].id == 1104) {
            it.bonusDamageMultiplyer += 0.2f
            companionList.overallSpdMultiplier -= 10
        }
        if (itemListPos[pos].id == 1105) {
            it.bonusDamageMultiplyer -= 0.1f
            companionList.overallSpdMultiplier += 20
        }

        if (itemListPos[pos].id == 5003) it.bonusDamageMultiplyer += 0.1f
        if (itemListPos[pos].id == 5004) it.overallSpellDmgMultiplyer += 0.1f
        if (itemListPos[pos].id == 5005) it.bonusmultiCrit += 1
        if (itemListPos[pos].id == 5006) {
            it.slowAura += 10f
            it.bonusItemChance += 10f
        }
        if (itemListPos[pos].id == 5007) {
            it.bagSize += 2
            companionList.interest += 0.01f
            it.bonusItemChance += 10f
        }
        if (itemListPos[pos].id == 5008) companionList.shieldBrakerItem = 0
        if (itemListPos[pos].id == 5009) {
            it.poisonRow2Item1 = 1
            it.stackablePoison += 0.1f
            it.itemStartPoison = true
        }
        if (itemListPos[pos].id == 5010) {
            companionList.lvlHp *= 0.9f
        }
        if (itemListPos[pos].id == 5011) {
            companionList.activeAbilityList.add(0, ActiveAbility.aAid3)
            companionList.insertSpell += 1
        }
        if (itemListPos[pos].id == 5012) {
            if (companionList.activeAbilityList.contains(aAid1)) {
                companionList.bombActiveAbility += 5
                companionList.insertSpellBomb += 1
            } else {
                companionList.activeAbilityList.add(0, aAid1)
                companionList.insertSpell += 1
                companionList.insertSpellBomb += 1
                companionList.bombActiveAbility += 5
            }
            if (companionList.mapMode == 2) companionList.bombDamage += 0.025f
            else companionList.bombDamage += 0.1f
            companionList.bombCost = true
            companionList.mysteryWokeCount += 1
        }
        if (itemListPos[pos].id == 5013) {
            companionList.wiseMan = 1.1f
        }

        if (itemListPos[pos].id == 5015) {
            companionList.wizardBombStartItemDmg += 0.4f
            companionList.wizardLightningStartItemTargets += 1
        }

        if (itemListPos[pos].id == 5016) {
            it.shotBounceTargetsStartItems = 2
        }

        if (itemListPos[pos].id == 5017) {
            companionList.upgraderBool = true
            companionList.upgradePoints += 3
        }
        if (itemListPos[pos].id == 6666) {
            it.darkSoulCollector = true
        }
    }

        override fun getItemCount() = itemList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
            val imageView: ImageView = itemView.item_view
            val imageViewOverlay: ImageView = itemView.item_view_overlay

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
              val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClick(position)
                  }
        //    callback.invoke()
            }

        }
        interface OnClickListener {
            fun onClick(position: Int)
        }
    }

/*
holder.itemView.setOnLongClickListener  {
                val clipText = holder.adapterPosition.toString()
                val item = ClipData.Item (clipText)
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                val data = ClipData(clipText, mimeTypes, item)
                val dragShadowBuilder = View.DragShadowBuilder(it)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.startDragAndDrop(data, dragShadowBuilder,it, 0)
                }
                else it.startDrag(data, dragShadowBuilder, it, 0)
            }

 */

/*    val clipText = "This is our ClipData text"
               val item = ClipData.Item(clipText)
               val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
               val data = ClipData(clipText, mimeTypes, item)
               val dragShadowBuilder = View.DragShadowBuilder(it)

               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   it.startDragAndDrop(data, dragShadowBuilder, it, 0)
               }
               else it.startDrag(data, dragShadowBuilder, it, 0)
           } */