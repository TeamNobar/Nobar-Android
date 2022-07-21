package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.common.RecommendModel
import org.sopt.appzam.nobar_android.databinding.ItemSearchSuggestBinding

class SearchSuggestAdapter() :
    ListAdapter<RecommendModel, SearchSuggestAdapter.SearchSuggetViewHolder>(SearchSuggestComparator()) {

    class SearchSuggetViewHolder(private val binding: ItemSearchSuggestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecommendModel) {
            val position: Int = adapterPosition
            binding.model = data
            binding.textRanking.text = (position + 1).toString()
        }
    }

    class SearchSuggestComparator() : DiffUtil.ItemCallback<RecommendModel>() {
        override fun areItemsTheSame(oldItem: RecommendModel, newItem: RecommendModel): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: RecommendModel, newItem: RecommendModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSuggetViewHolder {
        Log.d("server", "onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchSuggestBinding.inflate(layoutInflater, parent, false)
        return SearchSuggetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchSuggetViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}