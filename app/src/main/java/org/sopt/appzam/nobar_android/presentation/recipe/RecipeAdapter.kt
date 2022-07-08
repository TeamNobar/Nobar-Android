package org.sopt.appzam.nobar_android.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.recipe.RecipeData
import org.sopt.appzam.nobar_android.databinding.ItemIngredientBinding

class RecipeAdapter :
    ListAdapter<RecipeData.Ingredient, RecipeAdapter.RecipeViewHolder>(RecipeComparator()) {

    class RecipeViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecipeData.Ingredient) {
            //binding.model = data
        }
    }

    class RecipeComparator : DiffUtil.ItemCallback<RecipeData.Ingredient>() {
        override fun areItemsTheSame(
            oldItem: RecipeData.Ingredient,
            newItem: RecipeData.Ingredient
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: RecipeData.Ingredient,
            newItem: RecipeData.Ingredient
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(layoutInflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }
}