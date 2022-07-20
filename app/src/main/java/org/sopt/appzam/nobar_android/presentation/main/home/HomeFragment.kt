package org.sopt.appzam.nobar_android.presentation.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.GuideResponse
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.RecipeResponse
import org.sopt.appzam.nobar_android.data.remote.response.common.BaseModel
import org.sopt.appzam.nobar_android.data.remote.response.common.GlassModel
import org.sopt.appzam.nobar_android.data.remote.response.common.IngredientDetailModel
import org.sopt.appzam.nobar_android.data.remote.response.common.SkillModel
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

        onRefresh()

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

        laterAdapter()
        initAdapter()
        guideAdapter()
        scrollChange()
    }


    private fun onRefresh() {
        binding.layoutRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                laterAdapter()
                guideAdapter()
                initAdapter()
                binding.layoutRefresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun laterAdapter() {
        val laterRecipeList = listOf(
            RecipeResponse(
                "0",
                "피치크러쉬",
                "Peach Crush",
                listOf<String>(),
                BaseModel("dkd", "보드카"),
                1,
                SkillModel("블렌드", "블렌드"),
                GlassModel("온더락", "s"),
                listOf<IngredientDetailModel>(),
                listOf<String>(),
                "",
                ""
            ),
            RecipeResponse(
                "0",
                "피치크러쉬",
                "Peach Crush",
                listOf<String>(),
                BaseModel("dkd", "보드카"),
                1,
                SkillModel("블렌드", "블렌드"),
                GlassModel("온더락", "s"),
                listOf<IngredientDetailModel>(),
                listOf<String>(),
                "",
                ""
            ),
            RecipeResponse(
                "0",
                "피치크러쉬",
                "Peach Crush",
                listOf<String>(),
                BaseModel("dkd", "보드카"),
                1,
                SkillModel("블렌드", "블렌드"),
                GlassModel("온더락", "s"),
                listOf<IngredientDetailModel>(),
                listOf<String>(),
                "",
                ""
            ),
            RecipeResponse(
                "0",
                "피치크러쉬",
                "Peach Crush",
                listOf<String>(),
                BaseModel("dkd", "보드카"),
                1,
                SkillModel("블렌드", "블렌드"),
                GlassModel("온더락", "s"),
                listOf<IngredientDetailModel>(),
                listOf<String>(),
                "",
                ""
            ),
        )
        laterRecipeAdapter = LaterRecipeAdapter()
        binding.recyclerToDoRecipe.adapter = laterRecipeAdapter
        laterRecipeAdapter.submitList(laterRecipeList)
    }

    private fun guideAdapter() {
        //home - guide
        val guideList = listOf(
            GuideResponse("1", "3we", "sdf", "칵테일 좀 마셔봤니? 칵테일 용어 정복하기"),
            GuideResponse("1", "3we", "sdf", "어떤 잔에 마셔야 더 간지나는 홈텐딩이 될까?"),
            GuideResponse("1", "3we", "sdf", "칵테일 좀 마셔봤니? 칵테일 용어 정복하기"),
            GuideResponse("1", "3we", "sdf", "칵테일 좀 마셔봤니? 칵테일 용어 정복하기"),
            GuideResponse("1", "3we", "sdf", "어떤 잔에 마셔야 더 간지나는 홈텐딩이 될까?")
        )

        guideAdapter = GuideAdapter()
        binding.recyclerGuide.adapter = guideAdapter
        guideAdapter.submitList(guideList)

    }

    private fun initAdapter() {
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
        val nobarRecipeList = listOf(
            NobarRecipeResponse("1213", "블루하와이 마셔봤니?              편의점에서 간편하게 재료 겟!", "sdjfkds"),
            NobarRecipeResponse("122", "디자이너를 힘들게 하는 긴 이름 스트로베리 레몬 그라스 어쩌구", "sd22jfkds"),
            NobarRecipeResponse("1121", "연인과 함께 마셔봐                  섹시 마일드", "안녕하세요"),
            NobarRecipeResponse("11", "집에 남은 소주 어떻게 하려고? 그래서 준비했어 소주 라떼", "s하앙른일s"),
            NobarRecipeResponse("111", "모히또에서 몰디브 한 잔           에서 그 모히또", "sdjfkdsㅁㄴㄹㄴㅇㅁㄹㄴ")
        )
        nobarRecipeAdapter = NobarRecipeAdapter(randomIntList)
        binding.recyclerNobarRecipe.adapter = nobarRecipeAdapter
        nobarRecipeAdapter.submitList(nobarRecipeList)
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