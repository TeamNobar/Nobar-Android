package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.ActivitySearchResultBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordWritingFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.FROM
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.NOTE
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.SEARCH
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchResultViewModel
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private lateinit var searchResultAdapter: SearchResultAdapter
    private var from = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkFrom()
        initResultNetwork()
        initResultAdapter()
        observingData()
        clickX()
    }

    private fun checkFrom() {
        from = intent.getStringExtra(FROM) ?: ""
    }

    private fun initResultAdapter() {
        searchResultAdapter = SearchResultAdapter { clickResultItem(it) }
        binding.recyclerResult.adapter = searchResultAdapter
    }

    private fun initResultNetwork() {
        val id = intent.getStringExtra("id") ?: ""
        searchResultViewModel.initSearchResultNetWork(id)
    }

    private fun clickResultItem(recipeResponse: RecipeResponse) {
        if (from == NOTE) {
            val intent = Intent(this, RecordActivity::class.java)
            intent.putExtra(RecordWritingFragment.COCKTAIL_NAME, recipeResponse.name)
            intent.putExtra(RecordWritingFragment.COCKTAIL_ID, recipeResponse.id)
            setResult(RESULT_OK, intent)
            finish()
        }
        if (from == SEARCH) {
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("cocktailId", recipeResponse.id)
            startActivity(intent)
        }
    }

    private fun observingData() {
        searchResultViewModel.searchResult.observe(this) {
            searchResultAdapter.submitList(it)
        }
    }

    private fun clickX() {
        binding.imageX.setOnClickListener {
            finish()
        }
    }
}