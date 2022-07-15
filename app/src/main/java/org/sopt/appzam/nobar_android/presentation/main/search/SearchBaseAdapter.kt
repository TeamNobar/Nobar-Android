package org.sopt.appzam.nobar_android.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseResponse
import org.sopt.appzam.nobar_android.databinding.ItemSearchBaseBinding

class SearchBaseAdapter :
    ListAdapter<BaseResponse, SearchBaseAdapter.SearchBaseViewHolder>(SearchBaseComparator()) {

    class SearchBaseViewHolder(private val binding: ItemSearchBaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BaseResponse) {
            binding.model = data
            Glide.with(binding.imageBase).load(data.url).into(binding.imageBase)
        }
    }

    class SearchBaseComparator : DiffUtil.ItemCallback<BaseResponse>() {
        override fun areItemsTheSame(oldItem: BaseResponse, newItem: BaseResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BaseResponse, newItem: BaseResponse): Boolean {
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