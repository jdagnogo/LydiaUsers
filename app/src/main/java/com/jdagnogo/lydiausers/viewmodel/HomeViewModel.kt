package com.jdagnogo.lydiausers.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.UserRepository
import com.jdagnogo.lydiausers.ui.adapter.AdapterOnclick
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    @VisibleForTesting
    var currentSearchResult: Flow<PagingData<User>>? = null

    fun getUsers(): Flow<PagingData<User>> {
        val newResult: Flow<PagingData<User>> = repository.getUsers()
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}