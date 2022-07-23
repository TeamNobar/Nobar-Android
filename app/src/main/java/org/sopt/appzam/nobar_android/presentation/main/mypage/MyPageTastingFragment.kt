package org.sopt.appzam.nobar_android.presentation.main.mypage

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.TastingNoteResponse
import org.sopt.appzam.nobar_android.databinding.FragmentMyPageTastingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.adapter.MultiViewAdapter
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.MODE
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.NEW
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.NOTE_ID
import org.sopt.appzam.nobar_android.presentation.main.record.RecordActivity.Companion.READ

class MyPageTastingFragment :
    BaseFragment<FragmentMyPageTastingBinding>(R.layout.fragment_my_page_tasting) {
    private lateinit var multiViewAdapter: MultiViewAdapter
    private val myPageViewModel by viewModels<MyPageViewModel>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.myPageTastingViewModel = myPageViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tastingNoteAdapter()
        initObserver()
        initNetwork()

        clickWritingNote()
        setWritingResult()
    }

    private fun clickWritingNote() {
        binding.buttonNewNote.setOnClickListener {
            val intent = Intent(requireActivity(), RecordActivity::class.java)
            intent.putExtra(MODE, NEW)
            resultLauncher.launch(intent)
        }
    }

    private fun setWritingResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    myPageViewModel.reMyPageNetwork()
                }
            }
    }

    private fun tastingNoteAdapter() {
        multiViewAdapter = MultiViewAdapter { itemClick(it) }
        binding.recyclerMyPageTasting.adapter = multiViewAdapter
    }

    private fun itemClick(tastingNoteResponse: TastingNoteResponse) {
        val intent = Intent(requireContext(), RecordActivity::class.java)
        intent.putExtra(MODE, READ)
        intent.putExtra(NOTE_ID, tastingNoteResponse.id)
        startActivity(intent)
    }

    private fun initNetwork() {
        myPageViewModel.myPageNetwork()
    }

    private fun initObserver() {
        myPageViewModel.tastingNotes.observe(viewLifecycleOwner) {
            multiViewAdapter.submitList(it)
        }
    }
}