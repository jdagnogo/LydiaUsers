package com.jdagnogo.lydiausers.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.UserRepository
import com.jdagnogo.lydiausers.repository.api.UserMapperTest.Companion.FAKE_NAME
import com.nhaarman.mockitokotlin2.given
import getOrAwaitValue
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var user: User

    private lateinit var pagingData: PagingData<User>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        user = User(name = FAKE_NAME)
        pagingData = PagingData.from(listOf(user))
        viewModel = HomeViewModel(repository)
    }

    private fun observeUser() = flow {
        emit(pagingData)
    }

    @Test
    fun `getUsers should return the same data as repository_getUsers `() = runBlocking {
        val fakeFlow = observeUser()
        given(repository.getUsers()).willReturn(fakeFlow)

        val result= viewModel.getUsers()

        //assertTrue(result.first() == pagingData)
    }

}