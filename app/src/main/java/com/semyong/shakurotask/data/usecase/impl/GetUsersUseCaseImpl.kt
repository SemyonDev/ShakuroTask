package com.semyong.shakurotask.data.usecase.impl

import androidx.paging.DataSource
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.data.repository.GetUsersRepository
import com.semyong.shakurotask.data.usecase.GetUsersUseCase

class GetUsersUseCaseImpl(private val getUsersRepository: GetUsersRepository): GetUsersUseCase {
    override suspend fun getUsers(): DataSource.Factory<Int, User> = getUsersRepository.getUsers()
}