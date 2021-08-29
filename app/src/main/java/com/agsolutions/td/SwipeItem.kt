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
                var pos = viewHolder.bindingAdapterPosition

                if (itemList[pos].id == 2100 || itemList[pos].id == 2101 || itemList[pos].id == 2102 || itemList[pos].id == 2103 || itemList[pos].id == 2104 || itemList[pos].id == 2105 || itemList[pos].id == 2106 || itemList[pos].id == 2107 || itemList[pos].id == 2108 || itemList[pos].id == 2109 ||
                    itemList[pos].id == 2200 || itemList[pos].id == 2201 || itemList[pos].id == 2202 || itemList[pos].id == 2203 || itemList[pos].id == 2204 || itemList[pos].id == 2205 || itemList[pos].id == 2206 || itemList[pos].id == 2207 || itemList[pos].id == 2208 || itemList[pos].id == 2209 ||
                    itemList[pos].id == 2300 || itemList[pos].id == 2301 || itemList[pos].id == 2302 || itemList[pos].id == 2303 || itemList[pos].id == 2304 || itemList[pos].id == 2305 || itemList[pos].id == 2306 || itemList[pos].id == 2307 || itemList[pos].id == 2308 || itemList[pos].id == 2309
                ) {
                    if (!Companion.day && Companion.moonTalentItemCost > 0) {
                        if (Companion.gold < (itemList[pos].goldCost - (itemList[pos].goldCost * Companion.moonTalentItemCost))) return 0
                    } else if (Companion.midnightMadnessMidasGoldCost > 0) {
                        if (Companion.gold < (itemList[pos].goldCost + (itemList[pos].goldCost * Companion.midnightMadnessMidasGoldCost))) return 0
                    } else {
                        if (Companion.gold < itemList[pos].goldCost) return 0
                    }
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
                                    } else if ((tower.itemListBag.size - tower.bagSizeElementCount) > tower.bagSize) return 0
                                    else if (( itemList[pos] == Items.eearth || itemList[pos] == Items.ewizard || itemList[pos] == Items.eice || itemList[pos] == Items.ebutterfly
                                        || itemList[pos] == Items.epoison || itemList[pos] == Items.emoon || itemList[pos] == Items.ewind || itemList[pos] == Items.eutils || itemList[pos] == Items.efire || itemList[pos] == Items.edark)
                                        && tower.bagSizeElementCount > tower.bagSizeElement) return 0

                                    break
                                }
                            }
                        } else return 0
                    } finally {
                        Companion.readLockTower.unlock()
                    }

                    if (Companion.diamonds < itemList[pos].diaCost) return 0
                    if (!Companion.day && Companion.moonTalentItemCost > 0) {
                        if (Companion.gold < (itemList[pos].goldCost - (itemList[pos].goldCost * Companion.moonTalentItemCost))) return 0
                    } else if (Companion.midnightMadnessMidasGoldCost > 0) {
                        if (Companion.gold < (itemList[pos].goldCost + (itemList[pos].goldCost * Companion.midnightMadnessMidasGoldCost))) return 0
                    } else {
                        if (Companion.gold < itemList[pos].goldCost) return 0
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

