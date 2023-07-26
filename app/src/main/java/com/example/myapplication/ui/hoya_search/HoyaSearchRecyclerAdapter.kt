package com.example.myapplication.ui.hoya_search

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.core.data.model.TanamanModel
import com.google.android.material.card.MaterialCardView

class HoyaSearchRecyclerAdapter(private var itemList: List<TanamanModel>) :
    RecyclerView.Adapter<HoyaSearchRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = View.inflate(parent.context, R.layout.recycler_hoya_tanaman, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        with(holder) {
            tvTitle.text = item.nama
            Glide.with(itemView.context)
                .load(item.gambar)
                .into(ivPhoto)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.findViewById<ImageView>(R.id.iv_photo)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val layoutMain = itemView.findViewById<MaterialCardView>(R.id.layout_main)
    }

}