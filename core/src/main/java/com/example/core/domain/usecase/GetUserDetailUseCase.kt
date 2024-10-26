package com.example.core.domain.usecase

import com.example.core.domain.entity.DetailUser
import com.example.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(username: String): Flow<Result<DetailUser>> {
        return userRepository.getUserDetail(username)
    }
}