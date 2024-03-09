package com.huntams.domain

import com.huntams.network.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(

    private val userRepository: UserRepository
){

    suspend operator fun invoke() = userRepository.getUsers()
}