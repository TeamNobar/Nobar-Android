package org.sopt.appzam.nobar_android.presentation.main.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageViewPagerAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    private val fragments = mutableListOf<Fragment>()
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}