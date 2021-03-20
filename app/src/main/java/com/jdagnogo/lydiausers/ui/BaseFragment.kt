package com.jdagnogo.lydiausers.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        subscribeViewModel()
        return inflater.inflate(setContentView(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(setSupportInjection())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    abstract fun setContentView(): Int
    abstract fun subscribeViewModel()
    abstract fun setSupportInjection(): Fragment
    abstract fun initViews()
    abstract fun getFragmentTag():String
}