package org.sopt.appzam.nobar_android.presentation.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.NobarRecipeResponse
import org.sopt.appzam.nobar_android.databinding.FragmentHomeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.util.ItemDecoration
import kotlin.random.Random

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var nobarRecipeAdapter: NobarRecipeAdapter
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
        binding.recyclerToDoRecipe.addItemDecoration(
            ItemDecoration(
                R.dimen.margin8,
                R.dimen.margin8,
                2
            )
        )
        initAdapter()
    }

    private fun onRefresh() {
        binding.layoutRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.layoutMain.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.black
                    )
                )
                initAdapter()
                binding.layoutRefresh.isRefreshing = false
            }, 1000)
        }
    }

    private fun initAdapter() {
        val randomIntList = mutableListOf<Int>()
        randomIntList.clear()
        while (randomIntList.count() < 5) {
            var num = Random.nextInt(0, 5)
            if (randomIntList.contains(num))
                continue
            else{
                randomIntList.add(num)
            }

        }

        var nobarRecipeList = listOf(
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

}