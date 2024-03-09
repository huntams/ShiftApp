package com.huntams.network

import androidx.paging.PagingData
import com.huntams.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<PagingData<User>>
    suspend fun getUser(seed:String): User
}