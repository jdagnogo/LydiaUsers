package com.jdagnogo.lydiausers.viewmodel

import androidx.lifecycle.ViewModel
import com.jdagnogo.lydiausers.model.User
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var user: User

}