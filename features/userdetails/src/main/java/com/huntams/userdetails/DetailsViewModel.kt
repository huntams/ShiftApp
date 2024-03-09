package com.huntams.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huntams.data.base.BaseViewModel
import com.huntams.data.base.ResultLoader
import com.huntams.domain.GetUserBySeedUseCase
import com.huntams.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getUserBySeedUseCase: GetUserBySeedUseCase
) : BaseViewModel() {
    private val _userLiveData = MutableLiveData<ResultLoader<User>>()
    val userLiveData: LiveData<ResultLoader<User>> = _userLiveData
    fun getUser(seed: String) {
        _userLiveData.loadData { getUserBySeedUseCase(seed) }
    }
}