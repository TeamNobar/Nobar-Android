package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.data.remote.response.TastingNoteResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingNoteBinding
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingNoteDateBinding

class MultiViewAdapter(private val itemClick: (TastingNoteResponse) -> Unit) :
    ListAdapter<TastingNoteResponse, RecyclerView.ViewHolder>(TastingNoteTagComparator()) {


    class DateViewHolder(private val binding: ItemMyPageTastingNoteDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TastingNoteResponse, itemClick: (TastingNoteResponse) -> Unit) {
            binding.tastingNoteDateItem = data
            binding.ratingHeart.rating = data.rate.toFloat()
            binding.ratingHeart.setOnTouchListener(View.OnTouchListener { v, event -> true })
            val recipeTagAdapter = TastingNoteTagAdapter()
            binding.recyclerTag.adapter = recipeTagAdapter

            val tagList = mutableListOf<TagResponse>()

            for (i in 0 until data.tag.count()) {
                if (data.tag[i].isSelected)
                    tagList.add(data.tag[i])
            }

            recipeTagAdapter.submitList(tagList)

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    inner class TastingViewHolder(private val binding: ItemMyPageTastingNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TastingNoteResponse, itemClick: (TastingNoteResponse) -> Unit) {
            binding.myPageTastingNoteItem = data
            binding.ratingHeart.rating = data.rate.toFloat()
            binding.ratingHeart.setOnTouchListener(View.OnTouchListener { v, event -> true })
            val recipeTagAdapter = TastingNoteTagAdapter()

            binding.recyclerTag.adapter = recipeTagAdapter
            val tagList = mutableListOf<TagResponse>()

            for (i in 0 until data.tag.count()) {
                if (data.tag[i].isSelected)
                    tagList.add(data.tag[i])
            }
            recipeTagAdapter.submitList(tagList)

            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATE -> {
                val binding = ItemMyPageTastingNoteDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DateViewHolder(binding)
            }
            TASTING_VIEW -> {
                val binding = ItemMyPageTastingNoteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TastingViewHolder(binding)
            }
            else -> {
                throw RuntimeException("Error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.onBind(getItem(position), itemClick)
            is TastingViewHolder -> holder.onBind(getItem(position), itemClick)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            DATE
        } else if (getItem(position).createdAt == getItem(position - 1).createdAt) {
            TASTING_VIEW
        } else {
            DATE
        }
    }

    companion object {
        const val DATE = 1
        const val TASTING_VIEW = 2
    }

    class TastingNoteTagComparator : DiffUtil.ItemCallback<TastingNoteResponse>() {
        override fun areItemsTheSame(
            oldItem: TastingNoteResponse,
            newItem: TastingNoteResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: TastingNoteResponse,
            newItem: TastingNoteResponse
        ): Boolean = oldItem == newItem
    }
}