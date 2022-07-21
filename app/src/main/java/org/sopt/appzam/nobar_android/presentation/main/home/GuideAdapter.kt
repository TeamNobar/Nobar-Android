package org.sopt.appzam.nobar_android.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeGuideBinding

class GuideAdapter(private val itemClick: (GuideResponse) -> (Unit)) :
    ListAdapter<GuideResponse, GuideAdapter.GuideViewHolder>(guideDiffUtil) {

    class GuideViewHolder(
        private val binding: ItemHomeGuideBinding,
        private val itemClick: (GuideResponse) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GuideResponse) {
            binding.guideItem = data
            binding.root.setOnClickListener{
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val binding =
            ItemHomeGuideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuideViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val guideDiffUtil = object : DiffUtil.ItemCallback<GuideResponse>() {
            override fun areItemsTheSame(oldItem: GuideResponse, newItem: GuideResponse): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: GuideResponse,
                newItem: GuideResponse
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}