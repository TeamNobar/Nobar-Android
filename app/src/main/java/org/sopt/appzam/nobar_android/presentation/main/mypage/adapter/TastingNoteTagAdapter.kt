package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemMyPageTastingTagBinding

class TastingNoteTagAdapter :
    ListAdapter<String, TastingNoteTagAdapter.TastingTagViewHolder>(TastingNoteTagComparator()) {
    val tastingTagList = mutableListOf<String>()

    class TastingTagViewHolder(private val binding: ItemMyPageTastingTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.textTagName.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TastingTagViewHolder {
        val binding =
            ItemMyPageTastingTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TastingTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TastingTagViewHolder, position: Int) {
        holder.onBind(tastingTagList[position])
    }

    class TastingNoteTagComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean = oldItem == newItem
    }
}
