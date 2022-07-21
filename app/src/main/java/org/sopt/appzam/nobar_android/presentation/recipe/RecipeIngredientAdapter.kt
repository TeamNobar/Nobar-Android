package org.sopt.appzam.nobar_android.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.common.IngredientDetailModel
import org.sopt.appzam.nobar_android.databinding.ItemIngredientBinding

class RecipeIngredientAdapter() :
    ListAdapter<IngredientDetailModel, RecipeIngredientAdapter.RecipeIngredientViewHolder>(
        RecipeIngredientComparator()
    ) {
    class RecipeIngredientViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: IngredientDetailModel) {
            binding.model = data
        }
    }

    class RecipeIngredientComparator() : DiffUtil.ItemCallback<IngredientDetailModel>() {
        override fun areItemsTheSame(
            oldItem: IngredientDetailModel,
            newItem: IngredientDetailModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: IngredientDetailModel,
            newItem: IngredientDetailModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(layoutInflater, parent, false)
        return RecipeIngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeIngredientViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}