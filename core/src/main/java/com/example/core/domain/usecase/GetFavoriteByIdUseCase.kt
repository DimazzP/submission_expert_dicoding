package com.example.core.domain.usecase

import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import com.example.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteByIdUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    fun execute(username: String): Flow<Favorite?> {
        return repository.getFavoriteById(username)
    }
}