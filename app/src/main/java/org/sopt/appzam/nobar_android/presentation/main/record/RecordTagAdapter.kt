package org.sopt.appzam.nobar_android.presentation.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.databinding.ItemTasteBinding

class RecordTagAdapter(private val itemClick: (TagResponse) -> Unit) :
    ListAdapter<TagResponse, RecordTagAdapter.RecordTagViewHolder>(RecordTagComparator()) {

    inner class RecordTagViewHolder(private val binding: ItemTasteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagResponse) {
            binding.model = data
            val url = if(data.isSelected) data.activeIcon else data.inActiveIcon
            Glide.with(binding.imageIcon).load(url).error(R.drawable.cocktail_sample)
                .into(binding.imageIcon)

            itemView.setOnClickListener {
                itemClick(data)
                notifyItemChanged(adapterPosition)
            }
        }
    }

    class RecordTagComparator() : DiffUtil.ItemCallback<TagResponse>() {
        override fun areItemsTheSame(oldItem: TagResponse, newItem: TagResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TagResponse, newItem: TagResponse): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordTagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTasteBinding.inflate(layoutInflater, parent, false)
        return RecordTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordTagViewHolder, position: Int) {
        holder.onBind(getItem(position))
        //holder.itemView.isSelected = selectedItems.get(position, false)
    }
}