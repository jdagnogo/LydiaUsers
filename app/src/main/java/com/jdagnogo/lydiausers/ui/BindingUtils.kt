package com.jdagnogo.lydiausers.ui

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
    if (!nat.isNullOrEmpty()) {
        val drawable = when (nat) {
            "AU" -> R.drawable.australia
            "BR" -> R.drawable.brazil
            else -> R.drawable.france
        }
        Glide.with(view.context).load(drawable).into(view)
    }
}

@BindingAdapter("genderUrl")
fun loadGenderImage(view: AppCompatImageView, gender: String?) {
    if (!gender.isNullOrEmpty()) {
        val drawable = when (gender) {
            "male" -> R.drawable.male
            else -> R.drawable.female
        }
        Glide.with(view.context).load(drawable).into(view)
    }
}