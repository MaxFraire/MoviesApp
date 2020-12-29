package com.maxfraire.movies.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

object UIBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["android:image_url"])
    fun loadImage(view: ImageView, url: String? = null) {
        Glide.with(view.context)
            .load(url)
            .transform(CenterCrop())
            .into(view)
    }
}