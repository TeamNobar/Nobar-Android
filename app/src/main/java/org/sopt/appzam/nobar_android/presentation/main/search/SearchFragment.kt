package org.sopt.appzam.nobar_android.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentSearchBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var spinnerAdapter: SpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initSpinnerAdapter()
        setupSpinnerHandler()
        return binding.root
    }

    private fun initSpinnerAdapter() {
        val sorts = resources.getStringArray(R.array.spinner)
        val sortList = ArrayList<String>()

        for (i in sorts.indices) {
            val sort = sorts[i]
            sortList.add(sort)
        }

        spinnerAdapter = SpinnerAdapter(requireContext(), R.layout.item_spinner, sortList)
        binding.spinner.adapter = spinnerAdapter
    }

    private fun setupSpinnerHandler() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val sort = binding.spinner.getItemAtPosition(position)
                //sort 넣어서 서버통신
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}