package com.semyong.shakurotask.di

import com.semyong.shakurotask.data.datasource.GetUsersDataSourceFactory
import com.semyong.shakurotask.data.helpers.DataErrorManager
import com.semyong.shakurotask.data.repository.GetUsersRepository
import com.semyong.shakurotask.data.repository.impl.GetUsersRepositoryImpl
import com.semyong.shakurotask.data.usecase.GetUsersUseCase
import com.semyong.shakurotask.data.usecase.impl.GetUsersUseCaseImpl
import org.koin.dsl.module

val dataModule = module {
    single { GetUsersDataSourceFactory(get(), get()) }
}

val errorManagerModule = module {
    single { DataErrorManager() }
}

val useCaseModule = module {
    single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
}

val repositoryModule = module {
    single<GetUsersRepository> { GetUsersRepositoryImpl(get()) }
}