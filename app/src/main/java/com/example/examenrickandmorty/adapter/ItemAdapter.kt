package com.example.examenrickandmorty.adapter

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examenrickandmorty.R
import com.example.examenrickandmorty.data.ItemPerson
import com.example.examenrickandmorty.databinding.ItemLayoutBinding

class ItemAdapter(
    private val onItemClick: (ItemPerson) -> Unit = {}
) : ListAdapter<ItemPerson, ItemAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(
        private val binding: ItemLayoutBinding,
        private val onItemClick: (ItemPerson) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemPerson) {
            binding.titleTextView.text = item.title
            binding.descriptionTextView.text = item.description

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ItemPerson>() {
        override fun areItemsTheSame(oldItem: ItemPerson, newItem: ItemPerson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemPerson, newItem: ItemPerson): Boolean {
            return oldItem == newItem
        }
    }
}