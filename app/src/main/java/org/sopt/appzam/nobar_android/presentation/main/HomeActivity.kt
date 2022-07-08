package org.sopt.appzam.nobar_android.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeStatePagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter() {
        adapter = HomeStatePagerAdapter(this)
        binding.viewPager.adapter = adapter
    }

    private fun initBottomNavi() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    binding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    binding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                R.id.menu_record -> {
                    binding.viewPager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.viewPager.currentItem = 3
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}