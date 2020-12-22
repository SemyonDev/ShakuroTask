package com.semyong.shakurotask.presentation.fragments.listfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.databinding.ItemUserBinding
import com.semyong.shakurotask.presentation.helpers.load

class UsersAdapter(val actions: ListItemAction) :
    PagedListAdapter<User, UsersAdapter.UserViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                currentList?.get(adapterPosition)?.let { it1 -> actions.onItemClick(it1, binding.itemUserAvatar, binding.itemUserLogin) }
            }
        }

        fun bind(user: User) {
            binding.itemUserLogin.text = user.login
            binding.itemUserId.text = user.id.toString()
            user.avatar_url?.let { binding.itemUserAvatar.load(it) }
            binding.itemUserAvatar.transitionName = user.id.toString()
            binding.itemUserLogin.transitionName = user.login + user.id.toString()
        }
    }
}