package org.sopt.appzam.nobar_android.presentation.main.search

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.databinding.ItemSearchBaseBinding

class SearchBaseAdapter : ListAdapter<> {

    class SearchBaseViewHolder(private val binding : ItemSearchBaseBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : IngredientResponse){

        }
    }
}