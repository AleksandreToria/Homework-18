package com.example.homework18.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework18.databinding.ItemLayoutBinding
import com.example.homework18.dataclass.Data

class Adapter : PagingDataAdapter<Data, RecyclerView.ViewHolder>(USER_COMPARATOR) {

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return newItem == oldItem
            }
        }
    }

    @SuppressLint("SetTextI18n")
    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.avatar)
                    .into(image)

                email.text = "Email: ${data.email}"
                firstName.text = "First name: ${data.firstName}"
                lastName.text = "Last name: ${data.lastName}"
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as ViewHolder).bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
}
