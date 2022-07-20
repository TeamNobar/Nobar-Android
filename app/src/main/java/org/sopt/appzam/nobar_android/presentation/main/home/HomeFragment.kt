package org.sopt.appzam.nobar_android.presentation.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentHomeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
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
        /*binding.recyclerToDoRecipe.addItemDecoration(
            ItemDecoration(
                R.dimen.margin4,
                R.dimen.margin9,
                2
            )
        )*/

        initObserver()
        laterRecipeAdapter()
        recipeAdapter()
        guideAdapter()
        scrollChange()
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
        laterRecipeAdapter = LaterRecipeAdapter()
        binding.recyclerToDoRecipe.adapter = laterRecipeAdapter
    }

    private fun guideAdapter() {
        guideAdapter = GuideAdapter()
        binding.recyclerGuide.adapter = guideAdapter

    }

    private fun recipeAdapter() {
        val randomIntList = mutableListOf<Int>()
        randomIntList.clear()
        while (randomIntList.count() < 5) {
            var num = Random.nextInt(0, 5)
            if (randomIntList.contains(num))
                continue
            else {
                randomIntList.add(num)
            }

        }

        nobarRecipeAdapter = NobarRecipeAdapter(randomIntList)
        binding.recyclerNobarRecipe.adapter = nobarRecipeAdapter
    }

    private fun initObserver() {
        homeViewModel.laterRecipeList.observe(viewLifecycleOwner) {
            laterRecipeAdapter.submitList(it)
        }

        homeViewModel.guideList.observe(viewLifecycleOwner) {
            guideAdapter.submitList(it)
        }

        homeViewModel.nobarRecipeList.observe(viewLifecycleOwner) {
            nobarRecipeAdapter.submitList(it)
        }
    }

    private fun scrollChange() {
        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (binding.scrollView.scrollY == 0)
                binding.viewLine.visibility = View.GONE
            else
                binding.viewLine.visibility = View.VISIBLE
        }
    }


}