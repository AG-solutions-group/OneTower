package com.agsolutions.td

import android.graphics.Canvas
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView



class SwipeItem (var adapter: ItemAdapter) : ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.DOWN) {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                Log.d("itemBeingMoved", GameActivity.companionList.itemBeingMoved.toString())
                var pos = viewHolder.absoluteAdapterPosition
                if (pos < 0 || pos >= GameActivity.companionList.itemList.size){
                    return 0
                } else  {

                    if (GameActivity.companionList.itemList[pos].id == 2100 || GameActivity.companionList.itemList[pos].id == 2101 || GameActivity.companionList.itemList[pos].id == 2102 || GameActivity.companionList.itemList[pos].id == 2103 || GameActivity.companionList.itemList[pos].id == 2104 || GameActivity.companionList.itemList[pos].id == 2105 || GameActivity.companionList.itemList[pos].id == 2106 || GameActivity.companionList.itemList[pos].id == 2107 || GameActivity.companionList.itemList[pos].id == 2108 || GameActivity.companionList.itemList[pos].id == 2109 ||
                        GameActivity.companionList.itemList[pos].id == 2200 || GameActivity.companionList.itemList[pos].id == 2201 || GameActivity.companionList.itemList[pos].id == 2202 || GameActivity.companionList.itemList[pos].id == 2203 || GameActivity.companionList.itemList[pos].id == 2204 || GameActivity.companionList.itemList[pos].id == 2205 || GameActivity.companionList.itemList[pos].id == 2206 || GameActivity.companionList.itemList[pos].id == 2207 || GameActivity.companionList.itemList[pos].id == 2208 || GameActivity.companionList.itemList[pos].id == 2209 ||
                        GameActivity.companionList.itemList[pos].id == 2300 || GameActivity.companionList.itemList[pos].id == 2301 || GameActivity.companionList.itemList[pos].id == 2302 || GameActivity.companionList.itemList[pos].id == 2303 || GameActivity.companionList.itemList[pos].id == 2304 || GameActivity.companionList.itemList[pos].id == 2305 || GameActivity.companionList.itemList[pos].id == 2306 || GameActivity.companionList.itemList[pos].id == 2307 || GameActivity.companionList.itemList[pos].id == 2308 || GameActivity.companionList.itemList[pos].id == 2309
                    ) {
                        if (!GameActivity.companionList.day && GameActivity.companionList.moonTalentItemCost > 0) {
                            if (GameActivity.companionList.gold < (GameActivity.companionList.itemList[pos].goldCost - (GameActivity.companionList.itemList[pos].goldCost * GameActivity.companionList.moonTalentItemCost))) return 0
                        } else if (GameActivity.companionList.midnightMadnessMidasGoldCost > 0) {
                            if (GameActivity.companionList.gold < (GameActivity.companionList.itemList[pos].goldCost + (GameActivity.companionList.itemList[pos].goldCost * GameActivity.companionList.midnightMadnessMidasGoldCost))) return 0
                        } else {
                            if (GameActivity.companionList.gold < GameActivity.companionList.itemList[pos].goldCost) return 0
                        }
                        if (GameActivity.companionList.buildListBag.size > 2) return 0
                        return super.getMovementFlags(recyclerView, viewHolder)

                    } else {
                        if (GameActivity.companionList.towerClick) {

                            if (GameActivity.companionList.itemList[pos].id == 5 || GameActivity.companionList.itemList[pos].id == 6 || GameActivity.companionList.itemList[pos].id == 7 || GameActivity.companionList.itemList[pos].id == 8 || GameActivity.companionList.itemList[pos].id == 9 || GameActivity.companionList.itemList[pos].id == 1100 || GameActivity.companionList.itemList[pos].id == 1101 || GameActivity.companionList.itemList[pos].id == 1102 || GameActivity.companionList.itemList[pos].id == 1103 || GameActivity.companionList.itemList[pos].id == 1104 || GameActivity.companionList.itemList[pos].id == 1105 || GameActivity.companionList.itemList[pos].id == 1007
                                || GameActivity.companionList.itemList[pos].id == 106 || GameActivity.companionList.itemList[pos].id == 313 || GameActivity.companionList.itemList[pos].id == 107 || GameActivity.companionList.itemList[pos].id == 306
                            ) {
                            } else if (GameActivity.companionList.itemList[pos] == GameActivity.companionList.eearth || GameActivity.companionList.itemList[pos] == GameActivity.companionList.ewizard || GameActivity.companionList.itemList[pos] == GameActivity.companionList.eice || GameActivity.companionList.itemList[pos] == GameActivity.companionList.ebutterfly
                                || GameActivity.companionList.itemList[pos] == GameActivity.companionList.epoison || GameActivity.companionList.itemList[pos] == GameActivity.companionList.emoon || GameActivity.companionList.itemList[pos] == GameActivity.companionList.ewind || GameActivity.companionList.itemList[pos] == GameActivity.companionList.eutils || GameActivity.companionList.itemList[pos] == GameActivity.companionList.efire || GameActivity.companionList.itemList[pos] == GameActivity.companionList.edark
                            ) {
                                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount >= (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElement + 1)) return 0
                                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.contains(GameActivity.companionList.itemList[pos])) return 0
                            } else if ((GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.size - GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSizeElementCount) > GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].bagSize) return 0

                            if (GameActivity.companionList.diamonds < GameActivity.companionList.itemList[pos].diaCost) return 0
                            if (!GameActivity.companionList.day && GameActivity.companionList.moonTalentItemCost > 0) {
                                if (GameActivity.companionList.gold < (GameActivity.companionList.itemList[pos].goldCost - (GameActivity.companionList.itemList[pos].goldCost * GameActivity.companionList.moonTalentItemCost))) return 0
                            } else if (GameActivity.companionList.midnightMadnessMidasGoldCost > 0) {
                                if (GameActivity.companionList.gold < (GameActivity.companionList.itemList[pos].goldCost + (GameActivity.companionList.itemList[pos].goldCost * GameActivity.companionList.midnightMadnessMidasGoldCost))) return 0
                            } else {
                                if (GameActivity.companionList.gold < GameActivity.companionList.itemList[pos].goldCost) return 0
                            }
                        } else if(GameActivity.companionList.itemList[pos].id == 5){

                        } else return 0

                        return super.getMovementFlags(recyclerView, viewHolder)
                    }
                }
            }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                GameActivity.companionList.itemBeingMoved = isCurrentlyActive
            }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        GameActivity.companionList.itemBeingMoved = false
            var pos = viewHolder.absoluteAdapterPosition
            adapter.deleteItem(pos)

    }
}

