package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.data.remote.utils.BindingAdapter.setImage
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingTagBinding

class TastingNoteTagAdapter :
    ListAdapter<TagResponse, TastingNoteTagAdapter.TastingTagViewHolder>(TastingNoteTagComparator()) {

    class TastingTagViewHolder(private val binding: ItemMyPageTastingTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagResponse) {
            binding.myPageTastingTagItem = data
            setImage(binding.imageTag,data.activeIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TastingTagViewHolder {
        val binding =
            ItemMyPageTastingTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TastingTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TastingTagViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class TastingNoteTagComparator : DiffUtil.ItemCallback<TagResponse>() {
        override fun areItemsTheSame(
            oldItem: TagResponse,
            newItem: TagResponse
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TagResponse,
            newItem: TagResponse
        ): Boolean = oldItem == newItem
    }
}
