package com.semyong.shakurotask.di

import com.semyong.shakurotask.presentation.fragments.listfragment.HomeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    viewModel { HomeListViewModel(get(), get()) }
}
