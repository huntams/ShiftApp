package com.huntams.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.huntams.data.mappers.UserMapper
import com.huntams.model.User
import com.huntams.network.UserApiService
import com.huntams.network.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApiService: UserApiService,
    private val userMapper: UserMapper
) : UserRepository {
    override suspend fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(1, enablePlaceholders = true),
            pagingSourceFactory = { UserPagingSource(userMapper, userApiService) }
        ).flow
    }

    override suspend fun getUser(seed: String): User {
        val data =userApiService.getUsers(seed)
        return data.results.map {
            userMapper.apiToModel(it,data.info)
        }.first()
    }
}