package com.jdagnogo.lydiausers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.databinding.FragmentSplashBinding
import com.jdagnogo.lydiausers.databinding.FragmentUserDetailsBinding
import com.jdagnogo.lydiausers.viewmodel.SplashScreenViewModel
import javax.inject.Inject

class SplashScreenFragment : BaseFragment(){
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SplashScreenViewModel

    override fun setContentView(): Int {
      return  R.layout.fragment_splash
    }

    override fun subscribeViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SplashScreenViewModel::class.java)
        viewModel.loadingFinished.observe(viewLifecycleOwner, directionObserver)
    }

    private val directionObserver = Observer<Unit> { _ ->
        val fragment = parentFragmentManager.
        findFragmentByTag(HomeFragment.TAG) as? BaseFragment ?: HomeFragment.newInstance()
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, fragment.getFragmentTag())
            commit()
        }
    }

    override fun setSupportInjection(): Fragment {
        return this
    }

    override fun initDataBiding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return FragmentSplashBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }.root
    }

    override fun initViews() {
        //no op
    }


    override fun getFragmentTag(): String {
        return TAG
    }
    companion object {
        const val TAG = "SplashScreenFragment"
    }
}