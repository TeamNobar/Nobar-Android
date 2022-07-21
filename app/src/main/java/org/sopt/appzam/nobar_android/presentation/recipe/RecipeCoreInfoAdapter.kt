package org.sopt.appzam.nobar_android.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.common.RecipeCoreInfo
import org.sopt.appzam.nobar_android.databinding.ItemCoreInfoBinding

class RecipeCoreInfoAdapter :
    ListAdapter<RecipeCoreInfo, RecipeCoreInfoAdapter.RecipeCoreInfoViewHolder>(
        RecipeCoreInfoComparator()
    ) {
    class RecipeCoreInfoViewHolder(private val binding: ItemCoreInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeCoreInfo) {
            binding.model = data
            Glide.with(binding.imageView).load(data.image).error(R.drawable.cocktail_sample)
                .into(binding.imageView)
        }
    }

    class RecipeCoreInfoComparator() : DiffUtil.ItemCallback<RecipeCoreInfo>() {
        override fun areItemsTheSame(oldItem: RecipeCoreInfo, newItem: RecipeCoreInfo): Boolean {
            return oldItem.sort == newItem.sort
        }

        override fun areContentsTheSame(oldItem: RecipeCoreInfo, newItem: RecipeCoreInfo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCoreInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCoreInfoBinding.inflate(layoutInflater, parent, false)
        return RecipeCoreInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeCoreInfoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}