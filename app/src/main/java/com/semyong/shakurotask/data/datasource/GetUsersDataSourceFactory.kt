package com.semyong.shakurotask.data.datasource

import androidx.paging.DataSource
import com.semyong.shakurotask.data.api.ApiServices
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.data.helpers.DataErrorManager

class GetUsersDataSourceFactory(private val apiServices: ApiServices, private val dataErrorManager: DataErrorManager) : DataSource.Factory<Int, User>()  {
    override fun create(): DataSource<Int, User> = GetUsersPagingSource(apiServices, dataErrorManager)
}