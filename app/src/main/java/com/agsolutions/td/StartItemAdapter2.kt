package com.agsolutions.td

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class StartItemAdapter2 (
    private val startItemList: MutableList<Items>,
    private val listener: OnHiddenClickListener,
    private var callback:()->Unit
    ) :
    RecyclerView.Adapter<StartItemAdapter2.ExampleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
                parent, false)

            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = startItemList[position]
            holder.imageView.setImageResource(currentItem.image)

        }

        override fun getItemCount() = startItemList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {
            val imageView: ImageView = itemView.findViewById(R.id.item_view)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
              val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onHiddenClick(position)
                  }
            callback.invoke()
            }

        }
        interface OnHiddenClickListener {
            fun onHiddenClick(position: Int)
        }
    }