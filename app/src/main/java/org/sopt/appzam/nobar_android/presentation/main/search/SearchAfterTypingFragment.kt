package org.sopt.appzam.nobar_android.presentation.main.search

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentSearchAfterTypingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordWritingFragment.Companion.COCKTAIL_ID
import org.sopt.appzam.nobar_android.presentation.main.record.RecordWritingFragment.Companion.COCKTAIL_NAME
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.FROM
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.NOTE
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.SEARCH
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchAfterAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchIngredientsAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchResultAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchDetailViewModel
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class SearchAfterTypingFragment :
    BaseFragment<FragmentSearchAfterTypingBinding>(R.layout.fragment_search_after_typing) {
    private val searchDetailViewModel: SearchDetailViewModel by activityViewModels()
    private lateinit var recipeAdapter: SearchAfterAdapter
    private lateinit var resultAdapter: SearchResultAdapter
    private lateinit var ingredientAdapter: SearchIngredientsAdapter

    private lateinit var recipeList: ArrayList<RecipeResponse>
    private lateinit var ingredientsList: ArrayList<IngredientResponse>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = searchDetailViewModel
        checkFrom()
        initPreviewAdapter()
        initResultAdapter()
        initIngerdientsAdapter()
        getList()
        observingWord()
        observingResult()
        clickViewAll()
    }

    private fun checkFrom() {
        if (searchDetailViewModel.from == NOTE) {
            binding.recyclerIngredients.visibility = View.GONE
            binding.textIngredient.visibility = View.GONE
        }
        if (searchDetailViewModel.from == SEARCH) {
            binding.recyclerIngredients.visibility = View.VISIBLE
            binding.textIngredient.visibility = View.VISIBLE
        }
    }

    private fun return2Note(cocktialName: String, cocktailId: String) {
        val intent = Intent(requireActivity(), RecordActivity::class.java)
        intent.putExtra(COCKTAIL_NAME, cocktialName)
        intent.putExtra(COCKTAIL_ID, cocktailId)
        activity?.setResult(RESULT_OK, intent)
        activity?.finish()
    }

    private fun go2Recipe(cocktailId: String) {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        intent.putExtra("cocktailId", cocktailId)
        startActivity(intent)
    }

    private fun cocktailPreviewItemClick(recipeResponse: RecipeResponse) {
        if (searchDetailViewModel.from == NOTE) {
            return2Note(recipeResponse.name, recipeResponse.id)
        }
        if (searchDetailViewModel.from == SEARCH) {
            go2Recipe(recipeResponse.id)
        }
    }

    private fun initPreviewAdapter() {
        recipeAdapter = SearchAfterAdapter { cocktailPreviewItemClick(it) }
        binding.recyclerPreview.adapter = recipeAdapter
    }

    private fun initResultAdapter() {
        resultAdapter = SearchResultAdapter { clickResultItem(it) }
        binding.recyclerResult.adapter = resultAdapter
    }

    private fun clickResultItem(recipeResponse: RecipeResponse) {
        if (searchDetailViewModel.from == NOTE) {
            return2Note(recipeResponse.name, recipeResponse.id)
        }
        if (searchDetailViewModel.from == SEARCH) {
            go2Recipe(recipeResponse.id)
        }
    }

    private fun initIngerdientsAdapter() {
        ingredientAdapter = SearchIngredientsAdapter()
        binding.recyclerIngredients.adapter = ingredientAdapter
    }

    private fun clickViewAll() {
        binding.textViewAll.setOnClickListener {
            val intent = Intent(requireActivity(), SearchResultActivity::class.java)
            intent.putExtra("id", searchDetailViewModel.shareKeyword)
            intent.putExtra(FROM, searchDetailViewModel.from)
            startActivity(intent)
        }
    }

    private fun observingWord() {
        searchDetailViewModel.searchingWord.observe(viewLifecycleOwner) {
            searchingRecipes(it)
            searchingIngredients(it)
            searchDetailViewModel.resultAndXVisibility.value = true
        }
    }

    private fun observingResult() {
        searchDetailViewModel.searchResult.observe(viewLifecycleOwner) {
            resultAdapter.submitList(it)
        }
    }

    private fun searchingRecipes(text: String) {
        val tmpList = ArrayList<RecipeResponse>()
        for (i in 0..recipeList.size - 1) {
            if (recipeList.get(i).name.contains(text)) {
                tmpList.add(recipeList.get(i))
            }
        }
        recipeAdapter.findText = text
        recipeAdapter.submitList(tmpList)
    }

    private fun searchingIngredients(text: String) {
        val tmpList = ArrayList<IngredientResponse>()
        for (i in 0..ingredientsList.size - 1) {
            if (ingredientsList.get(i).name.contains(text)) {
                tmpList.add(ingredientsList.get(i))
            }
        }
        ingredientAdapter.findText = text
        ingredientAdapter.submitList(tmpList)
    }

    private fun getList() {
        recipeList = ArrayList<RecipeResponse>()
        searchDetailViewModel.searchKeyRecipe.observe(viewLifecycleOwner) {
            recipeList.addAll(it)
        }

        ingredientsList = ArrayList()
        searchDetailViewModel.searchKeyIngredients.observe(viewLifecycleOwner) {
            ingredientsList.addAll(it)
        }
    }
}