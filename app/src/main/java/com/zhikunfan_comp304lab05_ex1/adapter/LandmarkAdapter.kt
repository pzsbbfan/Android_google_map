package com.zhikunfan_comp304lab05_ex1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhikunfan_comp304lab05_ex1.R
import com.zhikunfan_comp304lab05_ex1.model.Landmark

class LandmarkAdapter(
    private val landmarks: List<Landmark>,
    private val listener: (Landmark) -> Unit
) : RecyclerView.Adapter<LandmarkAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.text_view_name)
        private val textViewAddress: TextView = itemView.findViewById(R.id.text_view_address)
        fun bind(item: Landmark) {
            textViewName.text = item.name
            textViewAddress.text = item.address
            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.landmark_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(landmarks[position])
    }

    override fun getItemCount() = landmarks.size
}
