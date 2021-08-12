package com.agsolutions.td

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.Companion.Companion.buildListBag
import com.agsolutions.td.Companion.Companion.itemList
import com.agsolutions.td.Companion.Companion.towerClick


class SwipeItem (var adapter: ItemAdapter) : ItemTouchHelper.SimpleCallback (0, ItemTouchHelper.DOWN
   )
        {

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                var pos = viewHolder.adapterPosition

                if (itemList[pos].id == 2000 || itemList[pos].id == 2001 || itemList[pos].id == 2002 || itemList[pos].id == 2003 || itemList[pos].id == 2004 || itemList[pos].id == 2005 || itemList[pos].id == 2006 || itemList[pos].id == 2007 || itemList[pos].id == 2008
                    || itemList[pos].id == 2009
                ) {
                    if (buildListBag.size > 2) return 0
                    return super.getMovementFlags(recyclerView, viewHolder)

                } else {

                    Companion.readLockTower.lock()
                    try {
                        if (towerClick) {
                            var towerListIterator = Companion.towerList.listIterator()
                            while (towerListIterator.hasNext()) {
                                var tower = towerListIterator.next()
                                if (tower.selected) {

                                    if (itemList[pos].id == 5 || itemList[pos].id == 6 || itemList[pos].id == 1100 || itemList[pos].id == 1101 || itemList[pos].id == 1102 || itemList[pos].id == 1103 || itemList[pos].id == 1104 || itemList[pos].id == 1105 || itemList[pos].id == 1007
                                        || itemList[pos].id == 106 || itemList[pos].id == 313 || itemList[pos].id == 107
                                    ) {
                                    } else if (tower.itemListBag.size > tower.bagSize) return 0
                                    break
                                }
                            }
                        } else return 0
                    } finally {
                        Companion.readLockTower.unlock()
                    }

                    if (Companion.diamonds < itemList[pos].diaCost) return 0
                    if (!Companion.day && Companion.moonTalentItemCost > 0) {
                        if (Companion.xp < (itemList[pos].xpCost - (itemList[pos].xpCost * Companion.moonTalentItemCost))) return 0
                    } else if (Companion.midnightMadnessMidasGoldCost > 0) {
                        if (Companion.xp < (itemList[pos].xpCost + (itemList[pos].xpCost * Companion.midnightMadnessMidasGoldCost))) return 0
                    } else {
                        if (Companion.xp < itemList[pos].xpCost) return 0
                    }

                    return super.getMovementFlags(recyclerView, viewHolder)
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
            var pos = viewHolder.adapterPosition
            adapter.deleteItem(pos)

    }
}

