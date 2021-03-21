package com.jdagnogo.lydiausers.ui

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, image: String?) {
    if (!image.isNullOrEmpty()) {
        Glide.with(view.context).load(image).into(view)
    }
}