package com.agsolutions.td


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.GameActivity.Companion.companionList


class RadioAdapter (
    private val radioList: MutableList<String>
    ) :
    RecyclerView.Adapter<RadioAdapter.ExampleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.radio,
                parent, false)

            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = radioList[position]
            holder.itemView.findViewById<TextView>(R.id.radio_view).text = currentItem
            holder.itemView.findViewById<TextView>(R.id.radio_view).setTextSize(companionList.scaleTextNews / companionList.screenDensity)
        }

        override fun getItemCount() = radioList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
             {

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