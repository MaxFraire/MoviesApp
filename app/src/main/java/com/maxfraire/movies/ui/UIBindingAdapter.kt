package com.maxfraire.movies.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.android.material.transition.MaterialFadeThrough

object UIBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleIfNotNull")
    fun visibleIfNotNull(view: View, target: Any?) {
        view.isVisible = target != null
    }

    @JvmStatic
    @BindingAdapter("visible")
    fun visible(view: View, value: Boolean) {
        view.isVisible = value
    }

    @JvmStatic
    @BindingAdapter("animateVisibility")
    fun visibleWithAnim(view: View, value: Boolean) {
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, MaterialFadeThrough())
        view.isVisible = value
    }

    @JvmStatic
    @BindingAdapter("textOrGoneIfEmpty")
    fun textOrGoneIfEmpty(view: TextView, s: CharSequence?) {
        view.text = s
        view.isGone = s.isNullOrEmpty()
    }

    @JvmStatic
    @BindingAdapter("goneIfNull")
    fun goneIfNull(view: View, value: Any?) {
        view.isGone = value == null
    }

    @JvmStatic
    @BindingAdapter("imageUrl", "imagePlaceholder", requireAll = false)
    fun loadImage(view: ImageView, url: String? = null, default: Drawable? = null) {
        Glide.with(view.context)
            .load(url)
            .placeholder(default)
            .error(default)
            .transform(CenterCrop())
            .into(view)
    }
}