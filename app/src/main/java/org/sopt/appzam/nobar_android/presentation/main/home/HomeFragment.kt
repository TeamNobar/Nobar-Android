package org.sopt.appzam.nobar_android.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentHomeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.recipe.RecipeActivity
import kotlin.random.Random

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var nobarRecipeAdapter: NobarRecipeAdapter
    private lateinit var guideAdapter: GuideAdapter
    private lateinit var laterRecipeAdapter: LaterRecipeAdapter
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.homeViewModel = homeViewModel

        onRefresh()
        initNetwork()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeObserver()
        //Adapter 연결
        laterRecipeAdapter()
        recipeAdapter()
        guideAdapter()

        scrollChange()
        seeAllClick()
    }

    private fun onRefresh() {
        binding.layoutRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                laterRecipeAdapter()
                guideAdapter()
                recipeAdapter()

                binding.layoutRefresh.isRefreshing = false
            }, 1000)
        }
    }


    private fun initNetwork() {
        homeViewModel.homeNetwork()
    }


    //Adapter 선언
    private fun laterRecipeAdapter() {
        //레시피 클릭시 칵테일 상세보기로 이동로직
        laterRecipeAdapter = LaterRecipeAdapter {
            val intent = Intent(requireContext(), RecipeActivity::class.java)
            intent.putExtra("recipeId", it.id)
            startActivity(intent)
        }
        binding.recyclerToDoRecipe.adapter = laterRecipeAdapter
    }

    private fun guideAdapter() {
        guideAdapter = GuideAdapter {
            val intent = Intent(requireContext(), HomeLaterRecipeDetailActivity::class.java)
            intent.putExtra("guideId", it.id)
            startActivity(intent)
        }
        binding.recyclerGuide.adapter = guideAdapter

    }

    private fun recipeAdapter() {
        val randomIntList = mutableListOf<Int>()
        randomIntList.clear()
        while (randomIntList.count() < 5) {
            val num = Random.nextInt(0, 5)
            if (randomIntList.contains(num))
                continue
            else {
                randomIntList.add(num)
            }

        }

        nobarRecipeAdapter = NobarRecipeAdapter(randomIntList) {
            val intent = Intent(requireContext(), HomeLaterRecipeDetailActivity::class.java)
            intent.putExtra("nobarRecipeId", it.id)
            startActivity(intent)
        }
        binding.recyclerNobarRecipe.adapter = nobarRecipeAdapter
    }

    //viewModel 관련
    private fun homeObserver() {
        homeViewModel.laterRecipeList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.constraintStart.visibility = View.VISIBLE
                binding.recyclerToDoRecipe.visibility = View.GONE
            } else {
                binding.constraintStart.visibility = View.GONE
                binding.recyclerToDoRecipe.visibility = View.VISIBLE
                laterRecipeAdapter.submitList(it)
            }
        }

        homeViewModel.guideList.observe(viewLifecycleOwner) {
            guideAdapter.submitList(it)
        }

        homeViewModel.nobarRecipeList.observe(viewLifecycleOwner) {
            nobarRecipeAdapter.submitList(it)
        }
    }

    //스크롤 했을 때 선 생기도록 설정
    private fun scrollChange() {
        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (binding.scrollView.scrollY == 0)
                binding.viewLine.visibility = View.GONE
            else
                binding.viewLine.visibility = View.VISIBLE
        }
    }

    private fun seeAllClick() {
        binding.textAllSee.setOnClickListener {
            val intent = Intent(requireActivity(), HomeLaterRecipeDetailActivity::class.java)
            startActivity(intent)
        }
    }


}