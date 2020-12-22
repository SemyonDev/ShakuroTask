package com.semyong.shakurotask.data.repository

import androidx.paging.DataSource
import com.semyong.shakurotask.data.entities.User

interface GetUsersRepository {
    suspend fun getUsers(): DataSource.Factory<Int, User>
}