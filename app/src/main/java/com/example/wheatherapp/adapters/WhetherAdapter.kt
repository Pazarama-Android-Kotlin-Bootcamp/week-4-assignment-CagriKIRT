package com.example.wheatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheatherapp.R
import com.example.wheatherapp.models.WhetherModel
import java.text.DecimalFormat

class WhetherAdapter(val whethers: List<WhetherModel>) :
    RecyclerView.Adapter<WhetherAdapter.WhetherViewHolder>() {


    class WhetherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val day_text: TextView = itemView.findViewById(R.id.day_text)
        private val day_image: ImageView = itemView.findViewById(R.id.day_image)
        private val day_degree1: TextView = itemView.findViewById(R.id.day_degree1)
        private val day_degree2: TextView = itemView.findViewById(R.id.day_degree2)
        fun bind(whether: WhetherModel) {
            day_text.text = whether.day

            if (whether.image != null) {
                day_image.setImageDrawable(whether.image)
            }
            val precision = DecimalFormat("0.00")
            day_degree1.text = precision.format(whether.degree1)
            day_degree2.text = precision.format(whether.degree2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhetherViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.whether_item, parent, false)
        return WhetherViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: WhetherViewHolder, position: Int) {
        holder.bind(whethers[position])
    }

    override fun getItemCount(): Int {
        return whethers.size
    }


}