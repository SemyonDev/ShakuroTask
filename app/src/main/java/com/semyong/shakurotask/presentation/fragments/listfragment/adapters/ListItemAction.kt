package com.semyong.shakurotask.presentation.fragments.listfragment.adapters

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.databinding.ItemUserBinding


interface ListItemAction {
    fun onItemClick(user: User, image: AppCompatImageView, title: TextView)
}