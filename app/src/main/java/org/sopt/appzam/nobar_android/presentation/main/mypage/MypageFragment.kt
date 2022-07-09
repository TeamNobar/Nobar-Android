package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentMypageBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class MypageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }
}