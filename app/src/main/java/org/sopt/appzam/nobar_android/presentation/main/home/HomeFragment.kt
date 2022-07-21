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
            NobarRecipesResponse("1", "몰디브 아니죠. 쿠바가 원조입니다.\n 모히또 가서 쿠바 한 잔?", R.drawable.ic_img_bluehawaii),
            NobarRecipesResponse("2", "달콤한 화이트 럼과 떠나는\n와이키키 해변, 블루하와이", R.drawable.ic_img_bluehawaii),
            NobarRecipesResponse("3", "뜨거운 여름엔 이 칵테일!\n섹스 온 더 비치", R.drawable.ic_img_sexonthebeach),
            NobarRecipesResponse("4", "그 그 그 복숭아 맛 나는 칵테일\n달달한 아 그 피치 크러쉬!", R.drawable.ic_img_peachcrush),
            NobarRecipesResponse("5", "12시 귀가를 보장하는\n논알콜 칵테일, 신데렐라", R.drawable.ic_img_cinderella)
        )

      /* var randomRecipeList = mutableListOf<Int>()
        randomRecipeList.clear()
        var nobarRecipeList = mutableListOf<NobarRecipesResponse>() //어뎁터에 전달할 리스트

        randomRecipeList.clear() //데이터 랜덤 지정
        while (randomRecipeList.count() < 5) {
            val num = Random.nextInt(0, 5)
            if (randomRecipeList.contains(num))
                continue
            else {
                nobarRecipeList.add(nobarList[num])
            }
        }*/

        nobarRecipeAdapter = NobarRecipeAdapter(randomIntList) {
            val intent = Intent(requireContext(), HomeLaterRecipeDetailActivity::class.java)
            intent.putExtra("nobarRecipeId", it.id)
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