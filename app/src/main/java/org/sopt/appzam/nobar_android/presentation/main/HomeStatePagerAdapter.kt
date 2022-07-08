package org.sopt.appzam.nobar_android.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.appzam.nobar_android.presentation.main.home.HomeFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.MypageFragment
import org.sopt.appzam.nobar_android.presentation.main.record.RecordFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchFragment

class HomeStatePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> RecordFragment()
            3 -> MypageFragment()
            else -> error("position $position is cannot exist")
        }
    }

}