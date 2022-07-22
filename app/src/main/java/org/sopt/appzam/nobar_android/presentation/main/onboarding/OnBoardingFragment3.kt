package org.sopt.appzam.nobar_android.presentation.main.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentOnBoarding3Binding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment


class OnBoardingFragment3 : BaseFragment<FragmentOnBoarding3Binding>(R.layout.fragment_on_boarding3) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

}