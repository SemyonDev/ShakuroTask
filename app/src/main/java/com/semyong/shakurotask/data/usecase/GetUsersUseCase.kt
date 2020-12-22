package com.semyong.shakurotask.data.usecase

import androidx.paging.DataSource
import com.semyong.shakurotask.data.entities.User

interface GetUsersUseCase {
    suspend fun getUsers(): DataSource.Factory<Int, User>
}