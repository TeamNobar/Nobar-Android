package org.sopt.appzam.nobar_android.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeLaterRecipeBinding
import org.sopt.appzam.nobar_android.databinding.ItemSearchResultBinding

class LaterRecipeAdapter :
    ListAdapter<RecipeResponse, LaterRecipeAdapter.LaterRecipeViewHolder>(LaterRecipeComparator()) {

    class LaterRecipeViewHolder(private val binding: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeResponse) {
            binding.searchResultItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class LaterRecipeComparator : DiffUtil.ItemCallback<RecipeResponse>() {
        override fun areItemsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean = oldItem == newItem
    }
}
