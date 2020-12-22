package com.semyong.shakurotask.data.api

import com.semyong.shakurotask.data.entities.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("users?since=0")
    suspend fun getUsers(@Query("since") since: Int): List<User>

}