package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ItemSearchPreviewBinding

class SearchAfterAdapter(private val itemClick: (RecipeResponse) -> Unit) :
    ListAdapter<RecipeResponse, SearchAfterAdapter.SearchAfterViewHolder>(SearchAfterComparator()) {
    var findText = ""

    class SearchAfterViewHolder(private val binding: ItemSearchPreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: RecipeResponse,
            text: SpannableStringBuilder,
            itemClick: (RecipeResponse) -> Unit
        ) {
            binding.textName.text = text
            itemView.setOnClickListener {
                itemClick(data)
            }
        }
    }

    class SearchAfterComparator() : DiffUtil.ItemCallback<RecipeResponse>() {
        override fun areItemsTheSame(oldItem: RecipeResponse, newItem: RecipeResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeResponse, newItem: RecipeResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAfterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchPreviewBinding.inflate(layoutInflater, parent, false)
        return SearchAfterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAfterViewHolder, position: Int) {
        holder.onBind(getItem(position), makeBold(getItem(position).name, findText), itemClick)
    }

    fun makeBold(fulltext: String, findText: String): SpannableStringBuilder {
        val str = SpannableStringBuilder(fulltext)
        val startInt = fulltext.indexOf(findText)
        val endInt = startInt + findText.length
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            startInt,
            endInt,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        return str
    }
}