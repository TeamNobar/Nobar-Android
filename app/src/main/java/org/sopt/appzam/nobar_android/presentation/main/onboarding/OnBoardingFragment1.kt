package org.sopt.appzam.nobar_android.presentation.main.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.mi.walkthroughandroid.animation.enums.ContentAnimationType
import com.mi.walkthroughandroid.animation.enums.IndicatorAnimationType
import com.mi.walkthroughandroid.data.WalkThroughScreenModel
import com.mi.walkthroughandroid.dsl.walkThrough
import com.mi.walkthroughandroid.ui.enums.IndicatorStyle
import org.sopt.appzam.nobar_android.R
import org.sopt.appzam.nobar_android.databinding.FragmentOnBoarding1Binding
import org.sopt.appzam.nobar_android.presentation.base.BaseFragment

class OnBoardingFragment1 :
    BaseFragment<FragmentOnBoarding1Binding>(R.layout.fragment_on_boarding1) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boardAction()
    }

    private fun boardAction() {
        val onBoardingScreens = ArrayList<WalkThroughScreenModel>().apply {
            add(
                WalkThroughScreenModel(
                    image = R.drawable.ic_img_bluehawaii,
                    title = R.string.onBoarding1,
                    description = R.string.homeAllSee
                )
            )
            add(
                WalkThroughScreenModel(
                    image = R.drawable.ic_img_cinderella,
                    title = R.string.onBoarding2,
                    description = R.string.homeAllSee
                )
            )
            add(
                WalkThroughScreenModel(
                    image = R.drawable.ic_img_peachcrush,
                    title = R.string.onBoarding3,
                    description = R.string.homeAllSee
                )
            )
        }

        val walkThroughIntent = walkThrough {
            with { requireContext() } // Pass Content
            walkThroughScreens { onBoardingScreens } // Pass Screens List
            titleColor { R.color.black } // Title Color
            descriptionColor { R.color.black } // Description Color
            titleFontFamily { R.font.pretendard_bold } // Title FontFamily
            descriptionFontFamily { R.font.pretendard_medium } // Description FontFamily
            titleFontSize { 8.0F } // Title Font Size
            descriptionFontSize { 4.0F } // Description Font Size
            skipButtonFontFamily { R.font.pretendard_medium } // Skip Button FontFamily
            skipButtonColor { R.color.black } // Skip Button Color
            backgroundColor { R.color.white } // Background Color for screen
            activeIndicatorColor { R.color.blue0E30AA } // Active Indicator Color
            inactiveIndicatorColor { R.color.blue133ed9 } // Inactive Indicator Color
            indicatorStyle { IndicatorStyle.ROUNDED_RECTANGLE } // Indicator Style CIRCLE, RECTANGLE, SQUARE, ROUNDED_RECTANGLE, VECTOR, BITMAP
            activeVectorDrawableSize { 50 } // Active Vector Drawable Size in PX
            inactiveVectorDrawableSize { 50 } // Inactive Vector Drawable Size in PX
            activeBitmapDrawableSize { 50 } // Inactive Bitmap Drawable Size in PX
            inactiveBitmapDrawableSize { 50 } // Inactive Bitmap Drawable Size in PX
            indicatorGap { R.dimen.dp5 } // Gap Between Indicators
            indicatorAnimationType { IndicatorAnimationType.NONE } // Indicator Animation Type  SCALE, SMOOTH_SCALE, LEFT_IN, RIGHT_IN, FLIP
            activeIndicatorWidth { R.dimen.dp30 } // Active Indicator Width
            activeIndicatorHeight { R.dimen.dp8 } // Active Indicator Height
            inactiveIndicatorWidth { R.dimen.dp8 } // Inactive Indicator Width
            inactiveIndicatorHeight { R.dimen.dp8 } // Inactive Indicator Height
            contentAnimationType { ContentAnimationType.FADE } // Content Animation Type   FADE, SLIDER, SCALE, TOP_IN, BOTTOM_IN, BOUNCE
            buttonColor { R.color.gray60 } // Next/Finish Button Color
            buttonTextColor { R.color.white } // Next/Finish Button Text color
            skipButtonFontSize { 4.0F } // Skip Button Font Size

        }
        val walkThroughBundle = walkThroughIntent.extras
        findNavController().navigate(
            R.id.action_onBoardingFragment1_to_walkThroughFragment,
            walkThroughBundle
        )


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}