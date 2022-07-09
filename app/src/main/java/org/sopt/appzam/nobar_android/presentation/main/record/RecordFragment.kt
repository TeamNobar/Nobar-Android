package org.sopt.appzam.nobar_android.presentation.main.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentRecordBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class RecordFragment : BaseFragment<FragmentRecordBinding>(R.layout.fragment_record) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}