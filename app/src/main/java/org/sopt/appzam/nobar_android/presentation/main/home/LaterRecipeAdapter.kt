package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.LaterRecipeData
import org.sopt.appzam.nobar_android.databinding.ItemLaterRecipeBinding

class LaterRecipeAdapter : ListAdapter<LaterRecipeData,LaterRecipeAdapter.LaterRecipeViewHolder>(laterRecipeDiffUtil){
    private val laterRecipeList = mutableListOf<LaterRecipeData>()
    inner class LaterRecipeViewHolder(private val binding : ItemLaterRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : LaterRecipeData) {
            binding.laterRecipeItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding = ItemLaterRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(laterRecipeList[position])
    }

    companion object {
        private val laterRecipeDiffUtil = object : DiffUtil.ItemCallback<LaterRecipeData>() {
            override fun areItemsTheSame(oldItem: LaterRecipeData, newItem: LaterRecipeData): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: LaterRecipeData, newItem: LaterRecipeData): Boolean =
                oldItem.equals(newItem)
        }
    }
}