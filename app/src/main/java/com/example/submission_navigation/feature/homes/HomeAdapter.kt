package com.example.submission_navigation.feature.homes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemProfileBinding
import com.example.core.domain.entity.SearchUser

class HomeAdapter(private val onItemClick: (SearchUser) -> Unit?) : ListAdapter<SearchUser, HomeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    inner class MyViewHolder(private val binding: ItemProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: SearchUser){
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.profileImage)
            binding.textName.text = user.login
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(user)
                }
            }
        }
    }

    fun clear() {
        submitList(null)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchUser>() {
            override fun areItemsTheSame(oldItem: SearchUser, newItem: SearchUser): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SearchUser, newItem: SearchUser): Boolean {
                return oldItem == newItem
            }
        }
    }
}