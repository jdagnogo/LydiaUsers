package com.jdagnogo.lydiausers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jdagnogo.lydiausers.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(userRepository: UserRepository) :
    ViewModel() {
    var loadingFinished = liveData(Dispatchers.IO) {
        emit(userRepository.startLogin())
    }
}