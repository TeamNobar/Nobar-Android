package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeLaterRecipeBinding

class LaterRecipeAdapter :
    ListAdapter<RecipeResponse, LaterRecipeAdapter.LaterRecipeViewHolder>(LaterRecipeComparator()) {
    private val laterRecipeList = mutableListOf<RecipeResponse>()

    class LaterRecipeViewHolder(private val binding: ItemHomeLaterRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeResponse) {
            binding.laterRecipeItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding =
            ItemHomeLaterRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(laterRecipeList[position])
    }

    class LaterRecipeComparator : DiffUtil.ItemCallback<RecipeResponse>() {
        override fun areItemsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean = oldItem == newItem
    }
}
