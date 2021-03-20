package com.jdagnogo.lydiausers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.model.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }

    fun bind(user: User?) {
        with(itemView) {
            name.text = user?.first
        }
    }
}