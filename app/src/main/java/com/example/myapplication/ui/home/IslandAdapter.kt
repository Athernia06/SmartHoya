package com.example.myapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.remote.response.IslandResponse
import com.example.myapplication.databinding.ListIslandBinding

class IslandAdapter(
    private val onItemClicked: (IslandResponse) -> Unit,
    private val context: Context
) : ListAdapter<IslandResponse, IslandAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var selectedItem: IslandResponse? = null

    fun setSelectedItem(item: IslandResponse) {
        selectedItem = item
    }
    inner class ListViewHolder(private val binding: ListIslandBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: IslandResponse) {
            with(binding) {
                tvIsland.text = item.namaPulau
                if (selectedItem == item) {
                    cvIsland.isChecked = true
                    tvIsland.setTextColor(ContextCompat.getColor(context, R.color.app_theme))
                } else {
                    cvIsland.isChecked = false
                    tvIsland.setTextColor(ContextCompat.getColor(context, R.color.shades6))
                }

                itemView.setOnClickListener {
                    setSelectedItem(item)
                    onItemClicked.invoke(item)
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IslandAdapter.ListViewHolder {
        val binding = ListIslandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IslandAdapter.ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<IslandResponse> =
            object : DiffUtil.ItemCallback<IslandResponse>() {
                override fun areItemsTheSame(
                    oldItem: IslandResponse,
                    newItem: IslandResponse
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: IslandResponse,
                    newItem: IslandResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}