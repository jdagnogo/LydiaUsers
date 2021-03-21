package com.jdagnogo.lydiausers.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdagnogo.lydiausers.model.User
import javax.inject.Inject

class UserListAdapter @Inject constructor(userComparator: UserComparator) :
    PagingDataAdapter<User, RecyclerView.ViewHolder>(userComparator) {
    lateinit var listener: AdapterOnclick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(getItem(position))
    }
}