package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentSearchBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.FROM
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.SEARCH
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchBaseAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SpinnerAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchViewModel
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var spinnerAdapter: SpinnerAdapter
    private lateinit var baseAdapter: SearchBaseAdapter
    private lateinit var searchResultAdapter: SearchResultAdapter
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //base tag
        getBaseList()
        initBaseAdapter()
        observingBaseTagList()

        //search result
        initSearchResultAdapter()
        observingSearchingResult()

        //spinner
        initSpinnerAdapter()
        setupSpinnerHandler()

        clickSearchBar()
    }

    private fun getBaseList() {
        searchViewModel.initBaseNetwork()
    }

    private fun initBaseAdapter() {
        baseAdapter = SearchBaseAdapter { baseItemClick(it) }
        binding.recyclerBase.adapter = baseAdapter
    }

    private fun baseItemClick(base: String) {
        searchViewModel.initBaseSearchNetwork(base)
    }

    private fun observingBaseTagList() {
        searchViewModel.baseTagList.observe(viewLifecycleOwner) {
            baseAdapter.submitList(it)
        }
    }

    private fun initSearchResultAdapter() {
        searchResultAdapter = SearchResultAdapter { clickResultItem(it) }
        binding.recyclerResult.adapter = searchResultAdapter
    }

    private fun clickResultItem(recipeResponse : RecipeResponse) {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        intent.putExtra("cocktailId", recipeResponse.id)
        startActivity(intent)
    }

    private fun observingSearchingResult() {
        searchViewModel.baseSearchResultList.observe(viewLifecycleOwner) {
            searchResultAdapter.submitList(it)
        }
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

    private fun clickSearchBar() {
        binding.editSearch.setOnClickListener {
            val intent = Intent(requireActivity(), SearchDetailActivity::class.java)
            intent.putExtra(FROM, SEARCH)
            startActivity(intent)
        }
    }
}