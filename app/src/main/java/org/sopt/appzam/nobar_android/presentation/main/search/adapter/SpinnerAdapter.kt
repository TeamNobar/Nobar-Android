package org.sopt.appzam.nobar_android.presentation.main.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import org.sopt.appzam.nobar_android.databinding.ItemSpinnerBinding

class SpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val values: MutableList<String>
) : ArrayAdapter<String>(context, resId, values) {

    override fun getCount() = values.size
    override fun getItem(position: Int) = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSpinnerBinding.inflate(layoutInflater, parent, false)
        val model = values[position]
        binding.textSort.text = model
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSpinnerBinding.inflate(layoutInflater, parent, false)
        val model = values[position]
        binding.textSort.text = model
        return binding.root
    }
}