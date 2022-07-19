package org.sopt.appzam.nobar_android.presentation.main

import android.os.Bundle
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityHomeBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity
import org.sopt.appzam.nobar_android.presentation.main.home.HomeFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.MyPageFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavi()
    }

    private fun initBottomNavi() {
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, HomeFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.menu_home -> transaction.replace(R.id.fragmentContainerView, HomeFragment())
                R.id.menu_search -> transaction.replace(
                    R.id.fragmentContainerView,
                    SearchFragment()
                )
                R.id.menu_mypage -> transaction.replace(
                    R.id.fragmentContainerView,
                    MyPageFragment()
                )
                else -> error("item id :${it.itemId}) is cannot be selected")
            }
            transaction.commit()
            true
        }

    }
}