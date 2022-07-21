package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentMyPageLaterRecipeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.adapter.MyLaterRecipeAdapter

class MyPageLaterRecipeFragment : BaseFragment<FragmentMyPageLaterRecipeBinding>(R.layout.fragment_my_page_later_recipe) {
    private val myPageViewModel by viewModels<MyPageViewModel>()
    private lateinit var laterRecipeAdapter: MyLaterRecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.myPageLaterRecipeViewModel = myPageViewModel
        initNetwork()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        laterRecipeAdapter()
        initObserver()
    }

    private fun laterRecipeAdapter(){
        laterRecipeAdapter = MyLaterRecipeAdapter()
        binding.recyclerMyPageRecipe.adapter = laterRecipeAdapter
    }

    private fun initNetwork(){
        myPageViewModel.myPageNetwork()
    }

    private fun initObserver(){
        myPageViewModel.laterRecipes.observe(viewLifecycleOwner){
            laterRecipeAdapter.submitList(it)
        }
    }

}