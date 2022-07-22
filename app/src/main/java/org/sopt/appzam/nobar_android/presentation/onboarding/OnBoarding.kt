package org.sopt.appzam.nobar_android.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.appzam.nobar_android.R

enum class OnBoarding(
        @StringRes val descriptionRes: Int,
        @DrawableRes val imageRes: Int,
) {
    FIRST(R.string.onBoardingDescriptionFirst, R.drawable.img_onboarding_1),
    SECOND(R.string.onBoardingDescriptionSecond, R.drawable.img_onboarding_2),
    THIRD(R.string.onBoardingDescriptionThird, R.drawable.img_onboarding_3);
}
