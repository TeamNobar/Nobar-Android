package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageLaterRecipeBinding

class MyLaterRecipeAdapter :
    ListAdapter<RecipeResponse, MyLaterRecipeAdapter.LaterRecipeViewHolder>(
        LaterRecipeDiffUtil()
    ) {

    class LaterRecipeViewHolder(private val binding: ItemMyPageLaterRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeResponse) {
            binding.myPageLaterRecipeItem = data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding =
            ItemMyPageLaterRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class LaterRecipeDiffUtil : DiffUtil.ItemCallback<RecipeResponse>() {
        override fun areItemsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: RecipeResponse,
            newItem: RecipeResponse
        ): Boolean = oldItem == newItem
    }

}