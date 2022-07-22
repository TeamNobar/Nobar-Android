package org.sopt.appzam.nobar_android.presentation.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipesResponse
import org.sopt.appzam.nobar_android.databinding.ItemHomeNobarRecipeBinding

class NobarRecipeAdapter(
    private val randomIntList: List<Int>,
    private val itemClick: (NobarRecipesResponse) -> (Unit)
) :
    ListAdapter<NobarRecipesResponse, NobarRecipeAdapter.NobarRecipeViewHolder>(NobarRecipeComparator()) {

    class NobarRecipeViewHolder(
        private val binding: ItemHomeNobarRecipeBinding,
        private val itemClick: (NobarRecipesResponse) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: NobarRecipesResponse, colorInt: Int) {
            binding.nobarRecipeItem = data
            when (colorInt) {
                0 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box)
                1 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box2)
                2 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box3)
                3 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box4)
                4 -> binding.layout.setBackgroundResource(R.drawable.home_navy_box5)
            }

            when(data.id) {
                "1" -> binding.textName.setText(R.string.homeAdapter1)
                "2" -> binding.textName.setText(R.string.homeAdapter2)
                "3" -> binding.textName.setText(R.string.homeAdapter3)
                "4" -> binding.textName.setText(R.string.homeAdapter4)
                "5" -> binding.textName.setText(R.string.homeAdapter5)
            }

            binding.root.setOnClickListener {
                itemClick(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NobarRecipeViewHolder {
        val binding =
            ItemHomeNobarRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NobarRecipeViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: NobarRecipeViewHolder, position: Int) {
        Log.d("안녕", position.toString())
        holder.onBind(getItem(position), randomIntList[position])
    }


    class NobarRecipeComparator : DiffUtil.ItemCallback<NobarRecipesResponse>() {
        override fun areItemsTheSame(
            oldItem: NobarRecipesResponse,
            newItem: NobarRecipesResponse
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NobarRecipesResponse,
            newItem: NobarRecipesResponse
        ): Boolean =
            oldItem == newItem
    }
}