package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivitySearchResultBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchResultViewModel
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("asdf", "oncreate")
        initResultNetwork()
        initResultAdapter()
        observingData()
    }

    private fun initResultAdapter() {
        Log.d("asdf", "initadapter first")
        searchResultAdapter = SearchResultAdapter { clickResultItem(it) }
        binding.recyclerResult.adapter = searchResultAdapter
        Log.d("asdf", "initadapter end")
    }

    private fun initResultNetwork() {
        val id = intent.getStringExtra("id") ?: ""
        searchResultViewModel.initSearchResultNetWork(id)
    }

    private fun clickResultItem(id: String) {
        val intent = Intent(this, RecipeActivity::class.java)
        intent.putExtra("cocktailId", id)
        startActivity(intent)
    }

    private fun observingData() {
        searchResultViewModel.searchResult.observe(this) {
            searchResultAdapter.submitList(it)
        }
    }
}