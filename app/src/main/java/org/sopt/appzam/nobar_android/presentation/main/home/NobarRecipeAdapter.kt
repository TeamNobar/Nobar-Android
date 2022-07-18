package org.sopt.appzam.nobar_android.presentation.main.home

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeNobarRecipeBinding

class NobarRecipeAdapter(private val randomIntList: List<Int>) :
    ListAdapter<NobarRecipeResponse, NobarRecipeAdapter.NobarRecipeViewHolder>(NobarRecipeComparator()) {
    val nobarRecipeList = mutableListOf<NobarRecipeResponse>()

    class NobarRecipeViewHolder(private val binding: ItemHomeNobarRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NobarRecipeResponse, colorInt: Int) {
            binding.nobarRecipeItem = data
            when (colorInt) {
                0 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box)
                1 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box2)
                2 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box3)
                3 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box4)
                4 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box5)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NobarRecipeViewHolder {
        val binding =
            ItemHomeNobarRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NobarRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NobarRecipeViewHolder, position: Int) {
        holder.onBind(nobarRecipeList[position], randomIntList[position])
    }

    override fun getItemCount(): Int {
        return nobarRecipeList.size
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
