package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.MyPageLaterRecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageLaterRecipeBinding

class MyLaterRecipeAdapter :
    ListAdapter<MyPageLaterRecipeResponse, MyLaterRecipeAdapter.LaterRecipeViewHolder>(
        LaterRecipeDiffUtil()
    ) {

    class LaterRecipeViewHolder(private val binding: ItemMyPageLaterRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyPageLaterRecipeResponse) {
            binding.myPageLaterRecipeItem = data
            val recipeTagAdapter = LaterRecipeTagAdapter().apply {
                currentList.clear()
                currentList.addAll(data.steps)
            }
            binding.recyclerTag.adapter = recipeTagAdapter
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

    class LaterRecipeDiffUtil : DiffUtil.ItemCallback<MyPageLaterRecipeResponse>() {
        override fun areItemsTheSame(
            oldItem: MyPageLaterRecipeResponse,
            newItem: MyPageLaterRecipeResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MyPageLaterRecipeResponse,
            newItem: MyPageLaterRecipeResponse
        ): Boolean = oldItem == newItem
    }

}