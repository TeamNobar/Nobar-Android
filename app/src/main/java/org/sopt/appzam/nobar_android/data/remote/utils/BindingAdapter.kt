package org.sopt.appzam.nobar_android.data.remote.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageview: ImageView, url: String?) {
        Glide.with(imageview.context)
            .load(url)
            .into(imageview)
    }
}