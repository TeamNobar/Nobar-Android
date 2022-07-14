package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.MyPageTastingResponse
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingTagBinding

class TastingNoteTagAdapter :
    ListAdapter<MyPageTastingResponse, TastingNoteTagAdapter.TastingNoteTagViewHolder>(TastingNoteTagComparator()) {
    private val tastingNoteTagList = mutableListOf<MyPageTastingResponse>()

    class TastingNoteTagViewHolder(private val binding: ItemMyPageTastingTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: MyPageTastingResponse) {
            binding.myPageTastingTagItem = data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TastingNoteTagViewHolder {
        val binding =
            ItemMyPageTastingTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TastingNoteTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TastingNoteTagViewHolder, position: Int) {
        holder.onBind(tastingNoteTagList[position])
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