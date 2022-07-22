package org.sopt.appzam.nobar_android.presentation.main.record

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.databinding.FragmentRecordWritingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.FROM
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.NOTE
import java.util.*

class RecordWritingFragment :
    BaseFragment<FragmentRecordWritingBinding>(R.layout.fragment_record_writing) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var recordTagAdapter: RecordTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = recordViewModel

        //tag
        getTagList()
        initTagAdapter()
        initTagList()

        //search
        clickSearch()
        setSearchResult()

        //date picker
        clickDatePicker()

        //글자수 트래킹
        observingEvaluationCount()
        observingTipCount()

        //글쓰기 등록 버튼
        clickEnrollment(binding.rotationRatingBar.rating)
    }

    private fun initTagAdapter() {
        recordTagAdapter = RecordTagAdapter { tagClick(it) }
        binding.recyclerView.adapter = recordTagAdapter
    }

    private fun getTagList() {
        recordViewModel.getTagListNetwork()
    }

    private fun initTagList() {
        recordViewModel.tagList.observe(viewLifecycleOwner) {
            recordTagAdapter.submitList(it)
        }
    }

    private fun clickSearch() {
        binding.textSearch.setOnClickListener {
            val searchIntent = Intent(requireActivity(), SearchDetailActivity::class.java)
            searchIntent.putExtra(FROM, NOTE)
            resultLauncher.launch(searchIntent)
        }
    }

    private fun setSearchResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    recordViewModel.cocktailId = result.data?.getStringExtra(COCKTAIL_ID) ?: ""
                    binding.textSearch.text = result.data?.getStringExtra(COCKTAIL_NAME) ?: ""
                }
            }
    }

    private fun clickDatePicker() {
        binding.textWhen.setOnClickListener {
            initDatePickerDialog()
        }
    }

    private fun initDatePickerDialog() {
        val calendar: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(), R.style.MySpinnerDatePickerStyle,
            { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_YEAR, day)

                val month = month + 1
                val dateString = String.format("%d년 %02d월 %02d일", year, month, day)

                binding.textWhen.text = dateString
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
        )

        val textColor = ContextCompat.getColor(requireContext(), R.color.navy000782)
        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(textColor)
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(textColor)
    }

    private fun tagClick(tagResponse: TagResponse) {
        recordViewModel.setSelectedTag(tagResponse)
    }

    private fun clickEnrollment(rating: Float) {
        binding.textEnrollment.setOnClickListener {
            recordViewModel.postTastingNote(recordViewModel.makeTastingNote(rating))
        }
    }

    private fun observingEvaluationCount() {
        recordViewModel.cocktailEvaluation.observe(viewLifecycleOwner) {
            recordViewModel.updateEvaluationCount()
        }
    }

    private fun observingTipCount() {
        recordViewModel.cocktailTip.observe(viewLifecycleOwner) {
            recordViewModel.updateTipCount()
        }
    }

    companion object {
        const val COCKTAIL_ID = "COCKTAIL_ID"
        const val COCKTAIL_NAME = "COCKTAIL_NAME"
    }
}