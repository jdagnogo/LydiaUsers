package com.jdagnogo.lydiausers.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jdagnogo.lydiausers.R
import com.jdagnogo.lydiausers.model.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserViewHolder(view: View, var adapterOnclick: AdapterOnclick) :
    RecyclerView.ViewHolder(view) {
    companion object {
        fun create(
            parent: ViewGroup,
            adapterOnclick: AdapterOnclick
        ): UserViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view, adapterOnclick)
        }
    }

    fun bind(user: User?) {
        user?.let {
            with(itemView) {
                container.setOnClickListener {
                    adapterOnclick.onClick(user)
                }
                user_name.text = user.name
                user_email.text = user.email
                Glide.with(context)
                    .load(user.image)
                    .circleCrop()
                    .into(user_image)
            }
        }
    }
}