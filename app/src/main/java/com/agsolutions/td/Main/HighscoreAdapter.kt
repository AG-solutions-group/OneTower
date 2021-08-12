package com.agsolutions.td.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agsolutions.td.R

class HighscoreAdapter (
    private val itemList: MutableList<HighscoreAtributes>
    ) :
    RecyclerView.Adapter<HighscoreAdapter.ExampleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.highscore,
                parent, false)

            return ExampleViewHolder(itemView)
        }
        override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
            val currentItem = itemList[position]
            holder.itemView.findViewById<TextView>(R.id.usernameHighscoreTV).text = currentItem.username
            holder.itemView.findViewById<TextView>(R.id.dateHighscoreTV).text = currentItem.date.toString()
            holder.itemView.findViewById<TextView>(R.id.levelHighscoreTV).text = currentItem.level.toString()
            holder.itemView.findViewById<TextView>(R.id.rankingHighscoreTV).text = (position + 1).toString()
        }


        override fun getItemCount() = itemList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        }

}