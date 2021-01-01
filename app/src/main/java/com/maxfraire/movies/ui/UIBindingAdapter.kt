package com.maxfraire.movies.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.maxfraire.movies.util.orFalse

object UIBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["android:image_url"])
    fun loadImage(view: ImageView, url: String? = null) {
        Glide.with(view.context)
            .load(url)
            .transform(CenterCrop())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("android:is_visible")
    fun hideView(view: View, isVisible: Boolean?) {
        view.visibility = if (isVisible.orFalse()) View.VISIBLE else View.GONE
    }
}