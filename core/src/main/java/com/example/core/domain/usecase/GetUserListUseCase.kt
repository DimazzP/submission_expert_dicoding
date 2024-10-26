package com.example.core.domain.usecase

import com.example.core.domain.entity.SearchUser
import com.example.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(query: String): Flow<Result<List<SearchUser>>> {
        return userRepository.getUserList(query)
    }
}