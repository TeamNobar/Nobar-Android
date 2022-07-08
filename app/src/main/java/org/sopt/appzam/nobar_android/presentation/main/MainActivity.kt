package org.sopt.appzam.nobar_android.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityMainBinding
import org.sopt.appzam.nobar_android.presentation.main.home.HomeFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.MypageFragment
import org.sopt.appzam.nobar_android.presentation.main.record.RecordFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MainVpAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter(){
        adapter = MainVpAdapter(this)
        binding.viewPager.adapter=adapter
    }

    private fun initBottomNavi(){
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNavigationView.menu.getItem(position).isChecked=true
            }
        })

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home->{
                    binding.viewPager.currentItem=0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search->{
                    binding.viewPager.currentItem=1
                    return@setOnItemSelectedListener true
                }
                R.id.menu_record->{
                    binding.viewPager.currentItem=2
                    return@setOnItemSelectedListener true
                }
                else->{
                    binding.viewPager.currentItem=3
                    return@setOnItemSelectedListener true
                }
            }
        }
    }
}