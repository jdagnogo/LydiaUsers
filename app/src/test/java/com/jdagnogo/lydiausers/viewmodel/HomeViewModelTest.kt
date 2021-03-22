package com.jdagnogo.lydiausers.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.repository.UserRepository
import com.jdagnogo.lydiausers.repository.api.UserMapperTest.Companion.FAKE_NAME
import com.jdagnogo.lydiausers.ui.adapter.UserComparator
import com.jdagnogo.lydiausers.ui.adapter.UserListAdapter
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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

    private lateinit var sut: HomeViewModel

    @Mock
    private lateinit var repository: UserRepository

    private lateinit var user: User

    private lateinit var pagingData: PagingData<User>

    private lateinit var adapter: UserListAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        user = User(name = FAKE_NAME)
        pagingData = PagingData.from(listOf(user))
        adapter = UserListAdapter(UserComparator())
        sut = HomeViewModel(repository)
    }

    private fun observeUser() = flow {
        emit(pagingData)
    }

    @Test
    fun `getUsers should return the same data as repository_getUsers `() = runBlocking {
        val fakeFlow = observeUser()
        given(repository.getUsers()).willReturn(fakeFlow)

        sut.getUsers().collect {
            adapter.submitData(it)
            val user = adapter.snapshot().getOrNull(0)
            assertTrue(user?.name == FAKE_NAME)
        }
    }

}