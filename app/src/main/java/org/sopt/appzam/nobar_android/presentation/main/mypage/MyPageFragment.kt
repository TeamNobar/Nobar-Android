package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentMypageBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.home.HomeViewModel

class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {
    private lateinit var adapter : MyPageStatePagerAdapter
    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.myPageViewModel = myPageViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAadapter()
        clickToggle()
        connectViewPager2Toggle()
    }

    private fun connectViewPager2Toggle() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> checkTastingNote()
                    1 -> checkLaterRecipe()
                }
            }
        })
    }

    private fun checkTastingNote() {
        with(binding) {
            radioTastingNote.isChecked = true
            radioLaterRecipe.isChecked = false
            changeRadioButtonText(radioTastingNote, radioLaterRecipe)
        }
    }

    private fun checkLaterRecipe() {
        with(binding) {
            radioLaterRecipe.isChecked = true
            radioTastingNote.isChecked = false
            changeRadioButtonText(radioLaterRecipe, radioTastingNote)
        }
    }

    private fun changeRadioButtonText(firstRadio: RadioButton, secondRadio: RadioButton){
        firstRadio.setTypeface(resources.getFont(R.font.pretendard_semibold))
        secondRadio.setTypeface(resources.getFont(R.font.pretendard_regular))
        firstRadio.setTextColor(ContextCompat.getColor(requireContext(), R.color.navy000782))
        secondRadio.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayAAABBE))
    }

    private fun clickToggle() {
        binding.radioTastingNote.setOnClickListener {
            clickRadioTastingNote()
        }

        binding.radioLaterRecipe.setOnClickListener {
            clickRadioLaterRecipe()
        }
    }

    private fun clickRadioTastingNote(){
        if (!binding.radioTastingNote.isSelected) {
            binding.viewPager.currentItem = 0
        }
    }

    private fun clickRadioLaterRecipe(){
        if (!binding.radioLaterRecipe.isSelected) {
            binding.viewPager.currentItem = 1
        }
    }

    private fun initAadapter() {
        adapter = MyPageStatePagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
    }
}