package com.semyong.shakurotask.presentation.fragments.listfragment.adapters

import androidx.recyclerview.widget.DiffUtil
import com.semyong.shakurotask.data.entities.User


class DiffUtilCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}