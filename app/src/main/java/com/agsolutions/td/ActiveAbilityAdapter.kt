package com.agsolutions.td

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.active_ability.view.*

class ActiveAbilityAdapter (
    private val activeAbilityListX: MutableList<ActiveAbility>,
    private val listener: OnActiveAbilityListener,
    ) :
    RecyclerView.Adapter<ActiveAbilityAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.active_ability,
               parent, false)

            return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = activeAbilityListX[position]
        holder.imageView.setImageResource(currentItem.image)
        holder.itemView.findViewById<TextView>(R.id.active_ability_cd).text = currentItem.cdRemain
    }

    override fun getItemCount() = activeAbilityListX.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.active_ability_view

        init {
            itemView.setOnClickListener(this)
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onActiveAbilityClick(position)
            }
        }
    }
    interface OnActiveAbilityListener {
        fun onActiveAbilityClick(position: Int)
    }
}

            /*

        override fun onClick(v: View?) {
              val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onActiveAbilityClick(position)
                  }
            }

        }
        interface OnActiveAbilityClickListener {
            fun onActiveAbilityClick(position: Int)
        }
    }

             */
