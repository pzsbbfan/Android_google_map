package com.zhikunfan_comp304lab05_ex1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhikunfan_comp304lab05_ex1.R
import com.zhikunfan_comp304lab05_ex1.model.LandmarkType

class LandmarkTypeAdapter(
    private val landmarkTypes: List<LandmarkType>,
    private val listener: (LandmarkType) -> Unit
) : RecyclerView.Adapter<LandmarkTypeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text_view)
        fun bind(item: LandmarkType) {
            textView.text = item.typeName
            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.landmark_type_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(landmarkTypes[position])
    }

    override fun getItemCount() = landmarkTypes.size
}
