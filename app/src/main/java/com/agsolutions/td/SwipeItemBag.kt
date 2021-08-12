package com.agsolutions.td

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeItemBag (var adapter: ItemBagAdapter) : ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.UP) {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if (!Companion.build) {
            var pos = viewHolder.adapterPosition

            Companion.readLockTower.lock()
            try {
                var towerListIterator = Companion.towerList.listIterator()
                while (towerListIterator.hasNext()) {
                    var tower = towerListIterator.next()
                    if (tower.selected) {

                        if (tower.itemListBag[pos].crossedOut) return 0
                        break
                    }
                    else {
                        return 0
                    }
                }
                }finally {
                    Companion.readLockTower.unlock()
                }
            return super.getMovementFlags(recyclerView, viewHolder)
        } else return super.getMovementFlags(recyclerView, viewHolder)
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