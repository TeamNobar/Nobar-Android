package org.sopt.appzam.nobar_android.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import org.sopt.appzam.nobar_android.databinding.ItemOnboardingPageBinding

class OnBoardingPageAdapter : ListAdapter<OnBoarding, OnBoardingPageAdapter.ViewHolder>(OnBoardingItemComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOnboardingPageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
            private val binding: ItemOnboardingPageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoarding: OnBoarding) {
            binding.onBoarding = onBoarding
        }
    }

    private object OnBoardingItemComparator : DiffUtil.ItemCallback<OnBoarding>() {
        override fun areItemsTheSame(oldItem: OnBoarding, newItem: OnBoarding): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: OnBoarding, newItem: OnBoarding): Boolean {
            return oldItem == newItem
        }
    }
}
