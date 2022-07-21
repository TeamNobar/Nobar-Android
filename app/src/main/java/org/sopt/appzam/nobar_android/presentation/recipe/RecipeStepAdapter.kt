package org.sopt.appzam.nobar_android.presentation.recipe

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemStepBinding

class RecipeStepAdapter(private val initcolorList: () -> ArrayList<Int>, private val context : Context) :
    ListAdapter<String, RecipeStepAdapter.RecipeStepViewHolder>(
        RecipeStepComparator()
    ) {
    class RecipeStepViewHolder(private val binding: ItemStepBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String, initcolorList: () -> ArrayList<Int>, context : Context) {
            val colorList = initcolorList()
            val position: Int = adapterPosition + 1
            binding.textDescription.text = data
            binding.textStep.text = "step" + position.toString()
            binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, colorList[position - 1]))
        }
    }

    class RecipeStepComparator() : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStepBinding.inflate(layoutInflater, parent, false)
        return RecipeStepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int) {
        holder.onBind(getItem(position), initcolorList, context)
    }
}