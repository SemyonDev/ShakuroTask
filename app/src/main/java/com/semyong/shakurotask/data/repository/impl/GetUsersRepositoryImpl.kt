package com.semyong.shakurotask.data.repository.impl

import androidx.paging.DataSource
import com.semyong.shakurotask.data.datasource.GetUsersDataSourceFactory
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.data.repository.GetUsersRepository

class GetUsersRepositoryImpl(
    private val getUsersDataSourceFactory: GetUsersDataSourceFactory
) : GetUsersRepository {
    override suspend fun getUsers(): DataSource.Factory<Int, User> = getUsersDataSourceFactory
}