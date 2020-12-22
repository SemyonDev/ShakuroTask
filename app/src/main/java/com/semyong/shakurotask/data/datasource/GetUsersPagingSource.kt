package com.semyong.shakurotask.data.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.semyong.shakurotask.data.api.ApiServices
import com.semyong.shakurotask.data.entities.User
import com.semyong.shakurotask.data.helpers.DataErrorManager
import kotlinx.coroutines.runBlocking

class GetUsersPagingSource(
    private val apiServices: ApiServices,
    private val dataErrorManager: DataErrorManager
) : PageKeyedDataSource<Int, User>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        runBlocking {
            try {
                val responseUsers = apiServices.getUsers(since = 0)
                callback.onResult(responseUsers ?: emptyList<User>(), null, 1)
            } catch (throwable: Throwable) {
                dataErrorManager.flow(throwable)
                Log.d("Error", "throwable =" + throwable)
            }
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        runBlocking {
            val pageNumber = params.key
            try {
                val responseUsers = apiServices.getUsers(since = pageNumber)


                callback.onResult(
                    responseUsers ?: emptyList<User>(),
                    params.key + 1
                )
            } catch (throwable: Throwable) {
                dataErrorManager.flow(throwable)
                Log.d("Error", "throwable =" + throwable)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) = Unit

}