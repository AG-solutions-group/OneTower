package com.agsolutions.td

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.Companion.Companion.buildListBag
import com.agsolutions.td.Companion.Companion.maceCount
import com.agsolutions.td.Companion.Companion.towerClick
import kotlinx.android.synthetic.main.item.view.*

class ItemBagAdapter (

    private val itemList2: MutableList<Items>,
    private val listener: OnBagClickListener,
    private val listener2: OnBagLongClickListener
) :
RecyclerView.Adapter<ItemBagAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent, false)

        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = itemList2[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.imageViewOverlay.setImageResource(currentItem.imageOverlay)
    }

    fun deleteItem (pos: Int) {


        if (Companion.towerClick) {

            var towerListIterator = Companion.towerList.listIterator()
            while (towerListIterator.hasNext()) {
                var it = towerListIterator.next()
                if (it.selected) {

                    if (itemList2[pos].crossedOut) {
                    } else {
                        it.bonusTowerSpeed -= itemList2[pos].speed
                        it.bonusTowerDmg -= itemList2[pos].dmg
                        it.bonusPhysicalDmg -= itemList2[pos].atkDmg
                        it.bonusSpellDamage -= itemList2[pos].mgcDmg
                        it.bonusCrit -= itemList2[pos].crit
                        it.bonusCritDmg -= itemList2[pos].critDmg
                        if (itemList2[pos].id == 3 || itemList2[pos].id == 101 || itemList2[pos].id == 201 || itemList2[pos].id == 301) Companion.magicBoxCount -= 1
                        if (itemList2[pos].id == 0 || itemList2[pos].id == 100 || itemList2[pos].id == 200 || itemList2[pos].id == 300) maceCount -= 1
                        if (itemList2[pos].id == 1 || itemList2[pos].id == 102 || itemList2[pos].id == 202 || itemList2[pos].id == 302) Companion.bowCount -= 1
                        if (itemList2[pos].id == 2 || itemList2[pos].id == 103 || itemList2[pos].id == 203 || itemList2[pos].id == 303) Companion.swordCount -= 1
                        if (itemList2[pos].id == 4 || itemList2[pos].id == 104 || itemList2[pos].id == 204) Companion.luckyCharmCount -= 1
                        if (itemList2[pos].id == 4) it.bonusItemChance -= itemList2[pos].specialFloat2

                        if ((itemList2[pos].id == 100 || itemList2[pos].id == 102 || itemList2[pos].id == 103) && itemList2[pos].special2 == "item find") it.bonusItemChance -= itemList2[pos].specialFloat2
                        if ((itemList2[pos].id == 100 || itemList2[pos].id == 102 || itemList2[pos].id == 103) && itemList2[pos].special2 == "item quality") it.bonusItemQuality -= itemList2[pos].specialFloat2
                        if (itemList2[pos].id == 104 || itemList2[pos].id == 204) it.bonusItemQuality -= itemList2[pos].specialFloat2
                        if (itemList2[pos].id == 105) it.itemPiggyBank -= 0.1f
                        if ((itemList2[pos].id == 200 || itemList2[pos].id == 202 || itemList2[pos].id == 203) && itemList2[pos].special == "armor penetration") it.bonusArmorPen -= itemList2[pos].specialFloat
                        if ((itemList2[pos].id == 200 || itemList2[pos].id == 202 || itemList2[pos].id == 203) && itemList2[pos].special == "magic penetration") it.bonusMagicPen -= itemList2[pos].specialFloat
                        if ((itemList2[pos].id == 200 || itemList2[pos].id == 202 || itemList2[pos].id == 203) && itemList2[pos].special == "hit chance") it.itemBonusHitChance -= itemList2[pos].specialFloat

                        if (itemList2[pos].id == 206) it.talentPoints -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 207 && itemList2[pos].special == "+ X dmg/round") it.bonusTowerDmg -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 207 && itemList2[pos].special == "+ X spd/round") it.bonusTowerSpeed -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 207 && itemList2[pos].special == "+ X crit/round") it.bonusCrit -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 208 || itemList2[pos].id == 308) it.itemFrost -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 209) Companion.interest -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 210 || itemList2[pos].id == 309) it.itemLasso -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 212) it.itemBonusHitChance -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 312) it.itemSlowDeath -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 213) it.itemLastStance -= 0.1f
                        if (itemList2[pos].id == 214) it.itemSniper -= 1
                        if (itemList2[pos].id == 214) it.itemSniper -= 1
                        if (itemList2[pos].id == 215) it.bonusArmorPen -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 216) it.bonusMagicPen -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 217) it.slowEach -= 10.0f

                        if ((itemList2[pos].id == 300 || itemList2[pos].id == 302 || itemList2[pos].id == 303) && itemList2[pos].special == "anti-heal") it.bonusAntiHeal -= itemList2[pos].specialFloat / 100
                        if ((itemList2[pos].id == 300 || itemList2[pos].id == 302 || itemList2[pos].id == 303) && itemList2[pos].special == "extra dmg immune") it.bonusDmgImmune -= itemList2[pos].specialFloat / 100
                        if ((itemList2[pos].id == 300 || itemList2[pos].id == 302 || itemList2[pos].id == 303) && itemList2[pos].special2 == "item find") it.bonusItemChance -= itemList2[pos].specialFloat2
                        if ((itemList2[pos].id == 300 || itemList2[pos].id == 302 || itemList2[pos].id == 303) && itemList2[pos].special2 == "item quality") it.bonusItemQuality -= (itemList2[pos].specialFloat2).toInt()
                        if (itemList2[pos].id == 304) it.slowAura -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 305) it.itemDisruptor -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 307) {
                            it.shotBounceTargets -= itemList2[pos].specialFloat.toInt()
                        }
                        if (itemList2[pos].id == 310) it.bonusmultiCrit -= itemList2[pos].specialFloat.toInt()
                        if (itemList2[pos].id == 314) it.armorPenPerHit -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 315) it.magicPenPerHit -= itemList2[pos].specialFloat


                        if (itemList2[pos].id == 1004) it.particleDmgBool = false
                        if (itemList2[pos].id == 1005) {
                            it.itemSniper -= 1
                            Companion.itemSniperPro = false
                        }
                        if (itemList2[pos].id == 1100 || itemList2[pos].id == 1101 || itemList2[pos].id == 1102 || itemList2[pos].id == 1103 || itemList2[pos].id == 1104 || itemList2[pos].id == 1105) Companion.pirateItemCount -= 1
                        if (itemList2[pos].id == 1100 || itemList2[pos].id == 1101 || itemList2[pos].id == 1102 || itemList2[pos].id == 1103 || itemList2[pos].id == 1104 || itemList2[pos].id == 1105 || itemList2[pos].id == 1007) it.bagSize -= 1
                        if (itemList2[pos].id == 1100) {
                            it.towerRange.r -= 15
                        }
                        if (itemList2[pos].id == 1104) {
                            it.bonusDamageMultiplyer -= 0.2f
                            Companion.overallSpdMultiplier += 10
                        }
                        if (itemList2[pos].id == 1105) {
                            it.bonusDamageMultiplyer += 0.1f
                            Companion.overallSpdMultiplier -= 20
                        }
                        if (itemList2[pos].id == 1006) Companion.itemFastDraw = false
                        if (itemList2[pos].id == 1007) {
                            Companion.activeAbilityList.remove(ActiveAbility.aAid2)
                            Companion.insertSpell += 1
                        }

                        if (itemList2[pos].id == 1008) {
                            it.bonusDamageMultiplyer -= 0.2f
                            Companion.overallSpdMultiplier -= 20
                            Companion.itemBoring = false
                        }

                        if (itemList2[pos].id == 5000) it.bonusTowerDmg -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 5001) it.bonusTowerSpeed -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 5002) it.bonusCrit -= itemList2[pos].specialFloat
                        if (itemList2[pos].id == 5003) {
                            it.bonusDamageMultiplyer -= 0.1f
                            it.bonusTowerDmg -= itemList2[pos].bonusDmgLevel
                        }
                        if (itemList2[pos].id == 5004) {
                            it.overallSpellDmgMultiplyer -= 0.1f
                            it.bonusSpellDamage -= itemList2[pos].bonusSpellDmgLevel
                        }
                        if (itemList2[pos].id == 5005) {
                            it.bonusmultiCrit -= 1
                            it.bonusCrit -= itemList2[pos].bonusCritLevel
                            it.bonusCritDmg -= itemList2[pos].bonusCritDmgLevel
                        }
                        if (itemList2[pos].id == 5006) {
                            it.slowAura -= 10f
                            it.bonusItemChance -= 10f
                        }
                        if (itemList2[pos].id == 5007) {
                            it.bagSize -= 2
                            Companion.interest -= 0.01f
                            it.bonusItemChance -= 10f
                        }
                        if (itemList2[pos].id == 5008) Companion.shieldBrakerItem = 1
                        if (itemList2[pos].id == 5009) {
                            it.poisonRow2Item1 -= 1
                            it.stackablePoison -= 0.1f
                            it.itemStartPoison = false
                        }
                        if (itemList2[pos].id == 5011) {
                            Companion.activeAbilityList.remove(ActiveAbility.aAid3)
                            Companion.insertSpell += 1
                        }
                        if (itemList2[pos].id == 5012) {
                            if (Companion.mapMode == 2) Companion.bombDamage -= 0.025f
                            else Companion.bombDamage -= 0.1f
                            Companion.bombCost = false
                        }
                        if (itemList2[pos].id == 5013) {
                           // TODO  itemStartTalentPoints = false
                        }
                        if (itemList2[pos].id == 5015) {
                            Companion.wizardBombStartItemDmg -= 0.4f
                            Companion.wizardLightningStartItemTargets -= 1
                        }
                       // TODO
                        /*
                        if (itemList2[pos].id == 5016) {
                            itemStartBounce = false
                            MoonTalentFragment.moonRow2Item2 -= 1

                            if (MoonTalentFragment.moonRow2Item2 <= 0) {
                                it.shotBounceTargets = 1
                            } else if (MoonTalentFragment.moonRow2Item2 == 1) {
                                it.shotBounceTargets = 2
                            } else if (MoonTalentFragment.moonRow2Item2 == 2) {
                                it.shotBounceTargets = 3
                            }
                        }

                         */

                        if (itemList2[pos].id == 6666) {
                            it.bonusTowerDmg -= itemList2[pos].specialFloat
                        }

                        if ((itemList2[pos] == Items.eearth || itemList2[pos] == Items.ebutterfly || itemList2[pos] == Items.ewind || itemList2[pos] == Items.emoon)) {
                            if (it.itemListBag[0] == Items.eearth || it.itemListBag[0] == Items.ebutterfly || it.itemListBag[0] == Items.ewind || it.itemListBag[0] == Items.emoon ){

                            }else {
                                it.towerPrimaryElement = "none"

                            }
                        }

                        it.itemListBag.removeAt(pos)
                        itemList2.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                }
            }
        }
    }

    fun replaceCrossedOut (pos: Int) {

        var towerListIterator = Companion.towerList.listIterator()
        while (towerListIterator.hasNext()) {
            var it = towerListIterator.next()

            it.bonusTowerSpeed -= it.itemListBag[pos].speed
            it.bonusTowerDmg -= it.itemListBag[pos].dmg
            it.bonusPhysicalDmg -= it.itemListBag[pos].atkDmg
            it.bonusSpellDamage -= it.itemListBag[pos].mgcDmg
            it.bonusCrit -= it.itemListBag[pos].crit
            it.bonusCritDmg -= it.itemListBag[pos].critDmg
        if (it.itemListBag[pos].id == 3 || it.itemListBag[pos].id == 101 || it.itemListBag[pos].id == 201 || it.itemListBag[pos].id == 301) Companion.magicBoxCount -= 1
        if (it.itemListBag[pos].id == 0 || it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 300) maceCount -= 1
        if (it.itemListBag[pos].id == 1 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 302) Companion.bowCount -= 1
        if (it.itemListBag[pos].id == 2 || it.itemListBag[pos].id == 103 || it.itemListBag[pos].id == 203 || it.itemListBag[pos].id == 303) Companion.swordCount -= 1
        if (it.itemListBag[pos].id == 4 || it.itemListBag[pos].id == 104 || it.itemListBag[pos].id == 204) Companion.luckyCharmCount -= 1
        if (it.itemListBag[pos].id == 4) it.bonusItemChance -= it.itemListBag[pos].specialFloat

        if ((it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 103) && it.itemListBag[pos].special == "item find") it.bonusItemChance -= it.itemListBag[pos].specialFloat
        if ((it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 103) && it.itemListBag[pos].special == "item quality") it.bonusItemQuality -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 104 || it.itemListBag[pos].id == 204) it.bonusItemQuality -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 105) it.itemPiggyBank -= 0.1f
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "armor penetration") it.bonusArmorPen -= it.itemListBag[pos].specialFloat
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "magic penetration") it.bonusMagicPen -= it.itemListBag[pos].specialFloat
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "hit chance") it.itemBonusHitChance -= it.itemListBag[pos].specialFloat


        if (it.itemListBag[pos].id == 206) it.talentPoints -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X dmg/round") it.bonusTowerDmg -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X spd/round") it.bonusTowerSpeed -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X crit/round") it.bonusCrit -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 208 || it.itemListBag[pos].id == 308) it.itemFrost -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 209) Companion.interest -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 210 || it.itemListBag[pos].id == 309) it.itemLasso -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 212) it.itemBonusHitChance -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 312) it.itemSlowDeath -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 213) it.itemLastStance -= 0.1f
        if (it.itemListBag[pos].id == 214) it.itemSniper -= 1
        if (it.itemListBag[pos].id == 214) it.itemSniper -= 1
        if (it.itemListBag[pos].id == 215) it.bonusArmorPen -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 216) it.bonusMagicPen -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 217) it.slowEach -= 10.0f

        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special == "anti-heal") it.bonusAntiHeal -= it.itemListBag[pos].specialFloat / 100
        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special == "extra dmg immune") it.bonusDmgImmune -= it.itemListBag[pos].specialFloat / 100
        if (it.itemListBag[pos].id == 304) it.slowAura -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 305) it.itemDisruptor -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 307) {
            it.shotBounceTargets -= it.itemListBag[pos].specialFloat.toInt()
        }
        if (it.itemListBag[pos].id == 310) it.bonusmultiCrit -= it.itemListBag[pos].specialFloat.toInt()

        if (it.itemListBag[pos].id == 1004) it.particleDmgBool = false
        if (it.itemListBag[pos].id == 1005) {
            it.itemSniper -= 1
            Companion.itemSniperPro = false
        }
        if (it.itemListBag[pos].id == 1100 || it.itemListBag[pos].id == 1101 || it.itemListBag[pos].id == 1102 || it.itemListBag[pos].id == 1103 || it.itemListBag[pos].id == 1104 || it.itemListBag[pos].id == 1105) Companion.pirateItemCount -= 1
        if (it.itemListBag[pos].id == 1100 || it.itemListBag[pos].id == 1101 || it.itemListBag[pos].id == 1102 || it.itemListBag[pos].id == 1103 || it.itemListBag[pos].id == 1104 || it.itemListBag[pos].id == 1105 || it.itemListBag[pos].id == 1007) it.bagSize -= 1
            if (it.itemListBag[pos].id == 1100) {
                it.towerBonusRange -= 15
            }
            if (it.itemListBag[pos].id == 1104) {
            it.bonusDamageMultiplyer -= 0.2f
            Companion.overallSpdMultiplier += 10
        }
        if (it.itemListBag[pos].id == 1105) {
            it.bonusDamageMultiplyer += 0.1f
            Companion.overallSpdMultiplier -= 30
        }
        if (it.itemListBag[pos].id == 1006) Companion.itemFastDraw = false
        if (it.itemListBag[pos].id == 1007) {
            Companion.activeAbilityList.remove(ActiveAbility.aAid2)
            Companion.insertSpell += 1
        }

        if (it.itemListBag[pos].id == 1008) {
            it.bonusDamageMultiplyer -= 0.2f
            Companion.overallSpdMultiplier -= 20
            Companion.itemBoring = false
        }

        if (it.itemListBag[pos].id == 5000) it.bonusTowerDmg -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 5001) it.bonusTowerSpeed -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 5002) it.bonusCrit -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 5003) {
            it.bonusDamageMultiplyer -= 0.1f
            it.bonusTowerDmg -= it.itemListBag[pos].bonusDmgLevel
        }
        if (it.itemListBag[pos].id == 5004) {
            it.overallSpellDmgMultiplyer -= 0.1f
            it.bonusSpellDamage -= it.itemListBag[pos].bonusSpellDmgLevel
        }
        if (it.itemListBag[pos].id == 5005) {
            it.bonusmultiCrit -= 1
            it.bonusCrit -= it.itemListBag[pos].bonusCritLevel
            it.bonusCritDmg -= it.itemListBag[pos].bonusCritDmgLevel
        }
        if (it.itemListBag[pos].id == 5006) {
            it.slowAura -= 10f
            it.bonusItemChance -= 10f
        }
        if (it.itemListBag[pos].id == 5007) {
            it.bagSize -= 2
            Companion.interest -= 0.01f
            it.bonusItemChance -= 10f
        }
        if (it.itemListBag[pos].id == 5008) Companion.shieldBrakerItem = 1
        if (it.itemListBag[pos].id == 5017) // TODO Companion.upgraderBool = false

        if (it.itemListBag[pos].id == 6666) {
            it.bonusTowerDmg -= it.itemListBag[pos].specialFloat

        }
        }
    }

    override fun getItemCount() = itemList2.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener, View.OnDragListener {

        val imageView: ImageView = itemView.item_view
        val imageViewOverlay: ImageView = itemView.item_view_overlay

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?):Boolean {
            if (!towerClick) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener2.onBagLongClick(position, v)
                }

                val item = ClipData.Item(buildListBag[position] as? CharSequence?)

                val dragData = ClipData(
                    buildListBag[position].name as CharSequence?,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )
                val myShadow = CustomDragShadowBuilder(GameView.towerBase)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Log.d("drag", v.toString())
                    v?.startDragAndDrop(dragData, myShadow, null, 0)
                } else {
                    v?.startDrag(dragData, myShadow, null, 0)
                }
                return true
            } else return false
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onBagClick(position, v)
            }
        }

        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            TODO("Not yet implemented")
        }
    }
    interface OnBagClickListener {
        fun onBagClick(position: Int, view: View?)
    }
    interface OnBagLongClickListener {
        fun onBagLongClick(position: Int, view: View?)
    }

    class CustomDragShadowBuilder(
        private val image: Bitmap?
    ) : View.DragShadowBuilder() {
        override fun onDrawShadow(canvas: Canvas) {
           canvas.drawBitmap(image!!, 0f, 0f, null)

        }

        override fun onProvideShadowMetrics(shadowSize: Point, shadowTouchPoint: Point) {
            shadowSize.x = image!!.width
            shadowSize.y = image!!.height
            shadowTouchPoint.x = shadowSize.x
            shadowTouchPoint.y = shadowSize.y
        }
    }
}