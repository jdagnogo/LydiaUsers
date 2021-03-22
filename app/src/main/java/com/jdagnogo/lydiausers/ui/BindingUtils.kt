package com.jdagnogo.lydiausers.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jdagnogo.lydiausers.R

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, image: String?) {
    if (!image.isNullOrEmpty()) {
        Glide.with(view.context).load(image).circleCrop().into(view)
    }
}

@BindingAdapter("natUrl")
fun loadNatImage(view: AppCompatImageView, nat: String?) {
    val drawable = when (nat) {
        "AU" -> R.drawable.australia
        "BR" -> R.drawable.brazil
        else -> R.drawable.france
    }
    loadImage(view, nat, drawable)

}

private fun loadImage(view: AppCompatImageView, value: String?, drawable: Int?) {
    if (!value.isNullOrEmpty()) {
        Glide.with(view.context).load(drawable).into(view)
        view.tag = drawable
    }
}

@BindingAdapter("genderUrl")
fun loadGenderImage(view: AppCompatImageView, gender: String?) {
    val drawable = when (gender) {
        "male" -> R.drawable.male
        else -> R.drawable.female
    }
    loadImage(view, gender, drawable)
}