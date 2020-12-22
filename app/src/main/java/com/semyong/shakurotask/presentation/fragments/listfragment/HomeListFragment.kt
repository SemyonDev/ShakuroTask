package com.semyong.shakurotask.presentation.fragments.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.databinding.FragmentHomelistBinding
import com.semyong.shakurotask.presentation.fragments.BaseFragment
import com.semyong.shakurotask.presentation.fragments.listfragment.adapters.ListItemAction
import com.semyong.shakurotask.presentation.fragments.listfragment.adapters.UsersAdapter
import com.semyong.shakurotask.presentation.helpers.showToastLong
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeListFragment : BaseFragment(), ListItemAction {

    private val viewModel: HomeListViewModel by viewModel()
    private val usersAdapter = UsersAdapter(this)
    private lateinit var binding: FragmentHomelistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getusers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomelistBinding.inflate(inflater)
        initViews()
        observeViewMOodel()

        return binding.root
    }

    private fun initViews() {
        binding.userListSwipeContainer.isRefreshing = true
        binding.userListRv.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.userListSwipeContainer.setOnRefreshListener {
            usersAdapter.submitList(null)
            viewModel.getusers()
        }

    }

    private fun observeViewMOodel() {
        viewModel.mUsersResult.observe(viewLifecycleOwner, Observer {
            it.observe(viewLifecycleOwner, Observer {
                println(it.toTypedArray().contentToString())
                usersAdapter.submitList(it)
                binding.userListSwipeContainer.isRefreshing = false
            }
            )
        }
        )

        viewModel.mError.observe(viewLifecycleOwner, Observer { errorMessage ->
            binding.userListSwipeContainer.isRefreshing = false
            context?.showToastLong(errorMessage)
        })
    }

    override fun onItemClick(user: User, image: AppCompatImageView, title: TextView) {
        goToDetailsFragment(user, image, title)
    }

    private fun goToDetailsFragment(user: User, image: AppCompatImageView, title: TextView) {
        val direction: NavDirections = HomeListFragmentDirections.actionGotToDetailsFragment(user)

        val extras = FragmentNavigatorExtras(
            image to user.id.toString(),
            title to (user.login + user.id)
        )

        findNavController(this@HomeListFragment.binding.userListRv).navigate(direction, extras)
    }

}