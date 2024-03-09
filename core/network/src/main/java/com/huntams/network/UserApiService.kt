package com.huntams.network

import com.huntams.network.model.ApiUser
import com.huntams.network.model.PageDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {
    @GET("/api")
    suspend fun getUsers(
        @Query("seed") seed: String,
    ): PageDataResponse<ApiUser>



}