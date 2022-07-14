package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.MyPageTastingResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingNoteBinding

class TastingNoteAdapter :
    ListAdapter<MyPageTastingResponse, TastingNoteAdapter.TastingNoteViewHolder>(
        TastingNoteComparator()
    ) {
    private val tastingNoteList = mutableListOf<MyPageTastingResponse>()

    class TastingNoteViewHolder(private val binding: ItemMyPageTastingNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyPageTastingResponse) {
            binding.myPageTastingNoteItem = data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TastingNoteViewHolder {
        val binding = ItemMyPageTastingNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TastingNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TastingNoteViewHolder, position: Int) {
        holder.onBind(tastingNoteList[position])
    }

    class TastingNoteComparator : DiffUtil.ItemCallback<MyPageTastingResponse>() {
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