package org.sopt.appzam.nobar_android.presentation.main.record

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.ActivityRecordBinding
import org.sopt.appzam.nobar_android.presentation.base.BaseActivity

class RecordActivity : BaseActivity<ActivityRecordBinding>(R.layout.activity_record) {
    private val recordViewModel: RecordViewModel by viewModels()
    private var whichFragment = ""
    private var whichNote = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentString()
        decideFragment()
    }

    private fun getIntentString() {
        whichFragment = intent.getStringExtra(MODE) ?: ""
        whichNote = intent.getStringExtra(NOTE_ID) ?: ""
    }

    private fun decideFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        when (whichFragment) {
            NEW -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordWritingFragment())
            }
            MODIFY -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordWritingFragment())
                recordViewModel.getTastingNote(whichNote)
            }
            READ -> {
                transaction.add(R.id.fragmentContainerViewRecord, RecordReadFragment())
                recordViewModel.getTastingNote(whichNote)
            }
        }
    }

    companion object {
        const val NOTE_ID = "NOTE_ID"
        const val MODE = "MODE"
        const val NEW = "NEW"
        const val MODIFY = "MODIFY"
        const val READ = "READ"
    }
}