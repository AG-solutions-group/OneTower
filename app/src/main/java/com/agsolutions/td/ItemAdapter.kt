package com.agsolutions.td

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.ActiveAbility.Companion.aAid1
import com.agsolutions.td.Companion.Companion.activeAbilityList
import com.agsolutions.td.Companion.Companion.antiHeal
import com.agsolutions.td.Companion.Companion.bombActiveAbility
import com.agsolutions.td.Companion.Companion.bombCost
import com.agsolutions.td.Companion.Companion.bombDamage
import com.agsolutions.td.Companion.Companion.bowCount
import com.agsolutions.td.Companion.Companion.buildListBag
import com.agsolutions.td.Companion.Companion.dmgImmune
import com.agsolutions.td.Companion.Companion.globalMultiCrit
import com.agsolutions.td.Companion.Companion.insertItemBag
import com.agsolutions.td.Companion.Companion.interest
import com.agsolutions.td.Companion.Companion.itemBoring
import com.agsolutions.td.Companion.Companion.itemDarkUltimate
import com.agsolutions.td.Companion.Companion.itemFastDraw
import com.agsolutions.td.Companion.Companion.itemPiggyBank
import com.agsolutions.td.Companion.Companion.itemSniperPro
import com.agsolutions.td.Companion.Companion.itemStartPoison
import com.agsolutions.td.Companion.Companion.itemStartTalentPoints
import com.agsolutions.td.Companion.Companion.lives
import com.agsolutions.td.Companion.Companion.livesMpCounter
import com.agsolutions.td.Companion.Companion.luckyCharmCount
import com.agsolutions.td.Companion.Companion.lvlHp
import com.agsolutions.td.Companion.Companion.maceCount
import com.agsolutions.td.Companion.Companion.magicBoxCount
import com.agsolutions.td.Companion.Companion.mapMode
import com.agsolutions.td.Companion.Companion.particleDmgBool
import com.agsolutions.td.Companion.Companion.pirateItemCount
import com.agsolutions.td.Companion.Companion.shieldBrakerItem
import com.agsolutions.td.Companion.Companion.shotBounce
import com.agsolutions.td.Companion.Companion.slowAura
import com.agsolutions.td.Companion.Companion.splashRange
import com.agsolutions.td.Companion.Companion.swordCount
import com.agsolutions.td.Companion.Companion.talentPoints
import com.agsolutions.td.Companion.Companion.towerClick
import com.agsolutions.td.Companion.Companion.upgradePoints
import com.agsolutions.td.Companion.Companion.upgraderBool
import com.agsolutions.td.Companion.Companion.wizardBombStartItemDmg
import com.agsolutions.td.Companion.Companion.wizardLightningStartItemTargets
import com.agsolutions.td.Companion.Companion.xp
import com.agsolutions.td.Fragments.MoonTalentFragment
import com.agsolutions.td.Fragments.PoisonTalentFragment
import kotlinx.android.synthetic.main.item.view.*

class ItemAdapter (
    private val itemList: MutableList<Items>,
    private val listener: OnClickListener,
    private var callback:()->Unit
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
        if (itemList[pos].id == 2000 || itemList[pos].id == 2001 || itemList[pos].id == 2002 || itemList[pos].id == 2003 || itemList[pos].id == 2004 || itemList[pos].id == 2005 || itemList[pos].id == 2006 || itemList[pos].id == 2007 || itemList[pos].id == 2008 || itemList[pos].id == 2009) {
            if (!Companion.day && Companion.moonTalentItemCost > 0) Companion.xp -= (itemList[pos].xpCost - (Companion.itemList[pos].xpCost * Companion.moonTalentItemCost))
            else if (Companion.midnightMadnessMidasGoldCost > 0) Companion.xp -= (itemList[pos].xpCost + (Companion.itemList[pos].xpCost * Companion.midnightMadnessMidasGoldCost))
            else Companion.xp -= itemList[pos].xpCost
            Companion.diamonds -= itemList[pos].diaCost

            buildListBag.add(0, itemList[pos])
            insertItemBag += 1

            Companion.fragmentItemCurrentItem = -1

            itemList.removeAt(pos)
            notifyItemRemoved(pos)
        }else {

            if (towerClick) {

                var towerListIterator = Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var it = towerListIterator.next()
                    if (it.selected) {


                        if (itemList[pos].id == 306 || itemList[pos].id == 5 || itemList[pos].id == 6 || itemList[pos].id == 106 || itemList[pos].id == 313 || itemList[pos].id == 107 || itemList[pos].id == 5010 || itemList[pos].id == 5014) {
                        } else {
                            it.itemListBag.add(0, itemList[pos])
                            insertItemBag += 1
                        }

                        if (!Companion.day && Companion.moonTalentItemCost > 0) Companion.xp -= (itemList[pos].xpCost - (Companion.itemList[pos].xpCost * Companion.moonTalentItemCost))
                        else if (Companion.midnightMadnessMidasGoldCost > 0) Companion.xp -= (itemList[pos].xpCost + (Companion.itemList[pos].xpCost * Companion.midnightMadnessMidasGoldCost))
                        else Companion.xp -= itemList[pos].xpCost

                        Companion.diamonds -= itemList[pos].diaCost
                        it.bonusTowerSpeed += itemList[pos].speed
                        it.bonusTowerDmg += itemList[pos].dmg
                        it.bonusPhysicalDmg += itemList[pos].atkDmg
                        it.bonusSpellDamage += itemList[pos].mgcDmg
                        it.bonusCrit += itemList[pos].crit
                        it.bonusCritDmg += itemList[pos].critDmg
                        if (itemList[pos].id == 3 || itemList[pos].id == 101 || itemList[pos].id == 201 || itemList[pos].id == 301) magicBoxCount += 1
                        if (itemList[pos].id == 0 || itemList[pos].id == 100 || itemList[pos].id == 200 || itemList[pos].id == 300) maceCount += 1
                        if (itemList[pos].id == 1 || itemList[pos].id == 102 || itemList[pos].id == 202 || itemList[pos].id == 302) bowCount += 1
                        if (itemList[pos].id == 2 || itemList[pos].id == 103 || itemList[pos].id == 203 || itemList[pos].id == 303) swordCount += 1
                        if (itemList[pos].id == 4 || itemList[pos].id == 104 || itemList[pos].id == 204) luckyCharmCount += 1

                        if (itemList[pos].id == 4) {
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
                            if (bombCost) xp += (itemList[pos].xpCost * 0.5f)
                        }
                        if (itemList[pos].id == 6) {
                            xp += itemList[pos].specialFloat
                        }

                        if ((itemList[pos].id == 100 || itemList[pos].id == 102 || itemList[pos].id == 103) && itemList[pos].special2 == "item find") {
                            it.bonusItemChance += itemList[pos].specialFloat2
                        }
                        if ((itemList[pos].id == 100 || itemList[pos].id == 102 || itemList[pos].id == 103) && itemList[pos].special2 == "item quality") {
                            it.bonusItemQuality += (itemList[pos].specialFloat2).toInt()
                        }
                        if (itemList[pos].id == 104 || itemList[pos].id == 204) {
                            it.bonusItemQuality += itemList[pos].specialFloat2
                        }
                        if (itemList[pos].id == 105) {
                            itemPiggyBank += 0.1f
                        }
                        if (itemList[pos].id == 106) {
                            if (mapMode != 2) lives += 1
                            else Companion.livesMode2 += 1
                        }
                        if (itemList[pos].id == 107) {
                            talentPoints += 1
                        }

                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "armor penetration") it.bonusArmorPen += itemList[pos].specialFloat
                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "magic penetration") it.bonusMagicPen += itemList[pos].specialFloat
                        if ((itemList[pos].id == 200 || itemList[pos].id == 202 || itemList[pos].id == 203) && itemList[pos].special == "hit chance") it.itemBonusHitChance += itemList[pos].specialFloat
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
                        if (itemList[pos].id == 212) it.itemBonusHitChance += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 213) it.itemLastStance += 0.1f
                        if (itemList[pos].id == 214) it.itemSniper += 1
                        if (itemList[pos].id == 215) it.bonusArmorPen += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 216) it.bonusMagicPen += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 217) Companion.slowEach += 10.0f

                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special == "anti-heal") antiHeal += itemList[pos].specialFloat / 100
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special == "extra dmg immune") dmgImmune += itemList[pos].specialFloat / 100
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special2 == "item find") it.bonusItemChance += itemList[pos].specialFloat2
                        if ((itemList[pos].id == 300 || itemList[pos].id == 302 || itemList[pos].id == 303) && itemList[pos].special2 == "item quality") it.bonusItemQuality += (itemList[pos].specialFloat2).toInt()
                        if (itemList[pos].id == 304) Companion.slowAura += itemList[pos].specialFloat
                        if (itemList[pos].id == 305) it.itemDisruptor += itemList[pos].specialFloat
                        if (itemList[pos].id == 306) {
                            it.bagSize += itemList[pos].specialFloat.toInt()
                        }
                        if (itemList[pos].id == 206) {
                            talentPoints += itemList[pos].specialFloat.toInt()
                        }
                        if (itemList[pos].id == 307 && !Companion.talentMultishot && splashRange == 0f) {
                            Companion.shotBounceTargets += itemList[pos].specialFloat.toInt()
                            shotBounce = true
                        }
                        if (itemList[pos].id == 310) globalMultiCrit += itemList[pos].specialFloat.toInt()
                        if (itemList[pos].id == 312) it.itemSlowDeath += itemList[pos].specialFloat
                        if (itemList[pos].id == 313) {
                            livesMpCounter = true
                            if (mapMode != 2) lives += 5
                            else Companion.livesMode2 += 5
                        }
                        if (itemList[pos].id == 314) it.armorPenPerHit += itemList[pos].specialFloat
                        if (itemList[pos].id == 315) it.magicPenPerHit += itemList[pos].specialFloat

                        if (itemList[pos].id == 1004) particleDmgBool = true
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
                        if (itemList[pos].id == 5005) globalMultiCrit += 1
                        if (itemList[pos].id == 5006) {
                            slowAura += 10f
                            it.bonusItemChance += 10f
                        }
                        if (itemList[pos].id == 5007) {
                            it.bagSize += 2
                            interest += 0.01f
                            it.bonusItemChance += 10f
                        }
                        if (itemList[pos].id == 5008) shieldBrakerItem = 0
                        if (itemList[pos].id == 5009) {
                            PoisonTalentFragment.poisonRow2Item1 = 1
                            Companion.stackablePoison += 0.1f
                            itemStartPoison = true
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
                            talentPoints += 1
                            itemStartTalentPoints = true
                        }
                        if (itemList[pos].id == 5014) {
                            itemDarkUltimate = true
                        }
                        if (itemList[pos].id == 5015) {
                            wizardBombStartItemDmg += 0.4f
                            wizardLightningStartItemTargets += 1
                        }
                        if (itemList[pos].id == 5016) {
                            shotBounce = true
                            MoonTalentFragment.moonRow2Item2 += 1
                            Companion.shotBounceTargets += 2
                            Companion.itemStartBounce = true
                        }
                        if (itemList[pos].id == 5017) {
                            upgraderBool = true
                            upgradePoints += 2
                        }

                        Companion.fragmentItemCurrentItem = -1

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
            callback.invoke()
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