package com.example.myapplication.ui.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.remote.response.ForumResponse
import com.example.myapplication.databinding.ListForumBinding
class ForumAdapter : ListAdapter<ForumResponse, ForumAdapter.ForumViewHolder>(DIFF_CALLBACK){
    inner class ForumViewHolder(private val binding: ListForumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForumResponse) {
            with(binding) {
                tvUsername.text = item.user.username
                tvCreatedAt.text = item.tanggal
                tvDesc.text = item.deskripsi
                if (item.foto != null) {
                    Glide.with(itemView)
                        .load(item.foto)
                        .into(imgPost)
                } else imgPost.visibility = View.GONE

                if (item.user.foto != null) {
                    Glide.with(itemView)
                        .load(item.user.foto)
                        .into(imgUser)
                } else imgUser.setBackgroundResource(R.color.blue1)

                tvCountComment.text = item.commentCount?.toString() ?: "0"
                tvCountLike.text = item.likeCount?.toString() ?: "0"
                tvCountShare.text = item.shareCount?.toString() ?: "0"
                tvSave.text = item.bookmarkCount?.toString() ?: "0"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val binding = ListForumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<ForumResponse> =
            object : DiffUtil.ItemCallback<ForumResponse>() {
                override fun areItemsTheSame(
                    oldItem: ForumResponse,
                    newItem: ForumResponse
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: ForumResponse,
                    newItem: ForumResponse
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}