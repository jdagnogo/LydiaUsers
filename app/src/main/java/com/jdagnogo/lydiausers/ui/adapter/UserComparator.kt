package com.jdagnogo.lydiausers.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jdagnogo.lydiausers.model.User

class UserComparator :  DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.email == newItem.email
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}