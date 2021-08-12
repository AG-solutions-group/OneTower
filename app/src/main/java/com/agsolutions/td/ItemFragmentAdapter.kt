package com.agsolutions.td


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.stats.view.*


class ItemFragmentAdapter (
    private val list: MutableList<ItemFragmentStrings>,
    private val listener: OnStatsClickListener
    ) :
    RecyclerView.Adapter<ItemFragmentAdapter.ExampleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stats,
                parent, false)

            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = list[position]
            holder.imageView.setImageResource(currentItem.name)
            holder.itemView.findViewById<TextView>(R.id.statsShowTV).text = currentItem.stats

        }

        override fun getItemCount() = list.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener
             {
                 val imageView: ImageView = itemView.statsNameTV

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            listener.onStatsClick(position)
        }
    }
             }
    interface OnStatsClickListener {
        fun onStatsClick(position: Int)
    }

}


class ItemFragmentStrings (var name: Int, var stats: String)

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