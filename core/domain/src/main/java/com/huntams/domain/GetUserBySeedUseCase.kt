package com.huntams.domain

import com.huntams.network.UserRepository
import javax.inject.Inject

class GetUserBySeedUseCase @Inject constructor(

    private val userRepository: UserRepository
){

    suspend operator fun invoke(seed:String) = userRepository.getUser(seed)
}