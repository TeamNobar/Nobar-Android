package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentMyPageTastingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity

class MyPageTastingFragment :
    BaseFragment<FragmentMyPageTastingBinding>(R.layout.fragment_my_page_tasting) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNewNote.setOnClickListener {
            val intent = Intent(requireActivity(), RecordActivity::class.java)
            startActivity(intent)
        }
    }

}