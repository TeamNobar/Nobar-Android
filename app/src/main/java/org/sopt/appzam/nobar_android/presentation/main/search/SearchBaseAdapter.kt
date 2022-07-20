package org.sopt.appzam.nobar_android.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.databinding.ItemSearchBaseBinding

class SearchBaseAdapter :
    ListAdapter<BaseModel, SearchBaseAdapter.SearchBaseViewHolder>(SearchBaseComparator()) {

    class SearchBaseViewHolder(private val binding: ItemSearchBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BaseModel) {
            binding.model = data
            Glide.with(binding.imageBase).load(data.id).into(binding.imageBase)
        }
    }

    class SearchBaseComparator : DiffUtil.ItemCallback<BaseModel>() {
        override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBaseBinding.inflate(layoutInflater, parent, false)
        return SearchBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchBaseViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}