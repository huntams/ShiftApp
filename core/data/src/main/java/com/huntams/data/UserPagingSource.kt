package com.huntams.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.huntams.data.mappers.UserMapper
import com.huntams.model.User
import com.huntams.network.UserApiService
import kotlin.random.Random

class UserPagingSource(
    private val userMapper: UserMapper,
    private val apiService: UserApiService,
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        try {
            val page = params.key ?: Random.nextInt(1,1000)
            val response = apiService.getUsers(
                seed = "abc$page"
            )
            val data = response.results.map {
                userMapper.apiToModel(it, response.info)
            }
            return LoadResult.Page(
                data = data,
                nextKey = page + 1,
                prevKey = if (page == 1) null else (page -1),
            )
        } catch (e: Exception) {
            Log.e("test_load",e.message.toString())
            return LoadResult.Error(e)
        }

    }


}