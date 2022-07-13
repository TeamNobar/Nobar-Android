package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.HomeNobarRecipeData
import org.sopt.appzam.nobar_android.databinding.ItemHomeNobarRecipeBinding

class NobarRecipeAdapter :
    ListAdapter<HomeNobarRecipeData, NobarRecipeAdapter.NobarRecipeViewHolder>(nobarRecipeDiffUtil) {
    private val nobarRecipeList = mutableListOf<HomeNobarRecipeData>()

    class NobarRecipeViewHolder(private val binding: ItemHomeNobarRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun onBind(data : HomeNobarRecipeData){
                binding.nobarRecipeItem = data
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NobarRecipeViewHolder {
        val binding = ItemHomeNobarRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NobarRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NobarRecipeViewHolder, position: Int) {
        holder.onBind(nobarRecipeList[position])
    }

    companion object {
        private val nobarRecipeDiffUtil = object : DiffUtil.ItemCallback<HomeNobarRecipeData>() {
            override fun areItemsTheSame(
                oldItem: HomeNobarRecipeData,
                newItem: HomeNobarRecipeData
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: HomeNobarRecipeData,
                newItem: HomeNobarRecipeData
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}