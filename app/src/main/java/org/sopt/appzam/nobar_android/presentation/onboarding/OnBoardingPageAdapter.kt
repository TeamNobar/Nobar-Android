package org.sopt.appzam.nobar_android.presentation.onboarding

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import org.sopt.appzam.nobar_android.databinding.ItemOnboardingPageBinding

class OnBoardingPageAdapter {

    class ViewHolder(
            private val binding: ItemOnboardingPageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(description: String, @DrawableRes imageRes: Int) {
            binding.textOnBoardingDescription.text = description
            binding.imageOnBoarding.setImageResource(imageRes)
        }
    }
}
