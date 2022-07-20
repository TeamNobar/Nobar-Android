package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemSearchResultBinding

class SearchResultAdapter() :
    ListAdapter<RecipeResponse, SearchResultAdapter.SearchResultViewHolder>(SearchResultComparator()) {

    class SearchResultViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeResponse) {
            binding.model=data
        }
    }

    class SearchResultComparator() : DiffUtil.ItemCallback<RecipeResponse>() {
        override fun areItemsTheSame(oldItem: RecipeResponse, newItem: RecipeResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeResponse, newItem: RecipeResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(layoutInflater)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}