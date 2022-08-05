package org.sopt.appzam.nobar_android.presentation.main.record

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.willy.ratingbar.BaseRatingBar
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.data.remote.response.TagResponse
import org.sopt.appzam.nobar_android.databinding.FragmentRecordWritingBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment
import org.sopt.appzam.nobar_android.presentation.main.mypage.MyPageTastingFragment
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.FROM
import org.sopt.appzam.nobar_android.presentation.main.search.SearchDetailActivity.Companion.NOTE
import org.sopt.appzam.nobar_android.util.LoadingDialog
import java.util.*

class RecordWritingFragment :
    BaseFragment<FragmentRecordWritingBinding>(R.layout.fragment_record_writing) {
    private val recordViewModel: RecordViewModel by activityViewModels()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var recordTagAdapter: RecordTagAdapter
    private var ratingValue = 0.5f

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

        //별점 트래킹
        binding.rotationRatingBar.setOnRatingChangeListener(Listener())

        //글쓰기 등록 버튼
        clickEnrollment()

        observingName()
        observingDate()
        observingTag()

        //종료
        clickX()
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
                val tmpDate = String.format("%d%02d%02d", year, month, day)
                binding.textWhen.text = dateString
                recordViewModel.tmpDate = tmpDate
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

    private fun clickEnrollment() {
        binding.textEnrollment.setOnClickListener {
            recordViewModel.postTastingNote(recordViewModel.makeTastingNote(ratingValue))
            showLoadingView()
        }
    }

    private fun sendIntentAndFinish(){
        val intent = Intent(requireContext(), MyPageTastingFragment::class.java)
        activity?.setResult(RESULT_OK, intent)
        activity?.finish()
    }

    private fun showLoadingView(){
        val loadingDialog = LoadingDialog()
        recordViewModel.writingSendComplete.observe(viewLifecycleOwner){
            if(!it){
                loadingDialog.show(childFragmentManager, "loader")
            }
            else{
                loadingDialog.dismissAllowingStateLoss()
                sendIntentAndFinish()
            }
        }
    }

    private fun clickX() {
        binding.imageX.setOnClickListener {
            val intent = Intent(requireContext(), MyPageTastingFragment::class.java)
            activity?.setResult(RESULT_CANCELED, intent)
            activity?.finish()
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

    private fun observingName() {
        recordViewModel.cocktailName.observe(viewLifecycleOwner) {
            checkRegisterValidation()
        }
    }

    private fun observingDate() {
        recordViewModel.drinkingDate.observe(viewLifecycleOwner) {
            checkRegisterValidation()
        }
    }

    private fun observingTag() {
        recordViewModel.isTagClicked.observe(viewLifecycleOwner) {
            checkRegisterValidation()
        }
    }

    private fun checkRegisterValidation() {
        if (recordViewModel.canRegister()) {
            binding.textEnrollment.isEnabled = true
            binding.textEnrollment.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.pinkE93370
                )
            )
        } else {
            binding.textEnrollment.isEnabled = false
            binding.textEnrollment.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grayDD
                )
            )
        }
    }

    inner class Listener() : BaseRatingBar.OnRatingChangeListener {
        override fun onRatingChange(ratingBar: BaseRatingBar?, rating: Float, fromUser: Boolean) {
            ratingValue = rating
        }
    }

    companion object {
        const val COCKTAIL_ID = "COCKTAIL_ID"
        const val COCKTAIL_NAME = "COCKTAIL_NAME"
    }
}