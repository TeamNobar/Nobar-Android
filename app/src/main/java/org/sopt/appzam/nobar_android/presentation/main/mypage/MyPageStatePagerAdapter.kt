package org.sopt.appzam.nobar_android.presentation.main.mypage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageStatePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> MyPageTastingFragment()
            1-> MyPageTastingFragment()
            else -> error("position $position is cannot exist")
        }
    }
}