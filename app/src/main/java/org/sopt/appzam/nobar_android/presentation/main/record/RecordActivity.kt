package org.sopt.appzam.nobar_android.presentation.main.record

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityRecordBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class RecordActivity : BaseActivity<ActivityRecordBinding>(R.layout.activity_record) {
    private val recordViewModel: RecordViewModel by viewModels()
    private var whichFragment = ""
    private var whichNoteId = ""
    private var whichNoteName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentString()
        decideFragment()
    }

    private fun getIntentString() {
        whichFragment = intent.getStringExtra(MODE) ?: ""
        whichNoteId = intent.getStringExtra(NOTE_ID) ?: ""
        whichNoteName = intent.getStringExtra(NOTE_NAME) ?: ""
    }

    private fun decideFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        when (whichFragment) {
            RECIPE -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordWritingFragment()).commit()
                recordViewModel.cocktailId = whichNoteId
                recordViewModel.cocktailName.value = whichNoteName
            }
            NEW -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordWritingFragment()).commit()
            }
            MODIFY -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordWritingFragment()).commit()
                recordViewModel.getTastingNote(whichNoteId)
            }
            READ -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordReadFragment()).commit()
                recordViewModel.getTastingNote(whichNoteId)
            }
        }
    }

    companion object {
        const val NOTE_NAME = "NOTE_NAME"
        const val NOTE_ID = "NOTE_ID"

        const val MODE = "MODE"
        const val RECIPE = "RECIPE"
        const val NEW = "NEW"
        const val MODIFY = "MODIFY"
        const val READ = "READ"
    }
}