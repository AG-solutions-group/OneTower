package com.agsolutions.td

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.Companion.Companion.towerClick

class SwipeItemBag (var adapter: ItemBagAdapter) : ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.UP) {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if (towerClick) {
            var pos = viewHolder.bindingAdapterPosition

            if (Companion.towerList[Companion.towerClickID].itemListBag[pos] == Companion.towerList[Companion.towerClickID].itemListBag[0]) return 0
            if (Companion.towerList[Companion.towerClickID].itemListBag[pos].crossedOut) return 0

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
        if (!Companion.build) {
            var pos = viewHolder.adapterPosition
            adapter.deleteItem(pos)
        }

    }

}