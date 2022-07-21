package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingTagBinding

class TastingNoteTagAdapter :
    ListAdapter<Int, TastingNoteTagAdapter.TastingTagViewHolder>(TastingNoteTagComparator()) {

    class TastingTagViewHolder(private val binding: ItemMyPageTastingTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Int) {
            binding.textTagName.text = data.toString()
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

    class TastingNoteTagComparator : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Int,
            newItem: Int
        ): Boolean = oldItem == newItem
    }
}
