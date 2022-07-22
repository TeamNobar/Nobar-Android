package org.sopt.appzam.nobar_android.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeGuideBinding

class GuideAdapter(private val itemClick: (GuideResponse) -> (Unit)) :
    ListAdapter<GuideResponse, GuideAdapter.GuideViewHolder>(guideDiffUtil) {

    class GuideViewHolder(
        private val binding: ItemHomeGuideBinding,
        private val itemClick: (GuideResponse) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GuideResponse, position: Int) {
            binding.guideItem = data
            Log.d("dd", position.toString())
            when (position % 5) {
                0 -> binding.imageGuide.setImageResource(R.drawable.img_guide_base)
                1 -> binding.imageGuide.setImageResource(R.drawable.img_guide_deco)
                2 -> binding.imageGuide.setImageResource(R.drawable.img_guide_price)
                3 -> binding.imageGuide.setImageResource(R.drawable._img_guide_glass)
                4 -> binding.imageGuide.setImageResource(R.drawable.img_guide_daiso)
            }
            binding.root.setOnClickListener {
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
        holder.onBind(getItem(position), position)
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