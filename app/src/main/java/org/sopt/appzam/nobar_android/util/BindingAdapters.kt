package org.sopt.appzam.nobar_android.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageview: ImageView, url: Int) {
        Glide.with(imageview.context)
            .load(url)
            .into(imageview)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(imageview: ImageView, url: String?) {
        Glide.with(imageview.context)
            .load(url)
            .circleCrop()
            .into(imageview)
    }
}