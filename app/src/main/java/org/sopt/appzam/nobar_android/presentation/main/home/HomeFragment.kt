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
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipesResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
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
                initNetwork()
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
            intent.putExtra("cocktailId", it.id)
            startActivity(intent)
        }
        binding.recyclerToDoRecipe.adapter = laterRecipeAdapter
    }

    private fun guideAdapter() {
        guideAdapter = GuideAdapter {
            val intent = Intent(requireContext(), GuideDetailActivity::class.java)
            intent.putExtra("guideId", it.id)
            startActivity(intent)
        }
        binding.recyclerGuide.adapter = guideAdapter

    }

    private fun recipeAdapter() {
        //색깔 랜덤 지정을 위한 로직
        var randomIntList = mutableListOf<Int>()
        randomIntList.clear()
        while (randomIntList.count() < 5) {
            val num = Random.nextInt(0, 5)
            if (randomIntList.contains(num))
                continue
            else {
                randomIntList.add(num)
            }

        }

        //데이터 랜덤을 위한 로직
        val nobarList = listOf(
            NobarRecipesResponse("62dafdc9c146e2cc2d52f3e8", R.string.homeAdapter1,R.drawable.ic_img_mojito),
            NobarRecipesResponse("62dafdc9c146e2cc2d52f3e2", R.string.homeAdapter2, R.drawable.ic_img_bluehawaii),
            NobarRecipesResponse("62dafdc9c146e2cc2d52f3d8", R.string.homeAdapter3, R.drawable.ic_img_sexonthebeach),
            NobarRecipesResponse("62dafdc9c146e2cc2d52f422", R.string.homeAdapter4, R.drawable.ic_img_peachcrush),
            NobarRecipesResponse("62dafdc9c146e2cc2d52f417", R.string.homeAdapter5, R.drawable.ic_img_cinderella)
        )

      /* var randomRecipeList = mutableListOf<Int>()
        randomRecipeList.clear()
        var nobarRecipeList = mutableListOf<NobarRecipesResponse>() //어뎁터에 전달할 리스트

        randomRecipeList.clear() //데이터 랜덤 지정
        while (randomReci9peList.count() < 5) {
            val num = Random.nextInt(0, 5)
            if (randomRecipeList.contains(num))
                continue
            else {
                nobarRecipeList.add(nobarList[num])
            }
        }*/

        nobarRecipeAdapter = NobarRecipeAdapter(randomIntList) {
            val intent = Intent(requireContext(), RecipeActivity::class.java)
            intent.putExtra("cocktailId", it.id)
            startActivity(intent)
        }
        binding.recyclerNobarRecipe.adapter = nobarRecipeAdapter
        nobarRecipeAdapter.submitList(nobarList)
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

        /* homeViewModel.nobarRecipeList.observe(viewLifecycleOwner) {
             nobarRecipeAdapter.submitList(it)
         }*/
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