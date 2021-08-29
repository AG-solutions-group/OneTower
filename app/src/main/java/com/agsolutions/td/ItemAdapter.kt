package com.agsolutions.td

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.ActiveAbility.Companion.aAid1
import com.agsolutions.td.Companion.Companion.activeAbilityList
import com.agsolutions.td.Companion.Companion.bombActiveAbility
import com.agsolutions.td.Companion.Companion.bombCost
import com.agsolutions.td.Companion.Companion.bombDamage
import com.agsolutions.td.Companion.Companion.bowCount
import com.agsolutions.td.Companion.Companion.buildListBag
import com.agsolutions.td.Companion.Companion.gold
import com.agsolutions.td.Companion.Companion.insertItemBag
import com.agsolutions.td.Companion.Companion.interest
import com.agsolutions.td.Companion.Companion.itemBoring
import com.agsolutions.td.Companion.Companion.itemFastDraw
import com.agsolutions.td.Companion.Companion.itemSniperPro
import com.agsolutions.td.Companion.Companion.level
import com.agsolutions.td.Companion.Companion.lives
import com.agsolutions.td.Companion.Companion.livesMpCounter
import com.agsolutions.td.Companion.Companion.luckyCharmCount
import com.agsolutions.td.Companion.Companion.lvlHp
import com.agsolutions.td.Companion.Companion.maceCount
import com.agsolutions.td.Companion.Companion.magicBoxCount
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.pirateItemCount
import com.agsolutions.td.Companion.Companion.shieldBrakerItem
import com.agsolutions.td.Companion.Companion.swordCount
import com.agsolutions.td.Companion.Companion.towerClick
import com.agsolutions.td.Companion.Companion.upgradePoints
import com.agsolutions.td.Companion.Companion.wizardBombStartItemDmg
import com.agsolutions.td.Companion.Companion.wizardLightningStartItemTargets
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
        if (itemList[pos].id == 2100 || itemList[pos].id == 2101 || itemList[pos].id == 2102 || itemList[pos].id == 2103 || itemList[pos].id == 2104 || itemList[pos].id == 2105 || itemList[pos].id == 2106 || itemList[pos].id == 2107 || itemList[pos].id == 2108 || itemList[pos].id == 2109 ||
            itemList[pos].id == 2200 || itemList[pos].id == 2201 || itemList[pos].id == 2202 || itemList[pos].id == 2203 || itemList[pos].id == 2204 || itemList[pos].id == 2205 || itemList[pos].id == 2206 || itemList[pos].id == 2207 || itemList[pos].id == 2208 || itemList[pos].id == 2209 ||
            itemList[pos].id == 2300 || itemList[pos].id == 2301 || itemList[pos].id == 2302 || itemList[pos].id == 2303 || itemList[pos].id == 2304 || itemList[pos].id == 2305 || itemList[pos].id == 2306 || itemList[pos].id == 2307 || itemList[pos].id == 2308 || itemList[pos].id == 2309) {
            if (!Companion.day && Companion.moonTalentItemCost > 0) Companion.gold -= (itemList[pos].goldCost - (Companion.itemList[pos].goldCost * Companion.moonTalentItemCost))
            else if (Companion.midnightMadnessMidasGoldCost > 0) Companion.gold -= (itemList[pos].goldCost + (Companion.itemList[pos].goldCost * Companion.midnightMadnessMidasGoldCost))
            else Companion.gold -= itemList[pos].goldCost
            Companion.diamonds -= itemList[pos].diaCost

            if (level == 0) buildListBag.add(0, itemList[pos])
            else buildListBag.add(1, itemList[pos])
            insertItemBag += 1

            Companion.fragmentItemCurrentItem = -1

            itemList.removeAt(pos)
            notifyItemRemoved(pos)
        } else {

            if (towerClick) {

                var towerListIterator = Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var it = towerListIterator.next()
                    if (it.selected) {

                        if (!Companion.day && Companion.moonTalentItemCost > 0) Companion.gold -= (itemList[pos].goldCost - (Companion.itemList[pos].goldCost * Companion.moonTalentItemCost))
                        else if (Companion.midnightMadnessMidasGoldCost > 0) Companion.gold -= (itemList[pos].goldCost + (Companion.itemList[pos].goldCost * Companion.midnightMadnessMidasGoldCost))
                        else Companion.gold -= itemList[pos].goldCost

                        Companion.diamonds -= itemList[pos].diaCost
                        itemList[pos].speed *= it.towerRarityMultiplier
                        it.bonusTowerSpeed += itemList[pos].speed
                        itemList[pos].dmg *= it.towerRarityMultiplier
                        it.bonusTowerDmg += itemList[pos].dmg
                        itemList[pos].atkDmg *= it.towerRarityMultiplier
                        it.bonusPhysicalDmg += itemList[pos].atkDmg
                        itemList[pos].mgcDmg *= it.towerRarityMultiplier
                        it.bonusSpellDamage += itemList[pos].mgcDmg
                        itemList[pos].crit *= it.towerRarityMultiplier
                        it.bonusCrit += itemList[pos].crit
                        itemList[pos].critDmg *= it.towerRarityMultiplier
                        it.bonusCritDmg += itemList[pos].critDmg
                        if (itemList[pos].id == 3 || itemList[pos].id == 101 || itemList[pos].id == 201 || itemList[pos].id == 301) magicBoxCount += 1
                        if (itemList[pos].id == 0 || itemList[pos].id == 100 || itemList[pos].id == 200 || itemList[pos].id == 300) maceCount += 1
                        if (itemList[pos].id == 1 || itemList[pos].id == 102 || itemList[pos].id == 202 || itemList[pos].id == 302) bowCount += 1
                        if (itemList[pos].id == 2 || itemList[pos].id == 103 || itemList[pos].id == 203 || itemList[pos].id == 303) swordCount += 1
                        if (itemList[pos].id == 4 || itemList[pos].id == 104 || itemList[pos].id == 204) luckyCharmCount += 1

                        if (itemList[pos].id == 4) {
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemChance += itemList[pos].specialFloat2
                        }
                        if (itemList[pos].id == 5) {
                            if (activeAbilityList.contains(aAid1)) {
                                bombActiveAbility += 1
                                Companion.insertSpellBomb += 1
                            } else {
                                activeAbilityList.add(0, aAid1)
                                Companion.insertSpell += 1
                                Companion.insertSpellBomb += 1
                                bombActiveAbility += 1
                            }
                            if (bombCost) gold += (itemList[pos].goldCost * 0.5f)
                        }
                        if (itemList[pos].id == 6) {
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            gold += itemList[pos].specialFloat
                        }

                        if ((itemList[pos].id == 100 || itemList[pos].id == 102 || itemList[pos].id == 103) && itemList[pos].special2 == "item find") {
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemChance += itemList[pos].specialFloat2
                        }
                        if ((itemList[pos].id == 100 || itemList[pos].id == 102 || itemList[pos].id == 103) && itemList[pos].special2 == "item quality") {
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemQuality += itemList[pos].specialFloat2
                        }
                        if (itemList[pos].id == 104 || itemList[pos].id == 204) {
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemQuality += itemList[pos].specialFloat2
                        }
                        if (itemList[pos].id == 105) {
                            it.itemPiggyBank += 0.1f
                        }
                        if (itemList[pos].id == 106) {
                            if (mapMode != 2) lives += 1
                            else Companion.livesMode2 += 1
                        }
                        if (itemList[pos].id == 107) {
                            it.talentPoints += 1
                        }

                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "armor penetration"){
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.bonusArmorPen += itemList[pos].specialFloat
                        }
                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "magic penetration") {
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.bonusMagicPen += itemList[pos].specialFloat
                        }
                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "hit chance"){
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.itemBonusHitChance += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 207 && itemList[pos].special == "+ X dmg/round") {
                            it.itemPikaDmg = itemList[pos].specialFloat
                            itemList[pos].specialFloat = 0f
                        }
                        if (itemList[pos].id == 207 && itemList[pos].special == "+ X spd/round") {
                            it.itemPikaSpd = itemList[pos].specialFloat
                            itemList[pos].specialFloat = 0f
                        }
                        if (itemList[pos].id == 207 && itemList[pos].special == "+ X crit/round") {
                            it.itemPikaCrit = itemList[pos].specialFloat
                            itemList[pos].specialFloat = 0f
                        }
                        if (itemList[pos].id == 208 || itemList[pos].id == 308) it.itemFrost += itemList[pos].specialFloat
                        if (itemList[pos].id == 209) {
                            interest += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 210 || itemList[pos].id == 309) it.itemLasso += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 212){
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.itemBonusHitChance += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 213) it.itemLastStance += 0.1f
                        if (itemList[pos].id == 214) it.itemSniper += 1
                        if (itemList[pos].id == 215){
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.bonusArmorPen += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 216){
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.bonusMagicPen += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 217) it.slowEach += 10.0f

                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special == "anti-heal") it.bonusAntiHeal += itemList[pos].specialFloat / 100
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special == "extra dmg immune") it.bonusDmgImmune += itemList[pos].specialFloat / 100
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special2 == "item find"){
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemChance += itemList[pos].specialFloat2
                        }
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special2 == "item quality"){
                            itemList[pos].specialFloat2 *= it.towerRarityMultiplier
                            it.bonusItemQuality += itemList[pos].specialFloat2
                        }
                        if (itemList[pos].id == 304) it.slowAura += itemList[pos].specialFloat
                        if (itemList[pos].id == 305) it.itemDisruptor += itemList[pos].specialFloat
                        if (itemList[pos].id == 306) {
                            it.bagSize += itemList[pos].specialFloat.toInt()
                        }
                        if (itemList[pos].id == 206) {
                            it.talentPoints += itemList[pos].specialFloat.toInt()
                        }
                        if (itemList[pos].id == 307) {
                            it.shotBounceTargets += itemList[pos].specialFloat.toInt()
                        }
                        if (itemList[pos].id == 310) it.bonusmultiCrit += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 312) it.itemSlowDeath += itemList[pos].specialFloat
                        if (itemList[pos].id == 313) {
                            livesMpCounter = true
                            if (mapMode != 2) lives += 5
                            else Companion.livesMode2 += 5
                        }
                        if (itemList[pos].id == 314) {
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.armorPenPerHit += itemList[pos].specialFloat
                        }
                        if (itemList[pos].id == 315) {
                            itemList[pos].specialFloat *= it.towerRarityMultiplier
                            it.magicPenPerHit += itemList[pos].specialFloat
                        }

                        if (itemList[pos].id == 1004) it.particleDmgBool = true
                        if (itemList[pos].id == 1005) {
                            it.itemSniper += 1
                            itemSniperPro = true
                        }
                        if (itemList[pos].id == 1006) itemFastDraw = true
                        if (itemList[pos].id == 1007) {
                            activeAbilityList.add(0, ActiveAbility.aAid2)
                            Companion.insertSpell += 1
                        }
                        if (itemList[pos].id == 1008) {
                            it.bonusDamageMultiplyer += 0.2f
                            Companion.overallSpdMultiplier += 20
                            itemBoring = true
                        }

                        if (itemList[pos].id == 1100 || itemList[pos].id == 1101 || itemList[pos].id == 1102 || itemList[pos].id == 1103 || itemList[pos].id == 1104 || itemList[pos].id == 1105) pirateItemCount += 1
                        if (itemList[pos].id == 1100 || itemList[pos].id == 1101 || itemList[pos].id == 1102 || itemList[pos].id == 1103 || itemList[pos].id == 1104 || itemList[pos].id == 1105 || itemList[pos].id == 1007) it.bagSize += 1
                        if (itemList[pos].id == 1100) {
                            it.towerBonusRange += 15
                        }
                        if (itemList[pos].id == 1104) {
                            it.bonusDamageMultiplyer += 0.2f
                            Companion.overallSpdMultiplier -= 10
                        }
                        if (itemList[pos].id == 1105) {
                            it.bonusDamageMultiplyer -= 0.1f
                            Companion.overallSpdMultiplier += 20
                        }

                        if (itemList[pos].id == 5003) it.bonusDamageMultiplyer += 0.1f
                        if (itemList[pos].id == 5004) it.overallSpellDmgMultiplyer += 0.1f
                        if (itemList[pos].id == 5005) it.bonusmultiCrit += 1
                        if (itemList[pos].id == 5006) {
                            it.slowAura += 10f
                            it.bonusItemChance += 10f
                        }
                        if (itemList[pos].id == 5007) {
                            it.bagSize += 2
                            interest += 0.01f
                            it.bonusItemChance += 10f
                        }
                        if (itemList[pos].id == 5008) shieldBrakerItem = 0
                        if (itemList[pos].id == 5009) {
                            it.poisonRow2Item1 = 1
                            it.stackablePoison += 0.1f
                            it.itemStartPoison = true
                        }
                        if (itemList[pos].id == 5010) {
                            lvlHp *= 0.9f
                        }
                        if (itemList[pos].id == 5011) {
                            activeAbilityList.add(0, ActiveAbility.aAid3)
                            Companion.insertSpell += 1
                        }
                        if (itemList[pos].id == 5012) {
                            if (activeAbilityList.contains(aAid1)) {
                                bombActiveAbility += 5
                                Companion.insertSpellBomb += 1
                            } else {
                                activeAbilityList.add(0, aAid1)
                                Companion.insertSpell += 1
                                Companion.insertSpellBomb += 1
                                bombActiveAbility += 5
                            }
                            if (mapMode == 2) bombDamage += 0.025f
                            else bombDamage += 0.1f
                            bombCost = true
                            Companion.mysteryWokeCount += 1
                        }
                        if (itemList[pos].id == 5013) {
                            // TODO
                            it.talentPoints += 1
                        }
                        if (itemList[pos].id == 5014) {
                            // TODO
                          //  itemDarkUltimate = true
                        }
                        if (itemList[pos].id == 5015) {
                            wizardBombStartItemDmg += 0.4f
                            wizardLightningStartItemTargets += 1
                        }
                      // TODO
                        /*
                        if (itemList[pos].id == 5016) {
                            it.moonRow2Item2 += 1
                            it.shotBounceTargets += 2
                            Companion.itemStartBounce = true
                        }

                         */
                        if (itemList[pos].id == 5017) {
                      // TODO
                            //      upgraderBool = true
                            upgradePoints += 2
                        }
                        if ((itemList[pos] == Items.eearth || itemList[pos] == Items.ebutterfly || itemList[pos] == Items.ewind || itemList[pos] == Items.emoon)) {
                            if (it.itemListBag.contains(Items.eearth) || it.itemListBag.contains(Items.ebutterfly) || it.itemListBag.contains(Items.ewind) || it.itemListBag.contains(Items.emoon) ){

                            }else {
                                when (itemList[pos]){
                                    Items.eearth -> it.towerPrimaryElement = "earth"
                                    Items.ebutterfly -> it.towerPrimaryElement = "butterfly"
                                    Items.ewind -> it.towerPrimaryElement = "wind"
                                    Items.emoon -> it.towerPrimaryElement = "moon"
                                }
                            }
                        }
                        if ((itemList[pos] == Items.eearth || itemList[pos] == Items.ebutterfly || itemList[pos] == Items.ewind || itemList[pos] == Items.emoon || itemList[pos] == Items.epoison ||
                                    itemList[pos] == Items.eice || itemList[pos] == Items.efire || itemList[pos] == Items.edark || itemList[pos] == Items.eutils || itemList[pos] == Items.ewizard)) {
                            itemList[pos].element = true
                        }

                        Companion.fragmentItemCurrentItem = -1

                        if (itemList[pos].id == 306 || itemList[pos].id == 5 || itemList[pos].id == 6 || itemList[pos].id == 106 || itemList[pos].id == 313 || itemList[pos].id == 107 || itemList[pos].id == 5010 || itemList[pos].id == 5014) {
                        } else {
                            if (it.bagSizeElementCount == 0) it.itemListBag.add(0, itemList[pos])
                            else it.itemListBag.add(1, itemList[pos])
                            insertItemBag += 1
                        }

                        itemList.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                }
            }
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