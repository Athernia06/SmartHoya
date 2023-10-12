package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.remote.response.HoyaResponse
import com.example.myapplication.databinding.ListHoyaBinding

class HoyaAdapter : ListAdapter<HoyaResponse, HoyaAdapter.HoyaViewHolder>(DIFF_CALLBACK) {
    inner class HoyaViewHolder(private val binding: ListHoyaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HoyaResponse) {
            with(binding) {
                if (item.foto != null) {
                    Glide.with(itemView)
                        .load(item.foto)
                        .into(ivPlants)
                } else ivPlants.setImageResource(R.drawable.hoya_anulata)
                tvTitle.text = item.nama
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoyaAdapter.HoyaViewHolder {
        val binding = ListHoyaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HoyaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoyaAdapter.HoyaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<HoyaResponse> =
            object : DiffUtil.ItemCallback<HoyaResponse>() {
                override fun areItemsTheSame(
                    oldItem: HoyaResponse,
                    newItem: HoyaResponse
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: HoyaResponse,
                    newItem: HoyaResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}