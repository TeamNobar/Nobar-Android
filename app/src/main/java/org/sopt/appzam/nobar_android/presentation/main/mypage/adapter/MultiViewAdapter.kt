package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.MyPageTastingResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingNoteBinding
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingNoteDateBinding
import java.lang.RuntimeException

class MultiViewAdapter :
    ListAdapter<MyPageTastingResponse, RecyclerView.ViewHolder>(TastingNoteTagComparator()) {


    inner class DateViewHolder(private val binding: ItemMyPageTastingNoteDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyPageTastingResponse) {
            binding.tastingNoteDateItem = data
        }
    }

    inner class TastingViewHolder(private val binding: ItemMyPageTastingNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyPageTastingResponse) {
            binding.myPageTastingNoteItem = data
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
        when(holder){
            is DateViewHolder -> holder.onBind(getItem(position))
            is TastingViewHolder -> holder.onBind(getItem(position))
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

    class TastingNoteTagComparator : DiffUtil.ItemCallback<MyPageTastingResponse>() {
        override fun areItemsTheSame(
            oldItem: MyPageTastingResponse,
            newItem: MyPageTastingResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MyPageTastingResponse,
            newItem: MyPageTastingResponse
        ): Boolean = oldItem == newItem
    }
}