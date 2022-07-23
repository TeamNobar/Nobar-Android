package org.sopt.appzam.nobar_android.presentation.main.record

import android.os.Bundle
import android.view.View
import android.view.View.OnTouchListener
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentRecordReadBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment


class RecordReadFragment : BaseFragment<FragmentRecordReadBinding>(R.layout.fragment_record_read) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private lateinit var tagAdapter: RecordTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = recordViewModel
        initAdapter()
        putDate()
        putRating()
        initTagList()
        clickX()
    }

    private fun initAdapter() {
        tagAdapter = RecordTagAdapter { }
        binding.recyclerView.adapter = tagAdapter
    }

    private fun initTagList() {
        recordViewModel.tagList.observe(viewLifecycleOwner) {
            tagAdapter.submitList(it)
        }
    }

    private fun clickX(){
        binding.imageX.setOnClickListener {
            activity?.finish()
        }
    }

    private fun putDate() {
        recordViewModel.drinkingDate.observe(viewLifecycleOwner) {
            val yearRange = IntRange(0, 3)
            val monthRange = IntRange(5, 6)
            val dayRange = IntRange(8, 9)
            val year = it.slice(yearRange).toInt()
            val month = it.slice(monthRange).toInt()
            val day = it.slice(dayRange).toInt()
            binding.textWhen.text = String.format("%d년 %2d월 %2d일", year, month, day)
        }
    }

    private fun putRating() {
        recordViewModel.rating.observe(viewLifecycleOwner) {
            binding.rotationRatingBar.rating = it.toFloat()
        }
        binding.rotationRatingBar.setOnTouchListener(OnTouchListener { v, event -> true })
    }
}