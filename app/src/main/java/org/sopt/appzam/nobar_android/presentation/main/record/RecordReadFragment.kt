package org.sopt.appzam.nobar_android.presentation.main.record

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentRecordReadBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class RecordReadFragment : BaseFragment<FragmentRecordReadBinding>(R.layout.fragment_record_read) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private lateinit var tagAdapter: RecordTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel=recordViewModel
        initAdapter()
    }

    private fun initAdapter() {
        tagAdapter = RecordTagAdapter()
        binding.recyclerView.adapter = tagAdapter
    }
}