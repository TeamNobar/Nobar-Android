package org.sopt.appzam.nobar_android.presentation.main.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.databinding.ItemTasteBinding

class RecordTagAdapter() : ListAdapter<TagResponse, RecordTagAdapter.RecordTagViewHolder>(RecordTagComparator()) {
    class RecordTagViewHolder(private val binding : ItemTasteBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(data : TagResponse){

        }
    }

    class RecordTagComparator():DiffUtil.ItemCallback<TagResponse>(){
        override fun areItemsTheSame(oldItem: TagResponse, newItem: TagResponse): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: TagResponse, newItem: TagResponse): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordTagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTasteBinding.inflate(layoutInflater)
        return RecordTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordTagViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}