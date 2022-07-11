package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.HomeLaterRecipeData
import org.sopt.appzam.nobar_android.databinding.ItemHomeLaterRecipeBinding

class LaterRecipeAdapter : ListAdapter<HomeLaterRecipeData,LaterRecipeAdapter.LaterRecipeViewHolder>(laterRecipeDiffUtil){
    private val laterRecipeList = mutableListOf<HomeLaterRecipeData>()
    class LaterRecipeViewHolder(private val binding : ItemHomeLaterRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data : HomeLaterRecipeData) {
            binding.laterRecipeItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding = ItemHomeLaterRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(laterRecipeList[position])
    }

    companion object {
        private val laterRecipeDiffUtil = object : DiffUtil.ItemCallback<HomeLaterRecipeData>() {
            override fun areItemsTheSame(oldItem: HomeLaterRecipeData, newItem: HomeLaterRecipeData): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: HomeLaterRecipeData, newItem: HomeLaterRecipeData): Boolean =
                oldItem.equals(newItem)
        }
    }
}