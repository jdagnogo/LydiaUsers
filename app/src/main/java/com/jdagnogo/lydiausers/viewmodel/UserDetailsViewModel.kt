package com.jdagnogo.lydiausers.viewmodel

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.jdagnogo.lydiausers.model.User
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var user: User



}