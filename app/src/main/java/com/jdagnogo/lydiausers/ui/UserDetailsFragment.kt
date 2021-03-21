package com.jdagnogo.lydiausers.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.model.User
import com.jdagnogo.lydiausers.viewmodel.UserDetailsViewModel
import javax.inject.Inject

class UserDetailsFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserDetailsViewModel

    private fun getUser(): User {
        return arguments?.getSerializable(USER_KEY) as? User ?: User()
    }

    override fun setContentView(): Int {
        return R.layout.fragment_user_details
    }

    override fun subscribeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserDetailsViewModel::class.java)
        viewModel.user = getUser()
    }

    override fun setSupportInjection(): Fragment {
        return this
    }

    override fun initViews() {

    }

    override fun getFragmentTag(): String {
        return TAG
    }

    companion object {
        fun newInstance(user: User) = UserDetailsFragment().apply {
            arguments = bundleOf().apply {
                putSerializable(USER_KEY, user)
            }
        }

        const val TAG = "UserDetailsFragment"
        const val USER_KEY = "USER_KEY"
    }

}