package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.databinding.ItemSearchIngredientsBinding

class SearchIngredientsAdapter() :
    ListAdapter<IngredientResponse, SearchIngredientsAdapter.SearchIngredientsViewHolder>(
        SearchIngredientsComparator()
    ) {
    var findText = ""

    class SearchIngredientsViewHolder(private val binding: ItemSearchIngredientsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(text: SpannableStringBuilder) {
            binding.textName.text = text
            itemView.setOnClickListener {

            }
        }
    }

    class SearchIngredientsComparator() : DiffUtil.ItemCallback<IngredientResponse>() {
        override fun areItemsTheSame(
            oldItem: IngredientResponse,
            newItem: IngredientResponse
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: IngredientResponse,
            newItem: IngredientResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchIngredientsViewHolder {
        Log.d("asdf", "ingredients oncreateviewholder")
        Log.d("asdf", "currentlist size3 : ${currentList.size}")
        Log.d("asdf", "word : $findText")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchIngredientsBinding.inflate(layoutInflater, parent, false)
        return SearchIngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchIngredientsViewHolder, position: Int) {
        holder.onBind(makeBold(getItem(position).name, findText))
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