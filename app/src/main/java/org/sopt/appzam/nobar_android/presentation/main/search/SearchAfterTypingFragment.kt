package org.sopt.appzam.nobar_android.presentation.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentSearchAfterTypingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.search.adapter.SearchAfterAdapter
import org.sopt.appzam.nobar_android.presentation.main.search.viewmodel.SearchDetailViewModel
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity

class SearchAfterTypingFragment :
    BaseFragment<FragmentSearchAfterTypingBinding>(R.layout.fragment_search_after_typing) {
    private val searchDetailViewModel: SearchDetailViewModel by activityViewModels()
    private lateinit var adapter: SearchAfterAdapter
    private lateinit var dummyList: ArrayList<RecipeResponse>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = searchDetailViewModel
        initAdapter()
        initDummy()
        observingWord()
    }

    private fun cocktailPreviewItemClick() {
        val intent = Intent(requireActivity(), RecipeActivity::class.java)
        startActivity(intent)
    }

    private fun initAdapter() {
        adapter = SearchAfterAdapter { cocktailPreviewItemClick() }
        binding.recyclerPreview.adapter = adapter
    }

    private fun observingWord() {
        searchDetailViewModel.searchingWord.observe(viewLifecycleOwner) {
            searching(it)
            searchDetailViewModel.visibility.value = false
        }
    }

    private fun searching(text: String) {
        val tmpList = ArrayList<RecipeResponse>()
        for (i in 0..dummyList.size - 1) {
            if (dummyList.get(i).name.contains(text)) {
                tmpList.add(dummyList.get(i))
            }
        }
        adapter.findText = text
        adapter.submitList(tmpList)
    }

    private fun initDummy() {
        dummyList = ArrayList()
        dummyList.addAll(
            listOf(
                RecipeResponse("0", "피치 크러시"),
                RecipeResponse("1", "크피치 러시"),
                RecipeResponse("2", "크러피 치시"),
                RecipeResponse("3", "크러시피치"),
                RecipeResponse("3", "응 아니야"),
            )
        )
    }
}