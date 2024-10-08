package com.agsolutions.td

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SwipeItemBag (var adapter: ItemBagAdapter) : ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.UP) {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if (GameActivity.companionList.towerClick) {
            var pos = viewHolder.absoluteAdapterPosition
            if (pos < 0 || pos >= GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag.size){
                return 0
            } else {
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] == GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[0]) return 0
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos].crossedOut) return 0
                if (GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] == GameActivity.companionList.eearth || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.ebutterfly || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.ewind || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.emoon ||
                    GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.eice || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.epoison || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.ewizard || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.efire ||
                    GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.eutils || GameActivity.companionList.towerList[GameActivity.companionList.towerClickID].itemListBag[pos] ==GameActivity.companionList.edark) return 0
            if (GameActivity.companionList.lockEverything) return 0
            }

            return super.getMovementFlags(recyclerView, viewHolder)
        } else {
            return 0
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (!GameActivity.companionList.build) {
            var pos = viewHolder.absoluteAdapterPosition
            adapter.deleteItemBag(pos)
        }

    }

}