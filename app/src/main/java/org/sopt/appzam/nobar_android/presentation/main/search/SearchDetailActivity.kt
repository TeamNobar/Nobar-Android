package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivitySearchDetailBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchDetailViewModel

class SearchDetailActivity :
    BaseActivity<ActivitySearchDetailBinding>(R.layout.activity_search_detail) {
    private val searchDetailViewModel: SearchDetailViewModel by viewModels()
    var whichFragment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = searchDetailViewModel
        getKeywords()
        focusEditText()
        supportFragmentManager.beginTransaction()
            .add(R.id.searchFragmentContainerView, SearchBeforeTypingFragment()).commit()

        observingWord()
        clickEnter()
        clickX()
    }

    private fun getKeywords() {
        searchDetailViewModel.initSearchDetailNetwork()
    }

    private fun clickEnter() {
        binding.editSearch.setOnEditorActionListener { textView, action, event ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {
                searchDetailViewModel.add2Local(this, binding.editSearch)
                searchDetailViewModel.initSearchResultNetWork(binding.editSearch.text.toString())
                searchDetailViewModel.resultAndXVisibility.value = false
                hideKeyboard(binding.editSearch)
                handled = true
            }
            handled
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun observingWord() {
        searchDetailViewModel.searchingWord.observe(this) {
            if (!it.isNullOrBlank()) {
                change2AfterFragment()
                searchDetailViewModel.resultAndXVisibility.value = true
            } else {
                change2BeforeFragment()
                searchDetailViewModel.resultAndXVisibility.value = false
            }
        }
    }

    private fun focusEditText() {
        binding.editSearch.focusable = 1
        val inputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.editSearch, 0)
    }

    private fun change2AfterFragment() {
        if (whichFragment == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.searchFragmentContainerView, SearchAfterTypingFragment()).commit()
            whichFragment = 1
        }
    }

    private fun change2BeforeFragment() {
        if (whichFragment == 1) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.searchFragmentContainerView, SearchBeforeTypingFragment()).commit()
            whichFragment = 0
        }
    }

    private fun clickX() {
        binding.imageX.setOnClickListener {
            searchDetailViewModel.searchingWord.value = ""
        }
    }
}