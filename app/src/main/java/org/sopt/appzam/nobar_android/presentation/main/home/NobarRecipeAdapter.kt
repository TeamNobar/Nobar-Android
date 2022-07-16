package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeNobarRecipeBinding

class NobarRecipeAdapter :
    ListAdapter<NobarRecipeResponse, NobarRecipeAdapter.NobarRecipeViewHolder>(NobarRecipeComparator()) {
    private val nobarRecipeList = mutableListOf<NobarRecipeResponse>()

    class NobarRecipeViewHolder(private val binding: ItemHomeNobarRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NobarRecipeResponse) {
            binding.nobarRecipeItem = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NobarRecipeViewHolder {
        val binding =
            ItemHomeNobarRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NobarRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NobarRecipeViewHolder, position: Int) {
        holder.onBind(nobarRecipeList[position])
    }

    class NobarRecipeComparator : DiffUtil.ItemCallback<NobarRecipeResponse>() {
        override fun areItemsTheSame(
            oldItem: NobarRecipeResponse,
            newItem: NobarRecipeResponse
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: NobarRecipeResponse,
            newItem: NobarRecipeResponse
        ): Boolean =
            oldItem == newItem
    }
}
