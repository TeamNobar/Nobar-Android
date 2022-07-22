package org.sopt.appzam.nobar_android.presentation.main.onboarding

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.mi.walkthroughandroid.ui.fragment.WalkThroughFragment
import org.sopt.appzam.nobar_android.R

class OnBoardingActivity : AppCompatActivity(R.layout.activity_on_boarding),
WalkThroughFragment.WalkThroughFragmentListener{
    override fun onSkipOrFinish(isFromOnSkip: Boolean) {
        findNavController(R.id.fragmentContainer).navigate(R.id.action_walkThroughFragment_to_onBoardingFragment2)
    }
}