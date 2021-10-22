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
import com.agsolutions.td.GameActivity.Companion.companionList
import com.agsolutions.td.GameActivity.Companion.paused
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

    fun deleteItemBag (pos: Int) {

        if (companionList.towerClick) {

            var towerListIterator = companionList.towerList.listIterator()
            while (towerListIterator.hasNext()) {
                var it = towerListIterator.next()
                if (it.selected) {

                    if (it.itemListBag[pos].crossedOut) {
                    } else {

                        statsDelete(pos, it)

                        if ((it.itemListBag[pos] == companionList.eearth || it.itemListBag[pos] == companionList.ebutterfly || it.itemListBag[pos] == companionList.ewind || it.itemListBag[pos] == companionList.emoon)) {
                            if (it.itemListBag[0] == companionList.eearth || it.itemListBag[0] == companionList.ebutterfly || it.itemListBag[0] == companionList.ewind || it.itemListBag[0] == companionList.emoon ){

                            }else {
                                it.towerPrimaryElement = "none"

                            }
                        }

                        companionList.itemListInsertItem.add(it.itemListBag[pos])
                        it.itemListBag.removeAt(pos)
                        companionList.itemListBagInserter.clear()
                        notifyDataSetChanged()
                        companionList.itemListBagInserter.addAll(it.itemListBag)
                        notifyItemRangeInserted(0, companionList.itemListBagInserter.size)
                    }
                }
            }
        }
    }

    fun statsDelete (pos: Int, it: Tower){

        it.bonusTowerSpeed -= it.itemListBag[pos].speed
        it.itemListBag[pos].speed /= it.towerRarityMultiplier
        it.bonusTowerDmg -= it.itemListBag[pos].dmg
        it.itemListBag[pos].dmg /= it.towerRarityMultiplier
        it.bonusPhysicalDmg -= it.itemListBag[pos].atkDmg
        it.itemListBag[pos].atkDmg /= it.towerRarityMultiplier
        it.bonusSpellDamage -= it.itemListBag[pos].mgcDmg
        it.itemListBag[pos].mgcDmg /= it.towerRarityMultiplier
        it.bonusCrit -= it.itemListBag[pos].crit
        it.itemListBag[pos].crit /= it.towerRarityMultiplier
        it.bonusCritDmg -= it.itemListBag[pos].critDmg
        it.itemListBag[pos].critDmg /= it.towerRarityMultiplier
        if (it.itemListBag[pos].id == 3 || it.itemListBag[pos].id == 101 || it.itemListBag[pos].id == 201 || it.itemListBag[pos].id == 301) companionList.magicBoxCount -= 1
        if (it.itemListBag[pos].id == 0 || it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 300) companionList.maceCount -= 1
        if (it.itemListBag[pos].id == 1 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 302) companionList.bowCount -= 1
        if (it.itemListBag[pos].id == 2 || it.itemListBag[pos].id == 103 || it.itemListBag[pos].id == 203 || it.itemListBag[pos].id == 303) companionList.swordCount -= 1
        if (it.itemListBag[pos].id == 4) {
            it.bonusItemChance -= it.itemListBag[pos].specialFloat2
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 10) it.xpMulti -= it.itemListBag[pos].specialFloat

        if ((it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 103) && it.itemListBag[pos].special2 == "item find") {
            it.bonusItemChance -= it.itemListBag[pos].specialFloat2
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 100 || it.itemListBag[pos].id == 102 || it.itemListBag[pos].id == 103) && it.itemListBag[pos].special2 == "item quality") {
            it.bonusItemQuality -= it.itemListBag[pos].specialFloat2
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 104 || it.itemListBag[pos].id == 204){
            it.bonusItemQuality -= it.itemListBag[pos].specialFloat2
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 105) it.itemPiggyBank -= 0.1f
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "armor penetration"){
            it.bonusArmorPen -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "magic penetration"){
            it.bonusMagicPen -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 200 || it.itemListBag[pos].id == 202 || it.itemListBag[pos].id == 203) && it.itemListBag[pos].special == "hit chance"){
            it.itemBonusHitChance -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }

        if (it.itemListBag[pos].id == 206) it.talentPoints -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X dmg/round") it.bonusTowerDmg -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X spd/round") it.bonusTowerSpeed -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 207 && it.itemListBag[pos].special == "+ X crit/round") it.bonusCrit -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 208 || it.itemListBag[pos].id == 308) it.itemFrost -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 209) companionList.interest -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 210 || it.itemListBag[pos].id == 309) it.itemLasso -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 212){
            it.itemBonusHitChance -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 213) it.itemLastStance -= 0.1f
        if (it.itemListBag[pos].id == 214) it.itemSniper -= 1
        if (it.itemListBag[pos].id == 214) it.itemSniper -= 1
        if (it.itemListBag[pos].id == 215) {
            it.bonusArmorPen -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 216) {
            it.bonusMagicPen -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 217) it.slowEach -= 10.0f
        if (it.itemListBag[pos].id == 218) it.bonusDamageMultiplyer -= (it.itemListBag[pos].specialFloat * 0.01f)

        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special == "anti-heal") {
            it.bonusAntiHeal -= it.itemListBag[pos].specialFloat / 100
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special == "extra dmg immune") {
            it.bonusDmgImmune -= it.itemListBag[pos].specialFloat / 100
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special2 == "item find") {
            it.bonusItemChance -= it.itemListBag[pos].specialFloat2
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if ((it.itemListBag[pos].id == 300 || it.itemListBag[pos].id == 302 || it.itemListBag[pos].id == 303) && it.itemListBag[pos].special2 == "item quality") {
            it.bonusItemQuality -= (it.itemListBag[pos].specialFloat2).toInt()
            it.itemListBag[pos].specialFloat2 /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 304) it.slowAura -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 305) it.itemDisruptor -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 307) {
            it.shotBounceTargets -= it.itemListBag[pos].specialFloat.toInt()
        }
        if (it.itemListBag[pos].id == 310) it.bonusmultiCrit -= it.itemListBag[pos].specialFloat.toInt()
        if (it.itemListBag[pos].id == 312) it.itemSlowDeath -= it.itemListBag[pos].specialFloat
        if (it.itemListBag[pos].id == 314) {
            it.armorPenPerHit -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 315) {
            it.magicPenPerHit -= it.itemListBag[pos].specialFloat
            it.itemListBag[pos].specialFloat /= it.towerRarityMultiplier
        }
        if (it.itemListBag[pos].id == 316) it.bonusDamageMultiplyer -= it.itemListBag[pos].specialFloat

        if (it.itemListBag[pos].id == 1004) it.particleDmgBool = false
        if (it.itemListBag[pos].id == 1005) {
            it.itemSniper -= 1
            it.itemSniperPro = false
        }
        if (it.itemListBag[pos].id == 1100 || it.itemListBag[pos].id == 1101 || it.itemListBag[pos].id == 1102 || it.itemListBag[pos].id == 1103 || it.itemListBag[pos].id == 1104 || it.itemListBag[pos].id == 1105 || it.itemListBag[pos].id == 1007) it.bagSize -= 1
        if (it.itemListBag[pos].id == 1100) {
            it.towerRange.r -= 15
        }
        if (it.itemListBag[pos].id == 1104) {
            it.bonusDamageMultiplyer -= 0.2f
            companionList.overallSpdMultiplier += 10
        }
        if (it.itemListBag[pos].id == 1105) {
            it.bonusDamageMultiplyer += 0.1f
            companionList.overallSpdMultiplier -= 20
        }
        if (it.itemListBag[pos].id == 1006) it.itemFastDraw = false
        if (it.itemListBag[pos].id == 1007) {
            companionList.activeAbilityList.remove(ActiveAbility.aAid2)
            companionList.insertSpell += 1
        }

        if (it.itemListBag[pos].id == 1008) {
            it.bonusDamageMultiplyer -= 0.2f
            companionList.overallSpdMultiplier -= 20
            it.itemBoring = false
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
            companionList.interest -= 0.01f
            it.bonusItemChance -= 10f
        }
        if (it.itemListBag[pos].id == 5008) companionList.shieldBrakerItem = 1
        if (it.itemListBag[pos].id == 5009) {
            it.poisonRow2Item1 -= 1
            it.stackablePoison -= 0.1f
            it.itemStartPoison = false
        }
        if (it.itemListBag[pos].id == 5011) {
            companionList.activeAbilityList.remove(ActiveAbility.aAid3)
            companionList.insertSpell += 1
        }
        if (it.itemListBag[pos].id == 5012) {
            if (companionList.mapMode == 2) companionList.bombDamage -= 0.025f
            else companionList.bombDamage -= 0.1f
            companionList.bombCost = false
        }
        if (it.itemListBag[pos].id == 5013) {
            companionList.wiseMan = 1.0f
        }
        if (it.itemListBag[pos].id == 5015) {
            companionList.wizardBombStartItemDmg -= 0.4f
            companionList.wizardLightningStartItemTargets -= 1
        }
        if (it.itemListBag[pos].id == 5016) {
            it.shotBounceTargetsStartItems = 1
        }
        if (it.itemListBag[pos].id == 5017) {
            companionList.upgraderBool = true
        }
        if (it.itemListBag[pos].id == 6666) {
            it.bonusTowerDmg -= it.itemListBag[pos].specialFloat
            it.darkSoulCollector = false
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
            if (!companionList.towerClick) {
                paused = true
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener2.onBagLongClick(position, v)
                }

                val item = ClipData.Item(companionList.buildListBag[position] as? CharSequence?)
                    companionList.dragTower = companionList.buildListBag[position]
                val dragData = ClipData(
                    companionList.buildListBag[position].name as CharSequence?,
                    arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                    item
                )
                var towerBaseScaledPlace  = Bitmap.createScaledBitmap(GameView.towerBase!!, (GameView.towerBase!!.width * ((companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), (GameView.towerBase!!.height * ((companionList.scaleScreen / 10) * GameView.scaleFactor)).toInt(), false)
                val myShadow = CustomDragShadowBuilder(towerBaseScaledPlace)
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