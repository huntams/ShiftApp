package com.huntams.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.huntams.data.base.BaseViewModel
import com.huntams.domain.GetUsersUseCase
import com.huntams.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : BaseViewModel() {
    private val _usersLiveData = MutableLiveData<PagingData<User>>()
    val usersLiveData: LiveData<PagingData<User>> = _usersLiveData


    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch {
                getUsersUseCase().cachedIn(viewModelScope).collect {
                    _usersLiveData.postValue(it)
                }
        }

    }
}
