package com.jdagnogo.lydiausers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.databinding.FragmentHomeBinding
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.ui.adapter.AdapterOnclick
import com.jdagnogo.lydiausers.ui.adapter.UserListAdapter
import com.jdagnogo.lydiausers.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment(), AdapterOnclick {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: UserListAdapter

    private var getUsersJob: Job? = null
    private lateinit var viewModel: HomeViewModel

    override fun setContentView(): Int {
        return R.layout.fragment_home
    }

    private fun getUsers() {
        getUsersJob?.cancel()
        getUsersJob = lifecycleScope.launch {
            viewModel.getUsers().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onClick(user: User) {
        (requireActivity() as MainActivity).onNavigationToFragment(UserDetailsFragment.TAG, user)
    }

    override fun subscribeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun setSupportInjection(): Fragment {
        return this
    }

    override fun initDataBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeLoadStateListener {}
    }

    @InternalCoroutinesApi
    override fun initViews() {
        user_list.adapter = adapter
        adapter.listener = this
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            //user_list.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            // Show the retry state if initial load or refresh fails.

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { user_list.scrollToPosition(0) }
        }
        getUsers()
    }

    override fun getFragmentTag(): String {
        return TAG
    }

    companion object {
        fun newInstance() = HomeFragment().apply {
            arguments = bundleOf()
        }

        const val TAG = "HomeFragment"
    }
}