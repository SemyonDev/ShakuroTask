package com.semyong.shakurotask.presentation.fragments.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import androidx.transition.TransitionInflater
import com.semyong.shakurotask.databinding.FragmentHomedetailsBinding
import com.semyong.shakurotask.presentation.fragments.BaseFragment
import com.semyong.shakurotask.presentation.helpers.load
import java.util.concurrent.TimeUnit


class HomeListDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentHomedetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomedetailsBinding.inflate(inflater)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            HomeListDetailsFragmentArgs.fromBundle(it).user.let {
                binding.detailsAvatar.load(it.avatar_url.toString())
                binding.detailsLogin.text = it.login
                binding.detailsAvatar.transitionName = it.id.toString()
                binding.detailsLogin.transitionName = it.login + it.id.toString()
            }
        }

        binding.detailsBack.setOnClickListener {
            Navigation.findNavController(binding.detailsBack).popBackStack()
        }
    }
}
