package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemMyPageRecipeBinding

class LaterRecipeTagAdapter : ListAdapter<String, LaterRecipeTagAdapter.LaterRecipeViewHolder>(LaterRecipeTagComparator()){
    val laterRecipeList = mutableListOf<String>()
    class LaterRecipeViewHolder(private val binding : ItemMyPageRecipeBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : String){
            binding.textTag.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaterRecipeViewHolder {
        val binding = ItemMyPageRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaterRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaterRecipeViewHolder, position: Int) {
        holder.onBind(laterRecipeList[position])
    }

    class LaterRecipeTagComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem == newItem
    }

}