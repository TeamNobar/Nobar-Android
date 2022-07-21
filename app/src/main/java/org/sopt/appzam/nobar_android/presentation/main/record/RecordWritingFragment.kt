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
import org.sopt.appzam.nobar_android.databinding.FragmentRecordWritingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity
import java.util.*

class RecordWritingFragment :
    BaseFragment<FragmentRecordWritingBinding>(R.layout.fragment_record_writing) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var recordTagAdapter: RecordTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //tag
        getTagList()
        initTagAdapter()
        initTagList()

        //search
        clickSearch()
        setSearchResult()

        //date picker
        initDatePickerDialog()
    }

    private fun initTagAdapter() {
        recordTagAdapter = RecordTagAdapter()
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
            searchIntent.putExtra("from", "tastingNote")
            resultLauncher.launch(searchIntent)
        }
    }

    private fun setSearchResult() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val cocktail = result.data?.getStringExtra("cocktail") ?: ""
                    binding.textSearch.text = cocktail
                }
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
                val dateString = String.format("%d.%02d.%02d", year, month, day)

                binding.textWhen.text = dateString
                //하고싶은 행동 적자
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



}