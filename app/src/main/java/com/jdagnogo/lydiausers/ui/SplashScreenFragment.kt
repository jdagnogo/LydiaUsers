package com.jdagnogo.lydiausers.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jdagnogo.lydiausers.R
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
    }

    override fun setSupportInjection(): Fragment {
        return this
    }

    override fun initViews() {
        //no op
    }


    override fun getFragmentTag(): String {
        return TAG
    }
    companion object {
        fun newInstance() = SplashScreenFragment().apply {
            arguments = bundleOf()
        }
        const val TAG = "SplashScreenFragment"
    }
}