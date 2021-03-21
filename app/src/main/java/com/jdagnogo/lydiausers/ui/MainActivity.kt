package com.jdagnogo.lydiausers.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentActivity
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.model.User
import dagger.android.AndroidInjection

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNavigationToFragment(tag: String, user: User = User()): Boolean {
        val fragment: BaseFragment = when (tag) {
            HomeFragment.TAG -> {
                supportFragmentManager.findFragmentByTag(tag)
                    ?: HomeFragment.newInstance()
            }
            else -> {
                supportFragmentManager.findFragmentByTag(tag)
                    ?: UserDetailsFragment.newInstance(user)
            }
        } as BaseFragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment, fragment.getFragmentTag())
            addToBackStack(null)
            commit()
        }
        return true
    }
}