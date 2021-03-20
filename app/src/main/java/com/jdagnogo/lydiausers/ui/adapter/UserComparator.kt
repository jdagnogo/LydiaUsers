package com.jdagnogo.lydiausers.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jdagnogo.lydiausers.model.User

class UserComparator :  DiffUtil.ItemCallback<User>(){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}