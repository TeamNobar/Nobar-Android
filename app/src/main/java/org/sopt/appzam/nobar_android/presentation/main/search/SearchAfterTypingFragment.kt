package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.IngredientResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentSearchAfterTypingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
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
        initPreviewAdapter()
        initResultAdapter()
        initIngerdientsAdapter()
        getList()
        observingWord()
        observingResult()
        clickViewAll()
    }

    private fun cocktailPreviewItemClick() {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        startActivity(intent)
    }

    private fun initPreviewAdapter() {
        recipeAdapter = SearchAfterAdapter { cocktailPreviewItemClick() }
        binding.recyclerPreview.adapter = recipeAdapter
    }

    private fun initResultAdapter() {
        resultAdapter = SearchResultAdapter { clickResultItem(it) }
        binding.recyclerResult.adapter = resultAdapter
    }

    private fun clickResultItem(id: String) {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        intent.putExtra("cocktailId", id)
        startActivity(intent)
    }

    private fun initIngerdientsAdapter() {
        ingredientAdapter = SearchIngredientsAdapter()
        binding.recyclerIngredients.adapter = ingredientAdapter
    }

    private fun clickViewAll() {
        binding.textViewAll.setOnClickListener {
            val intent = Intent(requireActivity(), SearchResultActivity::class.java)
            intent.putExtra("id", searchDetailViewModel.shareKeyword)
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