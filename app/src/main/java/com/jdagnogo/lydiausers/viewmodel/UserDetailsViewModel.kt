package com.jdagnogo.lydiausers.viewmodel

import androidx.lifecycle.ViewModel
import com.jdagnogo.lydiausers.model.User
import javax.inject.Inject

/**
 * I realize that i made a mistake in my conception as I could have use only one ViewModel and shared it
 * with all fragment.
 */
class UserDetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var user: User
}