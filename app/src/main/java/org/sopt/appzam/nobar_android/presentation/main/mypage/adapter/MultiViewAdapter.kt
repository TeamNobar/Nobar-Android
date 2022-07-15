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
    private val multiDataList = mutableListOf<MyPageTastingResponse>()
    private var type = 0

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
        when(type){
            DATE -> (holder as DateViewHolder).onBind(multiDataList[position])
            TASTING_VIEW -> (holder as TastingViewHolder).onBind(multiDataList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            type = DATE
            DATE
        } else if (multiDataList[position] == multiDataList[position - 1]) {
            type = TASTING_VIEW
            TASTING_VIEW
        } else {
            type = DATE
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
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: MyPageTastingResponse,
            newItem: MyPageTastingResponse
        ): Boolean = oldItem == newItem
    }
}