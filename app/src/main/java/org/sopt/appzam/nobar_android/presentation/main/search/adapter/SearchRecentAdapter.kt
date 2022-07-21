package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemSearchRecentBinding

class SearchRecentAdapter() :
    ListAdapter<String, SearchRecentAdapter.SearchRecentViewHolder>(SearchRecentComparator()) {
    class SearchRecentViewHolder(private val binding: ItemSearchRecentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.textView.text = data
        }
    }

    class SearchRecentComparator() : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchRecentBinding.inflate(layoutInflater, parent, false)
        return SearchRecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchRecentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}